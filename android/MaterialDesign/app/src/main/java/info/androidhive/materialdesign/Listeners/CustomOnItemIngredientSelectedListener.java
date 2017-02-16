package info.androidhive.materialdesign.Listeners;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import info.androidhive.materialdesign.R;
import info.androidhive.materialdesign.activity.CreateFragment;

import static info.androidhive.materialdesign.R.layout.fragment_create;

/**
 * Created by leych on 16.02.2017.
 */

public class CustomOnItemIngredientSelectedListener implements AdapterView.OnItemSelectedListener {
    public Context context;
    private TextView final_text;
    public String text;

    public void onItemSelected(final AdapterView<?> parent, View view, int pos, long id) {
         context = parent.getContext();
        //Toast.makeText(parent.getContext(),
               // "Вибір: " + parent.getItemAtPosition(pos).toString(),
               // Toast.LENGTH_SHORT).show();

     text =parent.getItemAtPosition(pos).toString();

                //Получаем вид с файла prompt.xml, который применим для диалогового окна:
                LayoutInflater li = LayoutInflater.from(context);
                View promptsView = li.inflate(R.layout.prompt, null);

                //Создаем AlertDialog
                AlertDialog.Builder mDialogBuilder = new AlertDialog.Builder(context);

                //Настраиваем prompt.xml для нашего AlertDialog:
                mDialogBuilder.setView(promptsView);

                //Настраиваем отображение поля для ввода текста в открытом диалоге:
                final EditText userInput = (EditText) promptsView.findViewById(R.id.input_text);

                //Настраиваем сообщение в диалоговом окне:
                mDialogBuilder
                        .setCancelable(false)
                        .setPositiveButton("Добавить",
                                new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog,int id) {

                                        //Вводим текст и отображаем в строке ввода на основном экране:
                                        View view= parent.getRootView();
                                        final_text = (TextView)view.findViewById(R.id.text_ingredients);
                                        final_text.append( text+" "+userInput.getText()+"\n");

                                    }
                                })
                        .setNegativeButton("Отмена",
                                new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog, int id) {
                                        dialog.cancel();
                                    }
                                });

                //Создаем AlertDialog:
                AlertDialog alertDialog = mDialogBuilder.create();

                //и отображаем его:
                alertDialog.show();

            }


    @Override
    public void onNothingSelected(AdapterView<?> arg0) {
        // TODO Auto-generated method stub
    }

}