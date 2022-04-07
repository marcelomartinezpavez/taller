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
    @Query(value = "select * from ORDEN_TRABAJO ot where ot.numero_orden = :numeroOrden and ot.habilitado = 1", nativeQuery = true)
    OrdenTrabajoDto findByNumeroOrden(String numeroOrden);

    @Query(value = "select * from ORDEN_TRABAJO ot where ot.numero_orden = :numeroOrden and empresa_id = :idEmpresa and ot.habilitado = 1", nativeQuery = true)
    OrdenTrabajoDto findByNumeroOrdenAndEmpresa(String numeroOrden, long idEmpresa);

    @Query(value = "select * from ORDEN_TRABAJO ot where ot.patente_vehiculo = :patente and ot.habilitado = 1", nativeQuery = true)
    List<OrdenTrabajoDto> findByPatenteVehiculo(String patente);

    @Query(value = "select * from ORDEN_TRABAJO ot where ot.patente_vehiculo = :patente and empresa_id = :idEmpresa and ot.habilitado = 1", nativeQuery = true)
    List<OrdenTrabajoDto> findByPatenteVehiculoAndEmpresa(String patente, long idEmpresa);

    @Query(value = "select * from ORDEN_TRABAJO ot where ot.rut_cliente = :rutCliente and ot.habilitado = 1", nativeQuery = true)
    List<OrdenTrabajoDto> findByRutCliente(String rutCliente);


    @Query(value = "select * from ORDEN_TRABAJO ot where ot.rut_cliente = :rutCliente and empresa_id = :idEmpresa and ot.habilitado = 1", nativeQuery = true)
    List<OrdenTrabajoDto> findByRutClienteAndEmpresa(String rutCliente, long idEmpresa);

    @Query(value = "select * from ORDEN_TRABAJO ot where ot.empresa_id = :idEmpresa", nativeQuery = true)
    List<OrdenTrabajoDto> findByIdEmpresa(long idEmpresa);

    //@Query("{id:'?0'}")
    //OrdenTrabajoDto findById(String id);

    //@Query(value="{category:'?0'}", fields="{'name' : 1, 'quantity' : 1}")
    //List<ClienteDto> findAll(String category);

}
