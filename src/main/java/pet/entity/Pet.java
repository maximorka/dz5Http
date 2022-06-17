package pet.entity;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Pet {
    private int id;
    private Category category;
    private String name;
    private String[] photoUrls;
    private Tag[] tags;
    private StatusPets status;
}
