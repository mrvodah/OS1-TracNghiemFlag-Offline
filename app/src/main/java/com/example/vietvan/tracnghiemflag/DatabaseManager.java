package com.example.vietvan.tracnghiemflag;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.BitmapFactory;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import static com.example.vietvan.tracnghiemflag.FlagDbSchema.FlagTable;

/**
 * Created by VietVan on 16/05/2018.
 */

// Thao tác với db
public class DatabaseManager {

    private static final String TAG = "TAG";
    private SQLiteDatabase sqLiteDatabase;
    private AssetHelper assetHelper;
    private static DatabaseManager db;
    private Context context;

    // single tons
    public static DatabaseManager newInstance(Context context){
        if(db == null)
            db = new DatabaseManager(context);

        return db;
    }

    // Hàm khởi tọa với tham số context
    public DatabaseManager(Context context){
        this.context = context;
        assetHelper = new AssetHelper(context);

        sqLiteDatabase = assetHelper.getWritableDatabase();
    }

    // Hàm lấy tất cả bản ghi trong db
    public List<FlagResponse> getListFlag(){

        Cursor cursor = sqLiteDatabase.rawQuery("select * from " + FlagTable.NAME, null);
        List<FlagResponse> list = new ArrayList<>();
        cursor.moveToFirst();
        while(!cursor.isAfterLast()){
            byte[] imgByte = cursor.getBlob(1);
            list.add(new FlagResponse(cursor.getInt(0), BitmapFactory.decodeByteArray(imgByte, 0, imgByte.length),
                    cursor.getString(2), cursor.getInt(3),
                    cursor.getString(4), cursor.getString(5), cursor.getString(6)));

            cursor.moveToNext();
        }

        Log.d(TAG, "getListNote: " + list.size());
        return list;
    }

}
