package info.androidhive.materialdesign.activity;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.List;

import entity.Family;
import entity.Ingredient;
import info.androidhive.materialdesign.Listeners.CustomOnItemIngredientSelectedListener;
import info.androidhive.materialdesign.Listeners.CustomOnItemSelectedListener;
import info.androidhive.materialdesign.R;
import info.androidhive.materialdesign.retrofit.FamilyOps;
import info.androidhive.materialdesign.retrofit.IngredientOps;
import info.androidhive.materialdesign.retrofit.RetrofitFactory;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Ravi on 29/07/15.
 */
public class CreateFragment extends Fragment {
    Spinner spinner_ingredient;
    Spinner spinner_family;
    private static IngredientOps ingredientOps;
    private static FamilyOps familyOps;
    public String[] arr;
    public String[] arr_family;
    public Button button_create;

    public CreateFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_create, container, false);
        spinner_ingredient = (Spinner) rootView.findViewById(R.id.spinner_ingredient);
        spinner_family = (Spinner) rootView.findViewById(R.id.spinner_family);


        ///////////////
        ingredientOps = RetrofitFactory.getIngridientOps();
        ingredientOps.getAll().enqueue(new Callback<List<Ingredient>>() {
            @Override
            public void onResponse(Call<List<Ingredient>> call, Response<List<Ingredient>> response) {
                if (response.body() != null) {
                    arr = new String[response.body().size()];
                    for (int i = 0; i < response.body().size(); i++) {
                        arr[i] = response.body().get(i).getName();
                        // Создаем адаптер ArrayAdapter с помощью массива строк и стандартной разметки элемета spinner
                        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_spinner_item, arr);
                        // Определяем разметку для использования при выборе элемента
                        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                        // Применяем адаптер к элементу spinner
                        spinner_ingredient.setAdapter(adapter);
                        // spinner_ingredient.setOnItemSelectedListener(this);

                    }

                }

            }


            @Override
            public void onFailure(Call<List<Ingredient>> call, Throwable t) {
                Log.e("ERROR", Log.getStackTraceString(t));
            }
        });


        //////////////////

        familyOps = RetrofitFactory.getFamilyOps();
        familyOps.getAll().enqueue(new Callback<List<Family>>() {
            @Override
            public void onResponse(Call<List<Family>> call, Response<List<Family>> response) {
                if (response.body() != null) {
                    arr_family = new String[response.body().size()];
                    for (int i = 0; i < response.body().size(); i++) {
                        arr_family[i] = response.body().get(i).getName();
                        // Создаем адаптер ArrayAdapter с помощью массива строк и стандартной разметки элемета spinner
                        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_spinner_item, arr_family);
                        // Определяем разметку для использования при выборе элемента
                        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                        // Применяем адаптер к элементу spinner
                        spinner_family.setAdapter(adapter);
                        // spinner_ingredient.setOnItemSelectedListener(this);

                    }

                }

            }


            @Override
            public void onFailure(Call<List<Family>> call, Throwable t) {
                Log.e("ERROR", Log.getStackTraceString(t));
            }
        });


        spinner_family.setOnItemSelectedListener(new CustomOnItemSelectedListener());
        spinner_ingredient.setOnItemSelectedListener(new CustomOnItemIngredientSelectedListener());
        button_create = (Button)rootView.findViewById(R.id.button_create);
        button_create.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                Toast.makeText(getActivity(),
                        "Ваш вибір : " +
                                "\nКатегорія : "+ String.valueOf(spinner_family.getSelectedItem()) +
                                "\nІнгредієнт : "+ String.valueOf(spinner_ingredient.getSelectedItem()),
                        Toast.LENGTH_SHORT).show();
            }

        });

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
}












