package com.dirapp.javaspringbootcrudserver.payload;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data // membuat setter getter
@NoArgsConstructor
@AllArgsConstructor
@Builder // untuk mempermudah saat membuat objek

public class TokenResponse {
    
    private String token;

    private Long expiredAt;

}
