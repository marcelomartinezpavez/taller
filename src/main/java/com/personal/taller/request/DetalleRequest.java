package com.personal.taller.request;

public class DetalleRequest {

    private String descripcion;
    private long recargo;
    private long repuesto_id;

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

    public long getRepuesto_id() {
        return repuesto_id;
    }

    public void setRepuesto_id(long repuesto_id) {
        this.repuesto_id = repuesto_id;
    }
}
