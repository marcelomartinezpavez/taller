package com.personal.taller.dto;

import org.bson.types.ObjectId;
//import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.persistence.*;
import java.util.UUID;

@Entity
@Table(name = "users")
public class LoginDto {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    @Column(name = "empresa")
    private ObjectId empresa;
    @Column(name = "users")
    private String users;
    @Column(name = "pass")
    private String pass;

    public LoginDto(){}

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public ObjectId getEmpresa() {
        return empresa;
    }

    public void setEmpresa(ObjectId empresa) {
        this.empresa = empresa;
    }

    public String getUsers() {
        return users;
    }

    public void setUsers(String users) {
        this.users = users;
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }
}
