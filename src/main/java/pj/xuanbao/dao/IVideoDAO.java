package pj.xuanbao.dao;

import java.util.List;

import pj.xuanbao.entity.Video;

public interface IVideoDAO {
	void insert (Video video);
	void update (Video video);
	void delete (int vidid);
	
	Video findById(int vidid);
	List<Video> findAll();
	List<Video> findByVideoName(String vidName);
	List<Video> findAll(int page, int pagesize);
	List<Video> findByCategory(String categoryname);
	int count();
}
