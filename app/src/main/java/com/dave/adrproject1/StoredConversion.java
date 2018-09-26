package com.dave.adrproject1;

import java.io.Serializable;
import java.util.Date;

public class StoredConversion implements Serializable{

    Date date;
    String type;
    String originalMessage;
    String convertedMessage;

    public StoredConversion(Date d, String t, String oMsg, String cMsg){
        date = d;
        type = t;
        originalMessage = oMsg;
        convertedMessage = cMsg;
    }

    public Date getDate(){
        return date;
    }

    public String getType(){
        return type;
    }

    public String getOriginalMessage(){
        return originalMessage;
    }

    public String getConvertedMessage(){
        return convertedMessage;
    }
}