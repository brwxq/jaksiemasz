package model;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.text.SimpleDateFormat;
import java.util.Date;

@Getter
@ToString(exclude = "dateFormat")
public class Task {
    private String title;
    private @Setter Status status = Status.CREATED;
    private Date assignedDate;
    private Date deadlineDate;
    private int unitsOfWork;

    private SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");

    @Builder
    private Task(int unitsOfWork, String title, Date deadlineDate) {
        this.unitsOfWork = unitsOfWork;
        this.title = title;
        this.deadlineDate = deadlineDate;
        this.assignedDate = new Date();
    }

    public enum Status{
        CREATED,
        ASSIGNED,
        IN_PROGRESS,
        COMPLETED
    }

}
