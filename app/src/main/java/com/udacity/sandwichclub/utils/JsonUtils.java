package com.udacity.sandwichclub.utils;

import com.udacity.sandwichclub.model.Sandwich;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;


public class JsonUtils {

    public static Sandwich parseSandwichJson(String json) {

        Sandwich sandwich = null;
        String mainName = null;
        JSONObject name;
        List <String> alsoKnownAs = new ArrayList<>();
        String placeOfOrigin = null;
        String description = null;
        String image = null;
        List <String> ingredients = new ArrayList<>();

        try {

            JSONObject contact = new JSONObject(json);

            name = contact.getJSONObject("name");

            mainName = name.getString("mainName");

            JSONArray aka = name.getJSONArray("alsoKnownAs");

            for(int i=0;i<aka.length();i++)

                alsoKnownAs.add(aka.getString(i));

            placeOfOrigin = contact.getString("placeOfOrigin");

            description = contact.getString("description");

            image = contact.getString("image");

            JSONArray ing = contact.getJSONArray("ingredients");

            for (int i = 0 ; i < ing.length(); i++)

                ingredients.add(ing.getString(i));

        } catch (JSONException e) {
            e.printStackTrace();
        }

        sandwich = new Sandwich(mainName,alsoKnownAs,placeOfOrigin,description,image,ingredients);

        return sandwich;

    }
}

