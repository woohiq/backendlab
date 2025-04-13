package com.example.lab.backendlab.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "user_table")
public class User {

    private String uuid; 

    @Id
    private String id;     // 로그인 ID
    private String name;
    private String password;

    public User() {}

    public User(String uuid, String id, String name, String password) {
        this.uuid = uuid;
        this.id = id;
        this.name = name;
        this.password = password;
    }

    public String getUuid() {
        return uuid;
    }
    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public String getId() {
        return id;
    }
    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }
}
