package com.beta.replyservice;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.server.ResponseStatusException;

@RestController
public class ReplyController {

	@Autowired
	private ReplyService replyService;

	Logger logger = LoggerFactory.getLogger(ReplyController.class);

	@GetMapping("/reply")
	public ReplyMessage replying() {
		logger.info("Reply service V1 is called with empty message string");
		return new ReplyMessage("Message is empty");
	}

	@GetMapping("/reply/{message}")
	public ReplyMessage replying(@PathVariable String message) {
		logger.info("Reply service V1 is called with message string : " + message);
		return new ReplyMessage(message);
	}

	@GetMapping("/v2/reply/{message}")
	public ResponseEntity<ReplyMessage> replyingMessageWithRules(@PathVariable String message) throws ResponseStatusException {
		logger.info("Reply service V2 is called with message string : " + message);
		try {
			return new ResponseEntity<>(replyService.getReplyWithRules(message), HttpStatus.OK) ;
		}
		catch (ResponseStatusException ex) {
			return new ResponseEntity<>(new ReplyMessage("Invalid input"), HttpStatus.BAD_REQUEST) ;
		}
	}
}