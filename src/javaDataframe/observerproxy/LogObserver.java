package javaDataframe.observerproxy;

import java.util.*;

public class LogObserver extends Observer{
    private Map<String, Integer> log = new LinkedHashMap<>();

    /**
     * Increase number of usages of an operation
     *
     * @param name array with name of function used
     */
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

    /**
     * Show number of times an operation has been used
     */
    public void getCount(){
        log.forEach((k, v) -> System.out.println(k + ": " + v));
    }

    public Map<String, Integer> getLog(){ return log; }
}
