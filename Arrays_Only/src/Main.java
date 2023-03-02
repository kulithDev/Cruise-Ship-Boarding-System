import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;

public class Main {

    private static String[] cruise = new String[12];
    private static Scanner input = new Scanner(System.in);
    private static String name;
    private static  int roomNum = 0;

        public static void main(String[] args)
        {
            System.out.println(Arrays.toString(cruise));
            initialise();
            choose_menu();


        }

        private static void initialise( )
        {
            for (int x = 0; x < 12; x++ ) {
                cruise[x] = "e";
            }
        }

        private static void view_menu(){
            System.out.println("Enter 'A' to add a customer to a cabin");
            System.out.println("Enter 'V' to to view all cabins");
            System.out.println("Enter 'E' to Display Empty cabins");
            System.out.println("Enter 'D' to Delete customer from cabin");
            System.out.println("Enter 'F' to Find cabin from customer name");
            System.out.println("Enter 'S' to Store program data into file");
            System.out.println("Enter 'L' to Load program data from file");
            System.out.println("Enter 'O' to View passengersOrdered alphabetically by name");
            System.out.println("Enter 'Q' to Quit");
        }

        private static void choose_menu(){

            String input1 = "";
            while (!input1.equals("Q")) {
                view_menu();
                System.out.println("");
                System.out.print("Choose from the menu: ");
                input1 = input.next();

                if (input1.equals("A")) {
                    add_customer();
                }

                else if (input1.equals("V")) {
                    view_cabin();
                }

                else if (input1.equals("E")) {
                    empty_cabins();
                }

                else if (input1.equals("E")) {
                    empty_cabins();
                }

                else if (input1.equals("D")) {
                    delete_cabins();
                }

                else if (input1.equals("F")) {
                    find_cabins();
                }

                else if (input1.equals("S")) {
                    store_file();
                }

                else if (input1.equals("L")) {
                    load_file();
                }

                else if (input1.equals("O")) {
                    arrange_names();
                }

                else{
                    if (!input1.equals("Q")) {
                        System.out.println("Invalid input.");
                    }
                }

            }
        }

        private static void add_customer() {

            System.out.print("Input the cabin number: ");
            int cabin_num;
            try {
                Scanner checkScanner = new Scanner(System.in);
                cabin_num = checkScanner.nextInt();
                if (cabin_num < 0 ||cabin_num > 11){
                    System.out.println("Enter numbers between 0 and 11");

                } else{
                if (cruise[cabin_num].equals("e")) {
                    System.out.print("Input the customer name: ");
                    String cus_name = checkScanner.next();
                    cruise[cabin_num] = cus_name;
                    System.out.println(cus_name + " is added to cabin " + cabin_num);
                }
                else {
                    System.out.println("Cabin is already reserved");

                }
                }
            }
            catch(Exception e){
                System.out.println("Invalid input.");

            }

        }

        private static void view_cabin() {
            for (int i = 0; i < cruise.length; i++) {

                if (!cruise[i].equals("e")) {
                    System.out.println("Cabin " + i + " is occupied by " + cruise[i]);
                }

                if (cruise[i].equals("e")) {
                    System.out.println("Cabin " + i + " is empty");
                }
            }
            System.out.println("");
        }

        private static void empty_cabins(){
            for (int j = 0; j < cruise.length; j++) {
                if (cruise[j].equals("e")) {
                    System.out.println("Cabin " + j + " is empty");
                }
            }

            System.out.println("");
        }

        private static void delete_cabins(){
            System.out.print("Enter the customer you want to delete: ");
            String del_cus = input.next();
            boolean found_name = false;
            for(int i=0; i < cruise.length; i++){
                if(cruise[i].equals(del_cus)){
                    found_name = true;
                    System.out.println(cruise[i] + " was deleted");
                    cruise[i] = "e";
                    break;
                }
            }
            if (!found_name) {
                System.out.println("Customer does not exists");
            }
            System.out.println("");
        }

    private static void find_cabins(){
        System.out.print("Enter the customer you want to find: ");
        String find_cus = input.next();
        boolean found_name = false;
        for(int i=0; i < cruise.length; i++){
            if(cruise[i].equals(find_cus)){
                found_name = true;
                System.out.println("Cabin number of " + find_cus + " is " + i);
            }
        }
        if (!found_name){
            System.out.println("Customer does not exists");
        }
        System.out.println("");
    }

    private static void store_file() {

        try {
            FileWriter wf = new FileWriter("./cruiseData.txt");
            for (int i = 0; i < cruise.length; i++) {

                wf.write(cruise[i]);
                wf.write("\n");
            }
            wf.close();

        } catch (IOException e) {

            e.printStackTrace();
            System.out.println("An error occurred while writing to file.");
        }

        System.out.println("Saved to file");
        System.out.println("");
    }

    private static void load_file() {
        System.out.println("Loading data from file");
        try {
            File cruiseDataFile = new File("./cruiseData.txt");
            Scanner rf = new Scanner(cruiseDataFile);
            int i = 0;
            while (rf.hasNext()) {

                String file_line = rf.nextLine();
                System.out.println(file_line);

            }
            rf.close();

        } catch (IOException e) {

            System.out.println("No existing data is found");
        }
        System.out.println("");

    }

    private static int size(){

        int num = 0;
        for (int i = 0; i < cruise.length; i++) {

            if (!cruise[i].equals("e")) {
                num += 1;
            }
        }
        return num;
    }

    private static void arrange_names() {

        if (size() > 0) {

            String [] names = new String[size()];
            int pos = 0;
            while (pos < size()) {

                for (int i = 0; i < cruise.length; i++) {

                    if (!cruise[i].equals("e")) {
                        names[pos] = cruise[i];
                        pos += 1;
                    }
                }
            }
            System.out.println(Arrays.toString(names));

            //Sorting Algorithm
            for (int i = names.length-1;  i > 0; i--) {

                for (int j = 0; j < i; j++) {

                    int n = names[j].compareTo(names[j+1]);
                    // System.out.println(n);
                    if (n > 0) {

                        String temp = names[j];
                        names[j] = names[j+1];
                        names[j+1] = temp;
                    }
                }
            }
            // System.out.println(Arrays.toString(name_set));
            for (int i = 0; i < names.length; i++) {
                System.out.println(names[i]);
            }
        }else{

            System.out.println("No data to sort");
        }

        System.out.println("");

    }


}




