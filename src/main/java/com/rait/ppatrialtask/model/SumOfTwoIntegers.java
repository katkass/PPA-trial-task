package com.rait.ppatrialtask.model;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;

/**
 * Object representing two integers to be added, with a specified minimum
 * and maximum value for them, and the sum of these two integers.
 */
public class SumOfTwoIntegers {

    //Static variables for accepted minimum and maximum integer values.
    public static final int MIN = 0;
    public static final int MAX = 100;

    //The three required integer attributes: the two integers to be added, and their sum.
    @Min(value = MIN, message = "Integer value can not be smaller than " + MIN)
    @Max(value = MAX, message = "Integer value can not be larger than " + MAX)
    private int firstInt;

    @Min(value = MIN, message = "Integer value can not be smaller than " + MIN)
    @Max(value = MAX, message = "Integer value can not be larger than " + MAX)
    private int secondInt;
    private int sum;

    //Constructor for instantiation.
    public SumOfTwoIntegers(int firstInt, int secondInt){
        this.firstInt = firstInt;
        this.secondInt = secondInt;
        this.sum = firstInt + secondInt;
    }

    //Getters and setters

    /**
     * Getter for the first integer to be added.
     *
     * @return first integer
     */
    public synchronized int getFirstInt(){ return firstInt; }
    /**
     * Setter for the first integer to be added. Also sets the new sum.
     *
     * @param newInt new integer
     */
    public synchronized void setFirstInt(Integer newInt){ this.firstInt = newInt; setSum(); }

    /**
     * Getter for the second integer to be added.
     *
     * @return second integer
     */
    public synchronized int getSecondInt(){ return secondInt; }
    /**
     * Setter for the second integer to be added. Also sets the new sum.
     *
     * @param newInt new integer
     */
    public synchronized void setSecondInt(Integer newInt){ this.secondInt = newInt; setSum(); }

    /**
     * Getter for the sum of the two integers added together.
     *
     * @return sum
     */
    public synchronized int getSum(){ return sum; }
    /**
     * Private setter for the new sum. Only needed when setting a new integer to be added.
     *
     */
    private void setSum(){ this.sum = this.firstInt + this.secondInt; }

}