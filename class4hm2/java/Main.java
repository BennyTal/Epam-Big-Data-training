package class4hm2.java;


import com.google.common.collect.ArrayListMultimap;
import com.google.common.collect.Multimap;
import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

import static java.util.Arrays.asList;

public class Main {

    public static List<Employee> initList(){
        List<Employee> e = new ArrayList<>();
        Random rand = new Random();
        for (int i = 0; i< 10000; i++)
            e.add(new Employee(rand.nextInt(50000) + 6000));
        return e;
    }

    //Exercise 1
    public static Multimap<Seniority, Employee> seniorityMap(List<Employee> emplyees){

        return emplyees.stream()
                .map(x -> asList(x.getSeniority(),x))
                .collect(ArrayListMultimap::create,
                        (m, i) -> m.put((Seniority) i.get(0), (Employee) i.get(1)),
                        Multimap::putAll);
    }

    //Exercise 2
    public static Map<Seniority,Long> seniorityCounter(List<Employee> employees){

        return employees.stream()
                .map(Employee::getSeniority)
                .collect(Collectors.groupingBy(Function.identity(),Collectors.counting()));
    }

    //Exercise 3
    public static Map<Seniority, Integer> seniorityMaxSalary(List<Employee> employees){

        return employees.stream()
                .sorted(Comparator.comparingInt(Employee::getSalary).reversed())
                .collect(Collectors.toMap(Employee::getSeniority
                , Employee::getSalary, (oldValue, newValue) -> oldValue , LinkedHashMap::new));

    }

    public static void main(String[] args) {

        List<Employee> employees = initList();
        System.out.println("Exercise 1: Multimap<Seniority,List<Employees>> = " + seniorityMap(employees).toString());
        System.out.println("Exercise 2: Map<Seniority, Amount of employees for Seniority> = " + seniorityCounter(employees).toString());
        System.out.println("Exercise 3: Map<Seniority, Max Salary for Seniority> = " + seniorityMaxSalary(employees).toString());

     }
}
