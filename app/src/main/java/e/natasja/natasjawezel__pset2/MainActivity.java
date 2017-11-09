package e.natasja.natasjawezel__pset2;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void get_started(View view) {
        Intent intent = new Intent(this, PickActivity.class);
        startActivity(intent);
        /* finish(); to make sure the old activity doesn't exist anymore */
    }
}
