package alken1t.runtime.kz.springpractice_9_00.security;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class GrantedAuthorityImpl implements org.springframework.security.core.GrantedAuthority {

    private final String authority;

    private final boolean role;


    @Override
    public String getAuthority() {
        if (role==true){
            return "ROLE_"+ authority;
        }
        return authority;
    }
}
