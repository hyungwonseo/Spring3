package dw.gameshop.chat;

import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;

@Controller
public class PrivateChatController {

    private final SimpMessagingTemplate messagingTemplate;

    public PrivateChatController(SimpMessagingTemplate messagingTemplate) {
        this.messagingTemplate = messagingTemplate;
    }

    @MessageMapping("/chat.privateMessage")
    public void sendPrivateMessage(@Payload ChatMessage chatMessage) {
        System.out.println("비밀메시지 " + chatMessage.getReceiver());
        // 예: chatMessage.getSender()가 "steve", chatMessage.getReceiver()가 "tom"인 경우
        // tom의 사용자 전용 큐(/user/queue/private)로 메시지 전송
        messagingTemplate.convertAndSendToUser(
                chatMessage.getReceiver(), "/queue/private", chatMessage);
    }
}
