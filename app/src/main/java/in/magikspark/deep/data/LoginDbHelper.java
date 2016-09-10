package in.magikspark.deep.data;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import in.magikspark.deep.data.LoginContract.LoginEntry;


public class LoginDbHelper extends SQLiteOpenHelper{


    //public static final String LOG_TAG = ClientDbHelper.class.getSimpleName();

    /** Name of the database file **/
    private static final String DATABASE_NAME = "login.db";
    private static final int DATABASE_VERSION = 1;

    public LoginDbHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String SQL_CREATE_LOGIN_TABLE =
                "CREATE TABLE "+ LoginEntry.TABLE_NAME
                        +" ( "
                        + LoginEntry._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                        + LoginEntry.COLUMN_LOGIN_NAME + " TEXT NOT NULL, "
                        + LoginEntry.COLUMN_LOGIN_EMAIL + " TEXT NOT NULL, "
                        + LoginEntry.COLUMN_LOGIN_PASSWORD + " TEXT NOT NULL, "
                        + LoginEntry.COLUMN_LOGIN_ROLE + " INTEGER NOT NULL DEFAULT 1"
                        + ");";

        db.execSQL(SQL_CREATE_LOGIN_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
