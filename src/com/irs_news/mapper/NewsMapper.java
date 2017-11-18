package com.irs_news.mapper;

import java.util.List;

import com.irs_news.pojo.News;

public interface NewsMapper {
	public void add(News news);  
    
    public void delete(int id);  
       
    public News get(int id);  
     
    public void update(News category);   
       
    public List<News> search();
    
    public int count();  
}
