package xyz.sumtplus.domain;

import lombok.Data;

/**
 * 요금제 테이블에 대응하는 자바클래스
 *
 * @author 민지현
 * @date   2021. 5. 20.
 */
@Data
public class FeeVO {
	private int fno; 		// 요금제 번호
	private String name; 	// 요금제 이름
	private int price; 		// 요금제 가격
	private int period; 	// 요금제 기간
}
