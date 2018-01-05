package com.irs_news.service.jtest;

import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.Statement;

import mybatis.inverted_element;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.sql.Blob;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Map;

/*
作者：杨寿国
创建日期：2018年1月4日上午11:49:53
文件名：readtest.java
TODO
*/
public class readtest
{

 @SuppressWarnings("unchecked")
public static void main(String[] args) {
	 ResultSet res = null;
	  Connect connect = new Connect();
	  Connection conn = connect.getConn();
	  Statement stat;
	try {
		stat = (Statement) conn.createStatement();
		 String str = "SELECT id,word,idf,winner1st FROM vocabulary where word = \"今天\";";  
		  if(conn!=null)
		  {
		   int vocabCount=0;
		   //从数据库读取词汇表
		   res = stat.executeQuery(str);
		   while(res.next())
		   {
		    int termID = res.getInt("id");
		    String term = res.getString("word");
		    double idf = res.getDouble("idf");
		    LinkedList<inverted_element> vector = null;
		    Blob tempBlob = res.getBlob("winner1st");
		    ObjectInputStream tempOis = new ObjectInputStream(tempBlob.getBinaryStream());
		    vector = (LinkedList<inverted_element>) tempOis.readObject();
		    vocabCount++;
		    System.out.println(term+"Winner1st: ");
		    
		    Collections.sort(vector);
		    
		    for(int i=0;i<vector.size();i++)
		    {
		     System.out.print("["+vector.get(i).getDocID()+","+vector.get(i).getWf()+"]→");
		    }
		    System.out.println("");
		   }
		   System.out.println("Read OK");
		  }	
	} catch (SQLException | ClassNotFoundException | IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	 
}
}