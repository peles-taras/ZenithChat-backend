package com.zenith.user;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @MessageMapping("/user.addUser")
    @SendTo("/user/topic")
    public User saveUser(@Payload User user) {
        userService.saveUser(user);
      return user;
    }

    @MessageMapping("/user.disconnectUser")
    @SendTo("/user/topic")
    public User disconnect (@Payload User user){
        userService.disconnect(user);
        return user;
    }

    public ResponseEntity<List<User>> findAllConnectedUsers (){
        return ResponseEntity.ok(userService.findConnectedUsers(Status.ONLINE));
    }
}