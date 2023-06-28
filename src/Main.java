import java.util.*;
import java.util.stream.Stream;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) {
        List<String> names = Arrays.asList("Jack", "Connor", "Harry", "George", "Samuel", "John");
        List<String> families = Arrays.asList("Evans", "Young", "Harris", "Wilson", "Davies", "Adamson", "Brown");
        Collection<Person> persons = new ArrayList<>();
        for (int i = 0; i < 10_000_000; i++) {
            persons.add(new Person(
                    names.get(new Random().nextInt(names.size())),
                    families.get(new Random().nextInt(families.size())),
                    new Random().nextInt(100),
                    Sex.values()[new Random().nextInt(Sex.values().length)],
                    Education.values()[new Random().nextInt(Education.values().length)])
            );
        }
        long countChildren = persons.stream().filter(value -> value.getAge() < 18).count();

        System.out.println(countChildren);

        Stream<Person> stream = persons.stream();
        List<String> recruitList = stream
                .filter(value -> value.getAge() > 18)
                .filter(value -> value.getAge() <= 27)
                .map(value -> value.getFamily())
                .collect(Collectors.toList());

//      System.out.println(recruitList);

        Stream<Person> stream2 = persons.stream();
        List<Person> labourForceList = stream2
                .filter(value -> value.getEducation().equals(Education.HIGHER))
                .filter(value -> ((value.getSex().equals(Sex.MAN) && value.getAge() > 18 && value.getAge() <= 65) || (value.getSex().equals(Sex.WOMAN) && value.getAge() > 18 && value.getAge() <= 60)))
                .filter(value -> value.getAge() <= 65)
                .sorted(Comparator.comparing(value -> value.getFamily()))
                .collect(Collectors.toList());
        System.out.println(labourForceList);
    }
}