package com.nanodegree.superduperdrive.services;

import com.nanodegree.superduperdrive.mapper.UserMapper;
import com.nanodegree.superduperdrive.model.User;
import org.springframework.stereotype.Service;

import java.security.SecureRandom;
import java.util.Base64;

/**
 * The type User service.
 */
@Service
public class UserService {

    private final UserMapper userMapper;
    private final HashService hashService;

    /**
     * Instantiates a new User service.
     *
     * @param userMapper  the user mapper
     * @param hashService the hash service
     */
    public UserService(UserMapper userMapper, HashService hashService) {
        this.userMapper = userMapper;
        this.hashService = hashService;
    }

    /**
     * Is user name available boolean.
     *
     * @param username the username
     * @return the boolean
     */
    public boolean isUserNameAvailable(String username) {
        return userMapper.getUser(username) == null;
    }

    /**
     * Create user int.
     *
     * @param user the user
     * @return the int
     */
    public int createUser(User user) {
        SecureRandom random = new SecureRandom();
        byte[] salt = new byte[16];
        random.nextBytes(salt);
        String encodedSalt = Base64.getEncoder().encodeToString(salt);
        String hashPassword = hashService.getHashedValue(user.getPassword(),encodedSalt);
        return userMapper.insert(new User(null,user.getUsername(),encodedSalt,hashPassword, user.getFirstName(), user.getLastName()));
    }

    /**
     * Gets user.
     *
     * @param username the username
     * @return the user
     */
    public User getUser(String username) {
        return userMapper.getUser(username);
    }
}
