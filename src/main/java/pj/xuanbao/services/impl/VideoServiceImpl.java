package pj.xuanbao.services.impl;

import java.util.List;

import pj.xuanbao.dao.IVideoDAO;
import pj.xuanbao.dao.impl.VideoDAOImpl;
import pj.xuanbao.entity.Video;
import pj.xuanbao.services.IVideoService;

public class VideoServiceImpl implements IVideoService{
	
	IVideoDAO vidDAO = new VideoDAOImpl();
	
	@Override
	public void insert(Video video) {
		vidDAO.insert(video);
	}

	@Override
	public void update(Video video) {
		vidDAO.update(video);
	}

	@Override
	public void delete(int vidid) {
		vidDAO.delete(vidid);
	}

	@Override
	public Video findById(int vidid) {
		return vidDAO.findById(vidid);
	}

	@Override
	public List<Video> findAll() {
		return vidDAO.findAll();
	}

	@Override
	public List<Video> findByVideoName(String vidName) {
		return vidDAO.findByVideoName(vidName);
	}

	@Override
	public List<Video> findAll(int page, int pagesize) {
		return vidDAO.findAll(page, pagesize);
	}

	@Override
	public List<Video> findByCategory(String categoryname) {
		return vidDAO.findByCategory(categoryname);
	}

	@Override
	public int count() {
		return vidDAO.count();
	}

}
