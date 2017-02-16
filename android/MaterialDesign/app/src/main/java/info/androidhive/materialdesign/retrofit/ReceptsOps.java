package info.androidhive.materialdesign.retrofit;

import entity.Recepts;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Headers;

/**
 * Created by cher- on 16.02.2017.
 */

public interface ReceptsOps {
    @Headers("Content-Type: application/json")
    @GET("recepts/all")
    Call<Recepts> getAll();
}
