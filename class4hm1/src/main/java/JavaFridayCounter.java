import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.Period;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class JavaFridayCounter {

    public static void printSortedFriday13(int startYear, int endYear){
        //Set-Up : Initializing Dates and Stream Objects.
        LocalDate start = LocalDate.of(startYear,1,13);
        LocalDate end = LocalDate.of(endYear,1,13);
        Stream<LocalDate> stream = start.datesUntil(end, Period.ofMonths(1));

        //Streaming Dates objects -> filtering by date and DayOfWeek -> changing shape to year -> grouping by year.
        Map<Integer,Long> results = stream
                .filter(x -> x.getDayOfWeek() == DayOfWeek.FRIDAY)
                .map(LocalDate::getYear)
                .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));

        //Streaming the result map by entry Set -> sorting by value (descending order) -> aggregating results to a new map.
        Map<Integer,Long> finalMap =new LinkedHashMap<>();
        results.entrySet()
                .stream()
                .sorted(Map.Entry.<Integer, Long>comparingByValue().reversed())
                .forEach(pair -> finalMap.put(pair.getKey(),pair.getValue()));

        //Printing the final map.
        System.out.println(finalMap);
    }

    public static void main(String[] args) {

        printSortedFriday13(1912,2005);

    }
}

