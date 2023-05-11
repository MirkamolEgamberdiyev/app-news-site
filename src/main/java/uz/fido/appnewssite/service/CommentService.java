package uz.fido.appnewssite.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uz.fido.appnewssite.entity.Comment;
import uz.fido.appnewssite.entity.Post;
import uz.fido.appnewssite.payload.ApiResponce;
import uz.fido.appnewssite.payload.CommentDto;
import uz.fido.appnewssite.repository.CommentRepository;
import uz.fido.appnewssite.repository.PostRepository;

import java.util.Optional;

@Service
public class CommentService {

    @Autowired
    CommentRepository commentRepository;
    @Autowired
    PostRepository postRepository;

    public ApiResponce addComment(CommentDto commentDto) {
        Comment comment = new Comment();
        comment.setText(commentDto.getText());
        Optional<Post> optionalPost = postRepository.findById(commentDto.getPostId());
        if (!optionalPost.isPresent())
            return new ApiResponce("Bunday id li Post mavjud emas", false);

        Post post = optionalPost.get();
        comment.setPost(post);
        Comment save = commentRepository.save(comment);
        return new ApiResponce("comment saqlandi", true, save);
    }

    public ApiResponce editComment(Long id, CommentDto commentDto) {
        Optional<Comment> optionalComment = commentRepository.findById(id);
        if (!optionalComment.isPresent())
            return new ApiResponce("Bundya id li comment mavjud emas", false);

        Comment comment = optionalComment.get();
        comment.setText(commentDto.getText());
        Optional<Post> optionalPost = postRepository.findById(commentDto.getPostId());
        if (!optionalPost.isPresent())
            return new ApiResponce("Bunday id li Post mavjud emas", false);

        Post post = optionalPost.get();
        comment.setPost(post);
        Comment save = commentRepository.save(comment);
        return new ApiResponce("comment edit qilindi", true, save);

    }

    public ApiResponce deleteComment(Long id) {
        commentRepository.deleteById(id);
        return new ApiResponce("comment delete qilindi", true);
    }
}
