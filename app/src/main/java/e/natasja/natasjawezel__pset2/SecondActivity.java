package e.natasja.natasjawezel__pset2;

import android.content.Intent;
import android.content.res.AssetManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import java.io.IOException;
import java.io.InputStream;

public class SecondActivity extends AppCompatActivity {
    Story story;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        Intent intent = getIntent();
        String story_picked = intent.getStringExtra("Story");

        // open the right story file
        AssetManager assetManager = getAssets();
        InputStream inputStream = null;
        try {
            inputStream = assetManager.open(story_picked);
        }
        catch (IOException e){
            Log.e("message: ",e.getMessage());
        }
        story = new Story(inputStream);

        int PlaceholderCount = story.getPlaceholderCount();
        TextView wordsLeft = (TextView) findViewById(R.id.wordsLeft);
        wordsLeft.setText(PlaceholderCount + " more word(s) to go!");

        String nextPlaceholder = story.getNextPlaceholder();
        EditText placeholder = (EditText) findViewById(R.id.whatWord);
        placeholder.setHint(nextPlaceholder);
    }

    public void nextWord(View view) {
        EditText Placeholder = (EditText) findViewById(R.id.whatWord);

        // get filled in word and save it in the placeholder
        story.fillInPlaceholder(Placeholder.getText().toString());
        Placeholder.setText("");

        int PlaceholderRemainingCount = story.getPlaceholderRemainingCount();

        if (PlaceholderRemainingCount == 0) {
            Intent intent = new Intent(this, PrintStoryActivity.class);
            String text = story.toString();
            intent.putExtra("Story", text);

            startActivity(intent);
        }

        TextView wordsLeft = (TextView) findViewById(R.id.wordsLeft);
        wordsLeft.setText(PlaceholderRemainingCount + " more word(s) to go!");

        String nextPlaceholder = story.getNextPlaceholder();
        Placeholder.setHint(nextPlaceholder);
    }
}
