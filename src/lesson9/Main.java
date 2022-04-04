package lesson9;

import java.util.Collection;
import java.util.Comparator;
import java.util.List;
import java.util.Map;

import static java.util.function.Function.identity;
import static java.util.stream.Collectors.*;

public class Main {

    public static List<Course> getUniqueCourses(List<Student> students) {
        return students.stream()
                .map(Student::getCourses)
                .flatMap(Collection::stream)
                .collect(groupingBy(identity(), counting()))
                .entrySet()
                .stream()
                .filter(entry -> entry.getValue() == 1)
                .map(Map.Entry::getKey)
                .collect(toList());
    }

    public static List<Student> getMostInquisitiveStudents(List<Student> students, int count) {
        return students.stream()
                .sorted(Comparator.<Student>comparingInt(student -> student.getCourses().size()).reversed())
                .limit(count)
                .collect(toList());
    }

    public static List<Student> getCourseStudents(List<Student> students, Course course) {
        return students.stream()
                .filter(student -> student.getCourses().contains(course))
                .collect(toList());
    }

}
