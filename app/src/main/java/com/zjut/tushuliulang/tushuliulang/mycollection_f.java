package com.zjut.tushuliulang.tushuliulang;


import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Environment;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.zjut.tushuliulang.tushuliulang.net.Change_Info;
import com.zjut.tushuliulang.tushuliulang.net.STU_INFO;
import com.zjut.tushuliulang.tushuliulang.net.Search;
import com.zjut.tushuliulang.tushuliulang.net.login;


/**
 * A simple {@link Fragment} subclass.
 */
public class mycollection_f extends Fragment {

        String s="no";
        private TextView textView;
    public mycollection_f() {

        int a;
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.fragment_mycollection_f, container, false);
        textView = (TextView) v.findViewById(R.id.testttt);

//        textView.setText(s);
         new threadnet().execute();
        // Inflate the layout for this fragment
        return v;
    }
    class threadnet extends AsyncTask<String,String,String>
    {
//        TextView textView1;
        login l;
        Change_Info c;
        String st="";
        STU_INFO stu_info;
        @Override
        protected String doInBackground(String... params) {

            Search s = new Search("数据");
            s.fetch();

             return "";
        }

        @Override
        protected void onPostExecute(String s) {

            super.onPostExecute(s);

        }
    }

}