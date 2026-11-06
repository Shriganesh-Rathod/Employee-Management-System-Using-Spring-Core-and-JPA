package com.company.util;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.util.concurrent.atomic.AtomicInteger;

@Component
//Here we are telling spring that this is a singleton bean and it will be created only once and it will be shared across the application
@Scope("singleton")
public class EmployeeIdGenerator {

    private final AtomicInteger counter = new AtomicInteger(1000);

    public Integer generateId() {
        return counter.incrementAndGet();
    }
}