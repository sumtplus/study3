package xyz.sumtplus.service;

import static org.junit.Assert.assertNotNull;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import lombok.extern.log4j.Log4j;
import xyz.sumtplus.domain.BoardVO;
import xyz.sumtplus.domain.Criteria;

/**
 * 게시판 서비스 테스트
 *
 * @author 이여름
 * @date   2021. 5. 20.
 */

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"file:src/main/webapp/WEB-INF/spring/root-context.xml",
						"file:src/main/webapp/WEB-INF/spring/security-context.xml"})
@Log4j
public class BoardServiceTests {
	@Autowired
	private BoardService service;
	
	@Test
	public void testExist() {
		log.info(service);
		assertNotNull(service);
	}
	
	@Test
	public void testRegister() {
		BoardVO board = new BoardVO();
		board.setTitle("신규 작성하는 글");
		board.setWriter("작성자");
		board.setContent("새로 작성하는 내용");
		board.setCategory(1);
		
		service.register(board);
		
		log.info("생성된 게시물의 번호 : " + board.getBno());
	}
	
	@Test
	public void testGetList() {
		Criteria cri = new Criteria(2, 10);
		service.getList(cri).forEach(board -> log.info(board));
	}
	
	/**
	 * 답글을 포함한 게시글 목록 테스트 ( + 조회수 추가 테스트)
	 * @author 김보경
	 * @Date 21. 6. 3.
	 */
	@Test
	public void getList2Test() {
		Criteria cri = new Criteria(1, 10, 2); // 1페이지 10개씩 2번 카테고리(QNA)
		service.getList(cri).forEach(log::info);
	}
	
	@Test
	public void testGet() {
		log.info(service.get(2018L));
	}
	
	@Test
	public void testDelete(){
		log.info("REMOVE RESULT : " + service.remove(128L));
	}

	@Test
	public void testUpdate(){
		BoardVO board = service.get(37L);
		
		if (board == null) {
			return;
		}
		
		board.setTitle("제목 수정 테스트");
		log.info("MODIFY RESULT : " + service.modify(board));
	}
	
	@Test
	public void testGetTotal() {
		log.info(service.getTotalCount(new Criteria()));
	}
	
	@Test
	public void testGetNoticeTotal() {
		log.info(service.getNoticeTotalCount(new Criteria()));
	}
	
	@Test
	public void testGetQATotal() {
		log.info(service.getQATotalCount(new Criteria()));
	}
	
	/**
	 * 게시글의 조회수를 늘리는 서비스 테스트
	 * @author 김보경
	 * @Date 21. 6. 3.
	 */
	@Test
	public void increaseHitCountTest() {
		log.warn(service.increaseHitCount(121L));
	};
}
