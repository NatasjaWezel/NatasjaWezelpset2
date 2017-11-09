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
    int PlaceholderRemainingCount;
    TextView wordsLeft, wordType;
    EditText whatWord;
    String nextPlaceholder;

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

        wordsLeft = (TextView) findViewById(R.id.wordsLeft);
        wordType = (TextView) findViewById(R.id.wordType);
        whatWord = (EditText) findViewById(R.id.whatWord);

        int PlaceholderCount = story.getPlaceholderCount();
        wordsLeft.setText(PlaceholderCount + " more word(s) to go!");

        nextPlaceholder = story.getNextPlaceholder();
        wordType.setText("Please typ a/an " + nextPlaceholder);
        whatWord.setHint(nextPlaceholder);
    }

    @Override
    public void onSaveInstanceState (Bundle outState) {
        super.onSaveInstanceState(outState);

        // save story
        outState.putSerializable("story", story);

        // save wordType
        outState.putString("to type", nextPlaceholder);

        // save remaining placeholders
        outState.putInt("remainder", story.getPlaceholderRemainingCount());
    }

    @Override
    public void onRestoreInstanceState (Bundle inState) {
        super.onRestoreInstanceState(inState);

        story = (Story) inState.getSerializable("story");

        // get remaining placeholders
        PlaceholderRemainingCount = inState.getInt("remainder");
        nextPlaceholder = inState.getString("to type");
        wordsLeft.setText(PlaceholderRemainingCount + " more word(s) to go!");

        wordType.setText("Please typ a/an " + nextPlaceholder);
        whatWord.setHint(nextPlaceholder);
    }

    public void nextWord(View view) {
        EditText placeholder = (EditText) findViewById(R.id.whatWord);
        String word = placeholder.getText().toString();

        if (word.equals("")) {
            Context context = getApplicationContext();
            CharSequence text = "Please fill in a word!";
            int duration = Toast.LENGTH_SHORT;

            Toast toast = Toast.makeText(context, text, duration);
            toast.show();
        }
        else {

            // get filled in word and save it in the placeholder, clear the editText balk
            story.fillInPlaceholder(word);
            whatWord.setText("");

            // get remaining placeholder count
            PlaceholderRemainingCount = story.getPlaceholderRemainingCount();

            // if remaining count = 0, you're done
            if (PlaceholderRemainingCount == 0) {
                Intent intent = new Intent(this, PrintStoryActivity.class);
                String text = story.toString();
                intent.putExtra("Story", text);

                startActivity(intent);
                finish();
            }

            // get next Placeholder
            nextPlaceholder = story.getNextPlaceholder();

            // set new hints and texts
            wordType.setText("Please typ a/an " + nextPlaceholder);
            wordsLeft.setText(PlaceholderRemainingCount + " more word(s) to go!");
            whatWord.setHint(nextPlaceholder);

        }
    }
}
