package com.personal.taller.response;

import com.personal.taller.dto.EmpresaDto;
import com.personal.taller.dto.UsersDto;

public class LoginResponse {

    private UsersDto users;
    private EmpresaDto empresa;

    public EmpresaDto getEmpresa() {
        return empresa;
    }

    public void setEmpresa(EmpresaDto empresa) {
        this.empresa = empresa;
    }

    public UsersDto getUsers() {
        return users;
    }

    public void setUsers(UsersDto users) {
        this.users = users;
    }
}
