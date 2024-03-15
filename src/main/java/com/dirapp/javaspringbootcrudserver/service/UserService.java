package com.dirapp.javaspringbootcrudserver.service;

import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dirapp.javaspringbootcrudserver.payload.RegisterUserRequest;
import com.dirapp.javaspringbootcrudserver.repository.UserRepository;

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
    private void register(RegisterUserRequest request){
        Set<ConstraintViolation<RegisterUserRequest>> constraintViolations = validator.validate(request);

        // jika constraintViloation ada isinya, maka ada error
        if (constraintViolations.size() != 0 ){
            throw new ConstraintViolationException(constraintViolations);
        }
    }
    
}
