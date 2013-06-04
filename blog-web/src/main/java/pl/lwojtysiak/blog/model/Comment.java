package pl.lwojtysiak.blog.model;

import java.io.Serializable;
import java.util.Date;

public class Comment implements Serializable {
	/**
	 * Generated serialVersionUID.
	 */
	private static final long serialVersionUID = -8432499878715230131L;
	
	private long id;
	private String author;
	private Date insertDate;
	private String text;
	private Long postId;
	
	public Comment() {
		
	}
	
	public Comment(String author, Date insertDate, String text) {
		this.author = author;
		this.insertDate = insertDate;
		this.text = text;
	}
	
	public Comment(String author, Date insertDate, String text, long postId) {
		this.author = author;
		this.insertDate = insertDate;
		this.text = text;
		this.postId = postId;
	}

	// getters_setters
	/**
	 * @return the id
	 */
	public long getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(long id) {
		this.id = id;
	}

	/**
	 * @return the author
	 */
	public String getAuthor() {
		return author;
	}

	/**
	 * @param author the author to set
	 */
	public void setAuthor(String author) {
		this.author = author;
	}

	/**
	 * @return the insertDate
	 */
	public Date getInsertDate() {
		return insertDate;
	}

	/**
	 * @param insertDate the insertDate to set
	 */
	public void setInsertDate(Date insertDate) {
		this.insertDate = insertDate;
	}

	/**
	 * @return the text
	 */
	public String getText() {
		return text;
	}

	/**
	 * @param text the text to set
	 */
	public void setText(String text) {
		this.text = text;
	}
	
	/**
	 * @return the postId
	 */
	public Long getPostId() {
		return postId;
	}

	/**
	 * @param postId the postId to set
	 */
	public void setPostId(long postId) {
		this.postId = postId;
	}
}
