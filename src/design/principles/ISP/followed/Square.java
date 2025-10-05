package design.principles.ISP.followed;

public record Square(double side) implements TwoDimensionalShape {
    @Override
    public void area() {
        System.out.println("Area of Square : " + side * side);
    }
}
