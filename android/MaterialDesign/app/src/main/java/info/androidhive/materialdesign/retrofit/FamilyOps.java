package info.androidhive.materialdesign.retrofit;

import java.util.List;

import entity.Family;
import entity.Ingredient;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Headers;

/**
 * Created by leych on 16.02.2017.
 */

public interface FamilyOps {
    @Headers("Content-Type: application/json")
    @GET("family/all")
    Call<List<Family>> getAll();
}
