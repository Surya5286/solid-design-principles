package design.principles.ISP.violated;

public record Rectangle(double length, double breadth) implements IShape {
    @Override
    public void area() {
        System.out.println("Area of Rectangle : " + length * breadth);
    }

    @Override
    public void volume() {
        throw new UnsupportedOperationException("Volume of Rectangle : not supported for 2D shape");
    }
}
