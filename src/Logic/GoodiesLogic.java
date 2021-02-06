package Logic;

import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import File_operations.WriteFile;

public class GoodiesLogic {


	public HashMap<String, Integer> sortByValue(HashMap<String, Integer> goodies) 
	{ 
		List<Map.Entry<String, Integer> > list = 
				new LinkedList<Map.Entry<String, Integer> >(goodies.entrySet()); 

		Collections.sort(list, new Comparator<Map.Entry<String, Integer> >() { 
			public int compare(Map.Entry<String, Integer> o1,  
					Map.Entry<String, Integer> o2) 
			{ 
				return (o1.getValue()).compareTo(o2.getValue()); 
			} 
		}); 

		// put data from sorted list to hashmap  
		HashMap<String, Integer> temp = new LinkedHashMap<String, Integer>(); 
		for (Map.Entry<String, Integer> aa : list) { 
			temp.put(aa.getKey(), aa.getValue()); 
		} 
		return temp; 
	} 

	public void findGoodies(HashMap<String,Integer> goodies,Integer numberOfEmployees) {
		
		goodies=sortByValue(goodies);
		HashMap<String,Integer> goodiesPair = new HashMap<String, Integer>();
		HashMap<String,Integer> finalResult = new HashMap<String, Integer>();

		int n=goodies.size()-(numberOfEmployees-2);
		for(int i=1; i< n; i++) {
			getGoodiesPair(i,goodiesPair,i+(numberOfEmployees-1),goodies);
		}
		goodiesPair=sortByValue(goodiesPair);
		Map.Entry<String,Integer> entry = goodiesPair.entrySet().iterator().next();
		String products = entry.getKey();
		Integer value = entry.getValue();
		finalResult=findFinalMap(goodies, products);
		finalResult=sortByValue(finalResult);
		WriteFile.writeFile(finalResult,value);
	}

	private void getGoodiesPair(Integer pair1,HashMap<String,Integer> goodiesPair,Integer pair2, HashMap<String,Integer> goodies) {
		Integer pair1Value=null;
		Integer pair2Value=null;

		int iteration =1;
		String pair1Key=null;
		String pair2Key=null;

		for (Map.Entry<String, Integer> en : goodies.entrySet()) { 
			if(iteration==pair1)
			{
				pair1Value=en.getValue();
				pair1Key = en.getKey();

			}
			if(iteration==pair2)
			{
				pair2Value = en.getValue();
				pair2Key=en.getKey();
			}
			iteration++;

		} 
	
		goodiesPair.put(pair1Key+"&"+pair2Key,pair2Value-pair1Value);

	}

	private  HashMap<String,Integer> findFinalMap(HashMap<String,Integer> goodies,String products) {
		boolean addRecord=false;
		HashMap<String,Integer> finalResult=new HashMap<String, Integer>();
		for (Map.Entry<String, Integer> en : goodies.entrySet()) {
			if(products.contains(en.getKey()) || addRecord)
			{
				finalResult.put(en.getKey(), en.getValue());
				addRecord=true;
			}
			if(products.contains("&"+en.getKey()))
			{
				addRecord=false;
			}
		}
		return finalResult;

	}


}
