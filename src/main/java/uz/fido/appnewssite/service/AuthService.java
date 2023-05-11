package uz.fido.appnewssite.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import uz.fido.appnewssite.entity.User;
import uz.fido.appnewssite.exceptions.ResourceNotFoundException;
import uz.fido.appnewssite.payload.ApiResponce;
import uz.fido.appnewssite.payload.RegisterDto;
import uz.fido.appnewssite.repository.RoleRepository;
import uz.fido.appnewssite.repository.UserRepository;
import uz.fido.appnewssite.utils.AppConstants;

@Service
public class AuthService implements UserDetailsService {
    @Autowired
    UserRepository userRepository;
    @Autowired
    RoleRepository roleRepository;
    @Autowired
    PasswordEncoder passwordEncoder;

    public ApiResponce registerUser(RegisterDto registerDto) {
        if (!registerDto.getPassword().equals(registerDto.getPrePassword()))
            return new ApiResponce("Parollar mos emas", false);
        boolean existsByUsername = userRepository.existsByUsername(registerDto.getUsername());
        if (existsByUsername) {
            return new ApiResponce("Bunday username avval ro'yxatdan o'tgan", false);
        }
        User user = new User(
                registerDto.getFullName(),
                registerDto.getUsername(),
                passwordEncoder.encode(registerDto.getPassword()),
                roleRepository.findByName(AppConstants.USER).orElseThrow(() -> new ResourceNotFoundException("Role", "name", AppConstants.USER)),
                true
        );
        userRepository.save(user);
        return new ApiResponce("Successfully register", true);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepository.findByUsername(username).orElseThrow(() -> new UsernameNotFoundException(username));
    }

}
