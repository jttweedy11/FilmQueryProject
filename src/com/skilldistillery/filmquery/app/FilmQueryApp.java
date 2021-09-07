package com.skilldistillery.filmquery.app;

import java.util.Scanner;

import com.skilldistillery.filmquery.database.DatabaseAccessor;
import com.skilldistillery.filmquery.database.DatabaseAccessorObject;
import com.skilldistillery.filmquery.entities.Actor;
import com.skilldistillery.filmquery.entities.Film;

public class FilmQueryApp {

	DatabaseAccessor db = new DatabaseAccessorObject();

	public static void main(String[] args) {
		FilmQueryApp app = new FilmQueryApp();
		app.launch();
		// will comment this in
//    app.launch();
	}

	private void launch() {
		Scanner input = new Scanner(System.in);
		startUserInterface(input);
		input.close();
	}

	private void startUserInterface(Scanner input) {
		// Application logic: menus, user input, etc.
		printMenu();
		int resp = input.nextInt();
		if (resp == 1) {
			System.out.println("Please enter the film's ID");
			int uID = input.nextInt();
			Film film = db.findFilmById(uID);
			System.out.println(film);
		}
		else if (resp == 2) {
			System.out.println("Please enter the actor's ID");
			int aID = input.nextInt();
			Actor a = db.findActorById(aID);
			System.out.println(a);
		}
	}

	private void printMenu() {
		System.out.println("Welcome to the MovieDB!");
		System.out.println("In this app, you will be allowed to look up film information by ");
		System.out.println("either the film's ID or a keyword search of the film's name.");
		System.out.println("Please select from the menu below:");
		System.out.println("1. Search film by ID.");
		System.out.println("2. Search film name by keyword.");
		System.out.println("3. Exit.");
	}

}
