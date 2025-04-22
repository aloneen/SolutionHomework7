package kz.seisen.Mediator;

import java.util.*;



public class ControlTower implements TowerMediator {
    private final Queue<Aircraft> landingQueue = new ArrayDeque<>();
    private final Queue<Aircraft> takeoffQueue = new ArrayDeque<>();

    @Override
    public synchronized void broadcast(String msg, Aircraft sender) {
        for (Aircraft a : AircraftRegistry.getAll()) {
            if (a != sender) a.receive(msg);
        }
    }

    @Override
    public synchronized boolean requestRunway(Aircraft a) {
        if (a.isEmergency()) {
            landingQueue.add(a);
            landingQueue.remove(a); // ensure front
            landingQueue.add(a);
            broadcast(a.getId() + " has emergency—clearing runway!", a);
            return true;
        }
        if (a.isLanding())
            landingQueue.offer(a);
        else
            takeoffQueue.offer(a);

        // grant if free
        if (landingQueue.peek() == a) {
            landingQueue.poll();
            broadcast(a.getId() + " cleared to land", a);
            return true;
        }
        if (takeoffQueue.peek() == a && landingQueue.isEmpty()) {
            takeoffQueue.poll();
            broadcast(a.getId() + " cleared for takeoff", a);
            return true;
        }
        return false;
    }
}
