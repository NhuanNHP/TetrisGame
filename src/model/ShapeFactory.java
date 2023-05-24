package model;

public class ShapeFactory implements IShapeFactory{
    @Override
    public AShape createShape(int id, int x, int y) {
        switch (id) {
            case 0: {
                return new ShapeL(x, y);
            }
            case 1:{
                return new ShapeO(x,y);
            }
            case 2: {
                return new ShapeZ(x,y);
            }
            default: throw new IllegalArgumentException("hinh khong ton tai");
        }
    }
}
