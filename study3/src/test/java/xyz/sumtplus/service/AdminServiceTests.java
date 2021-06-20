package xyz.sumtplus.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import lombok.extern.log4j.Log4j;
import xyz.sumtplus.domain.Criteria;
import xyz.sumtplus.domain.MemberVO;
import xyz.sumtplus.domain.RegInfoVO;

/**
 * 관리자의 서비스의 테스트코드입니다
 *
 * @author 박재우
 * @Date   2021. 5. 24.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"file:src/main/webapp/WEB-INF/spring/root-context.xml",
"file:src/main/webapp/WEB-INF/spring/security-context.xml"})
@Log4j
public class AdminServiceTests {
	
	@Autowired
	AdminService adminService;
	
	@Test
	public void getMemberList(){
		adminService.memberList(new Criteria(1,10)).forEach(log::info);
	}
	
	@Test
	public void getMemberContactList(){
		adminService.memberContactList(new Criteria(1,10)).forEach(log::info);
	}
	
	@Test
	public void getRegProperties(){
		adminService.regProperties(new Criteria(1,10)).forEach(log::info);
	}
	
	@Test
	public void getPaymentList(){
		adminService.paymentList(new Criteria(1,10)).forEach(log::info);
	}
	
	@Test
	public void getItemStatus(){
		adminService.itemStatus().forEach(log::info);
	}
	
	@Test
	public void getMemberDeatil(){
		log.info(adminService.memberDetail(3));
	}
	
	@Test
	public void updateMemberInfo(){
		MemberVO memberVO = new MemberVO();
		memberVO.setTel("123-456-0987");
		memberVO.setEmail("ganada@hangual.com");
		memberVO.setUserNo(16L);
		adminService.updateMember(memberVO);
		log.info(memberVO);
	}
	
	@Test
	public void updateRegPP(){
		RegInfoVO regInfoVO = new RegInfoVO();
		regInfoVO.setLockerNo(0);
		regInfoVO.setSeatNo(0);
		regInfoVO.setUserNo(10L);
		adminService.updateRegPP(regInfoVO);
		log.info(regInfoVO);
	}
	
	@Test
	public void getNotiList(){
		adminService.getNotiList().forEach(log::info);
	}
	
	/**
	 * 회원 중 좌석/사물함 미등록 회원 목록 조회 서비스 테스트
	 * @author 김보경
	 * @Date 2021. 5. 28.
	 */
	@Test
	public void getUnregListTest() {
		adminService.getUnregListTest(new Criteria(1,20)).forEach(log::info);
	}
	
	/**
	 * 좌석배치도에 이용 중인 상태와 회원 이름, 만료일 보여주는 서비스 테스트
	 * (관리자-좌석관리에서 이용)
	 * @author 김보경
	 * @Date 2021. 5. 29.
	 */
	@Test
	public void showSeatPlanInfoTest() {
		adminService.showSeatPlanInfo().forEach(log::info);
	}
	
	/**
	 * 좌석배치도에서 이용 중인 회원의 등록 취소 서비스 테스트
	 * (관리자-좌석관리에서 이용)
	 * @author 김보경
	 * @Date 2021. 5. 31.
	 */
	@Test
	public void revokeTheRegistrationTest() {
		adminService.revokeTheRegistration(48L);
	}
	
	/**
	 * 사물함배치도에 이용 중인 상태와 회원 이름, 만료일 보여주는 서비스 테스트
	 * (관리자-사물함관리에서 이용)
	 * @author 김보경
	 * @Date 2021. 6. 1.
	 */
	@Test
	public void showLockerPlanInfoTest() {
		adminService.showLockerPlanInfo().forEach(log::info);
	}
	
	/**
	 * 사물함배치도에서 이용 중인 사물함 등록을 취소하는 메서드
	 * (관리자-사물함관리에서 이용)
	 * @author 김보경
	 * @Date 2021. 6. 1.
	 */
	@Test
	public void revokeLocTest() {
		adminService.revokeLocker(216L);
	}
}
