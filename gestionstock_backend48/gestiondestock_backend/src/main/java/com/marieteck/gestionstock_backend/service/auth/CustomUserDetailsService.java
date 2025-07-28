package com.marieteck.gestionstock_backend.service.auth;

import com.marieteck.gestionstock_backend.dto.UsersDto;
import com.marieteck.gestionstock_backend.dto.auth.ExtendedUser;
import com.marieteck.gestionstock_backend.service.UsersServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CustomUserDetailsService implements UserDetailsService {


    @Autowired
    private UsersServices usersServices;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        UsersDto usersDto = usersServices.findByEmail(email);

        List<SimpleGrantedAuthority> authorities = new ArrayList<>();
        usersDto.getRoles().forEach(role -> authorities.add(new SimpleGrantedAuthority(role.getRoleName())));

        return new ExtendedUser(usersDto.getEmail(), usersDto.getMotDePasse(), usersDto.getEntrepriseDto().getId(), authorities);
    }

}
