package model;
import lombok.*;

@AllArgsConstructor
@Setter
@Getter
@ToString
@Builder
public class Address {
    private Type type;
    private String street;
    private String city;
    private String country;
}
