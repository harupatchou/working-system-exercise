package main.java.kot.logic;

import java.util.List;

public class GenelalLogic {

	/* 複数のStringを結合する */
	public static String joinString(List<String> str,String separate){
		String strAll = "";
		for(int i=0;i<str.size();i++){
			if(i==0){
				strAll = str.get(i);
			}else{
				strAll += separate + str.get(i);
			}
		}
		return strAll;
	}

}
