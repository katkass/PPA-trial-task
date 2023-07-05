package com.rait.ppatrialtask.controller;

import com.rait.ppatrialtask.MockDatabase;
import com.rait.ppatrialtask.model.SumOfTwoIntegers;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

/**
 * A controller with two services allowing two integers to be added, as well as previous
 * additions to be searched. Both have specific requirements.
 *
 */
@RestController
@RequestMapping("/sum")
@Validated
public class SumController {

    /**
     * Service for adding two integers. GET mapping.
     * Takes two integers as required arguments. Allowed integer values are between are greater
     * or equal to 0and less than or equal to 100. Creates and a TwoIntegerSum object that
     * corresponds to the two given integers and their sum, adds it to the list of objects.
     * Returns the newly created object.
     * /sum/new?firstInt=80&secondInt=20 would create and return a new instance with the values
     * of 80 and 20, with a sum of 100.
     *
     * @param firstInt first integer to be added
     * @param secondInt second integer to be added
     * @return the created object corresponding to the two integers and their sum
     */
    @Valid @GetMapping("/new")
    public SumOfTwoIntegers getNewSum(@RequestParam Integer firstInt,
                                      @RequestParam Integer secondInt){

        //Given suitable arguments, create a new sum object and add it to the list.
        //Return the newly created object.
        return MockDatabase.getInstance().addSumToList(firstInt, secondInt);
    }

    /**
     * Service for finding a previous addition. GET mapping.
     * Takes one integer as an optional argument representing the value to search the list for,
     * with a required Boolean argument for determining the order the list will be sorted in,
     * either ascending or descending. Allowed values for the integer are between are again greater
     * or equal to 0 and less than or equal to 100.
     * If given the optional integer argument, searches the list for such objects where one (but not
     * both) of the two integers matches the argument's value, or where the sum of the two integers
     * matches its value. If no optional integer is given, simply returns the entire list. In either
     * case, the list is ordered according to the value of the second argument, by sum.
     * /sum/order?intToMatch=20&resultAscending=true would find instances where one of the integers
     * or the sum matches 20, and the return in ascending order, by sum.
     *
     * @param intToMatch the integer value to find from the list
     * @param resultAscending Boolean determining whether to sort the list in ascending
     * or descending order, true/false for ascending/descending
     * @return if the optional argument is present, returns any found sums matching the given
     * integer, if not, returns all the sums in the list, in both cases sorted according to the
     * required Boolean argument given
     */
    @Valid @GetMapping("/order")
    public List<SumOfTwoIntegers> getSumInOrder(
            @RequestParam Optional<@Min(SumOfTwoIntegers.MIN) @Max(SumOfTwoIntegers.MAX) Integer> intToMatch,
            @RequestParam Boolean resultAscending){

        //If given the optional argument, only return the matching objects, in specified order.
        if(intToMatch.isPresent()){
            return MockDatabase.getInstance().returnMatchingSums(intToMatch.get(), resultAscending);
        }

        //Otherwise return the entire list, in specified order.
        return MockDatabase.getInstance().sortAllSumsInList(null, resultAscending);
    }
}
