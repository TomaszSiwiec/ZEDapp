package com.testowanie.api.testowanieapi;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
public class PurchaserDto implements Serializable {
    private String id;
    private String name;
    private String lastname;
    private String telNumber;
}
