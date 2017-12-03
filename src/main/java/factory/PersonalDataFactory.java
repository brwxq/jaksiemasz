package factory;

import com.google.common.base.Charsets;
import com.google.common.io.Files;
import model.PersonalData;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class PersonalDataFactory {

    private final String PATH_TO_EMAILS_FILE = "./src/main/java/resources/emails";
    private final String PATH_TO_NAMES_FILE = "./src/main/java/resources/names";
    private final String PATH_TO_SURNAMES_FILE = "./src/main/java/resources/surnames";

    private List<String> names;
    private List<String> surnames;
    private List<String> emails;

    private final Random random = new Random();

    public List<PersonalData> generate(int limit) throws IOException {
        if(names==null || surnames==null || emails==null){
            try {
                emails = Files.readLines(new File(PATH_TO_EMAILS_FILE), Charsets.UTF_8);
                names = Files.readLines(new File(PATH_TO_NAMES_FILE), Charsets.UTF_8);
                surnames = Files.readLines(new File(PATH_TO_SURNAMES_FILE), Charsets.UTF_8);
            }catch(IOException e){
                throw new FileNotFoundException("File not found");
            }
        }
        return Stream
                .generate(this::generateOne)
                .limit(limit)
                .collect(Collectors.toList());
    }


    private PersonalData generateOne() {
        String randomName = getRandom(names);
        String randomSurname = getRandom(surnames);
        String randomEmail = getRandomEmail(randomName,randomSurname);
        return new PersonalData(
                randomName,
                randomSurname,
                randomEmail
        );
    }

    private String getRandomEmail(String name, String surname) {
        String address="";
        switch(random.nextInt(4)) {
            case 0:
                address = name.charAt(0) + surname;
                break;
            case 1:
                address = surname + name.charAt(0);
                break;
            case 2:
                address = surname + name;
                break;
            case 3:
                address = name + surname;
                break;
        }
        return address.toLowerCase() + "@" + getRandom(emails);
    }

    private String getRandom(List<String> elements) {
        return elements.get(
                random.nextInt(
                        elements.size()));
    }

}
