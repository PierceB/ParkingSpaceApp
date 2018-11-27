package com.wits.witsparkingspace;

import android.content.Intent;
import android.content.res.ColorStateList;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Handler;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.LinearInterpolator;
import android.widget.TextView;
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

public class activity_west_campus extends AppCompatActivity {

    FloatingActionButton fab1,fab2,fab3,fab4,fab5,fab6,fab7,fab8,fab9,fab10;
    // Will show the string "data" that holds the results
    //TextView results;
    // URL of object to be parsed
    String JsonURL = "http://dione.ms.wits.ac.za/php/lotcount.php";
    // This string will hold the results
    String data = "";
    String data1 = "";
    String data2 = "";
    String data3 = "";
    String data4 = "";
    String data5 = "";
    String data6 = "";
    String data7 = "";
    String data8 = "";
    String data9 = "";

    Integer count;
    Integer count1;
    Integer count2;
    Integer count3;
    Integer count4;
    Integer count5;
    Integer count6;
    Integer count7;
    Integer count8;
    Integer count9;

    Integer total;
    Integer total1;
    Integer total2;
    Integer total3;
    Integer total4;
    Integer total5;
    Integer total6;
    Integer total7;
    Integer total8;
    Integer total9;

    // Defining the Volley request queue that handles the URL request concurrently
    RequestQueue requestQueue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_west_campus);


        getSetDB();



        fab1 = (FloatingActionButton) findViewById(R.id.fab_1);
        fab2 = (FloatingActionButton) findViewById(R.id.fab_2);
        fab3 = (FloatingActionButton) findViewById(R.id.fab_3);
        fab4 = (FloatingActionButton) findViewById(R.id.fab_4);
        fab5 = (FloatingActionButton) findViewById(R.id.fab_5);
        fab6 = (FloatingActionButton) findViewById(R.id.fab_6);
        fab7 = (FloatingActionButton) findViewById(R.id.fab_7);
        fab8 = (FloatingActionButton) findViewById(R.id.fab_8);
        fab9 = (FloatingActionButton) findViewById(R.id.fab_9);
        fab10 = (FloatingActionButton) findViewById(R.id.fab_10);


        fab1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(activity_west_campus.this,Pop.class));
            }
        });

        fab2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(activity_west_campus.this, "No. Of Open Parking Spaces: 18", Toast.LENGTH_SHORT).show();
            }
        });

        fab3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(activity_west_campus.this, "No. Of Open Parking Spaces: 21", Toast.LENGTH_SHORT).show();
            }
        });

        fab4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(activity_west_campus.this, "No. Of Open Parking Spaces: 2", Toast.LENGTH_SHORT).show();
            }
        });

        fab5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(activity_west_campus.this, "No. Of Open Parking Spaces: 7", Toast.LENGTH_SHORT).show();
            }
        });

        fab6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(activity_west_campus.this, "No. Of Open Parking Spaces: 14", Toast.LENGTH_SHORT).show();
            }
        });

        fab7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(activity_west_campus.this, "No. Of Open Parking Spaces: 32", Toast.LENGTH_SHORT).show();
            }
        });

        fab8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(activity_west_campus.this, "No. Of Open Parking Spaces: 20", Toast.LENGTH_SHORT).show();
            }
        });


    }

    private void getSetDB() {
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
                            JSONObject obj2 = response.getJSONObject("wits_fh_stu");
                            JSONObject obj3 = response.getJSONObject("wits_fnb");
                            JSONObject obj4 = response.getJSONObject("wits_dwh0");
                            JSONObject obj5 = response.getJSONObject("wits_dwh1");
                            JSONObject obj6 = response.getJSONObject("wits_law_lawns");
                            JSONObject obj7 = response.getJSONObject("wits_cafe0");
                            JSONObject obj8 = response.getJSONObject("wits_cafe1");
                            JSONObject obj9 = response.getJSONObject("wits_origins");
                            // Retrieves the string labeled "colorName" and "description" from
                            //the response JSON Object
                            //and converts them into javascript objects
                            count = obj.getInt("count");
                            count1 = obj1.getInt("count");
                            count2 = obj2.getInt("count");
                            count3 = obj3.getInt("count");
                            count4 = obj4.getInt("count");
                            count5 = obj5.getInt("count");
                            count6 = obj6.getInt("count");
                            count7 = obj7.getInt("count");
                            count8 = obj8.getInt("count");
                            count9 = obj9.getInt("count");

                            total = obj.getInt("total");
                            total1 = obj1.getInt("total");
                            total2 = obj2.getInt("total");
                            total3 = obj3.getInt("total");
                            total4 = obj4.getInt("total");
                            total5 = obj5.getInt("total");
                            total6 = obj6.getInt("total");
                            total7 = obj7.getInt("total");
                            total8 = obj8.getInt("total");
                            total9 = obj9.getInt("total");


                            String holder = String.valueOf(count);
                            String holder1 = String.valueOf(count1);
                            String holder2 = String.valueOf(count2);
                            String holder3 = String.valueOf(count3);
                            String holder4 = String.valueOf(count4);
                            String holder5 = String.valueOf(count5);
                            String holder6 = String.valueOf(count6);
                            String holder7 = String.valueOf(count7);
                            String holder8 = String.valueOf(count8);
                            String holder9 = String.valueOf(count9);

                            // Adds strings from object to the "data" string
                            data += holder;
                            data1 += holder1;
                            data2 += holder2;
                            data3 += holder3;
                            data4 += holder4;
                            data5 += holder5;
                            data6 += holder6;
                            data7 += holder7;
                            data8 += holder8;
                            data9 += holder9;

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
                paintButtons();
            }
        },1500);




        //fab1.setBackgroundTintList(ColorStateList.valueOf(Color.parseColor("#00ff00")));



        Handler b = new Handler();
        b.postDelayed(new Runnable() {
            @Override
            public void run() {
                fab1.setImageBitmap(textAsBitmap(data+"/"+total, 40, Color.WHITE));
                fab2.setImageBitmap(textAsBitmap(data1+"/"+total1, 40, Color.WHITE));
                fab3.setImageBitmap(textAsBitmap(data2+"/"+total2, 40, Color.WHITE));
                fab4.setImageBitmap(textAsBitmap(data3+"/"+total3, 40, Color.WHITE));
                fab5.setImageBitmap(textAsBitmap(data4+"/"+total4, 40, Color.WHITE));
                fab6.setImageBitmap(textAsBitmap(data5+"/"+total5, 40, Color.WHITE));
                fab7.setImageBitmap(textAsBitmap(data6+"/"+total6, 40, Color.WHITE));
                fab8.setImageBitmap(textAsBitmap(data7+"/"+total7, 40, Color.WHITE));
                fab9.setImageBitmap(textAsBitmap(data8+"/"+total8, 40, Color.WHITE));
                fab10.setImageBitmap(textAsBitmap(data9+"/"+total9, 40, Color.WHITE));
            }
        },2500);



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

    private void startFlash() {
        fab1 = (FloatingActionButton) findViewById(R.id.fab_1);
        fab2 = (FloatingActionButton) findViewById(R.id.fab_2);
        fab3 = (FloatingActionButton) findViewById(R.id.fab_3);
        fab4 = (FloatingActionButton) findViewById(R.id.fab_4);
        fab5 = (FloatingActionButton) findViewById(R.id.fab_5);
        fab6 = (FloatingActionButton) findViewById(R.id.fab_6);
        fab7 = (FloatingActionButton) findViewById(R.id.fab_7);
        fab8 = (FloatingActionButton) findViewById(R.id.fab_8);
        fab9 = (FloatingActionButton) findViewById(R.id.fab_9);
        fab10 = (FloatingActionButton) findViewById(R.id.fab_10);
        Animation mAnimation = new AlphaAnimation(1,0);
        mAnimation.setDuration(1100);
        mAnimation.setInterpolator(new LinearInterpolator());
        mAnimation.setRepeatCount(Animation.INFINITE);
        mAnimation.setRepeatMode(Animation.REVERSE);
        fab1.startAnimation(mAnimation);
        fab2.startAnimation(mAnimation);
        fab3.startAnimation(mAnimation);
        fab4.startAnimation(mAnimation);
        fab5.startAnimation(mAnimation);
        fab6.startAnimation(mAnimation);
        fab7.startAnimation(mAnimation);
        fab8.startAnimation(mAnimation);
        fab9.startAnimation(mAnimation);
        fab10.startAnimation(mAnimation);
    }

    private void paintButtons() {
        fab1 = (FloatingActionButton) findViewById(R.id.fab_1);
        fab2 = (FloatingActionButton) findViewById(R.id.fab_2);
        fab3 = (FloatingActionButton) findViewById(R.id.fab_3);
        fab4 = (FloatingActionButton) findViewById(R.id.fab_4);
        fab5 = (FloatingActionButton) findViewById(R.id.fab_5);
        fab6 = (FloatingActionButton) findViewById(R.id.fab_6);
        fab7 = (FloatingActionButton) findViewById(R.id.fab_7);
        fab8 = (FloatingActionButton) findViewById(R.id.fab_8);
        fab9 = (FloatingActionButton) findViewById(R.id.fab_9);
        fab10 = (FloatingActionButton) findViewById(R.id.fab_10);

        if(count >= 0 && count < total*(0.2)){
            fab1.setBackgroundTintList(ColorStateList.valueOf(Color.parseColor("#ff000b")));

        } else if(count >= total*(0.2) && count < total*(0.4)){

            fab1.setBackgroundTintList(ColorStateList.valueOf(Color.parseColor("#ff9700")));

        } else if (count >= total*(0.4) && count < total*(0.6)){
            fab1.setBackgroundTintList(ColorStateList.valueOf(Color.parseColor("#fffb00")));

        } else if (count >= total*(0.6) && count < total*(0.8)){
            fab1.setBackgroundTintList(ColorStateList.valueOf(Color.parseColor("#c5ff00")));

        } else {
            fab1.setBackgroundTintList(ColorStateList.valueOf(Color.parseColor("#27ff00")));
        }

        //Toast.makeText(this, "free spaces: "+count1+" total spaces: "+total1*(0.6), Toast.LENGTH_SHORT).show();

        if(count1 >= 0 && count1 < total1*(0.2)){
            fab2.setBackgroundTintList(ColorStateList.valueOf(Color.parseColor("#ff000b")));

        } else if(count1 >= total1*(0.2) && count1 < total1*(0.4)){

            fab2.setBackgroundTintList(ColorStateList.valueOf(Color.parseColor("#ff9700")));

        } else if (count1 >= total1*(0.4) && count1 < total1*(0.6)){
            fab2.setBackgroundTintList(ColorStateList.valueOf(Color.parseColor("#fffb00")));

        } else if (count1 >= total1*(0.6) && count1 < total1*(0.8)){
            fab2.setBackgroundTintList(ColorStateList.valueOf(Color.parseColor("#c5ff00")));

        } else {
            fab2.setBackgroundTintList(ColorStateList.valueOf(Color.parseColor("#27ff00")));
        }



        if(count2 >= 0 && count2 < total2*(0.2)){
            fab3.setBackgroundTintList(ColorStateList.valueOf(Color.parseColor("#ff000b")));

        } else if(count2 >= total2*(0.2) && count2 < total2*(0.4)){

            fab3.setBackgroundTintList(ColorStateList.valueOf(Color.parseColor("#ff9700")));

        } else if (count2 >= total2*(0.4) && count2 < total2*(0.6)){
            fab3.setBackgroundTintList(ColorStateList.valueOf(Color.parseColor("#fffb00")));

        } else if (count2 >= total2*(0.6) && count2 < total2*(0.8)){
            fab3.setBackgroundTintList(ColorStateList.valueOf(Color.parseColor("#c5ff00")));

        } else {
            fab3.setBackgroundTintList(ColorStateList.valueOf(Color.parseColor("#27ff00")));
        }

        if(count3 >= 0 && count3 < total3*(0.2)){
            fab4.setBackgroundTintList(ColorStateList.valueOf(Color.parseColor("#ff000b")));

        } else if(count3 >= total3*(0.2) && count3 < total3*(0.4)){

            fab4.setBackgroundTintList(ColorStateList.valueOf(Color.parseColor("#ff9700")));

        } else if (count3 >= total3*(0.4) && count3 < total3*(0.6)){
            fab4.setBackgroundTintList(ColorStateList.valueOf(Color.parseColor("#fffb00")));

        } else if (count3 >= total3*(0.6) && count3 < total3*(0.8)){
            fab4.setBackgroundTintList(ColorStateList.valueOf(Color.parseColor("#c5ff00")));

        } else {
            fab4.setBackgroundTintList(ColorStateList.valueOf(Color.parseColor("#27ff00")));
        }

        if(count4 >= 0 && count4 < total4*(0.2)){
            fab5.setBackgroundTintList(ColorStateList.valueOf(Color.parseColor("#ff000b")));

        } else if(count4 >= total4*(0.2) && count4 < total4*(0.4)){

            fab5.setBackgroundTintList(ColorStateList.valueOf(Color.parseColor("#ff9700")));

        } else if (count4 >= total4*(0.4) && count4 < total4*(0.6)){
            fab5.setBackgroundTintList(ColorStateList.valueOf(Color.parseColor("#fffb00")));

        } else if (count4 >= total4*(0.6) && count4 < total4*(0.8)){
            fab5.setBackgroundTintList(ColorStateList.valueOf(Color.parseColor("#c5ff00")));

        } else {
            fab5.setBackgroundTintList(ColorStateList.valueOf(Color.parseColor("#27ff00")));
        }

        if(count5 >= 0 && count5 < total5*(0.2)){
            fab6.setBackgroundTintList(ColorStateList.valueOf(Color.parseColor("#ff000b")));

        } else if(count5 >= total5*(0.2) && count5 < total5*(0.4)){

            fab6.setBackgroundTintList(ColorStateList.valueOf(Color.parseColor("#ff9700")));

        } else if (count5 >= total5*(0.4) && count5 < total5*(0.6)){
            fab6.setBackgroundTintList(ColorStateList.valueOf(Color.parseColor("#fffb00")));

        } else if (count5 >= total5*(0.6) && count5 < total5*(0.8)){
            fab6.setBackgroundTintList(ColorStateList.valueOf(Color.parseColor("#c5ff00")));

        } else {
            fab6.setBackgroundTintList(ColorStateList.valueOf(Color.parseColor("#27ff00")));
        }

        if(count6 >= 0 && count6 < total6*(0.2)){
            fab7.setBackgroundTintList(ColorStateList.valueOf(Color.parseColor("#ff000b")));

        } else if(count6 >= total6*(0.2) && count6 < total6*(0.4)){

            fab7.setBackgroundTintList(ColorStateList.valueOf(Color.parseColor("#ff9700")));

        } else if (count6 >= total6*(0.4) && count6 < total6*(0.6)){
            fab7.setBackgroundTintList(ColorStateList.valueOf(Color.parseColor("#fffb00")));

        } else if (count6 >= total6*(0.6) && count6 < total6*(0.8)){
            fab7.setBackgroundTintList(ColorStateList.valueOf(Color.parseColor("#c5ff00")));

        } else {
            fab7.setBackgroundTintList(ColorStateList.valueOf(Color.parseColor("#27ff00")));
        }

        if(count7 >= 0 && count7 < total7*(0.2)){
            fab8.setBackgroundTintList(ColorStateList.valueOf(Color.parseColor("#ff000b")));

        } else if(count7 >= total7*(0.2) && count7 < total7*(0.4)){

            fab8.setBackgroundTintList(ColorStateList.valueOf(Color.parseColor("#ff9700")));

        } else if (count7 >= total7*(0.4) && count7 < total7*(0.6)){
            fab8.setBackgroundTintList(ColorStateList.valueOf(Color.parseColor("#fffb00")));

        } else if (count7 >= total7*(0.6) && count7 < total7*(0.8)){
            fab8.setBackgroundTintList(ColorStateList.valueOf(Color.parseColor("#c5ff00")));

        } else {
            fab8.setBackgroundTintList(ColorStateList.valueOf(Color.parseColor("#27ff00")));
        }

        if(count8 >= 0 && count8 < total8*(0.2)){
            fab9.setBackgroundTintList(ColorStateList.valueOf(Color.parseColor("#ff000b")));

        } else if(count8 >= total8*(0.2) && count8 < total8*(0.4)){

            fab9.setBackgroundTintList(ColorStateList.valueOf(Color.parseColor("#ff9700")));

        } else if (count8 >= total8*(0.4) && count8 < total8*(0.6)){
            fab9.setBackgroundTintList(ColorStateList.valueOf(Color.parseColor("#fffb00")));

        } else if (count8 >= total8*(0.6) && count8 < total8*(0.8)){
            fab9.setBackgroundTintList(ColorStateList.valueOf(Color.parseColor("#c5ff00")));

        } else {
            fab9.setBackgroundTintList(ColorStateList.valueOf(Color.parseColor("#27ff00")));
        }

        if(count9 >= 0 && count9 < total9*(0.2)){
            fab10.setBackgroundTintList(ColorStateList.valueOf(Color.parseColor("#ff000b")));

        } else if(count9 >= total9*(0.2) && count9 < total9*(0.4)){

            fab10.setBackgroundTintList(ColorStateList.valueOf(Color.parseColor("#ff9700")));

        } else if (count9 >= total9*(0.4) && count9 < total9*(0.6)){
            fab10.setBackgroundTintList(ColorStateList.valueOf(Color.parseColor("#fffb00")));

        } else if (count9 >= total9*(0.6) && count9 < total9*(0.8)){
            fab10.setBackgroundTintList(ColorStateList.valueOf(Color.parseColor("#c5ff00")));

        } else {
            fab10.setBackgroundTintList(ColorStateList.valueOf(Color.parseColor("#27ff00")));
        }


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

    public void refresher(View view) {
        data = "";
        data1 = "";
        data2 = "";
        data3 = "";
        data4 = "";
        data5 = "";
        data6 = "";
        data7 = "";
        data8 = "";
        data9 = "";



        getSetDB();
    }
}
