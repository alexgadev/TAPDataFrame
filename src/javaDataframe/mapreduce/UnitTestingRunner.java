package javaDataframe.mapreduce;

import org.junit.runner.JUnitCore;
import org.junit.runner.Result;
import org.junit.runner.notification.Failure;

public class UnitTestingRunner {

    public static void main(String[] args) {
        Result result = JUnitCore.runClasses(UnitTestingSuite.class);
        for (Failure failure : result.getFailures()) {
            System.out.println(failure.toString());
        }
        System.out.println("TEST OK? " + result.wasSuccessful());
    }
}