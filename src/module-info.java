module OOAD_LAB {
	opens view;
	opens main;
	opens model.object;
		
	requires javafx.graphics;
	requires javafx.controls;
	requires java.sql;
	requires java.desktop;
	requires javafx.base;
	exports model.object;
}