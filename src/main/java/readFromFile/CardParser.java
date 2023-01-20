package readFromFile;

import model.BussCardContact;
import model.Telephone;

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
        Telephone telephone = new Telephone();
        String regex = "^(FN|TEL|EMAIL|ADDRESS|WEB)[;:](TYPE=.*:|)(.+)$";
        Pattern pattern = Pattern.compile(regex, Pattern.MULTILINE);
        Matcher matcher = pattern.matcher(contact);
        while(matcher.find()){
            //System.out.println(matcher.group(1));
            //System.out.println(matcher.group(2));
            System.out.println(matcher.group(3));

            if(matcher.group(1).contains("FN"))
                bussCardContact.setFullname(matcher.group(3));
            if(matcher.group(1).contains("TEL"))
                telephone.setVoice(matcher.group(3));
        }
        return null;
    }

    public static void main(String[] args) {
        CardParser cardParser = new CardParser();
        List<String> list = cardParser.fileParser("input_business_card.txt");
        for (String contact : list) {
            cardParser.parseContact(contact);
        }


    }
}
