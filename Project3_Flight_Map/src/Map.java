import java.io.*;
import java.util.*;
/**
* @author Zimo Chai (Jerry)
* Student ID: 1495687
* Lab Section: 1
* Date: 3/28/2017
* Description: This program serves as the basic implementation of Flight Map Problem.
*/
public class Map {
	// Did not expect that the interface can be an object.
    private ListInterface<City> adjList;

    public Map(String mapFileName) {
	adjList = new ListReferenceBased<City>();
	createFlightMap(mapFileName);
    }
    
    public void createFlightMap(String mapFileName) {
	try {
	    Scanner input = new Scanner(new File(mapFileName));
	    Scanner line;
	    while (input.hasNextLine()){
		line = new Scanner(input.nextLine());
		String origCity = line.next();
		City ct = getCity(origCity);
		if (ct==null) {
		    ct = new City(origCity);
		    adjList.append(ct);
		}
		String destCity = line.next();
		if(getCity(destCity)==null)
		    adjList.append(new City(destCity));
		double cost = line.nextDouble();
		ct.addNeighbor(new Destination(destCity, cost));
	    }
	    input.close();
	} catch (IOException e) {
	    System.out.println("IOException in reading input file!!!");
	}
    }
    
    public void unvisitAll() {
	for (int i = 1; i <= adjList.size(); i++){
	    City ct =  adjList.get(i);
	    ct.unmarkVisited();
	    ct.resetNext();
	}	
    }

    // Use contains on adjList to return the City with name cityName
    // if it exists in the map, otherwise return null.
    public City getCity(String cityName) {
    	// NEED CODE FOR PROJECT
    	
    	int index = adjList.contains(new City(cityName));
    	if(index == -1){
    		return null;
    	}
    	else{
    		return adjList.get(index);
    	}
    }

    public City getNextCity(City ct) {
	// If there are more neighbors to visit from ct,
	// loop
	//   get name of next neighbor 
	//   retrieve the City with that name // Q:How?
	//   if that City is unvisited return it
	//
	// if no unvisited neighbor of ct remains, return null
	
    // NEED CODE FOR PROJECT
    while(ct.moreNeighbors()){
    	City neighborCity = getCity(ct.getNextCityName());
    	if(!neighborCity.isVisited()){
    		return neighborCity;
    	}	
    }
    	return null;
    }
    
    public void findPath(String origin, String destination) {
	//	   ---------------------------------------------------
	//	   Determines whether a sequence of flights between
	//	   two cities exists. Nonrecursive stack version.
    //
	//	   Precondition: origin and destination are the origin
	//	   and destination city names, respectively.
    //
	//	   Postcondition: Prints out a sequence of flights
	//	   connecting origin to destination and the total
	//	   cost, otherwise prints out a failure
	//	   message. Cities visited during search are marked as
	//	   visited in the flight map.  
    //
    //	   Implementation notes:
	//	   Uses a stack for the cities of a potential
	//	   path. Calls unvisitAll, markVisited, and
	//	   getNextCity.
	//	   ---------------------------------------------------
	
	City originCity = getCity(origin);
	unvisitAll();  // Initialize the adjList.
	if (originCity == null)
	    System.out.println("No flights from " + origin + "!\n");
	else {
	    City destinationCity = new City(destination);
	    StackInterface<City> stack = new StackVectorBased<City>();
	    
	    // NEED CODE FOR PROJECT 
	    // Use stack to search the map and if path is found,
	    // print out the path and the total cost
	    stack.push(originCity);
	    while(true){
	    	if(stack.peek().moreNeighbors()){
	    		City aNeighbor = getNextCity(stack.peek());
	    		if(aNeighbor != null){
		    		stack.push(aNeighbor);
		    		aNeighbor.markVisited();	
	    		}
	    	}
	    	else{
	    		stack.pop();  // backtrack
	    	}
	    	if(stack.isEmpty()){
	    		System.out.printf("There is no way to go from %s to %s！\n", origin, destination);
    			System.out.println();
	    		break;
	    	}
	    	if(stack.peek().equals(destinationCity)){
	    		String path = "";
	    		double singleCost;
	    		double totalCost = 0;
	    		System.out.printf("Path from %s to %s is:\n", origin, destination, path);
	    		while(!stack.isEmpty()){
	    			City upperCity = stack.pop();
	    			if(!stack.isEmpty()){
	    				singleCost = stack.peek().findDest(upperCity.getName()).getCost();
	    				totalCost += singleCost;
	    				
	    				City lowerCity = stack.peek();
	    			
	    	    		path = String.format(String.format("%s -> %s: $%5.2f\n",
		    					lowerCity.getName(), upperCity.getName(), singleCost)) + path;

	    			}	  
	    		}
	    		System.out.printf("%s", path);
    			System.out.printf("The total cost is: $%5.2f\n", totalCost);
    			System.out.println();
    			break;
	    	}
	    }  // end while
	    
	}
    } // end isPath
}
