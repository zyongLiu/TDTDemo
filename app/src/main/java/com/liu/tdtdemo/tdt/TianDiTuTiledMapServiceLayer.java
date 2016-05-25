package com.liu.tdtdemo.tdt;

import android.text.TextUtils;
import android.util.Log;

import com.esri.android.map.TiledServiceLayer;
import com.esri.core.geometry.Envelope;
import com.esri.core.geometry.Point;
import com.esri.core.geometry.SpatialReference;
import com.esri.core.io.UserCredentials;

import java.io.BufferedInputStream;
import java.io.ByteArrayOutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.concurrent.RejectedExecutionException;


public class TianDiTuTiledMapServiceLayer extends TiledServiceLayer {
//    private TianDiTuTiledMapServiceType _mapType;
    private TileInfo tiandituTileInfo;
    private String url;

    public TianDiTuTiledMapServiceLayer() {
        this(null, true,null);
    }

    public TianDiTuTiledMapServiceLayer(String url){
        this(null, true, url);
    }

    public TianDiTuTiledMapServiceLayer(UserCredentials usercredentials,String url){
        this(usercredentials, true, url);
    }
    public TianDiTuTiledMapServiceLayer(UserCredentials usercredentials, boolean flag,String url){
        super("");
        this.url=url;
        setCredentials(usercredentials);

        if(flag)
            try
            {
                getServiceExecutor().submit(new Runnable() {

                    public final void run()
                    {
                        a.initLayer();
                    }

                    final TianDiTuTiledMapServiceLayer a;


                    {
                        a = TianDiTuTiledMapServiceLayer.this;
                        //super();
                    }
                });
                return;
            }
            catch(RejectedExecutionException _ex) { }
    }
//    public TianDiTuTiledMapServiceType getMapType(){
//        return this._mapType;
//    }



    protected void initLayer(){
        this.buildTileInfo();
        this.setFullExtent(new Envelope(-180, -90, 180, 90));
        this.setDefaultSpatialReference(SpatialReference.create(4490));   //CGCS2000
//        this.setInitialExtent(new Envelope(120.290533999, 30.2649434080001, 121.335647512, 31.0323484300001));
        super.initLayer();
    }
    public void refresh()
    {
        try
        {
            getServiceExecutor().submit(new Runnable() {

                public final void run()
                {
                    if(a.isInitialized())
                        try
                        {
                            a.b();
                            a.clearTiles();
                            return;
                        }
                        catch(Exception exception)
                        {
                            Log.e("ArcGIS", "Re-initialization of the layer failed.", exception);
                        }
                }

                final TianDiTuTiledMapServiceLayer a;


                {
                    a = TianDiTuTiledMapServiceLayer.this;
                    //super();
                }
            });
            return;
        }
        catch(RejectedExecutionException _ex)
        {
            return;
        }
    }
    final void b()
            throws Exception
    {

    }

