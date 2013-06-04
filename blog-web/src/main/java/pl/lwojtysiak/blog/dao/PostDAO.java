package pl.lwojtysiak.blog.dao;

import java.util.List;

import pl.lwojtysiak.blog.model.Post;

/**
 * DAO interface for database connection.
 * 
 * @author lwojtysiak
 * 
 */
public interface PostDAO {
	/**
	 * Method returns all posts from database.
	 * 
	 * @return list of posts
	 */
	List<Post> findAll();

	/**
	 * Method returns full content of post
	 * 
	 * @param id
	 *            post id
	 * @return post full content
	 */
	String getPostContent(long id);

	/**
	 * Method return post by id.
	 * 
	 * @param id
	 *            post id
	 * @return Object Post from database with specified id.
	 */
	Post getById(long id);

	/**
	 * Method save post to database.
	 * 
	 * @param post
	 *            post to save
	 * @return true if successfully saved
	 */
	boolean save(Post message);
}
