package com.skilldistillery.filmquery.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.skilldistillery.filmquery.entities.Actor;
import com.skilldistillery.filmquery.entities.Film;

public class DatabaseAccessorObject implements DatabaseAccessor {
	private static final String URL = "jdbc:mysql://localhost:3306/sdvid?useSSL=false";
	private String user = "student";
	private String pass = "student";

	public DatabaseAccessorObject() {
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public Film findFilmById(int filmId) {
		Film film = null;
		try {
			Connection conn = DriverManager.getConnection(URL, user, pass);
			String sqlst = "Select Distinct f.id, f.title, f.description, f.release_year, f.language_id, f.rental_duration, "
					+ "f.rental_rate, f.length, f.replacement_cost, f.rating, f.special_features, l.name from film f inner join language l on l.id = f.language_id where f.id = ?";
			PreparedStatement stmt = conn.prepareStatement(sqlst);
			stmt.setInt(1, filmId);
			ResultSet rs = stmt.executeQuery();
			if (rs.next()) {
				film = new Film();
				film.setId(rs.getInt("id"));
				film.setTitle(rs.getString(2));
				film.setDescription(rs.getString("Description"));
				film.setReleaseYear(rs.getInt("release_year"));
				film.setLanguageID(rs.getInt("language_id"));
				film.setLanguage(rs.getString("name"));
				film.setRentalDuration(rs.getInt("rental_duration"));
				film.setRentalRate(rs.getDouble("rental_rate"));
				film.setRentalDuration(rs.getInt("rental_duration"));
				film.setLength(rs.getInt("length"));
				film.setReplacementCost(rs.getDouble("replacement_cost"));
				film.setRating(rs.getString("rating"));
				film.setSpecialFeatures(rs.getString("special_features"));
				List<Actor> act = findActorsByFilmId(rs.getInt("id"));
				film.setActors(act);
			}
		} catch (SQLException e) {
			System.err.println("Database error:");
			System.err.println(e);
		}
		return film;
	}

	@Override
	public Actor findActorById(int actorID) {
		Actor actor = null;
		try {
			Connection conn = DriverManager.getConnection(URL, user, pass);
			String sqlst = "Select * from actor where id = ?";
			PreparedStatement stmt = conn.prepareStatement(sqlst);
			stmt.setInt(1, actorID);
			ResultSet rs = stmt.executeQuery();
			if (rs.next()) {
				actor = new Actor();
				actor.setId(rs.getInt("id"));
				actor.setFirstName(rs.getString("first_name"));
				actor.setLastName(rs.getString("last_name"));
			}
			conn.close();
			stmt.close();
			rs.close();
		} catch (SQLException e) {
			System.err.println("Database error:");
			System.err.println(e);
		}
		return actor;
	}

	@Override
	public List<Actor> findActorsByFilmId(int filmID) {
		List<Actor> actors = new ArrayList<>();
		try {
			Connection conn = DriverManager.getConnection(URL, user, pass);
			String sqlst = "Select distinct a.id, a.first_name, a.last_name from film f inner join film_actor fa on fa.film_id = f.id inner join actor a on a.id = fa.actor_id where f.id = ?";
			PreparedStatement stmt = conn.prepareStatement(sqlst);
			stmt.setInt(1, filmID);
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				Actor actor = new Actor();
				actor.setId(rs.getInt("id"));
				actor.setFirstName(rs.getString("first_name"));
				actor.setLastName(rs.getString("last_name"));
				actors.add(actor);
			}
		} catch (SQLException e) {
			System.err.println("Database error:");
			System.err.println(e);
		}
		return actors;
	}

	@Override
	public List<Film> findFilmByKeyword(String keyword) {
		List<Film> films = new ArrayList<>();
		try {
			Connection conn = DriverManager.getConnection(URL, user, pass);
			String sqlst = "Select Distinct f.id, f.title, f.description, f.release_year, f.language_id, f.rental_duration, "
					+ "f.rental_rate, f.length, f.replacement_cost, f.rating, f.special_features, l.name from film f inner join language l on l.id = f.language_id where f.title like ?";
			PreparedStatement stmt = conn.prepareStatement(sqlst);
			stmt.setString(1, "%" + keyword + "%");
			System.out.println();
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				Film film = new Film();
				film.setId(rs.getInt("id"));
				film.setTitle(rs.getString(2));
				film.setDescription(rs.getString("Description"));
				film.setReleaseYear(rs.getInt("release_year"));
				film.setLanguageID(rs.getInt("language_id"));
				film.setLanguage(rs.getString("name"));
				film.setRentalDuration(rs.getInt("rental_duration"));
				film.setRentalRate(rs.getDouble("rental_rate"));
				film.setRentalDuration(rs.getInt("rental_duration"));
				film.setLength(rs.getInt("length"));
				film.setReplacementCost(rs.getDouble("replacement_cost"));
				film.setRating(rs.getString("rating"));
				film.setSpecialFeatures(rs.getString("special_features"));
				List<Actor> act = findActorsByFilmId(rs.getInt("id"));
				film.setActors(act);
				films.add(film);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.err.println("Database error:");
			System.err.println(e);
		}

		return films;
	}
}
