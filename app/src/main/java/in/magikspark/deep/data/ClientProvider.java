package in.magikspark.deep.data;

import android.content.ContentProvider;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.support.annotation.Nullable;
import in.magikspark.deep.data.ClientContract.ClientEntry;

/**
 * Created by muku on 21-Sep-16.
 */
public class ClientProvider extends ContentProvider {

    private static final int CLIENTS = 100;
    private static final int CLIENTS_ID = 101;
    private static final UriMatcher sUriMatcher = new UriMatcher(UriMatcher.NO_MATCH);
    static {
        sUriMatcher.addURI(ClientContract.CONTENT_AUTHORITY,ClientContract.PATH_CLIENTS,CLIENTS);
        sUriMatcher.addURI(ClientContract.CONTENT_AUTHORITY,ClientContract.PATH_CLIENTS + "/#",CLIENTS_ID);
    }

    public static final String LOG_TAG = ClientProvider.class.getSimpleName();

    /** Database helper object */
    private ClientDbHelper mDbHelper;

    @Override
    public boolean onCreate() {
        mDbHelper = new ClientDbHelper(getContext());
        return true;
    }

    @Nullable
    @Override
    public Cursor query(Uri uri, String[] projection, String selection, String[] selectionArgs, String sortOrder) {

        SQLiteDatabase database = mDbHelper.getReadableDatabase();
        Cursor cursor;
        int match = sUriMatcher.match(uri);
        switch (match){
            case CLIENTS:
                cursor = database.query(ClientEntry.TABLE_NAME,projection,selection,selectionArgs,null,null,sortOrder);
                break;
            case CLIENTS_ID:
                selection = ClientEntry._ID + "=?";
                selectionArgs = new String[]{String.valueOf(ContentUris.parseId(uri))};
                cursor = database.query(ClientEntry.TABLE_NAME,projection,selection,selectionArgs,null,null,sortOrder);
                break;
            default:
                throw new IllegalArgumentException("Cannot query Unknown URI: "+ uri);
        }
        return cursor;
    }

    @Nullable
    @Override
    public String getType(Uri uri) {
        return null;
    }

    @Nullable
    @Override
    public Uri insert(Uri uri, ContentValues contentValues) {
        return null;
    }

    @Override
    public int delete(Uri uri, String s, String[] strings) {
        return 0;
    }

    @Override
    public int update(Uri uri, ContentValues contentValues, String s, String[] strings) {
        return 0;
    }
}
