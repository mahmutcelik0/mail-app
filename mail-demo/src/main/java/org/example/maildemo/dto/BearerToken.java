package org.example.maildemo.dto;

import jakarta.validation.constraints.NotEmpty;
import lombok.*;
import org.example.maildemo.constants.CustomResponseMessage;

@Getter
@Setter
@NotEmpty
@AllArgsConstructor
@Builder
public class BearerToken{
    @NotEmpty(message = CustomResponseMessage.ACCESS_TOKEN_REQUIRED)
    private String accessToken;
    @NotEmpty(message = CustomResponseMessage.TOKEN_TYPE_REQUIRED)
    private String tokenType;
}
