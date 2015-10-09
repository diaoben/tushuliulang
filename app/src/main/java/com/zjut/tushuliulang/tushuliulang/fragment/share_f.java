package com.zjut.tushuliulang.tushuliulang.fragment;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;

import com.zjut.tushuliulang.tushuliulang.R;
import com.zjut.tushuliulang.tushuliulang.fragment_2.share_book;
import com.zjut.tushuliulang.tushuliulang.widget.TopIcon;

import java.util.ArrayList;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class share_f extends Fragment implements ViewPager.OnPageChangeListener,View.OnClickListener,View.OnTouchListener{

    private List<Fragment> listfragment = new ArrayList<Fragment>();
    private ViewPager viewPager;
    private TopIcon[] topIcons;
    private SwipeRefreshLayout swipeRefreshLayout;


    private FragmentPagerAdapter adapter;



    public share_f() {

        // Required empty public constructor
    }


    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
       initcontroil(getView());
       viewPager.setCurrentItem(0);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View i= inflater.inflate(R.layout.fragment_share_f, container, false);

        return i;
    }

     private void initcontroil(View i ) {
        viewPager = (ViewPager) i.findViewById(R.id.share_viewpager);







         viewPager.setOnPageChangeListener(this);
        listfragment.add(new share_book());

        adapter = new FragmentPagerAdapter(getActivity().getSupportFragmentManager()) {
            @Override
            public Fragment getItem(int i) {
                return listfragment.get(i);
            }

            @Override
            public int getCount() {
                return listfragment.size();
            }
        };
         viewPager.setAdapter(adapter);

//         linearLayout.setOnTouchListener(this);

    }


    @Override
    public void onPageScrolled(int i, float v, int i2) {

    }

    @Override
    public void onPageSelected(int i) {

    }

    @Override
    public void onPageScrollStateChanged(int i) {

    }

    @Override
    public void onClick(View v) {

    }


    @Override
    public boolean onTouch(View v, MotionEvent event) {
        return false;
    }
}
