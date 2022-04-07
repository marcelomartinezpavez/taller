package com.personal.taller.service.impl;

import com.personal.taller.dto.ClienteDto;
import com.personal.taller.dto.EmpresaDto;
import com.personal.taller.repository.ClienteRepository;
import com.personal.taller.repository.EmpresaRepository;
import com.personal.taller.request.ClienteRequest;
import com.personal.taller.response.ClienteResponse;
import com.personal.taller.service.ClienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ClienteServiceImpl implements ClienteService {

    @Autowired
    ClienteRepository clienteRepository;

    @Autowired
    EmpresaRepository empresaRepository;

    public ResponseEntity getAll(){
        System.out.println("getAll");
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
        return new ResponseEntity(clienteDtoList, HttpStatus.OK);
    }

    public ResponseEntity getClientByCompany(long idempresa){
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

    public ResponseEntity getClientByRut(String rut){
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

    public ResponseEntity createClient(ClienteRequest newCliente){
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
            return new ResponseEntity("Error interno al crear Cliente", HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<ClienteDto>(cliente, HttpStatus.CREATED);
    }

    public ResponseEntity updateClient(ClienteRequest newCliente){
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

    public ResponseEntity deleteClient(ClienteRequest newCliente){
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
            return new ResponseEntity("Error interno al eliminar cliente",HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity(cliente, HttpStatus.OK);
    }

}
