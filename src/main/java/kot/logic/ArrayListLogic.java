package main.java.kot.logic;

import java.util.ArrayList;
import java.util.List;

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

}
