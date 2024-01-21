## JUnit 테스팅 툴 사용 방법

src 폴더에 보면 main 폴더와 test 폴더가 있다.
test 폴더에는 말 그대로 테스트를 하기 위한 클래스가 있다.

* 테스트 = 내가 만든 함수의 return 값이 내가 예상한 그대로 나올까?

1. `return "Bye Test"` 하는 함수를 하나 만들어 준다.
**src/main/java/clickcloud/server/service/TestService.java**
```java
package clickcloud.server.service;

import org.springframework.stereotype.Service;

@Service
public class TestService {
    public String helloTest() {
        return "Bye Test";
    }
}
```

2. 그 메서드는 `return "Hello Test"` 해 주니? 를 테스트할 수 있다.
**src/test/java/clickcloud/server/ServerApplicationTests.java**
```java
package clickcloud.server;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import clickcloud.server.service.TestService;

@SpringBootTest
class ServerApplicationTests {
    @Autowired
    private TestService testService;

	@Test
	public void testHello() {
        String result = testService.helloTest();
        assertEquals("Hello Test", result);
	}
}
```

3. 테스트를 실행하려면? Maven으로 프로젝트를 JAR 패키지로 빌드해 보자.

```
PS ...\clickcloud\server> ./mvnw clean package
```

```
[ERROR] Tests run: 1, Failures: 1, Errors: 0, Skipped: 0, Time elapsed: 3.039 s <<< FAILURE! -- in clickcloud.server.ServerApplicationTests
[ERROR] clickcloud.server.ServerApplicationTests.testHello -- Time elapsed: 0.745 s <<< FAILURE!
org.opentest4j.AssertionFailedError: expected: <Hello Test> but was: <Bye Test>
        at org.junit.jupiter.api.AssertionFailureBuilder.build(AssertionFailureBuilder.java:151)
        at org.junit.jupiter.api.AssertionFailureBuilder.buildAndThrow(AssertionFailureBuilder.java:132)
        at org.junit.jupiter.api.AssertEquals.failNotEqual(AssertEquals.java:197)
        at org.junit.jupiter.api.AssertEquals.assertEquals(AssertEquals.java:182)
        at org.junit.jupiter.api.AssertEquals.assertEquals(AssertEquals.java:177)
        at org.junit.jupiter.api.Assertions.assertEquals(Assertions.java:1145)
        at clickcloud.server.ServerApplicationTests.testHello(ServerApplicationTests.java:19)
        at java.base/java.lang.reflect.Method.invoke(Method.java:580)
        at java.base/java.util.ArrayList.forEach(ArrayList.java:1596)
        at java.base/java.util.ArrayList.forEach(ArrayList.java:1596)

[INFO] 
[INFO] Results:
[INFO]
[ERROR] Failures: 
[ERROR]   ServerApplicationTests.testHello:19 expected: <Hello Test> but was: <Bye Test>
[INFO]
[ERROR] Tests run: 1, Failures: 1, Errors: 0, Skipped: 0
[INFO]
[INFO] ------------------------------------------------------------------------
[INFO] BUILD FAILURE
[INFO] ------------------------------------------------------------------------
[INFO] Total time:  7.765 s
[INFO] Finished at: 2024-01-21T15:39:20+09:00
[INFO] ------------------------------------------------------------------------
```

4. 이번엔 "Bye Test"로 바꾸고 다시 실행한다면?
```java
package clickcloud.server;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import clickcloud.server.service.TestService;

@SpringBootTest
class ServerApplicationTests {
    @Autowired
    private TestService testService;

	@Test
	public void testHello() {
        String result = testService.helloTest();
        assertEquals("Bye Test", result);
	}
}
```

```
PS ...\clickcloud\server> ./mvnw clean package
```

```
[INFO] Tests run: 1, Failures: 0, Errors: 0, Skipped: 0, Time elapsed: 3.260 s -- in clickcloud.server.ServerApplicationTests
[INFO] 
[INFO] Results:
[INFO]
[INFO] Tests run: 1, Failures: 0, Errors: 0, Skipped: 0
[INFO]
[INFO] 
[INFO] --- jar:3.3.0:jar (default-jar) @ server ---
[INFO] Building jar: C:\Users\young\OneDrive\Documents\VSCODE\clickcloud\server\target\server-0.0.1-SNAPSHOT.jar
[INFO] 
[INFO] --- spring-boot:3.2.1:repackage (repackage) @ server ---
[INFO] Replacing main artifact C:\Users\young\OneDrive\Documents\VSCODE\clickcloud\server\target\server-0.0.1-SNAPSHOT.jar with repackaged archive, adding nested dependencies in BOOT-INF/.
[INFO] The original artifact has been renamed to C:\Users\young\OneDrive\Documents\VSCODE\clickcloud\server\target\server-0.0.1-SNAPSHOT.jar.original        
[INFO] ------------------------------------------------------------------------
[INFO] BUILD SUCCESS
[INFO] ------------------------------------------------------------------------
[INFO] Total time:  8.880 s
[INFO] Finished at: 2024-01-21T15:44:59+09:00
[INFO] ------------------------------------------------------------------------
```

5. 빌드 성공!