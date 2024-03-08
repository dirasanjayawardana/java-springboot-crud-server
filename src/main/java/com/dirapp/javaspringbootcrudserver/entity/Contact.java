/**
 * User entity (sama seperti model user)
 */

package com.dirapp.javaspringbootcrudserver.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "contacts")

public class Contact {

    @Id
    private String id;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    private String phone;

    private String email;

    @ManyToOne
    @JoinColumn(name = " username", referencedColumnName = "username")
    private User user;
}



// @Column(name = "first_name"):
// Memberitahu program bahwa properti "firstName" akan disimpan di kolom dengan nama "first_name" di dalam tabel basis data. Ini seperti memberi petunjuk tentang bagaimana nama kolom seharusnya.

// @ManyToOne:
// Mengindikasikan bahwa properti "user" adalah bagian dari hubungan di mana setiap objek "Contact" berkaitan dengan satu objek "User", tetapi satu objek "User" bisa berkaitan dengan banyak objek "Contact". Ini mirip dengan hubungan antara pemilik rumah dan mobil, di mana setiap rumah memiliki satu pemilik, tetapi satu pemilik bisa memiliki banyak rumah.

// @JoinColumn(name = "username", referencedColumnName = "username"):
// Menentukan bahwa kolom "username" di tabel "Contact" akan digunakan sebagai tempat untuk menyimpan informasi tentang koneksi dengan tabel "User". Artinya, nilai dalam kolom "username" di "Contact" akan mengacu pada nilai dalam kolom "username" di "User". Ini seperti menyambungkan dua bagian puzzle dengan kunci yang sesuai.

// properti "email" dan "phone", tidak adanya anotasi @Column karena default pemetaan properti ke kolom menggunakan nama yang sama. Jika perlu mengonfigurasi lebih lanjut, dapat menambahkan anotasi @Column dengan parameter sesuai ke properti tersebut.