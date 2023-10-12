# repository가 인식이 안되는 현상(is never used)
![image](https://github.com/minheebaek/TIL/assets/105588896/e591e1de-8443-412a-b3df-d5d88c06938b)

인식이 안되어서 무엇이 잘못되었는지 며칠간 고민했다.
![image](https://github.com/minheebaek/TIL/assets/105588896/ca1c8337-70d2-4ec3-b652-e4a2a483d8dd)
에러도 명확하게 안 보여줘서 무엇을 빼먹었는지 몰랐다.

그래서 repository 와 entity 개념을 다시 공부하던 중 전역변수 설정을 빼먹었다는 것을 알았다.

`private final BookRepository bookRepository;`

넣으니 해결되었다.

### 깨달은점

크게 오류가 난 줄 알았는데 기본적인 것을 빼먹은거라 더 꼼꼼하게 확인하는 습관을 가져야겠다.

기본적인 것을 빠뜨리지 않도록 조심하자