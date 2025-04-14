package dw.gameshop.controller;

import dw.gameshop.chat.ChatMessage;
import dw.gameshop.chat.MessageType;
import dw.gameshop.model.User;
import dw.gameshop.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/notification")
public class NotificationController {
    @Autowired
    SimpMessagingTemplate messagingTemplate;
    @Autowired
    UserService userService;

    @PostMapping("/")
    public void takeNotification(@RequestBody String notification) {
        System.out.println(notification);
        User currentUser = userService.getCurrentUser();
        ChatMessage chatMessage = new ChatMessage(MessageType.NOTIFICATION, notification, "관리자", currentUser.getUsername());
        messagingTemplate.convertAndSendToUser(currentUser.getUsername(), "/queue/private", chatMessage);
    }
}
