package com.naz.licznikkalorii;

import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.Date;

public class Second extends AppCompatActivity {

    private TextView mTextMessage, tvCount, tvNazwa, razem;
    private ListView list, listHome;
    private EditText editText;
    private Spinner spinner;

    ArrayAdapter<String> adapter;
    BoxAdapter boxAdapter;
    BoxAdapterHome boxAdapterHome;
    ArrayList<Product> products = new ArrayList<>();
    ArrayList<ProduktHome> productsHome = new ArrayList<>();
    String FILE_NAME = "raport.txt";

    String[] data = {"Owoce", "Warzywa", "Chleb", "Mięso i drób", "Produkty mleczne", "Owoce morza", "Кrupy"},
             prod = new String[20],
             kkal = new String[20];

    final int DIALOG_EXIT = 1, DIALOG_DOWNLOAD = 2;
    int pos, numer = 1;
    double razemmasa = 0, razemkkal = 0, massa;
    AlertDialog.Builder adb;

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @SuppressLint("SetTextI18n")
        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_home:
                    spinner.setVisibility(View.INVISIBLE);
                    list.setVisibility(View.INVISIBLE);
                    listHome.setVisibility(View.VISIBLE);
                    razem.setVisibility(View.VISIBLE);
                    if(productsHome.size() == 0)
                        mTextMessage.setText("Masz pusty koszyk \n" +
                                "Wybierz z listy produktow");
                    else
                        mTextMessage.setText("Dzisz zjedzone: ");

                    listHome.setAdapter(boxAdapterHome);
                    return true;
                case R.id.navigation_dashboard:

                    mTextMessage.setText("Wybierz produkt");
                    spinner.setAdapter(adapter);
                    spinner.setVisibility(View.VISIBLE);
                    list.setVisibility(View.VISIBLE);
                    listHome.setVisibility(View.INVISIBLE);
                    razem.setVisibility(View.INVISIBLE);

                    spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                        @Override
                        public void onItemSelected(AdapterView<?> parent, View view,
                                                   final int position, long id) {
                            products.clear();
                            switch (position){
                                case 0:
                                    for (int i = 0; i < getResources().getStringArray(R.array.owoce).length; i++){
                                        prod[i] = getResources().getStringArray(R.array.owoce)[i];
                                        kkal[i] = getResources().getStringArray(R.array.owoce_kkal)[i];
                                        products.add(new Product(prod[i], Integer.parseInt(kkal[i])));
                                    }
                                    list.setAdapter(boxAdapter);

                                    list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                                        public void onItemClick(AdapterView<?> parent, View view, int position1, long id) {
                                            pos = position1;
                                            showDialog(DIALOG_EXIT);
                                        }
                                    });
                                    break;
                                case 1:
                                    for (int i = 0; i < getResources().getStringArray(R.array.warzywa).length; i++){
                                        prod[i] = getResources().getStringArray(R.array.warzywa)[i];
                                        kkal[i] = getResources().getStringArray(R.array.warzywa_kkal)[i];
                                        products.add(new Product(prod[i], Integer.parseInt(kkal[i])));
                                    }
                                    list.setAdapter(boxAdapter);

