package uz.fido.appnewssite.payload;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserDto {
    @NotNull(message = "FullName bo'sh bolmasin")
    private String fullName;

    @NotNull(message = "Username bo'sh bolmasin")
    private String username;

    @NotNull(message = "Parol bo'sh bolmasin")
    private String password;
}
