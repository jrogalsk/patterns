package com.jrsoft.learning.patterns.behavioural.command.motors_and_sensors;

import com.jrsoft.learning.patterns.behavioural.command.motors_and_sensors.Command;

public abstract class Sensor {
    String name;
    Command command;

    int dummyCounter;

    public Sensor(String name, Command command) {
        this.name = name;
        this.command = command;
    }

    public void run() {
        while (true) {
            dummyCounter ++;
            if (dummyCounter == 1000)
                command.execute();
        }
    }
}
