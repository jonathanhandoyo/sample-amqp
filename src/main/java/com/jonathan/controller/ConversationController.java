package com.jonathan.controller;

import com.jonathan.domain.Conversation;
import com.jonathan.domain.User;
import com.jonathan.exception.BadRequestException;
import com.jonathan.exception.NotFoundException;
import com.jonathan.repository.ConversationRepository;
import com.jonathan.repository.UserRepository;
import com.jonathan.service.ConversationService;
import com.jonathan.util.CustomMapUtils;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.tuple.Pair;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.util.Map;
import java.util.stream.Collectors;

@AllArgsConstructor
@RequestMapping("/conversations")
@RestController
@Slf4j
public class ConversationController {

    private final ConversationService conversationService;

    private final ConversationRepository conversationRepository;
    private final UserRepository userRepository;

    @GetMapping
    public Iterable<Conversation> getConversations(@RequestParam User.Provider.Key platform,
                                               @RequestParam(required = false) String admin,
                                               @RequestParam(required = false) String participant) {
        if (!StringUtils.isEmpty(admin)) return this.conversationRepository.findAllByPlatformAndParticipantsIn(platform, admin).collect(Collectors.toList());
        if (!StringUtils.isEmpty(participant)) return this.conversationRepository.findAllByPlatformAndParticipantsIn(platform, participant).collect(Collectors.toList());

        throw new NotFoundException();
    }

    @GetMapping("/{id}")
    public Conversation getConversation(@PathVariable String id) {
        return this.conversationRepository.findOne(id);
    }

    @GetMapping("/{id}/members")
    public Map<String, Iterable<User>> getConversationMembers(@PathVariable String id) {
        Conversation conversation = this.conversationRepository.findOne(id);
        return CustomMapUtils
                .map(
                        Pair.of("admins", this.userRepository.findAll(conversation.getAdmins())),
                        Pair.of("participants", this.userRepository.findAll(conversation.getParticipants()))
                );
    }

    @PutMapping("/{conversation}/admins/{user}")
    public Conversation addAdmin(@PathVariable String conversation,
                                 @PathVariable String user) {
        Conversation convo = this.conversationRepository.findOne(conversation);
        User nominee = this.userRepository.findOne(user);

        if (convo != null && nominee != null) {
            convo.getAdmins().add(nominee.getId());
            return this.conversationService.addAdmin(convo, nominee);
        }

        throw new BadRequestException("Conversation or User not found");
    }

    @PutMapping("/{conversation}/participants/{user}")
    public Conversation addParticipant(@PathVariable String conversation,
                                       @PathVariable String user) {
        Conversation convo = this.conversationRepository.findOne(conversation);
        User nominee = this.userRepository.findOne(user);

        if (convo != null && nominee != null) {
            convo.getAdmins().add(nominee.getId());
            return this.conversationService.addParticipant(convo, nominee);
        }

        throw new BadRequestException("Conversation or User not found");
    }

    @DeleteMapping("/{conversation}/admins/{user}")
    public Conversation deleteAdmin(@PathVariable String conversation,
                                 @PathVariable String user) {
        Conversation convo = this.conversationRepository.findOne(conversation);
        User nominee = this.userRepository.findOne(user);

        if (convo != null && nominee != null) {
            convo.getAdmins().add(nominee.getId());
            return this.conversationService.removeAdmin(convo, nominee);
        }

        throw new BadRequestException("Conversation or User not found");
    }

    @DeleteMapping("/{conversation}/participants/{user}")
    public Conversation deleteParticipants(@PathVariable String conversation,
                                           @PathVariable String user) {
        Conversation convo = this.conversationRepository.findOne(conversation);
        User nominee = this.userRepository.findOne(user);

        if (convo != null && nominee != null) {
            convo.getAdmins().add(nominee.getId());
            return this.conversationService.removeParticipant(convo, nominee);
        }

        throw new BadRequestException("Conversation or User not found");
    }
}
