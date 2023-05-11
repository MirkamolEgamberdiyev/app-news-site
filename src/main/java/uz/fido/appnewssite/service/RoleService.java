package uz.fido.appnewssite.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uz.fido.appnewssite.entity.Role;
import uz.fido.appnewssite.payload.ApiResponce;
import uz.fido.appnewssite.payload.RoleDto;
import uz.fido.appnewssite.repository.RoleRepository;

import java.util.List;
import java.util.Optional;

@Service
public class RoleService {
    @Autowired
    RoleRepository roleRepository;

    public ApiResponce addRole(RoleDto roleDto) {
        boolean existsByName = roleRepository.existsByName(roleDto.getName());
        if (existsByName)
            return new ApiResponce("Bunday role mavjud", false);

        Role role = new Role(
                roleDto.getName(),
                roleDto.getPermissionList(),
                roleDto.getDescription()
        );
        roleRepository.save(role);

        return new ApiResponce("Role saqlandi", true);
    }

    public ApiResponce editRole(Long id, RoleDto roleDto) {
        Optional<Role> optionalRole = roleRepository.findById(id);
        if (!optionalRole.isPresent())
            return new ApiResponce("Bunday id li Role mavjud emas", false);
        Role role = optionalRole.get();

        role.setName(roleDto.getName());
        role.setPermissionList(roleDto.getPermissionList());
        role.setDescription(roleDto.getDescription());
        Role save = roleRepository.save(role);

        return new ApiResponce("Role edit qilindi", true, save);
    }

    public ApiResponce deleteRole(Long id) {
        roleRepository.deleteById(id);
        return new ApiResponce("Role o'chirildi", true);
    }

    public ApiResponce getRoles() {
        List<Role> all = roleRepository.findAll();
        return new ApiResponce("Roles lar listi", true, all);
    }
}
