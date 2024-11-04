package com.Hyoja1.springJWT.dto;

import com.Hyoja1.springJWT.domain.User;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;

@RequiredArgsConstructor
// 1. UserDetails 인터페이스: Spring Security에서 사용자의 인증 및 권한을 다루기 위해 사용되는
// 인터페이스로, 사용자의 권한(Authority), 비밀번호, 사용자명을 반환하는 메서드를 정의해야 합니다.
public class CustomUserDetails implements UserDetails {
    private final User user;

    // 2. getAuthorities 메서드: 사용자의 권한을 반환합니다. 이 예제에서는 User 엔티티의 role 필드를
    // 권한으로 설정하여 반환하고 있습니다. 이를 통해 사용자는 해당 권한에 맞는 접근 권한을 가지게 됩니다.
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        Collection<GrantedAuthority> collection = new ArrayList<>();

        collection.add(new GrantedAuthority() {
            @Override
            public String getAuthority() {
                return user.getRole();
            }
        });
        return collection;
    }

    // 3. getPassword 메서드: 사용자의 비밀번호를 반환합니다. Spring Security는 이 정보를 이용해
    // 입력된 비밀번호와 데이터베이스에 저장된 비밀번호를 비교하여 인증을 처리합니다.
    @Override
    public String getPassword() {
        return user.getPassword();
    }

    // 4. getUsername 메서드: 사용자의 이름을 반환합니다. username은 인증 시 기준이 되는 주요 정보로,
    // User 엔티티의 username 필드 값을 반환하여 사용자를 식별합니다.
    @Override
    public String getUsername() {
        return user.getUsername();
    }
}
