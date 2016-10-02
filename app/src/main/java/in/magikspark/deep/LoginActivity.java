package in.magikspark.deep;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import in.magikspark.deep.data.LoginContract.LoginEntry;
import in.magikspark.deep.data.LoginDbHelper;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener{

    private LoginDbHelper mDbHelper;
    private EditText usrName;
    private EditText usrPass;
    private Button login;
    private Button register;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        mDbHelper = new LoginDbHelper(this);
        SQLiteDatabase db = mDbHelper.getReadableDatabase();

        usrName = (EditText)findViewById(R.id.userName);
        usrPass = (EditText)findViewById(R.id.password);

        login = (Button)findViewById(R.id.button_login);
        register = (Button)findViewById(R.id.button_register);
        login.setOnClickListener(this);
        register.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.button_login:
                submitall();
                break;
            case R.id.button_register:
                registerall();
                break;
        }
    }

    public void submitall(){
        String uname = usrName.getText().toString();
        String pass = usrPass.getText().toString();
        if(verifyLogin(uname,pass)==true){
            open();
            Toast.makeText(LoginActivity.this, "Login Successfull", Toast.LENGTH_SHORT).show();
        }
        else {
            Toast.makeText(LoginActivity.this, "Login Failed", Toast.LENGTH_SHORT).show();
        }
    }

    private void open(){
        Intent mainIntent = new Intent(LoginActivity.this,Home.class);
        LoginActivity.this.startActivity(mainIntent);
        LoginActivity.this.finish();
    }

    private boolean verifyLogin(String uname, String pass){
        SQLiteDatabase db = mDbHelper.getReadableDatabase();

        // Define a projection that specifies which columns from the database
        // you will actually use after this query.
        String[] projection = {
                LoginEntry._ID,
                LoginEntry.COLUMN_LOGIN_NAME,
                LoginEntry.COLUMN_LOGIN_PASSWORD,
                LoginEntry.COLUMN_LOGIN_ROLE
        };

        // Filter results WHERE "title" = 'My Title'
        String selection = LoginEntry.COLUMN_LOGIN_NAME + " = ?";
        String[] selectionArgs = { uname };


        // How you want the results sorted in the resulting Cursor
        String sortOrder =
                LoginEntry.COLUMN_LOGIN_PASSWORD + " DESC";

        Cursor c = db.query(
                LoginEntry.TABLE_NAME,                     // The table to query
                projection,                               // The columns to return
                selection,                                // The columns for the WHERE clause
                selectionArgs,                            // The values for the WHERE clause
                null,                                     // don't group the rows
                null,                                     // don't filter by row groups
                sortOrder                                 // The sort order
        );

        if(c.getCount()==1)
            return true;
        else
            return false;
    }

    private int checkdDbAdminRow(){
        mDbHelper = new LoginDbHelper(this);
        SQLiteDatabase db = mDbHelper.getReadableDatabase();
        int count;

        // Define a projection that specifies which columns from the database
        // you will actually use after this query.
        String[] projection = {
                LoginEntry._ID,
                LoginEntry.COLUMN_LOGIN_NAME
        };

        // Filter results WHERE "title" = 'My Title'
        String selection = LoginEntry.COLUMN_LOGIN_NAME + " = ?";
        String[] selectionArgs = { "admin" };

        // How you  want the results sorted in the resulting Cursor
        String sortOrder = LoginEntry.COLUMN_LOGIN_NAME + " DESC";

        Cursor c = db.rawQuery("select * from "+ LoginEntry.TABLE_NAME+";",null);
        count=c.getCount();

        Log.v("SplashActivity","Total Row Count: "+ count);
        return count;
    }


    // Here is the insert query
    private void insertRow(){
        // Mukund Inserted New Table here
        mDbHelper = new LoginDbHelper(this);
        SQLiteDatabase db = mDbHelper.getWritableDatabase();

        // Create a new map of values, where column names are the keys
        ContentValues values = new ContentValues();
        values.put(LoginEntry.COLUMN_LOGIN_NAME, "admin");
        values.put(LoginEntry.COLUMN_LOGIN_EMAIL, "admin@gmail.com");
        values.put(LoginEntry.COLUMN_LOGIN_PASSWORD, "admin");
        values.put(LoginEntry.COLUMN_LOGIN_ROLE, LoginEntry.ROLE_ADMIN);

        // Insert the new row, returning the primary key value of the new row
        long newRowId = db.insert(LoginEntry.TABLE_NAME, null, values);

        Log.v("SplashActivity","New Row ID: "+ newRowId);
    }

    private void registerall(){
        int c = checkdDbAdminRow();
        if(c<1) {
            insertRow();
        }
    }
}
