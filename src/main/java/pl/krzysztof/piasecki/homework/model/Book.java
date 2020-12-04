package pl.krzysztof.piasecki.homework.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Book {
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private String isbn;
    private String title;
    private String subtitle;
    private String publisher;
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private Long publishedDate;
    private String description;
    private Integer pageCount;
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private String thumbnailUrl;
    private String language;
    private String previewLink;
    private Double averageRating;
    private List<String> authors = new ArrayList<>();
    private List<String> categories = new ArrayList<>();
    @JsonIgnore
    private Long addedTime;

    public static final class Builder {
        private String isbn;
        private String title;
        private String subtitle;
        private String publisher;
        private Long publishedDate;
        private String description;
        private Integer pageCount;
        private String thumbnailUrl;
        private String language;
        private String previewLink;
        private double averageRating;
        private List<String> authors;
        private List<String> categories;

        public Builder withIsbn(String isbn) {
            this.isbn = isbn;

            return this;
        }

        public Builder withTitle(String title) {
            this.title = title;

            return this;
        }

        public Builder withSubtitle(String subtitle) {
            this.subtitle = subtitle;

            return this;
        }

        public Builder withPublisher(String publisher) {
            this.publisher = publisher;

            return this;
        }

        public Builder withPublishedDate(Long publishedDate) {
            this.publishedDate = publishedDate;

            return this;
        }

        public Builder withDescription(String description) {
            this.description = description;

            return this;
        }

        public Builder withPageCount(Integer pageCount) {
            this.pageCount = pageCount;

            return this;
        }

        public Builder withThumbnailUrl(String thumbnailUrl) {
            this.thumbnailUrl = thumbnailUrl;

            return this;
        }

        public Builder withLanguage(String language) {
            this.language = language;

            return this;
        }

        public Builder withPreviewLink(String previewLink) {
            this.previewLink = previewLink;

            return this;
        }

        public Builder withAverageRating(Double averageRating) {
            this.averageRating = averageRating;

            return this;
        }

        public Builder withAuthors(List<String> authors) {
            this.authors = authors;

            return this;
        }

        public Builder withCategories(List<String> categories) {
            this.categories = categories;

            return this;
        }

        public Book build() {
            Book book = new Book();

            book.isbn = this.isbn;
            book.title = this.title;
            book.subtitle = this.subtitle;
            book.publisher = this.publisher;
            book.publishedDate = this.publishedDate;
            book.description = this.description;
            book.pageCount = this.pageCount;
            book.thumbnailUrl = this.thumbnailUrl;
            book.language = this.language;
            book.previewLink = this.previewLink;
            book.averageRating = this.averageRating;
            book.authors = this.authors;
            book.categories = this.categories;

            return book;
        }
    }

    public String getIsbn() {
        return isbn;
    }

    public String getTitle() {
        return title;
    }

    public String getSubtitle() {
        return subtitle;
    }

    public String getPublisher() {
        return publisher;
    }

    public Long getPublishedDate() {
        return publishedDate;
    }

    public String getDescription() {
        return description;
    }

    public Integer getPageCount() {
        return pageCount;
    }

    public String getThumbnailUrl() {
        return thumbnailUrl;
    }

    public String getLanguage() {
        return language;
    }

    public String getPreviewLink() {
        return previewLink;
    }

    public Double getAverageRating() {
        return averageRating;
    }

    public List<String> getAuthors() {
        return authors;
    }

    public List<String> getCategories() {
        return categories;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setSubtitle(String subtitle) {
        this.subtitle = subtitle;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }

    public void setPublishedDate(Long publishedDate) {
        this.publishedDate = publishedDate;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setPageCount(Integer pageCount) {
        this.pageCount = pageCount;
    }

    public void setThumbnailUrl(String thumbnailUrl) {
        this.thumbnailUrl = thumbnailUrl;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public void setPreviewLink(String previewLink) {
        this.previewLink = previewLink;
    }

    public void setAverageRating(Double averageRating) {
        this.averageRating = averageRating;
    }

    public void setAuthors(List<String> authors) {
        this.authors = authors;
    }

    public void setCategories(List<String> categories) {
        this.categories = categories;
    }

    public Long getAddedTime() {return addedTime;}

    public void setAddedTime(long addedTime) {this.addedTime = addedTime;}

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Book book = (Book) o;
        return  Objects.equals(pageCount, book.pageCount) &&
                Objects.equals(isbn, book.isbn) &&
                Objects.equals(title, book.title) &&
                Objects.equals(subtitle, book.subtitle) &&
                Objects.equals(publisher, book.publisher) &&
                Objects.equals(publishedDate, book.publishedDate) &&
                Objects.equals(description, book.description) &&
                Objects.equals(thumbnailUrl, book.thumbnailUrl) &&
                Objects.equals(language, book.language) &&
                Objects.equals(previewLink, book.previewLink) &&
                Objects.equals(averageRating, book.averageRating) &&
                Objects.equals(authors, book.authors) &&
                Objects.equals(categories, book.categories);
    }

    @Override
    public int hashCode() {
        return Objects.hash(isbn, title, subtitle, publisher, publishedDate, description, pageCount, thumbnailUrl, language, previewLink, averageRating, authors, categories, addedTime);
    }
}