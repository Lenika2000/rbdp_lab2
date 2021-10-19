package ru.itmo.refactor.model;

import java.time.LocalDate;
import java.util.Objects;

public class Book {
    private String name;
    private String authorName;
    private String genres; //жанр
    private LocalDate date;
    private String annotation;
    private String isbn;// Международный стандартный книжный номер

    public Book(String name, String authorName, String genres, LocalDate date,
                String annotation, String isbn) {
        this.name = name;
        this.authorName = authorName;
        this.genres = genres;
        this.date = date;
        this.annotation = annotation;
        this.isbn = isbn;
    }

    public Book() {
    }

    @Override
    public String toString() {
        return String.format("\nBook: %s, Author: %s, Genres: %s, Publication Date: %s, ISBN: %s" +
                "\nAnnotation: %s", name, authorName, genres, date.toString(), isbn, annotation);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Book book = (Book) o;
        return Objects.equals(name, book.name) &&
                Objects.equals(authorName, book.authorName) &&
                Objects.equals(genres, book.genres) &&
                Objects.equals(date, book.date) &&
                Objects.equals(annotation, book.annotation) &&
                Objects.equals(isbn, book.isbn);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, authorName, genres, date, annotation, isbn);
    }
}
