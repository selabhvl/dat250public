package no.hvl.dat250.l04.examples.domains;

import java.util.HashSet;
import java.util.Set;

public class Geometry {

    public static abstract class GeoObject {

        private double x;
        private double y;

        public GeoObject(double x, double y) {
            this.x = x;
            this.y = y;
        }

        public GeoObject() {
        }

        public double getX() {
            return x;
        }

        public void setX(double x) {
            this.x = x;
        }

        public double getY() {
            return y;
        }

        public void setY(double y) {
            this.y = y;
        }
    }

    public static class Point extends GeoObject {

        public Point() {
        }

        public Point(double x, double y) {
            super(x, y);
        }
    }

    public static class Rectangle extends GeoObject {
        private double width;
        private double height;

        public Rectangle(double x, double y, double width, double height) {
            super(x, y);
            this.width = width;
            this.height = height;
        }


        public Rectangle() {
        }

        public double getWidth() {
            return width;
        }

        public void setWidth(double width) {
            this.width = width;
        }

        public double getHeight() {
            return height;
        }

        public void setHeight(double height) {
            this.height = height;
        }
    }


    public static class Square extends Rectangle {

        public Square(double x, double y, double edge) {
            super(x, y, edge, edge);
        }
    }

    public static class Circle extends GeoObject {

        private double radius;

        public Circle(double x, double y, double radius) {
            super(x, y);
            this.radius = radius;
        }

        public Circle() {
        }

        public double getRadius() {
            return radius;
        }

        public void setRadius(double radius) {
            this.radius = radius;
        }
    }


    public static class Canvas {
        private double width;
        private double height;

        private Set<GeoObject> objects = new HashSet<>();

        public Canvas(double width, double height, Set<GeoObject> objects) {
            this.width = width;
            this.height = height;
            this.objects = objects;
        }

        public Canvas() {
        }

        public double getWidth() {
            return width;
        }

        public void setWidth(double width) {
            this.width = width;
        }

        public double getHeight() {
            return height;
        }

        public void setHeight(double height) {
            this.height = height;
        }

        public Set<GeoObject> getObjects() {
            return objects;
        }

        public void setObjects(Set<GeoObject> objects) {
            this.objects = objects;
        }
    }

}

