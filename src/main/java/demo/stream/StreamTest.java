package demo.stream;

import java.util.Optional;

public class StreamTest {
    public  String getCity8(Person person) {
        return Optional.ofNullable(person)
                .map(Person::getAddress)
                .map(Address::getCity)
                .orElseGet(() -> "默认值Jdk8");
    }

    public String getCity(Person person)  {
        if (person != null){
            Address address = person.getAddress();
            if (address != null){
                String city = address.getCity();
                if (city != null){
                    return city;
                }
            }
        }
        return "默认值Jdk";
    }
}
