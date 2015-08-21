package qwerty.cadence;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class WheelSettings extends Activity {

    private TextView frontStar[];
    private TextView rearStar[];
    private TextView circuit;

    public static final String APP_PREFERENCES = "syzeSettings";
    public static final String APP_FIRST_FRONT_STAR_PREFERENCES = "first_front_star";
    public static final String APP_SECOND_FRONT_STAR_PREFERENCES = "second_front_star";
    public static final String APP_THIRD_FRONT_STAR_PREFERENCES = "third_front_star";
    public static final String APP_FIRST_REAR_STAR_PREFERENCES = "first_rear_star";
    public static final String APP_SECOND_REAR_STAR_PREFERENCES = "second_rear_star";
    public static final String APP_THIRD_REAR_STAR_PREFERENCES = "third_rear_star";
    public static final String APP_FORTH_REAR_STAR_PREFERENCES = "forth_rear_star";
    public static final String APP_FIFTH_REAR_STAR_PREFERENCES = "fifth_rear_star";
    public static final String APP_SIXTH_REAR_STAR_PREFERENCES = "sixth_rear_star";
    public static final String APP_SEVENTH_REAR_STAR_PREFERENCES = "seventh_rear_star";
    public static final String APP_EIGHTH_REAR_STAR_PREFERENCES = "eighth_rear_star";
    public static final String APP_NINETH_REAR_STAR_PREFERENCES = "nineth_rear_star";
    public static final String APP_TENTH_REAR_STAR_PREFERENCES = "tenth_rear_star";

  public static final String APP_CIRCUIT_PREFERENCES = "circuit";
    private SharedPreferences mSettings;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);
        frontStar = new TextView[4];
        rearStar = new TextView[11];

        frontStar[1]=(TextView)findViewById(R.id.frontStarInputFirst);
        frontStar[2]=(TextView)findViewById(R.id.frontStarInputSecond);
        frontStar[3]=(TextView)findViewById(R.id.frontStarInputThird);

        rearStar[1]=(TextView)findViewById(R.id.rearStarInputFirst);
        rearStar[2]=(TextView)findViewById(R.id.rearStarInputSecond);
        rearStar[3]=(TextView)findViewById(R.id.rearStarInputThird);
        rearStar[4]=(TextView)findViewById(R.id.rearStarInputFourth);
        rearStar[5]=(TextView)findViewById(R.id.rearStarInputFifth);
        rearStar[6]=(TextView)findViewById(R.id.rearStarInputSixth);
        rearStar[7]=(TextView)findViewById(R.id.rearStarInputSeventh);
        rearStar[8]=(TextView)findViewById(R.id.rearStarInputEighth);
        rearStar[9]=(TextView)findViewById(R.id.rearStarInputNinth);
        rearStar[10]=(TextView)findViewById(R.id.rearStarInputTenth);

        circuit=(TextView)findViewById(R.id.circuitInput);

        mSettings = getSharedPreferences(APP_PREFERENCES, Context.MODE_PRIVATE);

            // Getting data from settings file and show on screen
        if (mSettings.contains(APP_FIRST_REAR_STAR_PREFERENCES)) {
            rearStar[1].setText(String.valueOf(mSettings.getString(APP_FIRST_REAR_STAR_PREFERENCES, "16")));//
        }
        if (mSettings.contains(APP_SECOND_REAR_STAR_PREFERENCES)) {
            rearStar[2].setText(String.valueOf(mSettings.getString(APP_SECOND_REAR_STAR_PREFERENCES, "")));
        }
        if (mSettings.contains(APP_THIRD_REAR_STAR_PREFERENCES)) {
            rearStar[3].setText(String.valueOf(mSettings.getString(APP_THIRD_REAR_STAR_PREFERENCES, "")));
        }
        if (mSettings.contains(APP_FORTH_REAR_STAR_PREFERENCES)) {
            rearStar[4].setText(String.valueOf(mSettings.getString(APP_FORTH_REAR_STAR_PREFERENCES, "")));
        }
        if (mSettings.contains(APP_FIFTH_REAR_STAR_PREFERENCES)) {
            rearStar[5].setText(String.valueOf(mSettings.getString(APP_FIFTH_REAR_STAR_PREFERENCES, "")));
        }
        if (mSettings.contains(APP_SIXTH_REAR_STAR_PREFERENCES)) {
            rearStar[6].setText(String.valueOf(mSettings.getString(APP_SIXTH_REAR_STAR_PREFERENCES, "")));
        }
        if (mSettings.contains(APP_SEVENTH_REAR_STAR_PREFERENCES)) {
            rearStar[7].setText(String.valueOf(mSettings.getString(APP_SEVENTH_REAR_STAR_PREFERENCES, "")));
        }
        if (mSettings.contains(APP_EIGHTH_REAR_STAR_PREFERENCES)) {
             rearStar[8].setText(String.valueOf(mSettings.getString(APP_EIGHTH_REAR_STAR_PREFERENCES, "")));
        }
        if (mSettings.contains(APP_NINETH_REAR_STAR_PREFERENCES)) {
            rearStar[9].setText(String.valueOf(mSettings.getString(APP_NINETH_REAR_STAR_PREFERENCES, "")));
        }
        if (mSettings.contains(APP_TENTH_REAR_STAR_PREFERENCES)) {
            rearStar[10].setText( String.valueOf(mSettings.getString(APP_TENTH_REAR_STAR_PREFERENCES, "")));
        }

        if (mSettings.contains(APP_FIRST_FRONT_STAR_PREFERENCES)) {
            frontStar[1].setText(String.valueOf(mSettings.getString(APP_FIRST_FRONT_STAR_PREFERENCES, "56")));
        }
        if (mSettings.contains(APP_SECOND_FRONT_STAR_PREFERENCES)) {
            frontStar[2].setText(String.valueOf(mSettings.getString(APP_SECOND_FRONT_STAR_PREFERENCES, "")));
        }
        if (mSettings.contains(APP_THIRD_FRONT_STAR_PREFERENCES)) {
            frontStar[3].setText(String.valueOf(mSettings.getString(APP_THIRD_FRONT_STAR_PREFERENCES, "")));
        }
        if (mSettings.contains(APP_CIRCUIT_PREFERENCES)) {
            circuit.setText(String.valueOf(mSettings.getInt(APP_CIRCUIT_PREFERENCES, 215)));
        }

        //Set front star focus
        frontStar[1].setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View view, int keyCode, KeyEvent keyEvent) {
                if(frontStar[1].getText().length()==2 &&keyCode==67 ){
                    frontStar[1].setText("");
                }
                if (frontStar[1].getText().length()==2){
                    frontStar[2].requestFocus();
                    return true;
                }
                return false;
            }
        });
        frontStar[2].setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View view, int keyCode, KeyEvent keyEvent) {
                if(frontStar[2].getText().length()==2 && keyCode==67){
                    frontStar[2].setText("");
                }
                if (frontStar[2].getText().length()==2){
                    frontStar[3].requestFocus();
                    return true;
                }
                return false;
            }
        });
        frontStar[3].setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View view, int keyCode, KeyEvent keyEvent) {
                if(frontStar[3].getText().length()==2 && keyCode==67){
                    frontStar[3].setText("");
                }
                if (frontStar[3].getText().length()==2){
                    rearStar[1].requestFocus(); //set focus to rear star
                    return true;
                }
                return false;
            }
        });

        rearStar[1].setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View view, int keyCode, KeyEvent keyEvent) {
                if(rearStar[1].getText().length()==2 && keyCode==67){
                    rearStar[1].setText("");
                }
                if (rearStar[1].getText().length()==2){
                    rearStar[2].requestFocus(); //set focus to rear star
                    return true;
                }
                return false;
            }
        });
        rearStar[2].setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View view, int keyCode, KeyEvent keyEvent) {
                if(rearStar[2].getText().length()==2 && keyCode==67){
                    rearStar[2].setText("");
                }
                if (rearStar[2].getText().length()==2){
                    rearStar[3].requestFocus(); //set focus to rear star
                    return true;
                }
                return false;
            }
        });
        rearStar[3].setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View view, int keyCode, KeyEvent keyEvent) {
                if(rearStar[3].getText().length()==2 && keyCode==67){
                    rearStar[3].setText("");
                }
                if (rearStar[3].getText().length()==2){
                    rearStar[4].requestFocus(); //set focus to rear star
                    return true;
                }
                return false;
            }
        });
        rearStar[4].setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View view, int keyCode, KeyEvent keyEvent) {
                if(rearStar[4].getText().length()==2 && keyCode==67){
                    rearStar[4].setText("");
                }
                if (rearStar[4].getText().length()==2){
                    rearStar[5].requestFocus(); //set focus to rear star
                    return true;
                }
                return false;
            }
        });
        rearStar[5].setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View view, int keyCode, KeyEvent keyEvent) {
                if(rearStar[5].getText().length()==2 && keyCode==67){
                    rearStar[5].setText("");
                }
                if (rearStar[5].getText().length()==2){
                    rearStar[6].requestFocus(); //set focus to rear star
                    return true;
                }
                return false;
            }
        });
        rearStar[6].setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View view, int keyCode, KeyEvent keyEvent) {
                if(rearStar[6].getText().length()==2 && keyCode==67){
                    rearStar[6].setText("");
                }
                if (rearStar[6].getText().length()==2){
                    rearStar[7].requestFocus(); //set focus to rear star
                    return true;
                }
                return false;
            }
        });
        rearStar[7].setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View view, int keyCode, KeyEvent keyEvent) {
                if(rearStar[7].getText().length()==2 && keyCode==67){
                    rearStar[7].setText("");
                }
                if (rearStar[7].getText().length()==2){
                    rearStar[8].requestFocus(); //set focus to rear star
                    return true;
                }
                return false;
            }
        });
        rearStar[8].setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View view, int keyCode, KeyEvent keyEvent) {
                if(rearStar[8].getText().length()==2 && keyCode==67){
                    rearStar[8].setText("");
                }
                if (rearStar[8].getText().length()==2){
                    rearStar[9].requestFocus(); //set focus to rear star
                    return true;
                }
                return false;
            }
        });
        rearStar[9].setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View view, int keyCode, KeyEvent keyEvent) {
                if(rearStar[9].getText().length()==2 && keyCode==67){
                    rearStar[9].setText("");
                }
                if (rearStar[9].getText().length()==2){
                    rearStar[10].requestFocus(); //set focus to rear star
                    return true;
                }
                return false;
            }
        });
        rearStar[10].setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View view, int keyCode, KeyEvent keyEvent) {
                if(rearStar[10].getText().length()==2 && keyCode==67){
                    rearStar[10].setText("");
                }
                if (rearStar[10].getText().length()==2){
                    circuit.requestFocus(); //set focus to rear star
                    return true;
                }
                return false;
            }
        });
    }
    @Override
    protected void onPause() {
        super.onPause();
    }
    @Override
    protected void onResume() {
        super.onResume();
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_test_screen2, menu);
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

  public void startBtnClick(View view) {

        Editor editor = mSettings.edit();
        //Writing data to settings file
      if (checkWithRegExp(String.valueOf(frontStar[1].getText()))){   //checking digits in textbox. If exist write it, else write null
      editor.putString(APP_FIRST_FRONT_STAR_PREFERENCES, (String.valueOf(frontStar[1].getText())));}
      else {
          editor.putString(APP_FIRST_FRONT_STAR_PREFERENCES,null);
      }
      if (checkWithRegExp(String.valueOf(frontStar[2].getText()))){
          editor.putString(APP_SECOND_FRONT_STAR_PREFERENCES,(String.valueOf(frontStar[2].getText())));
      }
      else {
          editor.putString(APP_SECOND_FRONT_STAR_PREFERENCES,null);
      }
      if (checkWithRegExp(String.valueOf(frontStar[3].getText()))){
          editor.putString(APP_THIRD_FRONT_STAR_PREFERENCES, (String.valueOf(frontStar[3].getText())));
      }
      else {
          editor.putString(APP_THIRD_FRONT_STAR_PREFERENCES,null);
      }

      if (checkWithRegExp(String.valueOf(rearStar[1].getText()))){
          editor.putString(APP_FIRST_REAR_STAR_PREFERENCES,(String.valueOf(rearStar[1].getText())));
      }
      else {
          editor.putString(APP_FIRST_REAR_STAR_PREFERENCES,null);
      }
      if (checkWithRegExp(String.valueOf(rearStar[2].getText()))){
          editor.putString(APP_SECOND_REAR_STAR_PREFERENCES,(String.valueOf(rearStar[2].getText())));
      }
      else {
          editor.putString(APP_SECOND_REAR_STAR_PREFERENCES,null);
      }
      if (checkWithRegExp(String.valueOf(rearStar[3].getText()))){
          editor.putString(APP_THIRD_REAR_STAR_PREFERENCES,(String.valueOf(rearStar[3].getText())));
      }
      else {
          editor.putString(APP_THIRD_REAR_STAR_PREFERENCES,null);
      }
      if (checkWithRegExp(String.valueOf(rearStar[4].getText()))){
          editor.putString(APP_FORTH_REAR_STAR_PREFERENCES,(String.valueOf(rearStar[4].getText())));
      }
      else {
          editor.putString(APP_FORTH_REAR_STAR_PREFERENCES,null);
      }
      if (checkWithRegExp(String.valueOf(rearStar[5].getText()))){
          editor.putString(APP_FIFTH_REAR_STAR_PREFERENCES,(String.valueOf(rearStar[5].getText())));
      }
      else {
          editor.putString(APP_FIFTH_REAR_STAR_PREFERENCES,null);
      }
      if (checkWithRegExp(String.valueOf(rearStar[6].getText()))){
          editor.putString(APP_SIXTH_REAR_STAR_PREFERENCES,(String.valueOf(rearStar[6].getText())));
      }
      else {
          editor.putString(APP_SIXTH_REAR_STAR_PREFERENCES,null);
      }
      if (checkWithRegExp(String.valueOf(rearStar[7].getText()))){
          editor.putString(APP_SEVENTH_REAR_STAR_PREFERENCES,(String.valueOf(rearStar[7].getText())));
      }
      else {
          editor.putString(APP_SEVENTH_REAR_STAR_PREFERENCES,null);
      }
      if (checkWithRegExp(String.valueOf(rearStar[8].getText()))){
          editor.putString(APP_EIGHTH_REAR_STAR_PREFERENCES,(String.valueOf(rearStar[8].getText())));
      }
      else {
          editor.putString(APP_EIGHTH_REAR_STAR_PREFERENCES,null);
      }
      if (checkWithRegExp(String.valueOf(rearStar[9].getText()))){
          editor.putString(APP_NINETH_REAR_STAR_PREFERENCES,(String.valueOf(rearStar[9].getText())));
      }
      else {
          editor.putString(APP_NINETH_REAR_STAR_PREFERENCES,null);
      }
      if (checkWithRegExp(String.valueOf(rearStar[10].getText()))){
          editor.putString(APP_TENTH_REAR_STAR_PREFERENCES, (String.valueOf(rearStar[10].getText())));}
       else {
          editor.putString(APP_TENTH_REAR_STAR_PREFERENCES,null);
      }


      editor.putInt(APP_CIRCUIT_PREFERENCES,Integer.parseInt(String.valueOf(circuit.getText())));
      editor.commit();     // editor.apply();  don't work at android2.2 and lower

      Intent intent = new Intent(this, cadence.class);
      intent.putExtra("pref", APP_PREFERENCES); //save data to file pref
      startActivity(intent);    //goto cadence activity

    }
    public static boolean checkWithRegExp(String userNameString){
        Pattern p = Pattern.compile("\\d+");
        Matcher m = p.matcher(userNameString);
        return m.matches();
    }
}
