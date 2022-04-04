import java.io.*;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class EnrollmentSystem implements StudentEnrolmentManager {

    //I created 3 array lists to store 3 categories of data
    public static ArrayList<Student> studentList = new ArrayList<Student>();
    public static ArrayList<Course> courseList = new ArrayList<Course>();
    public static ArrayList<StudentEnrolment> studentEnrolmentsList = new ArrayList<>();
    static Scanner in = new Scanner(System.in);

    // A menu function to print out the menu
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

    // A function to get the input option of users
    public static int InputOption() {
        int option;
        Scanner in = new Scanner(System.in);
        System.out.print("Please enter your choice: ");
        option = in.nextInt();
        return option;
    }

    // Readfile function in order to read through the line and also take the element in those data to store in the 3 array lists for later use
    public static String readCSV() {
        boolean done = false;
        String fileName = "d";
        StudentEnrolment se;
        Student s;
        Course c;

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
        }
        return check;
    }

    public static boolean cIdCheck(String cId) {
        boolean check = true;
        for (StudentEnrolment studentEnrolment : studentEnrolmentsList
        ) {
            if (studentEnrolment.getCourse().getcID().equals(cId)) {
                check = false;
            }
        }
        return check;
    }

    // Random number function to set the file name while file name exist but users don't want to override
    public static int randomNum(int min, int max) {
        Random random = new Random();
        return random.nextInt(max - min) + min;
    }

    public static int printOutInfo() {
        System.out.printf("%-20s", "STUDENT ID");
        System.out.printf("%20s", "STUDENT NAME");
        System.out.printf("%20s", "COURSE ID");
        System.out.printf("%40s", "COURSE NAME");
        System.out.printf("%20s", "SEMESTER");
        System.out.printf("%10s", "CREDITS" + "\n");
        return 0;
    }

    // The index function will find the index of the line that have data valid with the input from user
    public static int Index(String sId, String cId) {
        int i = 0;
        for (StudentEnrolment s : studentEnrolmentsList
        ) {
            if (s.getStudent().getsId().equals(sId) && s.getCourse().getcID().equals(cId)) {
                i = i + 1;
            }
        }
        return i;
    }

    // A function to check if the file exist
    public static boolean isFileExist(String name) {
        String fileNameNew = "src/" + name + ".csv";
        boolean check = false;
        File filename = new File(fileNameNew);
        if (filename.exists()) {
            check = true;
        }
        return check;
    }

    // This function will use the isFileExist function then save file
    public static void fileSaving(String name, List data) throws IOException {
        File fileName = new File("src" + "\\" + name + ".csv");
        File file = new File(String.valueOf(fileName));
        boolean fileExist = isFileExist(String.valueOf(fileName));
        System.out.println(file);
        System.out.println(fileExist);

        if (!fileExist) {
            System.out.println("""
                    File exist! Do you want to override?
                    [1] Yes, [2] No
                    Your options is:\s""");

            List sortedData;
            sortedData = data;

            if (InputOption() == 1) {
                FileWriter fileWriter = new FileWriter(file, true);
                fileWriter.write("STUDENT ID");
                fileWriter.write("STUDENT NAME");
                fileWriter.write("COURSE ID");
                fileWriter.write("COURSE NAME");
                fileWriter.write("SEMESTER");
                fileWriter.write("CREDITS");

                fileWriter.write(String.valueOf(sortedData));
                fileWriter.flush();
                fileWriter.close();
                System.out.println("Saving completely!");
            } else {
                name = name + randomNum(1, 50);
                File newFile = new File("src" + "\\" + name + ".csv");
                FileWriter fileWriter = new FileWriter(newFile, true);

                fileWriter.write(String.valueOf(sortedData));
                fileWriter.flush();
                fileWriter.close();
                System.out.println("Save successfully file " + newFile + "!");
            }
        } else {
            FileWriter fileWriter = new FileWriter(file, true);
            fileWriter.write(String.valueOf(data));
            fileWriter.flush();
            fileWriter.close();
            System.out.println("Saving completely!");
        }

    }


    // CRUD functions
    @Override
    public void add() {
        String studentResult = null;
        String courseResult = null;
        Student s = null;
        Course c = null;
        String semResult;
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
                        for (StudentEnrolment se : studentEnrolmentsList
                        ) {
                            if (se.getStudent().getsId().equals(studentResult)) {
                                System.out.println(se.getStudent().getsId() + ": " + se.getStudent().getsName() + ", Course: " + se.getCourse().getcID() + " " + se.getCourse().getcName());
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
                for (StudentEnrolment se : studentEnrolmentsList
                ) {
                    if (se.getCourse().getcID().equals(courseResult) && se.getStudent().getsId().equals(studentResult)) {
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
                    semResult = input3;
                    System.out.println("sem input ok");
                } else {
                    System.out.println("Invalid Semester!");
                    semResult = null;
                }
            } while (semResult == null);
            StudentEnrolment se = new StudentEnrolment(s, c, semResult);
            studentEnrolmentsList.add(se);
            System.out.println(studentEnrolmentsList);
        }
    }

    @Override
    public void delete() {
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

    @Override
    public void update() {
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
        for (StudentEnrolment s : studentEnrolmentsList
        ) {
            if (s.getStudent().getsId().equals(studentResult)) {
                System.out.println(s.getStudent().getsId() + ": " + s.getStudent().getsName() + ", Course: " + s.getCourse().getcID() + " " + s.getCourse().getcName());
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
        } while (input != 0);
    }

    @Override
    public void getOne() throws IOException {
        List<String> data = new ArrayList<>();
        String dataInput = null;
        System.out.println("""
                [1] Get a course from a semester.
                [2] Get a student from a semester.
                [3] Get a student from a course.\s""");
        int option;
        do {
            option = InputOption();
            switch (option) {
                case 1 -> {
                    String courseResult = null;
                    String semResult;
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
                    do {
                        System.out.print("Please input the semester(ex: 2022A): ");
                        String input3 = in.nextLine();
                        String regex = "202[0-5][A-C]";
                        Pattern input = Pattern.compile(regex);
                        Matcher matcher = input.matcher(input3);
                        if (matcher.find()) {
                            semResult = input3;
                            System.out.println("sem input ok");
                        } else {
                            System.out.println("Invalid Semester!");
                            semResult = null;
                        }
                    } while (semResult == null);
                    printOutInfo();
                    for (StudentEnrolment se : studentEnrolmentsList
                    ) {
                        if (se.getSemester().equals(semResult) && se.getCourse().getcID().equals(courseResult)) {
                            System.out.printf("%-20s", se.getStudent().getsId());
                            System.out.printf("%20s", se.getStudent().getsName());
                            System.out.printf("%20s", se.getCourse().getcID());
                            System.out.printf("%40s", se.getCourse().getcName());
                            System.out.printf("%20s", se.getSemester());
                            System.out.printf("%10s", se.getCourse().getcCredits() + "\n");
                            data.add(("\n"));
                            dataInput = se.getStudent().getsId() + "," + se.getStudent().getsName() + "," + se.getCourse().getcID() + "," + se.getCourse().getcName() + "," + se.getSemester() + "," + se.getCourse().getcCredits();
                            data.add((dataInput));
                        }
                    }
                    System.out.println("""
                            Do you want to save this file?
                            [1] Yes
                            [2] No""");
                    int choice = InputOption();
                    if (choice == 1) {
                        System.out.println("Please input the file name: ");
                        String input = in.nextLine();
                        fileSaving(input, data);
                    } else {
                        break;
                    }
                }

                case 2 -> {
                    String studentResult = null;
                    String semResult;
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
                        System.out.print("Please input the semester(ex: 2022A): ");
                        String input3 = in.nextLine();
                        String regex = "202[0-5][A-C]";
                        Pattern input = Pattern.compile(regex);
                        Matcher matcher = input.matcher(input3);
                        if (matcher.find()) {
                            semResult = input3;
                            System.out.println("sem input ok");
                        } else {
                            System.out.println("Invalid Semester!");
                            semResult = null;
                        }
                    } while (semResult == null);
                    printOutInfo();
                    for (StudentEnrolment se : studentEnrolmentsList
                    ) {
                        if (se.getSemester().equals(semResult) && se.getStudent().getsId().equals(studentResult)) {
                            System.out.printf("%-20s", se.getStudent().getsId());
                            System.out.printf("%20s", se.getStudent().getsName());
                            System.out.printf("%20s", se.getCourse().getcID());
                            System.out.printf("%40s", se.getCourse().getcName());
                            System.out.printf("%20s", se.getSemester());
                            System.out.printf("%10s", se.getCourse().getcCredits() + "\n");
                            data.add(("\n"));
                            dataInput = se.getStudent().getsId() + "," + se.getStudent().getsName() + "," + se.getCourse().getcID() + "," + se.getCourse().getcName() + "," + se.getSemester() + "," + se.getCourse().getcCredits();
                            data.add((dataInput));
                        }
                    }
                    System.out.println("""
                            Do you want to save this file?
                            [1] Yes
                            [2] No""");
                    int choice = InputOption();
                    if (choice == 1) {
                        System.out.println("Please input the file name: ");
                        String input = in.nextLine();
                        fileSaving(input, data);
                    } else {
                        break;
                    }
                }

                case 3 -> {
                    String studentResult = null;
                    String courseResult = null;
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
                    printOutInfo();
                    for (StudentEnrolment se : studentEnrolmentsList
                    ) {
                        if (se.getStudent().getsId().equals(studentResult) && se.getCourse().getcID().equals(courseResult)) {
                            System.out.printf("%-20s", se.getStudent().getsId());
                            System.out.printf("%20s", se.getStudent().getsName());
                            System.out.printf("%20s", se.getCourse().getcID());
                            System.out.printf("%40s", se.getCourse().getcName());
                            System.out.printf("%20s", se.getSemester());
                            System.out.printf("%10s", se.getCourse().getcCredits() + "\n");
                            data.add(("\n"));
                            dataInput = se.getStudent().getsId() + "," + se.getStudent().getsName() + "," + se.getCourse().getcID() + "," + se.getCourse().getcName() + "," + se.getSemester() + "," + se.getCourse().getcCredits();
                            data.add((dataInput));
                        }
                    }
                    System.out.println("""
                            Do you want to save this file?
                            [1] Yes
                            [2] No""");
                    int choice = InputOption();
                    if (choice == 1) {
                        System.out.println("Please input the file name: ");
                        String input = in.nextLine();
                        fileSaving(input, data);
                    } else {
                        break;
                    }
                }
            }
        } while (option != 0);
    }

    @Override
    public void getAll() throws IOException {
        printOutInfo();
        List<String> data = new ArrayList<>();
        String dataInput = null;
        for (StudentEnrolment se : studentEnrolmentsList
        ) {
            System.out.printf("%-20s", se.getStudent().getsId());
            System.out.printf("%20s", se.getStudent().getsName());
            System.out.printf("%20s", se.getCourse().getcID());
            System.out.printf("%40s", se.getCourse().getcName());
            System.out.printf("%20s", se.getSemester());
            System.out.printf("%10s", se.getCourse().getcCredits() + "\n");
            data.add(("\n"));
            dataInput = se.getStudent().getsId() + "," + se.getStudent().getsName() + "," + se.getCourse().getcID() + "," + se.getCourse().getcName() + "," + se.getSemester() + "," + se.getCourse().getcCredits();
            data.add((dataInput));
        }
        System.out.println("""
                Do you want to save this file?
                [1] Yes
                [2] No""");
        int choice = InputOption();
        if (choice == 1) {
            System.out.println("Please input the file name: ");
            String input = in.nextLine();
            fileSaving(input, data);
        }
    }

    public static void main(String[] args) throws IOException {
        EnrollmentSystem system = new EnrollmentSystem();
        String filename = readCSV();
        int opt;
        do {
            menu();
            opt = InputOption();
            switch (opt) {
                case 0 -> System.out.println("Exit");
                case 1 -> system.add();
                case 2 -> system.update();
                case 3 -> system.delete();
                case 4 -> system.getOne();
                case 5 -> system.getAll();
            }
        }while (opt!=0);
    }


}







