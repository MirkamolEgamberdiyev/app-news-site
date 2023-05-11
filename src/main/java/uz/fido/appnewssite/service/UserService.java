package uz.fido.appnewssite.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import uz.fido.appnewssite.entity.User;
import uz.fido.appnewssite.exceptions.ResourceNotFoundException;
import uz.fido.appnewssite.payload.ApiResponce;
import uz.fido.appnewssite.payload.UserDto;
import uz.fido.appnewssite.repository.RoleRepository;
import uz.fido.appnewssite.repository.UserRepository;
import uz.fido.appnewssite.utils.AppConstants;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {
    @Autowired
    UserRepository userRepository;
    @Autowired
    PasswordEncoder passwordEncoder;
    @Autowired
    RoleRepository roleRepository;

    public ApiResponce addUser(UserDto userDto) {
        boolean existsByUsername = userRepository.existsByUsername(userDto.getUsername());
        if (existsByUsername)
            return new ApiResponce("Bunday usernameli user mavjud", false);

        User user = new User(
                userDto.getFullName(),
                userDto.getUsername(),
                passwordEncoder.encode(userDto.getPassword()),
                roleRepository.findByName(AppConstants.USER).orElseThrow(() -> new ResourceNotFoundException("Role", "name", AppConstants.USER)),
                true
        );
        userRepository.save(user);
        return new ApiResponce("User saqlandi", true);
    }

    public ApiResponce editUser(Long id, UserDto userDto) {
        Optional<User> optionalUser = userRepository.findById(id);
        if (!optionalUser.isPresent())
            return new ApiResponce("Bunday user mavjud emas", false);
        User user = optionalUser.get();
        user.setFullName(userDto.getFullName());
        user.setUsername(userDto.getUsername());
        user.setPassword(passwordEncoder.encode(userDto.getPassword()));
        user.setRole(roleRepository.findByName(AppConstants.USER).orElseThrow(() -> new ResourceNotFoundException("Role", "name", AppConstants.USER)));
        user.setEnabled(true);
        userRepository.save(user);
        return new ApiResponce("User edit qilindi", true);
    }


    public ApiResponce deleteUser(Long id) {
        Optional<User> optionalUser = userRepository.findById(id);
        if (!optionalUser.isPresent())
            return new ApiResponce("Bunday user mavjud emas", false);

        userRepository.deleteById(id);
        return new ApiResponce("User o'chirildi", true);

    }

    public ApiResponce getAllUsers() {
        List<User> all = userRepository.findAll();
        return new ApiResponce("userlar listi", true, all);
    }
}
