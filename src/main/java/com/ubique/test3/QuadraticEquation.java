package com.ubique.test3;

public class QuadraticEquation {
    public static Roots findRoots(double a, double b, double c) {
        double sqr = Math.sqrt(b*b - 4*a*c);
        double res1 = (-b + sqr)/(2*a);
        double res2 = (-b - sqr)/(2*a);
        Roots roots = new Roots(res1,res2);
        return roots;
    }

    public static void main(String[] args) {
        Roots roots = QuadraticEquation.findRoots(2, 10, 8);
        System.out.println("Roots: " + roots.x1 + ", " + roots.x2);
    }
}
