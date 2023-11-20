package com.beta.replyservice;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class ReplyControllerTest {

    @Autowired
    private ReplyController replyController;

    @Test
    void replying_With_No_Message_Called_For_V1() {
        ReplyMessage expectedOutput = new ReplyMessage("Message is empty");
        ReplyMessage actualOutput = replyController.replying();

        assertEquals(expectedOutput, actualOutput);
    }

    @Test
    public void replying_With_Message_Called_For_V1() {
        String inputText = "kbzw9ru";
        ReplyMessage expectedOutput = new ReplyMessage("kbzw9ru");
        ReplyMessage actualOutput = replyController.replying(inputText);

        assertEquals(expectedOutput, actualOutput);
    }

    @Test
    public void replying_Message_With_Rules_With_Valid_Input_Called_For_V2() {
        String inputText = "12-kbzw9ru";
        ResponseEntity<ReplyMessage> expectedOutput = new ResponseEntity<ReplyMessage>(new ReplyMessage("5a8973b3b1fafaeaadf10e195c6e1dd4"), HttpStatus.OK);
        ResponseEntity<ReplyMessage> actualOutput = replyController.replyingMessageWithRules(inputText);

        assertEquals(expectedOutput, actualOutput);
    }

    @Test
    public void replying_Message_With_Rules_With_Invalid_Input_Called_For_V2() {
        String inputText = "13-kbzw9ru";
        ResponseEntity<ReplyMessage> expectedOutput = new ResponseEntity<ReplyMessage>(new ReplyMessage("Invalid text"), HttpStatus.BAD_REQUEST);
        ResponseEntity<ReplyMessage> actualOutput = replyController.replyingMessageWithRules(inputText);

        assertEquals(expectedOutput.getStatusCode(), actualOutput.getStatusCode());
    }
}