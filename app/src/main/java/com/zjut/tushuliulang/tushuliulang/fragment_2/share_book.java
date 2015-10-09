package com.zjut.tushuliulang.tushuliulang.fragment_2;


import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.zjut.tushuliulang.tushuliulang.R;
import com.zjut.tushuliulang.tushuliulang.activities.Book_share_info;
import com.zjut.tushuliulang.tushuliulang.listadapter_share;
import com.zjut.tushuliulang.tushuliulang.bookshare.BOOK_SHARE;
import com.zjut.tushuliulang.tushuliulang.bookshare.getbookshares;
import com.zjut.tushuliulang.tushuliulang.question.question.FloatingActionButton;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * A simple {@link Fragment} subclass.
 */
public class share_book extends Fragment implements ListView.OnItemClickListener,SwipeRefreshLayout.OnRefreshListener{
    private List<Map<String,Object>> list;
    private ListView listView;
    private boolean istop;
    private boolean isbotton = false;
    private boolean addedfooterlish = false;
    private SwipeRefreshLayout swipeRefreshLayout;
    private BOOK_SHARE[] shares;
    private getbookshares getshares;

    private FloatingActionButton add_more;

    List<Map<String, Object>> l = new ArrayList<Map<String, Object>>();

    public share_book() {
        // Required empty public constructor

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_share_book,container,false);

        // Inflate the layout for this fragment
        swipeRefreshLayout = (SwipeRefreshLayout)view.findViewById(R.id.book_share_swiperefresh);
        swipeRefreshLayout.setOnRefreshListener(this);


        listView = (ListView) view.findViewById(R.id.share_book_listview);
        listView .setOnItemClickListener(this);


        istopped();

        new getshare().execute("");


        add_more = (FloatingActionButton) view.findViewById(R.id.fabbutton_book_share);
        add_more.listenTo(listView);
        add_more.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String next = getshares.getnext();

                if (next.equals("0"))
                {
                    Toast.makeText(getActivity(),"没有啦！",Toast.LENGTH_SHORT).show();
                }
                else
                {
                    new getshare().execute(next);

                }
            }
        });
        return view;
    }


    public boolean istopped()
    {
//        boolean b = false;
        listView.setOnScrollListener(new AbsListView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(AbsListView view, int scrollState) {

            }

            @Override
            public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {

                if (firstVisibleItem == 0) {
                    Log.e("s", "top");
                    istop = true;
                    swipeRefreshLayout.setEnabled(true);
                } else {
                    swipeRefreshLayout.setEnabled(false);
                }
                if (firstVisibleItem + visibleItemCount == totalItemCount) {
                    Log.e("s", "bottom");
                    istop = false;
                    isbotton = true;

                    //底部 加载更多TE

                } else {
                    isbotton = false;
                }
            }

        });




        return istop;
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Intent intent = new Intent(getActivity(),Book_share_info.class);
        intent.putExtra("order",shares[position].number_order);

        startActivity(intent);
    }

    @Override
    public void onRefresh() {
        l = new ArrayList<Map<String, Object>>();
        new getshare().execute("");
    }


    class getshare extends AsyncTask<String,String,String>
    {

        @Override
        protected String doInBackground(String... params) {


            if (params[0].equals("")) {
                getshares = new getbookshares();
            }
            else
            {
                getshares = new getbookshares(params[0]);
            }

            if(getshares.fetch()) {
                if (params[0].equals("")) {
                    shares = getshares.getShares();
                }
                else
                {
                   int total = shares.length + getshares.gettotal();
                    BOOK_SHARE[] newshare = getshares.getShares();
                    BOOK_SHARE[] oldshare = shares;
                    shares = new BOOK_SHARE[total];
                    for(int n=0;n<oldshare.length;n++)
                    {
                        shares[n] = oldshare[n];
                    }
                    for (int n = oldshare.length, ns=0;n<total;n++,ns++)
                    {
                        shares[n] = newshare[ns];
                    }
                }


                for (int n = 0; n < shares.length; n++) {
                    Map<String, Object> map = new HashMap<String, Object>();
                         map.put("a", shares[n].book_name);
                        map.put("b", shares[n].intro);
                        map.put("i", shares[n].number_order);
                        map.put("type","0");
                         l.add(map);
                }

            }
            return null;
        }

        @Override
        protected void onPostExecute(String s) {
            int position = listView.getFirstVisiblePosition();
            listView.setAdapter(new listadapter_share(getActivity(), l));

            listView.setSelection(position);
            swipeRefreshLayout.setRefreshing(false);


//            }
        }
    }

}



