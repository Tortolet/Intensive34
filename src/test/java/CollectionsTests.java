import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.aston.mineev_ia.task1.car_rent.models.User;

import java.util.*;

public class CollectionsTests {

    private static final Comparator<User> surNameComparator = Comparator.comparing(User::getSurName);

    private final List<User> startList = new ArrayList<>();
    private final Map<Integer, User> startMap = new HashMap<>();
    private final Set<User> startSet = new TreeSet<>(surNameComparator);

    @BeforeEach
    void start() {
        User user1 = new User(20, "Минеев", "Иван");
        User user2 = new User(19, "Шматков", "Артем");
        User user3 = new User(19, "Семенов", "Александр");

        startList.add(user1);
        startList.add(user2);
        startList.add(user3);

        startMap.put(0, user1);
        startMap.put(1, user2);
        startMap.put(2, user3);

        startSet.add(user1);
        startSet.add(user2);
        startSet.add(user3);
    }

    @Test
    void arrayListContractors() {
        List<User> usersArrayListFirst = new ArrayList<>();
        List<User> usersArrayListSecond = new ArrayList<>(24);
        List<User> usersArrayListThird1 = new ArrayList<>(List.of(new User(20, "Минеев", "Иван"), new User(19, "Шматков", "Артем"), new User(19, "Семенов", "Александр")));
        List<User> usersArrayListThird2 = new ArrayList<>(startList);
    }

    @Test
    void addMethodArrayList() {
        User user = new User(25, "Каныгин", "Максим");
        startList.add(user);
        Assertions.assertEquals(user, startList.get(3));
    }

    @Test
    void addWithIndexMethodArrayList() {
        User user = new User(25, "Каныгин", "Максим");
        startList.add(1, user);
        Assertions.assertEquals(user, startList.get(1));
    }

    @Test
    void clearMethodArrayList() {
        startList.clear();
        Assertions.assertEquals(0, startList.size());
    }

    @Test
    void containsMethodArrayList() {
        User user = new User(25, "Каныгин", "Максим");
        Assertions.assertFalse(startList.contains(user));
    }

    @Test
    void getMethodArrayList() {
        Assertions.assertEquals(20, startList.get(0).getAge());
    }

    @Test
    void indexOfMethodArrayList() {
        User user = new User(19, "Шматков", "Артем");
        Assertions.assertEquals(1, startList.indexOf(user));
    }

    @Test
    void isEmptyMethodArrayList() {
        Assertions.assertFalse(startList.isEmpty());
    }

    @Test
    void removeIndexMethodArrayList() {
        User user = new User(19, "Шматков", "Артем");
        startList.remove(0);
        Assertions.assertEquals(user, startList.get(0));
    }

    @Test
    void removeObjectMethodArrayList() {
        User user = new User(19, "Шматков", "Артем");
        User user1 = new User(20, "Минеев", "Иван");
        startList.remove(user1);
        Assertions.assertEquals(user, startList.get(0));
    }

    @Test
    void setMethodArrayList() {
        User user = new User(25, "Каныгин", "Максим");
        startList.set(0, user);
        Assertions.assertEquals(user, startList.get(0));
    }

    @Test
    void sizeMethodArrayList() {
        Assertions.assertEquals(3, startList.size());
    }

    @Test
    void sortMethodArrayList() {
        startList.sort(surNameComparator);
        User user = new User(19, "Шматков", "Артем");
        Assertions.assertEquals(user, startList.get(startList.size()-1));
    }

    @Test
    void toArrayMethodList() {
        User user = new User(19, "Шматков", "Артем");
        Assertions.assertEquals(user, startList.toArray()[1]);
    }

    @Test
    void hashMapContractors() {
        Map<Integer, User> usersHashMapFirst = new HashMap<>();
        Map<Integer, User> usersHashMapSecond = new HashMap<>(24);
        Map<Integer, User> usersHashMapThird = new HashMap<>(24, 0.5f);
        Map<Integer, User> usersHashMapForth = new HashMap<>(startMap);
    }

    @Test
    void clearMethodMap() {
        startMap.clear();
        Assertions.assertEquals(0, startMap.size());
    }

    @Test
    void containsKeyMethodHashMap() {
        Assertions.assertTrue(startMap.containsKey(1));
    }

    @Test
    void containsValueMethodHashMap() {
        User user = new User(19, "Шматков", "Артем");
        Assertions.assertTrue(startMap.containsValue(user));
    }

    @Test
    void entrySetMethodHashMap() {
        Set<Map.Entry<Integer, User>> users = startMap.entrySet();
        Assertions.assertEquals(users, startMap.entrySet());
    }

    @Test
    void getMethodHashMap() {
        User user = new User(20, "Минеев", "Иван");
        Assertions.assertEquals(user, startMap.get(0));
    }

