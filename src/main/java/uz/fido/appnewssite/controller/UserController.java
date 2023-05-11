package uz.fido.appnewssite.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import uz.fido.appnewssite.payload.ApiResponce;
import uz.fido.appnewssite.payload.UserDto;
import uz.fido.appnewssite.service.UserService;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/user")
public class UserController {
    @Autowired
    UserService userService;

    @PreAuthorize(value = "hasAuthority('ADD_USER')")
    @PostMapping("/addUser")
    public HttpEntity<?> addUser(@Valid @RequestBody UserDto userDto) {
        ApiResponce apiResponce = userService.addUser(userDto);
        return ResponseEntity.status(apiResponce.isSuccess() ? 200 : 409).body(apiResponce);
    }

    @PreAuthorize(value = "hasAuthority('EDITE_USER')")
    @PutMapping("/editUser/{id}")
    public HttpEntity<?> editUser(@PathVariable Long id, @Valid @RequestBody UserDto userDto) {
        ApiResponce apiResponce = userService.editUser(id, userDto);
        return ResponseEntity.status(apiResponce.isSuccess() ? 200 : 409).body(apiResponce);
    }

    @PreAuthorize(value = "hasAuthority('DELETE_USER')")
    @DeleteMapping("/deleteUser/{id}")
    public HttpEntity<?> deleteUser(@PathVariable Long id) {
        ApiResponce apiResponce = userService.deleteUser(id);
        return ResponseEntity.status(apiResponce.isSuccess() ? 200 : 409).body(apiResponce);
    }

    @PreAuthorize(value = "hasAuthority('VIEW_USERS')")
    @GetMapping("/getUsers")
    public HttpEntity<?> getUsers() {
        ApiResponce apiResponce = userService.getAllUsers();
        return ResponseEntity.status(apiResponce.isSuccess() ? 200 : 409).body(apiResponce);
    }


}
