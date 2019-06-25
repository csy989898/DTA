package com.example.administrator.maintenanceapp.Fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.administrator.maintenanceapp.R;


public class Fragment_weibao_shebei extends Fragment {
    private  View view;
    public Context mcontext;

    public Fragment_weibao_shebei() { }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
       view=inflater.inflate(R.layout.fragment_weibaoshebei,null);
        return view;
    }
}
