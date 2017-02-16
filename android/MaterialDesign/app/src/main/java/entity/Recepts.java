package entity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by cher- on 16.02.2017.
 */

public class Recepts {
    private String id;
    private String name;
    private Ingredient ingredients; ;
    private String family;
    private String foto;

    public String getId() {
        return id;
    }


    public void setId(String id) {
        this.id = id;
    }


    public String getFoto() {
        return foto;
    }

    public void setFoto(String foto) {
        this.foto = foto;
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

    public Ingredient getIngredients() {
        return ingredients;
    }

    public void setIngredients(Ingredient ingredients) {
        this.ingredients = ingredients;
    }

}
