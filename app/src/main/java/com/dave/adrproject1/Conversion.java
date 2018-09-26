package com.dave.adrproject1;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import java.util.Date;

public class Conversion extends AppCompatActivity implements View.OnClickListener{

    TextView showMsg;
    Button back;
    Converter conv = new Converter();
    String convType;

    Date date;
    String type;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_conversion);

        showMsg = (TextView) findViewById(R.id.convMsg);
        back = (Button) findViewById(R.id.back);

        Intent dataInt = getIntent();
        if(dataInt.hasExtra("error")){
            showMsg.setText(dataInt.getExtras().getString("error"));
        }else{
            String message = dataInt.getExtras().getString("message");
            String convMsg = null;
            convType = dataInt.getExtras().getString("convType");
            date = new Date();
            switch(convType){
                case "ascii":
                    convMsg = convertToAscii(message);
                    type = getString(R.string.optASCII);
                    break;
                case "rp":
                    convMsg = convertToRP(message);
                    type = getString(R.string.optRP);
                    break;
                case "morse":
                    convMsg = convertToMorse(message);
                    type = getString(R.string.optMorse);
                    break;
                case "txtspeak":
                    convMsg = convertToTxtSpeak(message);
                    type = getString(R.string.optTxtSpeak);
                    break;
            }
            MainActivity.conversions.add(0,new StoredConversion(date,type,message,convMsg));
            MainActivity.recViewAdapter.notifyDataSetChanged();
            showMsg.setText(convMsg);
        }
        back.setOnClickListener(this);
    }



    public void onClick(View v){
        finish();
    }

    public String convertToAscii(String msg){
        int length = msg.length();
        String text = "";
        for(int i = 0; i < length; i++){
            int letter = (int) msg.charAt(i);
            String asciiChar = "$" + Integer.toHexString(letter).toUpperCase();
            text += asciiChar;
            if(i != length - 1)
                text += " ";
        }
        return text;
    }

    public String convertToRP(String msg){
        msg = msg.toUpperCase();
        int length = msg.length();
        String text = "";
        for(int i = 0; i < length; i++){
            char letter = msg.toUpperCase().charAt(i);
            if(conv.getRpMap().containsKey(letter)){
                text += conv.getRpMap().get(letter);
            }else{
                text += letter;
            }
            if(i != length - 1)
                text += " ";
        }
        return text;
    }

    public String convertToMorse(String msg){
        msg = msg.toUpperCase();
        int length = msg.length();
        String text = "";
        String morseStr = "";
        for(int i = 0; i < length; i++){
            char alphaNum = msg.toUpperCase().charAt(i);
            if(conv.getMorseMap().containsKey(alphaNum)){
                morseStr = conv.getMorseMap().get(alphaNum);
            }else{
                morseStr = ""+alphaNum;
            }
            text += morseStr;
            if(i != length - 1)
                text += " ";
        }
        return text;
    }

    public String convertToTxtSpeak(String msg){
        msg = msg.toLowerCase();
        for(String phrase : conv.getTxtSpkMap().keySet()){
            if(msg.contains(phrase)){
                msg = msg.replaceAll(phrase,conv.getTxtSpkMap().get(phrase));
            }
        }
        return msg;
    }
}
