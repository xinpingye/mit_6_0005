/* Copyright (c) 2007-2016 MIT 6.005 course staff, all rights reserved.
 * Redistribution of original or derived work requires permission of course staff.
 */
package turtle;

import java.util.List;
import java.util.ArrayList;
import java.lang.Math;

public class TurtleSoup {

    /**
     * Draw a square.
     * 
     * @param turtle the turtle context
     * @param sideLength length of each side
     */
    public static void drawSquare(Turtle turtle, int sideLength) {
        //throw new RuntimeException("implement me!");
        turtle.forward(sideLength);
        turtle.turn(90);
        turtle.forward(sideLength);
        turtle.turn(90);
        turtle.forward(sideLength);
        turtle.turn(90);
        turtle.forward(sideLength);
    }

    /**
     * Determine inside angles of a regular polygon.
     * 
     * There is a simple formula for calculating the inside angles of a polygon;
     * you should derive it and use it here.
     * 
     * @param sides number of sides, where sides must be > 2
     * @return angle in degrees, where 0 <= angle < 360
     */
    public static double calculateRegularPolygonAngle(int sides) {
        //throw new RuntimeException("implement me!");
        
        double angle = (double)(sides - 2) *  180 / sides;
        
        return angle;
        
    }

    /**
     * Determine number of sides given the size of interior angles of a regular polygon.
     * 
     * There is a simple formula for this; you should derive it and use it here.
     * Make sure you *properly round* the answer before you return it (see java.lang.Math).
     * HINT: it is easier if you think about the exterior angles.
     * 
     * @param angle size of interior angles in degrees, where 0 < angle < 180
     * @return the integer number of sides
     */
    public static int calculatePolygonSidesFromAngle(double angle) {
        //throw new RuntimeException("implement me!");
        double  sides =   1/ (1- angle / 180) * 2;
        return Math.round((float)sides);
        
    }

    /**
     * Given the number of sides, draw a regular polygon.
     * 
     * (0,0) is the lower-left corner of the polygon; use only right-hand turns to draw.
     * 
     * @param turtle the turtle context
     * @param sides number of sides of the polygon to draw
     * @param sideLength length of each side
     */
    public static void drawRegularPolygon(Turtle turtle, int sides, int sideLength) {
        //throw new RuntimeException("implement me!");
        double angle = calculateRegularPolygonAngle(sides);
        
        turtle.turn(360 - 90);
        
        for(int i = 0; i < sides; i++)
        {
            turtle.turn(180 - angle);
            
            turtle.forward(sideLength);
        }
    }

    /**
     * Given the current direction, current location, and a target location, calculate the heading
     * towards the target point.
     * 
     * The return value is the angle input to turn() that would point the turtle in the direction of
     * the target point (targetX,targetY), given that the turtle is already at the point
     * (currentX,currentY) and is facing at angle currentHeading. The angle must be expressed in
     * degrees, where 0 <= angle < 360. 
     *
     * HINT: look at http://en.wikipedia.org/wiki/Atan2 and Java's math libraries
     * 
     * @param currentHeading current direction as clockwise from north
     * @param currentX current location x-coordinate
     * @param currentY current location y-coordinate
     * @param targetX target point x-coordinate
     * @param targetY target point y-coordinate
     * @return adjustment to heading (right turn amount) to get to target point,
     *         must be 0 <= angle < 360
     */
    public static double calculateHeadingToPoint(double currentHeading, int currentX, int currentY,
                                                 int targetX, int targetY) {
        //throw new RuntimeException("implement me!");
        
        int vector_x = targetX - currentX;
        int vector_y = targetY - currentY;
        
        double vector_tan = (double)vector_y / (double)vector_x;
        double degree = Math.toDegrees(Math.atan(vector_tan));
        
        if(vector_x >= 0)
        {
            double reverse_to_y_degree = 90 - degree;
            
            double current_to_target = reverse_to_y_degree - currentHeading;
            
            if(current_to_target < 0)
                current_to_target += 360;
            
            return current_to_target;
        }
        else
        {
            double reverse_to_y_degree = 270 - degree;
            
            double current_to_target = reverse_to_y_degree - currentHeading;
            
            if(current_to_target < 0)
                current_to_target += 360;
            
            return current_to_target;
        }

    }