                                    list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                                        public void onItemClick(AdapterView<?> parent, View view, int position1, long id) {
                                            pos = position1;
                                            showDialog(DIALOG_EXIT);
                                        }
                                    });
                                    break;
                                case 2:
                                    for (int i = 0; i < getResources().getStringArray(R.array.chleb).length; i++){
                                        prod[i] = getResources().getStringArray(R.array.chleb)[i];
                                        kkal[i] = getResources().getStringArray(R.array.chleb_kkal)[i];
                                        products.add(new Product(prod[i], Integer.parseInt(kkal[i])));
                                    }
                                    list.setAdapter(boxAdapter);

                                    list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                                        public void onItemClick(AdapterView<?> parent, View view, int position1, long id) {
                                            pos = position1;
                                            showDialog(DIALOG_EXIT);
                                        }
                                    });
                                    break;
                                case 3:
                                    for (int i = 0; i < getResources().getStringArray(R.array.mieso).length; i++){
                                        prod[i] = getResources().getStringArray(R.array.mieso)[i];
                                        kkal[i] = getResources().getStringArray(R.array.mieso_kkal)[i];
                                        products.add(new Product(prod[i], Integer.parseInt(kkal[i])));
                                    }
                                    list.setAdapter(boxAdapter);

                                    list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                                        public void onItemClick(AdapterView<?> parent, View view, int position1, long id) {
                                            pos = position1;
                                            showDialog(DIALOG_EXIT);
                                        }
                                    });
                                    break;
                                case 4:
                                    for (int i = 0; i < getResources().getStringArray(R.array.mleko).length; i++){
                                        prod[i] = getResources().getStringArray(R.array.mleko)[i];
                                        kkal[i] = getResources().getStringArray(R.array.mleko_kkal)[i];
                                        products.add(new Product(prod[i], Integer.parseInt(kkal[i])));
                                    }
                                    list.setAdapter(boxAdapter);

                                    list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                                        public void onItemClick(AdapterView<?> parent, View view, int position1, long id) {
                                            pos = position1;
                                            showDialog(DIALOG_EXIT);
                                        }
                                    });
                                    break;
                                case 5:
                                    for (int i = 0; i < getResources().getStringArray(R.array.ryby).length; i++){
                                        prod[i] = getResources().getStringArray(R.array.ryby)[i];
                                        kkal[i] = getResources().getStringArray(R.array.ryby_kkal)[i];
                                        products.add(new Product(prod[i], Integer.parseInt(kkal[i])));
                                    }
                                    list.setAdapter(boxAdapter);

                                    list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                                        public void onItemClick(AdapterView<?> parent, View view, int position1, long id) {
                                            pos = position1;
                                            showDialog(DIALOG_EXIT);
                                        }
                                    });
                                    break;
                                case 6:
                                    for (int i = 0; i < getResources().getStringArray(R.array.krupy).length; i++){
                                        prod[i] = getResources().getStringArray(R.array.krupy)[i];
                                        kkal[i] = getResources().getStringArray(R.array.krupy_kkal)[i];
                                        products.add(new Product(prod[i], Integer.parseInt(kkal[i])));
                                    }
                                    list.setAdapter(boxAdapter);

                                    list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                                        public void onItemClick(AdapterView<?> parent, View view, int position1, long id) {
                                            pos = position1;
                                            showDialog(DIALOG_EXIT);
                                        }
                                    });
                                    break;
                            }
                        }
                        public void onNothingSelected(AdapterView<?> arg0) {
                        }
                    });
                    return true;
                case R.id.navigation_notifications:
                    spinner.setVisibility(View.INVISIBLE);
                    list.setVisibility(View.INVISIBLE);
                    listHome.setVisibility(View.INVISIBLE);
                    razem.setVisibility(View.INVISIBLE);
                    mTextMessage.setText("");

                    showDialog(DIALOG_DOWNLOAD);
                    return true;
            }
            return false;
        }
    };

    @SuppressLint({"InflateParams", "SetTextI18n"})
    protected Dialog onCreateDialog(int id, Bundle savedInstanceState) {
        if (id == DIALOG_EXIT) {
            adb = new AlertDialog.Builder(this);
            adb.setTitle("Podaj wagę produktu");
            LinearLayout view = (LinearLayout) getLayoutInflater()
                    .inflate(R.layout.prompt, null);
            adb.setView(view);
            tvCount = view.findViewById(R.id.tv);
            tvNazwa = view.findViewById(R.id.NazwaProd);
            editText = view.findViewById(R.id.editText);
            tvCount.setText(kkal[pos]+" kkal");
            tvNazwa.setText(prod[pos]+", ");
            adb.setPositiveButton("Dodaj produkt", myClickListener);
            adb.setNegativeButton("Anuluj", myClickListener);
            return adb.create();
        } else if (id == DIALOG_DOWNLOAD) {
            adb = new AlertDialog.Builder(this);
            adb.setTitle("Download");
            adb.setMessage("Chces pobrac wynik?");
            adb.setIcon(android.R.drawable.ic_dialog_info);
            adb.setPositiveButton("Tak", myClickListener2);
            adb.setNegativeButton("Nie", myClickListener2);
            return adb.create();
        }
        return super.onCreateDialog(id);
    }
    @SuppressLint("SetTextI18n")
    protected void onPrepareDialog (int id, Dialog dialog) {
        if (id == DIALOG_EXIT) {
            editText.setText("");
            tvCount.setText(kkal[pos]+" kkal");
            tvNazwa.setText(prod[pos]+", ");
        } else {
            super.onPrepareDialog(id, dialog);
        }
    }

    OnClickListener myClickListener2 = new OnClickListener() {
        @SuppressLint({"SetTextI18n", "DefaultLocale"})
        @Override
        public void onClick(DialogInterface dialogInterface, int i) {
            switch (i) {
                case DialogInterface.BUTTON_POSITIVE:
                    Date date = new Date();
                    File sdPath = new File("mnt/sdcard");
                         File sdFile = new File(sdPath, FILE_NAME);
                         try {
                             BufferedWriter bw = new BufferedWriter(new FileWriter(sdFile));
                             bw.write(date.toString()+"\n------------------------------------ \n" +
                                     "------------------------------------ \n");
                             for(int size =0; size < productsHome.size(); size++) {
                                 bw.write(productsHome.get(size).name + ", " + productsHome.get(size).massa +
                                         " gr, " + String.format("%.2f",productsHome.get(size).price) + " kkal \n");
                             }
                             bw.write("Razem: " + razemmasa + " gr, " + String.format("%.2f", razemkkal) +" kkal");
                             bw.close();
                             Toast.makeText(getApplicationContext(), "Plik został zapisany do " +
                                     sdFile, Toast.LENGTH_SHORT).show();
                         }
                    /* try {
                        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(
                                openFileOutput(FILE_NAME, MODE_PRIVATE)));
                        bw.write(text);
                        bw.close();
                        Toast.makeText(getApplicationContext(), "File zapisano", Toast.LENGTH_SHORT).show();
                    } */
                        catch (Exception e) {
                            Toast.makeText(getApplicationContext(), e.getMessage(), Toast.LENGTH_SHORT).show();
                                mTextMessage.setText(e.getMessage());
                        }
                    break;
            }
            mTextMessage.setText("Kontunuj process \n" +
                    "Wyboru produktów!");
        }
    };

        OnClickListener myClickListener = new OnClickListener() {
        @SuppressLint({"SetTextI18n", "DefaultLocale"})
        @Override
        public void onClick(DialogInterface dialogInterface, int i) {
            switch (i) {
                case DialogInterface.BUTTON_POSITIVE:
                    try {
                        massa = Integer.parseInt(editText.getText().toString());
                        productsHome.add(new ProduktHome(numer + ". " + prod[pos], massa,
                                massa / 1000 * Integer.parseInt(kkal[pos])));
                        Toast.makeText(getApplicationContext(), "Produkt został dodany do koszyka", Toast.LENGTH_SHORT).show();
                        razemmasa += massa;
                        razemkkal += massa / 1000 * Integer.parseInt(kkal[pos]);
                        razem.setText("Razem: " + razemmasa + "gr, " + String.format("%.2f", razemkkal) +"kkal");
                        numer++;
                    } catch (Exception e){
                        Toast.makeText(getApplicationContext(), "Podaj wagę produktu", Toast.LENGTH_SHORT).show();
                    }
                    break;
            }
        }
    };

    protected boolean shouldAskPermissions() {
        return (Build.VERSION.SDK_INT > Build.VERSION_CODES.LOLLIPOP_MR1);
    }

    @TargetApi(23)
    protected void askPermissions() {
        String[] permissions = {
                "android.permission.READ_EXTERNAL_STORAGE",
                "android.permission.WRITE_EXTERNAL_STORAGE"
        };
        int requestCode = 200;
        requestPermissions(permissions, requestCode);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        if (shouldAskPermissions()) {
            askPermissions();
        }

        adapter = new ArrayAdapter<>(getApplicationContext(), android.R.layout.simple_spinner_item, data);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        boxAdapter = new BoxAdapter(getApplicationContext(), products);
        boxAdapterHome = new BoxAdapterHome(getApplicationContext(), productsHome);

        spinner = findViewById(R.id.spinner);
        spinner.setVisibility(View.INVISIBLE);
        list = findViewById(R.id.listview);
        listHome = findViewById(R.id.listviewHome);

        razem = findViewById(R.id.textViewRazem);
        mTextMessage = findViewById(R.id.message);
        BottomNavigationView navigation = findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
    }
}
