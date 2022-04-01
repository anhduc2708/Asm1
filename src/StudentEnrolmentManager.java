import java.io.IOException;

public interface StudentEnrolmentManager {


    void add();
    void update();
    void delete();
    void getOne()throws IOException;
    void getAll();
}
