
/******************************************************/
/*** Purpose: Test class to illustrate Search class ***/
/***                                                ***/
/*** Author: Jason Steggles                         ***/
/*** Date: 16/10/2018                               ***/
/******************************************************/

public class TestSearch
{
   public static void main(String[] args) 
   {
         
       Search S = new Search(100);
      
       /** Read in test data **/
       S.readFileIn("data1.txt");
    
       /** Display data array **/
       S.displayData(20, "Data Array");
       
   }
   
}
