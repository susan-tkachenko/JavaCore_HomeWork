package lesson4;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import static java.util.Collections.emptySet;

public class PhoneBook {

    private final Map<String, Set<String>> nameToPhones = new HashMap<>();
    private final Map<String, String> phoneToName = new HashMap<>();

    public void add(String name, String phone) {
        String prevName = phoneToName.put(phone, name);
        if (prevName != null) {
            Set<String> prevNamePhones = nameToPhones.get(prevName);
            prevNamePhones.remove(phone);
            if (prevNamePhones.isEmpty()) {
                nameToPhones.remove(prevName);
            }
        }

        Set<String> phones = nameToPhones.get(name);
        if (phones == null) {
            phones = new HashSet<>();
            nameToPhones.put(name, phones);
        }

        phones.add(phone);
    }

    public Set<String> getPhones(String name) {
        return nameToPhones.getOrDefault(name, emptySet());
    }

}
