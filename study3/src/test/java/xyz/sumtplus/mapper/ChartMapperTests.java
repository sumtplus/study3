package xyz.sumtplus.mapper;



import java.util.List;
import java.util.Map;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import lombok.extern.log4j.Log4j;


/**
 * 이용현황 mapper 테스트
 *
 * @author 여름
 * @date   2021. 5. 26.
 */

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"file:src/main/webapp/WEB-INF/spring/root-context.xml","file:src/main/webapp/WEB-INF/spring/security-context.xml"})
@Log4j
public class ChartMapperTests {
	
	@Autowired
	private ChartMapper mapper;
	
	@Test // TBL_PAYMENT 조회
	public void test(){
		mapper.getChart().forEach(log::info);
		log.info(mapper);
	}

	@Test // 월별 이용건수 조회
	public void testGetCount() {
		mapper.getCount(null).forEach(log::info);
	}
	
	@Test // 월별 매출 조회
	public void testGetMonthPay() {
		mapper.getMonthPay("2021").forEach(log::info);
	}
	
	@Test // 주간 매출 조회
	public void testGetWeekPay() {
		mapper.getWeekPay("2021").forEach(log::info);
	}

	@Test // 일간 매출 조회
	public void testGetDayPay() {
		mapper.getDayPay("2021/05").forEach(log::info);
	}	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
