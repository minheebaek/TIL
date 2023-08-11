### create table : 테이블 생성

```sql
create table 테이블명
(
	컬럼명 데이터타입 [제약조건], ...
);
```

### alter table : 테이블 수정 - 컬럼

1. alter table 컬럼 추가
alter table 테이블명 add 컬럼명 테이터타입 [제약조건]
→사원 테이블의 전화번호라는 컬럼에 대해 타임이 varchar(11)이면서 unique 제약조건 걸도록 추가
2. alter table 컬럼 수정
alter table 테이블명 modify 컬럼명 테이터타입 [제약조건]
→사원테이블의 이름이라는 컬럼에 대해 타입이 varchar(30)이면서 not null 제약조건을 걸도록 수정
3. alter table 컬럼 삭제
    
    alter table 테이블명 drop 컬럼명;
    
    →사원 테이블에 생년월일이라는 컬럼을 삭제
    

1번 답 

```sql
alter table 사원 add 전화번호 varchar(11) unique;
```

2번

```sql
alter table 사원 modify 이름 varchar(30) not null; 
```

3번

```sql
alter table 사원 drop 생년월일;
```

## drop table - 테이블 삭제

drop table 테이블명 cascade | restrict

→사원 테이블 삭제

```sql
drop table 사원;
```

cascade : 연쇄적으로 삭제 

restrict : 다른 테이블이 삭제할 테이블을 참조중이면 제거하지 않음

## truncate table - 테이블 내 데이터 삭제

truncate table 테이블명;

→사원 테이블 내의 모든 데이터를 삭제

```sql
truncate table 사원;
```

## create view - 뷰생성

create view 뷰이름 as 

select 

from 

where

→사원 테이블에서 성별 값이 ‘M’을 가진 사번, 이름으로 생성된 사원뷰라는 이름의 뷰 생성

```sql
create view 사원뷰 as
select 사번, 이름
from 사원
where 성별 = 'M';
```

## create or replace view - 뷰교체

create or replace view 뷰이름 as 

조회쿼리;

## drop view

drop view 뷰이름;

## index 관련 ddl

## create index - 인덱스 생성

create index 인덱스명 on 테이블명(컬럼명1, 컬럼명2, … );

→사원 테이블의 사번 컬럼에 대해 사번인덱스라는 인덱스 명으로 인덱스 생성

```sql
create index 사번인덱스 on 사원(사번); 
```

## alter index - 인덱스 수정

alter index 인덱스명 on 테이블명(컬럼명1, 컬럼명2…);

→사원 테이블의 사번 컬럼에 대해 사번인덱스라는 인덱스 명으로 인덱스 생성

```sql
alter index 사번인덱스 on 사원(사번);
```

## drop index - 인덱스 삭제

drop index 인덱스명;

→사번인덱스라는 인덱스명을 가지고 있는 인덱스를 삭제

```sql
drop index 사번인덱스;
```

## insert - 데이터 삽입

insert into 테이블명(속성명1, …) 

values (데이터1, …);

→[학생]테이블에 학번이 6677, 성명 ‘장길산’, 학년이 3학년, 수강과목은 ‘수학’인 학생을 삽입

insert into 학생(학번, 성명, 학년, 수강과목)

values(6677, ‘장길산’, 3, ‘수학’);

## update -데이터 변경

update 테이블명

set 속성명 = 데이터

where 조건

→[학생]테이블에 장길산의 주소를 인천으로 수정

update 학생 set 주소 = ‘인천’ where 이름 = ‘장길산’;

## delete - 데이터 삭제

delete from 테이블명 where 조건; 

→학생 테이블에 장길산에 대한 튜플을 삭제

delete from 학생 where 이름=’장길산’;

## grant - 권한 부여

grant 권한 on 테이블명 to 사용자

→관리자가 사용자 장길산에게 ‘학생’ 테이블에 대해 update 할 수 있는 권한 부여

```sql
grant update on 학생 to 장길산; //(따옴표 없음)
```

## revoke -권한 회수

revoke 권한 on 테이블명 from 사용자

→관리자가 사용자 장길산에게 ‘학생’ 테이블에 대해 update 할 수 있는 권한 부여

```sql
revoke update on 학생 from 장길산;
```

## 조인

selec A.컬럼1, A.컬럼2, … B.컬럼1, B.컬럼2

from 테이블명1 A **join** 테이블명2 B

**on** 조인조건;

[left][right][full] join on 

[cross]은 on 안 붙임

## where절 like 사용

컬럼 like 패턴

% : 0개 이상의 문자열과 일치

_ : 특정 위치의 1개의 문자와 일치 

where 이름 like ‘이%’,   : ‘이’로 시작하는 사람 검색

where 이름 like ‘%이’,   : ‘이’로 끝나는 사람 검색

where 이름 like ‘이_’,    : ‘이’로 시작되고 ‘이’ 뒤에 1글자만 있는 사람 검색

where 이름 like ‘이__’,    : ‘이’로 시작되고 ‘이’ 뒤에 2글자만 있는 사람 검색
