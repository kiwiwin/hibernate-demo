package org.kiwi.hibernatedemo;

import java.util.*;

public class Person {
    private Integer id;
    private String name;
    private Set notes = new HashSet();

    public Person() {
    }

    public Integer getId() {
        return id;
    }

    private void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set getNotes() {
        return notes;
    }

    public void setNotes(Set notes) {
        this.notes = notes;
    }
}
