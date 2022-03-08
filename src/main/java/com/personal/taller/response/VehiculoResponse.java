package com.personal.taller.response;

import com.personal.taller.dto.ProveedorDto;
import com.personal.taller.dto.VehiculoDto;

import java.util.List;

public class VehiculoResponse {

    private List<VehiculoDto> vehiculoDtoList;

    public List<VehiculoDto> getVehiculoDtoList() {
        return vehiculoDtoList;
    }

    public void setVehiculoDtoList(List<VehiculoDto> vehiculoDtoList) {
        this.vehiculoDtoList = vehiculoDtoList;
    }
}
