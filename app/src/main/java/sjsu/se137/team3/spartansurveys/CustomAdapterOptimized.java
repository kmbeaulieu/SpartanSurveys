/*
package sjsu.se137.team3.spartansurveys;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

*/
/**
 * Created by Krystle on 5/4/2016.
 *//*

private class CustomAdapterOptimized extends ArrayAdapter<Survey> {

    public CustomAdapterOptimized(Context context, int textViewResourceId, List<Survey> objects) {
        super(context, textViewResourceId, objects);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        return getViewOptimize(position, convertView, parent);
    }

    public View getViewOptimize(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder = null;
        if (convertView == null) {
            LayoutInflater inflater = (LayoutInflater)
                    getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.example_item, null);
            viewHolder = new ViewHolder();
            viewHolder.exampleID= (TextView) convertView.findViewById(R.id.exampleID);
            viewHolder.exampleName= (TextView) convertView.findViewById(R.id.exampleName);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        Survey example = (Survey)getItem(position);
        viewHolder.exampleID.setText(example.ID);
        viewHolder.exampleName.setText(example.name);
        return convertView;
    }

    private class ViewHolder {
        public TextView exampleID;
        public TextView exampleName;
    }
}*/
