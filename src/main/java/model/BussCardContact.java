package model;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
//@ToString
public class BussCardContact {
    private String fullname;
    private Telephone telephone;
    private Email email;
    private Address address;
    private Web web;

    @Override
    public String toString() {
        return  "-------------------------------------------------------\n" +
                "\t\tFULLNAME => " + fullname + "\n" +
                "-------------------------------------------------------\n" +
                "TELEPHONE:\n" +
                "\tTYPE    : " + telephone.getType() + "\n" +
                "\tVOICE   : " + telephone.getVoice() + "\n" +
                "EMAIL:\n" +
                "\tTYPE    : " + email.getType() + "\n" +
                "\tINTERNET: " + email.getInternet() + "\n" +
                "ADDRESS:\n" +
                "\tTYPE    : " + address.getType() + "\n" +
                "\tSTREET  : " + address.getStreet() + "\n" +
                "\tCITY    : " + address.getCity() + "\n" +
                "\tCOUNTRY : " + address.getCountry() + "\n" +
                "WEB:\n" +
                "\tTYPE    : " + web.getType() + "\n" +
                "\tINTERNET: " + web.getInternet() + "\n" +
                "-------------------------------------------------------";
    }
}