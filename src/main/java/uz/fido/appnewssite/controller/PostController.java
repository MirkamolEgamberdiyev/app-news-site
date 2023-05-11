package uz.fido.appnewssite.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import uz.fido.appnewssite.payload.ApiResponce;
import uz.fido.appnewssite.payload.PostDto;
import uz.fido.appnewssite.service.PostService;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/post")
public class PostController {
    @Autowired
    PostService postService;

    @PreAuthorize(value = "hasAuthority('ADD_POST')")
    @PostMapping("/addPost")
    public HttpEntity<?> addPost(@Valid @RequestBody PostDto postDto) {
        ApiResponce apiResponce = postService.addPost(postDto);
        return ResponseEntity.status(apiResponce.isSuccess() ? 200 : 409).body(apiResponce);
    }

    @PreAuthorize(value = "hasAuthority('EDIT_POST')")
    @PutMapping("/editPost/{id}")
    public HttpEntity<?> editPost(@PathVariable Long id, @Valid @RequestBody PostDto postDto) {
        ApiResponce apiResponce = postService.editPost(id, postDto);
        return ResponseEntity.status(apiResponce.isSuccess() ? 200 : 409).body(apiResponce);
    }

    @PreAuthorize(value = "hasAuthority('DELETE_POST')")
    @DeleteMapping("/deletePost/{id}")
    public HttpEntity<?> deletePost(@PathVariable Long id) {
        ApiResponce apiResponce = postService.deletePost(id);
        return ResponseEntity.status(apiResponce.isSuccess() ? 200 : 409).body(apiResponce);
    }


}
