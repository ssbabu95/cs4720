package com.example.sumeetbabu.poiapp;

import android.content.Context;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CursorAdapter;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.io.ByteArrayInputStream;

/**
 * Created by sumeetbabu on 9/28/15.
 */
public class poiAdapter extends CursorAdapter {
    public poiAdapter(Context context, Cursor cursor, int flags) {
        super(context, cursor, 0);
    }

    // The newView method is used to inflate a new view and return it,
    // you don't bind any data to the view at this point.
    @Override
    public View newView(Context context, Cursor cursor, ViewGroup parent) {
        return LayoutInflater.from(context).inflate(R.layout.poi_lay, parent, false);
    }

    // The bindView method is used to bind all data to a given view
    // such as setting the text on a TextView.
    @Override
    public void bindView(View view, Context context, Cursor cursor) {
        // Find fields to populate in inflated template
        TextView poiName = (TextView) view.findViewById(R.id.showName);
        TextView poiLocation = (TextView) view.findViewById(R.id.showLocation);
        TextView poiDate = (TextView) view.findViewById(R.id.showDate);
        TextView poiCreator = (TextView) view.findViewById(R.id.showCreator);
        ImageView poiImg = (ImageView) view.findViewById(R.id.showImg);
        // Extract properties from cursor
        String titl = cursor.getString(cursor.getColumnIndexOrThrow("name"));
        String loc = cursor.getString(cursor.getColumnIndexOrThrow("location"));
        String dat = cursor.getString(cursor.getColumnIndexOrThrow("date"));
        String cre = cursor.getString(cursor.getColumnIndexOrThrow("creator"));
        byte[] imageByteArray=cursor.getBlob(cursor.getColumnIndexOrThrow("image"));
        Bitmap bmp = BitmapFactory.decodeByteArray(imageByteArray, 0, imageByteArray.length);
        // Populate fields with extracted properties
        poiName.setText(titl);
        poiLocation.setText(String.valueOf(loc));
        poiDate.setText(dat);
        poiCreator.setText(cre);
        poiImg.setImageBitmap(Bitmap.createScaledBitmap(bmp, bmp.getWidth()*2, bmp.getHeight()*2, false));
    }
}