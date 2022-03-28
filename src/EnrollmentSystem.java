import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class EnrollmentSystem {
    public static ArrayList<Student> studentList = new ArrayList<Student>();
    public static ArrayList<Course> courseList = new ArrayList<Course>();
    public static ArrayList<StudentEnrolment> studentEnrolmentsList = new ArrayList<>();
    static Scanner in = new Scanner(System.in);

    public static void menu() {
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

    public static String readCSV() {
        boolean done = false;
        String fileName = "d";
        StudentEnrolment se;
        Student s ;
        Course c ;

        while (!done) {
            System.out.print("Please input your file's name or press d to open the default file: ");
            fileName = in.nextLine();
            String defaultname = "src\\default.csv";
            if (!fileName.equals("d")) {
                defaultname = "src\\" + fileName;
            }
            try {
                BufferedReader bufferedReader = new BufferedReader(new FileReader(defaultname));
                String line = "";
                while ((line = bufferedReader.readLine()) != null) {
                    boolean studentCheck = false;
                    boolean courseCheck = false;
                    String[] ele = line.split(",");
                    for (Student student : studentList
                    ) {
                        if (student.getsId().equals(ele[0])) {
                            studentCheck = true;
                            break;
                        }
                    }
                    if (!studentCheck) {
                        s = new Student(ele[0], ele[1], ele[2]);
                        studentList.add(s);
                    }
                    for (Course course : courseList
                    ) {
                        if (course.getcID().equals(ele[3])) {
                            courseCheck = true;
                            break;
                        }
                    }
                    if (!courseCheck) {
                        c = new Course(ele[3], ele[4], ele[5]);
                        courseList.add(c);
                    }
                    se = new StudentEnrolment(new Student(ele[0], ele[1], ele[2]), new Course(ele[3], ele[4], ele[5]), ele[6]);
                    studentEnrolmentsList.add(se);
                }
                System.out.println(studentList);
                System.out.println(courseList);
                System.out.println(studentEnrolmentsList);
                done = true;
            } catch (Exception e) {
                System.out.println(e);
                done = false;
            }
        }
        return fileName;
    }

    public static boolean sIdCheck(String sId) {
        boolean check = true;
        for (StudentEnrolment studentEnrolment : studentEnrolmentsList
        ) {
            if (studentEnrolment.getStudent().getsId().equals(sId)) {
               check = false;
                }
            break;
            }
        return check;
    }

    public static boolean cIdCheck(String cId) {
        boolean check = true;
        for (StudentEnrolment studentEnrolment : studentEnrolmentsList
        ) {
            if (studentEnrolment.getCourse().getcID().equals(cId)){
                    check = false;
                }
            break;
        }
        return check;
    }

    public static int Index(String sId, String cId){
        int i = 0;
        for (StudentEnrolment s: studentEnrolmentsList
             ) {
            if (s.getStudent().getsId().equals(sId) && s.getCourse().getcID().equals(cId)){
                i= i+1;
            }
        }
        return i;
    }

    // CRUD functions
    public static void add() {
        String studentResult = null;
        String courseResult = null;
        Student s = null;
        Course c = null;
        String semResul ;
        while (true) {
            do {
                System.out.print("Please input your ID: ");
                String input1 = in.nextLine();
                for (Student student : studentList
                ) {
                    if (student.getsId().equalsIgnoreCase(input1)) {
                        studentResult = input1;
                         s = student;
                        System.out.println("Your information: ");
                        for (StudentEnrolment se: studentEnrolmentsList
                        ) {
                            if (se.getStudent().getsId().equals(studentResult)){
                                System.out.println(se.getStudent().getsId()+ ": " +se.getStudent().getsName() +", Course: "+ se.getCourse().getcID()+" "+se.getCourse().getcName());
                            }
                        }
                    }
                }
                if (studentResult == null) {
                    System.out.println("Invalid student ID!");
                }

            } while (studentResult == null);
            do {
                System.out.print("Please input the course ID: ");
                String input2 = in.nextLine();
                for (Course course : courseList
                ) {
                    if (course.getcID().equalsIgnoreCase(input2)) {
                        courseResult = input2;
                        c = course;

                    }
                }
                if (courseResult == null) {
                    System.out.println("Invalid course ID!");
                }
                for (StudentEnrolment se: studentEnrolmentsList
                ) {
                    if (se.getCourse().getcID().equals(courseResult) && se.getStudent().getsId().equals(studentResult)){
                        System.out.println("You are already enrolled this course!");
                        courseResult = null;
                    }
                }
            } while (courseResult == null);
            do {
                System.out.print("Please input the semester(ex: 2022A): ");
                String input3 = in.nextLine();
                String regex = "202[0-5][A-C]";
                Pattern input = Pattern.compile(regex);
                Matcher matcher = input.matcher(input3);
                if (matcher.find()) {
                    semResul = input3;
                    System.out.println("sem input ok");
                }
                else {
                    System.out.println("Invalid Semester!");
                    semResul = null;
                }
            } while (semResul == null);
            StudentEnrolment se = new StudentEnrolment(s,c,semResul);
            studentEnrolmentsList.add(se);
            System.out.println(studentEnrolmentsList);
        }
        }

    public static void delete(){
        String studentResult = null;
        String courseResult = null;
        while (true) {
            do {
                System.out.print("Please input your ID: ");
                String input1 = in.nextLine();
                for (Student student : studentList
                ) {
                    if (student.getsId().equalsIgnoreCase(input1)) {
                        studentResult = input1;
                    }
                }
                if (studentResult == null) {
                    System.out.println("Invalid student ID!");
                }

            } while (studentResult == null);
            do {
                System.out.print("Please input the course ID: ");
                String input2 = in.nextLine();
                for (Course course : courseList
                ) {
                    if (course.getcID().equalsIgnoreCase(input2)) {
                        courseResult = input2;
                    }
                }
                if (courseResult == null) {
                    System.out.println("Invalid course ID!");
                }
            } while (courseResult == null);
                studentEnrolmentsList.remove(Index(studentResult, courseResult));
                System.out.println("Delete complete!");
            }
        }

    public static void update(){
        String studentResult = null;
        do {
            System.out.print("Please input your ID: ");
            String input1 = in.nextLine();
            for (Student student : studentList
            ) {
                if (student.getsId().equalsIgnoreCase(input1)) {
                    studentResult = input1;
                }
            }
            if (studentResult == null) {
                System.out.println("Invalid student ID!");
            }
        } while (studentResult == null);
        System.out.println("Your information: ");
        for (StudentEnrolment s: studentEnrolmentsList
             ) {
            if (s.getStudent().getsId().equals(studentResult)){
            System.out.println(s.getStudent().getsId()+ ": " +s.getStudent().getsName() +", Course: "+ s.getCourse().getcID()+" "+s.getCourse().getcName());
        }
        }
        System.out.println("""
                Select an option:\s
                1. Add
                2. Delete""");
        int input;
        do {
            input = InputOption();
            switch (input) {
                case 1 -> add();
                case 2 -> delete();
            }
    }  while (input!=0);
    }

    public static void main(String[] args) {
        String filename = readCSV();
        int opt;
        do {
            menu();
            opt = InputOption();
            switch (opt) {
                case 0 -> System.out.println("Exit");
                case 1 -> add();
                case 2 -> update();
                case 3 -> delete();
                case 4 -> {
                    System.out.println("Get One");
                    opt = 0;
                }
                case 5 -> {
                    System.out.println("Get All");
                    opt = 0;
                }
            }
        }while (opt!=0);
    }
}








