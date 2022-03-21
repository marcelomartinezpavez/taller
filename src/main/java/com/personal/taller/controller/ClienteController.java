package com.personal.taller.controller;

import com.personal.taller.dto.ClienteDto;
import com.personal.taller.dto.EmpresaDto;
import com.personal.taller.repository.ClienteRepository;
import com.personal.taller.repository.EmpresaRepository;
import com.personal.taller.request.ClienteRequest;
import com.personal.taller.response.ClienteResponse;
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
@RequestMapping("cliente")
public class ClienteController {

    @Autowired
    ClienteRepository clienteRepository;

    @Autowired
    EmpresaRepository empresaRepository;

    @GetMapping(path = "/all", produces = "application/json")
    @CrossOrigin(origins = "*")
    public @ResponseBody
    ResponseEntity getAllCliente() {
        /*****
         * OBTIENE TODOS LOS CLIENTES, SIN FILTRAR POR EMPRESA
         *
         * *****/
        System.out.println("getAll");
        ClienteResponse clienteResponse = new ClienteResponse();
        List<ClienteDto> resp = clienteRepository.findAllHabilitado();
        List<ClienteDto> clienteDtoList = new ArrayList<ClienteDto>();
        for (int i = 0; i<resp.size();i++){
            ClienteDto cliente = new ClienteDto();
            EmpresaDto empresaDto = new EmpresaDto();

            ClienteDto clienteResp = resp.get(i);

            empresaDto.setId(clienteResp.getEmpresa().getId());
            empresaDto.setNombre(clienteResp.getEmpresa().getNombre());
            empresaDto.setRut(clienteResp.getEmpresa().getRut());
            empresaDto.setDireccion(clienteResp.getEmpresa().getDireccion());

            cliente.setId(clienteResp.getId());
            cliente.setHabilitado(clienteResp.getHabilitado());
            cliente.setApellido(clienteResp.getApellido());
            cliente.setCiudad(clienteResp.getCiudad());
            cliente.setComuna(clienteResp.getComuna());
            cliente.setDireccion(clienteResp.getDireccion());
            cliente.setEmail(clienteResp.getEmail());
            cliente.setNombre(clienteResp.getNombre());
            cliente.setRut(clienteResp.getRut());
            cliente.setTelefono(clienteResp.getTelefono());
            cliente.setEmpresa(empresaDto);
            clienteDtoList.add(cliente);
        }
        return new ResponseEntity(clienteDtoList,HttpStatus.OK);
    }

    @GetMapping(path = "/empresa/{idempresa}", produces = "application/json")
    @CrossOrigin(origins = "*")
    public @ResponseBody
    ResponseEntity getAllClientePorEmpresa(@PathVariable long idempresa) {

        /*****
         * OBTIENE TODOS LOS CLIENTES, FILTRADO POR EMPRESA
         *
         * *****/

        ClienteResponse clienteResponse = new ClienteResponse();
        List<ClienteDto> resp = clienteRepository.findByEmpresaId(idempresa);

        List<ClienteDto> clienteDtoList = new ArrayList<ClienteDto>();
        EmpresaDto empresaDto = new EmpresaDto();

        //Optional<EmpresaDto> respEmpresa = empresaRepository.findById(newCliente.getIdEmpresa());
        for (int i = 0; i<resp.size();i++){
            ClienteDto cliente = new ClienteDto();

            ClienteDto clienteResp = resp.get(i);

            empresaDto.setId(clienteResp.getEmpresa().getId());
            empresaDto.setNombre(clienteResp.getEmpresa().getNombre());
            empresaDto.setRut(clienteResp.getEmpresa().getRut());
            empresaDto.setDireccion(clienteResp.getEmpresa().getDireccion());

            cliente.setId(clienteResp.getId());
            cliente.setHabilitado(clienteResp.getHabilitado());
            cliente.setApellido(clienteResp.getApellido());
            cliente.setCiudad(clienteResp.getCiudad());
            cliente.setComuna(clienteResp.getComuna());
            cliente.setDireccion(clienteResp.getDireccion());
            cliente.setEmail(clienteResp.getEmail());
            cliente.setNombre(clienteResp.getNombre());
            cliente.setRut(clienteResp.getRut());
            cliente.setTelefono(clienteResp.getTelefono());
            cliente.setEmpresa(empresaDto);
            clienteDtoList.add(cliente);
        }
        return new ResponseEntity(clienteDtoList,HttpStatus.OK);
    }

