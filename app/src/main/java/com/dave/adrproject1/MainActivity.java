package com.dave.adrproject1;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements View.OnClickListener, DialogInterface.OnClickListener{

    //Declaring objects for layout elements
    EditText etConvert;
    ImageButton btConvert;
    Button typeChoice;
    //Defining constants for activity result integer and maximum number of items in recent conversion
    private final int OUTPUT_CHANGE = 10;
    private final int MAX_HISTORY = 15;
    //Defining the string for what conversion type is sent to Conversion.java
    String convType = "";
    //Declaring the alert
    AlertDialog alert;
    //Declaring the objects for RecyclerView
    RecyclerView recView;
    static ArrayList<StoredConversion> conversions;
    static RecViewAdapter recViewAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Binds the objects for the layout elements to their respective layout elements
        etConvert = (EditText) findViewById(R.id.txtConvert);
        btConvert = (ImageButton) findViewById(R.id.convertText);
        typeChoice = (Button) findViewById(R.id.typeChoice);

        //Sets onClickListeners to the buttons in the activity
        btConvert.setOnClickListener(this);
        typeChoice.setOnClickListener(this);

        //Defines the RecyclerView object, ArrayList and RecViewAdapter object. A LayoutManager is declared
        recView = (RecyclerView) findViewById(R.id.history);
        conversions = new ArrayList<>();
        LinearLayoutManager linearLM = new LinearLayoutManager(this);
        //Assigns the ArrayList to the Adapter
        recViewAdapter = new RecViewAdapter(conversions);
        //Sets the LayoutManager and Adapter to the RecViewAdapter object
        recView.setLayoutManager(linearLM);
        recView.setAdapter(recViewAdapter);

        //Builds the AlertDialog object
        alert = new AlertDialog.Builder(this).create();
        alert.setMessage(getString(R.string.noOutType));
        alert.setButton(DialogInterface.BUTTON_NEGATIVE,getString(R.string.exitApp),this);
        alert.setButton(DialogInterface.BUTTON_NEUTRAL,getString(R.string.removeAlert),this);
        alert.setButton(DialogInterface.BUTTON_POSITIVE,getString(R.string.selectType),this);
    }

    public void onClick(View v){ //Processes click events for buttons in the activity
        switch(v.getId()){
            case R.id.convertText: //If the user clicks the convert button
                if(convType.equals("")){ //If there is no type selected
                    alert.show(); //Shows the alert
                }else{
                    //Creates an intent to go to the conversion activity and send the text to convert and to what type
                    Intent convAct = new Intent(this, Conversion.class);
                    convAct.putExtra("message", etConvert.getText().toString());
                    convAct.putExtra("convType", convType);
                    startActivity(convAct);
                }
                break;
            case R.id.typeChoice: //If the user clicks the output type selection button
                //Creates an intent to get an output type from the user
                Intent outputAct = new Intent(this,OutputType.class);
                startActivityForResult(outputAct,OUTPUT_CHANGE);
        }
    }

    public void onClick(DialogInterface dialog, int which){ //Processes click events for the alert dialog
        if(dialog.equals(alert)){
            switch(which){
                case DialogInterface.BUTTON_NEGATIVE: //If the user clicks the negative button
                    finish(); //Exit the app
                    break;
                case DialogInterface.BUTTON_NEUTRAL: //If the user clicks the neutral button
                    alert.dismiss(); //Dismisses the alert
                    break;
                case DialogInterface.BUTTON_POSITIVE: //If the user clicks the positive button
                    //Creates an intent to get an output type from the user
                    Intent outputAct = new Intent(this,OutputType.class);
                    startActivityForResult(outputAct,OUTPUT_CHANGE);
                    break;
            }
        }
    }

    protected void onActivityResult(int reqCode, int resCode, Intent data){
        if(reqCode == OUTPUT_CHANGE && resCode ==  RESULT_OK){ //Storing the output type after being selected by the user
            convType = data.getExtras().getString("retConvType");
        }
        super.onActivityResult(reqCode,resCode,data);
    }

    public void onSaveInstanceState(Bundle instanceState){ //Saves the text to be converted and the output type when orientation changes
        instanceState.putString("convType",convType);
        instanceState.putString("msg",etConvert.getText().toString());
        super.onSaveInstanceState(instanceState);
    }

    public void onRestoreInstanceState(Bundle instanceState){ //Restores the text to be converted and the output type after orientation is changed
        super.onRestoreInstanceState(instanceState);
        convType = instanceState.getString("convType");
        etConvert.setText(instanceState.getString("msg"));
    }

    protected void onResume(){ //Updates the recent conversions
        super.onResume();
        try {
            FileInputStream fis = openFileInput("conversions"); //Opens the input stream to file 'conversions'
            ObjectInputStream inStream = new ObjectInputStream(fis); //Creates an object input stream to read data from the file
            StoredConversion sc; //Stores each stored conversion as they're read in from a file
            while((sc = (StoredConversion) inStream.readObject()) != null){ //Reads an object if it's not null
                conversions.add(sc); //Adds the read in stored conversion to the list
            }
            inStream.close(); //Closes the object input stream
            fis.close(); //Closes the file input stream
            MainActivity.recViewAdapter.notifyDataSetChanged(); //Updates the RecyclerView to show the recent conversions
        }catch(IOException|ClassNotFoundException|IndexOutOfBoundsException e){}
    }

    protected void onPause(){  //Saves the recent conversions
        try {
            FileOutputStream fos = this.openFileOutput("conversions", Context.MODE_PRIVATE); //Opens the output stream to file 'conversions' and keeps it private to the app
            ObjectOutputStream outStream = new ObjectOutputStream(fos); //Creates an object output stream to write data to the file
            for(int index = 0; index < MAX_HISTORY; index++){ //Writes a limited number of objects to the file
                outStream.writeObject(conversions.get(index)); //Adds the object to the files
                if(conversions.size() < index+1){ //If all stored conversions are written to the file and there are no more left so loop is finished
                    break;
                }
            }
            outStream.close(); //Closes the object output stream
            fos.close(); //Closes the file output stream
            conversions.clear(); //Clears the list of conversions
        }catch(IOException|IndexOutOfBoundsException e){}
        super.onPause();
    }

}
