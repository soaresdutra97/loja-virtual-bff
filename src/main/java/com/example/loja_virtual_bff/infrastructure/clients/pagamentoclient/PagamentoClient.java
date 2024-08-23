package com.example.loja_virtual_bff.infrastructure.clients.pagamentoclient;

import com.example.loja_virtual_bff.api.request.CartaoRequestDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(value = "pagamento-api", url = "${client1.pagamento-client}")
public interface PagamentoClient {

    @PostMapping("/pagamento")
    Boolean verificaPagamento(@RequestBody CartaoRequestDTO cartaoRequestDTO);
}