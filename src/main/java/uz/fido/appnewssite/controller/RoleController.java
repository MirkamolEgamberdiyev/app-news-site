package uz.fido.appnewssite.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import uz.fido.appnewssite.aop.CheckPermission;
import uz.fido.appnewssite.payload.ApiResponce;
import uz.fido.appnewssite.payload.RoleDto;
import uz.fido.appnewssite.service.RoleService;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/role")
public class RoleController {
    @Autowired
    RoleService roleService;

    @PreAuthorize(value = "hasAuthority('ADD_ROLE')")
    @PostMapping("/addRole")
    public HttpEntity<?> addRole(@Valid @RequestBody RoleDto roleDto) {
        ApiResponce apiResponce = roleService.addRole(roleDto);
        return ResponseEntity.status(apiResponce.isSuccess() ? 200 : 409).body(apiResponce);
    }

    // @PreAuthorize(value = "hasAuthority('EDIT_ROLE')")
    @CheckPermission(role = "EDIT_ROLE")
    @PutMapping("/editRole/{id}")
    public HttpEntity<?> editRole(@PathVariable Long id, @Valid @RequestBody RoleDto roleDto) {
        ApiResponce apiResponce = roleService.editRole(id, roleDto);
        return ResponseEntity.status(apiResponce.isSuccess() ? 200 : 409).body(apiResponce);
    }

    @PreAuthorize(value = "hasAuthority('DELETE_ROLE')")
    @DeleteMapping("/deleteRole/{id}")
    public HttpEntity<?> deleteRole(@PathVariable Long id) {
        ApiResponce apiResponce = roleService.deleteRole(id);
        return ResponseEntity.status(apiResponce.isSuccess() ? 200 : 409).body(apiResponce);
    }

    @PreAuthorize(value = "hasAnyAuthority('VIEW_ROLES')")
    @GetMapping("/getRoles")
    public HttpEntity<?> getRoles() {
        ApiResponce apiResponce = roleService.getRoles();
        return ResponseEntity.status(apiResponce.isSuccess() ? 200 : 409).body(apiResponce);
    }
}
