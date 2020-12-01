package pl.krzysztof.piasecki.homework.model;

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

}
