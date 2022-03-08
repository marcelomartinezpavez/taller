package com.personal.taller.response;


import com.personal.taller.dto.RepuestoDto;

import java.util.List;

public class RepuestoResponse {

    private List<RepuestoDto> repuestoDtoList;

    public List<RepuestoDto> getRepuestoDtoList() {
        return repuestoDtoList;
    }

    public void setRepuestoDtoList(List<RepuestoDto> repuestoDtoList) {
        this.repuestoDtoList = repuestoDtoList;
    }
}
