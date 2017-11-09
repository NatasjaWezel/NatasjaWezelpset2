package e.natasja.natasjawezel__pset2;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.widget.TextView;

public class PrintStoryActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_print_story);

        Intent intent = getIntent();
        String story = intent.getStringExtra("Story");

        TextView finished = (TextView) findViewById(R.id.Story);
        finished.setMovementMethod(new ScrollingMovementMethod());
        String text = story.toString();
        finished.setText(text);
    }
}
