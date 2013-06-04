package pl.lwojtysiak.blog.mbeans;

import java.io.Serializable;
import java.util.Date;
import java.util.Map;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import pl.lwojtysiak.blog.model.Comment;
import pl.lwojtysiak.blog.model.Post;
import pl.lwojtysiak.blog.service.BlogService;

@ManagedBean(name = "showPost")
@ViewScoped
public class ShowPostMBean implements Serializable {

	/**
	 * Generated serialVersionUID
	 */
	private static final long serialVersionUID = 5605523793582336427L;
	
	private Post post;
	private String commentAuthor;
	private String commentText;

	public void addComment() {
		Comment comment = new Comment(commentAuthor, new Date(), commentText,
				post.getId());

		if (BlogService.getInstance().addComment(comment)) {
			FacesContext.getCurrentInstance().addMessage(
					null,
					new FacesMessage(FacesMessage.SEVERITY_INFO,
							"Comment was successfully added!", null));

			clearFields();
			post = null;
		} else {
			FacesContext.getCurrentInstance().addMessage(
					null,
					new FacesMessage(FacesMessage.SEVERITY_ERROR,
							"Adding comment failed!", null));
		}
	}

	public Post getPost() {
		Map<String, String> params = FacesContext.getCurrentInstance()
				.getExternalContext().getRequestParameterMap();

		long postId;
		try {
			postId = Long.valueOf(params.get("postId"));
		} catch (NumberFormatException ex) {
			FacesContext
					.getCurrentInstance()
					.addMessage(
							null,
							new FacesMessage(
									FacesMessage.SEVERITY_ERROR,
									"Missing postId parameter. Cannot get post.",
									null));

			return null;
		}

		if (post == null) {
			post = BlogService.getInstance().getFullPostWithComments(postId);
		}

		return post;
	}

	private void clearFields() {
		commentAuthor = "";
		commentText = "";
	}

	// getters_setters
	/**
	 * @return the commentAuthor
	 */
	public String getCommentAuthor() {
		return commentAuthor;
	}

	/**
	 * @param commentAuthor
	 *            the commentAuthor to set
	 */
	public void setCommentAuthor(String commentAuthor) {
		this.commentAuthor = commentAuthor;
	}

	/**
	 * @return the commentText
	 */
	public String getCommentText() {
		return commentText;
	}

	/**
	 * @param commentText
	 *            the commentText to set
	 */
	public void setCommentText(String commentText) {
		this.commentText = commentText;
	}

	/**
	 * @param post
	 *            the post to set
	 */
	public void setPost(Post post) {
		this.post = post;
	}
}
