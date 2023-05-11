package uz.fido.appnewssite.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uz.fido.appnewssite.entity.Post;
import uz.fido.appnewssite.payload.ApiResponce;
import uz.fido.appnewssite.payload.PostDto;
import uz.fido.appnewssite.repository.PostRepository;

import java.util.Optional;

@Service
public class PostService {
    @Autowired
    PostRepository postRepository;

    public ApiResponce addPost(PostDto postDto) {
        Post post = new Post();
        post.setTitle(postDto.getTitle());
        post.setText(postDto.getText());
        post.setUrl(postDto.getUrl());
        Post save = postRepository.save(post);
        return new ApiResponce("Post saqlandi", true, save);
    }

    public ApiResponce editPost(Long id, PostDto postDto) {
        Optional<Post> optionalPost = postRepository.findById(id);
        if (!optionalPost.isPresent())
            return new ApiResponce("Bunday id li post mabjud emas", false);

        Post post = optionalPost.get();
        post.setTitle(postDto.getTitle());
        post.setText(postDto.getText());
        post.setUrl(postDto.getUrl());
        Post save = postRepository.save(post);
        return new ApiResponce("Post update qilindi", true, save);

    }

    public ApiResponce deletePost(Long id) {
        postRepository.deleteById(id);
        return new ApiResponce("Post o'chirildi", true);
    }
}
