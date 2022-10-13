package com.example.chapter06.config.security;

import com.example.chapter06.user.entity.User;
import com.example.chapter06.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.function.Supplier;

@Service
@RequiredArgsConstructor
public class JpaUserDetailService implements UserDetailsService {

    private final UserRepository userRepository;

    @Override
    public CustomUserDetail loadUserByUsername(String username) throws UsernameNotFoundException {

        // 예비 인스턴스를 만들기 위한 공급자 선언
        Supplier<UsernameNotFoundException> s =
                () -> new UsernameNotFoundException("Problem during authentication!");

        User user = userRepository
                .findByUsername(username) // 사용자를 포함한 Optional 인스턴스를 반환하거나 사용자가 없으면 비어 있는 Optional 인스턴스를 반환
                .orElseThrow(s); // Optional 인스턴스가 비어 있으면 정의된 공급자에서 생성한 예외를 투척하고, 그렇지 않으면 User 인스턴스르 반환

        return new CustomUserDetail(user); // User 인스턴스를 CustomUserDetails 데토레이터로 래핑하고 반환
    }
}
