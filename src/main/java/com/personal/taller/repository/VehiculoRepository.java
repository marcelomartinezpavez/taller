package com.personal.taller.repository;

import com.personal.taller.dto.ClienteDto;
import com.personal.taller.dto.ProveedorDto;
import com.personal.taller.dto.VehiculoDto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface VehiculoRepository extends JpaRepository<VehiculoDto, Long> {

    //@Query("select * from vehiculo")
    //List<VehiculoDto> findAll();

    @Query(value = "select * from vehiculo v ", nativeQuery = true)
    List<VehiculoDto> findAllHabilitado();


    //@Query("{patente:'?0'}")
    @Query(value = "select * from vehiculo v where v.patente = :patente ", nativeQuery = true)
    VehiculoDto findByPatente(String patente);

    @Query(value = "select * from vehiculo as v FULL JOIN clientes as c on v.rut_dueno = c.rut " +
            "where v.patente = :patente " +
            "and c.empresa_id = :idEmpresa ", nativeQuery = true)
    VehiculoDto findByPatenteAndEmpresa(String patente, long idEmpresa);

    //@Query("{rutDueno:'?0'}")
    @Query(value = "select * from vehiculo v where v.rut_dueno = :rutCliente", nativeQuery = true)
    List<VehiculoDto> findByRutDueno(String rutCliente);
    //@Query(value="{category:'?0'}", fields="{'name' : 1, 'quantity' : 1}")
    //List<ClienteDto> findAll(String category);

}
