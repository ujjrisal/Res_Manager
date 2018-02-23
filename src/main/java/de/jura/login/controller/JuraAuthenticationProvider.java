package de.jura.login.controller;

import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Component;

import de.jura.role.service.RoleServiceImpl;
import de.jura.user.data.User;
import de.jura.user.service.UserServiceImpl;

@Component("JuraAuthenticationProvider")
@ManagedBean(name = "JuraAuthenticationProvider")
public class JuraAuthenticationProvider implements AuthenticationProvider {

	@Autowired
	UserServiceImpl userService;

	@Autowired
	
	RoleServiceImpl roleService;

	
	
	// Authenticates an user, reads the permission that a user has & adds it to the granted authorities.
	public Authentication authenticate(Authentication authentication)
			throws AuthenticationException {
		String userName = authentication.getName();
		String password = (String) authentication.getCredentials();

		User user = userService.retrieveUserByLogin(userName);

		if (user == null) {
			throw new BadCredentialsException("Username not found.");
		}

		if (!password.equals(user.getPassword())) {
			throw new BadCredentialsException("Wrong password.");
		}

		// read permissions of logged in user and add it to authority

		List<String> permissions = roleService.retrievePermissionByRole(user
				.getRoleId());
		List<GrantedAuthority> grantedAuths = new ArrayList<GrantedAuthority>();

		for (String permission : permissions) {
			System.out.println("string equivalent permission is:"
					+ permission.toString());

			grantedAuths.add(new SimpleGrantedAuthority(permission));

		}

		return new UsernamePasswordAuthenticationToken(user, password,
				grantedAuths);
	}

	
	public boolean supports(Class<?> authentication) {
		return authentication.equals(UsernamePasswordAuthenticationToken.class);
	}

}
