package ch.sowatec.pg.notenapp;

import android.content.Context;
import android.widget.ArrayAdapter;

import java.util.HashMap;
import java.util.List;

public class MyArrayAdapter extends ArrayAdapter<Object> {
    HashMap<Object, Integer> mIdMap = new HashMap<Object, Integer>();

    public MyArrayAdapter(Context context, int textViewResourceId,
                          List<Object> objects) {
        super(context, textViewResourceId, objects);
        for (int i = 0; i < objects.size(); ++i) {
            mIdMap.put(objects.get(i), i);
        }
    }

    @Override
    public long getItemId(int position) {
        Object item = getItem(position);
        return mIdMap.get(item);
    }

    @Override
    public boolean hasStableIds() {
        return true;
    }

}