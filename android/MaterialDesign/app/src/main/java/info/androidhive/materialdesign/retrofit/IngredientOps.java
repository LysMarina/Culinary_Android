package info.androidhive.materialdesign.retrofit;

import java.util.List;

import entity.Ingredient;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Headers;

/**
 * Created by leych on 15.02.2017.
 */

public interface IngredientOps {

        @Headers("Content-Type: application/json")
        @GET("ingredient/all")
        Call<List<Ingredient>> getAll();
    }

