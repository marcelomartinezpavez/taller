package com.personal.taller.repository;

import com.personal.taller.dto.ClienteDto;
import com.personal.taller.dto.ProveedorDto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProveedorRepository extends JpaRepository<ProveedorDto, Long> {

    //@Query("select * from proveedores")
    //List<ProveedorDto> findAll();

    @Query(value = "select * from proveedores p where p.habilitado = 1", nativeQuery = true)
    List<ProveedorDto> findAllHabilitado();

    @Query(value = "select * from proveedores p where p.rut = :rut and p.habilitado = 1", nativeQuery = true)
    Optional<ProveedorDto> findByRutAndHabilitado(String rut);

    @Query(value = "select * from proveedores p where p.rut = :rut and p.empresa_id = :idEmpresa and p.habilitado = 1", nativeQuery = true)
    Optional<ProveedorDto> findByRutAndHabilitadoAndIdEmpresa(String rut, long idEmpresa);


    @Query(value = "select * from proveedores p where p.empresa_id = :idEmpresa and p.habilitado = 1", nativeQuery = true)
    List<ProveedorDto> findByEmpresaId(long idEmpresa);


    //@Query("{rut:'?0'}")
    ProveedorDto findByRut(String rut);

    //@Query(value="{category:'?0'}", fields="{'name' : 1, 'quantity' : 1}")
    //List<ClienteDto> findAll(String category);

}
