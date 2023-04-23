package com.intuit.review.user.mgmt.service.account;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class UserTrie {

	public static final int MAX_SUGGESTIONS = 10;
	private Node root = new Node('\0');

	public void insert(String word) {
		Node curr = root;
		for (char ch : word.toCharArray()) {
			if (!curr.children.containsKey(ch)) {
				curr.children.put(ch, new Node(ch));
			}
			curr = curr.children.get(ch);
		}
		curr.isEnd = true;
	}

	public List<String> getSuggestions(String prefix) {
		List<String> result = new ArrayList<>();
		Node curr = getLast(prefix);
		if (curr == null)
			return result;
		StringBuilder sb = new StringBuilder();
		sb.append(prefix);
		dfs(curr, sb, result);
		return result;
	}

	private void dfs(Node curr, StringBuilder builder, List<String> result) {
		if (curr != null && curr.isEnd) {
			result.add(builder.toString());
		}
		if (curr == null || result.size() > MAX_SUGGESTIONS)
			return;
		for (Node child : curr.children.values()) {
			if (child != null) {
				builder.append(child.val);
				dfs(child, builder, result);
				builder.setLength(builder.length()-1);
			}
		}
	}

	private Node getLast(String prefix) {
		Node curr = root;
		for (char ch : prefix.toCharArray()) {
			if (!curr.children.containsKey(ch)) return null;
			curr = curr.children.get(ch);
		}
		return curr;
	}

	class Node {
		char val;
		Map<Character, Node> children;
		boolean isEnd;

		public Node(char val) {
			this.val = val;
			this.children = new HashMap<>();
			this.isEnd = false;
		}
	}
}
