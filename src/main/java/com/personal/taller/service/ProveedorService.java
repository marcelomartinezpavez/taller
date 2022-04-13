package com.personal.taller.service;

import com.personal.taller.request.ProveedorRequest;
import org.springframework.http.ResponseEntity;

public interface ProveedorService {

    ResponseEntity getAllProveedor();

    ResponseEntity getAllProveedorPorEmpresa(long idempresa);

    ResponseEntity getProveedor(String rut);

    ResponseEntity create(ProveedorRequest newProveedor);
}
