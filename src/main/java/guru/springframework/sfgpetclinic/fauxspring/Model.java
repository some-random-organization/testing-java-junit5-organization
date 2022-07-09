package guru.springframework.sfgpetclinic.fauxspring;

import guru.springframework.sfgpetclinic.model.Vet;

import java.util.Map;
import java.util.Set;

public interface Model<T> {

    void addAttribute(String key, Object o);

    void addAttribute(Object o);

    Map<String, Object>  getMap();

    Set<T> get(String key);
}
