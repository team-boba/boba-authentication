package com.beaconfirebobo.authserver.domain.user;

import lombok.*;

import java.io.Serializable;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class SerializeUser implements Serializable {
    private String userName;
    private String email;
    private long personId;
}
