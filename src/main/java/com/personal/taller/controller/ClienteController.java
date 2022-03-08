package com.personal.taller.controller;

import com.personal.taller.dto.ClienteDto;
import com.personal.taller.repository.ClienteRepository;
import com.personal.taller.response.ClienteResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("cliente")
public class ClienteController {

    @Autowired
    ClienteRepository clienteRepository;

    @GetMapping(path = "/all", produces = "application/json")
    @CrossOrigin(origins = "*")
    public @ResponseBody
    ResponseEntity getAllCliente() {

        System.out.println("getAll");
        ClienteResponse clienteResponse = new ClienteResponse();
        List<ClienteDto> resp = clienteRepository.findAll();
        clienteResponse.setClienteDtoList(resp);
        //entityManager.
        return new ResponseEntity<>(clienteResponse,HttpStatus.OK);
    }

    @GetMapping(value = "/{rut}", produces = "application/json")
    @CrossOrigin(origins = "*")
    public @ResponseBody List<ClienteDto> getCliente(@PathVariable String rut) {
        List<ClienteDto> clientes = clienteRepository.findByRut(rut);
        return clientes;
    }

    @PostMapping(path = "/insert",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @CrossOrigin(origins = "*")
    public ResponseEntity<ClienteDto> create(@RequestBody ClienteDto newCliente) {
        try {
            clienteRepository.save(newCliente);
        }catch (Exception e){
            throw e;
        }
        return new ResponseEntity<>(newCliente, HttpStatus.CREATED);
    }

    @PutMapping(path = "/update",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @CrossOrigin(origins = "*")
    public ResponseEntity<ClienteDto> update(@RequestBody ClienteDto newCliente) {
        try {
            clienteRepository.save(newCliente);
        }catch (Exception e){
            throw e;
        }
        return new ResponseEntity<>(newCliente, HttpStatus.CREATED);
    }

    @DeleteMapping(path = "/delete",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @CrossOrigin(origins = "*")
    public ResponseEntity<ClienteDto> delete(@RequestBody ClienteDto newCliente) {
        newCliente.setHabilitado(0);

        try {
            clienteRepository.save(newCliente);
        }catch (Exception e){
            throw e;
        }
        return new ResponseEntity<>(newCliente, HttpStatus.CREATED);

    }
}
