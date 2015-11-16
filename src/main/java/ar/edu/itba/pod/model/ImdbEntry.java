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
    private static final String MOVIE_TYPE_ID = "movie";
    private static final String THOUSAND_SEPARATOR = ",";
    private static final Pattern ACTOR_SEPARATOR_PATTERN = Pattern
            .compile(",[ ]?");

    @JsonProperty("Title")
    private String title;
    @JsonProperty("Year")
    private String yearString;
    @JsonProperty("Rated")
    private String rated;
    @JsonProperty("Released")
    private String released;
    @JsonProperty("Runtime")
    private String runtime;
    @JsonProperty("Genre")
    private String genre;
    @JsonProperty("Director")
    private String director;
    @JsonProperty("Writer")
    private String writer;
    @JsonProperty("Actors")
    private String actorsString;
    @JsonProperty("Plot")
    private String plot;
    @JsonProperty("Language")
    private String language;
    @JsonProperty("Country")
    private String country;
    @JsonProperty("Awards")
    private String awards;
    @JsonProperty("Poster")
    private String poster;
    @JsonProperty("Metascore")
    private String metascoreString;
    private String imdbRating;
    @JsonProperty("imdbVotes")
    private String imdbVotesString;
    @JsonProperty("imdbID")
    private String imdbId;
    @JsonProperty("Type")
    private String type;
    private String tomatoMeter;
    private String tomatoImage;
    private String tomatoRating;
    private String tomatoReviews;
    private String tomatoFresh;
    private String tomatoRotten;
    private String tomatoConsensus;
    private String tomatoUserMeter;
    private String tomatoUserRating;
    private String tomatoUserReviews;
    @JsonProperty("DVD")
    private String dvd;
    @JsonProperty("BoxOffice")
    private String boxOffice;
    @JsonProperty("Production")
    private String production;
    @JsonProperty("Website")
    private String website;
    @JsonProperty("Response")
    private String response;

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

    public String getRated() {
        return rated;
    }

    public void setRated(String rated) {
        this.rated = rated;
    }

    public String getReleased() {
        return released;
    }

    public void setReleased(String released) {
        this.released = released;
    }

    public String getRuntime() {
        return runtime;
    }

    public void setRuntime(String runtime) {
        this.runtime = runtime;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public String getDirector() {
        return director;
    }

    public void setDirector(String director) {
        this.director = director;
    }

    public String getWriter() {
        return writer;
    }

    public void setWriter(String writer) {
        this.writer = writer;
    }

    public String getActorsString() {
        return actorsString;
    }

    public void setActorsString(String actorsString) {
        this.actorsString = actorsString;
    }

    public String getPlot() {
        return plot;
    }

    public void setPlot(String plot) {
        this.plot = plot;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getAwards() {
        return awards;
    }

    public void setAwards(String awards) {
        this.awards = awards;
    }

    public String getPoster() {
        return poster;
    }

    public void setPoster(String poster) {
        this.poster = poster;
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

    public String getTomatoMeter() {
        return tomatoMeter;
    }

    public void setTomatoMeter(String tomatoMeter) {
        this.tomatoMeter = tomatoMeter;
    }

    public String getTomatoImage() {
        return tomatoImage;
    }

    public void setTomatoImage(String tomatoImage) {
        this.tomatoImage = tomatoImage;
    }

    public String getTomatoRating() {
        return tomatoRating;
    }

    public void setTomatoRating(String tomatoRating) {
        this.tomatoRating = tomatoRating;
    }

    public String getTomatoReviews() {
        return tomatoReviews;
    }

    public void setTomatoReviews(String tomatoReviews) {
        this.tomatoReviews = tomatoReviews;
    }

    public String getTomatoFresh() {
        return tomatoFresh;
    }

    public void setTomatoFresh(String tomatoFresh) {
        this.tomatoFresh = tomatoFresh;
    }

    public String getTomatoRotten() {
        return tomatoRotten;
    }

    public void setTomatoRotten(String tomatoRotten) {
        this.tomatoRotten = tomatoRotten;
    }

    public String getTomatoConsensus() {
        return tomatoConsensus;
    }

    public void setTomatoConsensus(String tomatoConsensus) {
        this.tomatoConsensus = tomatoConsensus;
    }

    public String getTomatoUserMeter() {
        return tomatoUserMeter;
    }

    public void setTomatoUserMeter(String tomatoUserMeter) {
        this.tomatoUserMeter = tomatoUserMeter;
    }

    public String getTomatoUserRating() {
        return tomatoUserRating;
    }

    public void setTomatoUserRating(String tomatoUserRating) {
        this.tomatoUserRating = tomatoUserRating;
    }

    public String getTomatoUserReviews() {
        return tomatoUserReviews;
    }

    public void setTomatoUserReviews(String tomatoUserReviews) {
        this.tomatoUserReviews = tomatoUserReviews;
    }

    public String getDvd() {
        return dvd;
    }

    public void setDvd(String dvd) {
        this.dvd = dvd;
    }

    public String getBoxOffice() {
        return boxOffice;
    }

    public void setBoxOffice(String boxOffice) {
        this.boxOffice = boxOffice;
    }

    public String getProduction() {
        return production;
    }

    public void setProduction(String production) {
        this.production = production;
    }

    public String getWebsite() {
        return website;
    }

    public void setWebsite(String website) {
        this.website = website;
    }

    public String getResponse() {
        return response;
    }

    public void setResponse(String response) {
        this.response = response;
    }

    public boolean isMovie() {
        return MOVIE_TYPE_ID.equals(getType());
    }

    public int getImdbVotes() {
        return Integer
                .valueOf(getImdbVotesString().replace(THOUSAND_SEPARATOR, ""));
    }

    public String[] getActors() {
        return ACTOR_SEPARATOR_PATTERN.split(getActorsString());
    }

    public int getYear() {
        return Integer.valueOf(getYearString());
    }

    public int getMetascore() {
        return Integer.valueOf(getMetascoreString());
    }

    public void writeData(ObjectDataOutput out) throws IOException {
        out.writeUTF(title);
        out.writeUTF(yearString);
        out.writeUTF(rated);
        out.writeUTF(released);
        out.writeUTF(runtime);
        out.writeUTF(genre);
        out.writeUTF(director);
        out.writeUTF(writer);
        out.writeUTF(actorsString);
        out.writeUTF(plot);
        out.writeUTF(language);
        out.writeUTF(country);
        out.writeUTF(awards);
        out.writeUTF(poster);
        out.writeUTF(metascoreString);
        out.writeUTF(imdbRating);
        out.writeUTF(imdbVotesString);
        out.writeUTF(imdbId);
        out.writeUTF(type);
        out.writeUTF(tomatoMeter);
        out.writeUTF(tomatoImage);
        out.writeUTF(tomatoRating);
        out.writeUTF(tomatoReviews);
        out.writeUTF(tomatoFresh);
        out.writeUTF(tomatoRotten);
        out.writeUTF(tomatoConsensus);
        out.writeUTF(tomatoUserMeter);
        out.writeUTF(tomatoUserRating);
        out.writeUTF(tomatoUserReviews);
        out.writeUTF(dvd);
        out.writeUTF(boxOffice);
        out.writeUTF(production);
        out.writeUTF(website);
        out.writeUTF(response);
    }

    public void readData(ObjectDataInput in) throws IOException {
        title = in.readUTF();
        yearString = in.readUTF();
        rated = in.readUTF();
        released = in.readUTF();
        runtime = in.readUTF();
        genre = in.readUTF();
        director = in.readUTF();
        writer = in.readUTF();
        actorsString = in.readUTF();
        plot = in.readUTF();
        language = in.readUTF();
        country = in.readUTF();
        awards = in.readUTF();
        poster = in.readUTF();
        metascoreString = in.readUTF();
        imdbRating = in.readUTF();
        imdbVotesString = in.readUTF();
        imdbId = in.readUTF();
        type = in.readUTF();
        tomatoMeter = in.readUTF();
        tomatoImage = in.readUTF();
        tomatoRating = in.readUTF();
        tomatoReviews = in.readUTF();
        tomatoFresh = in.readUTF();
        tomatoRotten = in.readUTF();
        tomatoConsensus = in.readUTF();
        tomatoUserMeter = in.readUTF();
        tomatoUserRating = in.readUTF();
        tomatoUserReviews = in.readUTF();
        dvd = in.readUTF();
        boxOffice = in.readUTF();
        production = in.readUTF();
        website = in.readUTF();
        response = in.readUTF();
    }
}
