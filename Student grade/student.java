class Student {

    private String name;
    private int rollNo;
    private double marks;

    // Default Constructor
    Student() {
    }

    // Parameterized Constructor
    Student(String name, int rollNo, double marks) {
        this.name = name;
        this.rollNo = rollNo;
        this.marks = marks;
    }

    // Getters
    public String getName() {
        return name;
    }

    public int getRollNo() {
        return rollNo;
    }

    public double getMarks() {
        return marks;
    }

    // Setters
    public void setName(String name) {
        this.name = name;
    }

    public void setRollNo(int rollNo) {
        this.rollNo = rollNo;
    }

    public void setMarks(double marks) {
        this.marks = marks;
    }

    // Method to calculate grade
    public String calculateGrade() {
        return "Grade Not Calculated";
    }

    // Display details
    public void displayDetails() {
        System.out.println("Name       : " + name);
        System.out.println("Roll No    : " + rollNo);
        System.out.println("Marks      : " + marks);
        System.out.println("Grade      : " + calculateGrade());
    }
}