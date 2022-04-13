package com.personal.taller.request;

import com.personal.taller.dto.DetalleDto;

import java.util.List;

public class OrdenTrabajoRequest {

    private long id;
    private String numeroOrden;
    private boolean habilitado;
    private String fechaIngreso;
    private String rutCliente;
    private String patenteVehiculo;
    private String codigo;
    private long valorOt;

    private long idEmpresa;
    private List<DetalleRequest> detalleDtoList;

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

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public long getValorOt() {
        return valorOt;
    }

    public void setValorOt(long valorOt) {
        this.valorOt = valorOt;
    }

    public long getIdEmpresa() {
        return idEmpresa;
    }

    public void setIdEmpresa(long idEmpresa) {
        this.idEmpresa = idEmpresa;
    }

    public List<DetalleRequest> getDetalleDtoList() {
        return detalleDtoList;
    }

    public void setDetalleDtoList(List<DetalleRequest> detalleDtoList) {
        this.detalleDtoList = detalleDtoList;
    }
}
