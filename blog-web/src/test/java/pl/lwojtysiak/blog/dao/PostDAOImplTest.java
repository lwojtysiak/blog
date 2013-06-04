package pl.lwojtysiak.blog.dao;

import static org.junit.Assert.assertEquals;

import java.io.File;
import java.util.Date;
import java.util.List;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import pl.lwojtysiak.blog.db.ConnectionFactory;
import pl.lwojtysiak.blog.model.Post;

/**
 * Test class for PostDAO implementation
 * 
 * @author lwojtysiak
 *
 */
public class PostDAOImplTest {

	/** Path to test db file. */
	private static String DB_FILE_PATH = "D:/dbfile_test.db";
	
	private static String AUTHOR = "lwojtysiak";
	private static String TITLE = "Test message";
	
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

	/**
	 * Test if returns all posts from DB.
	 */
	@Test
	public void testFindAll() {
		List<Post> allPosts = postDAO.findAll();
		
		assertEquals(0, allPosts.size());
		
		Post post = new Post(AUTHOR, TITLE, new Date(), "summary", "full content");
		postDAO.save(post);
		
		allPosts = postDAO.findAll();
		
		assertEquals(1, allPosts.size());
		assertEquals(1L, allPosts.get(0).getId());
		
		postDAO.save(post);
		allPosts = postDAO.findAll();
		
		assertEquals(2, allPosts.size());
	}
	
	/**
	 * Test if returns correct post by id number.
	 */
	@Test
	public void testGetById() {
		Post post = new Post(AUTHOR, TITLE, new Date(), "summary", "full content");
		postDAO.save(post);
		
		Post post2 = postDAO.getById(1L);
		
		Assert.assertNotNull(post2);
		Assert.assertEquals(post2.getAuthor(), AUTHOR);
	}
	
	/**
	 * Test if returns correct post content.
	 */
	@Test
	public void testGetPostContent() {
		Post post = new Post(AUTHOR, TITLE, new Date(), "summary", "full content");
		postDAO.save(post);
		
		String content = postDAO.getPostContent(1L);
		
		Assert.assertEquals(content, "full content");
	}
	
	/**
	 * Test if properly save post to DB.
	 */
	@Test
	public void testSave() {
		Post post = new Post(AUTHOR, TITLE, new Date(), "summary", "full content");
		postDAO.save(post);
		
		Post post2 = postDAO.getById(1L);
		
		assertEquals(1, post2.getId());
		assertEquals(AUTHOR, post2.getAuthor());
		assertEquals(TITLE, post2.getTitle());
		assertEquals("summary", post2.getSummary());
		assertEquals("full content", post2.getText());
	}
}
