package sg.edu.rp.c346.reservation;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import java.util.Calendar;

public class MainActivity extends AppCompatActivity {

    TextView tvName;
    TextView tvPhone;
    TextView tvSize;
    TextView tvDay;
    TextView tvTime;
    EditText eTDay;
    EditText etTime;

    EditText etNameInput;
    EditText etPhoneInput;
    EditText etSizeInput;
    Button btnBook;
    Button btnCancel;
    CheckBox cbSmoking;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tvName = findViewById(R.id.textViewName);
        tvPhone = findViewById(R.id.textViewPhone);
        tvSize = findViewById(R.id.textViewSize);
        tvTime = findViewById(R.id.textViewTime);
        tvDay = findViewById(R.id.textViewDay);
        etTime = findViewById(R.id.editTextTime);
        eTDay = findViewById(R.id.editTextDay);

        etNameInput = findViewById(R.id.nameInput);
        etPhoneInput = findViewById(R.id.phoneInput);
        etSizeInput = findViewById(R.id.sizeInput2);
        btnBook = findViewById(R.id.book);
        btnCancel = findViewById(R.id.Cancel);
        cbSmoking = findViewById(R.id.smoke);



        eTDay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DatePickerDialog.OnDateSetListener myDateListener = new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker datePicker, int year, int monthOfYear, int dayOfMonth) {
                        eTDay.setText("Date: " + dayOfMonth + "/" + (monthOfYear + 1) + "/" + year);

                    }
                };

                Calendar date = Calendar.getInstance();
                int day = date.get(Calendar.DAY_OF_MONTH);

                int month = date.get(Calendar.MONTH);

                int year = date.get(Calendar.YEAR);

                DatePickerDialog myDateDialog = new DatePickerDialog(MainActivity.this, myDateListener, day, month, year);
                myDateDialog.show();



            }
        });

        etTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                TimePickerDialog.OnTimeSetListener myDateListener = new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                        etTime.setText("Time: "+ hourOfDay + ":" + minute);

                    }
                };
                Calendar date= Calendar.getInstance();
                int hour = date.get(Calendar.HOUR);
                int min = date.get(Calendar.MINUTE);

                TimePickerDialog myTimeDialog = new TimePickerDialog(MainActivity.this, myDateListener, hour,min,true);
                myTimeDialog.show();

            }
        });



        btnBook.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {



                String name = etNameInput.getText().toString();
                String phone1 = etPhoneInput.getText().toString();
                String size1 = etSizeInput.getText().toString();
                String time = etTime.getText().toString();
                String date = eTDay.getText().toString();
               String text = "";


                if(cbSmoking.isChecked()){
                      text = "yes";
                }
                else{
                     text = "no";

                }

                AlertDialog.Builder myBuilder = new AlertDialog.Builder(MainActivity.this);

                //myBuilder.setTitle("Demo 1-simple Dialog");
                //myBuilder.setMessage("i can develop android app");
                //myBuilder.setCancelable(false);
                //myBuilder.setPositiveButton("close", null);

                myBuilder.setTitle("Confirm your Order");
                myBuilder.setMessage("New Reservation"+"\n" + "Name: " + name +"\n" + "Smoking: " + text +"\n" + "Size: "+size1 +"\n" + date +"\n"+time);

                myBuilder.setCancelable(false);
                myBuilder.setNegativeButton("cancel", null);
                myBuilder.setPositiveButton("confirm", null);

                AlertDialog myDialog = myBuilder.create();
                myDialog.show();

            }



        });
        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                etNameInput.setText("");
                etPhoneInput.setText("");
                etSizeInput.setText("");
                Calendar date= Calendar.getInstance();
                int hour = date.get(Calendar.HOUR);
                int min = date.get(Calendar.MINUTE);
                etTime.setText("time: "+hour +":"+min );
                int day = date.get(Calendar.DAY_OF_MONTH);
                int month = date.get(Calendar.MONTH);
                int year = date.get(Calendar.YEAR);
                eTDay.setText("Date: "+day+"/"+(month+1) +"/"+year);
                cbSmoking.setChecked(false);


            }
        });

    }
    @Override
    protected void onPause() {
        super.onPause();
        String name = etNameInput.getText().toString();
        String size1 = etSizeInput.getText().toString();
        String time = etTime.getText().toString();
        String date = eTDay.getText().toString();
        String phone1 = etPhoneInput.getText().toString();
        boolean smoke = cbSmoking.isChecked();



        // obtain an instance of sharedpreferences
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(this);
        // obtain an instance of the sharedPreference Editor for update later
        SharedPreferences.Editor prefEdit = prefs.edit();
        // add the key-value pair
        prefEdit.putString("time", time );
        prefEdit.putString("date", date );
        prefEdit.putString("name", name );
        prefEdit.putString("size", size1 );
        prefEdit.putString("phone", phone1 );
        prefEdit.putBoolean("smoke", smoke );

        // call commit() method to save the changes into sharedpreferences
        prefEdit.commit();
    }



    @Override
    protected void onResume() {
        super.onResume();

        //obtain an instance of the sharedpreferences
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(this);
        String result = prefs.getString("time", "");
        String date = prefs.getString("date","");
        String name = prefs.getString("name","");
        String size = prefs.getString("size","");
        String phone = prefs.getString("phone","");
        boolean smoke = prefs.getBoolean("smoke",false);

        etTime.setText( result);
        eTDay.setText(date);
        etNameInput.setText(name);
        etSizeInput.setText(size);
        cbSmoking.setChecked(smoke);
        etPhoneInput.setText(phone);


    }
}
