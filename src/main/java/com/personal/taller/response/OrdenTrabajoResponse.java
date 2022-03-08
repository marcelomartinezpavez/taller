package com.personal.taller.response;

import com.personal.taller.dto.OrdenTrabajoDto;
import com.personal.taller.dto.VehiculoDto;

import java.util.List;

public class OrdenTrabajoResponse {

    private List<OrdenTrabajoDto> ordenTrabajoDtoList;

    public List<OrdenTrabajoDto> getOrdenTrabajoDtoList() {
        return ordenTrabajoDtoList;
    }

    public void setOrdenTrabajoDtoList(List<OrdenTrabajoDto> ordenTrabajoDtoList) {
        this.ordenTrabajoDtoList = ordenTrabajoDtoList;
    }
}
