# 스프링에서 다른 서버로 http요청하기

#### RestTemplete 사용법 
  - https://blog.naver.com/hj_kim97/222295259904

#### 요청테스트 할수 있는 사이트
  - https://jsonplaceholder.typicode.com/

```java
@Controller
@Log4j
@RequestMapping("/board/*")
//@AllArgsConstructor
public class BoardController {
	@Autowired
	private BoardService service;

	// 게시글 요청 url
	private String url1 = "https://jsonplaceholder.typicode.com/posts";

	/**
	 * http통신 get방식으로 요청 20220212
	 */
	@ResponseBody
	@GetMapping("testInfo")
	public String testInfo(@RequestParam("userId") int userId) {
		log.warn("testInfo......");

		// 결과값을 담을 객체
		String json = "";
		// HashMap<String, Object> resultMap = new HashMap<String, Object>();

		// 파라미터 생성
		MultiValueMap<String, String> params = new LinkedMultiValueMap<String, String>();
		//params.add("userId", String.valueOf(userId));

		// 헤더 생성
		HttpHeaders headers = new HttpHeaders();
		headers.add("", "");

		// 헤더와 파라미터 결합하여 요청데이터 생성
		HttpEntity<MultiValueMap<String, String>> entity = new HttpEntity<MultiValueMap<String,String>>(params, headers);

		// 레스트템플릿 생성
		RestTemplate rt = new RestTemplate();

		// url 생성
		String url = url1 + "/" + userId;
		// uri 생성, 인코딩여부 false
		//UriComponents uri = UriComponentsBuilder.fromHttpUrl(url1).queryParam("userId", 1).build(false);
		log.warn("url : " + url);

		// getForEntity() 메소드로 get방식으로 api요청[url, 결과타입]
		ResponseEntity<Map> response = rt.getForEntity(url, Map.class);
		//log.info("response : " + response);
		
		// 요청한 결과를 HashMap에 추가
//		resultMap.put("statusCode", response.getStatusCodeValue());
//		resultMap.put("header",response.getHeaders());
//		resultMap.put("body",response.getBody());
//		log.warn("resultMap : " + resultMap);

		// jackson라이브러리 이용하여 response의 body(map)를 json으로 변환
		ObjectMapper om = new ObjectMapper();
		try {
			json = om.writeValueAsString(response.getBody());
		} catch (JsonProcessingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		log.warn("json : " + json);
		return json;
	}
	
	/**
	* http통신 post방식으로 요청 20220212
	*/
	@ResponseBody
	@GetMapping("postTestInfo")
	public String postTestInfo() {
	log.warn("postTestInfo......");

	// 결과값을 담을 객체
	String json = "";

  // 	MultiValueMap<String, String> params = new LinkedMultiValueMap<String, String>();
  //	params.add("userId", "1");
  //	params.add("title", "foo");
  //	params.add("body", "bar");
  	
	// 바디에 넣을 객체 생성
	Posts posts = new Posts(1, 0, "foo", "bar");

	//log.warn("params : " + params);
	log.warn("posts : " + posts);
	
	// 헤더 생성
	HttpHeaders headers = new HttpHeaders();
	headers.add("Content-type", "application/json; charset=UTF-8");
	
	// 요청값(바디)과 헤더를 결합하여 요청데이터 생성
	HttpEntity<Posts> entity = new HttpEntity<>(posts, headers);

	RestTemplate rt = new RestTemplate();

	// uri 생성, 인코딩여부 false
	//UriComponents uri = UriComponentsBuilder.fromHttpUrl(url1).queryParam("userId", 1).build(false);

	// excahnge() 메소드로 api 호출[url, 요청방식, 요청데이터, 결과타입]
	ResponseEntity<Map> response = rt.exchange(url1, HttpMethod.POST, entity, Map.class);

	// jackson라이브러리 이용하여 response의 body(map)를 json으로 변환
	ObjectMapper om = new ObjectMapper();
	try {
	json = om.writeValueAsString(response.getBody());
	} catch (JsonProcessingException e) {
	// TODO Auto-generated catch block
	e.printStackTrace();
	}

	log.warn("json : " + json);
	return json;
	}
  }
  ```
