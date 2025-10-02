package design.principles.ISP.violated;

import java.util.List;

interface IShape {
    void area();
    void volume();
}

public class ISPViolated {
    public static void main(String[] args) {

        List<IShape> shapes = List.of(
                new Square(5),
                new Rectangle(5, 10),
                new Cube(5)
        );

        shapes.forEach(shape -> {
            try {
                shape.area();
                shape.volume();
            } catch (UnsupportedOperationException e) {
                System.out.println(e.getMessage());
            }
        });
    }
}
