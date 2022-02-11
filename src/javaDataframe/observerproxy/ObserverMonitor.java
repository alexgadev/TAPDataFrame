package javaDataframe.observerproxy;

import java.util.*;

public class ObserverMonitor {
    private List<Observer> observers = new ArrayList<Observer>();

    /**
     * Attach observers
     *
     * @param observer observer
     */
    public void attach(Observer observer){ observers.add(observer); }

    /**
     * Notify every observer of an execution of a method
     *
     * @param args array with the method name and parameters
     */
    public void notifyObservers(Object[] args){
        for(Observer obs : observers){
            obs.update(args);
        }
    }

    /**
     * Show observers results
     */
    public void getState(){
        for(Observer obs : observers){
            obs.getCount();
        }
    }
}
