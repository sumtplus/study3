# 자바에서 csv파일 생성하기

### 사용한 라이브러리
 - open-csv.jar
 
 ```java
@Controller
@RequestMapping("sample/*")
@Log4j
public class SampleController {

 @GetMapping("getCSV1")
	public void getCSV() throws Exception{
		log.info("getCSV1... start");
		
    //파일객체 생성
		File file = new File("C:\\upload\\output.csv");
    
    //writer객체 생성, 인코딩
		OutputStreamWriter writer = new OutputStreamWriter(new FileOutputStream(file), "MS949");
	
    // 객체를 이용하여 csv 파일 넣기
    StatefulBeanToCsv<Order> beanToCsv = new StatefulBeanToCsvBuilder<Order>(writer).build();
		
		beanToCsv.write(Arrays.asList(
				new Order(1,"muzi", 1000),
				new Order(1, "apeach", 2000)
		));
		writer.close();
		
		log.info("fileName : " + file.getName());
		log.info("fileSize : " + file.length());
		log.info("encoding : " + findFileEncoding(file));
		
		log.info("getCSV1... end");
	}
	
	@GetMapping("createCSV")
	public void createCSVGet() {
		log.info("createCSVGet... ");
	}
	
  // 포스트방식으로 받은 데이터로 csv파일 생성
	@PostMapping("createCSVPost")
	public void createCSVPost(int id, String user, int price) throws Exception{
		log.info("createCSVPost... start");
		
		Order order = new Order(id, user, price);
		log.info("infos : " + order);
		
    
		File file = new File("C:\\upload\\output.csv1");
		OutputStreamWriter writer = new OutputStreamWriter(new FileOutputStream(file), "MS949");
		StatefulBeanToCsv<Order> beanToCsv = new StatefulBeanToCsvBuilder<Order>(writer).build();
		
		beanToCsv.write(order);
		writer.close();
		
		log.info("fileName : " + file.getName());
		log.info("fileSize : " + file.length());
		log.info("encoding : " + findFileEncoding(file));
		
		log.info("getCSV1... end");
		
		log.info("createCSVPost... end");
	}
}


// csv파일에 담기 위한 객체
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Order {
	@CsvBindByName(column = "아이디")
	private int id;
	@CsvBindByName(column = "유저")
	private String user;
	@CsvBindByName(column = "가격")
	private int price;
}
```
