package xyz.sumtplus.mapper;

import xyz.sumtplus.domain.FeeVO;
import xyz.sumtplus.domain.PaymentVO;

/**
 * 클래스/인터페이스 설명
 *
 * @author 민지현
 * @date   2021. 5. 21.
 */
public interface PaymentMapper {
	
	// 결제내역 등록
	int insert(PaymentVO paymentVO);
	
	// 요금제찾기
	FeeVO selectFee(Integer fno);
}
