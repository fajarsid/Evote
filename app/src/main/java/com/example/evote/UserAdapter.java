package com.example.evote;

public class UserAdapter {
    public String email;
    public String password;
    public String nama;
    public String prodi;
    public String nim;

    public UserAdapter() {
    }

    public UserAdapter (String email,String nama, String password, String prodi, String nim) {
        this.email    = email;
        this.nama     = nama;
        this.password = password;
        this.prodi    = prodi;
        this.nim      = nim;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getProdi() {
        return prodi;
    }

    public void setProdi(String prodi) {
       this.prodi = prodi;
    }

    public String getNIM() {
        return nim;
    }

    public void setNIM(String nim) {
        this.nim = nim;
    }
}
