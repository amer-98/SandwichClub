package com.udacity.sandwichclub;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;
import com.udacity.sandwichclub.model.Sandwich;
import com.udacity.sandwichclub.utils.JsonUtils;


public class DetailActivity extends AppCompatActivity {

    public static final String EXTRA_POSITION = "extra_position";
    private static final int DEFAULT_POSITION = -1;
    TextView alsoKnownAs;
    TextView akaL;
    TextView placeOfOrigin;
    TextView pooL;
    TextView description;
    TextView ingredients;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        ImageView ingredientsIv = findViewById(R.id.image_iv);

        Intent intent = getIntent();
        if (intent == null) {
            closeOnError();
        }

        int position = intent.getIntExtra(EXTRA_POSITION, DEFAULT_POSITION);
        if (position == DEFAULT_POSITION) {
            // EXTRA_POSITION not found in intent
            closeOnError();
            return;
        }

        String[] sandwiches = getResources().getStringArray(R.array.sandwich_details);
        String json = sandwiches[position];
        Sandwich sandwich = JsonUtils.parseSandwichJson(json);
        if (sandwich == null) {
            // Sandwich data unavailable
            closeOnError();
            return;
        }

        populateUI(sandwich);
        Picasso.with(this)
                .load(sandwich.getImage())
                .into(ingredientsIv);

        setTitle(sandwich.getMainName());
    }

    private void closeOnError() {
        finish();
        Toast.makeText(this, R.string.detail_error_message, Toast.LENGTH_SHORT).show();
    }

    private void populateUI(Sandwich s) {

        alsoKnownAs = findViewById(R.id.also_known_tv);
        akaL =  findViewById(R.id.akaL);
        placeOfOrigin = findViewById(R.id.origin_tv);
        pooL =  findViewById(R.id.pooL);
        description = findViewById(R.id.description_tv);
        ingredients = findViewById(R.id.ingredients_tv);

        if (s.getAlsoKnownAs().isEmpty()){

            alsoKnownAs.setVisibility(View.GONE);
            akaL.setVisibility(View.GONE);

        } else{

            String a = TextUtils.join(", ", s.getAlsoKnownAs());

            alsoKnownAs.setText(a);

        }

        if(s.getPlaceOfOrigin().isEmpty()){

            placeOfOrigin.setVisibility(View.GONE);
            pooL.setVisibility(View.GONE);

        } else {

            placeOfOrigin.setText(s.getPlaceOfOrigin());

        }

        description.setText(s.getDescription());

        String i = TextUtils.join(", ", s.getIngredients());
        ingredients.setText(i);



    }
}
