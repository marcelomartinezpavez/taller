package com.personal.taller.dto;

//import org.springframework.data.annotation.Id;
//import org.springframework.data.mongodb.core.mapping.Document;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;
import java.util.Set;

//@Document("repuesto")
@JsonIgnoreProperties({"hibernateLazyInitializer"})
@Entity
@Table(name = "repuesto")
public class RepuestoDto implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    @Column(name = "habilitado")
    private int habilitado;
    @Column(name = "nombre")
    private String nombre;
    @Column(name = "codigo")
    private String codigo;
    @Column(name = "marca")
    private String marca;
    @Column(name = "modelo")
    private String modelo;
    @Column(name = "anio")
    private String anio;
    @Column(name = "rutProveedor")
    private String rutProveedor;
    @Column(name = "valor")
    private long valor;

    @OneToOne
    @JoinColumn(name = "detalle_id")
    private DetalleDto detalle;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "empresa_id")
    private EmpresaDto empresa;

    @OneToMany(fetch = FetchType.EAGER)
    @Column(name = "proveedor")
    private Set<ProveedorDto> proveedor;


    public RepuestoDto(){}

    public RepuestoDto(long id, int habilitado, String marca, String modelo, String nombre, String anio,
                       String codigo, String rutProveedor, long valor) {
        super();
        this.id = id;
        this.habilitado = habilitado;
        this.marca = marca;
        this.modelo = modelo;
        this.nombre = nombre;
        this.anio = anio;
        this.codigo = codigo;
        this.rutProveedor = rutProveedor;
        this.valor = valor;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public int getHabilitado() {
        return habilitado;
    }

    public void setHabilitado(int habilitado) {
        this.habilitado = habilitado;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getAnio() {
        return anio;
    }

    public void setAnio(String anio) {
        this.anio = anio;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getRutProveedor() {
        return rutProveedor;
    }

    public void setRutProveedor(String rutProveedor) {
        this.rutProveedor = rutProveedor;
    }

    public long getValor() {
        return valor;
    }

    public void setValor(long valor) {
        this.valor = valor;
    }

    public DetalleDto getDetalle() {
        return detalle;
    }

    public void setDetalle(DetalleDto detalle) {
        this.detalle = detalle;
    }

    public EmpresaDto getEmpresa() {
        return empresa;
    }

    public void setEmpresa(EmpresaDto empresa) {
        this.empresa = empresa;
    }

    public Set<ProveedorDto> getProveedor() {
        return proveedor;
    }

    public void setProveedor(Set<ProveedorDto> proveedor) {
        this.proveedor = proveedor;
    }
}
