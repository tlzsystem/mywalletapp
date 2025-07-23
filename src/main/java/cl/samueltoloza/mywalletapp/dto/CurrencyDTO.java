package cl.samueltoloza.mywalletapp.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CurrencyDTO {

    private Long id;
    private String code;
    private String name;
    private String symbol;
    private int precision;

}
