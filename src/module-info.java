module OOAD_LAB {
	opens controller;
	opens database;
	opens model;
	opens view;
		
	requires javafx.graphics;
	requires javafx.controls;
	requires java.sql;
	requires java.desktop;
	requires javafx.base;
}