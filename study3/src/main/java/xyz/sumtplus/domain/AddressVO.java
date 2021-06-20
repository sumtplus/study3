package xyz.sumtplus.domain;

import lombok.Data;

/**
 * 주소테이블에 대응되는 자바 클래스
 *
 * @author 류슬희
 * @date   2021. 5. 19.
 */
@Data
public class AddressVO {
	private Long addrNo; //주소번호
	private Long userNo; //회원번호
	private String post; //우편번호
	private String addr; //주소
	private String detail; //상세주소
	private String extra; //추가주소
}
