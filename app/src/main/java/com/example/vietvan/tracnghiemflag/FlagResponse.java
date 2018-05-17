package com.example.vietvan.tracnghiemflag;

import android.graphics.Bitmap;

/**
 * Created by VietVan on 16/05/2018.
 */

public class FlagResponse {

    public int area;
    public Bitmap flag;
    public String name;
    public int population;
    public String region, acronym, subregion;

    public FlagResponse(int area, Bitmap flag, String name, int population, String region, String acronym, String subregion) {
        this.area = area;
        this.flag = flag;
        this.name = name;
        this.population = population;
        this.region = region;
        this.acronym = acronym;
        this.subregion = subregion;
    }

    @Override
    public String toString() {
        return "FlagResponse{" +
                "area=" + area +
                ", flag=" + flag +
                ", name='" + name + '\'' +
                ", population=" + population +
                ", region='" + region + '\'' +
                ", acronym='" + acronym + '\'' +
                ", subregion='" + subregion + '\'' +
                '}';
    }

    public int getArea() {
        return area;
    }

    public void setArea(int area) {
        this.area = area;
    }

    public Bitmap getFlag() {
        return flag;
    }

    public void setFlag(Bitmap flag) {
        this.flag = flag;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPopulation() {
        return population;
    }

    public void setPopulation(int population) {
        this.population = population;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public String getAcronym() {
        return acronym;
    }

    public void setAcronym(String acronym) {
        this.acronym = acronym;
    }

    public String getSubregion() {
        return subregion;
    }

    public void setSubregion(String subregion) {
        this.subregion = subregion;
    }
}


