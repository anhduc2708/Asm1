public class Student {
    private String sId;
    private String sName;
    private String sBd;

    public Student(String sId, String sname, String sbd) {
        this.sId = sId;
        this.sName = sname;
        this.sBd = sbd;
    }

    public String getsId() {
        return sId;
    }

    public String getsName() {
        return sName;
    }

    public String getsBd() {
        return sBd;
    }

    @Override
    public String toString() {
        return "Student{" +
                "SID='" + sId + '\'' +
                ", Sname='" + sName + '\'' +
                ", Sbd='" + sBd + '\'' +
                '}';
    }
}
