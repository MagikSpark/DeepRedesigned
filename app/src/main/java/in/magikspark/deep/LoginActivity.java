package in.magikspark.deep;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);


        Button login = (Button)findViewById(R.id.button_login);

        login.setOnClickListener(this);


    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.button_login: submitall();break;
        }
    }

    public void submitall(){
        Intent mainIntent = new Intent(LoginActivity.this,Home.class);
        LoginActivity.this.startActivity(mainIntent);
        LoginActivity.this.finish();
    }
}
