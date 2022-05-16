package com.personal.taller.repository;

import com.personal.taller.dto.RepuestoDto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Set;

@Repository
public interface RepuestoRepository extends JpaRepository<RepuestoDto, Long> {

    //@Query("select * from repuesto")
    //List<RepuestoDto> findAll();

    //Hacer metodo que busque todos los repuestos


    @Query(value = "select * from repuesto r", nativeQuery = true)
    List<RepuestoDto> findAllHabilitado();

    @Query(value = "select * from repuesto r where r.codigo = :codigo", nativeQuery = true)
    RepuestoDto findByCodigo(String codigo);

    @Query(value = "select * from repuesto r where r.rut_proveedor = :rut", nativeQuery = true)
    Set<RepuestoDto> findByProveedor(String rut);

    @Query(value = "select * from repuesto r where r.empresa_id = :idEmpresa", nativeQuery = true)
    List<RepuestoDto> findByEmpresa(long idEmpresa);

    //@Query(value="{category:'?0'}", fields="{'name' : 1, 'quantity' : 1}")
    //List<ClienteDto> findAll(String category);

}
