# BookSearch 코드 개념 정리

## 책검색&상세검색 관련 개념

### 기본적인 @어노테이션

**@Entity** : 클래스 위에 선언하여 이 클래스가 엔티티임을 알려준다. 이렇게 되면 JPA에서 정의된 필드들을 바탕으로 데이터베이스에 테이블을 만들어준다.

**@Builder** : 해당 클래스에 해당하는 엔티티 객체를 만들 때 빌더 패턴을 이용해서 만들 수 있도록 지정해주는 어노테이션이다. 이렇게 선언해놓으면 나중에 다른 곳에서 Board.builder(). {여러가지 필드의 초기값 선언 }. build() 형태로 객체를 만들 수 있다.

**@AllArgsConstructor** : 선언된 모든 필드를 파라미터로 갖는 생성자를 자동으로 만들어준다.

**@NoArgsConstructor** : 파라미터가 아예없는 기본생성자를 자동으로 만들어준다.

**@Getter** : 각 필드값을 조회할 수 있는 getter를 자동으로 생성해준다. 예를들어 다른 파일에서 Board 객체의 title값을 얻고 싶다면 getTitle() 메서드를 정의해서 해당 객체의 title값을 얻어오게 되는데, 해당 메서드를 굳이 작성하지 않아도 자동으로 생성해주는 것이다.

변수는 보통 직접 접근 및 변경이 안되도록 private 선언자를 통해 지정한다. title 이라는 변수를 조회하기 위해서 getTitle() 이라는 메서드를 선언하고, title을 리턴하도록 해준다. 이 메서드를 **getter**라고 부른다.

```java
private String title
 
public String getTitle() { return title }
```

**@ToString** : 해당 클래스에 선언된 필드들을 모두 출력할 수 있는 toString 메서드를 자동으로 생성할 수 있도록 해준다.

### `bookSearch`

```java
/**
     * bookSearch
     *
     * @param bookkeyword
     * @return BookSearchDto
     */
    public BookSearchDto bookSearch(String bookkeyword, int start) {
        RestTemplate restTemplate = new RestTemplate();
        HttpEntity<String> httpEntity = getHttpEntity();
        URI targetUrl = UriComponentsBuilder
                .fromUriString(SEARCH_URL)
                .queryParam("query", bookkeyword)
                .queryParam("start", start)
                .build()
                .encode(StandardCharsets.UTF_8)
                .toUri();
        return restTemplate.exchange(targetUrl, HttpMethod.GET, httpEntity, BookSearchDto.class).getBody();
    }

private HttpEntity<String> getHttpEntity() { //헤더에 인증 정보 추가
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.set("X-Naver-Client-Id", naverId);
        httpHeaders.set("X-Naver-Client-Secret", naverSecret);
        return new HttpEntity<>(httpHeaders);
    }
```

### restTemplate

Spring에서 지원하는 객체로 간편하게 Rest 방식 API를 호출할 수 있는 Spring 내장 클래스다.

json, xml 응답을 모두 받을 수 있다.

**HTTP 프로토콜을 사용하여 RESTful API에 액세스 하고 쉽게 사용하여 ‘외부 도메인’에서 데이터를 가져오거나 전송할 때 사용되는 스프링 프레임워크의 클래스를 의미한다.**

GET, POST, PUT, DELETE 등의 HTTP 메서드를 사용하여 RESTful API에 요청을 보낼 수 있다. 또한 서버로부터 응답을 받을 때는 JSON, XML 등의 다양한 데이터 형식을 지원힌다.

**RestTemplate의 특징**

HTTP 요청 후 JSON, XML, String 과 같은 응답을 받을 수 있는 템플릿

RESTful 형식에 맞추어진 템플릿

Header, Content-Tpye등을 설정하여 외부 API 호출

Server to Server 통신에 사용

### exchange()

| 메서드 | 반환타입 | HTTP | 설명 |
| --- | --- | --- | --- |
| exchange | ResponseEntity | any | 헤더 생성 및 어떤 요청이든 사용 가능 HTTP, 헤더를 새로 만들 수 있고 어떤 HTTP 메서드도 사용가능하다.|
 

exchange 메서드 매개변수 첫번째는 내가 보낼 URI 정보를 두번째는 리턴받을 타입을 적는다.

### **RestTemplate의 동작 원리**

