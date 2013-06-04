package pl.lwojtysiak.blog.service;

import java.util.List;

import pl.lwojtysiak.blog.dao.CommentDAO;
import pl.lwojtysiak.blog.dao.CommentDAOImpl;
import pl.lwojtysiak.blog.dao.PostDAO;
import pl.lwojtysiak.blog.dao.PostDAOImpl;
import pl.lwojtysiak.blog.model.Comment;
import pl.lwojtysiak.blog.model.Post;

/**
 * Service providing methods for Blog application.
 * 
 * @author lwojtysiak
 * 
 */
public class BlogService {

	private PostDAO postDAO = null;
	private CommentDAO commentDAO = null;

	/** BlogService object - to provide Singleton. */
	private static BlogService blogService = null;

	/**
	 * Private constructor.
	 */
	private BlogService() {
		postDAO = new PostDAOImpl();
		commentDAO = new CommentDAOImpl();
	}

	/**
	 * Method returns singleton BlogService object.
	 * 
	 * @return BlogService object
	 */
	public static BlogService getInstance() {
		if (blogService == null) {
			blogService = new BlogService();
		}

		return blogService;
	}

	/**
	 * Method returns post with comments and full content.
	 * 
	 * @param postId
	 *            post id
	 * @return Post object
	 */
	public Post getFullPostWithComments(long postId) {
		Post post = null;

		post = postDAO.getById(postId);
		if (post != null) {
			// get full content
			post.setText(postDAO.getPostContent(postId));
			// get comments
			List<Comment> comments = commentDAO.getByPostId(postId);
			System.out.println("comments: " + comments.size());
			post.setComments(comments);
		}

		return post;
	}

	/**
	 * Method returns list of all posts from blog.
	 * 
	 * @return lists of posts
	 */
	public List<Post> getAllPosts() {
		List<Post> posts = null;

		posts = postDAO.findAll();

		for (Post post : posts) {
			int numberOfComments = commentDAO.getNumberOfCommentsByPostId(post
					.getId());
			post.setNumberOfComments(numberOfComments);
		}

		return posts;
	}

	/**
	 * Method adds comment with specified text to post.
	 * 
	 * @param comment
	 *            Comment object
	 * @return true if successfully added
	 */
	public boolean addComment(Comment comment) {
		return commentDAO.save(comment);
	}
	
	/**
	 * Method adds post to blog system.
	 * 
	 * @param post
	 *            Post object
	 * @return true if successfully added
	 */
	public boolean addPost(Post post) {
		return postDAO.save(post);
	}

}
