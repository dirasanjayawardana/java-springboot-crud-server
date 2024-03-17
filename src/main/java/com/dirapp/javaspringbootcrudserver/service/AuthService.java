package com.dirapp.javaspringbootcrudserver.service;

import java.util.Set;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.dirapp.javaspringbootcrudserver.entity.User;
import com.dirapp.javaspringbootcrudserver.payload.LoginUserRequest;
import com.dirapp.javaspringbootcrudserver.payload.TokenResponse;
import com.dirapp.javaspringbootcrudserver.repository.UserRepository;
import com.dirapp.javaspringbootcrudserver.security.BCrypt;

import jakarta.transaction.Transactional;

// service menjalankan repository yang sudah dibuat
// idealnya dibuat interfacenya dulu baru di implement ke class

@Service
public class AuthService {

    @Autowired // untuk inject object agar tidak null (sama seperti instansiasi object, private UserRepository userRepository = new UserRepository())
    private UserRepository userRepository;

    @Autowired
    private ValidationService validationService;

    @Transactional // Jika semuanya berhasil, maka perubahan akan disimpan di database. Jika ada masalah di tengah jalan, maka semua perubahan akan dibatalkan
    public TokenResponse login(LoginUserRequest request) {
        validationService.validate(request);

        // cek apakah user terdaftar, jika tidak maka throw error
        User user = userRepository.findById(request.getUsername())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Username or password error"));

        // cek apakah password benar
        if (BCrypt.checkpw(request.getPassword(), user.getPassword())){
            // jika benar buat tokennya, bisa dalam bentuk jwt, contoh ini hanya menggunakan UUID
            user.setToken(UUID.randomUUID().toString());
            user.setTokenExpiredAt(next30Days());

            // save user token
            userRepository.save(user);

            // return tokennya
            return TokenResponse.builder().token(user.getToken()).expiredAt(user.getTokenExpiredAt()).build();
        } else {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Username or password error");
        } 
    }

    // function untuk menghitung 30 hari kedepan
    private Long next30Days() {
        return (System.currentTimeMillis() + (1000 * 60 * 60 * 24 * 30));
    }

}