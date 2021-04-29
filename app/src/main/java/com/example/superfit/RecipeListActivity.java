package com.example.superfit;

import androidx.appcompat.app.AppCompatActivity;

import android.os.AsyncTask;
import android.os.Bundle;
import android.widget.ListView;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.DecimalFormat;
import java.util.ArrayList;

import javax.net.ssl.HttpsURLConnection;

public class RecipeListActivity extends AppCompatActivity {

    // константы: id и key для API
    private static final String APP_ID = "b0e23358";
    private static final String APP_KEY = "33972e22ce0dd8f06384d71f8bd3a3f2";

    private static String DIET = "high-protein";
    private static String SEARCH = "meat";

    private ListView lv_recipes;
    private ArrayList<Recipe> recipeArrayList;
    private AdapterRecipe adapterRecipe;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recipe_list);

        lv_recipes = findViewById(R.id.lv_recipes);
        recipeArrayList = new ArrayList<>();

        System.out.println("!");

        AsyncTask.execute(new Runnable() {
            @Override
            public void run() {
                // объявление соединения
                HttpURLConnection connection = null;
                try {
                    //URL url = new URL("https://api.edamam.com/search?q=chicken&app_id=4da5a427&app_key=6dd6f99730da1737e964379d886e607d&diet=high-protein");
                    URL url = new URL("https://api.edamam.com/search?q=" + SEARCH + "&app_id=" + APP_ID + "&app_key=" + APP_KEY + "&diet=" + DIET);

                    connection = (HttpURLConnection) url.openConnection();
                    connection.setRequestMethod("GET");
                    connection.connect();

                    System.out.println("#            ");

                    if (HttpsURLConnection.HTTP_OK == connection.getResponseCode()) {
                        InputStreamReader isr = new InputStreamReader(connection.getInputStream());
                        System.out.println("code 200");

                        parser(isr);

                        isr.close();
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                adapterRecipe = new AdapterRecipe(RecipeListActivity.this, recipeArrayList);
                                lv_recipes.setAdapter(adapterRecipe);
                            }
                        });
                    }

                } catch (MalformedURLException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                } finally {
                    if (connection != null) {
                        connection.disconnect();
                    }
                }
            }
        });
    }

    private void parser(InputStreamReader file)  {
        try {
            Object object = new JSONParser().parse(file);
            JSONObject jo = (JSONObject) object;

            JSONArray hits = (JSONArray) jo.get("hits");

            for (Object obj : hits) {
                JSONObject jsonObject = (JSONObject) obj;

                JSONObject recipe = (JSONObject) jsonObject.get("recipe");
                String name = recipe.get("label").toString();
                String image = recipe.get("image").toString();
//                System.out.println(name);

                ArrayList<String> dietLabels = new ArrayList<String>();
                JSONArray jsonObject1_dietLabels = (JSONArray) recipe.get("dietLabels");
                for (int i = 0; i < jsonObject1_dietLabels.size(); i++) {
//                    System.out.println(jsonObject1_dietLabels.get(i).toString());
                    dietLabels.add(jsonObject1_dietLabels.get(i).toString());
                }

                ArrayList<String> ingredient = new ArrayList<>();
                JSONArray ingredientLines = (JSONArray) recipe.get("ingredientLines");
                for (int i = 0; i < ingredientLines.size(); i++) {
//                    System.out.println(ingredientLines.get(i).toString());
                    ingredient.add(ingredientLines.get(i).toString());
                }

                JSONObject totalNutrients = (JSONObject) recipe.get("totalNutrients");
                JSONObject jo_kcal = (JSONObject) totalNutrients.get("ENERC_KCAL");
                JSONObject jo_fat = (JSONObject) totalNutrients.get("FAT");
                JSONObject jo_protein = (JSONObject) totalNutrients.get("PROCNT");
                JSONObject jo_carbs = (JSONObject) totalNutrients.get("CHOCDF");
                int kcal = (int) Math.round(Double.parseDouble(jo_kcal.get("quantity").toString()));
                int fat = (int) Math.round(Double.parseDouble(jo_fat.get("quantity").toString()));
                int protein = (int) Math.round(Double.parseDouble(jo_protein.get("quantity").toString()));
                int carbs = (int) Math.round(Double.parseDouble(jo_carbs.get("quantity").toString()));

//                System.out.println("!" + fat + " " + protein + " " + carbs);

                Recipe curr_recipe = new Recipe(name, image, dietLabels, kcal, protein, fat, carbs, ingredient);
                recipeArrayList.add(curr_recipe);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }
}