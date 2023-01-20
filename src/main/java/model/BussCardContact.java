package model;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString
public class BussCardContact {
    private String fullname;
    private Telephone telephone;
    private Email email;
    private Address address;
    private Web web;

    public BussCardContact(){}
}