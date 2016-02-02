
package com.surefor.weather.entity.place;

import java.util.ArrayList;
import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;


public class Prediction {

    @SerializedName("description")
    @Expose
    private String description;
    @SerializedName("id")
    @Expose
    private String id;
    @SerializedName("matched_substrings")
    @Expose
    private List<MatchedSubstring> matchedSubstrings = new ArrayList<MatchedSubstring>();
    @SerializedName("place_id")
    @Expose
    private String placeId;
    @SerializedName("reference")
    @Expose
    private String reference;
    @SerializedName("terms")
    @Expose
    private List<Term> terms = new ArrayList<Term>();
    @SerializedName("types")
    @Expose
    private List<String> types = new ArrayList<String>();

    /**
     * 
     * @return
     *     The description
     */
    public String getDescription() {
        return description;
    }

    /**
     * 
     * @param description
     *     The description
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * 
     * @return
     *     The id
     */
    public String getId() {
        return id;
    }

    /**
     * 
     * @param id
     *     The id
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * 
     * @return
     *     The matchedSubstrings
     */
    public List<MatchedSubstring> getMatchedSubstrings() {
        return matchedSubstrings;
    }

    /**
     * 
     * @param matchedSubstrings
     *     The matched_substrings
     */
    public void setMatchedSubstrings(List<MatchedSubstring> matchedSubstrings) {
        this.matchedSubstrings = matchedSubstrings;
    }

    /**
     * 
     * @return
     *     The placeId
     */
    public String getPlaceId() {
        return placeId;
    }

    /**
     * 
     * @param placeId
     *     The place_id
     */
    public void setPlaceId(String placeId) {
        this.placeId = placeId;
    }

    /**
     * 
     * @return
     *     The reference
     */
    public String getReference() {
        return reference;
    }

    /**
     * 
     * @param reference
     *     The reference
     */
    public void setReference(String reference) {
        this.reference = reference;
    }

    /**
     * 
     * @return
     *     The terms
     */
    public List<Term> getTerms() {
        return terms;
    }

    /**
     * 
     * @param terms
     *     The terms
     */
    public void setTerms(List<Term> terms) {
        this.terms = terms;
    }

    /**
     * 
     * @return
     *     The types
     */
    public List<String> getTypes() {
        return types;
    }

    /**
     * 
     * @param types
     *     The types
     */
    public void setTypes(List<String> types) {
        this.types = types;
    }

}
