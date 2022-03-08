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

    //@Query("{patente:'?0'}")
    VehiculoDto findByPatente(String patente);

    //@Query("{rutDueno:'?0'}")
    List<VehiculoDto> findByRutDueno(String rutCliente);
    //@Query(value="{category:'?0'}", fields="{'name' : 1, 'quantity' : 1}")
    //List<ClienteDto> findAll(String category);

}
