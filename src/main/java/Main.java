import com.google.common.collect.ComparisonChain;

import java.io.IOException;
import java.util.List;

public class Main {
    public static void main(String[] args) throws IOException {
        PersonFactory generator = new PersonFactory();

        int limit = 20;

        if(args.length>0 && args[0].matches("[1-9]+[0-9]*")){
            limit = Integer.valueOf(args[0]);
        }

        List<Person> persons = generator.generate(limit);

        persons.sort((p1,p2)->
            ComparisonChain
                    .start()
                    .compare(p1.getName(),p2.getName())
                    .compare(p1.getSurname(),p2.getSurname())
                    .result()
        );

        persons.forEach(System.out::println);
        //branch test
    }
}
