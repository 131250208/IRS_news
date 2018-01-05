package com.irs_news.service.jtest;

import java.sql.DriverManager;
import java.sql.SQLException;

import com.mysql.jdbc.Connection;

/*
作者：杨寿国
创建日期：2018年1月4日下午12:00:38
文件名：Cadsasd.java
TODO
*/
public class Connect {
	public Connect() {
		// TODO Auto-generated constructor stub
	}
	public Connection getConn(){
		Connection conn = null;
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			System.out.println("加载驱动失败");
			e.printStackTrace();
		}
		String url ="jdbc:mysql://127.0.0.1:3306/irs_news?characterEncoding=utf-8";
		try {
			conn = (Connection) DriverManager.getConnection(url,"root","6536013");
			System.out.println("连接成功!");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return conn;
	}
}