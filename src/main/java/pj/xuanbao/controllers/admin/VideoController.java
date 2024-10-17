package pj.xuanbao.controllers.admin;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Part;
import pj.xuanbao.entity.Category;
import pj.xuanbao.entity.Video;
import pj.xuanbao.services.ICategoryService;
import pj.xuanbao.services.IVideoService;
import pj.xuanbao.services.impl.CategoryServiceImpl;
import pj.xuanbao.services.impl.VideoServiceImpl;
import pj.xuanbao.ultis.Constant;

@MultipartConfig(fileSizeThreshold = 1024 * 1024, maxFileSize = 1024 * 1024 * 5, maxRequestSize = 1024 * 1024 * 5 * 5)
@WebServlet(urlPatterns = { "/admin/videos", "/admin/video/add", "/admin/video/insert",
		"/admin/video/edit", "/admin/video/update", "/admin/video/delete", "/admin/video/findvideo"})

public class VideoController extends HttpServlet{

	private static final long serialVersionUID = 1L;
	
	public IVideoService vidService = new VideoServiceImpl();

	@Override

	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		String url = req.getRequestURI();
		if (url.contains("/admin/videos")) {
			List<Video> list = vidService.findAll();
			req.setAttribute("listvideo", list);
			req.getRequestDispatcher("/views/admin/video-list.jsp").forward(req, resp);
		} else if (url.contains("/admin/video/add")) {
			ICategoryService cateSer = new CategoryServiceImpl();
			List<Category> listcate = cateSer.findAll();
			req.setAttribute("listcate", listcate);
			
			req.getRequestDispatcher("/views/admin/video-add.jsp").forward(req, resp);
		} else if (url.contains("/admin/video/edit")) {
			ICategoryService cateSer = new CategoryServiceImpl();
			List<Category> listcate = cateSer.findAll();
			req.setAttribute("listcate", listcate);
			
			int id = Integer.parseInt(req.getParameter("id"));
			Video Video = vidService.findById(id);
			req.setAttribute("vid", Video);
			req.getRequestDispatcher("/views/admin/video-edit.jsp").forward(req, resp);
		} 
//		else if (url.contains("/admin/findvideo")){
//			req.getRequestDispatcher("/views/admin/findvideo.jsp").forward(req, resp);
//		}
		else {
			int id = Integer.parseInt(req.getParameter("videoid"));
			try {
				vidService.delete(id);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			// chuyển trang
			resp.sendRedirect(req.getContextPath() + "/admin/videos");
		}
	}

	@Override

	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		String url = req.getRequestURI();
		if (url.contains("/admin/video/insert")) {
			// lấy dữ liệu từ form
			String videotitle = req.getParameter("videotitle");
			int status = Integer.parseInt(req.getParameter("videoactive"));
			String images = req.getParameter("videoimages");
			String videodesc = req.getParameter("videodesc");
			int vidviews = Integer.parseInt(req.getParameter("videoviews"));
			int videocate = Integer.parseInt(req.getParameter("videocate"));
			
			// đưa dữ liệu vào model
			ICategoryService cateSer = new CategoryServiceImpl();
			Category cate = cateSer.findById(videocate);
			List<Category> listcate = cateSer.findAll();
			req.setAttribute("listcate", listcate);
			
			Video video = new Video();
			video.setTitle(videotitle);
			video.setActive(status);
			video.setCategory(cate);
			video.setDescription(videodesc);
			video.setViews(vidviews);
			
			String fname = "";
			String uploadPath = Constant.UPLOAD_DIRECTORY; // upload vào thư mục bất kỳ
			File uploadDir = new File(uploadPath);
			if (!uploadDir.exists())
				uploadDir.mkdir();
			try {
				Part part = req.getPart("videoimages");
				if (part.getSize() > 0) {
					String filename = Paths.get(part.getSubmittedFileName()).getFileName().toString();
					int index = filename.lastIndexOf(".");
					String ext = filename.substring(index + 1);
					fname = System.currentTimeMillis() + "." + ext;
					part.write(uploadPath + "/" + fname);
					video.setPoster(fname);
				} else if (images != null) {
					video.setPoster(images);
				} else {
					video.setPoster("avatar.png");
				}
			} catch (FileNotFoundException fne) {
				fne.printStackTrace();
			}
			// đưa model vào phương thức insert
			vidService.insert(video);
			// chuyển trang
			resp.sendRedirect(req.getContextPath() + "/admin/videos");
		}

		if (url.contains("/admin/video/update")) {
			// lấy dữ liệu từ form
			int videoid = Integer.parseInt(req.getParameter("videoid"));
			String videotitle = req.getParameter("videotitle");
			int status = Integer.parseInt(req.getParameter("videoactive"));
			String images = req.getParameter("videoimages");
			String videodesc = req.getParameter("videodesc");
			int vidviews = Integer.parseInt(req.getParameter("videoviews"));
			int videocate = Integer.parseInt(req.getParameter("videocate"));
			
			// đưa dữ liệu vào model
			Video video = vidService.findById(videoid);
			String fileold = video.getPoster();
			ICategoryService cateSer = new CategoryServiceImpl();
			Category cate = cateSer.findById(videocate);
			List<Category> listcate = cateSer.findAll();
			req.setAttribute("listcate", listcate);
			
			video.setTitle(videotitle);
			video.setActive(status);
			video.setCategory(cate);
			video.setDescription(videodesc);
			video.setViews(vidviews);
			
			
			String fname = "";
			
			
			String uploadPath = Constant.UPLOAD_DIRECTORY; // upload vào thư mục bất kỳ
			File uploadDir = new File(uploadPath);
			if (!uploadDir.exists())
				uploadDir.mkdir();
			try {
				Part part = req.getPart("images1");
				if (part.getSize() > 0) {
					// xóa file cũ trên thư mục
					if (!video.getPoster().substring(0, 5).equals("https")) {
						deleteFile(uploadPath + "\\" + fileold);
					}
					String filename = Paths.get(part.getSubmittedFileName()).getFileName().toString();
					int index = filename.lastIndexOf(".");
					String ext = filename.substring(index + 1);
					fname = System.currentTimeMillis() + "." + ext;
					part.write(uploadPath + "/" + fname);
					video.setPoster(fname);
				} else if (images != null) {
					video.setPoster(images);
				} else {
					video.setPoster(fileold);
				}
			} catch (FileNotFoundException fne) {
				fne.printStackTrace();
			}
			// đưa model vào phương thức insert
			vidService.update(video);
			// chuyển trang
			resp.sendRedirect(req.getContextPath() + "/admin/videos");
		}
		if (url.contains("/admin/video/delete")) {
			int videoid = Integer.parseInt(req.getParameter("videoid"));
			vidService.delete(videoid);
			// chuyển trang
			resp.sendRedirect(req.getContextPath() + "/admin/videos");
		}
		
		if (url.contains("/admin/video/findvideo")) {
			String findkey = req.getParameter("findkey");
			if(findkey != null) {
				List<Video> list = vidService.findByVideoName(findkey);
				req.setAttribute("listvideo", list);
				req.getRequestDispatcher("/views/admin/video-list.jsp").forward(req, resp);
			}
			
		}
	}

	public static void deleteFile(String filePath) throws IOException {

		Path path = Paths.get(filePath);
		Files.delete(path);
	}
}