    @GetMapping(value = "/{rut}", produces = "application/json")
    @CrossOrigin(origins = "*")
    public @ResponseBody ResponseEntity getCliente(@PathVariable String rut) {
        Optional<ClienteDto> clientes = clienteRepository.findByRutAndHabilitado(rut);
        if(clientes.isPresent()){
            ClienteDto cliente= new ClienteDto();
            EmpresaDto empresaDto = new EmpresaDto();

            ClienteDto clienteResp = clientes.get();
            empresaDto.setId(clienteResp.getEmpresa().getId());
            empresaDto.setNombre(clienteResp.getEmpresa().getNombre());
            empresaDto.setRut(clienteResp.getEmpresa().getRut());
            empresaDto.setDireccion(clienteResp.getEmpresa().getDireccion());

            cliente.setId(clienteResp.getId());
            cliente.setHabilitado(clienteResp.getHabilitado());
            cliente.setApellido(clienteResp.getApellido());
            cliente.setCiudad(clienteResp.getCiudad());
            cliente.setComuna(clienteResp.getComuna());
            cliente.setDireccion(clienteResp.getDireccion());
            cliente.setEmail(clienteResp.getEmail());
            cliente.setNombre(clienteResp.getNombre());
            cliente.setRut(clienteResp.getRut());
            cliente.setTelefono(clienteResp.getTelefono());
            cliente.setEmpresa(empresaDto);
            return new ResponseEntity(cliente,HttpStatus.OK);

        }else{
            return new ResponseEntity("Cliente no se encuentra",HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping(path = "/insert",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @CrossOrigin(origins = "*")
    public ResponseEntity<ClienteDto> create(@RequestBody ClienteRequest newCliente) {

        ClienteDto cliente = new ClienteDto();
        Optional<EmpresaDto> respEmpresa = empresaRepository.findById(newCliente.getIdEmpresa());

        if(respEmpresa.isPresent()) {
            cliente.setHabilitado(newCliente.getHabilitado());
            cliente.setApellido(newCliente.getApellido());
            cliente.setCiudad(newCliente.getCiudad());
            cliente.setComuna(newCliente.getComuna());
            cliente.setDireccion(newCliente.getDireccion());
            cliente.setEmail(newCliente.getEmail());
            cliente.setNombre(newCliente.getNombre());
            cliente.setRut(newCliente.getRut());
            cliente.setTelefono(newCliente.getTelefono());
            cliente.setEmpresa(respEmpresa.get());
        }else{
            return new ResponseEntity("Error Empresa no existe",HttpStatus.BAD_REQUEST);
        }
        try {
            clienteRepository.save(cliente);
        }catch (Exception e){
            throw e;
        }
        return new ResponseEntity<ClienteDto>(cliente, HttpStatus.CREATED);
    }

    @PutMapping(path = "/update",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @CrossOrigin(origins = "*")
    public ResponseEntity<ClienteDto> update(@RequestBody ClienteRequest newCliente) {
            ClienteDto cliente = new ClienteDto();
            EmpresaDto empresaDto = new EmpresaDto();

            Optional<EmpresaDto> respEmpresa = empresaRepository.findById(newCliente.getIdEmpresa());
            if(respEmpresa.isPresent()) {
                cliente.setId(newCliente.getId());
                cliente.setHabilitado(newCliente.getHabilitado());
                cliente.setApellido(newCliente.getApellido());
                cliente.setCiudad(newCliente.getCiudad());
                cliente.setComuna(newCliente.getComuna());
                cliente.setDireccion(newCliente.getDireccion());
                cliente.setEmail(newCliente.getEmail());
                cliente.setNombre(newCliente.getNombre());
                cliente.setRut(newCliente.getRut());
                cliente.setTelefono(newCliente.getTelefono());
                cliente.setEmpresa(respEmpresa.get());
            }else{
                return new ResponseEntity("Error Empresa no existe",HttpStatus.BAD_REQUEST);
            }

            clienteRepository.save(cliente);

        return new ResponseEntity(cliente, HttpStatus.CREATED);
    }

    @DeleteMapping(path = "/delete",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @CrossOrigin(origins = "*")
    public ResponseEntity<ClienteDto> delete(@RequestBody ClienteRequest newCliente) {

        ClienteDto cliente = new ClienteDto();
        Optional<EmpresaDto> respEmpresa = empresaRepository.findById(newCliente.getIdEmpresa());

        if(respEmpresa.isPresent()) {
            cliente.setId(newCliente.getId());
            cliente.setHabilitado(0);
            cliente.setApellido(newCliente.getApellido());
            cliente.setCiudad(newCliente.getCiudad());
            cliente.setComuna(newCliente.getComuna());
            cliente.setDireccion(newCliente.getDireccion());
            cliente.setEmail(newCliente.getEmail());
            cliente.setNombre(newCliente.getNombre());
            cliente.setRut(newCliente.getRut());
            cliente.setTelefono(newCliente.getTelefono());
            cliente.setEmpresa(respEmpresa.get());
        }else{
            return new ResponseEntity("Error Empresa no existe",HttpStatus.BAD_REQUEST);
        }

        try {
            clienteRepository.save(cliente);
        }catch (Exception e){
            throw e;
        }
        return new ResponseEntity(cliente, HttpStatus.OK);

    }
}
