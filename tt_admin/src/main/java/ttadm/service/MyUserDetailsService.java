package ttadm.service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ttadm.dao.DaoImpl;
import ttadm.model.UserRole;

@Service("userDetailsService")
public class MyUserDetailsService implements UserDetailsService {
	
	@Autowired
	DaoImpl dao;

	@Transactional(readOnly=true)
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

		ttadm.model.User user = dao.findByUserName(username);
		
		List<GrantedAuthority> authorities =
                buildUserAuthority(user.getUserRole());
		
		
		return buildUserForAuthentication(user, authorities);
	}

	
	private List<GrantedAuthority> buildUserAuthority(Set<UserRole> userRoles) {

		Set<GrantedAuthority> setAuths = new HashSet<GrantedAuthority>();

		// Build user's authorities
		for (UserRole userRole : userRoles) {
			setAuths.add(new SimpleGrantedAuthority(userRole.getRole()));
		}

		List<GrantedAuthority> Result = new ArrayList<GrantedAuthority>(setAuths);

		return Result;
	}
	
	// Converts ttadm.model.User user to
	// org.springframework.security.core.userdetails.User
	private User buildUserForAuthentication(ttadm.model.User user,
			List<GrantedAuthority> authorities) {
				
				return new User(user.getName(), user.getPassword(),	user.isEnabled(), true, true, true, authorities);
	}	
	
}
