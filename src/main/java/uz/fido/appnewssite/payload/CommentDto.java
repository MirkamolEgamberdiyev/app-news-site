package uz.fido.appnewssite.payload;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import uz.fido.appnewssite.entity.Post;

import javax.validation.constraints.NotNull;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CommentDto {
    @NotNull(message = "text bo'sh bolmasin")
    private String text;

    @NotNull(message = "post bo'sh bolmasin")
    private Long postId;
}
