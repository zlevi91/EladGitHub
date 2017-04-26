package graphicshape;

import java.security.InvalidParameterException;

/**
 * Created by eladlavi on 07/02/2017.
 */
public class Triangle extends Shape{
    private Point p1, p2, p3;

    public Triangle(Point p1, Point p2, Point p3) {
        this.p1 = p1;
        this.p2 = p2;
        this.p3 = p3;

        /*Segment segment = new Segment(p1, p2);
        if(segment.distanceToPoint(p3) == 0){
            throw new InvalidParameterException();
        }*/

        Segment seg1 = new Segment(p1, p2);
        Segment seg2 = new Segment(p2, p3);
        Segment seg3 = new Segment(p3, p1);
        if(seg1.getLength() + seg2.getLength() <= seg3.getLength()
                || seg2.getLength() + seg3.getLength() <= seg1.getLength())
            throw new InvalidParameterException("not a triangle");
    }

    public Point getP1() {
        return p1;
    }

    public void setP1(Point p1) {
        this.p1 = p1;
    }

    public Point getP2() {
        return p2;
    }

    public void setP2(Point p2) {
        this.p2 = p2;
    }

    public Point getP3() {
        return p3;
    }

    public void setP3(Point p3) {
        this.p3 = p3;
    }

    @Override
    public double perimeter() {
        Segment seg1 = new Segment(p1, p2);
        Segment seg2 = new Segment(p2, p3);
        Segment seg3 = new Segment(p3, p1);
        return seg1.getLength() + seg2.getLength() + seg3.getLength();
    }

    @Override
    public double area() {
        Segment seg1 = new Segment(p1, p2);
        return seg1.distanceToPoint(p3) * seg1.getLength() / 2;
    }
}
