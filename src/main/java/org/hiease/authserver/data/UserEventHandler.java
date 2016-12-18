package org.hiease.authserver.data;

import org.hiease.authserver.data.User;
import org.hiease.authserver.data.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.core.annotation.HandleBeforeCreate;
import org.springframework.data.rest.core.annotation.HandleBeforeSave;
import org.springframework.data.rest.core.annotation.RepositoryEventHandler;
import org.springframework.stereotype.Component;

/**
 * Created by qihaiyan on 2016/11/14.
 */
@Component
@RepositoryEventHandler(User.class)
public class UserEventHandler {

    @HandleBeforeCreate
    public void setDefaultPassword(User user) {
        user.setPassword(user.getUsername());
    }
}