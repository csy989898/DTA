package com.example.administrator.maintenanceapp.Fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.example.administrator.maintenanceapp.R;

/**
 * Created by csy on 2019/5/21 .
 */
public class MyFragment2 extends Fragment implements View.OnClickListener{

    private View view;
    private FragmentManager manager;
    private FrameLayout frameLayout;
    /**
     * fragmen1
     */
    private TextView mBtn1;
    /**
     * fragment2
     */
    private TextView mBtn2;
    private FrameLayout mFragment;
    private Fragment_rearchtools f1,f2;

    public MyFragment2() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fg_content2,container,false);
        initView(view);
        manager=getFragmentManager();
        mBtn1.performClick();
        Log.e("HEHE", "第二个Fragment");
        return view;
    }

        public void selected(){
            mBtn1.setSelected(false);
            mBtn2.setSelected(false);
        }

        public void hindtransaction(FragmentTransaction fragmentTransaction){
            if(f1!=null)fragmentTransaction.show(f1);
            if(f2!=null)fragmentTransaction.show(f2);
        }

        private void initView(View view) {
            mBtn1 = (TextView) view.findViewById(R.id.btn1);
            mBtn1.setOnClickListener(this);
            mBtn2 = (TextView) view.findViewById(R.id.btn2);
            mBtn2.setOnClickListener(this);
            mFragment = (FrameLayout) view.findViewById(R.id.frag);
        }


        @Override
        public void onClick(View v) {
            FragmentTransaction transaction=manager.beginTransaction();
            hindtransaction(transaction);
            switch (v.getId()) {
                default:
                    break;
                case R.id.btn1:
                    selected();
                    mBtn1.setSelected(true);
                    if(f1==null){
                        Fragment_rearchtools btn1=new Fragment_rearchtools();
                        btn1.mcontext=getActivity();
                        transaction.replace(R.id.frag,btn1);
                        mBtn1.setBackgroundResource(R.drawable.statusbg1);
                        mBtn2.setBackgroundResource(R.drawable.statusbg2);
                    }else {
                        transaction.show(f1);
                    }

                    break;
                case R.id.btn2:
                    selected();
                    mBtn2.setSelected(true);
                    if(f2==null){
                        Fragment_weibao_shebei btn2=new Fragment_weibao_shebei();
                        btn2.mcontext=getActivity();
                        transaction.replace(R.id.frag,btn2);
                        mBtn2.setBackgroundResource(R.drawable.statusbg1);
                        mBtn1.setBackgroundResource(R.drawable.statusbg2);
                    }else {
                        transaction.show(f2);
                    }
                    break;
            }
            transaction.commit();
        }

}
