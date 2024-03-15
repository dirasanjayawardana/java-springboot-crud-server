package com.dirapp.javaspringbootcrudserver.payload;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data // membuat setter getter
@NoArgsConstructor
@AllArgsConstructor
@Builder // mempermudah saat membuat objek

public class WebResponse<T> {

    private T data;

    private String errors;
    
}