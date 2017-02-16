package info.androidhive.materialdesign.retrofit;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by leych on 15.02.2017.
 */

public class RetrofitFactory {

        public static final String BASE_ADDRESS = "https://lit-reaches-17435.herokuapp.com/";

        public static IngredientOps getIngridientOps(){
            Retrofit.Builder retrofitBuilder = new Retrofit.Builder();
            retrofitBuilder.baseUrl(BASE_ADDRESS);
            retrofitBuilder.addConverterFactory(GsonConverterFactory.create());
            Retrofit retrofit = retrofitBuilder.build();

            return retrofit.create(IngredientOps.class);
        }
    public static FamilyOps getFamilyOps(){
        Retrofit.Builder retrofitBuilder = new Retrofit.Builder();
        retrofitBuilder.baseUrl(BASE_ADDRESS);
        retrofitBuilder.addConverterFactory(GsonConverterFactory.create());
        Retrofit retrofit = retrofitBuilder.build();
        return retrofit.create(FamilyOps.class);
    }
    }

