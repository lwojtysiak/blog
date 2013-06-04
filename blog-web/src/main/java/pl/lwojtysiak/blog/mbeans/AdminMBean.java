package pl.lwojtysiak.blog.mbeans;

import java.util.Date;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import pl.lwojtysiak.blog.model.Post;
import pl.lwojtysiak.blog.service.BlogService;

/**
 * Bean for admin webpage.
 * 
 * @author lwojtysiak
 * 
 */
@ManagedBean(name = "admin")
@SessionScoped
public class AdminMBean {

	/* Fields for form editing. */
	/** Post author. */
	private String author;
	/** Post title. */
	private String title;
	/** Post summary. */
	private String summary;
	/** Post text. */
	private String text;

	/**
	 * Method add post to application with data from webpage form.
	 */
	public void addPost() {
		Post post = new Post(author, title, new Date(), summary, text);

		if (BlogService.getInstance().addPost(post)) {
			FacesContext.getCurrentInstance().addMessage(
					null,
					new FacesMessage(FacesMessage.SEVERITY_INFO,
							"Message was successfully posted!", null));

			clearFields();
		} else {
			FacesContext.getCurrentInstance().addMessage(
					null,
					new FacesMessage(FacesMessage.SEVERITY_ERROR,
							"Posting message failed!", null));
		}
	}

	/**
	 * Clear form fields after successfully adding post.
	 */
	private void clearFields() {
		author = "";
		title = "";
		summary = "";
		text = "";
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

}
