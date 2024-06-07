package org.example;

public interface DataMonitorListener {
    void state_change(boolean exists);
    void number_of_children_change(int number_of_children);
}
