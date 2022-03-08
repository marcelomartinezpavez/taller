package com.personal.taller.response;

import com.personal.taller.dto.ClienteDto;
import com.personal.taller.dto.ProveedorDto;

import java.util.List;

public class ProveedorResponse {

    private List<ProveedorDto> proveedorDtoList;

    public List<ProveedorDto> getProveedorDtoList() {
        return proveedorDtoList;
    }

    public void setProveedorDtoList(List<ProveedorDto> proveedorDtoList) {
        this.proveedorDtoList = proveedorDtoList;
    }
}
