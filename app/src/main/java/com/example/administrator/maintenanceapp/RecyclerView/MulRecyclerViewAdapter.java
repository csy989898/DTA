package com.example.administrator.maintenanceapp.RecyclerView;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.TextView;


import com.bumptech.glide.Glide;
import com.example.administrator.maintenanceapp.Fragment.MyFragment1;
import com.example.administrator.maintenanceapp.R;
import com.youth.banner.Banner;
import com.youth.banner.BannerConfig;
import com.youth.banner.Transformer;
import com.youth.banner.listener.OnBannerListener;
import com.youth.banner.loader.ImageLoader;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2019/3/21.
 */

public class MulRecyclerViewAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> implements View.OnClickListener{
    private static final int NEW_SIMPLE_TYPE = 0;//单图文模式
    private static final int NEW_MUL_TYPE = 1;//多图文模式
    private static final int NEW_OTHER_TYPE = 2;     //多图文模式
    private static final int NEW_BANNER_TYPE = 3;
    private Context context;
    private List<NewsPhotoBean> list;

    public MulRecyclerViewAdapter(Context context, List<NewsPhotoBean> list) {
        this.context = context;
        this.list = list;
    }

    //重写getItemViewType方法,通过此方法来判断应该加载是哪种类型布局
    @Override
    public int getItemViewType(int position) {
        int type = list.get(position).getType();
        switch (type) {
            case 0:
                return NEW_SIMPLE_TYPE;
            case 1:
                return NEW_MUL_TYPE;
            case 3:
                return NEW_BANNER_TYPE;
        }
        return NEW_OTHER_TYPE;
    }
    //根据不同的item类型来加载不同的viewholder
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        switch (viewType) {
            case NEW_SIMPLE_TYPE:
                return new NewsPhotoViewHolder(inflater.inflate(R.layout.recyclerview_item_type_02, parent, false));
            case NEW_MUL_TYPE:
                return new NewsPhotosViewHolder(inflater.inflate(R.layout.recyclerview_item_type_01, parent, false));
            case NEW_BANNER_TYPE:
                return new NewsPhotosViewHolder(inflater.inflate(R.layout.recyclerview_item_type_03, parent, false));

        }
        return null;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {
        //把对应位置的数据得到
        String title = list.get(position).getTitle();
        String time = list.get(position).getF_time();
        String author = list.get(position).getAuthor();
        List<String> ls = list.get(position).getList();//这里是json数据中的图片集合，也就是封面。不同类型item的封面图片数量是不一样的
        //  //无论是否单图文，标题和更新时间以及作者不变

         List<String> imagePath1=list.get(position).getList();
         List<String> imageTitle1=list.get(position).getList();

        //如果单图文
        if (holder instanceof NewsPhotoViewHolder) {

            ((NewsPhotoViewHolder) holder).tx_news_simple_photos_title.setText(title);
            ((NewsPhotoViewHolder) holder).tx_news_simple_photos_time.setText(time);
            ((NewsPhotoViewHolder) holder).tx_news_simple_photos_author.setText(author);
            ((NewsPhotoViewHolder) holder).itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (mOnItemClickListener != null) {
                        mOnItemClickListener.onItemClick(position,"NewsPhotoViewHolder");
                    }
                }
            });
