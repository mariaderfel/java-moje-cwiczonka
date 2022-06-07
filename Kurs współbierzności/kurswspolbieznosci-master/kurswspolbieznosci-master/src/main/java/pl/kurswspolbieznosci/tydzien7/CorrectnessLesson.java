package pl.kurswspolbieznosci.tydzien7;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

public class CorrectnessLesson {
    static Logger logger = LoggerFactory.getLogger(CorrectnessLesson.class);

    public static void main(String[] args) {
        Store store = new Store();

        Set<Integer> ids = new HashSet<>();

        ids.add(store.add("ksiazka"));
        ids.add(store.add("rower"));
        ids.add(store.add("zabawka"));
        ids.add(store.add("koszula"));
        ids.add(store.add("jeansy"));
        ids.add(store.add("kurtka"));
        ids.add(store.add("buty"));

        logger.info("Ids size correctness: {}", ids.size() == 7);
        logger.info("Store size: {}", store.size());
        logger.info("Store: {}", store.inventory);
    }

    static class Store {
        private final Map<Integer, String> inventory = new ConcurrentHashMap<>();

        public Integer add(String name) {
            int id = inventory.size();
            inventory.put(id, name);
            return id;
        }

        public String getById(Integer id) {
            return inventory.get(id);
        }

        public Integer size() {
            return inventory.size();
        }
    }
}
