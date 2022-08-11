package DTO;

import Model.Type;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.concurrent.atomic.AtomicInteger;

@Data
@NoArgsConstructor
public class ProductDTO {
    private Type type;
    private AtomicInteger initialStock;
    private Boolean deleted;
}
