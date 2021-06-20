package xyz.sumtplus.mapper;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import lombok.extern.log4j.Log4j;
import xyz.sumtplus.domain.BoardVO;
import xyz.sumtplus.domain.Criteria;

/**
 * 게시글 단위 테스트
 *
 * @author 여름
 * @date   2021. 5. 18. > 5. 24. 복구
 */

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"file:src/main/webapp/WEB-INF/spring/root-context.xml","file:src/main/webapp/WEB-INF/spring/security-context.xml"})
@Log4j
public class BoardMapperTests {
	@Autowired
	private BoardMapper mapper;
	
	@Test // 조회 테스트 동작확인
	public void testGetPage() {
		mapper.getList().forEach(log::info);
	}
	
	@Test 
	public void getListWithPaging() {
		Criteria cri = new Criteria();
		cri.setPageNum(2);
		cri.setAmount(10);
		cri.setType("T");
		cri.setKeyword("수정");
		log.info(cri);
		mapper.getListWithPaging(cri).forEach(log::info);
	}
		
	
	@Test // 입력 테스트
	public void testInsert() {
		BoardVO board = new BoardVO();
		board.setTitle("카테고리 1 / 비밀글 true 테스트");
		board.setWriter("tester");
		board.setContent("단위 테스트 작성 내용~~~~~~");
		board.setCategory(1);
		board.setSecret(false); //
		
		mapper.insert(board);
		log.info(board);
	} 
	
	@Test
	public void testInsertSelectKey() {
		BoardVO board = new BoardVO(); 
			board.setTitle("작성 테스트 select key");
			board.setContent("작성 테스트 select key");
			board.setWriter("신규 작성자");
			board.setCategory(1);
			
			mapper.insertSelectKey(board);
			log.info(board);
	}
	
	@Test
	public void insertSelectKey2() {
		BoardVO board = new BoardVO(); 
		board.setTitle("작성 테스트 select key");
		board.setContent("작성 테스트 select key");
		board.setWriter("신규 작성자");
		board.setCategory(1);
		board.setParentNo(1880L);
		
		mapper.insertSelectKey2(board);
		log.info(board);
	}
	
		

	@Test // 수정 테스트
	public void testUpdate() {
		BoardVO board = new BoardVO();
		
		board.setBno(150L);

		board.setTitle("수정된 단위 테스트 작성 제목");
		board.setWriter("newbie");
		board.setContent("수정된 단위 테스트 작성 내용");
		
		log.info("UPDATE COUNT: " + mapper.update(board));
	}
	
	@Test // 삭제 테스트
	public void testDelete() {
		log.info("DELETE COUNT: " + mapper.delete(136L));
	}	

	@Test // 상세
	public void testRead() {
		log.info(mapper.read(150L));
	}
	
	@Test
	public void testGetTotalCount() {
		Criteria cri = new Criteria(1, 10);
		log.info(mapper.getTotalCount(cri));
	}
	
	@Test // 답글포함 게시글 목록
	public void testgetList2(){
		Criteria cri = new Criteria();
		cri.setPageNum(1);
		cri.setAmount(10);
		mapper.getList2(cri).forEach(log::info);
	}
	
	/**
	 * 게시글 조회수 업데이트 매퍼 테스트
	 * @author 김보경
	 * @Date 21. 6. 3.
	 */
	@Test
	public void updateHitCountTest() {
		mapper.updateHitCount(121L);
	}
	
}