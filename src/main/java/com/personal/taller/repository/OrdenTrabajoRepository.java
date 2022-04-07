package com.personal.taller.repository;

import com.personal.taller.dto.ClienteDto;
import com.personal.taller.dto.OrdenTrabajoDto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrdenTrabajoRepository extends JpaRepository<OrdenTrabajoDto, Long> {

    //@Query("select * from ordenTrabajo")
    //List<OrdenTrabajoDto> findAll();

    //@Query("{numeroOrden:'?0'}")
    OrdenTrabajoDto findByNumeroOrden(String numeroOrden);

    List<OrdenTrabajoDto> findByPatenteVehiculo(String patente);

    List<OrdenTrabajoDto> findByRutCliente(String rutCliente);

    @Query(value = "select * from ORDEN_TRABAJO c where c.empresa_id = :idEmpresa", nativeQuery = true)
    List<OrdenTrabajoDto> findByIdEmpresa(long idEmpresa);

    //@Query("{id:'?0'}")
    //OrdenTrabajoDto findById(String id);

    //@Query(value="{category:'?0'}", fields="{'name' : 1, 'quantity' : 1}")
    //List<ClienteDto> findAll(String category);

}
