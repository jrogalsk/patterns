package com.jrsoft.learning.patterns.behavioural.command.actor_model;

import java.util.ArrayList;
import java.util.List;

public class ActorModel {
    public static List<Command> commands = new ArrayList<>();

    public ActorModel() {
        commands.add(new ButtonCommand(1, new ButtonCommand.LightCommand(11)));
        commands.add(new ButtonCommand(2, new ButtonCommand.LightCommand(12)));
        commands.add(new ButtonCommand(3, new ButtonCommand.LightCommand(13)));
        commands.add(new ButtonCommand(4, new ButtonCommand.LightCommand(14)));
        commands.add(new ButtonCommand(5, new ButtonCommand.LightCommand(16)));
    }

    public void run() {
        while (commands.size() != 0) {
            Command cmd = commands.get(0);
            commands.remove(0);
            cmd.execute();
        }
    }
}
