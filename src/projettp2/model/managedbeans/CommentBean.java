package projettp2.model.managedbeans;

import java.io.Serializable;
import java.util.List;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;

import projettp2.model.beanentities.Comment;
import projettp2.model.services.CommentService;
import projettp2.model.services.MemberService;

@Named
@RequestScoped
public class CommentBean implements Serializable {

	private static final long serialVersionUID = -4274251529162542806L;

	private List<Comment> comments;
	
	private String commentText;
	
	@Inject
	LoginBean lb;

	public List<Comment> getComments() {
		return CommentService.getComments();
	}

	public void setComments(List<Comment> comments) {
		this.comments = comments;
	}

	public String getCommentText() {
		System.out.println("getCommentText ...");
		return commentText;
	}

	public void setCommentText(String commentText) {
		System.out.println("setCommentText ...");
		this.commentText = commentText;
	}
	
	public String postCommentAction() {
		Comment newComment = new Comment();
		
		newComment.setText(commentText);
		
		if(lb.getLogin() != null && !lb.getLogin().isEmpty()) {
			newComment.setMember(MemberService.getMemberFromLogin(lb.getLogin()));
		}
		
		if(CommentService.postNewComment(newComment)) {
			return "success";
		}
		else {
			return "failure";
		}
    }
}