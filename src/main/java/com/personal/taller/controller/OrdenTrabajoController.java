package com.personal.taller.controller;

import com.personal.taller.dto.*;
import com.personal.taller.repository.*;
import com.personal.taller.request.OrdenTrabajoRequest;
import com.personal.taller.response.OrdenTrabajoResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Controller
@RequestMapping("ordenTrabajo")
public class OrdenTrabajoController {
    @Autowired
    OrdenTrabajoRepository ordenTrabajoRepository;

    @Autowired
    EmpresaRepository empresaRepository;

    @Autowired
    VehiculoRepository vehiculoRepository;

    @Autowired
    ClienteRepository clienteRepository;

    @Autowired
    RepuestoRepository repuestoRepository;


    @Autowired
    DetalleRepository detalleRepository;

    @GetMapping(path = "/all", produces = "application/json")
    @CrossOrigin(origins = "*")
    public @ResponseBody
    ResponseEntity getAllOrdenTrabajo() {
        try {
            System.out.println("getAll Orden Trabajo");
            List<OrdenTrabajoDto> ot = ordenTrabajoRepository.findAll();
            return new ResponseEntity(ot, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity("Error Interno al buscar todos las ordenes de trabajo", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping(value = "/{numeroOrden}", produces = "application/json")
    @CrossOrigin(origins = "*")
    public @ResponseBody
    ResponseEntity getOrdenTrabajo(@PathVariable String numeroOrden) {
        try {
            OrdenTrabajoDto ordenTrabajo = ordenTrabajoRepository.findByNumeroOrden(numeroOrden);
            return new ResponseEntity(ordenTrabajo, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity("Error Interno al buscar por numero de orden", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping(path = "/insert",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @CrossOrigin(origins = "*")
    public ResponseEntity<OrdenTrabajoDto> create(@RequestBody OrdenTrabajoRequest newOrdenTrabajo) {
        System.out.println("INSERT OT");
        OrdenTrabajoDto otResponse = new OrdenTrabajoDto();
        try {
            EmpresaDto empresaDto = empresaRepository.getById(newOrdenTrabajo.getIdEmpresa());
            VehiculoDto vehiculoDto = vehiculoRepository.findByPatenteAndEmpresa(newOrdenTrabajo.getPatenteVehiculo(), newOrdenTrabajo.getIdEmpresa());
            Optional<ClienteDto> clienteDto = clienteRepository.findByRutAndHabilitadoAndEmpresa(newOrdenTrabajo.getRutCliente(), newOrdenTrabajo.getIdEmpresa());

            EmpresaDto empresa = new EmpresaDto();
            empresa.setNombre(empresaDto.getNombre());
            empresa.setDireccion(empresaDto.getDireccion());
            empresa.setRut(empresaDto.getRut());
            empresa.setId(empresaDto.getId());

            DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
            String fechaActual = dtf.format(LocalDateTime.now());

            otResponse.setHabilitado(1);
            otResponse.setFechaIngreso(fechaActual);
            otResponse.setRutCliente(newOrdenTrabajo.getRutCliente());
            ClienteDto cl = new ClienteDto();
            if(clienteDto.isPresent()){
                cl.setId(clienteDto.get().getId());
                cl.setHabilitado(clienteDto.get().getHabilitado());
                cl.setNombre(clienteDto.get().getNombre());
                cl.setApellido(clienteDto.get().getApellido());
                cl.setRut(clienteDto.get().getRut());
                cl.setDireccion(clienteDto.get().getDireccion());
                cl.setComuna(clienteDto.get().getComuna());
                cl.setCiudad(clienteDto.get().getCiudad());
                cl.setTelefono(clienteDto.get().getTelefono());
                cl.setEmail(clienteDto.get().getEmail());
                cl.setEmpresa(empresa);
                otResponse.setCliente(clienteDto.get());
            }else{
                return new ResponseEntity("Cliente no existe", HttpStatus.BAD_REQUEST);
            }

            otResponse.setPatenteVehiculo(newOrdenTrabajo.getPatenteVehiculo());
            otResponse.setCodigo(newOrdenTrabajo.getCodigo());
            otResponse.setValorOt(newOrdenTrabajo.getValorOt());

            otResponse.setNumeroOrden(newOrdenTrabajo.getNumeroOrden());

            VehiculoDto vehiculo = new VehiculoDto();
            vehiculo.setId(vehiculoDto.getId());
            vehiculo.setPatente(vehiculoDto.getPatente());
            vehiculo.setHabilitado(vehiculoDto.getHabilitado());
            vehiculo.setMarca(vehiculoDto.getMarca());
            vehiculo.setModelo(vehiculoDto.getModelo());
            vehiculo.setAnio(vehiculoDto.getAnio());
            vehiculo.setNumeroChasis(vehiculoDto.getNumeroChasis());
            vehiculo.setNumeroMotor(vehiculoDto.getNumeroMotor());
            vehiculo.setColor(vehiculoDto.getColor());
            vehiculo.setKilometraje(vehiculoDto.getKilometraje());
            vehiculo.setRutDueno(vehiculoDto.getRutDueno());

            Set<ClienteDto> clientesSet = new HashSet<>();
            clientesSet.add(cl);
            vehiculo.setCliente(clientesSet);

            otResponse.setVehiculo(vehiculo);
            otResponse.setVehiculo(vehiculoDto);

            otResponse.setEmpresa(empresa);

            Set<DetalleDto> detalleSet = new HashSet();
            newOrdenTrabajo.getDetalleDtoList().forEach(detalle -> {
                DetalleDto detalleDto = new DetalleDto();
                detalleDto.setDescripcion(detalle.getDescripcion());
                detalleDto.setRecargo(detalle.getRecargo());

                RepuestoDto repuestoDto = new RepuestoDto();
                if (detalle.getRepuesto_id() != 0) {
                    Optional<RepuestoDto> repuestoDtoOptional = repuestoRepository.findById(detalle.getRepuesto_id());
                    if(repuestoDtoOptional.isPresent()){
                        repuestoDto.setId(repuestoDtoOptional.get().getId());
                        repuestoDto.setCodigo(repuestoDtoOptional.get().getCodigo());
                        repuestoDto.setHabilitado(repuestoDtoOptional.get().getHabilitado());
                        repuestoDto.setNombre(repuestoDtoOptional.get().getNombre());
                        repuestoDto.setMarca(repuestoDtoOptional.get().getMarca());
                        repuestoDto.setModelo(repuestoDtoOptional.get().getModelo());
                        repuestoDto.setAnio(repuestoDtoOptional.get().getAnio());
                        repuestoDto.setRutProveedor(repuestoDtoOptional.get().getRutProveedor());
                        repuestoDto.setValor(repuestoDtoOptional.get().getValor());
                        repuestoDto.setEmpresa(empresa);

                        //Falta agregar proveedor a repuestoDto
                    }
                    detalleDto.setRepuesto(repuestoDto);

                }

                detalleRepository.save(detalleDto);
                detalleSet.add(detalleDto);

            });
            otResponse.setDetalle(detalleSet);
            ordenTrabajoRepository.save(otResponse);

        }catch (Exception e){
            e.printStackTrace();
            new ResponseEntity("Error al crear orden de trabajo", HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<>(otResponse, HttpStatus.CREATED);

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
