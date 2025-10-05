package design.principles.ISP.violated;

public record Square(double side) implements IShape {
    @Override
    public void area() {
        System.out.println("Area of Square : " + side * side);
    }

    @Override
    public void volume() {
        throw new UnsupportedOperationException("Volume of Square : not supported for 2D shape");
    }
}
