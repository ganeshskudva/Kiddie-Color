package com.gkudva.kidscolor;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.GestureDetector;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.animation.Animation;
import android.view.animation.LinearInterpolator;
import android.view.animation.TranslateAnimation;
import android.widget.Toast;
import android.widget.ViewFlipper;

import com.gkudva.kidscolor.Util.ColorMap;
import com.gkudva.kidscolor.Util.SettingsMenu;

import java.util.Random;

public class KiddieActivity extends AppCompatActivity {

    private ViewFlipper viewFlipper;

    private GestureDetector mGestureDetector;
    private int mCurrentLayoutState, mCount;
    private Random randomGenerator;
    private static final String COLOR = "color";
    private String currentColor;
    private int oldConfigInt;
    private SharedPreferences myPrefLogin;
    private SharedPreferences.Editor prefsEditor;
    private static final String MY_PREFS = "myPreferences";
    private static final String ORIENTATION_CHANGED = "orientationChanged";
    private String fromOrientation;
    private SettingsMenu settingsMenu;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_kiddie);

        myPrefLogin = this.getSharedPreferences(MY_PREFS, Context.MODE_PRIVATE);
        prefsEditor = myPrefLogin.edit();

        fromOrientation = myPrefLogin.getString(ORIENTATION_CHANGED, "false");

        viewFlipper = (ViewFlipper) findViewById(R.id.view_flipper);
        settingsMenu = new SettingsMenu(this);

        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setIcon(R.mipmap.ic_launcher);

        if (fromOrientation.contains("false") )  {
            Toast.makeText(getApplicationContext(), "Swipe Left or Right to begin", Toast.LENGTH_SHORT).show();
        }
        randomGenerator = new Random();
        ColorMap.initializeColorMap(false/*Non Advanced Mode*/);
        mGestureDetector = new GestureDetector(this,
                new GestureDetector.SimpleOnGestureListener() {
                    private static final int SWIPE_THRESHOLD = 100;
                    private static final int SWIPE_VELOCITY_THRESHOLD = 100;
                    @Override
                    public boolean onFling(MotionEvent e1, MotionEvent e2,
                                           float velocityX, float velocityY) {
                        boolean result = false;

                        try {
                            float diffX = e2.getX() - e1.getX();
                            float diffY = e2.getY() - e1.getY();
                            if (Math.abs(diffX) > SWIPE_THRESHOLD && Math.abs(velocityX) > SWIPE_VELOCITY_THRESHOLD)
                            {
                                mCurrentLayoutState = mCurrentLayoutState == 0 ? 1 :0;


                                if (diffX > 0)
                                {
                                    switchLayoutStateTo(mCurrentLayoutState, false);
                                } else {
                                    switchLayoutStateTo(mCurrentLayoutState, true);
                                }

                                result = true;
                            }
                        }catch (Exception e)
                        {
                            e.printStackTrace();
                        }

                        return result;
                    }
                });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        return mGestureDetector.onTouchEvent(event);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (R.id.action_settings == id)
        {
            settingsMenu.dialog.show();
        }

        return super.onOptionsItemSelected(item);
    }

    public void switchLayoutStateTo(int switchTo,
                                    boolean left) {
        String disp;
        mCurrentLayoutState = switchTo;

        viewFlipper.setInAnimation(inFromRightAnimation());
        viewFlipper.setOutAnimation(outToLeftAnimation());

        int randInt = randomGenerator.nextInt(ColorMap.getColorMapSize());
        int randomInt = randomGenerator.nextInt(ColorMap.getColorMapSize());
        randomInt = (randInt + randomInt)%(ColorMap.getColorMapSize());


        if (left == false) {
            viewFlipper.setBackgroundColor(Color.parseColor("#" + ColorMap.getColorHexCode(randomInt)));
        } else {
            viewFlipper.setBackgroundColor(Color.parseColor("#" + ColorMap.getColorHexCode(randomInt)));
        }

        currentColor = ColorMap.getColorHexCode(randomInt);
        Toast.makeText(getApplicationContext(), ColorMap.getColorName(ColorMap.getColorHexCode(randomInt)), Toast.LENGTH_SHORT).show();
        viewFlipper.showPrevious();

    }

    private Animation inFromRightAnimation() {
        Animation inFromRight = new TranslateAnimation(
                Animation.RELATIVE_TO_PARENT, +1.0f,
                Animation.RELATIVE_TO_PARENT, 0.0f,
                Animation.RELATIVE_TO_PARENT, 0.0f,
                Animation.RELATIVE_TO_PARENT, 0.0f);
        inFromRight.setDuration(500);
        inFromRight.setInterpolator(new LinearInterpolator());
        return inFromRight;
    }

    private Animation outToLeftAnimation() {
        Animation outtoLeft = new TranslateAnimation(
                Animation.RELATIVE_TO_PARENT, 0.0f,
                Animation.RELATIVE_TO_PARENT, -1.0f,
                Animation.RELATIVE_TO_PARENT, 0.0f,
                Animation.RELATIVE_TO_PARENT, 0.0f);
        outtoLeft.setDuration(500);
        outtoLeft.setInterpolator(new LinearInterpolator());
        return outtoLeft;
    }

    @Override
    public void onSaveInstanceState(Bundle saveBundle)
    {
        saveBundle.putString(COLOR, currentColor);

        super.onSaveInstanceState(saveBundle);

    }

    @Override
    public void onRestoreInstanceState(Bundle saveBundle)
    {
        super.onRestoreInstanceState(saveBundle);
        currentColor = saveBundle.getString(COLOR);

        if (currentColor != null) {
            viewFlipper.setBackgroundColor(Color.parseColor("#" + currentColor));
        }
        else
        {
            viewFlipper.setBackgroundColor(Color.GRAY);
        }

    }

    @Override
    public void onDestroy()
    {
        super.onDestroy();

        if (!isFinishing())
        {
            prefsEditor.putString(ORIENTATION_CHANGED, "true");
        }
        else
        {
            prefsEditor.putString(ORIENTATION_CHANGED, "false");
        }
        prefsEditor.commit();
    }

    @Override
    public void onPause()
    {
        super.onPause();
        prefsEditor.putString(ORIENTATION_CHANGED, "false");
        prefsEditor.commit();
    }

    @Override
    public void onStop()
    {
        super.onStop();
        prefsEditor.putString(ORIENTATION_CHANGED, "false");
        prefsEditor.commit();
    }

}
