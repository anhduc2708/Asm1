import java.awt.*;
import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Scanner;

public class EnrollmentSystem {
    public static ArrayList<Student> studentlist = new ArrayList<Student>();

    public static void menu(){
        System.out.println("\n Enrollment System:");
        System.out.println("**********************");
        System.out.println("1: Add ");
        System.out.println("2: Update");
        System.out.println("3: Delete");
        System.out.println("4: Get One");
        System.out.println("5: Get All");
        System.out.println("0: Exit");
    }
    public static int InputOption() {
        int option;
        Scanner in = new Scanner(System.in);
        System.out.print("Please enter your choice: ");
        option = in.nextInt();
        return option;
    }
    public static void readCSV(String filename){
        try {
            BufferedReader bufferedReader = new BufferedReader(new FileReader(filename));
            String line ="";
            while (( line = bufferedReader.readLine()) != null){
                boolean studentCheck = false;
                boolean CourseCheck = false;
                String[] ele = line.split(",");
                for (Student student : studentlist
                     ) {
                    if (student.getSID().equals(ele[0])){
                        studentCheck = true;break;
                    }
                }
                if(!studentCheck){
                    studentlist.add(new Student(ele[0],ele[1],ele[2]));
                }
            }
            System.out.println(studentlist);
        }

        catch (Exception e){
            e.printStackTrace();
        }

    }
    public static void main(String[] args) {
        menu();
        readCSV("src\\default.csv");
        int opt;;
        do {
            opt = InputOption();
            switch (opt){
                case 1:
                    System.out.println("Add");
                    opt = 0; break;
                case 2:
                    System.out.println("Update");
                    opt = 0; break;
                case 3:
                    System.out.println("Delete");
                    opt = 0; break;
                case 4:
                    System.out.println("Get One");
                    opt = 0; break;
                case 5:
                    System.out.println("Get All");
                    opt = 0; break;
            }
        }while (opt!=0);
    }
}









