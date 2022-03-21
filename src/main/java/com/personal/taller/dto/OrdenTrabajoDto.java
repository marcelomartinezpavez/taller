package com.personal.taller.dto;

//import org.springframework.data.annotation.Id;
//import org.springframework.data.mongodb.core.mapping.Document;

import javax.persistence.*;
import java.util.Date;
import java.util.List;
import java.util.Set;

//@Document("ordenTrabajo")
@Entity
@Table(name = "ordenTrabajo")
public class OrdenTrabajoDto {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    @Column(name = "numeroOrden")
    private String numeroOrden;
    @Column(name = "habilitado")
    private int habilitado;
    @Column(name = "fechaIngreso")
    private String fechaIngreso;
    @Column(name = "rutCliente")
    private String rutCliente;
    @Column(name = "patenteVehiculo")
    private String patenteVehiculo;
    @Column(name = "codigo")
    private String codigo;
    @Column(name = "valorOt")
    private String valorOt;


    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL, orphanRemoval = true)
    @Column(name = "detalle")
    private Set<DetalleDto> detalle;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "vehiculo_id")
    private VehiculoDto vehiculo;

    //@OneToOne(fetch = FetchType.LAZY)
    //@JoinColumn(foreignKey, name = "cliente_id" )
    //private ClienteDto cliente;

    //@OneToOne(fetch = FetchType.LAZY)
    //@JoinColumn(name = "rut_cliente")
    //private ClienteDto rut_cliente;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "empresa_id")
    private EmpresaDto empresa;

    public OrdenTrabajoDto(){}

    public OrdenTrabajoDto(long id, int habilitado, String numeroOrden, String fechaIngreso, String rutCliente, String patenteVehiculo, Set<DetalleDto> detalle, String codigoRepuestos){
        super();
        this.id= id;
        this.numeroOrden = numeroOrden;
        this.habilitado = habilitado;
        this.fechaIngreso = fechaIngreso;
        this.rutCliente = rutCliente;
        this.patenteVehiculo = patenteVehiculo;
        this.detalle = detalle;
        this.codigo = codigoRepuestos;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getNumeroOrden() {
        return numeroOrden;
    }

    public void setNumeroOrden(String numeroOrden) {
        this.numeroOrden = numeroOrden;
    }

    public int getHabilitado() {
        return habilitado;
    }

    public void setHabilitado(int habilitado) {
        this.habilitado = habilitado;
    }

    public String getFechaIngreso() {
        return fechaIngreso;
    }

    public void setFechaIngreso(String fechaIngreso) {
        this.fechaIngreso = fechaIngreso;
    }

    public String getRutCliente() {
        return rutCliente;
    }

    public void setRutCliente(String rutCliente) {
        this.rutCliente = rutCliente;
    }

    public String getPatenteVehiculo() {
        return patenteVehiculo;
    }

    public void setPatenteVehiculo(String patenteVehiculo) {
        this.patenteVehiculo = patenteVehiculo;
    }

    public Set<DetalleDto> getDetalle() {
        return detalle;
    }

    public void setDetalle(Set<DetalleDto> detalle) {
        this.detalle = detalle;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigoRepuestos(String codigoRepuestos) {
        this.codigo = codigoRepuestos;
    }

    public String getValorOt() {
        return valorOt;
    }

    public void setValorOt(String valorOt) {
        this.valorOt = valorOt;
    }
}
