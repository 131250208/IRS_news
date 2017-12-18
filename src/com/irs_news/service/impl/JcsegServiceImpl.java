package com.irs_news.service.impl;

import java.io.IOException;

import org.lionsoul.jcseg.ASegment;
import org.lionsoul.jcseg.core.ADictionary;
import org.lionsoul.jcseg.core.DictionaryFactory;
import org.lionsoul.jcseg.core.JcsegException;
import org.lionsoul.jcseg.core.JcsegTaskConfig;
import org.lionsoul.jcseg.core.SegmentFactory;

import com.irs_news.service.JcsegService;

/*
作者：杨寿国
创建日期：2017年12月18日下午4:35:39
文件名：JcsegServiceImpl.java
TODO
*/
public class JcsegServiceImpl implements JcsegService {
	private static int seg_mode = JcsegTaskConfig.SIMPLE_MODE;//分词模式配置
	private static ASegment seg = null;
	private static boolean isInstance = false;//判断是否实例化
	private JcsegTaskConfig config = null;
	private ADictionary dic = null;
	public JcsegServiceImpl() {
		// TODO Auto-generated constructor stub
		if (!isInstance) {
			loadConfig();
			loadDic();
			CreateIseg();
			isInstance = true;
		}
	}
	
	@Override
	public void loadConfig() {
		// TODO Auto-generated method stub
		config = new JcsegTaskConfig();
	}

	@Override
	public void loadDic() {
		// TODO Auto-generated method stub
		if (config != null) {
			dic = DictionaryFactory.createDefaultDictionary(config);
			try {
				dic.loadFromLexiconDirectory((config.getLexiconPath())[0]);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
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

	public static ASegment getSeg() {
		return seg;
	}

}
