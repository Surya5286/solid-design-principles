package design.principles.ISP.followed;

public record Cube(double side) implements ThreeDimensionalShape {
    @Override
    public void area() {
        System.out.println("Area of Cube : " + 6 * side * side);
    }

    @Override
    public void volume() {
        System.out.println("Volume of Cube : " + side * side * side);
    }
}
