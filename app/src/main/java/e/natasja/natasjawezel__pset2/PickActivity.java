package e.natasja.natasjawezel__pset2;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.RadioButton;

public class PickActivity extends AppCompatActivity {

    public String story_picked = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pick);
    }

    public void pick(View view) {
        // Is the button now checked?
        boolean checked = ((RadioButton) view).isChecked();

        // Check which radio button was clicked
        switch (view.getId()) {
            case R.id.simple:
                if (checked) {
                    story_picked = "simple.txt";
                }
                    break;
            case R.id.tarzan:
                if (checked) {
                    story_picked = "tarzan.txt";
                }
                    break;
            case R.id.university:
                if (checked) {
                    story_picked = "university.txt";
                }
                    break;
            case R.id.dance:
                if (checked) {
                    story_picked = "dance.txt";
                }
                    break;
            case R.id.clothes:
                if (checked) {
                    story_picked = "clothes.txt";
                }
                    break;
            case R.id.randomize:
                if (checked) {
                    story_picked = "clothes.txt";
                }
                    break;
        }
    }

    public void picked(View view) {
        Intent intent = new Intent(this, SecondActivity.class);
        intent.putExtra("Story", story_picked);
        startActivity(intent);
        /* finish(); to make sure the old activity doesn't exist anymore */
    }
}
