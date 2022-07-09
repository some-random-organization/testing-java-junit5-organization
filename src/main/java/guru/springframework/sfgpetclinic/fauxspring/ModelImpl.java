package guru.springframework.sfgpetclinic.fauxspring;

import guru.springframework.sfgpetclinic.fauxspring.Model;
import guru.springframework.sfgpetclinic.model.Vet;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

//public class ModelImpl implements Model<Vet> {
//
//    Map<String, Set> map = new HashMap();
//
//    @Override
//    public void addAttribute(String key, Object o) {
//        map.put(key, (Set) o);
//    }
//
//    @Override
//    public void addAttribute(Object o) {
//        map.put(o.toString(), (Set) o);
//    }
//
//    public Map<String, Object> getMap() {
//        return Collections.unmodifiableMap(map);
//    }
//
//    public Set<Vet> get(String key){
//        return map.get(key);
//    }
//}
