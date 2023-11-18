package com.beta.replyservice;

import org.jetbrains.annotations.NotNull;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

@Service
public class RestService {

    final String INVALID_INPUT = "Invalid input";
    final String EMPTY_STRING = "";
    final int RULE_1_INDEX = 0;
    final int RULE_2_INDEX = 1;
    final int HYPHEN_INDEX = 2;
    final int TEXT_STARTING_INDEX = 3;

    public ReplyMessage getReplyWithRules(final String message) throws ResponseStatusException {

        int lengthOfInputText = message.length();

        if(lengthOfInputText < 3) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, INVALID_INPUT);
        }

        if(message.charAt(HYPHEN_INDEX) != '-') {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, INVALID_INPUT);
        }

        char rule1 = message.charAt(RULE_1_INDEX);
        char rule2 = message.charAt(RULE_2_INDEX);
        String inputText = message.substring(TEXT_STARTING_INDEX, lengthOfInputText);

        if(inputText.isEmpty()) {
            return new ReplyMessage("Message is empty");
        }

        String updatedTextAfterRule1 = changeAccordingToRule(inputText, rule1);
        String updatedTextAfterRule2 = changeAccordingToRule(updatedTextAfterRule1, rule2);

        return new ReplyMessage(updatedTextAfterRule2);
    }

    private String changeAccordingToRule(final String text,final char rule) throws ResponseStatusException {

        String updatedText = EMPTY_STRING;

        switch (rule) {
            case '1':
                updatedText = reverseString(text);
                break;

            case '2':
                try {
                    updatedText = encodeInputString(text);
                } catch (NoSuchAlgorithmException e) {
                    throw new RuntimeException(e);
                }
                break;
            default:
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST, INVALID_INPUT);
        }

        return updatedText;
    }

    private String encodeInputString(@NotNull final String str) throws NoSuchAlgorithmException {
        MessageDigest md = MessageDigest.getInstance("MD5");
        byte[] digest = md.digest(str.getBytes());
        BigInteger no = new BigInteger(1, digest);

        String myHash = no.toString(16);
        while(myHash.length() < 32) {
            myHash = "0" + myHash;
        }
        return myHash;
    }

    private String reverseString(@NotNull final String str) {
        StringBuilder sb = new StringBuilder(str);
        sb.reverse();
        return sb.toString();
    }
}
