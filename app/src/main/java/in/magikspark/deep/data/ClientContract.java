package in.magikspark.deep.data;

import android.net.Uri;
import android.provider.BaseColumns;

/**
 * Created by muku on 09-Sep-16.
 */
public final class ClientContract {
    private ClientContract(){}

    public static final String CONTENT_AUTHORITY = "in.magikspark.deep";
    public static final Uri BASE_CONTENT_URI = Uri.parse("content://" + CONTENT_AUTHORITY);
    public static final String PATH_CLIENTS = "clients";

    public static final class ClientEntry implements BaseColumns{

        public static final Uri CONTENT_URI = Uri.withAppendedPath(BASE_CONTENT_URI, PATH_CLIENTS);

        public final static String TABLE_NAME = "clients";

        public final static String _ID = BaseColumns._ID;
        public final static String COLUMN_CLIENT_NAME = "name";
        public final static String COLUMN_CLIENT_GENDER = "gender";
        public final static String COLUMN_CLIENT_SOCIETY = "society";
        public final static String COLUMN_CLIENT_ADDRESS = "address";
        public final static String COLUMN_CLIENT_PHONE = "phone";
        //public final static String COLUMN_CLIENT_EMAIL = "email";

        public static final int GENDER_UNKNOWN = 0;
        public static final int GENDER_MALE = 1;
        public static final int GENDER_FEMALE = 2;
    }
}
