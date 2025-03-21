package com.example.demo.jwt;

import org.springframework.core.convert.converter.Converter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.oauth2.jwt.Jwt;

import java.util.ArrayList;
import java.util.List;

public class CustomJwtConverter implements Converter<Jwt, CustomJwt> {
    @Override
    public CustomJwt convert(Jwt source) {
        List<GrantedAuthority> authorities = new ArrayList<>();
        var customJwt = new CustomJwt(source, authorities);
        customJwt.setFirstName(source.getClaimAsString("firstName: "));
        customJwt.setLastName(source.getClaimAsString("lastName: "));
        return null;
    }

    private List<GrantedAuthority> extracAuthorities(Jwt source) {

        var result = new ArrayList<GrantedAuthority>();

        var realm_access = source.getClaimAsMap("realm_access");
        if (realm_access != null && realm_access.get("roles") != null) {
            var roles = realm_access.get("roles");
            if (roles instanceof List l) {
                l.forEach(role -> {
                    result.add(new SimpleGrantedAuthority("ROLE_" + role));
                });
            }
        }

        return result;
    }




}
