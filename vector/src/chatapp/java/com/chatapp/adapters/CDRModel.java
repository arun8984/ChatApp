package com.chatapp.adapters;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class CDRModel {
    String name;
    String country;
    String cost;
    String duration;
    String rate;

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getCost() {
        return cost;
    }

    public void setCost(String cost) {
        this.cost = cost;
    }

    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }

    public String getRate() {
        return rate;
    }

    public void setRate(String rate) {
        this.rate = rate;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public static ArrayList<CDRModel> getCDRList(JSONArray array) {
        ArrayList<CDRModel> list = new ArrayList<>();
        for (int i = 0; i < array.length(); i++) {
            CDRModel model = new CDRModel();
            try {
                JSONObject o = array.getJSONObject(i);
                model.setCost(o.optString("cost"));
                model.setDuration(o.optString("duration"));
                model.setCountry(o.optString("country"));
                model.setName(o.optString("called_user"));
                model.setRate(o.optString("rates"));
                list.add(model);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return list;
    }
}
