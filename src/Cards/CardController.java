package Cards;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import codes.Card;
import codes.Dealer;
import codes.Deck;
import javafx.animation.TranslateTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.util.Duration;

public class CardController implements Initializable{
	@FXML
	private ImageView cardImage;
	
	@FXML
	private Button showButton;
	
	@FXML
	private AnchorPane anchor;
	
	private Deck deck;
	private Dealer dealer;
	private int x;
	private int y;
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
		try {
			deck = new Deck(1);
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		dealer = new Dealer(deck);
		x = 0;
		y = 0;
	}
	
	public void showCard(ActionEvent e) throws IOException {
		Card card = dealer.drawCard();
		ImageView im = new ImageView();
        im.setImage(card.getCardImage());
        im.setFitWidth(137);
        im.setFitHeight(180);
        im.setLayoutX(572);
        im.setLayoutY(497);
        TranslateTransition tt = new TranslateTransition(Duration.millis(20), im);
	    tt.setByX(x);
	    tt.setByY(y);
	    tt.play();
	    x = x+25;
	    y = y-30;
		anchor.getChildren().add(im);
		}

	
	
}
