package e.natasja.natasjawezel__pset2;

import android.content.Context;
import android.content.Intent;
import android.content.res.AssetManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

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
        TextView wordType = (TextView) findViewById(R.id.wordType);
        wordType.setText("Please typ a/an " + nextPlaceholder);
        placeholder.setHint(nextPlaceholder);
    }

    public void nextWord(View view) {
        EditText placeholder = (EditText) findViewById(R.id.whatWord);
        String word = placeholder.getText().toString();

        TextView wordsLeft = (TextView) findViewById(R.id.wordsLeft);
        TextView wordType = (TextView) findViewById(R.id.wordType);


        if (word.equals("")) {
            Context context = getApplicationContext();
            CharSequence text = "Please fill in a word!";
            int duration = Toast.LENGTH_SHORT;

            Toast toast = Toast.makeText(context, text, duration);
            toast.show();
        }
        else {

            // get filled in word and save it in the placeholder
            story.fillInPlaceholder(word);
            placeholder.setText("");

            int PlaceholderRemainingCount = story.getPlaceholderRemainingCount();

            if (PlaceholderRemainingCount == 0) {
                Intent intent = new Intent(this, PrintStoryActivity.class);
                String text = story.toString();
                intent.putExtra("Story", text);

                startActivity(intent);
                finish();
            }

            String nextPlaceholder = story.getNextPlaceholder();

            wordType.setText("Please typ a/an " + nextPlaceholder);
            wordsLeft.setText(PlaceholderRemainingCount + " more word(s) to go!");
            placeholder.setHint(nextPlaceholder);

        }
    }
}
