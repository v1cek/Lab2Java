abstract class Vehicle {
    protected String make;
    protected String model;
    protected int year;

    public Vehicle(String make, String model, int year) {
        this.make = make;
        this.model = model;
        this.year = year;
    }

    public abstract void start();

    public abstract void stop();

    @Override
    public String toString() {
        return year + " " + make + " " + model;
    }
}
