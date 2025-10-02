package design.principles.ISP.followed;

interface TwoDimensionalShape {
    void area();
}

interface ThreeDimensionalShape extends TwoDimensionalShape {
    void volume();
}

public class ISPFollowed {
    public static void main(String[] args) {
        TwoDimensionalShape  square = new Square(4);
        square.area();

        TwoDimensionalShape rectangle = new Rectangle(4, 5);
        rectangle.area();

        ThreeDimensionalShape cube = new Cube(3);
        cube.area();
        cube.volume();
    }
}
