package ru.itmo.refactor.model;

import java.time.LocalDate;
import java.util.Objects;

public class Book {
    private String title;
    private String authorName;
    private String genres; //жанр
    private LocalDate date;
    private String annotation;
    private String isbn;// Международный стандартный книжный номер
    private Integer keyWordsCounter;
    private boolean keyWordsInAnnotation;

    public Book(String title, String authorName, String genres, LocalDate date,
                String annotation, String isbn) {
        this.title = title;
        this.authorName = authorName;
        this.genres = genres;
        this.date = date;
        this.annotation = annotation;
        this.isbn = isbn;
        this.keyWordsCounter = 0;
    }

    public Book() {
    }

    public void incrementKeyWordsCounter() {
        this.keyWordsCounter++;
    }

    @Override
    public String toString() {
        String result = String.format("\nBook: %s, Author: %s, Genres: %s, Publication Date: %s, ISBN: %s",
                title, authorName, genres, date.toString(), isbn);
        if (this.keyWordsInAnnotation) {
            result += String.format("\nAnnotation: %s", annotation);
        }
        return result;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthorName() {
        return authorName;
    }

    public void setAuthorName(String authorName) {
        this.authorName = authorName;
    }

    public String getGenres() {
        return genres;
    }

    public void setGenres(String genres) {
        this.genres = genres;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public String getAnnotation() {
        return annotation;
    }

    public void setAnnotation(String annotation) {
        this.annotation = annotation;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public Integer getKeyWordsCounter() {
        return keyWordsCounter;
    }

    public void setKeyWordsCounter(Integer keyWordsCounter) {
        this.keyWordsCounter = keyWordsCounter;
    }

    public boolean hasKeyWordsInAnnotation() {
        return keyWordsInAnnotation;
    }

    public void setKeyWordsInAnnotation(boolean keyWordsInAnnotation) {
        this.keyWordsInAnnotation = keyWordsInAnnotation;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Book book = (Book) o;
        return Objects.equals(title, book.title) &&
                Objects.equals(authorName, book.authorName) &&
                Objects.equals(genres, book.genres) &&
                Objects.equals(date, book.date) &&
                Objects.equals(annotation, book.annotation) &&
                Objects.equals(isbn, book.isbn);
    }

    @Override
    public int hashCode() {
        return Objects.hash(title, authorName, genres, date, annotation, isbn);
    }
}
