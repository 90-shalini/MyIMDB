package com.trainings.jdbc.tables;

public class Film {
	
	private int yearOfRelease;
	private int length;
	private String title;
	private String genre;
	private String actor;
	private String actress;
	private String director;
	private int popularity;
	private char awards;
	private String image;
	
	private int genre_id;
	private int actor_id;
	private int actress_id;
	private int director_id;	
	private int image_id;	
	
	public enum FileColumns {
		
	    YEAR, DURATION, TITLE, GENRE, ACTOR, ACTRESS, DIRECTOR, POPULARITY, AWARDS, IMAGE;
		
		public int getColumn(){
			return this.ordinal();
		}
	}
	
	//-----------------------------------------------------
	
	public String getGenre() {
		return genre;
	}
	public void setGenre(String genre) {
		this.genre = genre;
	}
	public String getActor() {
		return actor;
	}
	public void setActor(String actor) {
		this.actor = actor;
	}
	public String getActress() {
		return actress;
	}
	public void setActress(String actress) {
		this.actress = actress;
	}
	public String getDirector() {
		return director;
	}
	public void setDirector(String director) {
		this.director = director;
	}
	public int getImage_id() {
		return image_id;
	}
	public void setImage_id(int image_id) {
		this.image_id = image_id;
	}

	
	public int getYearOfRelease() {
		return yearOfRelease;
	}
	public void setYearOfRelease(int yearOfRelease) {
		this.yearOfRelease = yearOfRelease;
	}
	public int getLength() {
		return length;
	}
	public void setLength(int length) {
		this.length = length;
	}
	public int getPopularity() {
		return popularity;
	}
	public void setPopularity(int popularity) {
		this.popularity = popularity;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public char getAwards() {
		return awards;
	}
	public void setAwards(char awards) {
		this.awards = awards;
	}
	public String getImage() {
		return image;
	}
	public void setImage(String image) {
		this.image = image;
	}
	public int getGenre_id() {
		return genre_id;
	}
	public void setGenre_id(int genre_id) {
		this.genre_id = genre_id;
	}
	public int getActor_id() {
		return actor_id;
	}
	public void setActor_id(int actor_id) {
		this.actor_id = actor_id;
	}
	public int getActress_id() {
		return actress_id;
	}
	public void setActress_id(int actress_id) {
		this.actress_id = actress_id;
	}
	public int getDirector_id() {
		return director_id;
	}
	public void setDirector_id(int director_id) {
		this.director_id = director_id;
	}
	

}
