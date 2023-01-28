package model;
import lombok.*;

@Setter
@Getter
public class Address {
    private Type type;
    private String street;
    private String city;
    private String country;
}
