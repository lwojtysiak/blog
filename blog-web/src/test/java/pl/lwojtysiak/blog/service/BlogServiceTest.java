package pl.lwojtysiak.blog.service;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

import java.util.Date;
import java.util.LinkedList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import pl.lwojtysiak.blog.dao.CommentDAO;
import pl.lwojtysiak.blog.dao.PostDAO;
import pl.lwojtysiak.blog.model.Comment;
import pl.lwojtysiak.blog.model.Post;

@RunWith(MockitoJUnitRunner.class)
public class BlogServiceTest {

	@Mock
    private PostDAO postDAO;
    @Mock
    private CommentDAO commentDAO;

	private static String AUTHOR = "lwojtysiak";
	private static String TITLE = "Test message";
	private static String MESSAGE = "Test message";

	/**
	 * Init tests - set new db file path
	 * 
	 * @throws Exception
	 */
	@Before
	public void setUp() throws Exception {
		BlogService.getInstance().setCommentDAO(commentDAO);
		BlogService.getInstance().setPostDAO(postDAO);
		
		Post post = new Post(AUTHOR, TITLE, new Date(), "summary", "full content");
		post.setId(1L);
		Post post2 = new Post(AUTHOR, TITLE, new Date(), "summary2", "full content2");
		post2.setId(2L);
		
		List<Comment> comments = new LinkedList<Comment>();
		Comment comment = new Comment(AUTHOR, new Date(), MESSAGE);
		comment.setPostId(1L);
		comments.add(comment);
		
		List<Post> allPosts = new LinkedList<Post>();
		allPosts.add(post2);
		allPosts.add(post);

		when(postDAO.getById(1L)).thenReturn(post);
		when(postDAO.findAll()).thenReturn(allPosts);
		when(postDAO.getPostContent(1L)).thenReturn("full content");
		when(commentDAO.getNumberOfCommentsByPostId(1L)).thenReturn(1);
		when(commentDAO.getNumberOfCommentsByPostId(2L)).thenReturn(0);
		when(commentDAO.getByPostId(1L)).thenReturn(comments);
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

	/**
	 * Test if returns all posts from DB.
	 */
	@Test
	public void testFindAll() {
		List<Post> allPosts = BlogService.getInstance().getAllPosts();

		// verify list contains all added posts
		assertEquals(2, allPosts.size());

		// verify properly get number of comments
		assertEquals(1, allPosts.get(1).getNumberOfComments());
		assertEquals(0, allPosts.get(0).getNumberOfComments());
	}

	/**
	 * Test if returns full post with comments from DB.
	 */
	@Test
	public void testGetFullPostWithComments() {
		Post fullPost = BlogService.getInstance().getFullPostWithComments(1L);

		assertEquals(1, fullPost.getId());
		assertEquals(AUTHOR, fullPost.getAuthor());
		assertEquals(TITLE, fullPost.getTitle());
		assertEquals("summary", fullPost.getSummary());
		assertEquals("full content", fullPost.getText());

		assertEquals(1, fullPost.getComments().size());
		Comment c = fullPost.getComments().get(0);

		assertEquals(AUTHOR, c.getAuthor());
		assertEquals(MESSAGE, c.getText());
	}

}
