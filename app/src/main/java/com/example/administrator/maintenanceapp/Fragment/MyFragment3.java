package com.example.administrator.maintenanceapp.Fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.baidu.location.BDLocation;
import com.baidu.location.BDLocationListener;
import com.baidu.location.LocationClient;
import com.baidu.location.LocationClientOption;
import com.baidu.mapapi.SDKInitializer;
import com.baidu.mapapi.map.BaiduMap;
import com.baidu.mapapi.map.BaiduMapOptions;
import com.baidu.mapapi.map.BitmapDescriptor;
import com.baidu.mapapi.map.BitmapDescriptorFactory;
import com.baidu.mapapi.map.InfoWindow;
import com.baidu.mapapi.map.LogoPosition;
import com.baidu.mapapi.map.MapPoi;
import com.baidu.mapapi.map.MapStatus;
import com.baidu.mapapi.map.MapStatusUpdate;
import com.baidu.mapapi.map.MapStatusUpdateFactory;
import com.baidu.mapapi.map.MapView;
import com.baidu.mapapi.map.Marker;
import com.baidu.mapapi.map.MarkerOptions;
import com.baidu.mapapi.map.MyLocationData;
import com.baidu.mapapi.map.Overlay;
import com.baidu.mapapi.map.OverlayOptions;
import com.baidu.mapapi.map.TextOptions;
import com.baidu.mapapi.map.UiSettings;
import com.baidu.mapapi.map.offline.MKOLSearchRecord;
import com.baidu.mapapi.map.offline.MKOfflineMap;
import com.baidu.mapapi.map.offline.MKOfflineMapListener;
import com.baidu.mapapi.model.LatLng;
import com.example.administrator.maintenanceapp.R;
import com.example.administrator.maintenanceapp.SDKReceiver.SDKReceiver;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by csy on 2019/5/21 .
 */
public class MyFragment3 extends Fragment  implements View.OnClickListener{

    private MapView mMapView;
    private BaiduMap mBaiduMap;
    public LocationClient mLocationClient;
    public BDLocationListener myListener = new MyLocationListener();
    private Button bt;
    private Button button;
    private Button buttons;
    private LatLng latLng;
    private boolean isFirstLoc = true; // 是否首次定位

    private View view;

    private int[]  makers={R.mipmap.maker1,R.mipmap.maker2,R.mipmap.maker3,R.mipmap.maker4
            ,R.mipmap.maker5,R.mipmap.maker6,R.mipmap.maker7,R.mipmap.maker8
    };

    private SDKReceiver mReceiver;

    /*private MapView mMapView;
    private Context mContext;
    private View view;*/
    public MyFragment3() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        //在使用SDK各组件之前初始化context信息，传入ApplicationContext
        //注意该方法要再setContentView方法之前实现
        SDKInitializer.initialize(getActivity().getApplicationContext());
        view = inflater.inflate(R.layout.fg_content3,container,false);
       // view = LayoutInflater.from(getContext()).inflate(R.layout.fg_content3,null);

        initView();
        initMap();
        //setMakers();
        /*mMapView = (MapView) view.findViewById(R.id.bmapView);
        BaiduMapOptions options = new BaiduMapOptions();
        //设置地图模式为卫星地图
        options.mapType(BaiduMap.MAP_TYPE_SATELLITE);*/

