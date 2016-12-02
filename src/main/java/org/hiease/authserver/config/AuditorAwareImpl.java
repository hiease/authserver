package org.hiease.authserver.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.AuditorAware;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

public class AuditorAwareImpl implements AuditorAware<String> {

    private static final Logger LOGGER = LoggerFactory.getLogger(AuditorAwareImpl.class);

    @Override
    public String getCurrentAuditor() {
        LOGGER.debug("Getting the username of authenticated user.");
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if (authentication == null || !authentication.isAuthenticated()) {
            LOGGER.debug("Current user is anonymous. Returning null.");
            return null;
        }

        org.springframework.security.core.userdetails.User user = (org.springframework.security.core.userdetails.User) authentication.getPrincipal();
        LOGGER.debug("Returning username: {}", user.getUsername());

        return user.getUsername();
    }
}

