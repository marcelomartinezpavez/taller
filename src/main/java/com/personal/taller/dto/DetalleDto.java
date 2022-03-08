package com.personal.taller.dto;

//import org.springframework.data.annotation.Id;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "detalle")
public class DetalleDto {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    @Column(name = "descripcion")
    private String descripcion;
    @Column(name = "recargo")
    private long recargo;

    //@ManyToOne
    //@Column(name = "ordenTrabajoDto")
    //private OrdenTrabajoDto ordenTrabajoDto;

    public DetalleDto(){}

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public long getRecargo() {
        return recargo;
    }

    public void setRecargo(long recargo) {
        this.recargo = recargo;
    }

/*    public OrdenTrabajoDto getOrdenTrabajoDto() {
        return ordenTrabajoDto;
    }

    public void setOrdenTrabajoDto(OrdenTrabajoDto ordenTrabajoDto) {
        this.ordenTrabajoDto = ordenTrabajoDto;
    }*/
}
