package com.taskmanager;

import com.taskmanager.config.ConfigApp;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;


public class App {
    public static void main( String[] args ) {
        final var context = new AnnotationConfigApplicationContext(ConfigApp.class);
        context.close();
    }
}
