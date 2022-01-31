package javaDataframe.observerproxy;

//TODO: add javadoc

import java.util.*;

public class LogObserver extends Observer{
    private Map<String, Integer> log = new HashMap<>();

    @Override
    public void update(Object[] name){
        if(log.containsKey(name[0].toString())){
            int n = log.get(name[0].toString());
            log.replace((String) name[0], ++n);
        }
        else{
            log.put((String) name[0], 1);
        }
    }

    public void getCount(){
        log.forEach((k, v) -> System.out.println(k + ": " + v));
    }

    public Map<String, Integer> getLog(){ return log; }
}
