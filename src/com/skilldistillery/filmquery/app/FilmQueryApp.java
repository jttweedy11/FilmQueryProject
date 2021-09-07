package com.skilldistillery.filmquery.app;

import java.util.List;
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
		boolean cont = true;
		while (cont) {
			System.out.println();
			printMenu();
			System.out.println();
			int resp = input.nextInt();
			if (resp == 1) {
				System.out.println("Please enter the film's ID");
				int uID = input.nextInt();
				Film film = db.findFilmById(uID);
				System.out.println(film);
			} else if (resp == 2) {
				System.out.println("Please enter the actor's ID");
				int aID = input.nextInt();
				Actor a = db.findActorById(aID);
				System.out.println(a);
			} else if (resp == 3) {
				System.out.println("Please enter the film's ID");
				int fID = input.nextInt();
				List<Actor> act = db.findActorsByFilmId(fID);
				if (act.size() > 0) {
					for (int i = 0; i < act.size(); i++) {
						System.out.println(act.get(i).toString());
					}
				}
			} else if (resp == 4) {
				System.out.println("Please enter a keyword to search by title:");
				input.nextLine();
				String key = input.nextLine();
				List<Film> fil = db.findFilmByKeyword(key);
				if (fil != null) {
					for (int i = 0; i < fil.size(); i++) {
						System.out.println(fil.get(i).toString());
					}
				}
			} else if (resp == 5) {
				System.out.println("Goobye!");
				cont = false;
			}
		}
	}

	private void printMenu() {
		System.out.println("****************************************************************");
		System.out.println("Welcome to the MovieDB!");
		System.out.println("In this app, you will be allowed to look up film information by ");
		System.out.println("either the film's ID or a keyword search of the film's name.");
		System.out.println("Please select from the menu below:");
		System.out.println("1. Search film by ID.");
		System.out.println("2. Search actor by ID.");
		System.out.println("3. Search actors by film ID.");
		System.out.println("4. Search films by ");
		System.out.println("5. Exit.");
	}

}
