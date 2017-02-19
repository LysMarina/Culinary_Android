package info.androidhive.materialdesign.retrofit;

import android.util.Log;

import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicHeader;
import org.apache.http.protocol.HTTP;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.io.IOException;
import java.util.ArrayList;
import java.net.URL;
import entity.Ingredient;
import entity.Receipts;

/**
 * Created by leych on 19.02.2017.
 */

public class RequestHandler {
    public String sendPostRequest(Receipts receipt) throws IOException {
        String requestURL1 ="https://lit-reaches-17435.herokuapp.com/recipe/add";

        URL url=  new URL(requestURL1);

        JSONObject jrecipt = new JSONObject();
        JSONArray ingredientsArray = new JSONArray();

        String json="";

        try{
            jrecipt.put("name",receipt.getName() );
            jrecipt.put("text", receipt.getText());
            jrecipt.put("family", receipt.getFamily());

            ArrayList<Ingredient> ingr =receipt.getIngredients();
            for (int i = 0; i < ingr.size(); i++){
                JSONObject jsonObj = new JSONObject();

                jsonObj.put("name", ingr.get(i).getName());
                jsonObj.put("count", ingr.get(i).getCount());
                ingredientsArray.put(jsonObj);
            }

            jrecipt.put("ingredients", ingredientsArray);
            jrecipt.put("foto", receipt.getPhoto());
            json = jrecipt.toString();
        } catch (JSONException e){}

        Log.d("classInJson", json);

        //////////////

        HttpClient client = new DefaultHttpClient();
        HttpPost post = new HttpPost(String.valueOf(url));
        StringEntity se = new StringEntity(json.toString());
        se.setContentType(new BasicHeader(HTTP.CONTENT_TYPE, "application/json"));
        post.setHeader("Accept", "application/json");
        post.setHeader("Content-type", "application/json");
        post.setEntity(se);
        client.execute(post);
        return json;
    }
}