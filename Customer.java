public class Customer {
    private String name;
    private String mobile;

    public Customer(String name, String mobile) {
        this.name = name;
        this.mobile = mobile;
    }

    public String getName() {
        return name;
    }

    public String getMobile() {
        return mobile;
    }

    public void displayDetails() {
        System.out.println("Customer Name: " + name);
        System.out.println("Mobile Number: " + mobile);
    }
}
