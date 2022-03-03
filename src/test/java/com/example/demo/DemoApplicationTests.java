package com.example.demo;

import com.example.demo.domain.appuser.User;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class DemoApplicationTests {

	@Test
	void savedUserHasName() {
		User user = new User(null, "testuser", "testmail", "testpassword", null);
		assert (!user.getUsername().isEmpty());
	}

}