    /**
     * Given a sequence of points, calculate the heading adjustments needed to get from each point
     * to the next.
     * 
     * Assumes that the turtle starts at the first point given, facing up (i.e. 0 degrees).
     * For each subsequent point, assumes that the turtle is still facing in the direction it was
     * facing when it moved to the previous point.
     * You should use calculateHeadingToPoint() to implement this function.
     * 
     * @param xCoords list of x-coordinates (must be same length as yCoords)
     * @param yCoords list of y-coordinates (must be same length as xCoords)
     * @return list of heading adjustments between points, of size 0 if (# of points) == 0,
     *         otherwise of size (# of points) - 1
     */
    public static List<Double> calculateHeadings(List<Integer> xCoords, List<Integer> yCoords) {
        //throw new RuntimeException("implement me!");
        
        List<Double> turn_list = new ArrayList<Double>();
        
        int cur_x = xCoords.get(0);
        int cur_y = yCoords.get(0);
        double cur_degree = 0;
        
        for(int i = 1; i < xCoords.size(); i++)
        {
            double turn_degree = calculateHeadingToPoint(cur_degree, cur_x, cur_y, xCoords.get(i), yCoords.get(i));
            
            turn_list.add(turn_degree);
            
            cur_x = xCoords.get(i);
            
            cur_y = yCoords.get(i);
            
            cur_degree += turn_degree;
            
            if(cur_degree >= 360)
                cur_degree -= 360;
        }
        
        return turn_list;
    }

    /**
     * Draw your personal, custom art.
     * 
     * Many interesting images can be drawn using the simple implementation of a turtle.  For this
     * function, draw something interesting; the complexity can be as little or as much as you want.
     * 
     * @param turtle the turtle context
     */
    public static void drawPersonalArt(Turtle turtle) {
        //throw new RuntimeException("implement me!");
        
        List<Integer> sets_x = new ArrayList<Integer>();
        //{0, 30£¬ 60£¬ 100£¬ 130£¬ 160}
        sets_x.add(0);
        sets_x.add(30);
        sets_x.add(60);
        sets_x.add(100);
        sets_x.add(130);
        sets_x.add(160);
        sets_x.add(80);
        sets_x.add(0);
        
        List<Integer> sets_y = new ArrayList<Integer>();
        //{20, 20, 60, 60, 20, 20, 0, 20}
        sets_y.add(0);
        sets_y.add(0);
        sets_y.add(40);
        sets_y.add(40);
        sets_y.add(0);
        sets_y.add(0);
        sets_y.add(-20);
        sets_y.add(0);
        
        List<Double> turn_list= calculateHeadings(sets_x, sets_y);
        
        for(int i = 0; i < sets_y.size() - 1; i++)
        {
            double length = Math.sqrt((sets_y.get(i + 1) - sets_y.get(i)) * (sets_y.get(i + 1) - sets_y.get(i)) + (sets_x.get(i + 1) - sets_x.get(i)) * (sets_x.get(i + 1) - sets_x.get(i)));
            
            turtle.turn(turn_list.get(i));
            
            turtle.forward((int)length);
        }
    }

    /**
     * Main method.
     * 
     * This is the method that runs when you run "java TurtleSoup".
     * 
     * @param args unused
     */
    public static void main(String args[]) {
        DrawableTurtle turtle = new DrawableTurtle();

        //drawSquare(turtle, 40);
        
        //drawRegularPolygon(turtle, 12, 60);
        
        drawPersonalArt(turtle);

        // draw the window
        turtle.draw();
    }

}
