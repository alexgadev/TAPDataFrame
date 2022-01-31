package javaDataframe.observerproxy;

import java.util.*;

//TODO: add javadoc

public class ObserverMonitor {
    private List<Observer> observers = new ArrayList<Observer>();

    public void attach(Observer observer){ observers.add(observer); }

    public void notifyObservers(Object[] args){
        for(Observer obs : observers){
            obs.update(args);
        }
    }

    public void getState(){
        for(Observer obs : observers){
            obs.getCount();
        }
    }
}