    @Test
    void idEmptyMethodHashMap() {
        Assertions.assertEquals(3, startMap.size());
    }

    @Test
    void keySetMethodHashMap() {
        Set<Integer> keys = Set.of(0,1,2);
        Assertions.assertEquals(keys, startMap.keySet());
    }

    @Test
    void putMethodHashMap() {
        User user = new User(25, "Каныгин", "Максим");
        startMap.put(3, user);

        Assertions.assertEquals(user, startMap.get(3));
    }

    @Test
    void removeMethodHashMap() {
        User user = new User(20, "Минеев", "Иван");
        startMap.remove(0);
        Assertions.assertFalse(startMap.containsValue(user));
    }

    @Test
    void sizeMethodHashMap() {
        Assertions.assertEquals(3, startMap.size());
    }

    @Test
    void valuesMethodHashMap() {
        Assertions.assertArrayEquals(startList.toArray(), startMap.values().toArray());
    }

    @Test
    void treeSetContractors() {
        Comparator<User> surNameComparator = Comparator.comparing(User::getSurName);

        SortedSet<User> users = new TreeSet<>(surNameComparator);

        Set<User> usersSetFirst = new TreeSet<>();
        Set<User> usersSetSecond = new TreeSet<>(surNameComparator);
        Set<User> usersSetThird = new TreeSet<>(startSet); // работает только с компаратором внутри класса, если его не будет будет выброшена ошибка ClassCastException
        Set<User> usersSetForth = new TreeSet<>(users);
    }

    @Test
    void addMethodTreeSet() {
        User user = new User(25, "Каныгин", "Максим");
        startSet.add(user);
        Assertions.assertTrue(startSet.contains(user));
    }

    @Test
    void clearMethodTreeSet() {
        startSet.clear();
        Assertions.assertEquals(0, startSet.size());
    }

    @Test
    void containsMethodTreeSet() {
        User user = new User(20, "Минеев", "Иван");
        Assertions.assertTrue(startSet.contains(user));
    }

    @Test
    void isEmptyMethodTreeSet() {
        Assertions.assertFalse(startSet.isEmpty());
    }

    @Test
    void removeMethodTreeSet() {
        User user = new User(20, "Минеев", "Иван");
        Assertions.assertTrue(startSet.contains(user));
    }

    @Test
    void sizeMethodTreeSet() {
        Assertions.assertEquals(3, startSet.size());
    }

    @Test
    void subSetMethodTreeSet() {
        SortedSet<Integer> integers = new TreeSet<>();
        integers.add(1);
        integers.add(2);
        integers.add(3);
        integers.add(4);
        integers.add(5);
        integers.add(6);
        integers.add(7);

        Assertions.assertEquals(new TreeSet<>(Set.of(3,4,5)), integers.subSet(3,6));
    }

    @Test
    void tailSetMethodTreeSet() {
        SortedSet<Integer> integers = new TreeSet<>();
        integers.add(1);
        integers.add(2);
        integers.add(3);
        integers.add(4);
        integers.add(5);
        integers.add(6);
        integers.add(7);

        Assertions.assertEquals(new TreeSet<>(Set.of(5,6,7)), integers.tailSet(5));
    }

    @Test
    void sortCollectionsMethod() {
        User user1 = new User(20, "Минеев", "Иван");
        User user2 = new User(19, "Шматков", "Артем");
        User user3 = new User(19, "Семенов", "Александр");
        Collections.sort(startList);
        Assertions.assertEquals(List.of(user1, user3, user2), startList);
    }

    @Test
    void minCollectionsMethod() {
        List<Integer> integers = new ArrayList<>(List.of(1,2,3,4,5,6,7,8));
        Assertions.assertEquals(1, Collections.min(integers));
    }

    @Test
    void maxCollectionsMethod() {
        List<Integer> integers = new ArrayList<>(List.of(1,2,3,4,5,6,7,8));
        Assertions.assertEquals(8, Collections.max(integers));
    }

    @Test
    void reverseCollectionMethod() {
        User user = startList.get(0);
        Collections.reverse(startList);
        Assertions.assertEquals(user, startList.get(2));
    }

    @Test
    void unmodifiableListCollectionMethod() {
        List<User> userList = Collections.unmodifiableList(startList);
        Assertions.assertThrows(UnsupportedOperationException.class, () -> userList.add(new User(25, "Каныгин", "Максим")));
    }

    @Test
    void binarySearchCollectionMethod() {
        User user = new User(19, "Шматков", "Артем");
        Collections.sort(startList);
        Assertions.assertEquals(2, Collections.binarySearch(startList, user));
    }

    @Test
    void name() {
        List<User> userList = List.of(new User(25, "Каныгин", "Максим"));
        Assertions.assertTrue(Collections.disjoint(userList, startList));
    }
}
