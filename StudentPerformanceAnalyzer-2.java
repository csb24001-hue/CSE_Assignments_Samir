
import java.util.*;
import java.util.stream.Collectors;

class Student {
    int id;
    String name;
    List<String> courses;
    Map<String, Integer> scores;

    public Student(int id, String name, List<String> courses, Map<String, Integer> scores) {
        this.id = id;
        this.name = name;
        this.courses = courses;
        this.scores = scores;
    }

    public double getAverage() {
        if (scores == null || scores.isEmpty()) return 0.0;
        return scores.values().stream().mapToInt(Integer::intValue).average().orElse(0.0);
    }
}

public class StudentPerformanceAnalyzer {

    // 1. Top N students by average score (descending)
    public static List<Student> getTopNStudents(List<Student> students, int n) {
        return students.stream()
                .sorted(Comparator.comparingDouble(Student::getAverage).reversed())
                .limit(n)
                .collect(Collectors.toList());
    }

    // 2. Average score per course
    public static Map<String, Double> getAverageScorePerCourse(List<Student> students) {
        Map<String, List<Integer>> temp = new HashMap<>();

        for (Student s : students) {
            for (String course : s.courses) {
                int score = s.scores.getOrDefault(course, 0); // required getOrDefault
                temp.computeIfAbsent(course, k -> new ArrayList<>()).add(score);
            }
        }

        return temp.entrySet().stream()
                .collect(Collectors.toMap(
                        Map.Entry::getKey,
                        e -> e.getValue().stream().mapToInt(Integer::intValue).average().orElse(0.0)
                ));
    }

    // 3. All unique courses
    public static Set<String> getAllUniqueCourses(List<Student> students) {
        return students.stream()
                .flatMap(s -> s.courses.stream())
                .collect(Collectors.toCollection(HashSet::new));
    }

    // Demo main
    public static void main(String[] args) {

        List<Student> students = new ArrayList<>();

        Map<String, Integer> scores1 = new HashMap<>();
        scores1.put("Math", 85);
        scores1.put("CS", 90);

        Map<String, Integer> scores2 = new HashMap<>();
        scores2.put("Math", 75);
        scores2.put("CS", 95);

        students.add(new Student(1, "Amit", Arrays.asList("Math", "CS"), scores1));
        students.add(new Student(2, "Riya", Arrays.asList("Math", "CS"), scores2));

        System.out.println("Top Students:");
        getTopNStudents(students, 2).forEach(s -> 
            System.out.println(s.name + " Avg: " + s.getAverage())
        );

        System.out.println("\nAverage per course:");
        System.out.println(getAverageScorePerCourse(students));

        System.out.println("\nUnique courses:");
        System.out.println(getAllUniqueCourses(students));
    }
}

/*
Complexity Analysis:

1. Average score per course:
   Let S = number of students, C = average courses per student
   Time Complexity: O(S * C) to iterate all scores
   Space Complexity: O(C)

2. Sorting top N students:
   Computing average: O(S * C)
   Sorting: O(S log S)
   Total: O(S log S)

*/
