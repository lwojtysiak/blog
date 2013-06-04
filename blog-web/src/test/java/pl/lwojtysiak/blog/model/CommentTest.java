package pl.lwojtysiak.blog.model;

import static org.junit.Assert.*;

import java.util.Date;

import org.junit.Test;

/**
 * Test for Comments class
 * 
 * @author lwojtysiak
 *
 */
public class CommentTest {

	private static String AUTHOR = "lwojtysiak";
	private static String MESSAGE = "Test message";
	
	/**
	 * Test if comment is properly created.
	 */
	@Test
	public void testDefaultConstructor() {
		Comment comment = new Comment();
		
		assertEquals(null, comment.getAuthor());
		assertEquals(null, comment.getText());
	}
	
	/**
	 * Test if comment is properly created with specified values.
	 */
	@Test
	public void testParamsConstructor() {
		Comment comment = new Comment(AUTHOR, new Date(), MESSAGE);
		
		assertEquals(AUTHOR, comment.getAuthor());
		assertEquals(MESSAGE, comment.getText());
	}

}
