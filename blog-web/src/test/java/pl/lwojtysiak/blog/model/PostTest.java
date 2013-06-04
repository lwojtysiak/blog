package pl.lwojtysiak.blog.model;

import static org.junit.Assert.assertEquals;

import java.util.Date;

import org.junit.Test;

/**
 * Test for Post class
 * 
 * @author lwojtysiak
 *
 */
public class PostTest {

	private static String AUTHOR = "lwojtysiak";
	private static String TITLE = "Test message";
	
	
	/**
	 * Test if post is properly created.
	 */
	@Test
	public void testDefaultConstructor() {
		Post post = new Post();
		
		assertEquals(null, post.getAuthor());
		assertEquals(null, post.getTitle());
		assertEquals(null, post.getSummary());
		assertEquals(null, post.getText());
	}
	
	/**
	 * Test if post is properly created with specified values.
	 */
	@Test
	public void testParamsConstructor() {
		Post post = new Post(AUTHOR, TITLE, new Date(), "summary", "full content");
		
		assertEquals(AUTHOR, post.getAuthor());
		assertEquals(TITLE, post.getTitle());
		assertEquals("summary", post.getSummary());
		assertEquals("full content", post.getText());
	}
	
	/**
	 * Test if comment is properly added to post.
	 */
	@Test
	public void testAddComments() {
		Post post = new Post();
		
		// initially empty
		assertEquals(0, post.getComments().size());
		
		post.addComment(AUTHOR, "message");
		
		// check Size
		assertEquals(1, post.getComments().size());
		
		// check comment correctness
		Comment comment = post.getComments().get(0);
		assertEquals(AUTHOR, comment.getAuthor());
		assertEquals("message", comment.getText());
	}

}