        Log.e("HEHE", "第三个Fragment");
        return view;
    }

    private void initMap() {
        //获取地图控件引用
        mBaiduMap = mMapView.getMap();
        //普通地图
        mBaiduMap.setMapType(BaiduMap.MAP_TYPE_NORMAL);
        //开启地图的定位图层
        mBaiduMap.setMyLocationEnabled(true);

        //默认显示普通地图
        mBaiduMap.setMapType(BaiduMap.MAP_TYPE_NORMAL);
        //开启交通图
       // mBaiduMap.setTrafficEnabled(true);
        //开启热力图
        //mBaiduMap.setBaiduHeatMapEnabled(true);
        // 开启定位图层
        mBaiduMap.setMyLocationEnabled(true);

        mLocationClient = new LocationClient(getActivity().getApplicationContext());     //声明LocationClient类
        //配置定位SDK参数
        initLocation();
        //注册监听函数
        mLocationClient.registerLocationListener(myListener);

       /* //注册LocationListener监听器
        MyLocationListener myLocationListener = new MyLocationListener();
        mLocationClient.registerLocationListener(myLocationListener);*/

        //开启定位
        mLocationClient.start();
        //图片点击事件，回到定位点
        mLocationClient.requestLocation();
    }

    //配置定位SDK参数，通过LocationClient发起定位
    private void initLocation() {
        //通过LocationClientOption设置LocationClient相关参数
        LocationClientOption option = new LocationClientOption();
        option.setLocationMode(LocationClientOption.LocationMode.Hight_Accuracy
        );//可选，默认高精度，设置定位模式，高精度，低功耗，仅设备
        option.setCoorType("bd09ll");//可选，默认gcj02，设置返回的定位结果坐标系
        int span = 1000;
        option.setScanSpan(span);//可选，默认0，即仅定位一次，设置发起定位请求的间隔需要大于等于1000ms才是有效的
        option.setIsNeedAddress(true);//可选，设置是否需要地址信息，默认不需要
        option.setOpenGps(true);//可选，默认false,设置是否使用gps
        option.setLocationNotify(true);//可选，默认false，设置是否当GPS有效时按照1S/1次频率输出GPS结果
        option.setIsNeedLocationDescribe(true);//可选，默认false，设置是否需要位置语义化结果，可以在BDLocation
        // .getLocationDescribe里得到，结果类似于“在北京天安门附近”
        option.setIsNeedLocationPoiList(true);//可选，默认false，设置是否需要POI结果，可以在BDLocation.getPoiList里得到
        option.setIgnoreKillProcess(false);
        option.setOpenGps(true); // 打开gps

        //可选，默认true，定位SDK内部是一个SERVICE，并放到了独立进程，设置是否在stop的时候杀死这个进程，默认不杀死
        option.SetIgnoreCacheException(false);//可选，默认false，设置是否收集CRASH信息，默认收集
        option.setEnableSimulateGps(false);//可选，默认false，设置是否需要过滤GPS仿真结果，默认需要
        mLocationClient.setLocOption(option);
    }

    //实现BDLocationListener接口,BDLocationListener为结果监听接口，异步获取定位结果
    public class MyLocationListener implements BDLocationListener {

        @Override
        public void onReceiveLocation(BDLocation location) {
            latLng = new LatLng(location.getLatitude(), location.getLongitude());
            // 构造定位数据
            MyLocationData locData = new MyLocationData.Builder()
                    .accuracy(location.getRadius())
                    // 此处设置开发者获取到的方向信息，顺时针0-360
                    .direction(100).latitude(location.getLatitude())
                    .longitude(location.getLongitude()).build();
            // 设置定位数据
            mBaiduMap.setMyLocationData(locData);
            // 当不需要定位图层时关闭定位图层
            //mBaiduMap.setMyLocationEnabled(false);
            if (isFirstLoc) {
                isFirstLoc = false;
                LatLng ll = new LatLng(location.getLatitude(),
                        location.getLongitude());
                MapStatus.Builder builder = new MapStatus.Builder();
                builder.target(ll).zoom(18.0f);
                mBaiduMap.animateMapStatus(MapStatusUpdateFactory.newMapStatus(builder.build()));

                if (location.getLocType() == BDLocation.TypeGpsLocation) {
                    // GPS定位结果
                    Toast.makeText(getActivity(), location.getAddrStr(), Toast.LENGTH_SHORT).show();
                } else if (location.getLocType() == BDLocation.TypeNetWorkLocation) {
                    // 网络定位结果
                    Toast.makeText(getActivity(), location.getAddrStr(), Toast.LENGTH_SHORT).show();

                } else if (location.getLocType() == BDLocation.TypeOffLineLocation) {
                    // 离线定位结果
                    Toast.makeText(getActivity(), location.getAddrStr(), Toast.LENGTH_SHORT).show();

                } else if (location.getLocType() == BDLocation.TypeServerError) {
                    Toast.makeText(getActivity(), "服务器错误，请检查", Toast.LENGTH_SHORT).show();
                } else if (location.getLocType() == BDLocation.TypeNetWorkException) {
                    Toast.makeText(getActivity(), "网络错误，请检查", Toast.LENGTH_SHORT).show();
                } else if (location.getLocType() == BDLocation.TypeCriteriaException) {
                    Toast.makeText(getActivity(), "手机模式错误，请检查是否飞行", Toast.LENGTH_SHORT).show();
                }
            }
        }
    }

    /*绘制Maker*/
    public void setMakers(){
        /*//创建OverlayOptions的集合
        List<OverlayOptions> options = new ArrayList<OverlayOptions>();
        //构造大量坐标数据
        LatLng point1 = new LatLng(39.92235, 116.380338);
        LatLng point2 = new LatLng(39.947246, 116.414977);
        LatLng point3 = new LatLng(39.937246, 116.314977);
        //创建OverlayOptions属性
        OverlayOptions option1 =  new MarkerOptions()
                .position(point1)
                .icon(bdA);
        OverlayOptions option2 =  new MarkerOptions()
                .position(point2)
                .icon(bdA);
        OverlayOptions option3 =  new MarkerOptions()
                .position(point3)
                .icon(bdA);*/

        //定义Maker坐标点
        LatLng point2 = new LatLng(121.963175, 31.400244);
        //构建Marker图标
        BitmapDescriptor bitmap2 = BitmapDescriptorFactory
                .fromResource(R.mipmap.maker1);
        //构建MarkerOption，用于在地图上添加Marker
        OverlayOptions option2 = new MarkerOptions()
                .position(point2)
                .icon(bitmap2);
              /*  .draggable(true)   //是否可拖拽
//设置平贴地图，在地图中双指下拉查看效果
                .flat(true)     //是否平贴地图 (俯视图)（双手下拉地图查看效果）
                .alpha(0.5f);*/  //透明度
        //在地图上添加Marker，并显示
        mBaiduMap.addOverlay(option2);
        setOnMapClickListener();
    }

    private void setOnMapClickListener(){
        BaiduMap.OnMarkerClickListener listener = new BaiduMap.OnMarkerClickListener() {
            /**
             * 地图 Marker 覆盖物点击事件监听函数
             * @param marker 被点击的 marker
             */
            public boolean onMarkerClick(Marker marker){
                setInfoWindow();
                return false;
            }
        };
    }

