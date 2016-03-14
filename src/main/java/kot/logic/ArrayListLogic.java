package main.java.kot.logic;

import java.util.ArrayList;
import java.util.List;

public class ArrayListLogic {

	/*Listから重複を削除*/
	public static List<Integer> duplicateDelete(List<Integer> list){

		List<Integer> tempList = new ArrayList<>();

		for(int i = 0; i < list.size(); i++){

			if(i == 0){
				tempList.add(list.get(i));
			}else{

				int tempNum = list.get(i);
				for(int j = 0; j < tempList.size(); j++){
					if(tempNum != tempList.get(j)){
						tempList.add(tempNum);
					}else{
						tempList.remove(j);
					}
				}
			}
		}
		return tempList;
	}

}
