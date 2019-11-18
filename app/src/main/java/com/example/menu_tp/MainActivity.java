package com.example.menu_tp;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

import de.codecrafters.tableview.TableView;
import de.codecrafters.tableview.listeners.TableDataClickListener;
import de.codecrafters.tableview.toolkit.SimpleTableDataAdapter;
import de.codecrafters.tableview.toolkit.SimpleTableHeaderAdapter;

public class MainActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    String [] tableHeaders = { "ID", "Nom", "Catégorie", "Prix", "Nombre" };
    String [][] tableProduit;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Spinner spinner = findViewById(R.id.spinner);
        final Button btnTotal = findViewById(R.id.buttonTotal);
        final Button btnLister = findViewById(R.id.buttonLister);
        btnLister.setOnClickListener(new View.OnClickListener() {
            @SuppressLint({"WrongViewCast", "SetTextI18n"})
            @Override
            public void onClick(View view) {


                TableShow();
            }
        });

        btnTotal.setOnClickListener(new View.OnClickListener() {
            @SuppressLint({"WrongViewCast", "SetTextI18n"})
            @Override
            public void onClick(View view) {

                final TextView textView = findViewById(R.id.textViewTotal);

                textView.setText(String.valueOf(calculTotal()));

            }
        });

        // Initilasion of the spinner of the provinces
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.category_array, android.R.layout.simple_spinner_item);

        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(this);


        TableShow();


    }

    private void TableShow() {
        final TableView<String[]> tb = findViewById(R.id.tableInventory);
        tb.setColumnCount(5);
        tb.setBackgroundColor(Color.parseColor("#d8dce8"));


        // event listener for datatable;
        tb.addDataClickListener(new TableDataClickListener<String[]>() {
            @Override
            public void onDataClicked(int rowIndex, String[] clickedData) {
                Toast.makeText(MainActivity.this, (clickedData)[1], Toast.LENGTH_SHORT).show();

            }
        });
        //populate
        transferData();

        //set adapters for the TABLE
        SimpleTableHeaderAdapter headerAdapter = new SimpleTableHeaderAdapter(this, tableHeaders);
        headerAdapter.setTextSize(10);
        tb.setHeaderAdapter(headerAdapter);
        SimpleTableDataAdapter dataAdapter = new SimpleTableDataAdapter(this, tableProduit);
        dataAdapter.setTextSize(10);
        tb.setDataAdapter(dataAdapter);
    }

    void transferData(){

        ArrayList<Produit> arrayList = getProduits();

        // initilize the new instance of the table
        tableProduit = new String[arrayList.size()][5];

        for (int i = 0; i < arrayList.size(); i++){

            Produit newProduit = arrayList.get(i);

            tableProduit[i][0] = String.valueOf(newProduit.getId());
            tableProduit[i][1] = newProduit.getCom();
            tableProduit[i][2] = newProduit.getCategory();
            tableProduit[i][3] = newProduit.getPrix();
            tableProduit[i][4] = String.valueOf(newProduit.getNombre());

        }
    }


    int calculTotal(){
        ArrayList<Produit> arrayList = getProduits();

        // initilize the new instance of the table
        tableProduit = new String[arrayList.size()][5];
         int sum = 0;
        for (int i = 0; i < arrayList.size(); i++){

            Produit newProduit = arrayList.get(i);
            sum += newProduit.getNombre();

        }
        return sum;

    }

    private ArrayList<Produit> getProduits() {

        Produit produit = new Produit(1, "DDR4", "Memory", "46,5", 45);
        Produit produit1 = new Produit(2, "DDR3", "Memory", "46,5", 123);
        Produit produit2 = new Produit(3, "DDR3", "Memory", "32,5", 47);
        Produit produit3 = new Produit(4, "DDR4", "Memory", "46,5", 12);
        Produit produit4 = new Produit(5, "DDR2", "Memory", "12,5", 34);

        ArrayList<Produit> arrayList = new ArrayList<>();
        arrayList.add(produit);
        arrayList.add(produit1);
        arrayList.add(produit2);
        arrayList.add(produit3);
        arrayList.add(produit4);
        return arrayList;
    }


    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

        String choice = adapterView.getItemAtPosition(i).toString();
        Toast.makeText(adapterView.getContext(), choice, Toast.LENGTH_SHORT).show();
        if(choice.equals("DDR2")){

            TableView<String[]> tb2 = findViewById(R.id.tableInventory);
            tb2.setColumnCount(5);
            tb2.setBackgroundColor(Color.parseColor("#d8dce8"));


            ArrayList<Produit> arrayListDDR2 = new ArrayList<>();
            ArrayList<Produit> arrayListTotal = getProduits();
            int countTotal = arrayListTotal.size();

            for(int k = 0; k < countTotal; k++){

                if(arrayListTotal.get(k).getCom().equals("DDR2")){


                    arrayListDDR2.add(arrayListTotal.get(k));

                }

            }

            int count = arrayListDDR2.size();

            // initilize the new instance of the table
            tableProduit = new String[count][5];

            for (int p = 0; p < count; p++){

                Produit newProduit = arrayListDDR2.get(p);

                tableProduit[p][0] = String.valueOf(newProduit.getId());
                tableProduit[p][1] = newProduit.getCom();
                tableProduit[p][2] = newProduit.getCategory();
                tableProduit[p][3] = newProduit.getPrix();
                tableProduit[p][4] = String.valueOf(newProduit.getNombre());

            }

            //set adapters for the TABLE
            SimpleTableHeaderAdapter headerAdapter = new SimpleTableHeaderAdapter(this, tableHeaders);
            headerAdapter.setTextSize(10);
            tb2.setHeaderAdapter(headerAdapter);
            SimpleTableDataAdapter dataAdapter = new SimpleTableDataAdapter(this, tableProduit);
            dataAdapter.setTextSize(10);
            tb2.setDataAdapter(dataAdapter);

            // event listener for datatable;
            tb2.addDataClickListener(new TableDataClickListener<String[]>() {
                @Override
                public void onDataClicked(int rowIndex, String[] clickedData) {
                    Toast.makeText(MainActivity.this, (clickedData)[1], Toast.LENGTH_SHORT).show();

                }
            });
        }
        if(choice.equals("DDR3")){

            TableView<String[]> tb2 = findViewById(R.id.tableInventory);
            tb2.setColumnCount(5);
            tb2.setBackgroundColor(Color.parseColor("#d8dce8"));


            ArrayList<Produit> arrayListDDR2 = new ArrayList<>();
            ArrayList<Produit> arrayListTotal = getProduits();
            int countTotal = arrayListTotal.size();

            for(int k = 0; k < countTotal; k++){

                if(arrayListTotal.get(k).getCom().equals("DDR3")){


                    arrayListDDR2.add(arrayListTotal.get(k));

                }

            }

            int count = arrayListDDR2.size();

            // initilize the new instance of the table
            tableProduit = new String[count][5];

            for (int p = 0; p < count; p++){

                Produit newProduit = arrayListDDR2.get(p);

                tableProduit[p][0] = String.valueOf(newProduit.getId());
                tableProduit[p][1] = newProduit.getCom();
                tableProduit[p][2] = newProduit.getCategory();
                tableProduit[p][3] = newProduit.getPrix();
                tableProduit[p][4] = String.valueOf(newProduit.getNombre());

            }

            //set adapters for the TABLE
            SimpleTableHeaderAdapter headerAdapter = new SimpleTableHeaderAdapter(this, tableHeaders);
            headerAdapter.setTextSize(10);
            tb2.setHeaderAdapter(headerAdapter);
            SimpleTableDataAdapter dataAdapter = new SimpleTableDataAdapter(this, tableProduit);
            dataAdapter.setTextSize(10);
            tb2.setDataAdapter(dataAdapter);

            // event listener for datatable;
            tb2.addDataClickListener(new TableDataClickListener<String[]>() {
                @Override
                public void onDataClicked(int rowIndex, String[] clickedData) {
                    Toast.makeText(MainActivity.this, (clickedData)[1], Toast.LENGTH_SHORT).show();

                }
            });
        }
        if(choice.equals("DDR4")){

            TableView<String[]> tb2 = findViewById(R.id.tableInventory);
            tb2.setColumnCount(5);
            tb2.setBackgroundColor(Color.parseColor("#d8dce8"));


            ArrayList<Produit> arrayListDDR2 = new ArrayList<>();
            ArrayList<Produit> arrayListTotal = getProduits();
            int countTotal = arrayListTotal.size();

            for(int k = 0; k < countTotal; k++){

                if(arrayListTotal.get(k).getCom().equals("DDR4")){


                    arrayListDDR2.add(arrayListTotal.get(k));

                }

            }

            int count = arrayListDDR2.size();

            // initilize the new instance of the table
            tableProduit = new String[count][5];

            for (int p = 0; p < count; p++){

                Produit newProduit = arrayListDDR2.get(p);

                tableProduit[p][0] = String.valueOf(newProduit.getId());
                tableProduit[p][1] = newProduit.getCom();
                tableProduit[p][2] = newProduit.getCategory();
                tableProduit[p][3] = newProduit.getPrix();
                tableProduit[p][4] = String.valueOf(newProduit.getNombre());

            }

            //set adapters for the TABLE
            SimpleTableHeaderAdapter headerAdapter = new SimpleTableHeaderAdapter(this, tableHeaders);
            headerAdapter.setTextSize(10);
            tb2.setHeaderAdapter(headerAdapter);
            SimpleTableDataAdapter dataAdapter = new SimpleTableDataAdapter(this, tableProduit);
            dataAdapter.setTextSize(10);
            tb2.setDataAdapter(dataAdapter);

            // event listener for datatable;
            tb2.addDataClickListener(new TableDataClickListener<String[]>() {
                @Override
                public void onDataClicked(int rowIndex, String[] clickedData) {
                    Toast.makeText(MainActivity.this, (clickedData)[1], Toast.LENGTH_SHORT).show();

                }
            });
        }

    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }

    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {

    }
}
