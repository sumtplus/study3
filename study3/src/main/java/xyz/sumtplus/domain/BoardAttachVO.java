package xyz.sumtplus.domain;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * 첨부파일 테이블에 대응되는 자바 클래스
 *
 * @author 박재우
 * @date   2021. 5. 29.
 */
@Getter
@Setter
@ToString(callSuper=true)
public class BoardAttachVO extends AttachFileDTO{
	private Long bno; //글번호
	
}
