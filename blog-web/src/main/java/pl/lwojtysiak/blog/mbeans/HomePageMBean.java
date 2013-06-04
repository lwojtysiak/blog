package pl.lwojtysiak.blog.mbeans;

import java.io.Serializable;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import pl.lwojtysiak.blog.model.Post;
import pl.lwojtysiak.blog.service.BlogService;

@ManagedBean(name="homePage")
@ViewScoped
public class HomePageMBean implements Serializable {
	
	/**
	 * Generated serialVersionUID
	 */
	private static final long serialVersionUID = -7900321543220264276L;

	public List<Post> getPosts() {
		return BlogService.getInstance().getAllPosts();
	}
}
