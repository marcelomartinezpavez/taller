package com.personal.taller.controller;

import com.personal.taller.dto.EmpresaDto;
import com.personal.taller.dto.UsersDto;
import com.personal.taller.repository.EmpresaRepository;
import com.personal.taller.repository.LoginRepository;
import com.personal.taller.request.EmpresaAndUser;
import com.personal.taller.request.Users;
import com.personal.taller.response.LoginResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
        Optional<UsersDto> resp = loginRepository.findByUsersAndPass(newUsers.getUsers(), newUsers.getPass());
        UsersDto usersDto = resp.get();
        Optional<EmpresaDto> empresa = empresaRepository.findById(usersDto.getId());
        LoginResponse loginResponse = new LoginResponse();
        if (empresa.isPresent()){
            loginResponse.setUsers(usersDto);
            loginResponse.setEmpresa(empresa.get());
        }
        return loginResponse;
    }

    @PostMapping(path = "/create",produces = "application/json")
    @CrossOrigin(origins = "*")
    public @ResponseBody
    ResponseEntity<LoginResponse> create(@RequestBody EmpresaAndUser empresaAndUser) {
        Optional<EmpresaDto> empresa = empresaRepository.findByRut(empresaAndUser.getRutEmpresa());
        Optional<UsersDto> user = loginRepository.findByUsersAndPass(empresaAndUser.getUsers(), empresaAndUser.getPass());

        EmpresaDto empresaDto = new EmpresaDto();
        UsersDto users = new UsersDto();

        if (!user.isPresent()){

            users.setUsers(empresaAndUser.getUsers());
            users.setPass(empresaAndUser.getPass());
            users.setHabilitado(1);
            users.setRol(empresaAndUser.getRol());

            if(empresa.isPresent()) {
                empresaDto = empresa.get();
                users.setEmpresa(empresaDto);
                loginRepository.save(users);
            }else{
                //empresaDto.setUsers(users);
                empresaDto.setDireccion(empresaAndUser.getDireccionEmpresa());
                empresaDto.setNombre(empresaAndUser.getNombreEmpresa());
                empresaDto.setRut(empresaAndUser.getRutEmpresa());
                empresaRepository.save(empresaDto);
                users.setEmpresa(empresaDto);
                loginRepository.save(users);

            }
        }else{
            return new ResponseEntity("Usuario que intenta crear ya esta en uso",HttpStatus.IM_USED);
        }

        LoginResponse loginResponse = new LoginResponse();

        loginResponse.setUsers(users);
        loginResponse.setEmpresa(empresaDto);

        return new ResponseEntity<LoginResponse>(loginResponse, HttpStatus.OK);
    }

}
