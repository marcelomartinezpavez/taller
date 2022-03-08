package com.personal.taller.controller;

import com.personal.taller.dto.VehiculoDto;
import com.personal.taller.repository.VehiculoRepository;
import com.personal.taller.response.VehiculoResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("vehiculo")
public class VehiculoController {
    @Autowired
    VehiculoRepository vehiculoRepository;

    @GetMapping(path = "/all", produces = "application/json")
    @CrossOrigin(origins = "*")
    public @ResponseBody
    ResponseEntity getAllVehiculo() {
        System.out.println("getAll Vehiculo");
        VehiculoResponse vehiculoResponse = new VehiculoResponse();
        vehiculoResponse.setVehiculoDtoList(vehiculoRepository.findAll());
        return new ResponseEntity<>(vehiculoResponse, HttpStatus.OK);
    }

    @GetMapping(value = "/cliente/{rutCliente}", produces = "application/json")
    @CrossOrigin(origins = "*")
    public @ResponseBody
    ResponseEntity getVehiculoByCliente(@PathVariable String rutCliente) {
        VehiculoResponse vehiculoResponse = new VehiculoResponse();
        vehiculoResponse.setVehiculoDtoList(vehiculoRepository.findByRutDueno(rutCliente));
        return new ResponseEntity<>(vehiculoResponse, HttpStatus.OK);
    }



    @GetMapping(value = "/{patente}", produces = "application/json")
    @CrossOrigin(origins = "*")
    public @ResponseBody
    VehiculoDto getVehiculo(@PathVariable String patente) {
        return vehiculoRepository.findByPatente(patente);
    }

    @PostMapping(path = "/insert",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @CrossOrigin(origins = "*")
    public ResponseEntity<VehiculoDto> create(@RequestBody VehiculoDto newVehiculo) {
        try {
            vehiculoRepository.save(newVehiculo);
        }catch (Exception e){
            throw e;
        }
        return new ResponseEntity<>(newVehiculo, HttpStatus.CREATED);

        /*VehiculoDto vehiculo = vehiculoRepository.save(newVehiculo);
        if (vehiculo == null) {
            throw new RuntimeException();
        } else {
            return new ResponseEntity<>(vehiculo, HttpStatus.CREATED);
        }
        */
    }

    @PutMapping(path = "/update",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @CrossOrigin(origins = "*")
    public ResponseEntity<VehiculoDto> update(@RequestBody VehiculoDto newVehiculo) {
        try {
            vehiculoRepository.save(newVehiculo);
        }catch (Exception e){
            throw e;
        }
        return new ResponseEntity<>(newVehiculo, HttpStatus.CREATED);
        /*VehiculoDto vehiculo = vehiculoRepository.save(newVehiculo);
        if (vehiculo == null) {
            throw new RuntimeException();
        } else {
            return new ResponseEntity<>(vehiculo, HttpStatus.CREATED);
        }*/
    }

    @DeleteMapping(path = "/delete",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @CrossOrigin(origins = "*")
    public ResponseEntity<VehiculoDto> delete(@RequestBody VehiculoDto newVehiculo) {
        newVehiculo.setHabilitado(0);
        try {
            vehiculoRepository.save(newVehiculo);
        }catch (Exception e){
            throw e;
        }
        return new ResponseEntity<>(newVehiculo, HttpStatus.CREATED);
        /*VehiculoDto vehiculo = vehiculoRepository.save(newVehiculo);
        if (vehiculo == null) {
            throw new RuntimeException();
        } else {
            return new ResponseEntity<>(vehiculo, HttpStatus.CREATED);
        }*/
    }

}
