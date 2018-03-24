/**
 * Sample Skeleton for 'SpellChecker.fxml' Controller Class
 */

package it.polito.tdp.spellchecker.controller;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;


import it.polito.tdp.spellchecker.model.Dictionary;
import it.polito.tdp.spellchecker.model.RichWord;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.layout.VBox;

public class SpellCheckerController {
	
	String inputProva;
	String input;
	String lingua;
	private Dictionary model;
	private List<RichWord> listaErrate=new ArrayList<>();
	private StringBuilder sb=new StringBuilder();
	
	
	

    @FXML // ResourceBundle that was given to the FXMLLoader
    private ResourceBundle resources;

    @FXML // URL location of the FXML file that was given to the FXMLLoader
    private URL location;

    @FXML // fx:id="time"
    private VBox time; // Value injected by FXMLLoader

    @FXML // fx:id="inputTextList"
    private TextArea inputTextList; // Value injected by FXMLLoader

    @FXML // fx:id="btnSpellCheck"
    private Button btnSpellCheck; // Value injected by FXMLLoader

    @FXML // fx:id="outputTextList"
    private TextArea outputTextList; // Value injected by FXMLLoader
    
    @FXML // fx:id="tempoProcesso"
    private Label tempoProcesso; // Value injected by FXMLLoader
    
    @FXML // fx:id="combo"
    private ComboBox<String> combo; // Value injected by FXMLLoader DEVI DIRGLI CHE TIPO DI DATI GESTISCE IN DICHIARAZIONE

    @FXML // fx:id="numErrori"
    private Label numErrori; // Value injected by FXMLLoader

    @FXML // fx:id="btnClearText"
    private Button btnClearText; // Value injected by FXMLLoader
    
    
    @FXML
    void doClearText(ActionEvent event) {
    	inputTextList.clear();
    	outputTextList.clear();
    	model.clearDictionary();
    	listaErrate.clear();
    	sb.delete(0, sb.capacity()-1);

    }

    @FXML
    void doSpellCheck(ActionEvent event) {
    	 model.clearDictionary();
   	  listaErrate.clear();
   	  sb.delete(0,sb.capacity()-1);
   	  lingua=null;
   	  
    	
    lingua=combo.getSelectionModel().getSelectedItem();
    
    if(lingua!=null) {
    	 
    	  
       model.loadDictionary(lingua);
  
    
   inputProva=inputTextList.getText();
   input=(inputProva.replaceAll("[.,\\/#!$%\\^&\\*;:{}=\\-_`~()\\[\\]\"]", " ")).replaceAll("\\s+", " ");//NB regex expressions, serve per eliminare anche
    String arrayS[]=input.split(" ");                                                                  //spazi duplicati, oppure StringTokenizer
    List<String> lista=new ArrayList<String>();
    for(String s:arrayS)
    {
    	lista.add(s);
    }
    
  	
    
    double d1=System.nanoTime();
    //listaErrate=model.spellCheckTextLinear(lista);
    listaErrate=model.spellCheckTextBinary(lista);
    //listaErrate=model.spellCheckTextContains(lista);
   double d2=System.nanoTime();
   double d3=d2-d1;
   
    
   
    sb.append("");
    for(RichWord w:listaErrate)
    {
    	sb.append(w.getParola());
    	sb.append("\n");
    }
    
   outputTextList.setText(sb.toString());
   numErrori.setText("Num errori: "+listaErrate.size());
   tempoProcesso.setText("process time: "+d3); 
    }
    else
    {
    	inputTextList.setText("Seleziona la lingua!!!!!!");
    }
   
    
    	

    }

    @FXML // This method is called by the FXMLLoader when initialization is complete
    void initialize() {
    	 assert time != null : "fx:id=\"time\" was not injected: check your FXML file 'SpellChecker.fxml'.";
         assert combo != null : "fx:id=\"combo\" was not injected: check your FXML file 'SpellChecker.fxml'.";
         assert inputTextList != null : "fx:id=\"inputTextList\" was not injected: check your FXML file 'SpellChecker.fxml'.";
         assert btnSpellCheck != null : "fx:id=\"btnSpellCheck\" was not injected: check your FXML file 'SpellChecker.fxml'.";
         assert outputTextList != null : "fx:id=\"outputTextList\" was not injected: check your FXML file 'SpellChecker.fxml'.";
         assert numErrori != null : "fx:id=\"numErrori\" was not injected: check your FXML file 'SpellChecker.fxml'.";
         assert btnClearText != null : "fx:id=\"btnClearText\" was not injected: check your FXML file 'SpellChecker.fxml'.";
         assert tempoProcesso != null : "fx:id=\"tempoProcesso\" was not injected: check your FXML file 'SpellChecker.fxml'.";
    }
    
    
    public void setModel(Dictionary model) {
		this.model = model;
		this.combo.getItems().addAll("Italiano","Inglese");//Devi dirgli all'inizio il tipo di oggetti in lista combo
    }
}

