package com.personal.taller.controller;

import com.personal.taller.dto.EmpresaDto;
import com.personal.taller.dto.ProveedorDto;
import com.personal.taller.repository.EmpresaRepository;
import com.personal.taller.repository.ProveedorRepository;
import com.personal.taller.request.ProveedorRequest;
import com.personal.taller.service.ProveedorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@Controller
@RequestMapping("proveedor")
public class ProveedorController {

    @Autowired
    ProveedorService proveedorService;

    @Autowired
    ProveedorRepository proveedorRepository;

    @Autowired
    EmpresaRepository empresaRepository;

    @GetMapping(path = "/all", produces = "application/json")
    @CrossOrigin(origins = "*")
    public @ResponseBody ResponseEntity getAllProveedor() {
        return proveedorService.getAllProveedor();
    }

    @GetMapping(path = "/empresa/{idempresa}", produces = "application/json")
    @CrossOrigin(origins = "*")
    public @ResponseBody
    ResponseEntity getAllProveedorPorEmpresa(@PathVariable long idempresa) {
        return proveedorService.getAllProveedorPorEmpresa(idempresa);
    }

    @GetMapping(value = "/{rut}", produces = "application/json")
    @CrossOrigin(origins = "*")
    public @ResponseBody
    ResponseEntity getProveedor(@PathVariable String rut) {
        return proveedorService.getProveedor(rut);
    }

    @PostMapping(path = "/insert",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @CrossOrigin(origins = "*")
    public ResponseEntity create(@RequestBody ProveedorRequest newProveedor) {
        return proveedorService.create(newProveedor);
    }

    @PutMapping(path = "/update",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @CrossOrigin(origins = "*")
    public ResponseEntity<ProveedorDto> update(@RequestBody ProveedorRequest newProveedor) {
        ProveedorDto proveedor = new ProveedorDto();
        EmpresaDto empresaDto = new EmpresaDto();

        Optional<EmpresaDto> respEmpresa = empresaRepository.findById(newProveedor.getIdEmpresa());
        if(respEmpresa.isPresent()) {
            proveedor.setId(newProveedor.getId());
            proveedor.setHabilitado(newProveedor.getHabilitado());
            proveedor.setApellido(newProveedor.getApellido());
            proveedor.setCiudad(newProveedor.getCiudad());
            proveedor.setComuna(newProveedor.getComuna());
            proveedor.setDireccion(newProveedor.getDireccion());
            proveedor.setEmail(newProveedor.getEmail());
            proveedor.setNombre(newProveedor.getNombre());
            proveedor.setRut(newProveedor.getRut());
            proveedor.setTelefono(newProveedor.getTelefono());
            proveedor.setEmpresa(respEmpresa.get());
        }else{
            return new ResponseEntity("Error Empresa no existe",HttpStatus.BAD_REQUEST);
        }

        proveedorRepository.save(proveedor);

        return new ResponseEntity(proveedor, HttpStatus.CREATED);
    }

    @DeleteMapping(path = "/delete",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @CrossOrigin(origins = "*")
    public ResponseEntity<ProveedorDto> delete(@RequestBody ProveedorDto newProveedor) {
        Optional<ProveedorDto> proveedor = proveedorRepository.findById(newProveedor.getId());
        if(proveedor.isPresent()){
            proveedor.get().setHabilitado(false);
            proveedorRepository.save(proveedor.get());
        }else{
                return new ResponseEntity("Error proveedor no existe.",HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity(proveedor, HttpStatus.OK);

    }
}
