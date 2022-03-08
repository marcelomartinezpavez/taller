package com.personal.taller.repository;

import com.personal.taller.dto.ClienteDto;
import com.personal.taller.dto.LoginDto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface LoginRepository extends JpaRepository<LoginDto, Long> {

    //@Query("select l from users l where l.users = ?0 and l.pass = ?1")
    Optional<LoginDto> findByUsersAndPass(String users, String pass);

}
