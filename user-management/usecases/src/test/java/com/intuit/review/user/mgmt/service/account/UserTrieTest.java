package com.intuit.review.user.mgmt.service.account;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Test;

class UserTrieTest {

	@Test
	void getSuggestions() {
		// given
		List<String> usernames = Arrays.asList("Arvind Kasale", "Ankit Bhatt", "Umesh Wadhwani", "Umakant Waghe", "Arpit Jain", "Mukund Waghmare");
		for (String u : usernames) {
			UserTrie.insert(u);
		}

		// when then
		List<String> res1 = UserTrie.getSuggestions("A");
		System.out.println(res1);
		assertEquals(res1.size(), 6);

		List<String> res2 = UserTrie.getSuggestions("U");
		System.out.println(res2);
		assertEquals(res2.size(), 4);

	}
}
