package org.zisik.edu.user.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UpdateProfileRequest {

    private String nickname;
    private String bio;
    private String profileImageUrl;
}
