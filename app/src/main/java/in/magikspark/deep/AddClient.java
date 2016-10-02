package in.magikspark.deep;

import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.NavUtils;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import in.magikspark.deep.data.ClientContract.ClientEntry;
import in.magikspark.deep.data.ClientDbHelper;

public class AddClient extends AppCompatActivity {

    private EditText clientName;
    private EditText clientSociety;
    private EditText clientAddress;
    private EditText clientPhone;
    //private EditText clientEmail;
    private Spinner mGenderSpinner;

    private ClientDbHelper mDbHelper;
    private int mGender = 0;
    private String name;
    private String society;
    private String address;
    private String phone;
    //private String emailString;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_client);



        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        // Enable up icon
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


        clientName = (EditText)findViewById(R.id.add_client_name);
        clientAddress = (EditText)findViewById(R.id.add_client_address);
        //clientEmail = (EditText)findViewById(R.id.add_client_email);
        clientPhone = (EditText)findViewById(R.id.add_client_phone);
        clientSociety = (EditText)findViewById(R.id.add_client_society);
        mGenderSpinner = (Spinner)findViewById(R.id.add_client_gender);
        setupSpinner();



        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                name = clientName.getText().toString().trim();
                address = clientAddress.getText().toString().toLowerCase().trim();
                society = clientSociety.getText().toString().toLowerCase().trim();
                phone = clientPhone.getText().toString().trim();
                //emailString = clientEmail.getText().toString().toLowerCase().trim();


                    long ir = insertRow();

                if(ir==-1){
                    Snackbar.make(view,"Error in inserting the row",Snackbar.LENGTH_SHORT).show();
                }else{
                    Snackbar.make(view,"Row inserted Successfully. ID: "+ir ,Snackbar.LENGTH_SHORT).show();
                }

            }
        });
    }



    private void setupSpinner() {
        // Create adapter for spinner. The list options are from the String array it will use
        // the spinner will use the default layout
        ArrayAdapter genderSpinnerAdapter = ArrayAdapter.createFromResource(this,
                R.array.array_gender_options, android.R.layout.simple_spinner_item);

        // Specify dropdown layout style - simple list view with 1 item per line
        genderSpinnerAdapter.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line);

        // Apply the adapter to the spinner
        mGenderSpinner.setAdapter(genderSpinnerAdapter);

        // Set the integer mSelected to the constant values
        mGenderSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String selection = (String) parent.getItemAtPosition(position);
                if (!TextUtils.isEmpty(selection)) {
                    if (selection.equals(getString(R.string.gender_male))) {
                        mGender = 1; // Male
                    } else if (selection.equals(getString(R.string.gender_female))) {
                        mGender = 2; // Female
                    } else {
                        mGender = 0; // Unknown
                    }
                }
            }

            // Because AdapterView is an abstract class, onNothingSelected must be defined
            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                mGender = 0; // Unknown
            }
        });
    }

    // Here is the insert query
    private long insertRow(){
        // Mukund Inserted New Table here
        mDbHelper = new ClientDbHelper(this);
        SQLiteDatabase db = mDbHelper.getWritableDatabase();

        // Create a new map of values, where column names are the keys
        ContentValues values = new ContentValues();
        values.put(ClientEntry.COLUMN_CLIENT_NAME, name);
        values.put(ClientEntry.COLUMN_CLIENT_GENDER, mGender);
        values.put(ClientEntry.COLUMN_CLIENT_SOCIETY, society);
        values.put(ClientEntry.COLUMN_CLIENT_ADDRESS, address);
        values.put(ClientEntry.COLUMN_CLIENT_PHONE, phone);
        //values.put(ClientEntry.COLUMN_CLIENT_EMAIL, emailString);

        // Insert the new row, returning the primary key value of the new row
        long newRowId = db.insert(ClientEntry.TABLE_NAME, null, values);


        Log.v("ClientActivity","New Row ID: "+ newRowId);
        return newRowId;
    }


    /*@Override
    public boolean onCreateOptionsMenu(Menu menu){
        getMenuInflater().inflate(R.menu.home,menu);
        return true;
    }*/


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            // This is the up button
            case android.R.id.home:
                Intent upintent = new Intent(AddClient.this, Home.class);
                //NavUtils.navigateUpTo(this,upintent);
                NavUtils.navigateUpFromSameTask(this);
                // overridePendingTransition(R.animator.anim_left, R.animator.anim_right);
                return true;
            default:return super.onOptionsItemSelected(item);
        }

    }


}