1. 어플리케이션이 RestTemplate를 생성하고, URI, HTTP 메소드 등의 헤더를 담아 요청
2. RestTemplate는 HttpMessageConverter를 사용하여 requestEntity를 요청 메세지로 변환
3. RestTemplate는 ClientHttpRequestFactory로 부터 ClientHttpRequest를 가져와서 요청을 보냄
4. ClientHttpRequest 는 요청메세지를 만들어 HTTP 프로토콜을 통해 서버와 통신
5. RestTemplate 는 ResponseErrorHandler 로 오류를 확인하고 있다면 처리로직을 태움
6. ResponseErrorHandler 는 오류가 있다면 ClientHttpResponse 에서 응답데이터를 가져와서 처리
7. RestTemplate 는 HttpMessageConverter 를 이용해서 응답메세지를 java object(Class responseType) 로 변환
8. 어플리케이션에 반환

예제

```java
// RestTemplate 생성
RestTemplate restTemplate = new RestTemplate();

// 요청 매개변수 설정
HttpHeaders headers = new HttpHeaders();
headers.setContentType(MediaType.APPLICATION_JSON);
HttpEntity<RequestDto> request = new HttpEntity<>(requestDto, headers);

// HTTP 요청 및 응답 처리
ResponseDto responseDto = restTemplate.exchange(url, HttpMethod.POST, request, ResponseDto.class).getBody();
```

### getbody()→문자열 반환

Spring Boot의 @RequestBody 애노테이션을 사용하여 요청 본문을 매핑할 때 getBody() 메서드를 사용하여 요청 본문을 가져올 수 있다. getBody() 메서드는 요청 본문을 문자열로 반환한다.

getBody() 메서드는 요청 본문이 JSON이 아닌 경우 작동하지 않는다.

```java
return restTemplate.exchange(targetUrl, HttpMethod.GET, httpEntity, BookSearchDto.class).getBody();
```

Spring Boot의 `RestTemplate` 클래스를 사용하여 HTTP GET 요청을 보내고 응답을 `BookSearchDto` 객체로 변환하는 코드다.

- `restTemplate.exchange()` 메서드는 다음과 같은 매개변수를 취합니다.
    - `targetUrl`: 요청을 보낼 대상 URL
    - `httpMethod`: 사용할 HTTP 메서드 (GET, POST, PUT, DELETE 등)
    - `httpEntity`: 요청 본문과 헤더 정보를 포함하는 `HttpEntity` 객체
    - `responseType`: 응답 본문을 변환할 클래스
- `restTemplate.exchange()` 메서드는 HTTP 요청을 보내고 응답을 받은 후, 응답 본문을 지정된 클래스로 변환하여 반환합니다.

### httpEntity

**HttpEntity**클래스는 HTTP요청또는 응답에 해당하는 HttpHeader와 HttpBody를 포함하는 클래스다. HttpEntity 클래스를 상속받아 구현한 클래스가 RequestEntity, ResponseEntity 클래스이다.

http header에는 (요청/응답)에 대한 요구사항이 http body에는 그 내용이 적혀있다.

### UriComponentsBuilder

UriComponentsBuilder는 여러개의 파라미터를 이용하여 URL를 작성할 때에 편하게 작성할 수 있게끔 도와준다.

**UriComponents 를 Build할 수 있도록 도와주는 클래스**다.

**UriComponents** 클래스의 생성자는 모두 **package-private** 또는 **private** 이기 때문에, 개발자가 이를 직접 구현하지 않는 이상 **생성자를 통해 직접 생성할 수는 없다.**

1. **Static Factory Method** 중에 하나를 이용하여 **UriComponentsBuilder**
   객체를 생성한다. Static Factory Method 중에서 fromUriString(String) 을 이용했다.
2. 대응되는 각 메서드를 이용하여 URI 구성요소를 설정하거나 인코딩을 설정한다.
    - **scheme(String)**
    - **userInfo(String)**
    - **host(String)**
    - **port(String or int)**
    - **path(String)**
    - **queryParam(String, Object...)**
    - **queryParams(MultiValueMap<String, String>)**
    - **fragment(String)**
    - **encode(void or Charset) : void일 경우 UTF-8 로 인코딩**
    - **expand(Map<String, ?> or Object... or UriTemplateVariables) : URI 템플릿 변수 값을 지정**

3 .**build()** 메서드를 이용하여 UriComponents 인스턴스를 Build 한다.

또는 **buildAndExpand()** 메서드를 이용하여 **URI 템플릿 변수를 설정한 후 Build**

3단계에 걸쳐서 최종적으로 UriComponents 인스턴스가 생성됨

**UriComponents의 toUri() 메서드를 이용하여 URI 객체로 변환하면 된다.**

### HttpHeaders

HTTP 헤더(Header)

클라이언트의 요청이나 서버의 응답에 포함되어 부가적인 정보를 담고 있음

HTTP 헤더 예시

