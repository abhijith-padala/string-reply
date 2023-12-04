package com.beta.replyservice;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PathVariable;

import java.security.MessageDigest;

@RestController
public class ReplyController {
	private static final Logger logger = LoggerFactory.getLogger(ReplyController.class);

	@GetMapping("/reply")
	public ReplyMessage replying() {
		return new ReplyMessage("Message is empty");
	}

	@GetMapping("/reply/{message}")
	public ReplyMessage replying(@PathVariable String message) {
		return new ReplyMessage(message);
	}

	@GetMapping("/v2/reply")
	public ReplyMessage replyingV2() {
		return new ReplyMessage("Message is empty");
	}

	@GetMapping("/v2/reply/{rule}-{message}")
	public ReplyMessage replyingV2(@PathVariable String rule, @PathVariable String message) {
		try {
			// A regular expression to check whether the rule is valid or not.
			validateRule(rule);
			if (message.equals(""))
				return new ReplyMessage("Message is empty");
			char[] rules = rule.toCharArray();

			for (char ruleChar : rules) {
				switch (ruleChar) {
					case '1':
						message = reversalOfString(message);
						break;
					case '2':
						message = encodeMD5(message);
						break;
				}
			}
			return new ReplyMessage(message);
		} catch (InvalidInputException e) {
			logger.error("Invalid input: {}", e.getMessage());
			return new ReplyMessage(HttpStatus.BAD_REQUEST.value(), "Invalid input");
		}
	}

	private void validateRule(String rule) {
		if (!rule.matches("[12]{2}")) {
			throw new InvalidInputException("Invalid rule format");
		}
	}

	public String reversalOfString(String message) {
		return new StringBuilder(message).reverse().toString();
	}

	public String encodeMD5(String message) {
		try {
			MessageDigest md = MessageDigest.getInstance("MD5");
			byte[] messageDigest = md.digest(message.getBytes());

			// Convert byte array to hex string
			StringBuilder hexString = new StringBuilder();
			for (byte b : messageDigest) {
				hexString.append(String.format("%02x", b));
			}
			return hexString.toString();
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
}