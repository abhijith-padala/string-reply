package com.beta.replyservice;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
public class RestServiceApplicationTest {

	@Test
	public void testReverseString() {
		ReplyController controller = new ReplyController();
		String reversed = controller.reversalOfString("abc");
		assertEquals("cba", reversed);
	}

	@Test
	public void testMd5Encode() {
		ReplyController controller = new ReplyController();
		String encoded = controller.encodeMD5("abc");
		assertEquals("900150983cd24fb0d6963f7d28e17f72", encoded);
	}

}
