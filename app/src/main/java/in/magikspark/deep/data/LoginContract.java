package in.magikspark.deep.data;

import android.provider.BaseColumns;

/**
 * Created by muku on 09-Sep-16.
 */
public final class LoginContract {

    private LoginContract(){}

    public static final class LoginEntry implements BaseColumns{

        public final static String TABLE_NAME = "login";

        public final static String _ID = BaseColumns._ID;
        public final static String COLUMN_LOGIN_NAME ="uname";
        public final static String COLUMN_LOGIN_EMAIL ="email";
        public final static String COLUMN_LOGIN_PASSWORD = "password";
        public final static String COLUMN_LOGIN_ROLE = "role";

        public static final int ROLE_ADMIN = 0;
        public static final int ROLE_USER = 1;
        public static final int ROLE_DEVELOPER = 2;
    }

}