/*
* 文字覆盖物
* 添加信息窗（弹窗覆盖物InfoWindow）
* */
    private InfoWindow mInfoWindow;
    private void setInfoWindow(){
        //用来构造InfoWindow的Button
        Button button = new Button(getActivity().getApplicationContext());
        button.setBackgroundResource(R.mipmap.maker2);
        button.setText("松江区");
        //-100 InfoWindow相对于point在y轴的偏移量
        LatLng point1 = new LatLng(121.227747, 31.032243);
         mInfoWindow = new InfoWindow(button, point1, -100);
        //使InfoWindow生效
        mBaiduMap.showInfoWindow(mInfoWindow);

        /*//文字覆盖物位置坐标
        LatLng llText = new LatLng(121.544721, 31.221959);
        //构建TextOptions对象
        OverlayOptions mTextOptions = new TextOptions()
                .text("当前位置浦东新区") //文字内容
                .bgColor(0xAAFFFF00) //背景色
                .fontSize(24) //字号
                .fontColor(0xFFFF00FF) //文字颜色
                .rotate(-30) //旋转角度
                .position(llText);

        //在地图上显示文字覆盖物
        Overlay mText = mBaiduMap.addOverlay(mTextOptions);*/
    }


    private void initView() {
        mMapView = (MapView) view.findViewById(R.id.bmapView);
        bt = (Button) view.findViewById(R.id.bt);
        bt.setOnClickListener(this);
        button = (Button) view.findViewById(R.id.button);
        button.setOnClickListener(this);
        buttons = (Button) view.findViewById(R.id.buttons);
        buttons.setOnClickListener(this);
        setControlsAndGestures();//控件与手势
    }

    /*控件与手势*/
    public void setControlsAndGestures(){
        boolean enable=true;
        //设置地图logo
        mMapView.setLogoPosition(LogoPosition.logoPostionleftBottom);
        //地图Logo不允许遮挡，可通过以下方法可以设置地图边界区域，来避免UI遮挡。
        //paddingLeft, paddingTop, paddingRight, paddingBottom
        //mBaiduMap.setPadding(paddingLeft, paddingTop, paddingRight, paddingBottom);

        // 指南针默认为开启状态，可以关闭显示
        //实例化UiSettings类对象
        boolean enabled=true;

        UiSettings mUiSettings = mMapView.getMap().getUiSettings();
        //通过设置enable为true或false 选择是否显示指南针
        mUiSettings.setCompassEnabled(enabled);
        //通过设置enable为true或false 选择是否启用地图平移
        mUiSettings.setScrollGesturesEnabled(enable);
        //通过设置enable为true或false 选择是否启用地图缩放手势
        mUiSettings.setZoomGesturesEnabled(enable);
        //通过设置enable为true或false 选择是否启用地图俯视功能
        mUiSettings.setOverlookingGesturesEnabled(enable);
        //通过设置enable为true或false 选择是否禁用所有手势
        // boolean enable1=false;
        // mUiSettings.setAllGesturesEnabled(enable1);


        //比例尺默认为开启状态，可以关闭显示
        //通过设置enable为true或false 选择是否显示比例尺
        mMapView.showScaleControl(enabled);

        //同时支持设置maxZoomLevel和minZoomLevel，方法为：
        // mBaiduMap.setMaxAndMinZoomLevel(float max,float min)

        //另外，可通过mMapView.getMapLevel获取当前地图级别下比例尺所表示的距离大小
        mMapView.getMapLevel();

        //缩放按钮是否显示：
        //通过设置enable为true或false 选择是否显示缩放按钮
        mMapView.showZoomControls(enable);
    }

    public void setcontent(){
        //设置地图的缩放级别
       /* MapStatus.Builder builder = new MapStatus.Builder();
        builder.zoom(18.0f);
        mMapView.setMapStatus(MapStatusUpdateFactory.newMapStatus(builder.build()));*/
        LatLng GEO_SHANGHAI = new LatLng(31.227, 121.481);
        //上海为地图中心
        MapStatusUpdate status2 = MapStatusUpdateFactory.newLatLng(GEO_SHANGHAI);
        mMapView.getMap().setMapStatus(status2);
    }

    /*离线地图*/
    public void setloadedMap(){
        int cityid=2;
        String cityName="上海市";
        //1.初始化离线地图
        MKOfflineMap mOffline = new MKOfflineMap();
        // 传入MKOfflineMapListener，离线地图状态发生改变时会触发该回调
        MKOfflineMapListener listener=new MKOfflineMapListener() {
            @Override
            public void onGetOfflineMapState(int i, int i1) {

            }
        };
        mOffline.init(listener);

        //2.根据城市编码下载离线地图
        // 开始下载离线地图
        // cityID 城市的数字标识
        mOffline.start(cityid);
        //可以通过MKOfflineMap类的searchCity方法根据城市名查找cityId。
        ArrayList<MKOLSearchRecord> records = mOffline.searchCity(cityName);
        if (records != null && records.size() == 1) {
            cityid = records.get(0).cityID;
        }

        //暂停下载
        mOffline.pause(cityid);
        //删除下载
        mOffline.remove(cityid);
        //更新下载
        mOffline.update(cityid);

    }

    @Override
    public void onResume() {
        mMapView.onResume();
        super.onResume();
        //mMapView.onResume();
    }

    @Override
    public void onPause() {
        mMapView.onPause();
        super.onPause();
       // mMapView.onPause();
    }

    @Override
    public void onDestroy() {
        mLocationClient.stop();
        mBaiduMap.setMyLocationEnabled(false);
        mMapView.onDestroy();
        mMapView = null;
        super.onDestroy();
       // mMapView.onDestroy();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.bt:
                //把定位点再次显现出来
                MapStatusUpdate mapStatusUpdate = MapStatusUpdateFactory.newLatLng(latLng);
                mBaiduMap.animateMapStatus(mapStatusUpdate);
                break;
            case R.id.button:
                //卫星地图
                mBaiduMap.setMapType(BaiduMap.MAP_TYPE_SATELLITE);
                break;
            case R.id.buttons:
                //普通地图
                mBaiduMap.setMapType(BaiduMap.MAP_TYPE_NORMAL);
                break;
        }
    }
}
