package com.personal.taller.repository;

import com.personal.taller.dto.ClienteDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ClienteRepository extends JpaRepository<ClienteDto, Long> {

    //@Query("select * from clientes")
    //List<ClienteDto> findAll();

    //@Query("{rut:'?0'}")
    //ClienteDto findByRut(String rut);
    //@Query("SELECT c FROM clientes c where c.rut=?1")
    List<ClienteDto> findByRut(String rut);

    //@Query(value="{category:'?0'}", fields="{'name' : 1, 'quantity' : 1}")
    //List<ClienteDto> findAll(String category);

}
