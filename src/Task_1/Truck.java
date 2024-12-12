class Truck extends Vehicle implements Refuelable {
    public Truck(String make, String model, int year) {
        super(make, model, year);
    }

    @Override
    public void start() {
        System.out.println(toString() + " is starting.");
    }

    @Override
    public void stop() {
        System.out.println(toString() + " is stopping.");
    }

    @Override
    public void refuel() {
        System.out.println(toString() + " is refueling.");
    }
}