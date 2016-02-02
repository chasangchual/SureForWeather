
package com.surefor.weather.entity.place;

import java.util.ArrayList;
import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class AutocompletePlace {

    @SerializedName("predictions")
    @Expose
    private List<Prediction> predictions = new ArrayList<Prediction>();
    @SerializedName("status")
    @Expose
    private String status;

    /**
     * 
     * @return
     *     The predictions
     */
    public List<Prediction> getPredictions() {
        return predictions;
    }

    /**
     * 
     * @param predictions
     *     The predictions
     */
    public void setPredictions(List<Prediction> predictions) {
        this.predictions = predictions;
    }

    /**
     * 
     * @return
     *     The status
     */
    public String getStatus() {
        return status;
    }

    /**
     * 
     * @param status
     *     The status
     */
    public void setStatus(String status) {
        this.status = status;
    }

}