- Authorization
    - 클라이언트가 적절한 자격 증명을 가지고 있는지를 확인하기 위한 정보를 담음
    - 일반적으로 REST API 기반 애플리케이션의 경우 클라이언트와 서버 간의 로그인 인증에 통과한 클라이언트들은 ‘Authorization’ 헤더 정보를 기준으로 인증에 통과한 클라이언트가 맞는지 확인하는 절차를 거친다.

**Spring, HTTP Header 정보 얻기**

HttpEntity 이용

Spring MVC에서는 HttpEntity 객체를 통해서도 헤더 정보를 읽을 수 있다.

HttpEntity는 Request 헤더와 바디 정보를 래핑하고 있음

조금 더 쉽게 헤더와 바디에 접근할 수 있는 다양한 API를 지원

### -searchdetail-

```java
/**
    * bookSearchDetail
    *
    * @param isbn
    * @return BookSearchResDto
*/
    public BookSearchResDto bookSearchDetail(String isbn) {
        List<BookSearchDetailDto.Items> items = searchInfo(isbn).getItems();
        if (searchInfo(isbn).getItems().isEmpty() || !Pattern.matches("^.{10}\\s.{13}$", isbn)) //검색 결과가 없거나, isbn 형식이 잘못됐을 때
        {}

        BookSearchDetailDto.Items item = items.get(0);
        String[] str = isbn.split(" ");

        return BookSearchResDto.builder()
                .bookTitle(item.title)
                .bookThumbnail(item.image)
                .author(item.author)
                .publisher(item.publisher)
                .isbn(item.isbn)
                .build();
    }
```

### List<>

### builder()

builder() 메소드는 빌더 패턴을 구현하는 데 사용된다. 빌더 패턴은 복잡한 객체를 생성하기 위한 디자인 패턴이다. 빌더 패턴을 사용하면 필수 및 선택적 필드를 개별적으로 설정할 수 있어 객체를 생성하는 코드를 더 간결하고 읽기 쉽게 만들 수 있다.

Spring은 @Builder 애노테이션을 사용하여 빌더 패턴을 구현하는 것을 지원한다.

Spring에서 builder() 메소드는 다음과 같은 역할을 한다.

- 빌더 객체를 반환
- 빌더 객체에 필드를 설정하기 위한 메소드를 제공.
- 빌더 객체를 사용하여 객체를 생성

```java
@Data
@Builder
public class User {
	private String name;
	private int age;
	private String address;
}
```

이 코드를 사용하여 다음과 같이 User 객체를 생성할 수 있다.

```java
User user = User.builder()
        .name("John Doe")
        .age(30)
        .address("서울시 강남구")
        .build();
```

이 코드는 다음과 같은 User 객체를 생성한다.

`User(name=John Doe, age=30, address=서울시 강남구)`

```java
/**
     * 책 검색
     * localhost:8080/search
     *
     * @param bookkeyword
     * @param start
     * @return ResponseEntity
     */
    @GetMapping("")
    public ResponseEntity<?> search(
            @RequestParam String bookkeyword,
            @RequestParam int start) {
        log.info("[Request] search");
        return new ResponseEntity<>(bookSearchService.bookSearch(bookkeyword, start), HttpStatus.OK);
    }
```

### @RequestParam

> ex) [http://xxx.x.x?index=1&page=2](http://xxx.x.x/?index=1&page=2)
>

**파라미터의 값과 이름을 함께 전달**하는 방식으로 게시판 등에서 페이지 및 검색 정보를 함께 전달하는 방식을 사용할 때 많이 사용한다

⇒@RequestParam 사용

> ex) /getDriver?name=**"name에 담긴 value"**
>

위의 경우 url이 전달될 때 name 파라미터(name에 담긴 value)를 받아온다

**@RequestParam("실제 값") String 설정할 변수 이름**으로 표현한다

이렇게 @RequestParam의 경우 url뒤에 붙는 파라미터의 값을 가져올 때 사용한다.

### **ResponseEntity**

ResponseEntity란, httpentity를 상속받는, 결과 데이터와 HTTP 상태 코드를 직접 제어할 수 있는 클래스이다.

ResponseEntity에는 사용자의  HttpRequest에 대한 응답 데이터가 포함된다.

ResponseEntity 클래스를 사용하면 결과값, 상태코드 헤더값을 모두 프론트에 넘겨줄 수 있고, 에러코드 또한 섬세하게 설정해서 보내줄 수 있다는 장점이 있다.

생성자를 이용하는 대신, **UriComponentsBuilder** 클래스를 이용하여 **UriComponents**의 인스턴스를 생성한다.

```java
@PostMapping("/detail")
    public ResponseEntity<?> bookSearchDetail(@AuthenticationPrincipal User user, @Valid @RequestBody BookSearchReqDto bookSearchReqDto) {
        log.info("[Request] Add book " + user.getEmail());
        AddSearchDetailResDto result = bookSearchService.addSearchDetail(user, bookSearchReqDto);
        if (result == null)
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }
```

