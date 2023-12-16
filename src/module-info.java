module OOAD_LAB {
	opens view;
	opens main;
		
	requires javafx.graphics;
	requires javafx.controls;
	requires java.sql;
	requires java.desktop;
	requires javafx.base;
	opens model.object to javafx.base;
	exports model.object;
}