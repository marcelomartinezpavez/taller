package com.personal.taller.controller;

import com.personal.taller.dto.RepuestoDto;
import com.personal.taller.repository.RepuestoRepository;
import com.personal.taller.response.RepuestoResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("repuesto")
public class RepuestoController {

    @Autowired
    RepuestoRepository repuestoRepository;

    @GetMapping(path = "/all", produces = "application/json")
    @CrossOrigin(origins = "*")
    public @ResponseBody
    ResponseEntity getAllRepuesto() {
        System.out.println("getAll Repuesto");
        RepuestoResponse repuestoResponse = new RepuestoResponse();
        repuestoResponse.setRepuestoDtoList(repuestoRepository.findAll());
        return new ResponseEntity<>(repuestoResponse, HttpStatus.OK);
    }

    @GetMapping(value = "/{codigo}", produces = "application/json")
    @CrossOrigin(origins = "*")
    public @ResponseBody
    RepuestoDto getRepuesto(@PathVariable String codigo) {
        return repuestoRepository.findByCodigo(codigo);
    }

    @PostMapping(path = "/insert",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @CrossOrigin(origins = "*")
    public ResponseEntity<RepuestoDto> create(@RequestBody RepuestoDto newRepuesto) {
        RepuestoDto repuestoResponse;
        try {
            repuestoResponse = repuestoRepository.save(newRepuesto);
        }catch (Exception e){
            throw e;
        }
        return new ResponseEntity<>(repuestoResponse, HttpStatus.CREATED);
        /*RepuestoDto repuesto = repuestoRepository.save(newRepuesto);
        if (repuesto == null) {
            throw new RuntimeException();
        } else {
            return new ResponseEntity<>(repuesto, HttpStatus.CREATED);
        }*/
    }

    @PutMapping(path = "/update",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @CrossOrigin(origins = "*")
    public ResponseEntity<RepuestoDto> update(@RequestBody RepuestoDto newRepuesto) {
        RepuestoDto repuestoResponse;
        try {
            repuestoResponse = repuestoRepository.save(newRepuesto);
        }catch (Exception e){
            throw e;
        }
        return new ResponseEntity<>(repuestoResponse, HttpStatus.CREATED);
        /*RepuestoDto repuesto = repuestoRepository.save(newRepuesto);
        if (repuesto == null) {
            throw new RuntimeException();
        } else {
            return new ResponseEntity<>(repuesto, HttpStatus.CREATED);
        }*/
    }

    @DeleteMapping(path = "/delete",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @CrossOrigin(origins = "*")
    public ResponseEntity<RepuestoDto> delete(@RequestBody RepuestoDto newRepuesto) {
        newRepuesto.setHabilitado(0);
        /*RepuestoDto repuesto = repuestoRepository.save(newRepuesto);
        if (repuesto == null) {
            throw new RuntimeException();
        } else {
            return new ResponseEntity<>(repuesto, HttpStatus.CREATED);
        }*/
        RepuestoDto repuestoResponse;
        try {
            repuestoResponse = repuestoRepository.save(newRepuesto);
        }catch (Exception e){
            throw e;
        }
        return new ResponseEntity<>(repuestoResponse, HttpStatus.CREATED);

    }

}
