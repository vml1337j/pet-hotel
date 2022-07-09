package vml1337j.hotelapp.security;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import vml1337j.hotelapp.security.UserDetailsImpl;
import vml1337j.hotelapp.service.authorization.service.AuthorizationAttemptService;
import vml1337j.hotelapp.store.User;
import vml1337j.hotelapp.store.UserRepository;

import javax.servlet.http.HttpServletRequest;

@RequiredArgsConstructor
@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    private final UserRepository userRepository;
    private final AuthorizationAttemptService loginAttemptService;
    private final HttpServletRequest request;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        String ip = getClientIP();

        if (loginAttemptService.isBlocked(ip)) {
            throw new RuntimeException("blocked");
        }

        try {
            User user = userRepository.findByUsername(username)
                    .orElseThrow(() -> new UsernameNotFoundException(
                            String.format("User with username \"%s\" not found", username)
                    ));

           return new UserDetailsImpl(user);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private String getClientIP() {
        String xfHeader = request.getHeader("X-Forwarded-For");

        if (xfHeader == null){
            return request.getRemoteAddr();
        }

        return xfHeader.split(",")[0];
    }
}
