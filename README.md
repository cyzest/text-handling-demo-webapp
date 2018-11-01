# text-handling-demo-webapp

[![Build Status](https://travis-ci.org/cyzest/text-handling-demo-webapp.svg?branch=master)](https://travis-ci.org/cyzest/text-handling-demo-webapp)
[![Code Coverage](https://codecov.io/gh/cyzest/text-handling-demo-webapp/branch/master/graph/badge.svg)](https://codecov.io/gh/cyzest/text-handling-demo-webapp)

### Text Handling Demo Web Application

1. Java 8 버전을 사용
1. Spring Boot 2.1 사용 (Spring Framework 5.1 기반)
1. Junit5 & Mockito로 단위테스트 작성

### Back-end 추가 라이브러리

1. Lombok - Boilerplate 코드 자동생성 용도
1. Jsoup - HTML 파싱 용도

### 빌드 및 실행 (Local)

* 빌드툴은 Maven을 활용
* 빌드 시 Java8과 Maven이 미리 설치되어 있어야 합니다.
* Open JDK
  - https://openjdk.java.net/install/index.html
  - https://adoptopenjdk.net/installation.html
* Maven
  - https://maven.apache.org/download.cgi

```console
$ git clone https://github.com/cyzest/text-handling-demo-webapp.git
$ cd text-handling-demo-webapp
$ mvn clean package
$ java -jar ./target/text-handler-1.0.0.jar
```
* http://localhost:8080 으로 접속하여 확인 가능합니다.
* 포트 정보는 application.properties 에서 수정 가능합니다.