package uz.fido.appnewssite.payload;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PostDto {

    @NotNull(message = "title bo'sh bolmasin")
    private String title;

    @NotNull(message = "text bo'sh bolmasin")
    private String text;

    @NotNull(message = "url bo'sh bolmasin")
    private String url;
}
