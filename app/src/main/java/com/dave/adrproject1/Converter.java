package com.dave.adrproject1;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

public class Converter {

    private Map<Character,String> rpMap = new HashMap<>();
    private Map<Character,String> morseMap = new HashMap<>();
    private char[] hexDigits = {'A','B','C','D','E','F'};
    private Map<String,String> txtSpkMap = new LinkedHashMap<>();

    public Converter(){
        setupRPMap();
        setupMorseMap();
        setupTxtSpkMap();
    }

    public void setupRPMap(){
        rpMap.put('A',"Alpha");
        rpMap.put('B',"Bravo");
        rpMap.put('C',"Charlie");
        rpMap.put('D',"Delta");
        rpMap.put('E',"Echo");
        rpMap.put('F',"Foxtrot");
        rpMap.put('G',"Golf");
        rpMap.put('H',"Hotel");
        rpMap.put('I',"India");
        rpMap.put('J',"Juliette");
        rpMap.put('K',"Kilo");
        rpMap.put('L',"Lima");
        rpMap.put('M',"Mike");
        rpMap.put('N',"November");
        rpMap.put('O',"Oscar");
        rpMap.put('P',"Papa");
        rpMap.put('Q',"Quebec");
        rpMap.put('R',"Romeo");
        rpMap.put('S',"Sierra");
        rpMap.put('T',"Tango");
        rpMap.put('U',"Uniform");
        rpMap.put('V',"Victor");
        rpMap.put('W',"Whiskey");
        rpMap.put('X',"X-ray");
        rpMap.put('Y',"Yankee");
        rpMap.put('Z',"Zulu");
    }

    public void setupMorseMap(){
        morseMap.put('A',"*-");
        morseMap.put('B',"-***");
        morseMap.put('C',"-*-*");
        morseMap.put('D',"-**");
        morseMap.put('E',"*");
        morseMap.put('F',"**-*");
        morseMap.put('G',"--*");
        morseMap.put('H',"****");
        morseMap.put('I',"**");
        morseMap.put('J',"*---");
        morseMap.put('K',"-*-");
        morseMap.put('L',"*-**");
        morseMap.put('M',"--");
        morseMap.put('N',"-*");
        morseMap.put('O',"---");
        morseMap.put('P',"*--*");
        morseMap.put('Q',"--*-");
        morseMap.put('R',"*-*");
        morseMap.put('S',"***");
        morseMap.put('T',"-");
        morseMap.put('U',"**-");
        morseMap.put('V',"***-");
        morseMap.put('W',"*--");
        morseMap.put('X',"-**-");
        morseMap.put('Y',"-*--");
        morseMap.put('Z',"--**");
        morseMap.put('1',"*----");
        morseMap.put('2',"**---");
        morseMap.put('3',"***--");
        morseMap.put('4',"****-");
        morseMap.put('5',"*****");
        morseMap.put('6',"-****");
        morseMap.put('7',"--***");
        morseMap.put('8',"---**");
        morseMap.put('9',"----*");
        morseMap.put('0',"----");
    }

    public void setupTxtSpkMap(){
        txtSpkMap.put("talk to you later","ttyl");
        txtSpkMap.put("be right back","brb");
        txtSpkMap.put("got to go","gtg");
        txtSpkMap.put("by the way","btw");
        txtSpkMap.put("for the win","ftw");
        txtSpkMap.put("i don't know","idk");
        txtSpkMap.put("right now","rn");
        txtSpkMap.put("no bother","nb");
        txtSpkMap.put("laugh out lol","lol");
        txtSpkMap.put("oh my god","omg");
        txtSpkMap.put("see","c");
        txtSpkMap.put("you","u");
        txtSpkMap.put("are","r");
        txtSpkMap.put("before","b4");
        txtSpkMap.put("for","4");
        txtSpkMap.put("great","gr8");
        txtSpkMap.put("late","l8");
        txtSpkMap.put("to","2");
        txtSpkMap.put("with","w/");
        txtSpkMap.put("without","w/o");
    }

    public Map<Character,String> getRpMap(){
        return rpMap;
    }

    public Map<Character,String> getMorseMap(){
        return morseMap;
    }

    public Map<String,String> getTxtSpkMap(){
        return txtSpkMap;
    }

}