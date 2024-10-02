package com.Chiranjibi.JavaTrading.service;

import com.Chiranjibi.JavaTrading.domain.VerificationType;
import com.Chiranjibi.JavaTrading.models.User;
import jdk.jshell.spi.ExecutionControl;

public interface UserService {

    public User findUserProfileByJwt( String jwt) throws Exception;
    public User findUserProfileByEmail( String email) throws Exception;
    public User findUserProfileById( Long userId) throws Exception;


    User enableTwoFactorAuthentication(VerificationType verificationType, String sendTo, User user);

    User updatePassword(User user, String newPassword);

}
