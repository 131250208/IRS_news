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
浣滆�咃細鏉ㄥ鍥�
鍒涘缓鏃ユ湡锛�2017骞�12鏈�18鏃ヤ笅鍗�4:35:39
鏂囦欢鍚嶏細JcsegServiceImpl.java
TODO
*/
public class JcsegServiceImpl implements JcsegService {
	private static int seg_mode = JcsegTaskConfig.SIMPLE_MODE;// 鍒嗚瘝妯″紡閰嶇疆
	private static ASegment seg = null;
	private static Trie root = new Trie('#'); // 鍓嶇紑鏍戠殑鏍戞牴
	private static String voc_file = "C:/Users/15850/Documents/GitHub/J2EE/IRS_news_201801062209/IRS_news/src/sources/voc.txt"; // voc
	// file

	// 瀛楀吀鐨勬枃浠剁粷瀵硅矾寰�
	private static String dir_path = "C:/Users/15850/Documents/GitHub/J2EE/IRS_news_201801062209/IRS_news/lexicon";
	private static boolean isInstance = false;// 鍒ゆ柇鏄惁瀹炰緥鍖�
	private JcsegTaskConfig config = null;
	private ADictionary dic = null;

	public JcsegServiceImpl() {
		// TODO Auto-generated constructor stub
		if (!isInstance) {
			System.out.println("绯荤粺鍒濆鍖栦腑...");
			loadConfig(); // 鍒嗚瘝宸ュ叿閰嶇疆鍔犺浇
			loadDic();
			CreateIseg();
			System.out.println("鍔犺浇鍒嗚瘝宸ュ叿");
			loadTries();// 鍔犺浇杞帓绱㈠紩宸ュ叿
			System.out.println("鍔犺浇杞帓绱㈠紩");
			isInstance = true;
			System.out.println("绯荤粺鍒濆鍖栧畬姣曪紒");
		}
	}

	private void loadTries() {
		// TODO Auto-generated method stub
		// System.out.println(System.getProperty("user.dir"));//鎵撳嵃褰撳墠璺緞
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
				// e.printStackTrace();
			}
		} else {
			System.out.println("config load failed");
		}
	}

	@Override
	public void CreateIseg() {
		// TODO Auto-generated method stub
		try {
			seg = (ASegment) SegmentFactory.createJcseg(seg_mode, new Object[] { config, dic });
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
