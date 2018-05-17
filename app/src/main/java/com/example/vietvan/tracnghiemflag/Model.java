package com.example.vietvan.tracnghiemflag;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Typeface;
import android.util.Log;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.Random;

/**
 * Created by VietVan on 03/05/2018.
 */

public class Model {

    public static String TAG = "TAG";

    public static void loadfont(Context context, TextView view, String path){
        Typeface type = Typeface.createFromAsset(context.getAssets(), path);
        view.setTypeface(type);
    }
    public static void loadfontButton(Context context, Button view, String path){
        Typeface type = Typeface.createFromAsset(context.getAssets(), path);
        view.setTypeface(type);
    }

    public static List<FlagResponse> getListLevel1(List<FlagResponse> list){
        List<FlagResponse> flagResponseList = new ArrayList<>();

        flagResponseList.addAll(getListCountryTop(list));
        flagResponseList.addAll(getListCountryinAsean(list));

        Log.d(TAG, "getListLevel1: " + flagResponseList.size() + "/" + flagResponseList.toString());

        return flagResponseList;
    }

    public static List<FlagResponse> getListLevel2(List<FlagResponse> list){
        List<FlagResponse> flagResponseList = new ArrayList<>();

        flagResponseList.addAll(getListCountryinSouthxAsia(list));
        flagResponseList.addAll(getListCountryinEurope(list));
        flagResponseList.addAll(getListCountryinAmerica(list));

        Log.d(TAG, "getListLevel2: " + flagResponseList.size() + "/" + flagResponseList.toString());

        return flagResponseList;
    }

    public static List<FlagResponse> getListLevel3(List<FlagResponse> list){
        List<FlagResponse> flagResponseList = new ArrayList<>();

        flagResponseList.addAll(getListCountryinAfrica(list));
        flagResponseList.addAll(getListCountrySuperSmallinEurope(list));

        Log.d(TAG, "getListLevel3: " + flagResponseList.size() + "/" + flagResponseList.toString());

        return flagResponseList;
    }

    public static List<FlagResponse> getListCountryTop(List<FlagResponse> list){

        Collections.sort(list, new Comparator<FlagResponse>() {
            @Override
            public int compare(FlagResponse a, FlagResponse b) {
                return b.getPopulation() - a.getPopulation();
            }
        });

        return list.subList(0, 100);
    }

    public static List<FlagResponse> test(List<FlagResponse> list){
        return list.subList(0, 3);
    }

    public static List<FlagResponse> getListCountryinAsean(List<FlagResponse> list){

        List<FlagResponse> flagResponseList = new ArrayList<>();

        for(FlagResponse flagResponse : list)
            if(flagResponse.getAcronym() != null)
                flagResponseList.add(flagResponse);

        return flagResponseList;
    }

    public static List<FlagResponse> getListCountryinEurope(List<FlagResponse> list){
        List<FlagResponse> flagResponseList = new ArrayList<>();

        for(FlagResponse flagResponse : list)
            if(flagResponse.getRegion().equals("Europe"))
                flagResponseList.add(flagResponse);

        Collections.sort(flagResponseList, new Comparator<FlagResponse>() {
            @Override
            public int compare(FlagResponse a, FlagResponse b) {
                return (int) (a.getArea() - b.getArea());
            }
        });

        return flagResponseList.subList(10, 50);
    }

    public static List<FlagResponse> getListCountryinAmerica(List<FlagResponse> list){
        List<FlagResponse> flagResponseList = new ArrayList<>();

        for(FlagResponse flagResponse : list)
            if(flagResponse.getRegion().equals("Americas"))
                flagResponseList.add(flagResponse);

        Collections.sort(flagResponseList, new Comparator<FlagResponse>() {
            @Override
            public int compare(FlagResponse a, FlagResponse b) {
                return (int) (a.getArea() - b.getArea());
            }
        });

        return flagResponseList.subList(20, 50);
    }

    public static List<FlagResponse> getListCountryinSouthxAsia(List<FlagResponse> list){
        List<FlagResponse> flagResponseList = new ArrayList<>();

        for(FlagResponse flagResponse : list)
            if(flagResponse.getSubregion().equals("Central Asia") || flagResponse.getSubregion().equals("Southern Asia"))
                flagResponseList.add(flagResponse);

        return flagResponseList;
    }

    public static List<FlagResponse> getListCountryinAfrica(List<FlagResponse> list){
        List<FlagResponse> flagResponseList = new ArrayList<>();

        for(FlagResponse flagResponse : list)
            if(flagResponse.getRegion().equals("Africa"))
                flagResponseList.add(flagResponse);

        return flagResponseList;
    }

    public static List<FlagResponse> getListCountrySuperSmallinEurope(List<FlagResponse> list){
        List<FlagResponse> flagResponseList = new ArrayList<>();

        for(FlagResponse flagResponse : list)
            if(flagResponse.getRegion().equals("Europe"))
                flagResponseList.add(flagResponse);

        Collections.sort(flagResponseList, new Comparator<FlagResponse>() {
            @Override
            public int compare(FlagResponse a, FlagResponse b) {
                return (int) (a.getArea() - b.getArea());
            }
        });
        Collections.shuffle(flagResponseList);

        return flagResponseList.subList(0, 50);
    }

    public static List<String> getAnswerWord(List<FlagResponse> list, int pos){
        HashSet<String> hashSet = new HashSet<>();
        hashSet.add(list.get(pos).getName());
        while (hashSet.size() != 4){
            int random = new Random().nextInt(list.size() - 1);
            hashSet.add(list.get(random).getName());
        }

        return new ArrayList<>(hashSet);
    }

    public static List<Bitmap> getAnswerImage(List<FlagResponse> list, int pos){
        HashSet<Bitmap> hashSet = new HashSet<>();
        hashSet.add(list.get(pos).getFlag());
        while (hashSet.size() != 4){
            int random = new Random().nextInt(list.size() - 1);
            hashSet.add(list.get(random).getFlag());
        }

        return new ArrayList<>(hashSet);
    }

}
