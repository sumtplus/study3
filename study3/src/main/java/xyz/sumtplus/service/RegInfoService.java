package xyz.sumtplus.service;

import java.util.List;

import xyz.sumtplus.domain.RegInfoVO;
import xyz.sumtplus.domain.SeatVO;

/**
 * 이용 등록 정보 서비스 인터페이스
 *
 * @author김보경
 * @date   2021. 5. 17.
 */
public interface RegInfoService {
	// 이용 등록 정보 등록
	int RegisterRegInfo(RegInfoVO infoVO);
	
	// 결제 전 임시 등록
	int RegisterTmpReg(RegInfoVO infoVO);
	
	// 이용 기간 연장
	int extendUsingPeriod(RegInfoVO infoVO);
	
	// 이용 등록 정보 목록 조회
	List<RegInfoVO> getAllList();
	
	// 이용 등록 정보 상세 조회
	RegInfoVO read(Long userNo);
	
	// 좌석 변경 (이용 등록 정보 수정)
	void changeSeat(RegInfoVO infoVO, SeatVO seatVO);
}
