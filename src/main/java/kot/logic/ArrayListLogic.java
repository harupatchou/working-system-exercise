package main.java.kot.logic;

import java.util.ArrayList;
import java.util.List;

import main.java.kot.entity.Workingtype;

public class ArrayListLogic {

	/*Listから重複を削除*/
	public static List<Integer> duplicateDelete(List<Integer> list){

		List<Integer> ret = new ArrayList<>();

			for (int i = 0; i < list.size(); i++) {
				Integer tempNum = list.get(i);
				if (!ret.contains(tempNum)) {
					ret.add(tempNum);
				}
			}
			return ret;
	}


	/*WorkingtypeListから重複を削除*/
	public static List<Workingtype> workingtypeUnipue(List<Workingtype> workingtypeList){

		List<Workingtype> ret = new ArrayList<>();
		List<Integer> tempworkingtypeIdList = new ArrayList<>();

		for(int i = 0; i < workingtypeList.size(); i++) {
			Workingtype x = workingtypeList.get(i);
			Integer workingtypeId = workingtypeList.get(i).getId();
			if(!tempworkingtypeIdList.contains(workingtypeId)){
				tempworkingtypeIdList.add(workingtypeId);
				ret.add(x);
			}
		}
		return ret;
	}

}
