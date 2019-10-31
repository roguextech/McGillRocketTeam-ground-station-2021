import static org.junit.jupiter.api.Assertions.*;

import java.io.IOException;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class UDPClientTest {
	
	UDPClient client;

	@BeforeEach
	void setUp() throws Exception {
		client = new UDPClient("192.168.137.177", 8888);
	}

	@AfterEach
	void tearDown() throws Exception {
		client.close();
	}

	@Test
	void test() {
		String response;
		try {
			response = client.sendMessage("HELLO WORLD!");
			assertEquals("Data Received", response);
			response = client.sendMessage("This is a TEST");
			assertEquals("Data Received", response);
		} catch (IOException e) {
			fail("Error");
		}
	}

}
