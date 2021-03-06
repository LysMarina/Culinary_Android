package info.androidhive.materialdesign.activity;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Environment;
import android.support.v4.app.Fragment;
import android.util.Base64;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Objects;

import entity.Family;
import entity.Ingredient;
import entity.Receipts;
import info.androidhive.materialdesign.Listeners.CustomOnItemIngredientSelectedListener;
import info.androidhive.materialdesign.Listeners.CustomOnItemSelectedListener;
import info.androidhive.materialdesign.R;
import info.androidhive.materialdesign.retrofit.FamilyOps;
import info.androidhive.materialdesign.retrofit.IngredientOps;
import info.androidhive.materialdesign.retrofit.RequestHandler;
import info.androidhive.materialdesign.retrofit.RetrofitFactory;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import static android.app.Activity.RESULT_OK;



public class CreateFragment extends Fragment {

    Spinner spinner_ingredient;
    Spinner spinner_family;
    private static IngredientOps ingredientOps;
    private static FamilyOps familyOps;
    public String[] arr;
    public String[] arr_family;
    public Button button_create;
    public Button button_addphoto;
    public EditText edit_name;
    public EditText edit_instructions;
    public ImageView image_photo;
    public static final int IMAGE_GALLERY_REQUEST = 20;
    Uri imageUri;
    Bitmap bitmap;
    ArrayList<Ingredient> ingredients = new ArrayList<Ingredient>();

    CustomOnItemIngredientSelectedListener add_ingredient;


    Receipts receipt= new Receipts() ;

    public CreateFragment()  {
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
        edit_instructions = (EditText) rootView.findViewById(R.id.edit_text);



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


       add_ingredient = new CustomOnItemIngredientSelectedListener(ingredients);
        spinner_family.setOnItemSelectedListener(new CustomOnItemSelectedListener());
        spinner_ingredient.setOnItemSelectedListener(add_ingredient);
        edit_name=(EditText) rootView.findViewById(R.id.edit_name);


        button_addphoto = (Button)rootView.findViewById(R.id.button_addphoto);
        image_photo=(ImageView)rootView.findViewById(R.id.image_photo);
        button_addphoto.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
//////////////////////////////////////////////////
                // invoke the image gallery using an implict intent.
                Intent photoPickerIntent = new Intent(Intent.ACTION_PICK);

                // where do we want to find the data?
                File pictureDirectory = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES);
                String pictureDirectoryPath = pictureDirectory.getPath();
                // finally, get a URI representation
                Uri data = Uri.parse(pictureDirectoryPath);
             //   URL data1 ;
             //   data= new URL()

                // set the data and type.  Get all image types.
                photoPickerIntent.setDataAndType(data, "image/*");

                // we will invoke this activity, and get something back from it.
                startActivityForResult(photoPickerIntent, IMAGE_GALLERY_REQUEST);

            }
        });



        button_create = (Button)rootView.findViewById(R.id.button_create);
        button_create.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                receipt = new Receipts();
                receipt.setFamily(String.valueOf(spinner_family.getSelectedItem()));
                receipt.setIngredients(add_ingredient.ingredients);
                receipt.setName(edit_name.getText().toString());
                receipt.setText(edit_instructions.getText().toString());
                receipt.setIngredients(add_ingredient.ingredients);
                //receipt.setPhoto(imageUri);
                uploadReciept();
                Toast.makeText(getActivity(),
                        "Ви додали рецепт : " +edit_name.getText()+
                                "\nКатегорія : "+ String.valueOf(spinner_family.getSelectedItem()) ,
                        Toast.LENGTH_SHORT).show();
            }

        });

        // Inflate the layout for this fragment
        return rootView;
    }
                /////////////////////////////////////////////////////

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode == RESULT_OK) {
            // if we are here, everything processed successfully.
            if (requestCode == IMAGE_GALLERY_REQUEST) {
                // if we are here, we are hearing back from the image gallery.

                // the address of the image on the SD Card.
                 imageUri = data.getData();

                // declare a stream to read the image data from the SD Card.
                InputStream inputStream;

                // we are getting an input stream, based on the URI of the image.
                try {
                    inputStream = getActivity().getContentResolver().openInputStream(imageUri);
                    // get a bitmap from the stream.
                    bitmap = BitmapFactory.decodeStream(inputStream);
                    int width=200;
                    int height=200;
                    bitmap=Bitmap.createScaledBitmap(bitmap, width,height, true);


                    // show the image to the user
                    image_photo.setImageBitmap(bitmap);

                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                    // show a message to the user indictating that the image is unavailable.
                    Toast.makeText(getActivity(), "Unable to open image", Toast.LENGTH_LONG).show();
                }

            }
        }
    }

    public String getStringImage(Bitmap bmp){
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        bmp.compress(Bitmap.CompressFormat.JPEG, 100, baos);
        byte[] imageBytes = baos.toByteArray();
        String encodedImage = Base64.encodeToString(imageBytes, Base64.DEFAULT);
        receipt.setPhoto(Base64.encodeToString(imageBytes, Base64.DEFAULT));
        return encodedImage;
    }


    public void uploadReciept(){
        final String name = edit_name.getText().toString().trim();
        final String image = getStringImage(bitmap);
        class UploadImage extends AsyncTask<Void,Void,String> {
            ProgressDialog loading;
            @Override
            protected void onPreExecute() {
                super.onPreExecute();
                loading = ProgressDialog.show(getActivity(),"Please wait...","uploading",false,false);
            }

            @Override
            protected void onPostExecute(String s) {
                super.onPostExecute(s);
                loading.dismiss();
                Toast.makeText(getActivity(),s,Toast.LENGTH_LONG).show();
            }

            @Override
            protected String doInBackground(Void... params) {
                RequestHandler rh = new RequestHandler();

                String result = null;
                try {
                    result = rh.sendPostRequest(receipt);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                return result;
            }
        }
        UploadImage u = new UploadImage();
        u.execute();
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












