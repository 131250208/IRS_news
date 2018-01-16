package com.irs_news.service.impl;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.irs_news.mapper.NewsMapper;
import com.irs_news.mapper.VocabularyMapper;
import com.irs_news.pojo.Comment;
import com.irs_news.pojo.News;
import com.irs_news.pojo.Word;
import com.irs_news.service.NewsService;

import mybatis.inverted_element;

@Service
public class NewsServiceImpl implements NewsService {
	@Autowired
	NewsMapper newsMapper;
	@Autowired
	VocabularyMapper wordMapper;

	private static int number_news_of_everypage = 10; // 每页显示的新闻数量
	private static List<Integer> old_doc_list = null;
	private List<inverted_element> final_winner;
	private int start_pos;
	private static List<Integer> doc_list;

	@Override
	public Map<String, Object> search(List<Integer> id_list, String ranking_indicator, int page_index,
			boolean same_search) {
		// TODO Auto-generated method stub
		// @杨寿国
		// 1、用工具将search_text切词
		// 2、用切好的词搜索 轮排索引树 找到 所有的词项id
		// 3、用词项id的list搜数据库找到所有词项对应的倒排索引
		// 4、快速合并倒排索引并返回排名最高的1000个文档的id
		// 5、文档id的list赋值给以下id_list

		// 通过词典找到词项对象

		// 用来装载返回的news_list和total_size的map
		// 初始化
		Date t0 = new Date();

		Map<String, Object> resMap = new HashMap<String, Object>();
		resMap.put("total_size", "0");
		resMap.put("news_list", new ArrayList<News>());

		// 如果不是同一个查询
		if (!same_search) {
			if (id_list == null || id_list.size() == 0)
				return resMap;

			List<Word> word_list = wordMapper.get_words_byIDs(id_list);

			if (word_list.size() == 0)
				return resMap;

			// 对得到的胜者表进行合并，并且排序
			final_winner = getListFrombyte(word_list.get(0).getWinner1st());

			System.out.println(word_list.get(0).getWord() + "胜者表长度为" + final_winner.size());

			double last_idf = word_list.get(0).getIdf();
			for (int i = 1; i < word_list.size(); ++i) {
				System.out.print(word_list.get(i).getWord() + "胜者表长度为");
				final_winner = getUnionbyDocID(final_winner, getListFrombyte(word_list.get(i).getWinner1st()), last_idf,
						word_list.get(i).getIdf());
			}

			// 对合并后的胜者表进行按照 wfidf 排序
			Collections.sort(final_winner, new Comparator<inverted_element>() {

				@Override
				public int compare(inverted_element o1, inverted_element o2) {
					// TODO Auto-generated method stub
					if (o1.getWf() > o2.getWf()) {
						return -1;
					} else if (o1.getWf() == o2.getWf()) {
						return 0;
					}
					return 1;
				}
			});
			// for (int i = 0 ; i < 10 ; ++i) {
			// System.out.println(final_winner.get(i).getWf()+" id :
			// "+final_winner.get(i).getDocID());
			// }
			System.out.println("排序合并之后的文档记录长度为" + final_winner.size());
			if (final_winner.isEmpty())
				return resMap;

			doc_list = new ArrayList<Integer>();
			for (int i = 0; i < final_winner.size() && i < 1000; ++i) {
				doc_list.add(final_winner.get(i).getDocID());
			}
		}
		// System.out.println("page_index"+page_index);
		// @杨寿国，提供相关度前1000的新闻的id_list, 按相关度顺序排列

		Date t1 = new Date();
		List<News> news_list = newsMapper.search(doc_list);
		// List<News> news_list = getNews(doc_list);
		System.out.println("从数据库中得到新闻条数为" + news_list.size());
		Date t2 = new Date();

		for (News n : news_list) {
			// 将新闻里的评论分成两个list，分别为褒贬，方便前端调用
			List<Comment> com_up = new ArrayList<Comment>();
			List<Comment> com_down = new ArrayList<Comment>();
			if (n.getComments() == null)
				continue;
			for (Comment c : n.getComments()) {
				if (c.getEmotion() != -1)
					com_up.add(c);
				else {
					com_down.add(c);
				}
			}
			n.setComments_up(com_up);
			n.setComments_down(com_down);

			// // 按id查找相关新闻
			// n.setNews_sim(newsMapper.get_simNews(n.getId()));
		}

		Date t3 = new Date();
		// 按照某种方式进行重新排序
		Resort_newslist(news_list, ranking_indicator);
		Date t4 = new Date();

		int size = news_list.size();
		// @杨寿国
		// 若是按时间和热度排序
		// news_list还需要在这里再重新排序

		// 排序后按页码返回10个
		page_index -= 1;
		int start_pos = page_index * number_news_of_everypage;
		if (page_index * number_news_of_everypage > news_list.size()) {
			System.err.println("page_index error");
			return resMap;
		}

		int end_pos = (page_index + 1) * number_news_of_everypage;
		if ((page_index + 1) * number_news_of_everypage > news_list.size())
			end_pos = news_list.size();
		// System.out.println("rank_index"+ranking_indicator);

		// 得到最终结果，更新map并返回
		resMap.put("total_size", String.valueOf(size));
		resMap.put("news_list", news_list.subList(start_pos, end_pos));

		System.out.println("从词项id_list到获取到新闻id_list: " + (t1.getTime() - t0.getTime()) + "; 从数据库读取新闻: "
				+ (t2.getTime() - t1.getTime()) + "; 读取相关新闻: " + (t3.getTime() - t2.getTime()) + "; 排序: "
				+ (t4.getTime() - t3.getTime()));
		return resMap;
	}

