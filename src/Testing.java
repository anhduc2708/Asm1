import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class Testing {

    private static EnrollmentSystem studentEnrolmentList;

    @BeforeEach
    public void setUp(){
        studentEnrolmentList = new EnrollmentSystem();
    }

    @Test
    public void testEnrollIdToAdd(){
        Student studentResult = new Student("s3818988","Duc","27/08/2001");
        Course courseResult = new Course("COSC2440","Further programming","12");
        String sem = "2021A";
        studentEnrolmentList.equals( new StudentEnrolment(studentResult,courseResult,sem));
        assertEquals("s3818988", studentResult.getsId());

    }
    @Test
    public void testEnrollStudentNameToAdd(){
        Student studentResult = new Student("s3818988","Duc","27/08/2001");
        Course courseResult = new Course("COSC2440","Further programming","12");
        String sem = "2021A";
        studentEnrolmentList.equals( new StudentEnrolment(studentResult,courseResult,sem));
        assertEquals("Duc", studentResult.getsName());
    }
    @Test
    public void testEnrollCourseNameToAdd(){
        Student studentResult = new Student("s3818988","Duc","27/08/2001");
        Course courseResult = new Course("COSC2440","Further programming","12");
        String sem = "2021A";
        studentEnrolmentList.equals( new StudentEnrolment(studentResult,courseResult,sem));
        assertEquals("Further programming", courseResult.getcName());
    }
    @Test
    public void testEnrollCourseNameToUpdate(){
        Student studentResult = new Student("s3818988","Duc","27/08/2001");
        Course courseResult = new Course("COSC2440","Further programming","12");
        String sem = "2021A";
        studentEnrolmentList.equals( new StudentEnrolment(studentResult,courseResult,sem));
        assertEquals("Further programming", courseResult.getcName());
    }
}