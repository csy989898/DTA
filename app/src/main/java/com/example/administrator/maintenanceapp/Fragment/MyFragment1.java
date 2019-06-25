package com.example.administrator.maintenanceapp.Fragment;

import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.chanven.lib.cptr.PtrClassicFrameLayout;
import com.chanven.lib.cptr.PtrDefaultHandler;
import com.chanven.lib.cptr.PtrFrameLayout;
import com.chanven.lib.cptr.loadmore.OnLoadMoreListener;
import com.chanven.lib.cptr.recyclerview.RecyclerAdapterWithHF;
import com.example.administrator.maintenanceapp.Bean.MyGrideAdapter;
import com.example.administrator.maintenanceapp.Bean.NewsAdapter;
import com.example.administrator.maintenanceapp.Bean.NewsBean;
import com.example.administrator.maintenanceapp.Bean.mesgBean;
import com.example.administrator.maintenanceapp.R;
import com.example.administrator.maintenanceapp.RecyclerView.MulRecyclerViewAdapter;
import com.example.administrator.maintenanceapp.RecyclerView.NewsPhotoBean;
import com.youth.banner.Banner;
import com.youth.banner.BannerConfig;
import com.youth.banner.Transformer;
import com.youth.banner.listener.OnBannerListener;
import com.youth.banner.loader.ImageLoader;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by csy on 2019/6/21 .
 */
public class MyFragment1 extends Fragment implements OnBannerListener {
    private Banner mBanner;
    private MyImageLoader mMyImageLoader;
    private ArrayList<Integer> imagePath;
    private ArrayList<String> imageTitle;
    private View view;
    private Context mContext;
    public MyFragment1() {
    }

