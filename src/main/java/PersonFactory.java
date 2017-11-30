import com.google.common.base.Charsets;
import com.google.common.io.Files;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class PersonFactory {

    private final String PATH_TO_EMAILS_FILE = "/Users/dawid/IdeaProjects/jsm-exercise0/src/main/resources/emails";
    private final String PATH_TO_NAMES_FILE = "/Users/dawid/IdeaProjects/jsm-exercise0/src/main/resources/names";
    private final String PATH_TO_SURNAMES_FILE = "/Users/dawid/IdeaProjects/jsm-exercise0/src/main/resources/surnames";

    private List<String> names;
    private List<String> surnames;
    private List<String> emails;

    private final Random random = new Random();

    public List<Person> generate(int limit) throws IOException {
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
                .generate(this::generate)
                .limit(limit)
                .collect(Collectors.toList());
    }

    private Person generate() {
        String randomName = getRandom(names);
        String randomSurname = getRandom(surnames);
        String randomEmail = getRandomEmail(randomName,randomSurname);
        return new Person(
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
