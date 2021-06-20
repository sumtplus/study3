package xyz.sumtplus.mapper;

import org.junit.Test;

import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import lombok.extern.log4j.Log4j;

/**
 * 답글 메퍼 테스트
 * @author 류슬희
 * @Date 21. 6. 3.
 */

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"file:src/main/webapp/WEB-INF/spring/root-context.xml",
"file:src/main/webapp/WEB-INF/spring/security-context.xml"})
@Log4j
public class ReplyMapperTests {
	@Autowired
	private ReplyMapper replyMapper;
	
	@Test
	public void testExist() {
		log.info(replyMapper);
	}
	
	@Test
	public void testGetListWithShowMore() {
		replyMapper.getListWithShowMore(null, 2069L).forEach(log::info);;
	}
	
}
