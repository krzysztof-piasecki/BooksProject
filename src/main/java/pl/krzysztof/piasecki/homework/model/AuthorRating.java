package pl.krzysztof.piasecki.homework.model;

import java.util.Objects;

public class AuthorRating {
    private String authorName;
    private Double averageRating;

    public AuthorRating(String authorName, Double averageRating) {
        this.authorName = authorName;
        this.averageRating = averageRating;
    }

    public String getAuthorName() {
        return authorName;
    }

    public Double getAverageRating() {
        return averageRating;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AuthorRating that = (AuthorRating) o;
        return Objects.equals(authorName, that.authorName) &&
                Objects.equals(averageRating, that.averageRating);
    }

    @Override
    public int hashCode() {
        return Objects.hash(authorName, averageRating);
    }
}
