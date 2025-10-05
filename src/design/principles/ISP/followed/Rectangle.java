package design.principles.ISP.followed;

public record Rectangle(double length, double breadth) implements TwoDimensionalShape {
    @Override
    public void area() {
        System.out.println("Area of Rectangle : " + length * breadth);
    }
}
