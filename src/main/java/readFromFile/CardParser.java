package readFromFile;

import model.*;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CardParser {
    final String BEGIN_VCARD = "BEGIN:VCARD";
    final String END_VCARD = "END:VCARD";

    public List<String> fileParser(String path){
        List<String> list = new ArrayList<>();
        try(BufferedReader reader = new BufferedReader(new FileReader(path))){
            StringBuilder sb = new StringBuilder();
            String line;
            boolean start = false;
            while((line = reader.readLine()) != null){
                if(line.contains(END_VCARD)){
                    start = false;
                    list.add(sb.toString());
                    sb = new StringBuilder();
                }
                if (start)
                    sb.append(line).append("\n");
                if(line.contains(BEGIN_VCARD))
                    start = true;
            }
        }catch(IOException ex){
            ex.printStackTrace();
        }
        return list;
    }

    public BussCardContact parseContact(String contact){
        BussCardContact bussCardContact = new BussCardContact();
        Telephone telephone = new Telephone();;
        Email email = new Email();
        Address address = new Address();
        Web web = new Web();
        //String regex = "^(FN|TEL|EMAIL|ADDRESS|WEB)[;:](TYPE=.*:|)(.+)$";
        String regex = "^(FN|TEL|EMAIL|ADDRESS|WEB)[;:](TYPE=|)(WORK|PREF|HOME|)(,|)(VOICE|INTERNET|STREET|CITY|COUNTRY|)(: |:|)(.+)$";
        Pattern pattern = Pattern.compile(regex, Pattern.MULTILINE);
        Matcher matcher = pattern.matcher(contact);
        while(matcher.find()){
            //System.out.println(matcher.group(5) + " " + matcher.group(7));
            switch (matcher.group(1)){
                case("FN") -> bussCardContact.setFullname(matcher.group(7));
                case("TEL") -> {
                    telephone.setType(Type.valueOf(matcher.group(3)));
                    telephone.setVoice(matcher.group(7));
                }
                case("EMAIL") ->{
                    email.setType(Type.valueOf(matcher.group(3)));
                    email.setInternet(matcher.group(7));
                }
                case("ADDRESS") ->{
                    address.setType(Type.valueOf(matcher.group(3)));
                    switch (matcher.group(5)){
                        case("STREET") -> address.setStreet(matcher.group(7));
                        case("CITY") -> address.setCity(matcher.group(7));
                        case("COUNTRY") -> address.setCountry(matcher.group(7));
                    }
                }
                case("WEB")->{
                    web.setType(Type.valueOf(matcher.group(3)));
                    web.setInternet(matcher.group(7));
                }
            }
        }
            bussCardContact.setTelephone(telephone);
            bussCardContact.setEmail(email);
            bussCardContact.setAddress(address);
            bussCardContact.setWeb(web);

        return bussCardContact;
    }

    public static void main(String[] args) {
        CardParser cardParser = new CardParser();
        List<String> list = cardParser.fileParser("input_business_card.txt");
        for (String contact : list) {
            System.out.println(cardParser.parseContact(contact));
        }
    }
}