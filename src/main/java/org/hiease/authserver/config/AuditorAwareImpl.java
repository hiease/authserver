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

        String username = (String) authentication.getPrincipal();
        LOGGER.debug("Returning username: {}", username);

        return username;
    }
}

