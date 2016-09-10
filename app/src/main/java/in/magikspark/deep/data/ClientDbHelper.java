package in.magikspark.deep.data;

import android.content.Context;
import android.database.StaleDataException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.text.StaticLayout;
import in.magikspark.deep.data.ClientContract.ClientEntry;

/**
 * Created by muku on 09-Sep-16.
 */
public class ClientDbHelper extends SQLiteOpenHelper {

    public static final String LOG_TAG = ClientDbHelper.class.getSimpleName();

    /** Name of the database file **/
    private static final String DATABASE_NAME = "deep.db";
    private static final int DATABASE_VERSION = 1;

    public ClientDbHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String SQL_CREATE_CLIENTS_TABLE =
                "CREATE TABLE "+ ClientEntry.TABLE_NAME
                        +" ( "
                        + ClientEntry._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                        + ClientEntry.COLUMN_CLIENT_NAME + " TEXT NOT NULL, "
                        + ClientEntry.COLUMN_CLIENT_GENDER + " INTEGER NOT NULL DEFAULT 0, "
                        + ClientEntry.COLUMN_CLIENT_ADDRESS + " TEXT, "
                        + ClientEntry.COLUMN_CLIENT_SOCIETY + " TEXT, "
                        + ClientEntry.COLUMN_CLIENT_PHONE + " TEXT, "
                        + ClientEntry.COLUMN_CLIENT_EMAIL + "TEXT "
                + ");";

        db.execSQL(SQL_CREATE_CLIENTS_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
