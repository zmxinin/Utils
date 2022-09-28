package Stream;

import Stream.Person;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * 功能描述
 *
 * @author zmx
 * @date 2022/9/27 15:19
 */

public class Test {

    public static void main(String[] args) {
        List<Person> personList = new ArrayList<Person>();
        personList.add(new Person("Tom", 8900, 23, "male", "New York"));
        personList.add(new Person("Jack", 7000, 25, "male", "Washington"));
        personList.add(new Person("Lily", 7800, 21, "female", "Washington"));
        personList.add(new Person("Anni", 8200, 24, "female", "New York"));
        personList.add(new Person("Owen", 9500, 25, "male", "New York"));
        personList.add(new Person("Alisa", 7900, 26, "female", "New York"));

        // 不改变原来员工集合的方式
        List<Person> personListNew = personList.stream().map(person -> {
            Person personNew = new Person(person.getName(), 0, 0, null, null);
            personNew.setSalary(person.getSalary() + 10000);
            return personNew;
        }).collect(Collectors.toList());
        System.out.println("一次改动前：" + personList.get(0).getName() + "-->" + personList.get(0).getSalary());
        System.out.println("一次改动后：" + personListNew.get(0).getName() + "-->" + personListNew.get(0).getSalary());

        // 改变原来员工集合的方式
        List<Person> personListNew2 = personList.stream().map(person -> {
            person.setSalary(person.getSalary() + 10000);
            return person;
        }).collect(Collectors.toList());
        System.out.println("二次改动前：" + personList.get(0).getName() + "-->" + personList.get(0).getSalary());
        System.out.println("二次改动后：" + personListNew2.get(0).getName() + "-->" + personListNew.get(0).getSalary());

//        List<String> list = Arrays.asList("m,k,l,a", "1,3,5,7");
//        List<String> listNew = list.stream().flatMap(s -> {
//            // 将每个元素转换成一个stream
//            String[] split = s.split(",");
//            Stream<String> s2 = Arrays.stream(split);
//            return s2;
//        }).collect(Collectors.toList());
//
//        System.out.println("处理前的集合：" + list);
//        System.out.println("处理后的集合：" + listNew);

    }

}