    @Override
    protected byte[] getTile(int level, int col, int row) throws Exception {
        /**
         *
         * */

        byte[] result = null;
        try {
            ByteArrayOutputStream bos = new ByteArrayOutputStream();

            URL sjwurl = new URL(this.getTianDiMapUrl(level, col, row));
            HttpURLConnection httpUrl = null;
            BufferedInputStream bis = null;
            byte[] buf = new byte[1024];

            httpUrl = (HttpURLConnection) sjwurl.openConnection();
            httpUrl.connect();
            bis = new BufferedInputStream(httpUrl.getInputStream());

            while (true) {
                int bytes_read = bis.read(buf);
                if (bytes_read > 0) {
                    bos.write(buf, 0, bytes_read);
                } else {
                    break;
                }
            }
            bis.close();
            httpUrl.disconnect();

            result = bos.toByteArray();
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return result;
    }


    @Override
    public TileInfo getTileInfo(){
        return this.tiandituTileInfo;
    }

    /**
     * 获取各等级的url
     * @param level
     * @param col
     * @param row
     * @return
     */
    private String getTianDiMapUrl(int level, int col, int row){
//        http://220.191.220.90/JXEMAP/service/wmts?SERVICE=WMTS&VERSION=1.0.0&REQUEST=GetTile&LAYER=JXEMAP&FORMAT=image/png&TILEMATRIXSET=TileMatrixSet0&
//        TILEMATRIX=18&TILEROW=85&STYLE=default&TILECOL=23;
//        http://srv.zjditu.cn/ZJEMAP_2D/wmts?SERVICE=WMTS&VERSION=1.0.0&REQUEST=GetTile&LAYER=ZJEMAP&FORMAT=image/png&TILEMATRIXSET=TileMatrixSet0&
//        TILEMATRIX=15&TILEROW=12&STYLE=default&TILECOL=47;
//        http://t0.tianditu.com/vec_c/wmts?SERVICE=WMTS&REQUEST=GetTile&VERSION=1.0.0&LAYER=vec&STYLE=default&TILEMATRIXSET=c&
//        TILEMATRIX=0&TILEROW=42&TILECOL=14&FORMAT=tiles;
        StringBuilder serviceUrl=new StringBuilder();
        if (!TextUtils.isEmpty(url)){
            String [] urls=url.split(";");
            if (urls.length==3){
                if (level<=20&&level>=18&&!TextUtils.isEmpty(urls[0])){
                    serviceUrl.append(urls[0]
                            .replaceAll("TILEMATRIX=[0-9]*", "TILEMATRIX=" + level)
                            .replaceAll("TILEROW=[0-9]*", "TILEROW=" + row)
                            .replaceAll("TILECOL=[0-9]*", "TILECOL=" + col));
                }else if (level <= 17 && level >= 15&&!TextUtils.isEmpty(urls[1])){
                    serviceUrl.append(urls[1]
                            .replaceAll("TILEMATRIX=[0-9]*", "TILEMATRIX=" + level)
                            .replaceAll("TILEROW=[0-9]*", "TILEROW=" + row)
                            .replaceAll("TILECOL=[0-9]*", "TILECOL=" + col));
                }else if (level >= 0 && level <= 14&&!TextUtils.isEmpty(urls[2])){
                    serviceUrl.append(urls[2]
                            .replaceAll("TILEMATRIX=[0-9]*", "TILEMATRIX=" + level)
                            .replaceAll("TILEROW=[0-9]*", "TILEROW=" + row)
                            .replaceAll("TILECOL=[0-9]*", "TILECOL=" + col));
                }
            }
        }
        return serviceUrl.toString();
    }

    private void buildTileInfo()
    {
        Point originalPoint=new Point(-180,90);

        double[] res={
                1.40625,
                0.703125,
                0.3515625,
                0.17578125,
                0.087890625,
                0.0439453125,
                0.02197265625,
                0.010986328125,
                0.0054931640625,
                0.00274658203125,
                0.001373291015625,
                0.0006866455078125,
                0.00034332275390625,
                0.000171661376953125,
                8.58306884765629E-05,
                4.29153442382814E-05,
                2.14576721191407E-05,
                1.07288360595703E-05,
                5.36441802978515E-06,
                2.68220901489258E-06,
                1.34110450744629E-06
        };
        double[] scale={
                400000000,
                295497598.5708346,
                147748799.285417,
                73874399.6427087,
                36937199.8213544,
                18468599.9106772,
                9234299.95533859,
                4617149.97766929,
                2308574.98883465,
                1154287.49441732,
                577143.747208662,
                288571.873604331,
                144285.936802165,
                72142.9684010827,
                36071.4842005414,
                18035.7421002707,
                9017.87105013534,
                4508.93552506767,
                2254.467762533835,
                1127.2338812669175,
                563.616940
        };
        int levels=21;
        int dpi=96;
        int tileWidth=256;
        int tileHeight=256;
        this.tiandituTileInfo=new com.esri.android.map.TiledServiceLayer.TileInfo(originalPoint, scale, res, levels, dpi, tileWidth,tileHeight);
        this.setTileInfo(this.tiandituTileInfo);
    }

}
