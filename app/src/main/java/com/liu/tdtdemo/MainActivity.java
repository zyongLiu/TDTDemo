package com.liu.tdtdemo;

import android.app.Activity;
import android.os.Bundle;
import android.widget.CheckBox;
import android.widget.CompoundButton;

import com.esri.android.map.MapView;
import com.liu.tdtdemo.tdt.TianDiTuTiledMapServiceLayer;

public class MainActivity extends Activity {
    private MapView mv;
    private String url1;
    private String url2;
    private CheckBox cb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mv= (MapView) findViewById(R.id.mv);
        cb= (CheckBox) findViewById(R.id.cb);

        url1="http://220.191.220.90/JXEMAP/service/wmts?SERVICE=WMTS&VERSION=1.0.0&REQUEST=GetTile&LAYER=JXEMAP&FORMAT=image/png&TILEMATRIXSET=TileMatrixSet0&TILEMATRIX=18&TILEROW=85&STYLE=default&TILECOL=23;"+
        "http://srv.zjditu.cn/ZJEMAP_2D/wmts?SERVICE=WMTS&VERSION=1.0.0&REQUEST=GetTile&LAYER=ZJEMAP&FORMAT=image/png&TILEMATRIXSET=TileMatrixSet0&TILEMATRIX=15&TILEROW=12&STYLE=default&TILECOL=47;"+
        "http://t0.tianditu.com/vec_c/wmts?SERVICE=WMTS&REQUEST=GetTile&VERSION=1.0.0&LAYER=vec&STYLE=default&TILEMATRIXSET=c&TILEMATRIX=0&TILEROW=42&TILECOL=14&FORMAT=tiles#"+
        "http://220.191.220.90/JXEMAPANNO/service/wmts?SERVICE=WMTS&VERSION=1.0.0&REQUEST=GetTile&LAYER=JXEMAPANNO&FORMAT=image/png&TILEMATRIXSET=TileMatrixSet0&TILEMATRIX=18&TILEROW=12&STYLE=default&TILECOL=11;"+
        "http://srv.zjditu.cn/ZJEMAPANNO_2D/wmts?SERVICE=WMTS&VERSION=1.0.0&REQUEST=GetTile&LAYER=ZJEMAPANNO&FORMAT=image/png&TILEMATRIXSET=TileMatrixSet0&TILEMATRIX=11&STYLE=default&TILEROW=23&TILECOL=12;"+
        "http://t0.tianditu.com/cva_c/wmts?SERVICE=WMTS&REQUEST=GetTile&VERSION=1.0.0&LAYER=cva&STYLE=default&TILEMATRIXSET=c&TILEMATRIX=0&TILEROW=12&TILECOL=32&FORMAT=tiles;";

        url2="http://220.191.220.90/JXIMG/service?SERVICE=WMTS&VERSION=1.0.0&REQUEST=GetTile&LAYER=JXIMG&FORMAT=image/png&TILEMATRIXSET=TileMatrixSet0&TILEMATRIX=18&TILEROW=23&TILECOL=15&STYLE=default;" +
                "http://srv.zjditu.cn/ZJDOM_2D/wmts?SERVICE=WMTS&VERSION=1.0.0&REQUEST=GetTile&LAYER=ZJDOM2W1&FORMAT=image/jpeg&TILEMATRIXSET=Matrix_0&TILEMATRIX=10&TILEROW=23&TILECOL=23;" +
                "http://t0.tianditu.com/img_c/wmts?SERVICE=WMTS&REQUEST=GetTile&VERSION=1.0.0&LAYER=img&STYLE=default&TILEMATRIXSET=c&TILEMATRIX=0&TILEROW=12&TILECOL=36&FORMAT=tiles#" +
                "http://220.191.220.90/JXIMGANNO/service?SERVICE=WMTS&VERSION=1.0.0&REQUEST=GetTile&LAYER=JXIMGANNO&FORMAT=image/png&TILEMATRIXSET=TileMatrixSet0&TILEMATRIX=18&TILEROW=23&TILECOL=32&STYLE=default;" +
                "http://srv.zjditu.cn/ZJDOMANNO_2D/wmts?SERVICE=WMTS&VERSION=1.0.0&REQUEST=GetTile&LAYER=ZJIMGANNO&FORMAT=image/png&TILEMATRIXSET=TileMatrixSet0&TILEMATRIX=13&STYLE=default&TILEROW=23&TILECOL=36;" +
                "http://t0.tianditu.com/cia_c/wmts?SERVICE=WMTS&REQUEST=GetTile&VERSION=1.0.0&LAYER=cia&STYLE=default&TILEMATRIXSET=c&TILEMATRIX=0&TILEROW=36&TILECOL=63&FORMAT=tiles;";
        addLayer(url1);
        cb.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                mv.removeAll();
                if (isChecked){
                    addLayer(url1);
                    buttonView.setText("矢量地图");
                }else {
                    addLayer(url2);
                    buttonView.setText("影像地图");
                }
            }
        });
    }

    private void addLayer(String url){
        String[] baselayers=url.split("#");
        for (int i=0;i<baselayers.length;i++){
            TianDiTuTiledMapServiceLayer layer=new TianDiTuTiledMapServiceLayer(baselayers[i]);
            mv.addLayer(layer, i);
        }
    }

}
