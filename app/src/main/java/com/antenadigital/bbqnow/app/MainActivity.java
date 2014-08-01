package com.antenadigital.bbqnow.app;

import java.util.ArrayList;

import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.AdapterView.OnItemClickListener;

public class MainActivity extends Activity {

    MyCustomAdapter dataAdapter = null;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Generate list View from ArrayList
        displayListView();

        checkButtonClick();

    }

    private void displayListView() {


        Drawable image = this.getResources().getDrawable(R.drawable.ic_launcher);


        //Array list of countries
        ArrayList<BBQItem> BBQItemList = new ArrayList<BBQItem>();
        BBQItem BBQItem = new BBQItem(image,"Picanha",false);
        BBQItemList.add(BBQItem);
        BBQItem = new BBQItem(image,"Maminha",false);
        BBQItemList.add(BBQItem);
        BBQItem = new BBQItem(image,"Peito de Frango",false);
        BBQItemList.add(BBQItem);
        BBQItem = new BBQItem(image,"Linguiça",false);
        BBQItemList.add(BBQItem);
        BBQItem = new BBQItem(image,"Coração",false);
        BBQItemList.add(BBQItem);
        BBQItem = new BBQItem(image,"Asa de Frango",false);
        BBQItemList.add(BBQItem);
        BBQItem = new BBQItem(image,"Cerveja",false);
        BBQItemList.add(BBQItem);
        BBQItem = new BBQItem(image,"Carvão",false);
        BBQItemList.add(BBQItem);
        BBQItem = new BBQItem(image,"Sal grosso",false);
        BBQItemList.add(BBQItem);

        //create an ArrayAdapter from the String Array
        dataAdapter = new MyCustomAdapter(this,
                R.layout.bbqitem_info, BBQItemList);
        ListView listView = (ListView) findViewById(R.id.listView1);
        // Assign adapter to ListView
        listView.setAdapter(dataAdapter);


        listView.setOnItemClickListener(new OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {
                // When clicked, show a toast with the TextView text
                BBQItem BBQItem = (BBQItem) parent.getItemAtPosition(position);
                Toast.makeText(getApplicationContext(),BBQItem.getName(),
                        Toast.LENGTH_LONG).show();
            }
        });

    }

    private class MyCustomAdapter extends ArrayAdapter<BBQItem> {

        private ArrayList<BBQItem> BBQItemList;

        public MyCustomAdapter(Context context, int textViewResourceId,
                               ArrayList<BBQItem> BBQItemList) {
            super(context, textViewResourceId, BBQItemList);
            this.BBQItemList = new ArrayList<BBQItem>();
            this.BBQItemList.addAll(BBQItemList);
        }

        private class ViewHolder {
            CheckBox name;
        }

        @TargetApi(Build.VERSION_CODES.JELLY_BEAN)
        @Override
        public View getView(int position, View convertView, ViewGroup parent) {

            ViewHolder holder = null;
            Log.v("ConvertView", String.valueOf(position));

            if (convertView == null) {
                LayoutInflater vi = (LayoutInflater)getSystemService(
                        Context.LAYOUT_INFLATER_SERVICE);
                convertView = vi.inflate(R.layout.bbqitem_info, null);

                holder = new ViewHolder();
                holder.name = (CheckBox) convertView.findViewById(R.id.checkBox1);
                convertView.setTag(holder);

                holder.name.setOnClickListener( new View.OnClickListener() {
                    public void onClick(View v) {
                        CheckBox cb = (CheckBox) v;
                        BBQItem BBQItem = (BBQItem) cb.getTag();

                        if (cb.isChecked())

                            Toast.makeText(getApplicationContext(),
                                    "Você adicionou : " + cb.getText() +
                                            " à lista ! ",
                                    Toast.LENGTH_LONG).show();


                        else

                            Toast.makeText(getApplicationContext(),
                                    "Você removeu : " + cb.getText() +
                                            " da lista ! ",
                                    Toast.LENGTH_LONG).show();


                        BBQItem.setSelected(cb.isChecked());
                    }
                });
            }

            else {
                holder = (ViewHolder) convertView.getTag();
            }

            BBQItem BBQItem = BBQItemList.get(position);
            //holder.name.setBackground(BBQItem.getImage());
            holder.name.setText(BBQItem.getName());
            holder.name.setChecked(BBQItem.isSelected());
            holder.name.setTag(BBQItem);

            return convertView;

        }

    }

    private void checkButtonClick() {


        Button myButton = (Button) findViewById(R.id.findSelected);
        myButton.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {

                StringBuffer responseText = new StringBuffer();
                responseText.append("Temos até o momento...\n");

                ArrayList<BBQItem> BBQItemList = dataAdapter.BBQItemList;
                for(int i=0;i<BBQItemList.size();i++){
                    BBQItem BBQItem = BBQItemList.get(i);
                    if(BBQItem.isSelected()){
                        responseText.append("\n" + BBQItem.getName());
                    }
                }

                Toast.makeText(getApplicationContext(),
                        responseText, Toast.LENGTH_LONG).show();

            }
        });

    }

}