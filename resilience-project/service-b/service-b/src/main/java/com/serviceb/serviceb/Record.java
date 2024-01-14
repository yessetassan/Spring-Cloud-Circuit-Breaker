package com.serviceb.serviceb;


import lombok.Builder;
import lombok.Data;

import java.io.Serializable;

@Data
@Builder
public class Record  implements Serializable {
    private String message;
}