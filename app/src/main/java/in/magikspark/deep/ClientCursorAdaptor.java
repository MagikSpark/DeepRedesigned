package in.magikspark.deep;

import android.content.Context;
import android.database.Cursor;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CursorAdapter;
import android.widget.TextView;

import in.magikspark.deep.R;
import in.magikspark.deep.data.ClientContract.ClientEntry;

/**
 * Created by muku on 24-Sep-16.
 */
public class ClientCursorAdaptor extends CursorAdapter {

    public ClientCursorAdaptor(Context context, Cursor c) {
        super(context, c,0/*flags*/);
    }

    @Override
    public View newView(Context context, Cursor cursor, ViewGroup parent) {
        return LayoutInflater.from(context).inflate(R.layout.clients_cardview, parent, false);
    }

    @Override
    public void bindView(View view, Context context, Cursor cursor) {
        // Find fields to populate in inflated template
        TextView tvCName = (TextView) view.findViewById(R.id.client_name);
        TextView tvRef = (TextView) view.findViewById(R.id.ref_no);
        TextView tvSociety = (TextView) view.findViewById(R.id.soc_name);
        TextView tvContact = (TextView) view.findViewById(R.id.con_no);

        int cnameColumnIndex = cursor.getColumnIndexOrThrow(ClientEntry.COLUMN_CLIENT_NAME);
        int refColumnIndex = cursor.getColumnIndexOrThrow(ClientEntry._ID);
        int socColumnIndex = cursor.getColumnIndexOrThrow(ClientEntry.COLUMN_CLIENT_SOCIETY);
        int conColumnIndex = cursor.getColumnIndexOrThrow(ClientEntry.COLUMN_CLIENT_PHONE);

        // Extract properties from cursor
        String cname = cursor.getString(cnameColumnIndex);
        int ref = cursor.getInt(refColumnIndex);
        String soc = cursor.getString(socColumnIndex);
        String con = cursor.getString(conColumnIndex);

        // Populate fields with extracted properties
        tvCName.setText(cname);
        tvRef.setText(String.valueOf(ref));
        tvSociety.setText(soc);
        tvContact.setText(con);

    }
}
