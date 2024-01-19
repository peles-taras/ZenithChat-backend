package com.zenith.chat;

import com.zenith.chatRoom.ChatRoomService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ChatMessageService {

    private final ChatMessageRepository repo;
    private final ChatRoomService chatRoomService;

    public ChatMessage save(ChatMessage chatMessage) {
        var chatId = chatRoomService.getChatRoomId(
                chatMessage.getSenderId(),
                chatMessage.getRecipientId(),
                true).orElseThrow(); // TODO: CREATE DEDICATED EXCEPTION;

        chatMessage.setChatId(chatId);
        return repo.save(chatMessage);
    }

    public List<ChatMessage> findChatMessages(
            String senderId, String recipientId
    ){
        var chatId = chatRoomService.getChatRoomId(
                senderId,
                recipientId,
                false);

        return chatId.map(repo::findByChatId).orElse(new ArrayList<>());
    }
}
