package model;

import com.google.common.collect.ComparisonChain;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

public class Report{
    private final IEmployee employee;
    private final List<IEmployee> employees;

    public Report(IEmployee employee, List<IEmployee> employees){
        this.employee=employee;
        this.employees=new LinkedList<>(employees);
    }

    public Report(IEmployee employee){
        this.employee=employee;
        this.employees=new LinkedList<>(Collections.singletonList(employee));
    }

    public void printReport() {
        employees.sort((e1,e2)->
                ComparisonChain.start()
                        .compare(e1.getPersonalData().getSurname(),e2.getPersonalData().getSurname())
                        .compare(e1.getPersonalData().getName(),e2.getPersonalData().getName())
                        .compare(e1.getRole(),e2.getRole())
                        .compare(e1.getUnitsOfWork(),e2.getUnitsOfWork()).result());


        employees.forEach(IEmployee::presentWork);

        System.out.println("Units of work: " + employees.stream().mapToInt(IEmployee::getUnitsOfWork).sum());
    }

}