//            ((NewsPhotoViewHolder) holder).img_news_simple_photos_01.setImageBitmap(btm_01);//单图文不用遍历直接将图片转换bitmap对象设置到ImageView上
            return;
        }
        //如果多图文
        if (holder instanceof NewsPhotosViewHolder) {
            ((NewsPhotosViewHolder) holder).tx_news_mul_photos_title.setText(title);
            ((NewsPhotosViewHolder) holder).tx_news_mul_photos_time.setText(time);
            ((NewsPhotosViewHolder) holder).tx_news_mul_photos_author.setText(author);
//            ((NewsPhotosViewHolder) holder).img_news_mul_photos_01.setImageBitmap(btm_01);//多图文需要遍历list将每个图片链接转换成Bitmap对象设置到ImageView上
//            ((NewsPhotosViewHolder) holder).img_news_mul_photos_02.setImageBitmap(btm_02);
//            ((NewsPhotosViewHolder) holder).img_news_mul_photos_03.setImageBitmap(btm_03);
            ((NewsPhotosViewHolder) holder).itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (mOnItemClickListener != null) {
                        mOnItemClickListener.onItemClick(position,"NewsPhotosViewHolder");
                    }
                }
            });
            return;
        }
        //轮播图
        if (holder instanceof BannerViewHolder) {
            imagePath1 = new ArrayList<>();
            imageTitle1 = new ArrayList<>();
           /* imagePath1.add(R.mipmap.a5);
            imagePath1.add(R.mipmap.a8);
            imagePath1.add(R.mipmap.a11);
            imageTitle1.add("一号,hello");
            imageTitle1.add("二号,hello");
            imageTitle1.add("三号,hello");*/

            ((BannerViewHolder) holder).bannerholder.setBannerTitles(imageTitle1);
            ((BannerViewHolder) holder).bannerholder.setImages(imagePath1)
                    .setOnBannerListener((OnBannerListener) this)
                    .start();
            ((BannerViewHolder) holder).itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (mOnItemClickListener != null) {
                        mOnItemClickListener.onItemClick(position,"BannerViewHolder");
                    }
                }
            });
            return;
        }



    }

    //具体item数据等于pages*10，每页10条
    @Override
    public int getItemCount() {
        return list.size();
    }

    @Override
    public void onClick(View v) {
       /* int position = (int) v.getTag();      //getTag()获取数据
        if (mOnItemClickListener != null) {
            switch (v.getId()){
                case R.id.news_recycler_view:
                    //mOnItemClickListener.onItemClick(v, ViewName.PRACTISE, position);
                    break;
                default:
                    //mOnItemClickListener.onItemClick(v, ViewName.ITEM, position);
                    break;
            }
        }*/

    }

    /*===============item控件点击==============*/
    //自定义一个回调接口来实现Click和LongClick事件
    public interface OnItemClickListener  {
        void onItemClick(int position,String str);
        void onItemLongClick(View v);
    }

    private OnItemClickListener mOnItemClickListener;//声明自定义的接口

    //定义方法并传给外面的使用者
    public void setOnItemClickListener(OnItemClickListener  listener) {
        this.mOnItemClickListener  = listener;
    }


    /**
     * NewsPhotoViewHolder为单图文模式
     */
    class NewsPhotoViewHolder extends RecyclerView.ViewHolder  {
        private TextView tx_news_simple_photos_title;//标题

       // private ImageView img_news_simple_photos_01;//单图文模式的唯一一张图

        private TextView tx_news_simple_photos_time;//单图文模式的更新时间
        private TextView tx_news_simple_photos_author;//单图文模式的新闻作者
        private AdapterView.OnItemClickListener mListener;
        private AdapterView.OnItemLongClickListener mLongClickListener;
        public NewsPhotoViewHolder(View itemView) {
            super(itemView);
            tx_news_simple_photos_title = (TextView) itemView.findViewById(R.id.tx_news_simple_photos_title);//标题
           //img_news_simple_photos_01 = (ImageView) itemView.findViewById(R.id.tx_news_simple_photos_01);//单图文模式的唯一一张图
            tx_news_simple_photos_time = (TextView) itemView.findViewById(R.id.tx_news_simple_photos_time);//单图文模式的更新时间
            tx_news_simple_photos_author = (TextView) itemView.findViewById(R.id.img_news_simple_photos_author);//单图文模式的新闻作者

            tx_news_simple_photos_title.setOnClickListener(MulRecyclerViewAdapter.this);
            tx_news_simple_photos_time.setOnClickListener(MulRecyclerViewAdapter.this);
            tx_news_simple_photos_author.setOnClickListener(MulRecyclerViewAdapter.this);
        }


        /*
         * 点击监听
         */
       /* @Override
        public void onClick(View v) {
            if(mListener != null) mListener.onItemClick(v, getPosition());
        }*/

       /*
         * 长按监听
         */
       /* @Override
        public boolean onLongClick(View arg0) {
            if(mLongClickListener != null){
                mLongClickListener.onItemLongClick(arg0, getPosition());
            }
            return true;
        }*/

    }

    /**
     * NewsPhotosViewHolder为多图模式
     */
    class NewsPhotosViewHolder extends RecyclerView.ViewHolder {
        private TextView tx_news_mul_photos_title;//标题

      /*private ImageView img_news_mul_photos_01;//多图文模式的第一张图
      private ImageView img_news_mul_photos_02;//多图文模式的第二张图
      private ImageView img_news_mul_photos_03;//多图文模式的第三张图*/

        private TextView tx_news_mul_photos_time;//多图文模式的更新时间
        private TextView tx_news_mul_photos_author;//多图文模式的新闻作者

        public NewsPhotosViewHolder(View itemView) {
            super(itemView);
            tx_news_mul_photos_title = (TextView) itemView.findViewById(R.id.tx_news_mul_photos_title);

            /*img_news_mul_photos_01 = (ImageView) itemView.findViewById(R.id.img_news_mul_photos_01);
            img_news_mul_photos_02 = (ImageView) itemView.findViewById(R.id.img_news_mul_photos_02);
            img_news_mul_photos_03 = (ImageView) itemView.findViewById(R.id.img_news_mul_photos_03);*/

            tx_news_mul_photos_time = (TextView) itemView.findViewById(R.id.tx_news_mul_photos_time);
            tx_news_mul_photos_author = (TextView) itemView.findViewById(R.id.tx_news_mul_photos_author);
        }
    }

    /*轮播图*/
    class BannerViewHolder extends RecyclerView.ViewHolder{

        private Banner bannerholder;
        private MyImageLoader mMyImageLoader;
        private ArrayList<Integer> imagePath;
        private ArrayList<String> imageTitle;

        public BannerViewHolder(@NonNull View itemView) {
            super(itemView);
            bannerholder=itemView.findViewById(R.id.banner1);

            imagePath = new ArrayList<>();
            imageTitle = new ArrayList<>();
            imagePath.add(R.mipmap.a5);
            imagePath.add(R.mipmap.a8);
            imagePath.add(R.mipmap.a11);
            imageTitle.add("一号,hello");
            imageTitle.add("二号,hello");
            imageTitle.add("三号,hello");

            mMyImageLoader = new MyImageLoader();
            bannerholder.setBannerStyle(BannerConfig.CIRCLE_INDICATOR_TITLE);
            bannerholder.setImageLoader(mMyImageLoader);
            bannerholder.setBannerAnimation(Transformer.ZoomOutSlide);
            bannerholder.setBannerTitles(imageTitle);
            bannerholder.setDelayTime(3000);
            bannerholder.isAutoPlay(true);
            bannerholder.setIndicatorGravity(BannerConfig.CENTER);
            bannerholder.setImages(imagePath)
                    .setOnBannerListener((OnBannerListener) this)
                    .start();

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
    }


}
