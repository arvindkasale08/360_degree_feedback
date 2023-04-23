package com.intuit.review.user.mgmt;

import java.util.List;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import com.intuit.review.user.mgmt.domain.bo.user.UserBo;
import com.intuit.review.user.mgmt.service.account.UserTrie;
import com.intuit.review.user.mgmt.service.port.mongo.UserRepository;
import jakarta.annotation.PostConstruct;

@RequiredArgsConstructor
@Slf4j
public class TrieLoader {

	private final UserRepository repository;

	@PostConstruct
	public void postConstruct() {
		log.info("Post construct is called");
		List<UserBo> users = repository.getAllUsers().collectList().block();
		log.info("User count is {}", users.size());
		for (UserBo user : users) {
			UserTrie.insert(user.getName());
		}
		log.info("User Trie built successfully !!!");
	}
}
