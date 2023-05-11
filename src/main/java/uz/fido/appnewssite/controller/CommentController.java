package uz.fido.appnewssite.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import uz.fido.appnewssite.payload.ApiResponce;
import uz.fido.appnewssite.payload.CommentDto;
import uz.fido.appnewssite.service.CommentService;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/comment")
public class CommentController {
    @Autowired
    CommentService commentService;

    @PreAuthorize(value = "hasAuthority('ADD_COMMENT')")
    @PostMapping("/addComment")
    public HttpEntity<?> addComment(@Valid @RequestBody CommentDto commentDto) {
        ApiResponce apiResponce = commentService.addComment(commentDto);
        return ResponseEntity.status(apiResponce.isSuccess() ? 200 : 409).body(apiResponce);
    }

    @PreAuthorize(value = "hasAuthority('EDIT_COMMENT')")
    @PutMapping("/editComment/{id}")
    public HttpEntity<?> editComment(@PathVariable Long id, @Valid @RequestBody CommentDto commentDto) {
        ApiResponce apiResponce = commentService.editComment(id, commentDto);
        return ResponseEntity.status(apiResponce.isSuccess() ? 200 : 409).body(apiResponce);
    }

    @PreAuthorize(value = "hasAuthority('DELETE_COMMENT')")
    @DeleteMapping("/deleteComment/{id}")
    public HttpEntity<?> deleteComment(@PathVariable Long id) {
        ApiResponce apiResponce = commentService.deleteComment(id);
        return ResponseEntity.status(apiResponce.isSuccess() ? 200 : 409).body(apiResponce);
    }



}
