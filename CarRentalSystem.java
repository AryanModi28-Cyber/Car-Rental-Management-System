import java.util.*;

public class CarRentalSystem {
    ArrayList<Car> cars = new ArrayList<>();
    ArrayList<Rental> rentals = new ArrayList<>();
    Scanner sc = new Scanner(System.in);

    public CarRentalSystem() {
        cars.add(new Car(1, "Toyota Fortuner", 5000));
        cars.add(new Car(2, "Mahindra Scorpio", 3000));
        cars.add(new Car(3, "Hyundai Creta", 2000));
        cars.add(new Car(4, "BMW", 8000));
        cars.add(new Car(5, "Jaguar", 10000));
    }

    int input() {
        try { return Integer.parseInt(sc.nextLine().trim()); }
        catch (NumberFormatException e) { return -1; }
    }

    Car find(int id) {
        for (Car c : cars) if (c.getId() == id) return c;
        return null;
    }

    Rental active(Car c) {
        for (Rental r : rentals)
            if (r.getCar().getId() == c.getId() && !r.isReturned()) return r;
        return null;
    }

    void showCars() {
        for (Car c : cars)
            System.out.println(c.getId() + ". " + c.getName()
                    + " Rs." + c.getRentPerDay() + "/day");
    }

    public void start() {
        showCars();
        while (true) {
            System.out.println("\n1.Rent  2.Availability  3.Return  4.Details  5.Exit");
            System.out.print("Choice: ");
            int n = input();
            if (n == 5) break;
            if (n == 1) rent();
            else if (n == 2) check();
            else if (n == 3) giveBack();
            else if (n == 4) details();
            else System.out.println("Invalid choice!");
        }
        System.out.println("Thank you!");
    }

    void rent() {
        showCars();
        System.out.print("Car ID: ");
        Car c = find(input());
        if (c == null) { System.out.println("Invalid car!"); return; }
        Rental r = active(c);
        if (r != null) {
            System.out.println("Sorry, this car is not available.");
            r.displayRentalDetails();
            return;
        }
        System.out.print("Customer name: ");
        String name = sc.nextLine().trim();
        System.out.print("Mobile (10 digits): ");
        String mobile = sc.nextLine().trim();
        System.out.print("Rental days: ");
        int days = input();
        if (name.isEmpty() || !mobile.matches("[0-9]{10}") || days <= 0) {
            System.out.println("Invalid details!"); return;
        }
        r = new Rental(c, new Customer(name, mobile), days);
        rentals.add(r);
        c.setAvailable(false);
        System.out.println("Car rented successfully!");
        r.displayRentalDetails();
    }

    void check() {
        for (Car c : cars) {
            System.out.println(c.getName() + ": "
                    + (c.isAvailable() ? "Available" : "Rented"));
            Rental r = active(c);
            if (r != null) r.displayRentalDetails();
        }
        System.out.print("Choose car ID (0 = menu): ");
        int id = input();
        if (id == 0) return;
        Car c = find(id);
        if (c == null) System.out.println("Invalid car!");
        else if (active(c) != null) {
            System.out.println("Sorry, this car is not available.");
            active(c).displayRentalDetails();
        } else System.out.println(c.getName() + " is available.");
    }

    void giveBack() {
        showCars();
        System.out.print("Car ID to return: ");
        Car c = find(input());
        if (c == null) { System.out.println("Invalid car!"); return; }
        Rental r = active(c);
        if (r == null) {
            System.out.println("This car is not currently rented.");
            for (int i = rentals.size() - 1; i >= 0; i--)
                if (rentals.get(i).getCar().getId() == c.getId()) {
                    rentals.get(i).displayRentalDetails(); return;
                }
            return;
        }
        r.displayRentalDetails();
        System.out.print("Confirm return? (1=Yes, 2=No): ");
        if (input() == 1) {
            r.returnCar();
            System.out.println("Car returned successfully!");
            r.displayRentalDetails();
        } else System.out.println("Car has not been returned.");
    }

    void details() {
        showCars();
        System.out.print("Car ID: ");
        Car c = find(input());
        if (c == null) { System.out.println("Invalid car!"); return; }
        c.displayDetails();
        Rental r = active(c);
        if (r != null) r.displayRentalDetails();
    }
}
