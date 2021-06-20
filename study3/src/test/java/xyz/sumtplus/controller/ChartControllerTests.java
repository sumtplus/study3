package xyz.sumtplus.controller;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import lombok.extern.log4j.Log4j;


/**
 * 클래스/인터페이스 설명
 *
 * @author 여름
 * @date   2021. 5. 28.
 */

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"file:src/main/webapp/WEB-INF/spring/root-context.xml",
		"file:src/main/webapp/WEB-INF/spring/appServlet/servlet-context.xml",
		"file:src/main/webapp/WEB-INF/spring/security-context.xml"})
@Log4j
@WebAppConfiguration
public class ChartControllerTests {
	@Autowired
	private WebApplicationContext ctx;
	private MockMvc mockMvc;
	
	@Before
	public void setup() {
		this.mockMvc = MockMvcBuilders.webAppContextSetup(ctx).build();
	}
	
	
	/* 특정 년도의 월별 이용자 수 */
	@Test
	public void getDataTest() throws Exception{
		log.info(
				mockMvc.perform(MockMvcRequestBuilders.get("/admin/memberChart")
				.param("year", "2021")
				).andReturn().getModelAndView().getModelMap());
	}

	/* 특정 년도의 월 매출 */
	@Test
	public void getMonthPayTest() throws Exception{
		log.info(
				mockMvc.perform(MockMvcRequestBuilders.get("/admin/payChart")
				.param("year", "2021")
				).andReturn().getModelAndView().getModelMap());
	}
	
}
