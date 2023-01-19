package cop2805;
import java.util.*;

public class main {

    public static void main(String[] args) {

        ArrayList<Double> list = new ArrayList();
        list.add(1.5); //0
        list.add(2.35); //1
        list.add(-4.7); //2
        list.add(0.01); //3

        System.out.println("Original list:");
        printList(list);
        System.out.println("\n");
        System.out.println("Sorted list:");
        Collections.sort(list);
        printList(list);
        System.out.println("\n");
        System.out.println("Found 1.5 at index " + Collections.binarySearch(list, 1.5) + "\n");
        Collections.fill(list, 0.0);
        System.out.println("Zero list:");
        printList(list);

    }

    static void printList(ArrayList<Double> list) {

        for (int i = 0; i < list.size(); i++) {

            System.out.println(list.get(i));

        }
    }
}
