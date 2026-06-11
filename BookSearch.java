import java.util.ArrayList;
import java.util.Scanner;

public class BookSearch {
    public static void main(String[] args) {
        ArrayList<String> books = new ArrayList<>();
        books.add("Java Programming");
        books.add("Data Structures");
        books.add("Operating Systems");
        books.add("Computer Networks");
        books.add("Database Management");

        Scanner sc = new Scanner(System.in);
        System.out.print("Enter keyword: ");
        String keyword = sc.nextLine().toLowerCase();

        for(String book : books) {
            if(book.toLowerCase().contains(keyword))
                System.out.println(book);
        }
    }
}