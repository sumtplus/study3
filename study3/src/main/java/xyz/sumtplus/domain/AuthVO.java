package xyz.sumtplus.domain;

import lombok.Data;

/**
 * 회원 authvo 클래스 
 *
 * @author 류슬희
 * @date   2021. 5. 19.
 */
@Data
public class AuthVO {
	private Long userNo; //회원번호
	private String auth; //권한
}
