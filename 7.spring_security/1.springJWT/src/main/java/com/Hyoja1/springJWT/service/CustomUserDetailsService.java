package com.Hyoja1.springJWT.service;

import com.Hyoja1.springJWT.Repository.UserRepository;
import com.Hyoja1.springJWT.domain.User;
import com.Hyoja1.springJWT.dto.CustomUserDetails;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
// 1. UserDetailsService 인터페이스: Spring Security에서 사용자 인증 시, 사용자의 계정 정보를
// 조회하기 위해 사용하는 인터페이스입니다. 이 인터페이스를 구현하여 loadUserByUsername 메서드를 정의해야
// 합니다.
public class CustomUserDetailsService implements UserDetailsService {

    private final UserRepository userRepository;

    /**
     * 2. loadUserByUsername 메서드: 이 메서드는 사용자의 username을 파라미터로 받아 데이터베이스에서
     * 사용자 정보를 조회합니다. username을 통해 UserRepository에서 User 엔티티를 조회하고,
     * 조회된 User 객체를 CustomUserDetails에 담아 반환합니다. 만약 사용자 정보가 없으면 null을
     *  반환하여 인증 실패 처리를 유도합니다.
     */

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        // 3. DB에서 조회
        User user = userRepository.findByUsername(username);

        if(user != null){
            /**
             * 4. CustomUserDetails 객체 반환: CustomUserDetails는 UserDetails 인터페이스를 구현한
             * 클래스입니다. UserDetails를 통해 Spring Security는 인증된 사용자 정보를 관리하고,
             * 인증 매니저(AuthenticationManager)가 이 정보를 바탕으로 인증을 수행합니다.
             */

            return new CustomUserDetails(user);
        }
        throw new UsernameNotFoundException("User not found");
    }
}
