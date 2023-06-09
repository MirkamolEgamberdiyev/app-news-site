package uz.fido.appnewssite.companent;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import uz.fido.appnewssite.entity.Role;
import uz.fido.appnewssite.entity.User;
import uz.fido.appnewssite.entity.enums.Permission;
import uz.fido.appnewssite.repository.RoleRepository;
import uz.fido.appnewssite.repository.UserRepository;
import uz.fido.appnewssite.utils.AppConstants;

import java.util.Arrays;

import static uz.fido.appnewssite.entity.enums.Permission.*;

@Component
public class DataLoader implements CommandLineRunner {

    @Autowired
    UserRepository userRepository;
    @Autowired
    RoleRepository roleRepository;
    @Value("${spring.datasource.initialization-mode}")
    private String initialMode;
    @Autowired
    PasswordEncoder passwordEncoder;

    @Override
    public void run(String... args) throws Exception {

        if (initialMode.equals("always")) {

            Permission[] permissions = Permission.values();
            Role admin = roleRepository.save(new Role(
                    AppConstants.ADMIN,
                    Arrays.asList(permissions),
                    "Sistema egasi"
            ));

            Role user = roleRepository.save(new Role(
                    AppConstants.USER,
                    Arrays.asList(ADD_COMMENT, EDIT_COMMENT, DELETE_MY_COMMENT),
                    "Oddiy foydalanuvchi"
            ));

            userRepository.save(new User(
                    "Admin",
                    "admin",
                    passwordEncoder.encode("admin123"),
                    admin,
                    true
            ));

            userRepository.save(new User(
                    "User",
                    "user",
                    passwordEncoder.encode("user123"),
                    user,
                    true
            ));

        }

    }
}
