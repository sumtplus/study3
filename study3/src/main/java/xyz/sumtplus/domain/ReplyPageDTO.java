package xyz.sumtplus.domain;

import java.util.List;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * 댓글 페이징처리를 위한 클래스
 *
 * @author 민지현
 * @date   2021. 5. 26.
 */
@Getter
@Setter
@ToString
public class ReplyPageDTO extends PageDTO{
	private List<ReplyVO> list;
	
	public ReplyPageDTO(Criteria cri, int total) {
		super(total, cri);
	}
	
	public ReplyPageDTO(Criteria cri, int total, List<ReplyVO> list) {
		super(total, cri);
		this.list = list;
	}
	
}
