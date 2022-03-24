import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Scanner;

public class EnrollmentSystem {
    public static ArrayList<Student> studentList = new ArrayList<Student>();
    public static ArrayList<Course> courseList = new ArrayList<Course>();
    public static ArrayList<StudentEnrolment> studentEnrolmentsList = new ArrayList<>();
    static Scanner in =new Scanner(System.in);
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
    public static String readCSV(){
        boolean done = false;
        String fileName = "d";
        StudentEnrolment se;
        Student s =null;
        Course c = null;

        while (!done) {
            System.out.println("Please input your file's name or press d to open the default file: ");
            fileName = in.nextLine();
            String defaultname = "src\\default.csv";
            if (!fileName.equals("d")){
                defaultname = "src\\" + fileName;
            }
            try {
                BufferedReader bufferedReader = new BufferedReader(new FileReader(defaultname));
                String line ="";
                while (( line = bufferedReader.readLine()) != null){
                    boolean studentCheck = false;
                    boolean courseCheck = false;
                    String[] ele = line.split(",");
                    for (Student student : studentList
                    ) {
                        if (student.getSID().equals(ele[0])){
                            studentCheck = true;break;
                        }
                    }
                    if(!studentCheck){
                        s = new Student(ele[0],ele[1],ele[2]);
                        studentList.add(s);
                    }
                    for (Course course : courseList
                         ) {
                        if (course.getcID().equals(ele[3])){
                            courseCheck = true;break;
                        }
                    }
                    if(!courseCheck){
                        c = new Course(ele[3], ele[4], ele[5]);
                        courseList.add(c);
                    }
                    se = new StudentEnrolment(s,c, ele[6]);
                    studentEnrolmentsList.add(se);
                }
                System.out.println(studentList);
                System.out.println(courseList);
                System.out.println(studentEnrolmentsList);
                done = true;
            } catch (Exception e){
                System.out.println(e);
                done = false;
            }
        }
        return fileName;
    }

    public static void main(String[] args) {
        String filename = readCSV();
        menu();

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