## 책 저장 코드

### @AuthenticationPrincipal

스프링 시큐리티의 AuthenticationPrincipalArgumentResolver 클래스를 활용하여 SecurityContextHolder에 접근해서 값을 돌려준다.

### @Valid

객체 안에서 들어오는 값에 대해 검증이 가능해진다.

### -addSearchDeatail-

```java
/**
     * addBookSearchDetail
     *
     * @param user
     * @param bookSearchReqDto
     * @return addSearchDetailResDto
     */
    @Transactional
    public AddSearchDetailResDto addSearchDetail(User user, BookSearchReqDto bookSearchReqDto) {

        Book save = bookRepository.save(buildBookFromReq(user, bookSearchReqDto)); //책 저장
        return null;
    }
```

### @Transactional

@Transactional이 적용된 메소드가 호출될 경우,

PlatformTransactionManager를 사용하여 트랜잭션을 시작하고, 정상 여부에 따라 Commit/Rollback 동작을 수행한다.

`@Transactional(readOnly = true)`

조회용 메서드에 대해서는 @Transactional(readOnly = true) 를 설정함

### ****Entity****

데이터베이스(Database, DB) 에 쓰일 필드와 여러 엔티티간 연관관계를 정의한다. 데이터베이스는 엑셀처럼 2차원 테이블이라고 생각하면 되는데, 이 테이블에 서비스에서 필요한 정보를 다 저장하고 활용하게 된다.

아래 그림과 같이 세로의 열 부분이**Column**이고, 가로의 행 부분이 엔티티 객체가 된다. 이 테이블 전체가 **엔티티**이고, 각 1개의 행들이 **엔티티 객체**가 되는 것이라고 생각하면 된다. 이것을 스프링, JPA의 코드로 표현해보자.

![Untitled](https://github.com/minheebaek/TIL/assets/105588896/1bccce30-88e0-44a6-8945-7bc41f9c9283)

**필드**라는 것은 엔티티의 각 Column을 의미한다. 아래 예시에서 "private Long bno"라고 적은 것처럼 bno라는 필드를 정의하면 하나의 Column을 정의할 수 있는 것이다.

![Untitled (1)](https://github.com/minheebaek/TIL/assets/105588896/a3bf44f5-5773-427c-854a-ed298ce65763)

### Repository

Entity에 의해 생성된 DB에 접근하는 메서드(ex) findAll()) 들을 사용하기 위한 인터페이스이다. 위에서 엔티티를 선언함으로써 데이터베이스 구조를 만들었다면, 여기에 **어떤 값을 넣거나, 넣어진 값을 조회하는 등의 CRUD(Create, Read, Update, Delete)를 해야 쓸모가 있는데, 이것을 어떻게 할 것인지 정의해주는 계층**이라고 생각하면 된다.

### **JpaRepository**

**JpaRepository**를 상속받도록 함으로써 기본적인 동작이 모두 가능해진다! JpaRepository는 어떤 엔티티를 메서드의 대상으로 할지를 다음 키워드로 지정한다.

**JpaRepository<대상으로 지정할 엔티티, 해당 엔티티의 PK의 타입>**

![Untitled (2)](https://github.com/minheebaek/TIL/assets/105588896/a74c8bd7-bf5a-4abc-9b3a-c7c34e4ffead)

이렇게 extends를 통해서 상속받고나면, 해당 레포지토리의 객체를 이용해서 기본적으로 제공되는 메서드(save(), findAll(), get()) 등을 사용할 수 있게 된다.

### @Enum

@Constraint(validatedBy = {EnumValidator.class}) //annotation이 실행 할 ConstraintValidator 구현체
@Target({ElementType.METHOD, ElementType.FIELD, ElementType.PARAMETER}) //메소드, 필드, 파라미터에 적용 가능
@Retention

### @JsonCreator

역 직렬화에 사용되는 생성자나 팩토리를 조정할 수 있습니다.

필요한 대상 엔티티와 정확히 일치하지 않는 일부 JSON을 역 직렬화해야 할 때 유용합니다

역직렬화:특정 포맷 상태의 데이터를 다시 객체로 변환하는 것

### **toUpperCase()**

문자열을 모두 대문자로 바꾸어 변환해줍니다.

### Assert.notNull

Assert

- Spring Assert는 인수를 검증하고 조건에 맞지 않는 경우 IllegalArgumentException 또는 IllegalStateException를 발생
- 조건문을 단순화하고 반복적인 코드를 줄이는 역할

| 함수 | 설명 |
| --- | --- |
| notNull | 해당 객체가 not null이면 OK |