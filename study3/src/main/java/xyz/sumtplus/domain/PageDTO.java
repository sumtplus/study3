package xyz.sumtplus.domain;

import lombok.Data;
import lombok.Getter;
import lombok.ToString;

/**
 * 페이징 처리를 위한 클래스
 *
 * @author 민지현
 * @date   2021. 5. 26.
 */
@Data
public class PageDTO {
	private int startPage; //시작페이지
	private int endPage; //끝페이지
	private boolean prev, next; //이전, 다음
	private int total; //전체 데이터 수
	private Criteria cri;
	
	public PageDTO(int total, Criteria cri) {
		this.total = total;
		this.cri = cri;
		// 페이지번호로 끝페이지와 시작페이지 계산
		this.endPage = (int)(Math.ceil(cri.getPageNum()/10.0)) * 10;
		
		this.startPage = this.endPage - 9;
		// 전체데이터수로 realEnd 계산
		int realEnd = (int)(Math.ceil((total*1.0)/ cri.getAmount()));
		// 끝페이지가 realEnd보다 크면 realEnd 반환
		if(realEnd < this.endPage){
			this.endPage = realEnd;
		}
		// 이전은 시작번호가 1보다 큰 경우에 존재
		this.prev = this.startPage > 1;
		// 다음은 realEnd가 끝번호보다 큰 경우에만 존재
		this.next = this.endPage < realEnd;
	}
	
}
