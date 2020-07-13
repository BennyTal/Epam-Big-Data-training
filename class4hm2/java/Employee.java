package class4hm2.java;

public class Employee {
    private Integer salary;

    public Employee(int salary){
        this.salary = salary;
    }

    public Integer getSalary() {
        return salary;
    }

    public void setSalary(int salary) {
        this.salary = salary;
    }

    @Override
    public String toString() {
        return "Emplyee{" +
                "salary=" + salary +
                '}';
    }

    public Seniority getSeniority(){
        if(this.salary > 25000)
            return Seniority.Sinior;
        else if(this.salary > 20000)
            return Seniority.Middle;
        else
            return Seniority.Junior;
    }
}
