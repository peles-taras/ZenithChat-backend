package com.zenith.user;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.zenith.user.Status.OFFLINE;
import static com.zenith.user.Status.ONLINE;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository repo;

    public void saveUser(User user) {
        user.setStatus(ONLINE);
        repo.save(user);
    }

    public void disconnect(User user) {
        User storedUser = repo.findById(user.getId())
                .orElse(null);

        if(storedUser != null){
            storedUser.setStatus(OFFLINE);
            repo.save(storedUser);
        }
    }

    public List<User> findConnectedUsers(Status status) {
        return repo.findAllByStatus(status);
    }
}
