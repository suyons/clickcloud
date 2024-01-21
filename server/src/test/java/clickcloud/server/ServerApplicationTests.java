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
