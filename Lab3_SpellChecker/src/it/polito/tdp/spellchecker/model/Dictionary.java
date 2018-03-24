package it.polito.tdp.spellchecker.model;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class Dictionary {

	private String lingua;
	private List<String> lista;

	public Dictionary() {
		lista = new ArrayList<>();
	}

	public void loadDictionary(String language) {

		if (language.toLowerCase().equals("italiano"))//NB bisogna mettere <<nome cartella>>/ prima dei file
			lingua = "rsc/Italian.txt";
		else if (language.toLowerCase().equals("inglese"))
			lingua = "rsc/English.txt";

		try {
			FileReader fr = new FileReader(lingua);
			BufferedReader br = new BufferedReader(fr);
			String word;
			while ((word = br.readLine()) != null) {
				
				lista.add(word);
				
			}
			br.close();
		} catch (IOException e) {
			System.out.println("Errore nella lettura del file");
			e.printStackTrace();
		}
	}

	public List<RichWord> spellCheckTextLinear(List<String> inputTextList) {
		ArrayList<RichWord> listaPL = new ArrayList<>();
		boolean flag;
		for (String s : inputTextList) {
			flag = false;

			for (String w : lista) {
				if (w.equals(s.toLowerCase())) {
					flag = true;
					break;
				}
			}
			if (!flag)
				listaPL.add(new RichWord(s, false));
		}

		return (List) listaPL;
	}
	
	public List<RichWord> spellCheckTextContains(List<String> inputTextList)
	{
		ArrayList<RichWord> listaPC = new ArrayList<>();
		for (String s : inputTextList)
		{
			if(!lista.contains(s.toLowerCase()))
				listaPC.add(new RichWord(s,false));
		}
			
		return listaPC;
	}
	

	public List<RichWord> spellCheckTextBinary(List<String> inputTextList)//ricerca dicotomica
	{
	boolean flag;
	ArrayList<RichWord> listaPB = new ArrayList<>();
	
	
   for(String s:inputTextList)
   {
	int low=0;
	int high=lista.size();
	flag=false;
	while(low<=high && !flag)
	{
		int middle=(low+high)/2;
		
		if((s.toLowerCase().compareTo(lista.get(middle)))>0)
		{
			low=middle+1;
		}
		else if((s.toLowerCase().compareTo(lista.get(middle)))<0)
		{
			high=middle-1;
		}
		else
		{
			flag=true;
		}	
	}
	if(!flag)
		listaPB.add(new RichWord(s,false));
}	
	return listaPB;	
	}
	
	public void clearDictionary()
	{
		lista.clear();
   
	}

}
