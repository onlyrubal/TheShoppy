package com.example.assignment1;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.text.InputType;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.ScrollView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Calendar;

public class MainActivity extends AppCompatActivity {

    private static final String[] PROVINCES = new String[] {
            "Ontario", "Quebec", "Nova Scotia", "New Brunswick", "Manitoba", "British Columbia", "Prince Edward Island",
            "Saskatchewan", "Alberta", "Newfoundland and Labrador"
    };

    private static final String[] BRANDS = new String[]{"Lenovo", "Dell", "HP"};

    //Variable Declaration
    TextView txtNameOfPerson;
    DatePickerDialog dobPicker;
    EditText editDate;
    RadioButton rdDesk, rdLap, rdCoolingMat, rdUsbC, rdLaptopStand, rdWebCam, rdHardDrive;
    RadioGroup rdLaptopGroup, rdDesktopGroup;
    Spinner spnBrand;
    CheckBox chkSSD, chkPrinter;
    Button compute;
    int cost = 0;
    String computerSelected;
    String brandSelected;
    AutoCompleteTextView actvProvinceName;
    String peripheralSelected;
    String additionalPeripherals = "";
    ScrollView svDesktop, svLaptop;

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

        // Setting up the Date Picker.
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

        // Setting up the radio buttons references for Desktop and Computer Selection.
        rdDesk = (RadioButton)findViewById(R.id.rdDesktop);
        rdLap = (RadioButton) findViewById(R.id.rdLaptop);

        // Setting up the Spinner Reference for product brand selectiont.
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
                brandSelected = BRANDS[position];
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent){

            }
        });

        // Setting up the references for the check boxes.
        chkPrinter = (CheckBox) findViewById(R.id.check_printer);
        chkSSD = (CheckBox) findViewById(R.id.check_SSD);


        // Setting up the radio buttons for selecting the additional peripherals.
        rdCoolingMat = (RadioButton)findViewById(R.id.rd_cooling_mat);
        rdUsbC = (RadioButton)findViewById(R.id.rd_usb_c);
        rdLaptopStand = (RadioButton)findViewById(R.id.rd_laptop_stand);
        rdWebCam = (RadioButton)findViewById(R.id.rd_web_cam);
        rdHardDrive = (RadioButton)findViewById(R.id.rd_hard_drive);
        rdLaptopGroup = (RadioGroup) findViewById(R.id.rd_laptop_type_of_peripherals);
        rdDesktopGroup = (RadioGroup)findViewById(R.id.rd_computer_type_of_peripheral);

        // Setting up the textfields to get the selected data.
        txtNameOfPerson = (TextView)findViewById(R.id.txtName);
        actvProvinceName = (AutoCompleteTextView)findViewById(R.id.provinces_list);
        svDesktop = (ScrollView)findViewById(R.id.sv_desktop);
        svLaptop = (ScrollView)findViewById(R.id.sv_laptop);
    }

    // Implementing RdComputerClicked method.
    public void RdComputerClicked(View view){
        computerSelected = "Computer Selected";
        if(rdLap.isChecked()) {
            computerSelected = "Laptop";
            svLaptop.setVisibility(View.VISIBLE);
            svDesktop.setVisibility(View.INVISIBLE);
            rdLaptopGroup.setVisibility(View.VISIBLE);
            rdDesktopGroup.setVisibility(View.INVISIBLE);

        }

        if(rdDesk.isChecked()){
            computerSelected = "Desktop";
            svDesktop.setVisibility(View.VISIBLE);
            svLaptop.setVisibility(View.INVISIBLE);

            rdLaptopGroup.setVisibility(View.INVISIBLE);
            rdDesktopGroup.setVisibility(View.VISIBLE);
        }
        Toast.makeText(getApplicationContext(), computerSelected, Toast.LENGTH_SHORT).show();
    }

    //Implementing onChkAddOnClicked method.
    public void onChkAddOnClicked(View view) {
        if(chkSSD.isChecked()){
            Toast.makeText(getApplicationContext(), "SSD Selected", Toast.LENGTH_SHORT).show();
        }
        if(chkPrinter.isChecked()){
            Toast.makeText(getApplicationContext(), "Printer Selected", Toast.LENGTH_SHORT).show();
        }
    }

    // Implementing onManipulateChecked method.
    public void onManipulateChecked(View view) {

        if(rdCoolingMat.isChecked()){
            cost = cost +33;
            peripheralSelected = "Cooling Mat";
        }
        if(rdLaptopStand.isChecked()){
            cost = cost + 139;
            peripheralSelected = "Laptop Stand";
        }
        if(rdUsbC.isChecked()){
            cost = cost + 60;
            peripheralSelected = "USB C";
        }
        if(rdWebCam.isChecked()){
            cost = cost + 95;
            peripheralSelected = "Web Cam";
        }
        if(rdHardDrive.isChecked()){
            cost = cost + 64;
            peripheralSelected = "External Hard Drive";
        }

        if(chkSSD.isChecked()){
            cost = cost + 60;
            additionalPeripherals = additionalPeripherals + "SSD, ";
        }
        if(chkPrinter.isChecked()){
            cost = cost + 100;
            additionalPeripherals = additionalPeripherals + "Printer, ";
        }

        if(computerSelected == "Desktop"){
            if(brandSelected == "Dell"){
                cost = cost + 475;
            }
            if(brandSelected == "Lenovo"){
                cost = cost + 450;
            }
            if(brandSelected == "HP"){
                cost = cost + 400;
            }
        }

        if(computerSelected == "Laptop"){
            if(brandSelected == "Dell"){
                cost = cost + 1249;
            }
            if(brandSelected == "Lenovo"){
                cost = cost + 1549;
            }
            if(brandSelected == "HP"){
                cost = cost + 1150;
            }
        }

        // Total cost and tax calculation.
        Double tax = cost * 0.13;
        Double totalCost = tax + cost;
        String invoice = "Customer : " + txtNameOfPerson.getText().toString() + "\nProvince : " + actvProvinceName.getText().toString() + "\nDate Of Purchase : " +
                editDate.getText().toString() + "\nBrand : " + brandSelected + "\nPeripherals Selected : " + peripheralSelected +
                "\nAdditional Peripherals : " + additionalPeripherals + "\nCost : " + totalCost.toString();

        Toast.makeText(getApplicationContext(), invoice, Toast.LENGTH_LONG).show();

        //Resetting the cost to 0.
        cost = 0;
        additionalPeripherals = "";
    }
}
