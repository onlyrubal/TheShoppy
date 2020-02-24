package com.example.assignment1;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.text.InputType;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.Calendar;

public class MainActivity extends AppCompatActivity {

    private static final String[] PROVINCES = new String[] {
            "Ontario", "Quebec", "Nova Scotia", "New Brunswick", "Manitoba", "British Columbia", "Prince Edward Island",
            "Saskatchewan", "Alberta", "Newfoundland and Labrador"
    };

    private static final String[] BRANDS = new String[]{"Lenovo", "Dell", "HP"};

    DatePickerDialog dobPicker;
    EditText editDate;
    RadioButton rdDesk, rdLap;
    Spinner spnBrand;
    CheckBox chkSSD, chkPrinter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Setting up the Auto Complete Text View for selecting provinces
        ArrayAdapter<String> adptProvinces = new ArrayAdapter<String>(this,
                android.R.layout.simple_dropdown_item_1line, PROVINCES);
        AutoCompleteTextView textView = (AutoCompleteTextView)
                findViewById(R.id.provinces_list);
        textView.setAdapter(adptProvinces);

        editDate = (EditText) findViewById(R.id.date_of_purchase);

        editDate.setInputType(InputType.TYPE_NULL);

        editDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               final Calendar calendar = Calendar.getInstance();
                int day = calendar.get(Calendar.DAY_OF_MONTH);
                int month = calendar.get(Calendar.MONTH);
                int year = calendar.get(Calendar.YEAR);

                //date picker dialog

                dobPicker = new DatePickerDialog(MainActivity.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                        editDate.setText(dayOfMonth + "/" + (month + 1) + "/" + year);
                    }
                },year, month, day);
                dobPicker.show();
            }
        });

        // Setting up the radio buttons for selecting the type of computer

        rdDesk = (RadioButton)findViewById(R.id.rdDesktop);
        rdLap = (RadioButton) findViewById(R.id.rdLaptop);


        // Setting up the Spinner to select the brand for the product.

        spnBrand = (Spinner) findViewById(R.id.spn_brand_item);
        ArrayAdapter<String> adptBrands = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_dropdown_item, BRANDS);

        adptBrands.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spnBrand.setAdapter(adptBrands);

        // Setting up the on Click event listener for the spinner.

        spnBrand.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(getApplicationContext(),"Brand Selected : " + BRANDS[position], Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent){

            }
        });

        // Initializing the CheckBoxes

        chkPrinter = (CheckBox) findViewById(R.id.check_printer);
        chkSSD = (CheckBox) findViewById(R.id.check_SSD);
    }

    public void RdComputerClicked(View view){

        String computerSelected = "Computer Selected";
        if(rdLap.isChecked()) {
            computerSelected = "Laptop";
        }
        if(rdDesk.isChecked()){
            computerSelected = "Desktop";
        }

        Toast.makeText(getApplicationContext(), computerSelected, Toast.LENGTH_SHORT).show();
    }

    public void onChkAddOnClicked(View view) {
        if(chkSSD.isChecked()){
            Toast.makeText(getApplicationContext(), "SSD Selected", Toast.LENGTH_SHORT).show();
        }
        if(chkPrinter.isChecked()){
            Toast.makeText(getApplicationContext(), "Printer Selected", Toast.LENGTH_SHORT).show();
        }
    }
}
