module dedale {
	exports application;
	exports boardGame;
	exports card;
	exports factory;
	exports game;
	exports player;
	exports treasure;
	opens application to javafx.fxml;
	requires transitive javafx.controls;
	requires javafx.fxml;
	requires transitive javafx.graphics;
}