package pl.lwojtysiak.blog.mbeans;

import java.io.Serializable;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import pl.lwojtysiak.blog.model.Post;
import pl.lwojtysiak.blog.service.BlogService;

/**
 * Bean for home page.
 * 
 * @author lwojtysiak
 * 
 */
@ManagedBean(name = "homePage")
@ViewScoped
public class HomePageMBean implements Serializable {

	/**
	 * Generated serialVersionUID
	 */
	private static final long serialVersionUID = -7900321543220264276L;

	/**
	 * Method returns list of all posts in application.
	 * 
	 * @return list of Post objects
	 */
	public List<Post> getPosts() {
		return BlogService.getInstance().getAllPosts();
	}
}
