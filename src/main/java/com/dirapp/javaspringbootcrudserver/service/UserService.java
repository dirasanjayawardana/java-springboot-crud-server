package com.dirapp.javaspringbootcrudserver.service;

import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.dirapp.javaspringbootcrudserver.entity.User;
import com.dirapp.javaspringbootcrudserver.payload.RegisterUserRequest;
import com.dirapp.javaspringbootcrudserver.repository.UserRepository;
import com.dirapp.javaspringbootcrudserver.security.BCrypt;

import jakarta.transaction.Transactional;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;
import jakarta.validation.Validator;

// service menjalankan repository yang sudah dibuat
// idealnya dibuat interfacenya dulu baru di implement ke class

@Service
public class UserService {

    @Autowired // untuk inject object agar tidak null
    private UserRepository userRepository;

    @Autowired
    private Validator validator; // membuat valiadator, contoh untuk validasi header dan params, agar terpusat di service, sebenarnya bisa melakukanvalidasi di controller

    // dibuat void karena register tidak mereturn data
    // @Transactional --> Jika semuanya berhasil, maka perubahan akan disimpan di database. Jika ada masalah di tengah jalan, maka semua perubahan akan dibatalkan
    @Transactional
    public void register(RegisterUserRequest request){
        Set<ConstraintViolation<RegisterUserRequest>> constraintViolations = validator.validate(request);

        // jika constraintViloation ada isinya, maka ada error
        if (constraintViolations.size() != 0 ){
            throw new ConstraintViolationException(constraintViolations);
        }

        // cek apakah user sudah terdaftar atau belum
        if(userRepository.existsById(request.getUsername())){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Username alrady register");
        }

        // membuat objek user dengan hash password 
        User user = new User();
        user.setUsername(request.getUsername());
        user.setPassword(BCrypt.hashpw(request.getPassword(), BCrypt.gensalt()));
        user.setName(request.getName());

        // simpan user baru ke database
        userRepository.save(user);
    }
    
}
