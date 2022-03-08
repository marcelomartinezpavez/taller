package com.personal.taller.response;

import com.personal.taller.dto.ClienteDto;

import java.util.List;

public class ClienteResponse {
    private List<ClienteDto> clienteDtoList;

    public List<ClienteDto> getClienteDtoList() {
        return clienteDtoList;
    }

    public void setClienteDtoList(List<ClienteDto> clienteDtoList) {
        this.clienteDtoList = clienteDtoList;
    }
}
