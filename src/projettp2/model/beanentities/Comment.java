package projettp2.model.beanentities;

import java.util.Date;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="Comment")
public class Comment {
	
	@Id @GeneratedValue(strategy=GenerationType.AUTO)
	private int commentId;
	
	@ManyToOne
    private Member member;
	
	private Date commentDate;
	
	private String text;
	
	@ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name="LIKES_MESSAGES",
            joinColumns={@JoinColumn(name="COMMENT",
                    referencedColumnName="commentId")},
            inverseJoinColumns={@JoinColumn(name="MEMBER",
                    referencedColumnName="email")})
	private List<Member> members;

	public Comment() {
		super();
		commentDate = new Date(System.currentTimeMillis());
	}
	
	public int getNumberOfLikes() {
		return members.size();
	}

	public int getCommentId() {
		return commentId;
	}

	public void setCommentId(int commentId) {
		this.commentId = commentId;
	}

	public Member getMember() {
		if(member == null) {
			return new Member();
		}
		return member;
	}

	public void setMember(Member member) {
		this.member = member;
	}

	public Date getCommentDate() {
		return commentDate;
	}

	public void setCommentDate(Date commentDate) {
		this.commentDate = commentDate;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public List<Member> getMembers() {
		return members;
	}

	public void setMembers(List<Member> members) {
		this.members = members;
	}
	
}
