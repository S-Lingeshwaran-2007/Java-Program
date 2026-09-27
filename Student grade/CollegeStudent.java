class CollegeStudent extends Student {

    CollegeStudent(String name, int rollNo, double marks) {
        super(name, rollNo, marks);
    }

    // Method Overriding
    @Override
    public String calculateGrade() {

        double marks = getMarks();

        if (marks >= 90)
            return "A+";
        else if (marks >= 80)
            return "A";
        else if (marks >= 70)
            return "B";
        else if (marks >= 60)
            return "C";
        else if (marks >= 50)
            return "D";
        else
            return "Fail";
    }
}