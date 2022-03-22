public class Student {
    private String SID;
    private String Sname;
    private String Sbd;

    public Student(String SID, String sname, String sbd) {
        this.SID = SID;
        this.Sname = sname;
        this.Sbd = sbd;
    }


    public String getSID() {
        return SID;
    }

    public String getSname() {
        return Sname;
    }

    public String getSbd() {
        return Sbd;
    }

    @Override
    public String toString() {
        return "Student{" +
                "SID='" + SID + '\'' +
                ", Sname='" + Sname + '\'' +
                ", Sbd='" + Sbd + '\'' +
                '}';
    }
}
