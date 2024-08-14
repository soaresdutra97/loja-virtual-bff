package com.example.loja_virtual_bff.api.request;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@EqualsAndHashCode
public class WishListRequestDTO {

    private String produtoId;

}