	private List<News> getNews(List<Integer> doc_list2) {
		// TODO Auto-generated method stub
		List<News> news = new ArrayList<News>();

		for (int docid : doc_list2) {
			News newaa = newsMapper.get(docid);
			if (newaa != null) {
				news.add(newaa);
			} else {
				System.out.println("no news in database , docid = " + docid);
			}
		}
		return news;
	}

	// 将得到的新闻列表重新排序
	private void Resort_newslist(List<News> news_list, String ranking_indicator) {

		// 按照时间进行排序
		if (ranking_indicator.equals("datetime")) {
			System.out.println("排序指标" + ranking_indicator);
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm");
			Collections.sort(news_list, new Comparator<News>() {
				@Override
				public int compare(News o1, News o2) {
					// TODO Auto-generated method stub
					try {
						if (sdf.parse(o2.getDatetime()).getTime() > sdf.parse(o1.getDatetime()).getTime())
							return 1;
					} catch (ParseException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					return -1;
				}
			});
			// 按照热度排序
		} else if (ranking_indicator.equals("heat")) {
			System.out.println("排序指标" + ranking_indicator);
			Collections.sort(news_list, new Comparator<News>() {

				@Override
				public int compare(News o1, News o2) {
					// TODO Auto-generated method stub
					return o2.getHeat() - o1.getHeat();
				}
			});
		}
	}

	// 20180104更新，取两个胜者表的并集，取并集中排名前1000
	public List<inverted_element> getUnionbyDocID(List<inverted_element> u1, List<inverted_element> u2, double u1_idf,
			double u2_idf) {

		List<inverted_element> newlist = new LinkedList<inverted_element>();

		// 按照docid排序
		Collections.sort(u1);
		Collections.sort(u2);

		// 取并集
		int l1 = 0, doc1;
		int l2 = 0, doc2;
		while (l1 < u1.size() && l2 < u2.size()) {
			doc1 = u1.get(l1).getDocID();
			doc2 = u2.get(l2).getDocID();
			if (doc1 == doc2) {
				u1.get(l1).setWf(u1.get(l1).getWf() * u1_idf + u2.get(l2).getWf() * u2_idf); // 更新文档的wf
																								// =
																								// wf1
																								// *
																								// idf1
																								// +
																								// wf2
																								// *
																								// idf2
				newlist.add(u1.get(l1));
				l1++;
				l2++;
			} else if (doc1 > doc2) {// 文档doc2不包含单词1
				// 更新文档的wf = wf2 * idf2 + 0 * idf1
				newlist.add(new inverted_element(doc2, u2.get(l2).getWf() * u2_idf * 0.01));
				l2++;
			} else { // 文档doc1不包含单词2
				// 更新文档的wf = wf2 * idf2 + 0 * idf1
				newlist.add(new inverted_element(doc1, u1.get(l1).getWf() * u1_idf * 0.01));
				l1++;
			}
		}

		return newlist;

	}

	@SuppressWarnings("unchecked")
	public LinkedList<inverted_element> getListFrombyte(byte[] data) {

		ObjectInputStream in;
		try {
			in = new ObjectInputStream(new ByteArrayInputStream(data));
			return (LinkedList<inverted_element>) in.readObject();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;

	}

	@Override
	public List<News> get_sim_news(int news_id) {
		// TODO Auto-generated method stub

		return newsMapper.get_simNews(news_id);
	}

	// 这个已经写完了
	@Override
	public List<News> get_hot_news(int num) {
		// TODO Auto-generated method stub
		return newsMapper.get_hotNews(num);
	}

}
