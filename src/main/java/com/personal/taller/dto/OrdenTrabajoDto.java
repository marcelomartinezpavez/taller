package com.personal.taller.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Set;

@JsonIgnoreProperties({"hibernateLazyInitializer"})
@Entity
@Table(name = "ordenTrabajo")
public class OrdenTrabajoDto implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    @Column(name = "numeroOrden")
    private String numeroOrden;
    @Column(name = "habilitado")
    private boolean habilitado;
    @Column(name = "fechaIngreso")
    private String fechaIngreso;
    @Column(name = "rutCliente")
    private String rutCliente;
    @Column(name = "patenteVehiculo")
    private String patenteVehiculo;
    @Column(name = "codigo")
    private String codigo;
    @Column(name = "valorOt")
    private long valorOt;
    @Column(name = "estado")
    private String estado;
    @Column(name = "fechaCerrado")
    private String fechaCerrado;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "vehiculo_id", referencedColumnName = "id")
    private VehiculoDto vehiculo;

    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL, orphanRemoval = true)
    @Column(name = "detalle")
    private Set<DetalleDto> detalle;

    //@OneToOne(fetch = FetchType.LAZY)
    //@JoinColumn(name = "vehiculo_id")
    //private VehiculoDto vehiculo;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "cliente_id", referencedColumnName = "id")
    private ClienteDto cliente;

    //@OneToOne(fetch = FetchType.LAZY)
    //@JoinColumn(name = "rut_cliente")
    //private ClienteDto rut_cliente;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "empresa_id")
    private EmpresaDto empresa;

    public OrdenTrabajoDto(){}

    public OrdenTrabajoDto(long id, boolean habilitado, String numeroOrden, String fechaIngreso, String rutCliente, String patenteVehiculo, Set<DetalleDto> detalle, String codigoRepuestos){
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

    public boolean getHabilitado() {
        return habilitado;
    }

    public void setHabilitado(boolean habilitado) {
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

    public long getValorOt() {
        return valorOt;
    }

    public void setValorOt(long valorOt) {
        this.valorOt = valorOt;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public VehiculoDto getVehiculo() {
        return vehiculo;
    }

    public void setVehiculo(VehiculoDto vehiculo) {
        this.vehiculo = vehiculo;
    }

    public EmpresaDto getEmpresa() {
        return empresa;
    }

    public void setEmpresa(EmpresaDto empresa) {
        this.empresa = empresa;
    }

    public ClienteDto getCliente() {
        return cliente;
    }

    public void setCliente(ClienteDto cliente) {
        this.cliente = cliente;
    }

    public boolean isHabilitado() {
        return habilitado;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getFechaCerrado() {
        return fechaCerrado;
    }

    public void setFechaCerrado(String fechaCerrado) {
        this.fechaCerrado = fechaCerrado;
    }
}
