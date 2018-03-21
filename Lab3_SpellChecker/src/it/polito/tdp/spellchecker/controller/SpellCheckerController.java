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
	private Dictionary model=new Dictionary();
	
	
	
	

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
    
    @FXML // fx:id="combo"
    private ComboBox<String> combo; // Value injected by FXMLLoader

    @FXML // fx:id="numErrori"
    private Label numErrori; // Value injected by FXMLLoader

    @FXML // fx:id="btnClearText"
    private Button btnClearText; // Value injected by FXMLLoader
    
    
    
    
    
    
    @FXML
    void doClearText(ActionEvent event) {
    	inputTextList.clear();
    	outputTextList.clear();

    }

    @FXML
    void doSpellCheck(ActionEvent event) {
    	
    lingua=combo.getSelectionModel().getSelectedItem();
    model.loadDictionary(lingua);
    
    
    inputProva=inputTextList.getText();
   input=inputProva.replaceAll("[.,\\/#!$%\\^&\\*;:{}=\\-_`~()\\[\\]\"]"," ");
    String arrayS[]=input.split(" ");
    List<String> lista=new ArrayList<String>();
    for(String s:arrayS)
    {
    	lista.add(s);
    }
    	
    List<RichWord> listaErrate=new ArrayList<RichWord>();
    listaErrate=model.spellCheckText(lista);
    
    StringBuilder sb=new StringBuilder();
    
    for(RichWord w:listaErrate)
    {
    	sb.append(w.getParola());
    	sb.append("\n");
    }
    
    outputTextList.setText(sb.toString());
    	
    	

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
    }
    
    
    public void setModel(Dictionary model) {
		this.model = model;
		this.combo.getItems().addAll("Italiano","Inglese");//Devi dirgli all'inizio il tipo di iggetti in lista combo
    }
}

