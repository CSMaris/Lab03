package it.polito.tdp.spellchecker.model;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Dictionary {

	private String lingua;
	private ArrayList<String> lista;

	public Dictionary() {
		lista = new ArrayList<>();
	}

	public void loadDictionary(String language) {

		if (language.toLowerCase().equals("italiano"))
			lingua = "Italian.txt";
		else if (language.toLowerCase().equals("inglese"))
			lingua = "English.txt";

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

	public List<RichWord> spellCheckText(List<String> inputTextList) {
		ArrayList<RichWord> listaP = new ArrayList<>();
		boolean flag;
		for (String s : inputTextList) {
			flag = false;

			for (String s2 : lista) {
				if (s2.equals(s)) {
					flag = true;
					break;
				}

				if (!flag)
					listaP.add(new RichWord(s, false));
			}
		}

		return (List) listaP;
	}

}
