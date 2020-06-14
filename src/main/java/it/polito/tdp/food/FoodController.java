/**
 * Sample Skeleton for 'Food.fxml' Controller Class
 */

package it.polito.tdp.food;

import java.net.URL;
import java.util.LinkedList;
import java.util.ResourceBundle;

import com.sun.tools.javac.util.List;

import it.polito.tdp.food.model.Model;
import it.polito.tdp.food.model.PorzionePeso;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class FoodController {
	
	private Model model;

    @FXML // ResourceBundle that was given to the FXMLLoader
    private ResourceBundle resources;

    @FXML // URL location of the FXML file that was given to the FXMLLoader
    private URL location;

    @FXML // fx:id="txtCalorie"
    private TextField txtCalorie; // Value injected by FXMLLoader

    @FXML // fx:id="txtPassi"
    private TextField txtPassi; // Value injected by FXMLLoader

    @FXML // fx:id="btnAnalisi"
    private Button btnAnalisi; // Value injected by FXMLLoader

    @FXML // fx:id="btnCorrelate"
    private Button btnCorrelate; // Value injected by FXMLLoader

    @FXML // fx:id="btnCammino"
    private Button btnCammino; // Value injected by FXMLLoader

    @FXML // fx:id="boxPorzioni"
    private ComboBox<String> boxPorzioni; // Value injected by FXMLLoader

    @FXML // fx:id="txtResult"
    private TextArea txtResult; // Value injected by FXMLLoader

    @FXML
    void doCammino(ActionEvent event) {
    	txtResult.clear();
    	txtResult.appendText("Cerco cammino peso massimo...");
    	try{
    		int passi = Integer.parseInt(txtPassi.getText());
    	}
    	catch(NumberFormatException n) {
    		txtResult.appendText("Devi inserire un numero");
    	}
    }

    @FXML
    void doCorrelate(ActionEvent event) {
    	txtResult.clear();
    	txtResult.appendText("CPorzionePesoi correlate...");
    	LinkedList<PorzionePeso> l = new LinkedList<PorzionePeso>(this.model.getVerticiConnessi(boxPorzioni.getValue()));
    	if(l.size()==0) {
    		txtResult.appendText("Non ci sono connessi");
    	}
    	else {
    		for(PorzionePeso p : l) {
    			txtResult.appendText(p + "\n");
    		}
    	}
    	
    	
    }

    @FXML
    void doCreaGrafo(ActionEvent event) {
    	txtResult.clear();
    	txtResult.appendText("Creazione grafo...");
    	try{
    		int calorie = Integer.parseInt(txtCalorie.getText());
    		this.model.creaGrafo(calorie);
    		System.out.println("Numero vertici : " + this.model.nVertici());
    		System.out.println("Numero archi : " + this.model.nArchi()); 
    		txtResult.appendText("Numero vertici : " + this.model.nVertici() + "\n");
    		txtResult.appendText("Numero archi : " + this.model.nArchi()); 
    		ObservableList<String> porzioni = FXCollections.observableList(this.model.tendina(calorie));
        	boxPorzioni.setItems(porzioni);
        	boxPorzioni.setValue(porzioni.get(0));
    		}
    	catch(NumberFormatException n) {
    		txtResult.appendText("Devi inserire un numero");
    	}
    	
    }

    @FXML // This method is called by the FXMLLoader when initialization is complete
    void initialize() {
        assert txtCalorie != null : "fx:id=\"txtCalorie\" was not injected: check your FXML file 'Food.fxml'.";
        assert txtPassi != null : "fx:id=\"txtPassi\" was not injected: check your FXML file 'Food.fxml'.";
        assert btnAnalisi != null : "fx:id=\"btnAnalisi\" was not injected: check your FXML file 'Food.fxml'.";
        assert btnCorrelate != null : "fx:id=\"btnCorrelate\" was not injected: check your FXML file 'Food.fxml'.";
        assert btnCammino != null : "fx:id=\"btnCammino\" was not injected: check your FXML file 'Food.fxml'.";
        assert boxPorzioni != null : "fx:id=\"boxPorzioni\" was not injected: check your FXML file 'Food.fxml'.";
        assert txtResult != null : "fx:id=\"txtResult\" was not injected: check your FXML file 'Food.fxml'.";

    }
    
    public void setModel(Model model) {
    	this.model = model;
    }
}
