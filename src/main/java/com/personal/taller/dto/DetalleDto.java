package com.personal.taller.dto;

//import org.springframework.data.annotation.Id;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@JsonIgnoreProperties({"hibernateLazyInitializer"})
@Entity
@Table(name = "detalle")
public class DetalleDto implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    @Column(name = "descripcion")
    private String descripcion;
    @Column(name = "recargo")
    private long recargo;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ordenTrabajo_id")
    private OrdenTrabajoDto ordenTrabajo;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "repuesto_id")
    private RepuestoDto repuesto;


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

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public OrdenTrabajoDto getOrdenTrabajo() {
        return ordenTrabajo;
    }

    public void setOrdenTrabajo(OrdenTrabajoDto ordenTrabajo) {
        this.ordenTrabajo = ordenTrabajo;
    }

    public RepuestoDto getRepuesto() {
        return repuesto;
    }

    public void setRepuesto(RepuestoDto repuesto) {
        this.repuesto = repuesto;
    }

    /*    public OrdenTrabajoDto getOrdenTrabajoDto() {
        return ordenTrabajoDto;
    }

    public void setOrdenTrabajoDto(OrdenTrabajoDto ordenTrabajoDto) {
        this.ordenTrabajoDto = ordenTrabajoDto;
    }*/
}
