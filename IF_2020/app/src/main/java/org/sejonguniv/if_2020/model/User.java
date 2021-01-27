package org.sejonguniv.if_2020.model;

import java.lang.reflect.Constructor;
import java.lang.reflect.Parameter;
import java.util.Arrays;
import java.util.List;

public class User {
    String state;
    String generation;
    String name;
    String department;


    public User(String state, String generation, String name, String department) {
        this.state = state;
        this.generation = generation;
        this.name = name;
        this.department = department;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getGeneration() {
        return generation;
    }

    public void setGeneration(String generation) {
        this.generation = generation;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public int itemSize() {
        Class userClass = User.class;
        Constructor[] constructors = userClass.getDeclaredConstructors();
        int parametersSize = 0;
        for (Constructor constructor : constructors) {
            parametersSize = constructor.getParameterCount();

        }
        return parametersSize;
    }

    public String getValue(int input) {

        switch (input) {
            case 0: {
                return state;
            }
            case 1: {
                return generation;
            }
            case 2: {
                return name;
            }
            case 3: {
                return department;
            }
            default: {
                break;
            }
        }
        return "ERROR";
    }
}
