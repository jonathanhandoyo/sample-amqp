package com.jonathan.service;

import com.jonathan.adapter.messaging.*;
import com.jonathan.domain.Conversation;
import com.jonathan.domain.User;
import com.jonathan.repository.ConversationRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

@AllArgsConstructor
@Service
public class ConversationService {

    private final ConversationRepository conversationRepository;

    private final FacebookAdapter facebookAdapter;
    private final LineAdapter lineAdapter;
    private final SmsAdapter smsAdapter;
    private final WechatAdapter wechatAdapter;
    private final WhatsappAdapter whatsappAdapter;

    public Conversation createConversation(User.Provider.Key platform, User creator) {
        Assert.notNull(platform, "Platform is null");
        Assert.notNull(creator, "Creator is null");

        Conversation conversation = new Conversation();
        conversation.setPlatform(platform);
        conversation.getAdminIds().add(creator.getId());
        conversation.getAdmins().add(creator);

        //TODO: send to platform

        return this.conversationRepository.save(conversation);
    }

    public Conversation addAdmin(Conversation conversation, User admin) {
        Assert.notNull(conversation, "Conversation is null");
        Assert.notNull(conversation.getId(), "Conversation.id is null");
        Assert.notNull(conversation.getPlatform(), "Conversation.platform is null");
        Assert.notNull(admin, "Admin is null");
        Assert.notNull(admin.getId(), "Admin.id is null");

        conversation.getAdminIds().add(admin.getId());
        conversation.getAdmins().add(admin);

        //TODO: send to platform

        return this.conversationRepository.save(conversation);
    }

    public Conversation removeAdmin(Conversation conversation, User admin) {
        Assert.notNull(conversation, "Conversation is null");
        Assert.notNull(conversation.getId(), "Conversation.id is null");
        Assert.notNull(conversation.getPlatform(), "Conversation.platform is null");
        Assert.notNull(admin, "Admin is null");
        Assert.notNull(admin.getId(), "Admin.id is null");

        conversation.getAdminIds().remove(admin.getId());
        conversation.getAdmins().remove(admin);

        //TODO: send to platform

        return this.conversationRepository.save(conversation);
    }

    public Conversation addParticipant(Conversation conversation, User participant) {
        Assert.notNull(conversation, "Conversation is null");
        Assert.notNull(conversation.getId(), "Conversation.id is null");
        Assert.notNull(conversation.getPlatform(), "Conversation.platform is null");
        Assert.notNull(participant, "Participant is null");
        Assert.notNull(participant.getId(), "Participant.id is null");

        conversation.getParticipantIds().add(participant.getId());
        conversation.getParticipants().add(participant);

        //TODO: send to platform

        return this.conversationRepository.save(conversation);
    }

    public Conversation removeParticipant(Conversation conversation, User participant) {
        Assert.notNull(conversation, "Conversation is null");
        Assert.notNull(conversation.getId(), "Conversation.id is null");
        Assert.notNull(conversation.getPlatform(), "Conversation.platform is null");
        Assert.notNull(participant, "Participant is null");
        Assert.notNull(participant.getId(), "Participant.id is null");

        conversation.getParticipantIds().remove(participant.getId());
        conversation.getParticipants().remove(participant);

        //TODO: send to platform

        return this.conversationRepository.save(conversation);
    }
}
