package com.wits.witsparkingspace;

import android.app.Activity;
import android.content.Context;
import android.graphics.Paint;
import android.graphics.Point;
import android.graphics.PointF;
import android.os.Bundle;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.util.Log;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;

import static java.lang.Math.round;


public class Pop extends Activity {

    LineView customView;

    static int is_open;
    String JsonURL = "http://dione.ms.wits.ac.za/php/getcoordinates.php?parkinglot=wits_msl";

    RequestQueue requestQueue;

    //public static ArrayList<Point> coordinateList_1 = new ArrayList<Point>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //setContentView(R.layout.pop_window);
        customView = new LineView(this.getBaseContext());
        this.setContentView(customView);


        DisplayMetrics dm = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);

        int width = dm.widthPixels;
        int height = dm.heightPixels;

        //Toast.makeText(this, "width: "+width*0.8+" height: "+height*0.6, Toast.LENGTH_SHORT).show();

        getWindow().setLayout((int) (width * 0.8), (int) (height * 0.6));

        getSetCood();

    }

    private void getSetCood() {
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

                            JSONObject obj = response.getJSONObject("wits_msl0");

                            is_open = obj.getInt("is_open");

                            String coord1 = obj.getString("coord1");
                            String coord2 = obj.getString("coord2");
                            String coord3 = obj.getString("coord3");
                            String coord4 = obj.getString("coord4");

                            String[] values1 = coord1.split(",");
                            String[] values2 = coord2.split(",");
                            String[] values3 = coord3.split(",");
                            String[] values4 = coord4.split(",");

                            float f_1 = Float.parseFloat(values1[0]);
                            float f_1_2 = Float.parseFloat(values1[1]);

                            float f_2 = Float.parseFloat(values2[0]);
                            float f_2_2 = Float.parseFloat(values2[1]);

                            float f_3 = Float.parseFloat(values3[0]);
                            float f_3_2 = Float.parseFloat(values3[1]);

                            float f_4 = Float.parseFloat(values4[0]);
                            float f_4_2 = Float.parseFloat(values4[1]);

                            int x_1 = Math.round(f_1);
                            int y_1 = Math.round(f_1_2);

                            Point point1 = new Point(x_1,y_1);

                            int x_2 = Math.round(f_2);
                            int y_2 = Math.round(f_2_2);

                            Point point2 = new Point(x_2,y_2);

                            int x_3 = Math.round(f_3);
                            int y_3 = Math.round(f_3_2);

                            Point point3 = new Point(x_3,y_3);

                            int x_4 = Math.round(f_4);
                            int y_4 = Math.round(f_4_2);

                            Point point4 = new Point(x_4,y_4);


                            ArrayList<Point> coordinateList = new ArrayList<Point>();

                            coordinateList.add(point1);
                            coordinateList.add(point2);
                            coordinateList.add(point3);
                            coordinateList.add(point4);

                            //Point point8 = new Point(90,114);
                            //Point point7 = new Point(93,138);
                            //Point point6 = new Point(150,132);
                            //Point point5 = new Point(146,109);

                            //coordinateList.add(point1);
                            //coordinateList.add(point2);
                            //coordinateList.add(point3);
                            //coordinateList.add(point4);

                            /*Collections.sort(coordinateList, new PointCompare());
                            if (point1.y < point2.y){
                                Point point_1 = new Point(point1.x,point1.y);
                                Point point_2 = new Point(point2.x,point2.y);
                            } else {
                                Point point_2 = new Point(point1.x,point1.y);
                                Point point_1 = new Point(point2.x,point2.y);
                            }

                            if (point3.y < point3.y)*/




                            //System.out.println(Arrays.toString(coordinateList.toArray()));

                            // Adds strings from object to the "data" string


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

    }



    public class PointCompare implements Comparator<Point> {
        public int compare(Point a, Point b) {
            if (a.x < b.x) {
                return -1;
            }
            else if (a.x > b.x) {
                return 1;
            }
            else {
                return 0;
            }
        }
    }


}
