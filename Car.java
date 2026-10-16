public class Car {
    private int id;
    private String name;
    private double rentPerDay;
    private boolean available;

    public Car(int id, String name, double rentPerDay) {
        this.id = id;
        this.name = name;
        this.rentPerDay = rentPerDay;
        this.available = true;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public double getRentPerDay() {
        return rentPerDay;
    }

    public boolean isAvailable() {
        return available;
    }

    public void setAvailable(boolean available) {
        this.available = available;
    }

    public void displayDetails() {
        System.out.println("Car ID: " + id);
        System.out.println("Car Name: " + name);
        System.out.println("Rent Per Day: Rs. " + rentPerDay);
        System.out.println("Status: " +
                (available ? "Available" : "Rented"));
    }
}
