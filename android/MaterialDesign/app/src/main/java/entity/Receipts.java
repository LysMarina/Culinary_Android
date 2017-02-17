package entity;

import android.net.Uri;

import java.net.URI;
import java.util.ArrayList;

/**
 * Created by cher- on 16.02.2017.
 */

public class Receipts {
    private String id;
    private String name;
    private ArrayList<Ingredient> ingredients; ;
    private String family;
    private Uri photo;



    public  Receipts(){
        this.id = "";
        this.name="name";
        this.ingredients =new ArrayList<Ingredient>();
        this.family="family";
        this.photo = null;
    }
public void addIngredient(Ingredient ingredient){
   ingredients.add(ingredient);
}



    public String getId() {
        return id;
    }


    public void setId(String id) {
        this.id = id;
    }


    public Uri getPhoto() {
        return photo;
    }

    public void setPhoto(Uri photo) {
        this.photo = photo;
    }
    public String getFamily() {
        return family;
    }

    public void setFamily(String family) {
        this.family = family;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ArrayList<Ingredient> getIngredients() {
        return ingredients;
    }

    public void setIngredients(ArrayList<Ingredient> ingredients) {
        this.ingredients = ingredients;
    }

}
