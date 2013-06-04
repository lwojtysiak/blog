package pl.lwojtysiak.blog.dao;

import java.util.List;

import pl.lwojtysiak.blog.model.Comment;

/**
 * DAO interface for database connection.
 * 
 * @author lwojtysiak
 * 
 */
public interface CommentDAO {
	/**
	 * Method returns all comments for specified post from database.
	 * 
	 * @param postId
	 *            database id of post
	 * @return list of comments by post id
	 */
	List<Comment> getByPostId(long postId);

	/**
	 * Method returns number of comments added to specified post.
	 * 
	 * @param postId
	 *            database id of post
	 * @return number of comments connected with post
	 */
	int getNumberOfCommentsByPostId(long postId);

	/**
	 * Method save comment to database.
	 * 
	 * @param comment
	 *            comment to save
	 * @return true if successfully saved
	 */
	boolean save(Comment comment);
}
