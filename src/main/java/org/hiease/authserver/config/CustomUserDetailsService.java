package org.hiease.authserver.config;

import org.hiease.authserver.data.User;
import org.hiease.authserver.data.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.util.Collection;

@Component
public class CustomUserDetailsService implements UserDetailsService {

	private final UserRepository userRepository;

	@Autowired
	private HttpServletRequest request;

	@Autowired
	public CustomUserDetailsService(UserRepository userRepository) {
		this.userRepository = userRepository;
	}

	public UserDetails loadUserByUsername(String username)
			throws UsernameNotFoundException {
		User user = userRepository.findByUsernameAndStatus(username,"0");
		if (user == null) {
			throw new UsernameNotFoundException("Could not find user " + username);
		}
		return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(), getAuthorities(user));
	}

	public Collection<? extends GrantedAuthority> getAuthorities(User user) {
		String authorities="ROLE_USER";
	    if (user != null) {
	        String userAdmin = user.getIsAdmin();
	        if (userAdmin != null && userAdmin.equals("Y")) {
                authorities += ",ROLE_ADMIN";
//	            return AuthorityUtils.createAuthorityList("ROLE_ADMIN");
	        }
	    }
		return AuthorityUtils.commaSeparatedStringToAuthorityList(authorities);
	}

	private String getClientIP() {
		final String xfHeader = request.getHeader("X-Forwarded-For");
		if (xfHeader == null) {
			return request.getRemoteAddr();
		}
		return xfHeader.split(",")[0];
	}
}
