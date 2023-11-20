package com.beta.replyservice;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.web.server.ResponseStatusException;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class ReplyServiceTest {

    @Autowired
    private ReplyService replyService;

    @Test
    public void reply_Service_Called_With_Rule_11_And_Valid_Text() {
        String inputText = "11-kbzw9ru";
        ReplyMessage expectedOutput = new ReplyMessage("kbzw9ru");

        ReplyMessage actualOutput = replyService.getReplyWithRules(inputText);

        assertEquals(expectedOutput.getMessage(), actualOutput.getMessage());
    }

    @Test
    void reply_Service_Called_With_Rule_12_And_Valid_Text() {
        String inputText = "12-kbzw9ru";
        ReplyMessage expectedOutput = new ReplyMessage("5a8973b3b1fafaeaadf10e195c6e1dd4");

        ReplyMessage actualOutput = replyService.getReplyWithRules(inputText);

        assertEquals(expectedOutput.getMessage(), actualOutput.getMessage());
    }

    @Test
    void reply_Service_Called_With_Rule_22_And_Valid_Text() {
        String inputText = "22-kbzw9ru";
        ReplyMessage expectedOutput = new ReplyMessage("e8501e64cf0a9fa45e3c25aa9e77ffd5");

        ReplyMessage actualOutput = replyService.getReplyWithRules(inputText);

        assertEquals(expectedOutput.getMessage(), actualOutput.getMessage());
    }

    @Test
    void reply_Service_Called_With_Rule_And_Empty_Text() {
        String inputText = "22-";
        ReplyMessage expectedOutput = new ReplyMessage("Message is empty");

        ReplyMessage actualOutput = replyService.getReplyWithRules(inputText);

        assertEquals(expectedOutput.getMessage(), actualOutput.getMessage());
    }

    @Test
    void reply_Service_Called_With_Invalid_Rule() {
        String inputText = "13-kbzw9ru";

        assertThrows(ResponseStatusException.class ,() -> {
            replyService.getReplyWithRules(inputText);
        });
    }

    @Test
    void reply_Service_Called_With_Rule_And_Hyphen_Not_Present() {
        String inputText = "13kbzw9ru";

        assertThrows(ResponseStatusException.class ,() -> {
            replyService.getReplyWithRules(inputText);
        });
    }

    @Test
    void reply_Service_Called_With_Rule_And_Input_Text_Not_Present() {
        String inputText = "13";

        assertThrows(ResponseStatusException.class ,() -> {
            replyService.getReplyWithRules(inputText);
        });
    }

}