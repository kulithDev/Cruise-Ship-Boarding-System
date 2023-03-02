import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;
import java.util.Scanner;

public class Main {

    private  static Cabin[] cruise = new Cabin[12];
    private static Scanner input = new Scanner(System.in);
    private static CircularQueue waitingList = new CircularQueue();


    public static void main(String[] args) {
        System.out.println(Arrays.toString(cruise));
        initialise();
        choose_menu();

    }

    private static void initialise( )
    {
        for (int x = 0; x < cruise.length; x++ ) {
            cruise[x] = new Cabin();
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
        System.out.print("Enter Cabin Number: ");
        int cabin_num;
        try {
            Scanner checkScanner = new Scanner(System.in);
            cabin_num = checkScanner.nextInt();
            if (cabin_num < 0 ||cabin_num > 11){
                System.out.println("Enter numbers between 0 and 11");
            }else{
            if (cruise[cabin_num].isFull()){
                if(total() == 6){
                    System.out.println("Cruise ship is full. adding to waiting list.");
                    System.out.print("Enter First Name: ");
                    String first_name = input.next();
                    System.out.print("Enter Last Name: ");
                    String sur_name = input.next();
                    System.out.print("Enter Expenses: ");
                    int expenses = input.nextInt();

                    waitingList.enQueue(new Passenger(first_name,sur_name,expenses));
                    waitingList.show();
                }else{
                    System.out.println("Cabin is full. Choose another cabin.");
                }


            }
            else{
                System.out.print("Enter First Name: ");
                String first_name = input.next();
                System.out.print("Enter Last Name: ");
                String sur_name = input.next();
                System.out.print("Enter Expenses: ");
                int expenses = input.nextInt();

                cruise[cabin_num].addToList(new Passenger(first_name,sur_name,expenses));
                System.out.println(Arrays.toString(cruise[cabin_num].getPassengerList() ));

            }}
        }catch(Exception e) {
            System.out.println("Invalid input.");
        }




    }

    private static void view_cabin() {
        for (int i=0; i < cruise.length; i++){
            System.out.println("Cabin " + i + " has");
            cruise[i].print_passengers();
        }

    }

    private static void empty_cabins(){

        for (int i = 0; i < cruise.length; i++) {
            if (cruise[i].isEmpty()){
                System.out.println("Cabin " + i + " is empty.");
            }
        }
    }

    private static void delete_cabins(){

        System.out.println("Ënter name of passesnger: ");
        String dlname = input.next();

        for (int i = 0; i < cruise.length; i++) {

            if (cruise[i].check(dlname)){
                cruise[i].removepassenger(dlname);
                if (!waitingList.isEmpty()) {
                    cruise[i].addToList(waitingList.deQueue());
                }
            }
        }
    }

    private static void find_cabins(){

        System.out.print("Enter name of passesnger: ");
        String findname = input.next();

        for (int i = 0; i < cruise.length; i++) {

            if (cruise[i].check(findname)){
                System.out.println(findname + " is in cabin " + i);
                break;
            }
        }
    }

    private static void store_file() {
        try {
            FileWriter wf = new FileWriter("./cabinData.txt");
            for (int i = 0; i < cruise.length; i++) {
                for (int j = 0; j < cruise[i].getPassengerList().length; j++) {
                    if (cruise[i].getPassengerList()[j] != null) {
                        wf.write( Integer.valueOf(i) + " " + cruise[i].getPassengerList()[j].getFirstName() + " " + cruise[i].getPassengerList()[j].getSurName() + " " + Integer.valueOf(cruise[i].getPassengerList()[j].getExpenses()));
                        wf.write("\n");
                    }
                }

            }
            wf.close();

        } catch (IOException e) {

            System.out.println("An error occured while writing to file.");
        }
    }

    private static void load_file() {

        System.out.println("Loading data from file");
        try {
            File cruiseDataFile = new File("./cabinData.txt");
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

    }

    private static int total(){

        int num = 0;
        for (int i = 0; i < cruise.length; i++) {

            num += cruise[i].getPassengerListSize();
        }
        return num;
    }

    private static void arrange_names() {

        if (total() > 0) {

            String [] names = new String[total()];
            int pos = 0;
            while (pos < total()) {

                for (int i = 0; i < cruise.length; i++) {

                    for (int j = 0; j < cruise[i].getPassengerList().length; j++) {
                        if (cruise[i].getPassengerList()[j] != null){
                            names[pos] = cruise[i].getPassengerList()[j].getFirstName();
                            pos += 1;
                        }
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

        }
    }


}
