package xyz.sumtplus.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import lombok.extern.log4j.Log4j;

/**
 * 이용현황 서비스
 *
 * @author 여름
 * @date   2021. 5. 27.
 */


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"file:src/main/webapp/WEB-INF/spring/root-context.xml",
						"file:src/main/webapp/WEB-INF/spring/security-context.xml"
})
@Log4j
public class ChartServiceTests {
	
	@Autowired
	private ChartService service;
	
	
	
	@Test // 월별 이용건수 조회
	public void testGetCount() {
		List<Map<String, String>> chart = new ArrayList<Map<String, String>>();
		Map<String, String> map = new HashMap<String, String>();
		
		service.getCount("2021");
	}
	
	@Test // 월별 매출 조회
	public void testGetMonthPay() {
		List<Map<String, String>> chart = new ArrayList<Map<String, String>>();
		Map<String, String> map = new HashMap<String, String>();
		
		service.getMonthPay("2021");
	}
	
	@Test // 일별 매출 조회
	public void testGetDayPay() {
		List<Map<String, String>> chart = new ArrayList<Map<String, String>>();
		Map<String, String> map = new HashMap<String, String>();
		
		service.getDayPay("05");
	}	
	
	
}
