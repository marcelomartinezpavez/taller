package com.personal.taller.repository;

import com.personal.taller.dto.ClienteDto;
import com.personal.taller.dto.ProveedorDto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProveedorRepository extends JpaRepository<ProveedorDto, Long> {

    //@Query("select * from proveedores")
    //List<ProveedorDto> findAll();

    //@Query("{rut:'?0'}")
    ProveedorDto findByRut(String rut);

    //@Query(value="{category:'?0'}", fields="{'name' : 1, 'quantity' : 1}")
    //List<ClienteDto> findAll(String category);

}
