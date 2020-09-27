package com.jarry.demo1.java3y.ProxyMode.ListenerAndFilter;

/**
 * @BelongsProject: demo1
 * @BelongsPackage: com.jarry.demo1.java3y.ProxyMode.ListenerAndFilter
 * @Author: Jarry.Chang
 * @CreateTime: 2020-09-27 17:48
 */
public class Event {

    private Person person;

    public Event(Person person) {
        this.person = person;
    }

    public Event(){}

    public Person getResource(){
        return person;
    }
}
