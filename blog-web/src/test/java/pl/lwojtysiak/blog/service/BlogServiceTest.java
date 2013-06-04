package pl.lwojtysiak.blog.service;

import static org.junit.Assert.assertEquals;

import java.io.File;
import java.util.Date;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import pl.lwojtysiak.blog.db.ConnectionFactory;
import pl.lwojtysiak.blog.model.Post;

public class BlogServiceTest {

	/** Path to test db file. */
	private static String DB_FILE_PATH = "D:/dbfile_test.db";

	private static String AUTHOR = "lwojtysiak";
	private static String TITLE = "Test message";
	
	/**
	 * Init tests - set new db file path
	 * 
	 * @throws Exception
	 */
	@Before
	public void setUp() throws Exception {
		ConnectionFactory cf = ConnectionFactory.getInstance();
		cf.setDbFilePath(DB_FILE_PATH);
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
	 * Test singleton
	 */
	@Test
	public void testSingleton() {
		BlogService blogService1 = BlogService.getInstance();
		BlogService blogService2 = BlogService.getInstance();

		assertEquals(blogService1, blogService2);
	}

	public void testFindAll() {
		List<Post> allPosts = BlogService.getInstance().getAllPosts();

		assertEquals(0, allPosts.size());

		Post post = new Post(AUTHOR, TITLE, new Date(), "summary",
				"full content");
		BlogService.getInstance().addPost(post);

		allPosts = BlogService.getInstance().getAllPosts();

		assertEquals(1, allPosts.size());
		assertEquals(1L, allPosts.get(0).getId());

		BlogService.getInstance().addPost(post);
		allPosts = BlogService.getInstance().getAllPosts();

		assertEquals(2, allPosts.size());
	}

}
