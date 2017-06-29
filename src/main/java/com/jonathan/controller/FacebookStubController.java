package com.jonathan.controller;

import com.jonathan.domain.Conversation;
import com.jonathan.domain.User;
import com.jonathan.repository.UserRepository;
import com.jonathan.service.ConversationService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("/fb")
@Slf4j
public class FacebookStubController {

    private final ConversationService conversationService;

    private final UserRepository userRepository;

    @GetMapping("/conversations")
    public List<Conversation> getConversations(@RequestParam User.Provider.Key platform,
                                               @RequestParam String participantId) {
        return this.conversationService.findConversation(platform, this.userRepository.findOne(participantId));
    }
}
