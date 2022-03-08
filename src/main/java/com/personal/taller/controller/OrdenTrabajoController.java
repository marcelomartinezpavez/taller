package com.personal.taller.controller;

import ch.qos.logback.core.net.SyslogOutputStream;
import com.personal.taller.dto.DetalleDto;
import com.personal.taller.dto.OrdenTrabajoDto;
import com.personal.taller.repository.DetalleRepository;
import com.personal.taller.repository.OrdenTrabajoRepository;
import com.personal.taller.response.OrdenTrabajoResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("ordenTrabajo")
public class OrdenTrabajoController {
    @Autowired
    OrdenTrabajoRepository ordenTrabajoRepository;

    @Autowired
    DetalleRepository detalleRepository;

    @GetMapping(path = "/all", produces = "application/json")
    @CrossOrigin(origins = "*")
    public @ResponseBody
    ResponseEntity getAllOrdenTrabajo() {
        System.out.println("getAll Orden Trabajo");
        OrdenTrabajoResponse ordenTrabajoResponse = new OrdenTrabajoResponse();
        //List ot = ordenTrabajoRepository.findAll();
        ordenTrabajoResponse.setOrdenTrabajoDtoList(ordenTrabajoRepository.findAll());
        return new ResponseEntity<>(ordenTrabajoResponse, HttpStatus.OK);
    }

    @GetMapping(value = "/cliente/{numeroOrden}", produces = "application/json")
    @CrossOrigin(origins = "*")
    public @ResponseBody
    OrdenTrabajoDto getOrdenTrabajo(@PathVariable String numeroOrden) {
        return ordenTrabajoRepository.findByNumeroOrden(numeroOrden);
    }

    @PostMapping(path = "/insert",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @CrossOrigin(origins = "*")
    public ResponseEntity<OrdenTrabajoDto> create(@RequestBody OrdenTrabajoDto newOrdenTrabajo) {
        System.out.println("INSERT OT");
        OrdenTrabajoDto otResponse;
        try {

            //DetalleDto detalleDto = new DetalleDto();
            //detalleDto.setDescripcion(newOrdenTrabajo.getDetalle().get(0).getDescripcion());
            //detalleDto.setRecargo(newOrdenTrabajo.getDetalle().get(0).getRecargo());
            //DetalleDto detalleResp = detalleRepository.save(detalleDto);
            //List<DetalleDto> detalleList = new ArrayList<>();
            //detalleList.add(detalleResp);

            //newOrdenTrabajo.setDetalle(detalleList);

            otResponse = ordenTrabajoRepository.save(newOrdenTrabajo);
        }catch (Exception e){
            e.printStackTrace();
            throw e;
        }
        return new ResponseEntity<>(otResponse, HttpStatus.CREATED);

        /*OrdenTrabajoDto ordenTrabajo = ordenTrabajoRepository.save(newOrdenTrabajo);
        if (ordenTrabajo == null) {
            throw new RuntimeException();
        } else {
            return new ResponseEntity<>(ordenTrabajo, HttpStatus.CREATED);
        }*/
    }

    @PutMapping(path = "/update",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @CrossOrigin(origins = "*")
    public ResponseEntity<OrdenTrabajoDto> update(@RequestBody OrdenTrabajoDto newOrdenTrabajo) {
        /*OrdenTrabajoDto ordenTrabajo = ordenTrabajoRepository.save(newOrdenTrabajo);
        if (ordenTrabajo == null) {
            throw new RuntimeException();
        } else {
            return new ResponseEntity<>(ordenTrabajo, HttpStatus.CREATED);
        }*/
        OrdenTrabajoDto otResponse;
        try {
            otResponse = ordenTrabajoRepository.save(newOrdenTrabajo);
        }catch (Exception e){
            throw e;
        }
        return new ResponseEntity<>(otResponse, HttpStatus.CREATED);

    }

    @DeleteMapping(path = "/delete",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @CrossOrigin(origins = "*")
    public ResponseEntity<OrdenTrabajoDto> delete(@RequestBody OrdenTrabajoDto newOrdenTrabajo) {
        newOrdenTrabajo.setHabilitado(0);
        /*OrdenTrabajoDto ordenTrabajo = ordenTrabajoRepository.save(newOrdenTrabajo);
        if (ordenTrabajo == null) {
            throw new RuntimeException();
        } else {
            return new ResponseEntity<>(ordenTrabajo, HttpStatus.CREATED);
        }*/
        OrdenTrabajoDto otResponse;
        try {
            otResponse = ordenTrabajoRepository.save(newOrdenTrabajo);
        }catch (Exception e){
            throw e;
        }
        return new ResponseEntity<>(otResponse, HttpStatus.CREATED);

    }

}