    private GridView grid_mesg;
    private BaseAdapter mAdapter = null;
    private ArrayList<mesgBean> mData = null;
    private List<NewsBean> newsData = null;
    private NewsAdapter newsAdapter = null;
    private ListView list_news;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fg_content1, container, false);
        mContext=getActivity();
        initData();
        initView();
        initMesgView();
        initListNews();
        //adapter.setOnItemClickListener(MyItemClickListener);
        Log.e("HEHE", "第一个Fragment");
        return view;
    }

    private void initData() {
        imagePath = new ArrayList<>();
        imageTitle = new ArrayList<>();
        imagePath.add(R.mipmap.a5);
        imagePath.add(R.mipmap.a8);
        imagePath.add(R.mipmap.a11);
        imageTitle.add("一号,hello");
        imageTitle.add("二号,hello");
        imageTitle.add("三号,hello");
    }

    private void initView() {
        mMyImageLoader = new MyImageLoader();
        mBanner = view.findViewById(R.id.banner);
        //设置样式，里面有很多种样式可以自己都看看效果
        mBanner.setBannerStyle(BannerConfig.CIRCLE_INDICATOR_TITLE);
        //设置图片加载器
        mBanner.setImageLoader(mMyImageLoader);
        //设置轮播的动画效果,里面有很多种特效,可以都看看效果。
        mBanner.setBannerAnimation(Transformer.ZoomOutSlide);
        //轮播图片的文字
        mBanner.setBannerTitles(imageTitle);
        //设置轮播间隔时间
        mBanner.setDelayTime(3000);
        //设置是否为自动轮播，默认是true
        mBanner.isAutoPlay(true);
        //设置指示器的位置，小点点，居中显示
        mBanner.setIndicatorGravity(BannerConfig.CENTER);
        //设置图片加载地址
        mBanner.setImages(imagePath)
                //轮播图的监听
                .setOnBannerListener(this)
                //开始调用的方法，启动轮播图。
                .start();

    }


    private void initMesgView(){
        grid_mesg = (GridView) view.findViewById(R.id.grid_msg);

        mData = new ArrayList<mesgBean>();
        mData.add(new mesgBean(R.mipmap.mes1, "通知"));
        mData.add(new mesgBean(R.mipmap.mes2, "维保逾期"));
        mData.add(new mesgBean(R.mipmap.mes3, "消息"));
        mData.add(new mesgBean(R.mipmap.mes4, "应急救援"));
        mData.add(new mesgBean(R.mipmap.mes5, "故障处理"));


        mAdapter = new MyGrideAdapter<mesgBean>(mData, R.layout.item_grid_mesg_layout) {
            @Override
            public void bindView(ViewHolder holder, mesgBean obj) {
                holder.setImageResource(R.id.img_icon, obj.getiId());
                holder.setText(R.id.txt_icon, obj.getiName());
            }
        };

        grid_mesg.setAdapter(mAdapter);

        grid_mesg.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                switch (position){
                    case 0:
                        Toast.makeText(mContext, "你点击了" + position + "通知项", Toast.LENGTH_SHORT).show();
                        break;
                    case 1:
                        Toast.makeText(mContext, "你点击了" + position + "维保逾期项", Toast.LENGTH_SHORT).show();
                        break;
                    case 2:
                        Toast.makeText(mContext, "你点击了" + position + "消息项", Toast.LENGTH_SHORT).show();
                        break;
                    case 3:
                        Toast.makeText(mContext, "你点击了" + position + "应急救援项", Toast.LENGTH_SHORT).show();
                        break;
                    case 4:
                        Toast.makeText(mContext, "你点击了" + position + "故障处理项", Toast.LENGTH_SHORT).show();
                        break;

                }
            }
        });




    }

    private void initListNews(){
        list_news = (ListView) view.findViewById(R.id.list_msg);
        newsData = new LinkedList<NewsBean>();
        newsData.add(new NewsBean("NewsTitle1", "NewsContent1", R.mipmap.ic_launcher));
        newsData.add(new NewsBean("NewsTitle2", "NewsContent2", R.mipmap.ic_launcher));
        newsData.add(new NewsBean("NewsTitle3", "NewsContent3", R.mipmap.ic_launcher));
        newsData.add(new NewsBean("NewsTitle4", "NewsContent4", R.mipmap.ic_launcher));
        newsData.add(new NewsBean("NewsTitle5", "NewsContent5", R.mipmap.ic_launcher));

        newsAdapter = new NewsAdapter((LinkedList<NewsBean>) newsData, mContext);
        list_news.setAdapter(newsAdapter);
    }

    /**
     * 轮播图的监听
     * @param position
     */
    @Override
    public void OnBannerClick(int position) {
        Toast.makeText(getActivity(), "你点了第" + (position + 1) + "张轮播图", Toast.LENGTH_SHORT).show();
    }


    /**
     * 图片加载类
     */
    private class MyImageLoader extends ImageLoader {
        @Override
        public void displayImage(Context context, Object path, ImageView imageView) {
            Glide.with(context.getApplicationContext())
                    .load(path)
                    .into(imageView);
        }
    }

    /**
     * item＋item里的控件点击监听事件
     */
    /*private MulRecyclerViewAdapter.OnItemClickListener MyItemClickListener=new MulRecyclerViewAdapter.OnItemClickListener() {

            @Override
            public void onItemClick(View v, MulRecyclerViewAdapter.ViewName viewName, int position) {
                //viewName可区分item及item内部控件
                switch (v.getId()){
                    case R.id.tx_news_simple_photos_title:
                        Toast.makeText(getActivity(),"你点击了标题"+(position+1),Toast.LENGTH_SHORT).show();
                        break;
                    case R.id.tx_news_simple_photos_time:
                        Toast.makeText(getActivity(),"你点击了时间"+(position+1),Toast.LENGTH_SHORT).show();
                        break;
                    default:
                        Toast.makeText(getActivity(),"你点击了item按钮"+(position+1),Toast.LENGTH_SHORT).show();
                        break;
                }


            }

            @Override
            public void onItemLongClick(View v) {

            }
        };*/




}
