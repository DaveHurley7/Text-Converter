package com.dave.adrproject1;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;

public class OutputType extends AppCompatActivity implements View.OnClickListener{

    RadioButton ascii, rp, morse, txtspeak;
    Button choose;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_output_type);

        ascii = (RadioButton) findViewById(R.id.rbASCII);
        rp = (RadioButton) findViewById(R.id.rbRP);
        morse = (RadioButton) findViewById(R.id.rbMorse);
        txtspeak = (RadioButton) findViewById(R.id.rbTxtSpeak);
        choose = (Button) findViewById(R.id.btChoose);

        choose.setOnClickListener(this);
    }

    public void onClick(View v){
        Intent i = getIntent();
        if(ascii.isChecked()){
            i.putExtra("retConvType","ascii");
        }else if(rp.isChecked()){
            i.putExtra("retConvType","rp");
        }else if(morse.isChecked()){
            i.putExtra("retConvType","morse");
        }else if(txtspeak.isChecked()){
            i.putExtra("retConvType","txtspeak");
        }else{
            i.putExtra("retConvType","");
        }

        /*NotificationCompat.Builder notifBuild = new NotificationCompat.Builder(this);
        notifBuild.setSmallIcon(R.drawable.appicon);
        notifBuild.setContentTitle(getString(R.string.app_name));
        if(i.getExtras().getString("retConvType").equals("")) {
            notifBuild.setContentText(getString(R.string.noOutType) + " " + getString(R.string.callNum));
        }else{
            notifBuild.setContentText(getString(R.string.outTypeChosen) + " " + getString(R.string.callNum));
        }
        Intent notifIntent = new Intent(Intent.ACTION_VIEW,Uri.parse("tel:021000001"));
        PendingIntent pendIntent = PendingIntent.getActivity(getApplicationContext(),0,notifIntent,PendingIntent.FLAG_UPDATE_CURRENT);
        notifBuild.setContentIntent(pendIntent);
        int notifId = 234;
        NotificationManager notifManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
        notifManager.notify(notifId,notifBuild.build());*/

        setResult(RESULT_OK,i);
        finish();
    }
}


