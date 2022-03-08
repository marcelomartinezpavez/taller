package com.personal.taller.controller;

import com.personal.taller.dto.EmpresaDto;
import com.personal.taller.dto.LoginDto;
import com.personal.taller.repository.EmpresaRepository;
import com.personal.taller.repository.LoginRepository;
import com.personal.taller.request.Users;
import com.personal.taller.response.LoginResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@Controller
@RequestMapping("login")
public class LoginController {

    @Autowired
    LoginRepository loginRepository;

    @Autowired
    EmpresaRepository empresaRepository;


    @PostMapping(path = "/",produces = "application/json")
    @CrossOrigin(origins = "*")
    public @ResponseBody
    LoginResponse login(@RequestBody Users newUsers) {
        Optional<LoginDto> resp = loginRepository.findByUsersAndPass(newUsers.getUsers(), newUsers.getPass());
        LoginDto loginDto = resp.get();
        Optional<EmpresaDto> empresa = empresaRepository.findById(loginDto.getId());
        LoginResponse loginResponse = new LoginResponse();
        if (empresa.isPresent()){

            loginResponse.setUsers(loginDto.getUsers());
            loginResponse.setPass(loginDto.getPass());
            loginResponse.setDireccion(empresa.get().getDireccion());
            loginResponse.setNombre(empresa.get().getNombre());
            loginResponse.setRut(empresa.get().getRut());
        }
        return loginResponse;
    }

}
