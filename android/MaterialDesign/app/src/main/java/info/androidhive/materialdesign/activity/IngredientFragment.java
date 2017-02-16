package info.androidhive.materialdesign.activity;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.ListFragment;
import android.util.Log;
import android.util.SparseBooleanArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import entity.Ingredient;
import info.androidhive.materialdesign.R;
import info.androidhive.materialdesign.retrofit.IngredientOps;
import info.androidhive.materialdesign.retrofit.RetrofitFactory;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class IngredientFragment extends Fragment implements View.OnClickListener{

    ListView list;
    private static IngredientOps ingridientOps;
    public String[] arr;
    Button findbtn;
    final String LOG_TAG = "myLogs";

    public IngredientFragment() {
        // Required empty public constructor

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_ingredient, container, false);
        findbtn = (Button) rootView.findViewById(R.id.button);
        findbtn.setOnClickListener(this);
        list = (ListView) rootView.findViewById(R.id.list);
        ingridientOps = RetrofitFactory.getIngridientOps();
        ingridientOps.getAll().enqueue(new Callback<List<Ingredient>>() {
            @Override
            public void onResponse(Call<List<Ingredient>> call, Response<List<Ingredient>> response) {
                if (response.body() != null) {
                    arr = new String[response.body().size()];

                    for (int i = 0; i < response.body().size(); i++) {

                        arr[i] = response.body().get(i).getName();
                        ArrayAdapter<String> adapter1 = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_list_item_multiple_choice, arr);
                        list.setAdapter(adapter1);
                        list.setChoiceMode(ListView.CHOICE_MODE_MULTIPLE);

                    }

                }

            }


            @Override
            public void onFailure(Call<List<Ingredient>> call, Throwable t) {
                Log.e("ERROR", Log.getStackTraceString(t));
            }
        });


        ////////////////////////////


        // Inflate the layout for this fragment
        return rootView;
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
    }

    @Override
    public void onDetach() {
        super.onDetach();
    }

    @Override
    public void onClick(View view) {

        SparseBooleanArray sbArray = list.getCheckedItemPositions();
        for (int i = 0; i < sbArray.size(); i++) {
            int key = sbArray.keyAt(i);
            if (sbArray.get(key))
                Log.d(LOG_TAG, arr[key]);

        }
    }
}
