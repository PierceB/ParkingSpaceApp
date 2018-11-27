package com.wits.witsparkingspace;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Handler;
import android.support.design.internal.NavigationMenu;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.LinearInterpolator;
import android.widget.Toast;

import android.util.Log;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import org.json.JSONException;
import org.json.JSONObject;



public class activity_east_campus extends AppCompatActivity {

    FloatingActionButton fab1,fab2,fab3,fab4;

    // Will show the string "data" that holds the results
    //TextView results;
    // URL of object to be parsed
    String JsonURL = "http://dione.ms.wits.ac.za/php/lotcount.php";
    // This string will hold the results
    String data = "";
    String data1 = "";
    String data2 = "";
    String data3 = "";
    // Defining the Volley request queue that handles the URL request concurrently
    RequestQueue requestQueue;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_east_campus);

        // Creates the Volley request queue
        requestQueue = Volley.newRequestQueue(this);

        // Casts results into the TextView found within the main layout XML with id jsonData
        //results = (TextView) findViewById(R.id.textView4);

        // Creating the JsonObjectRequest class called obreq, passing required parameters:
        //GET is used to fetch data from the server, JsonURL is the URL to be fetched from.
        JsonObjectRequest obreq = new JsonObjectRequest(Request.Method.GET, JsonURL,
                // The third parameter Listener overrides the method onResponse() and passes
                //JSONObject as a parameter
                new Response.Listener<JSONObject>() {

                    // Takes the response from the JSON request
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            JSONObject obj = response.getJSONObject("wits_msl");
                            JSONObject obj1 = response.getJSONObject("wits_com");
                            JSONObject obj2 = response.getJSONObject("wits_com");
                            JSONObject obj3 = response.getJSONObject("wits_com");
                            // Retrieves the string labeled "colorName" and "description" from
                            //the response JSON Object
                            //and converts them into javascript objects

                            Integer count = obj.getInt("count");
                            Integer count1 = obj1.getInt("count");
                            Integer count2 = obj2.getInt("count");
                            Integer count3 = obj3.getInt("count");

                            String holder = String.valueOf(count);
                            String holder1 = String.valueOf(count1);
                            String holder2 = String.valueOf(count2);
                            String holder3 = String.valueOf(count3);

                            // Adds strings from object to the "data" string
                            data += holder;
                            data1 += holder1;
                            data2 += holder2;
                            data3 += holder3;

                            // Adds the data string to the TextView "results"
                            //results.setText(data);
                        }
                        // Try and catch are included to handle any errors due to JSON
                        catch (JSONException e) {
                            // If an error occurs, this prints the error to the log
                            e.printStackTrace();
                        }
                    }
                },
                // The final parameter overrides the method onErrorResponse() and passes VolleyError
                //as a parameter
                new Response.ErrorListener() {
                    @Override
                    // Handles errors that occur due to Volley
                    public void onErrorResponse(VolleyError error) {
                        Log.e("Volley", "Error");
                    }
                }
        );
    
        // Adds the JSON object request "obreq" to the request queue
        requestQueue.add(obreq);

        Handler h = new Handler();
        h.postDelayed(new Runnable() {
            @Override
            public void run() {
                startFlash();
            }
        },1500);


        fab1 = (FloatingActionButton) findViewById(R.id.fab_1);
        fab2 = (FloatingActionButton) findViewById(R.id.fab_2);
        fab3 = (FloatingActionButton) findViewById(R.id.fab_3);
        fab4 = (FloatingActionButton) findViewById(R.id.fab_4);

        Handler b = new Handler();
        b.postDelayed(new Runnable() {
            @Override
            public void run() {
                fab1.setImageBitmap(textAsBitmap(data, 40, Color.WHITE));
                fab2.setImageBitmap(textAsBitmap(data1, 40, Color.WHITE));
                fab3.setImageBitmap(textAsBitmap(data2, 40, Color.WHITE));
                fab4.setImageBitmap(textAsBitmap(data3, 40, Color.WHITE));
            }
        },2500);

        fab1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(activity_east_campus.this, "No. Of Open Parking Spaces: 6", Toast.LENGTH_SHORT).show();
            }
        });

        fab2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(activity_east_campus.this, "No. Of Open Parking Spaces: 18", Toast.LENGTH_SHORT).show();
            }
        });

        fab3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(activity_east_campus.this, "No. Of Open Parking Spaces: 21", Toast.LENGTH_SHORT).show();
            }
        });

        fab4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(activity_east_campus.this, "No. Of Open Parking Spaces: 2", Toast.LENGTH_SHORT).show();
            }
        });

    }

    //method to convert your text to image
    public static Bitmap textAsBitmap(String text, float textSize, int textColor) {
        Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);
        paint.setTextSize(textSize);
        paint.setColor(textColor);
        paint.setTextAlign(Paint.Align.LEFT);
        float baseline = -paint.ascent(); // ascent() is negative
        int width = (int) (paint.measureText(text) + 0.0f); // round
        int height = (int) (baseline + paint.descent() + 0.0f);
        Bitmap image = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888);

        Canvas canvas = new Canvas(image);
        canvas.drawText(text, 0, baseline, paint);
        return image;
    }

    public void startFlash() {
        fab1 = (FloatingActionButton) findViewById(R.id.fab_1);
        fab2 = (FloatingActionButton) findViewById(R.id.fab_2);
        fab3 = (FloatingActionButton) findViewById(R.id.fab_3);
        fab4 = (FloatingActionButton) findViewById(R.id.fab_4);
        Animation mAnimation = new AlphaAnimation(1,0);
        mAnimation.setDuration(1100);
        mAnimation.setInterpolator(new LinearInterpolator());
        mAnimation.setRepeatCount(Animation.INFINITE);
        mAnimation.setRepeatMode(Animation.REVERSE);
        fab1.startAnimation(mAnimation);
        fab2.startAnimation(mAnimation);
        fab3.startAnimation(mAnimation);
        fab4.startAnimation(mAnimation);
    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

    public void back_to_main(View view){
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }
}
