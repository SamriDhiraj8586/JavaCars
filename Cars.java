public class Cars {
    private String name;
    private String[] variants;
    private double[] priceRange;

    public Cars(String name, String[] variants, double[] priceRange) {
        this.name = name;
        this.variants = variants;
        this.priceRange = priceRange;
    }

    public String getName() {
        return name;
    }

    public String[] getVariants() {
        return variants;
    }

    public double[] getPriceRange() {
        return priceRange;
    }
}
