
/************************************************/
/*** Purpose:                                 ***/
/***                                          ***/
/***                                          ***/
/*** Author:                                  ***/
/*** Date:                                    ***/
/***                                          ***/
/*** Note: Based on skeleton code provided by ***/
/*** Jason Steggles 16/10/2018                ***/
/************************************************/

import java.io.*;
import java.text.*;
import java.util.*;

public class Search {

/** Global vars for counting comparisons **/
public int compSeq=0;
public int compBin=0;
public int compNew=0;

/** Array of values to be searched and size **/
private int[] A;
private int size;


/** Constructor **/
Search(int n)
{
    /** set size of array **/
    size = n;

    /** Create arrays **/
    A = new int[size];
    
    /** Initialise Array **/
    for (int i=0; i<size; i++)
    {
    	A[i] = 0;
    }

}


/********************************************/
/*** Read a file of numbers into an array ***/
/********************************************/
public void readFileIn(String file)
{
   try
   {
       /** Set up file for reading **/
       FileReader reader = new FileReader(file);
       Scanner in = new Scanner(reader);

       /** Loop round reading in data **/
       for (int i=0;i<size;i++)
       {
         A[i] = in.nextInt();
       }
    }
    catch (IOException e)
    {
       System.out.println("Error processing file " + file);
    }
}


/*****************************/
/*** Display array of data ***/
/*****************************/
public void displayData(int line, String header)
{
    /** Integer Formatter **/
    NumberFormat FI = NumberFormat.getInstance();
    FI.setMinimumIntegerDigits(3);

    /** Print header string **/
    System.out.print("\n\n"+header);

    /** Display array data **/
    for (int i=0;i<size;i++)
    {
        if (i%line == 0) { System.out.println(); }
        
        System.out.print(FI.format(A[i])+" ");
    }
}


} /*** End of class Search ***/
