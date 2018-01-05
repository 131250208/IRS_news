package com.irs_news.service.impl;

import java.io.IOException;
import java.util.HashMap;

import org.lionsoul.jcseg.ASegment;
import org.lionsoul.jcseg.core.ADictionary;
import org.lionsoul.jcseg.core.DictionaryFactory;
import org.lionsoul.jcseg.core.JcsegException;
import org.lionsoul.jcseg.core.JcsegTaskConfig;
import org.lionsoul.jcseg.core.SegmentFactory;

import com.irs_news.service.JcsegService;
import com.irs_news.tools.Tools;
import com.irs_news.tools.Trie;

/*
作者：杨寿国
创建日期：2017年12月18日下午4:35:39
文件名：JcsegServiceImpl.java
TODO
*/
public class JcsegServiceImpl implements JcsegService {
	private static int seg_mode = JcsegTaskConfig.SIMPLE_MODE;//分词模式配置
	private static ASegment seg = null;
	private static Trie root = new Trie('#'); //前缀树的树根
	private static String voc_file = "/home/ysg/workspace/IR_assignment/newir/IRS_news/src/sources/voc.txt"; //voc file

	//字典的文件绝对路径
	private static String dir_path = "/home/ysg/workspace/IR_assignment/newir/IRS_news/lexicon";
	private static boolean isInstance = false;//判断是否实例化
	private JcsegTaskConfig config = null;
	private ADictionary dic = null;
	public JcsegServiceImpl() {
		// TODO Auto-generated constructor stub
		if (!isInstance) {
			System.out.println("系统初始化中...");
			loadConfig(); //分词工具配置加载
			loadDic();
			CreateIseg();
			System.out.println("加载分词工具");
			loadTries();//加载轮排索引工具
			System.out.println("加载轮排索引");
			isInstance = true;
			System.out.println("系统初始化完毕！");
		}
	}
	
	private void loadTries() {
		// TODO Auto-generated method stub
//		System.out.println(System.getProperty("user.dir"));//打印当前路径
		HashMap<String, String> map = new HashMap<String, String>();
		Tools.loadTerm(voc_file, root, map);
	}

	@Override
	public void loadConfig() {
		// TODO Auto-generated method stub
		try {
			config = new JcsegTaskConfig();
			config.setLoadCJKPinyin(true);
			config.setICnName(true);
			config.setMixCnLength(6);
			config.setMaxCnLnadron(4);
			config.setCnNumToArabic(false);
			config.setCnFactionToArabic(false);
			config.setAppendCJKSyn(false);
			config.setAppendPartOfSpeech(false);
			config.setLoadCJKPos(false);
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println("Exception when load jcseg config");
		}
		
	}

	@Override
	public void loadDic() {
		// TODO Auto-generated method stub
		if (config != null) {
			try {
				dic = DictionaryFactory.createDefaultDictionary(config);
				dic.loadFromLexiconDirectory(dir_path);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				//e.printStackTrace();
			}
		}else {
			System.out.println("config load failed");
		}
	}

	@Override
	public void CreateIseg() {
		// TODO Auto-generated method stub
		try {
			seg = (ASegment) SegmentFactory.createJcseg(seg_mode, new Object[] {config,dic});
		} catch (JcsegException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	//
	public static Trie get_TrieRoot() {
		return root;
	}

	public static ASegment getSeg() {
		return seg;
	}

}
