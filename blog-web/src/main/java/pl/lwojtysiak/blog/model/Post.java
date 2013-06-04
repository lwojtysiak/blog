package pl.lwojtysiak.blog.model;

import java.io.Serializable;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

public class Post implements Serializable {

	/**
	 * Generated serialVersionUID.
	 */
	private static final long serialVersionUID = -3516137957812365381L;

	/** Database identifier. */
	private long id;
	/** Post author. */
	private String author;
	/** Post title. */
	private String title;
	/** Post insert date. */
	private Date insertDate;
	/** Post summary - showed on home page. */
	private String summary;
	/** Post full text. */
	private String text;
	/** Number of comments to post. */
	private int numberOfComments;

	/** List of comments. */
	private List<Comment> comments = new LinkedList<Comment>();

	/**
	 * Constructor.
	 */
	public Post() {

	}

	/**
	 * Constructor with params.
	 * 
	 * @param author
	 *            post author
	 * @param title
	 *            post title
	 * @param insertDate
	 *            post date
	 * @param summary
	 *            psot summary
	 * @param text
	 *            post text
	 */
	public Post(String author, String title, Date insertDate, String summary,
			String text) {
		this.author = author;
		this.title = title;
		this.insertDate = insertDate;
		this.summary = summary;
		this.text = text;
	}

	/**
	 * Methods adds comment to post.
	 * 
	 * @param author
	 *            comment author
	 * @param text
	 *            comment text
	 */
	public void addComment(String author, String text) {
		Comment c = new Comment(author, new Date(), text);
		comments.add(c);
	}

	// getters_setters
	/**
	 * @return the author
	 */
	public String getAuthor() {
		return author;
	}

	/**
	 * @param author
	 *            the author to set
	 */
	public void setAuthor(String author) {
		this.author = author;
	}

	/**
	 * @return the title
	 */
	public String getTitle() {
		return title;
	}

	/**
	 * @param title
	 *            the title to set
	 */
	public void setTitle(String title) {
		this.title = title;
	}

	/**
	 * @return the insertDate
	 */
	public Date getInsertDate() {
		return insertDate;
	}

	/**
	 * @param insertDate
	 *            the insertDate to set
	 */
	public void setInsertDate(Date insertDate) {
		this.insertDate = insertDate;
	}

	/**
	 * @return the summary
	 */
	public String getSummary() {
		return summary;
	}

	/**
	 * @param summary
	 *            the summary to set
	 */
	public void setSummary(String summary) {
		this.summary = summary;
	}

	/**
	 * @return the text
	 */
	public String getText() {
		return text;
	}

	/**
	 * @param text
	 *            the text to set
	 */
	public void setText(String text) {
		this.text = text;
	}

	/**
	 * @return the id
	 */
	public long getId() {
		return id;
	}

	/**
	 * @param id
	 *            the id to set
	 */
	public void setId(long id) {
		this.id = id;
	}

	/**
	 * @return the numberOfComments
	 */
	public int getNumberOfComments() {
		return numberOfComments;
	}

	/**
	 * @param numberOfComments
	 *            the numberOfComments to set
	 */
	public void setNumberOfComments(int numberOfComments) {
		this.numberOfComments = numberOfComments;
	}

	/**
	 * @return the comments
	 */
	public List<Comment> getComments() {
		return comments;
	}

	/**
	 * @param comments
	 *            the comments to set
	 */
	public void setComments(List<Comment> comments) {
		this.comments = comments;
	}
}
