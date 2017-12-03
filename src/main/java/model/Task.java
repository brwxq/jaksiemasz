package model;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Task {
    private int unitsOfWork;
    private String title;
    private Date deadlineDate;
    private Date assignedDate;
    private Status status = Status.CREATED;

    private SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");

    public Task(int unitsOfWork, String title, Date deadlineDate) {
        this.unitsOfWork = unitsOfWork;
        this.title = title;
        this.deadlineDate = deadlineDate;
        this.assignedDate = new Date();
    }

    public int getUnitsOfWork() {
        return unitsOfWork;
    }

    public String getTitle() {
        return title;
    }

    public Date getDeadlineDate() {
        return deadlineDate;
    }

    public Date getAssignedDate() {
        return assignedDate;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "\"" + title + "\" " + "deadline: " + dateFormat.format(deadlineDate)+
                " status: " + status + " " + " units: " + unitsOfWork;
    }

    public enum Status{
        CREATED,
        ASSIGNED,
        IN_PROGRESS,
        COMPLETED
    }
}
