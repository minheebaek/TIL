# `BookController`

### `UriComponentsBuilder`

**UriComponents 를 Build할 수 있도록 도와주는 클래스**입니다.

**UriComponents** 클래스의 생성자는 모두 **package-private** 또는 **private** 이기 때문에, 개발자가 이를 직접 구현하지 않는 이상 **생성자를 통해 직접 생성할 수는 없습니다.**

우리는 생성자를 이용하는 대신, **UriComponentsBuilder** 클래스를 이용하여

**UriComponents**의 인스턴스를 생성할 수 있습니다.

```java
URI uri = UriComponentsBuilder
                .fromUriString("https://openapi.naver.com") //객체 생성
                .path("/v1/search/book.json")
                .queryParam("query", text)
                .queryParam("display", 15)
                .queryParam("start", 1)
                .queryParam("sort", "sim")
                .encode()
                .build()
                .toUri();
```

1. **Static Factory Method** 중에 하나를 이용하여 **UriComponentsBuilder** 객체를 생성합니다.→fromUriString 사용

2. 대응되는 각 메서드를 이용하여 URI 구성요소를 설정하거나 인코딩을 설정합니다.
   →path(String), queryParam(String, Object..), **encode(void or Charset) → (void일 경우 UTF-8 로 인코딩)**
3. **build()** 메서드를 이용하여 UriComponents 인스턴스를 Build 합니다.
   또는 **buildAndExpand()** 메서드를 이용하여 **URI 템플릿 변수를 설정한 후 Build**합니다.

위 세 단계를 거치게 되면 최종적으로 **UriComponents** 인스턴스가 생성됩니다.

그러면 이제 **UriComponents의 toUri() 메서드를 이용하여 URI 객체로 변환**한 후, 이를 이용해 원하는 작업을 수행하면 됩니다.

또는 **uriString()** 메서드를 이용해 **String 형태의 URI**를 얻어낸 후 이용해도 됩니다.

```java
//toUriString()을 이용한 방법
UriComponents uriComponents5 = UriComponentsBuilder
    .fromUriString("https://example.com/test/{testVariable}")
    .queryParam("q", "{q}").encode().buildAndExpand("test", "12345");
    // https://example.com/test/test?q=12345
```

**UriComponentsBuilder**의 메서드들은 반환값이 **UriComponentsBuilder**이므로 **메서드 체이닝**을 이용하여 간단하게 설정할 수 있습니다.

각 구성요소를 설정하는 메서드를 이용해 값을 지정하고, 문자를 인코딩하려는 경우 **encode()** 메서드를 이용하면 됩니다.

또, **URI 내에 { } 를 이용해 URI 템플릿 변수를 지정한 경우**, **buildAndExpand() 메서드에서 그 순서대로 실제 값을 지정**하면 됩니다.

스프링 프레임워크의 **UriComponentsBuilder**는 **URI를 인코딩함에 있어 두 가지 메서드를 제공**합니다.

1. **UriComponentsBuilder** 클래스의 **encode()**
- **URI 템플릿을 먼저 인코딩**한 후, URI 템플릿 변수 위치에 URI 변수 값을 삽입할 때 그 값을 인코딩한다.
1. **UriComponents** 클래스의 **encode() →** 이거 사용됨
- URI에 포함된 템플릿 변수 위치에 **URI 변수 값을 삽입한 후에 URI 컴포넌트를 인코딩**한다.

두 가지 **encode()** 메서드는 모두 **non-ASCII** 문자와 **URI에 적절하지 않은 문자**를 인코딩합니다.

그러나, **①의 encode()**는 URI 변수에 포함된 **reserved 문자까지 인코딩**하게 됩니다.

따라서, 이러한 값들이 인코딩되지 않게 하기 위해서는 **②의 encode()**를 이용해야만 합니다.

```java
UriComponents uriComponents2 = UriComponentsBuilder
    .fromUriString("https://example.com/test/{testVariable}")
    .queryParam("q", "{q}")
    .encode() // UriComponentsBuilder의 encode() 메서드 이용 => 
    .buildAndExpand("testVariable;", "123+45");

// https://example.com/test/testVariable%3B?q=123%2B45
```

```java
// Spring 요청 제공 클래스
RequestEntity<Void> req = RequestEntity
                .get(uri)
                .header("X-Naver-Client-Id", clientId)
                .header("X-Naver-Client-Secret", clientSecret)
                .build();
RestTemplate restTemplate = new RestTemplate();
ResponseEntity<String> resp = restTemplate.exchange(req, String.class);
```

### `RequestEntity`

springframework에서 제공하는 HttpEntity 의 상속클래스.

url 요청을 보낼 때 사용. responseEntity랑 짝꿍임

header, body, method, url, type을 생성자 파라미터로 넘길 수 있다.

### `RestTemplate`

exchange 메소드를 사용해서 http 요청을 보내고 ResponseEntity 객체를 return 받는다

url, method, requestEntity, responseType 을 생성자 파라미터로 넘길 수 있다.

```java
// JSON 파싱 (Json 문자열을 객체로 만듦, 문서화) 역직렬화
        ObjectMapper om = new ObjectMapper();
        NaverResultVO resultVO = null;

        try {
            resultVO = om.readValue(resp.getBody(), NaverResultVO.class);
        } catch (JsonMappingException e) {
            e.printStackTrace();
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
```

### `ObjectMapper`

**JSON 형식**을 사용할 때, 응답들을 **직렬화**하고 요청들을 **역직렬화** 할 때 사용하는 기술이다.

### 직렬화 (Serialize)

데이터를 전송하거나 저장할 때 바이트 문자열이어야 하기 때문에 객체들을 문자열로 바꾸어 주는 것

- **Object -> String 문자열**

### 역직렬화(Deserialize)

데이터가 모두 전송된 이후, 수신측에서 다시 문자열을 기존의 객체로 회복시켜주는 것

- **String 문자열 -> Object**

### String 문자열 => Object

```java
Car carObject = mapper.readValue(text, Car.class);
```

```java
resultVO = om.readValue(resp.getBody(), NaverResultVO.class);
```

역직렬화 과정

```java
List<BookVO> books =resultVO.getItems();
// books를 list.html에 출력 -> model선언
model.addAttribute("books", books);
```