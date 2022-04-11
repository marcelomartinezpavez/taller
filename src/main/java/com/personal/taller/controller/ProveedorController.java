package com.personal.taller.controller;

import com.personal.taller.dto.EmpresaDto;
import com.personal.taller.dto.ProveedorDto;
import com.personal.taller.repository.EmpresaRepository;
import com.personal.taller.repository.ProveedorRepository;
import com.personal.taller.request.ProveedorRequest;
import com.personal.taller.response.ProveedorResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("proveedor")
public class ProveedorController {

    @Autowired
    ProveedorRepository proveedorRepository;

    @Autowired
    EmpresaRepository empresaRepository;

    @GetMapping(path = "/all", produces = "application/json")
    @CrossOrigin(origins = "*")
    public @ResponseBody ResponseEntity getAllProveedor() {
        System.out.println("getAll");

        List<ProveedorDto> proveedorDtoList = proveedorRepository.findAllHabilitado();

        List<ProveedorDto> proveedorDtoListResp = new ArrayList<ProveedorDto>();
        for (int i = 0; i<proveedorDtoList.size();i++){
            ProveedorDto proveedor = new ProveedorDto();
            EmpresaDto empresaDto = new EmpresaDto();

            ProveedorDto proveedorResp = proveedorDtoList.get(i);

            empresaDto.setId(proveedorResp.getEmpresa().getId());
            empresaDto.setNombre(proveedorResp.getEmpresa().getNombre());
            empresaDto.setRut(proveedorResp.getEmpresa().getRut());
            empresaDto.setDireccion(proveedorResp.getEmpresa().getDireccion());

            //cliente.setId(proveedorResp.getId());
            proveedor.setHabilitado(proveedorResp.getHabilitado());
            proveedor.setNombre(proveedorResp.getNombre());
            proveedor.setApellido(proveedorResp.getApellido());
            proveedor.setRut(proveedorResp.getRut());
            proveedor.setDireccion(proveedorResp.getDireccion());
            proveedor.setComuna(proveedorResp.getComuna());
            proveedor.setCiudad(proveedorResp.getCiudad());
            proveedor.setTelefono(proveedorResp.getTelefono());
            proveedor.setEmail(proveedorResp.getEmail());
            proveedor.setEmpresa(empresaDto);

            proveedorDtoListResp.add(proveedor);
        }
        return new ResponseEntity(proveedorDtoListResp,HttpStatus.OK);
    }

    @GetMapping(path = "/empresa/{idempresa}", produces = "application/json")
    @CrossOrigin(origins = "*")
    public @ResponseBody
    ResponseEntity getAllProveedorPorEmpresa(@PathVariable long idempresa) {

        /*****
         * OBTIENE TODOS LOS PROVEEDORES, FILTRADO POR EMPRESA
         *
         * *****/

        ProveedorResponse proveedorResponse = new ProveedorResponse();
        List<ProveedorDto> resp = proveedorRepository.findByEmpresaId(idempresa);

        List<ProveedorDto> proveedorDtoList = new ArrayList<ProveedorDto>();
        EmpresaDto empresaDto = new EmpresaDto();

        //Optional<EmpresaDto> respEmpresa = empresaRepository.findById(newCliente.getIdEmpresa());
        for (int i = 0; i<resp.size();i++){
            ProveedorDto proveedor = new ProveedorDto();

            ProveedorDto proveedorResp = resp.get(i);

            empresaDto.setId(proveedorResp.getEmpresa().getId());
            empresaDto.setNombre(proveedorResp.getEmpresa().getNombre());
            empresaDto.setRut(proveedorResp.getEmpresa().getRut());
            empresaDto.setDireccion(proveedorResp.getEmpresa().getDireccion());

            proveedor.setId(proveedorResp.getId());
            proveedor.setHabilitado(proveedorResp.getHabilitado());
            proveedor.setApellido(proveedorResp.getApellido());
            proveedor.setCiudad(proveedorResp.getCiudad());
            proveedor.setComuna(proveedorResp.getComuna());
            proveedor.setDireccion(proveedorResp.getDireccion());
            proveedor.setEmail(proveedorResp.getEmail());
            proveedor.setNombre(proveedorResp.getNombre());
            proveedor.setRut(proveedorResp.getRut());
            proveedor.setTelefono(proveedorResp.getTelefono());
            proveedor.setEmpresa(empresaDto);
            proveedorDtoList.add(proveedor);
        }
        return new ResponseEntity(proveedorDtoList,HttpStatus.OK);
    }

    @GetMapping(value = "/{rut}", produces = "application/json")
    @CrossOrigin(origins = "*")
    public @ResponseBody
    ResponseEntity getProveedor(@PathVariable String rut) {
        Optional<ProveedorDto> proveedores = proveedorRepository.findByRutAndHabilitado(rut);
        if(proveedores.isPresent()){
            ProveedorDto proveedor= new ProveedorDto();
            EmpresaDto empresaDto = new EmpresaDto();

            ProveedorDto clienteResp = proveedores.get();
            empresaDto.setId(clienteResp.getEmpresa().getId());
            empresaDto.setNombre(clienteResp.getEmpresa().getNombre());
            empresaDto.setRut(clienteResp.getEmpresa().getRut());
            empresaDto.setDireccion(clienteResp.getEmpresa().getDireccion());

            proveedor.setId(clienteResp.getId());
            proveedor.setHabilitado(clienteResp.getHabilitado());
            proveedor.setApellido(clienteResp.getApellido());
            proveedor.setCiudad(clienteResp.getCiudad());
            proveedor.setComuna(clienteResp.getComuna());
            proveedor.setDireccion(clienteResp.getDireccion());
            proveedor.setEmail(clienteResp.getEmail());
            proveedor.setNombre(clienteResp.getNombre());
            proveedor.setRut(clienteResp.getRut());
            proveedor.setTelefono(clienteResp.getTelefono());
            proveedor.setEmpresa(empresaDto);
            return new ResponseEntity(proveedor, HttpStatus.OK);

        }else{
            return new ResponseEntity("Proveedor no se encuentra",HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping(path = "/insert",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @CrossOrigin(origins = "*")
    public ResponseEntity create(@RequestBody ProveedorRequest newProveedor) {

        ProveedorDto proveedor = new ProveedorDto();
        Optional<EmpresaDto> respEmpresa = empresaRepository.findById(newProveedor.getIdEmpresa());

        if(respEmpresa.isPresent()) {
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
        try {
            proveedorRepository.save(proveedor);
        }catch (Exception e){
            return new ResponseEntity("Error interno al crear Proveedor", HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity(proveedor, HttpStatus.CREATED);
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
    public ResponseEntity<ProveedorDto> delete(@RequestBody ProveedorRequest newProveedor) {
        Optional<ProveedorDto> proveedor = proveedorRepository.findById(newProveedor.getId());
        if(proveedor.isPresent()){
            if(newProveedor.getIdEmpresa() == proveedor.get().getEmpresa().getId()){
                proveedor.get().setHabilitado(0);
                proveedorRepository.save(proveedor.get());
            }else{
                return new ResponseEntity("Error Empresa no corresponde al proveedor.",HttpStatus.BAD_REQUEST);
            }
        }

        return new ResponseEntity(proveedor, HttpStatus.OK);

    }
}
