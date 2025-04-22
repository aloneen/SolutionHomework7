package kz.seisen.Mediator;

import java.util.*;
import java.util.concurrent.*;

public class SimulationDriver {
    public static void main(String[] args) {
        ControlTower tower = new ControlTower();
        ScheduledExecutorService exec = Executors.newScheduledThreadPool(1);
        Random rnd = new Random();

        // spawn 10 random craft
        for (int i = 0; i < 10; i++) {
            int type = rnd.nextInt(3);
            boolean landing = rnd.nextBoolean();
            Aircraft a;
            String id = "A" + (i+1);
            switch (type) {
                case 0: a = new PassengerPlane(id, tower, landing); break;
                case 1: a = new CargoPlane(id, tower, landing); break;
                default: a = new Helicopter(id, tower, landing); break;
            }
            // scheduling runway requests/emergencies
            exec.scheduleAtFixedRate(() -> {
                if (rnd.nextDouble() < 0.05) {
                    System.out.println(id + " declares MAYDAY!");
                    a.declareEmergency();
                } else {
                    a.requestRunway();
                }
            }, rnd.nextInt(5), 1, TimeUnit.SECONDS);
        }

        exec.schedule(() -> exec.shutdown(), 30, TimeUnit.SECONDS);
    }
}
