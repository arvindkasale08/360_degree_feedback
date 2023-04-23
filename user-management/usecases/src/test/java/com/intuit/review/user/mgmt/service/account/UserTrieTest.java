package com.intuit.review.user.mgmt.service.account;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Test;

class UserTrieTest {

	UserTrie userTrie = new UserTrie();

	@Test
	void getSuggestions() {
		// given
		List<String> usernames = Arrays.asList("Arvind Kasale", "Ankit Bhatt", "Umesh Wadhwani", "Umakant Waghe", "Arpit Jain", "Mukund Waghmare");
		for (String u : usernames) {
			userTrie.insert(u);
		}

		// when then
		List<String> res1 = userTrie.getSuggestions("A");
		assertEquals(res1.size(), 3);

		List<String> res2 = userTrie.getSuggestions("U");
		assertEquals(res2.size(), 2);

	}
}
