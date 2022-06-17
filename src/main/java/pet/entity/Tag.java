package pet.entity;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class Tag{
    private int id;
    private String name;
}