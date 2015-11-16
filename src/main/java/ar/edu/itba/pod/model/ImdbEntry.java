package ar.edu.itba.pod.model;

import java.io.IOException;
import java.io.Serializable;
import java.util.regex.Pattern;

import com.hazelcast.nio.ObjectDataInput;
import com.hazelcast.nio.ObjectDataOutput;
import com.hazelcast.nio.serialization.DataSerializable;

import io.advantageous.boon.json.annotations.JsonProperty;

public class ImdbEntry implements DataSerializable, Serializable {
    private static final long serialVersionUID = 303252150063077190L;

    private static final String NOT_APPLICABLE = "N/A";
    private static final String MOVIE_TYPE_ID = "movie";
    private static final String THOUSAND_SEPARATOR = ",";
    private static final Pattern ACTOR_SEPARATOR_PATTERN = Pattern
            .compile(",[ ]?");

    @JsonProperty("Title")
    private String title;
    @JsonProperty("Year")
    private String yearString;
    @JsonProperty("Director")
    private String director;
    @JsonProperty("Actors")
    private String actorsString;
    @JsonProperty("Metascore")
    private String metascoreString;
    private String imdbRating;
    @JsonProperty("imdbVotes")
    private String imdbVotesString;
    @JsonProperty("imdbID")
    private String imdbId;
    @JsonProperty("Type")
    private String type;

    public ImdbEntry() {
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getYearString() {
        return yearString;
    }

    public void setYearString(String year) {
        this.yearString = year;
    }

    public String getDirector() {
        return director;
    }

    public void setDirector(String director) {
        this.director = director;
    }

    public String getActorsString() {
        return actorsString;
    }

    public void setActorsString(String actorsString) {
        this.actorsString = actorsString;
    }

    public String getMetascoreString() {
        return metascoreString;
    }

    public void setMetascoreString(String metascore) {
        this.metascoreString = metascore;
    }

    public String getImdbRating() {
        return imdbRating;
    }

    public void setImdbRating(String imdbRating) {
        this.imdbRating = imdbRating;
    }

    public String getImdbVotesString() {
        return imdbVotesString;
    }

    public void setImdbVotesString(String imdbVotes) {
        this.imdbVotesString = imdbVotes;
    }

    public String getImdbId() {
        return imdbId;
    }

    public void setImdbId(String imdbId) {
        this.imdbId = imdbId;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public boolean isMovie() {
        return MOVIE_TYPE_ID.equals(getType());
    }

    public int getImdbVotes() {
        return asOptionalInteger(
                imdbVotesString.replace(THOUSAND_SEPARATOR, ""));
    }

    public String[] getActors() {
        return ACTOR_SEPARATOR_PATTERN.split(getActorsString());
    }

    public int getYear() {
        return asOptionalInteger(yearString);
    }

    public int getMetascore() {
        return asOptionalInteger(metascoreString);
    }

    public void writeData(ObjectDataOutput out) throws IOException {
        out.writeUTF(title);
        out.writeUTF(yearString);
        out.writeUTF(director);
        out.writeUTF(actorsString);
        out.writeUTF(metascoreString);
        out.writeUTF(imdbRating);
        out.writeUTF(imdbVotesString);
        out.writeUTF(imdbId);
        out.writeUTF(type);
    }

    public void readData(ObjectDataInput in) throws IOException {
        title = in.readUTF();
        yearString = in.readUTF();
        director = in.readUTF();
        actorsString = in.readUTF();
        metascoreString = in.readUTF();
        imdbRating = in.readUTF();
        imdbVotesString = in.readUTF();
        imdbId = in.readUTF();
        type = in.readUTF();
    }

    private int asOptionalInteger(String stringValue) {
        int value = 0;
        if (!NOT_APPLICABLE.equals(stringValue)) {
            value = Integer.valueOf(stringValue);
        }
        return value;
    }
}
