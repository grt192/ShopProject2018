package frc.mechs;

public class MechCollection {
    public Elevator elevator;
    public Pickup pickup;

    public MechCollection() {
        elevator = new Elevator();
        pickup = new Pickup();
    }
}