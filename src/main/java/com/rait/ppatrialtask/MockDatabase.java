package com.rait.ppatrialtask;

import com.rait.ppatrialtask.model.SumOfTwoIntegers;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * A static synchronized list for storing the sum objects. Pretend this class accesses a database.
 *
 */
public class MockDatabase {

    //List of sum object instances.
    private static final List<SumOfTwoIntegers> sumList = Collections.synchronizedList(new ArrayList<>());

    //Static instance of the class.
    private static final MockDatabase instance = new MockDatabase();
    public static MockDatabase getInstance(){
        return instance;
    }

    /**
     * Method for a new sum object instance to be created based on the two integer arguments given
     * by the respective REST service. Creates a new instance, adds it to the list, and returns the
     * newly created instance.
     *
     * @param firstInt first integer to add
     * @param secondInt second integer to add
     * @return newly created sum object instance
     */
    public synchronized SumOfTwoIntegers addSumToList(Integer firstInt, Integer secondInt) {

        //Create the instance, add to list, return the new instance.
        SumOfTwoIntegers sumToAdd = new SumOfTwoIntegers(firstInt, secondInt);
        sumList.add(sumToAdd);

        return sumToAdd;
    }

    /**
     * Method to sort the entire list (if no other list is given) based on the given Boolean argument,
     * ordered by sum.
     *
     * @param givenList the list to sort, if null, sort the entire list
     * @param resultAscending Boolean determining whether to sort the list in ascending
     * or descending order by sum, true/false for ascending/descending
     * @return sorted list
     */
    public synchronized List<SumOfTwoIntegers> sortAllSumsInList(List<SumOfTwoIntegers> givenList,
                                                                 Boolean resultAscending) {

        //Sort the entire list by default.
        if(givenList == null){ givenList = sumList; }

        //Sort the list in either an ascending or descending order by sum, then return it.
        if(!resultAscending) {
            givenList.sort(Comparator.comparingInt(SumOfTwoIntegers::getSum).reversed());
        } else {
            givenList.sort(Comparator.comparingInt(SumOfTwoIntegers::getSum));
        }

        return givenList;
    }

    /**
     * Method to sort matching sum object instances from the list, then ordered based on the given
     * Boolean argument, by sum. Creates new list for the matching instances, sorts, then returns it.
     *
     * @param intToMatch the given integer find from the list, either as one of the integers or the sum
     * @param resultAscending Boolean determining whether to sort the list in ascending
     * or descending order by sum, true/false for ascending/descending
     * @return new sorted list of matching instances
     */
    public synchronized List<SumOfTwoIntegers> returnMatchingSums(Integer intToMatch, Boolean resultAscending){

        //Create new list for the matching sum instances.
        List<SumOfTwoIntegers> matchingSums = Collections.synchronizedList(new ArrayList<>());

        //Go through the list to find if one of the integers (but not both) or the sum matches.
        for (SumOfTwoIntegers sum : sumList){
            if(sum.getSum() == intToMatch
                    || (sum.getFirstInt() == intToMatch && sum.getSecondInt() != intToMatch)
                    || (sum.getFirstInt() != intToMatch && sum.getSecondInt() == intToMatch)){

                //Add it.
                matchingSums.add(sum);
            }
        }

        //Return the matching instances, sorted.
        return sortAllSumsInList(matchingSums, resultAscending);
    }

}
