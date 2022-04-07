package com.personal.taller.repository;

import com.personal.taller.dto.RepuestoDto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RepuestoRepository extends JpaRepository<RepuestoDto, Long> {

    //@Query("select * from repuesto")
    //List<RepuestoDto> findAll();

    //Hacer metodo que busque todos los repuestos


    @Query(value = "select * from repuesto r where r.habilitado = 1", nativeQuery = true)
    List<RepuestoDto> findAllHabilitado();

    @Query(value = "select * from repuesto r where r.habilitado = 1 and r.codigo = :codigo", nativeQuery = true)
    RepuestoDto findByCodigo(String codigo);

    //@Query(value="{category:'?0'}", fields="{'name' : 1, 'quantity' : 1}")
    //List<ClienteDto> findAll(String category);

}
