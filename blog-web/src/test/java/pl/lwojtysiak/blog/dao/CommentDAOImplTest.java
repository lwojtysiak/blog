package pl.lwojtysiak.blog.dao;

import static org.junit.Assert.assertEquals;

import java.io.File;
import java.util.Date;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import pl.lwojtysiak.blog.db.ConnectionFactory;
import pl.lwojtysiak.blog.model.Comment;
import pl.lwojtysiak.blog.model.Post;

/**
 * Test class for CommentDAO implementation
 * 
 * @author lwojtysiak
 *
 */
public class CommentDAOImplTest {

	/** Path to test db file. */
	private static String DB_FILE_PATH = "D:/dbfile_test.db";
	
	private static String AUTHOR = "lwojtysiak";
	private static String TITLE = "Test message";
	private static String MESSAGE = "Test message";
	
	private CommentDAO commentDAO = null;
	private PostDAO postDAO = null;
	
	/**
	 * Init tests - set new db file path
	 * 
	 * @throws Exception
	 */
	@Before
	public void setUp() throws Exception {
		ConnectionFactory cf = ConnectionFactory.getInstance();
		cf.setDbFilePath(DB_FILE_PATH);
		
		postDAO = new PostDAOImpl();
		commentDAO = new CommentDAOImpl();
	}
	
	/**
	 * End tests - delete db file for test
	 * 
	 * @throws Exception
	 */
	@After
	public void tearDown() throws Exception {
		File f = new File(DB_FILE_PATH);
		
		if (f.exists()) {
			f.delete();
		}
	}

	@Test
	public void testGetByPostId() {
		Post post = new Post(AUTHOR, TITLE, new Date(), "summary", "full content");
		postDAO.save(post);
		post = new Post(AUTHOR, TITLE, new Date(), "summary2", "full content2");
		postDAO.save(post);
		
		List<Comment> comments = commentDAO.getByPostId(1L);
		
		assertEquals(0, comments.size());
		
		Comment comment = new Comment(AUTHOR, new Date(), MESSAGE);
		comment.setPostId(1L);
		commentDAO.save(comment);
		comment = new Comment(AUTHOR, new Date(), MESSAGE+"2");
		comment.setPostId(2L);
		commentDAO.save(comment);
		
		comments = commentDAO.getByPostId(1L);
		assertEquals(1, comments.size());
		assertEquals(AUTHOR, comments.get(0).getAuthor());
		assertEquals(MESSAGE, comments.get(0).getText());
	}
	
	@Test
	public void testGetNumberOfCommentsByPostId() {
		Post post = new Post(AUTHOR, TITLE, new Date(), "summary", "full content");
		postDAO.save(post);
		
		int comments = commentDAO.getNumberOfCommentsByPostId(1L);
		
		assertEquals(0, comments);
		
		Comment comment = new Comment(AUTHOR, new Date(), MESSAGE);
		comment.setPostId(1L);
		
		commentDAO.save(comment);
		commentDAO.save(comment);
		
		comments = commentDAO.getNumberOfCommentsByPostId(1L);
		
		assertEquals(2, comments);
	}
	
	@Test
	public void testSave() {
		Post post = new Post(AUTHOR, TITLE, new Date(), "summary", "full content");
		postDAO.save(post);
		
		Comment comment = new Comment(AUTHOR, new Date(), MESSAGE);
		comment.setPostId(1L);
		commentDAO.save(comment);
		
		List<Comment> comments = commentDAO.getByPostId(1L);
		
		assertEquals(1, comments.size());
		assertEquals(AUTHOR, comments.get(0).getAuthor());
		assertEquals(MESSAGE, comments.get(0).getText());
		
	}

}
