public class Course {
    private String cID;
    private String cName;
    private String cCredits;

    public Course(String cID, String cName, String cCredits) {
        this.cID = cID;
        this.cName = cName;
        this.cCredits = cCredits;
    }

    public String getcID() {
        return cID;
    }

    public String getcName() {
        return cName;
    }

    public String getcCredits() {
        return cCredits;
    }

    @Override
    public String toString() {
        return "Course{" +
                "cID='" + cID + '\'' +
                ", cName='" + cName + '\'' +
                ", cCredits='" + cCredits + '\'' +
                '}';
    }
}
