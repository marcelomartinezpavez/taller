package com.personal.taller.repository;

import com.personal.taller.dto.ClienteDto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ClienteRepository extends JpaRepository<ClienteDto, Long> {
    List<ClienteDto> findByRut(String rut);
}
