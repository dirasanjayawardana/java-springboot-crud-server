package com.dirapp.javaspringbootcrudserver.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.dirapp.javaspringbootcrudserver.payload.RegisterUserRequest;
import com.dirapp.javaspringbootcrudserver.payload.WebResponse;
import com.dirapp.javaspringbootcrudserver.service.UserService;
import org.springframework.web.bind.annotation.PostMapping;


@RestController // menandakan bahwa ini adalah controller
public class UserController {

    @Autowired // untuk inject object agar tidak null (instansiasi object)
    private UserService userService;

    // membuat controller register dengan return payload WebResponse dengan data string, menggunakan RequestBody dengan struktur data dari payload RegisterUserRequest
    // menggunakan method POST, dengan tipe data yang dapat diterima dan dikembalikan berupa JSON (atribute consume dan produce tidak wajib)
    @PostMapping(
        path = "/api/users", 
        consumes = MediaType.APPLICATION_JSON_VALUE, 
        produces = MediaType.APPLICATION_JSON_VALUE
    )
    public WebResponse<String> register(@RequestBody RegisterUserRequest request){
        // memanggil method register dari userService dengan parameter request
        userService.register(request);
        // mengembalikan WebResponse dengan data string yang dibentuk dengan builder karena telah menggunakan anotasi builder dipayload WebResponse 
        return WebResponse.<String>builder().data("OK").errors(null).build();
    }
    
}
