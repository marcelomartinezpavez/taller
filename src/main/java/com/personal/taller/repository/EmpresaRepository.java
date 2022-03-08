package com.personal.taller.repository;

import com.personal.taller.dto.ClienteDto;
import com.personal.taller.dto.EmpresaDto;
import org.bson.types.ObjectId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
//import org.springframework.data.mongodb.repository.MongoRepository;
//import org.springframework.data.mongodb.repository.Query;

import java.util.Optional;

@Repository
public interface EmpresaRepository extends JpaRepository<EmpresaDto, Long> {

    //@Query("{id:'?0'}")
    //Optional<EmpresaDto> findById(ObjectId id);

}
