public class Rental {
    private Car car;
    private Customer customer;
    private int days;
    private boolean returned;

    public Rental(Car car, Customer customer, int days) {
        this.car = car;
        this.customer = customer;
        this.days = days;
        this.returned = false;
    }

    public Car getCar() {
        return car;
    }

    public Customer getCustomer() {
        return customer;
    }

    public int getDays() {
        return days;
    }

    public boolean isReturned() {
        return returned;
    }

    public void returnCar() {
        returned = true;
        car.setAvailable(true);
    }

    public double getTotalRent() {
        return car.getRentPerDay() * days;
    }

    public void displayRentalDetails() {
        System.out.println("\n----- Customer Details -----");
        customer.displayDetails();

        System.out.println("\n----- Car Details -----");
        car.displayDetails();

        System.out.println("Rental Days: " + days);
        System.out.println("Rent Per Day: Rs. "
                + car.getRentPerDay());
        System.out.println("Total Rent: Rs. " + getTotalRent());
        System.out.println("Return Status: "
                + (returned ? "Returned" : "Not Returned"));
    }
}
