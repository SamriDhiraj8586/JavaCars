import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.*;


public class Main {
    private static final Map<String, Cars> cars = new HashMap<>();
    private static final Map<String, Map<String, String>> accessories = new HashMap<>();

    static {
        cars.put("Alto K10", new Cars("Alto K10", new String[]{"STD", "LXI", "VXI", "VXI+"}, new double[]{3.99, 4.25, 4.75, 5.00, 5.96}));
        cars.put("Wagon R", new Cars("Wagon R", new String[]{"LXI", "VXI", "ZXI", "ZXI+"}, new double[]{5.54, 6.25, 6.75, 7.00, 7.38}));
        cars.put("Celerio", new Cars("Celerio", new String[]{"LXI", "VXI", "ZXI", "ZXI+"}, new double[]{4.99, 6.00, 6.75, 7.04}));
        cars.put("Fronx", new Cars("Fronx", new String[]{"Sigma", "Delta", "Zeta", "Alpha", "Alpha Dual Tone"}, new double[]{7.51, 10, 11, 12, 13.04}));
        cars.put("Jimny", new Cars("Jimny", new String[]{"Zeta", "Alpha"}, new double[]{12.74, 14.95}));

        accessories.put("Exterior Accessories", new HashMap<String, String>() {{
            put("Alloy Wheels", "15000 - 40000");
            put("Body Side Moulding", "1500 - 3000");
            put("Door Visors", "1000 - 2500");
            put("Mud Flaps", "500 - 1200");
            put("Spoilers", "3000 - 8000");
            put("Chrome Garnish", "1000 - 5000");
            put("Roof Rails", "2500 - 6000");
            put("Side Skirts", "4000 - 10000");
        }});
        accessories.put("Interior Accessories", new HashMap<String, String>() {{
            put("Seat Covers", "2000 - 10000");
            put("Floor Mats", "1000 - 3000");
            put("Steering Wheel Covers", "500 - 1500");
            put("Sunshades", "500 - 2000");
            put("Dashboard Kits", "2000 - 5000");
            put("Ambient Lighting", "2000 - 6000");
            put("Car Perfumes", "200 - 1000");
        }});
        accessories.put("Infotainment and Electronics", new HashMap<String, String>() {{
            put("Touchscreen Infotainment Systems", "10000 - 50000");
            put("Speakers and Amplifiers", "5000 - 20000");
            put("Reverse Parking Sensors", "2000 - 5000");
            put("Rear View Cameras", "3000 - 7000");
            put("GPS Navigation Systems", "5000 - 15000");
            put("Car Chargers", "300 - 1000");
        }});
        accessories.put("Safety and Security", new HashMap<String, String>() {{
            put("Car Alarms", "2000 - 5000");
            put("Central Locking Systems", "3000 - 8000");
            put("Fire Extinguishers", "500 - 2000");
            put("First Aid Kits", "300 - 1000");
            put("Child Safety Seats", "5000 - 20000");
        }});
        accessories.put("Car Care", new HashMap<String, String>() {
            {
                put("Car Covers", "1000 - 5000");
                put("Cleaning Kits", "500 - 2000");
                put("Polish and Wax", "300 - 1500");
                put("Pressure Washers", "5000 - 15000");
            }
        });
    }
        public static List<Cars> getAvailableCars(double budget){
            List<Cars> availableCars = new ArrayList<Cars>();
            for (Cars car : cars.values()) {
                for (double price : car.getPriceRange()) {
                    if (price <= budget) {
                        availableCars.add(car);
                        break;
                    }
                }
            }
            return availableCars;
        }

    public static double calculateOnRoadPrice (double carPrice, double accessoriesCosts){
        double carPriceInRupees = carPrice * 100000;
        double totalPrice = carPriceInRupees + accessoriesCosts;
        double onRoadPrice = totalPrice + (totalPrice * 0.07) + (totalPrice * 0.12) + (totalPrice * 0.02);
        return onRoadPrice;
    }
        public static void main (String[]args){
            Scanner scanner = new Scanner(System.in);
            System.out.print("Enter your budget in Lakhs: ");
            double budget = scanner.nextDouble();
            System.out.println("Available cars within your budget:");

            List<Cars> availableCars = getAvailableCars(budget);


            if (availableCars.isEmpty()) {
                System.out.println("No cars available within your budget.");
                // return;
            }

            for (int i = 0; i < availableCars.size(); i++) {
                System.out.println((i + 1) + ". " + availableCars.get(i).getName());
            }

            System.out.print("Select a car by entering its number: ");
            int carIndex = scanner.nextInt() - 1;
            Cars selectedCar = availableCars.get(carIndex);
            System.out.println("Available variants for " + selectedCar.getName() + ":");

            for (int i = 0; i < selectedCar.getVariants().length; i++) {
                System.out.println((i + 1) + ". " + selectedCar.getVariants()[i] + " - " + selectedCar.getPriceRange()[i] + " Lakhs");
            }

            System.out.print("Select a variant by entering its number: ");

            int variantIndex = scanner.nextInt() - 1;

            double selectedCarPrice = selectedCar.getPriceRange()[variantIndex];

            System.out.println("Choose accessories within your budget (type 'done' to finish):");

            double accessoriesCost = 0;
            for (Map.Entry<String, Map<String, String>> category : accessories.entrySet()) {
                System.out.println(category.getKey() + ":");
                for (Map.Entry<String, String> accessory : category.getValue().entrySet()) {
                    System.out.println("- " + accessory.getKey() + ": ₹" + accessory.getValue());
                }
            }
            scanner.nextLine();


            while (true) {
                System.out.print("Enter accessory name or type 'done': ");
                String accessory = scanner.nextLine();
                if (accessory.equalsIgnoreCase("done")) break;
                System.out.print("Enter accessory cost in ₹: ");
                accessoriesCost += scanner.nextDouble();
                scanner.nextLine();
            }

            double onRoadPrice = calculateOnRoadPrice(selectedCarPrice, accessoriesCost);
            System.out.println("Selected car: " + selectedCar.getName() + " " + selectedCar.getVariants()[variantIndex]);
            System.out.println("Car price: " + selectedCarPrice + " Lakhs");
            System.out.println("Accessories cost: ₹" + accessoriesCost);
            System.out.println("On-road price: ₹" + onRoadPrice);
        }

    }
