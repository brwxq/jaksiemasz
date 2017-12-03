package model;

public interface IManager {
    void hire(IEmployee employee);
    void fire(IEmployee employee);
    boolean canHire();
}
