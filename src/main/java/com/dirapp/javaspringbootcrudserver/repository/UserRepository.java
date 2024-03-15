package com.dirapp.javaspringbootcrudserver.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.dirapp.javaspringbootcrudserver.entity.User;

@Repository
public interface UserRepository extends JpaRepository<User, String> {
    // berisi method untuk melakukan query ke database
    // secara default sudah ada method findAll(), save(), findById()
    // jika ingin menambahkan custom query bisa ditambahkan disini
    
}
