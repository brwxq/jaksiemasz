package model;

import java.util.function.Predicate;

public interface IManager {
    void hire(IEmployee employee);
    void fire(IEmployee employee);
    boolean canHire(IEmployee employee);
    void addPreference(Predicate<IEmployee> p);
}
