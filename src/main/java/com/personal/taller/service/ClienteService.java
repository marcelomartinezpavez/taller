package com.personal.taller.service;

import com.personal.taller.request.ClienteRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

public interface ClienteService {

    ResponseEntity getAll();

    ResponseEntity getClientByCompany(long idempresa);

    ResponseEntity getClientByRut(String rut);

    ResponseEntity createClient(ClienteRequest newCliente);

    ResponseEntity updateClient(ClienteRequest newCliente);

    ResponseEntity deleteClient(ClienteRequest newCliente);

}
