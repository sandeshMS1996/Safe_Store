package com.SafeStore.DataManager;

import java.util.ArrayList;
import java.util.List;

public class Sort {
	public static List<String> quickSort(List<String> list)
	{
	    if (list.isEmpty()) 
	        return list; 
	        
	    List<String> sorted;  
	    List<String> smaller = new ArrayList<String>(); 
	    List<String> greater = new ArrayList<String>(); 
	    String pivot = list.get(0);  
	    int i;
	    String j;     
	    for (i=1;i<list.size();i++)
	    {
	        j=list.get(i);
	        if (j.compareTo(pivot)<0)   
	            smaller.add(j);
	        else
	            greater.add(j);
	    }
	    smaller=quickSort(smaller);  
	    greater=quickSort(greater);  
	    smaller.add(pivot);          
	    smaller.addAll(greater);     
	    sorted = smaller;           

	    return sorted;
	}
}
	