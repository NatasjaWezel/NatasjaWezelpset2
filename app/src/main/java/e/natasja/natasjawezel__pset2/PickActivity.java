package e.natasja.natasjawezel__pset2;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import java.util.Random;

public class PickActivity extends AppCompatActivity {

    public String story_picked = "";
    RadioGroup radioGroup;
    public String[] textArray = {"simple.txt", "tarzan.txt", "university.txt", "dance.txt", "clothes.txt"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pick);

        radioGroup = findViewById(R.id.radioGroup);
    }


    public void pick(View view) {
        // Is the button now checked?
        boolean checked = ((RadioButton) view).isChecked();

        // Check which radio button was clicked
        switch (view.getId()) {
            case R.id.simple:
                if (checked) {
                    story_picked = textArray[0];
                }
                    break;
            case R.id.tarzan:
                if (checked) {
                    story_picked = textArray[1];
                }
                    break;
            case R.id.university:
                if (checked) {
                    story_picked = textArray[2];
                }
                    break;
            case R.id.dance:
                if (checked) {
                    story_picked = textArray[3];
                }
                    break;
            case R.id.clothes:
                if (checked) {
                    story_picked = textArray[4];
                }
                    break;
            case R.id.randomize:
                if (checked) {
                    Random random = new Random();
                    int i = random.nextInt(textArray.length);
                    story_picked = textArray[i];
                }
                    break;
        }
    }

    public void picked(View view) {
        if (story_picked.equals("")) {
            int radioButtonID = radioGroup.getCheckedRadioButtonId();
            View radioView = findViewById(radioButtonID);
            radioView.performClick();
        }
        Intent intent = new Intent(this, SecondActivity.class);
        intent.putExtra("Story", story_picked);
        startActivity(intent);
        /* finish(); to make sure the old activity doesn't exist anymore */
        finish();
    }
}
