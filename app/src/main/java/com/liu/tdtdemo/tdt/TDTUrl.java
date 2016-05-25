package com.liu.tdtdemo.tdt;


/**
 * 抓图类
 */
public class TDTUrl {
    private TianDiTuTiledMapServiceType _tiandituMapServiceType;
    private int _level;
    private int _col;
    private int _row;
    public TDTUrl(int level, int col, int row, TianDiTuTiledMapServiceType tiandituMapServiceType){
        this._level=level;
        this._col=col;
        this._row=row;
        this._tiandituMapServiceType=tiandituMapServiceType;
    }
    public String generatUrl(){
        /**
         * 天地图矢量、影像
         * */
        StringBuilder url=new StringBuilder();
        if (_level<=20&&_level>=18){
          switch(this._tiandituMapServiceType){
            case VEC_C:
                url.append("http://220.191.220.90/JXEMAP/service/wmts?SERVICE=WMTS&VERSION=1.0.0&" +
                        "REQUEST=GetTile&LAYER=JXEMAP&FORMAT=image/png&" +
                        "TILEMATRIXSET=TileMatrixSet0&TILEMATRIX=" + this._level + "&TILEROW=" + this._row + "&STYLE=default&TILECOL=" + this._col);
                break;
            case CVA_C:
                url.append("http://220.191.220.90/JXEMAPANNO/service/wmts?SERVICE=WMTS&VERSION=1.0.0&" +
                        "REQUEST=GetTile&LAYER=JXEMAPANNO&FORMAT=image/png&" +
                        "TILEMATRIXSET=TileMatrixSet0&TILEMATRIX=" + this._level + "&TILEROW=" + this._row + "&STYLE=default&TILECOL=" + this._col);
            case IMG_C:
                url.append("http://220.191.220.90/JXIMG/service?SERVICE=WMTS&VERSION=1.0.0&REQUEST=GetTile" +
                        "&LAYER=JXIMG&FORMAT=image/png&TILEMATRIXSET=TileMatrixSet0" +
                        "&TILEMATRIX=" + this._level + "&TILEROW="+this._row+"&TILECOL="+this._col+"&STYLE=default");
                break;
            case CIA_C:
                url.append("http://220.191.220.90/JXIMGANNO/service?SERVICE=WMTS&VERSION=1.0.0&REQUEST=GetTile&LAYER=JXIMGANNO&" +
                        "FORMAT=image/png&TILEMATRIXSET=TileMatrixSet0&TILEMATRIX="+this._level+"&TILEROW="+this._row+"&TILECOL="+this._col+"&STYLE=default");
                break;
            default:
                return null;
        }
        }else if (_level <= 17 && _level >= 15) {
            switch(this._tiandituMapServiceType){
                case VEC_C:
                    url.append("http://srv.zjditu.cn/ZJEMAP_2D/wmts?SERVICE=WMTS&VERSION=1.0.0&" +
                            "REQUEST=GetTile&LAYER=ZJEMAP&FORMAT=image/png&" +
                            "TILEMATRIXSET=TileMatrixSet0&TILEMATRIX=" + this._level + "&TILEROW=" + this._row + "&STYLE=default&TILECOL=" + this._col);
                    break;
                case CVA_C:
                    url.append("http://srv.zjditu.cn/ZJEMAPANNO_2D/wmts?SERVICE=WMTS&VERSION=1.0.0&" +
                            "REQUEST=GetTile&LAYER=ZJEMAPANNO&FORMAT=image/png" +
                            "&TILEMATRIXSET=TileMatrixSet0&TILEMATRIX="+this._level+"&STYLE=default&TILEROW="+this._row+"&TILECOL="+this._col);
                    break;
                case IMG_C:
                    url.append("http://srv.zjditu.cn/ZJDOM_2D/wmts?SERVICE=WMTS&VERSION=1.0.0&REQUEST=GetTile&" +
                            "LAYER=ZJDOM2W1&FORMAT=image/jpeg&TILEMATRIXSET=Matrix_0&TILEMATRIX="+this._level+"&TILEROW="+this._row+"&TILECOL="+this._col);
                    break;
                case CIA_C:
                    url.append("http://srv.zjditu.cn/ZJDOMANNO_2D/wmts?SERVICE=WMTS&VERSION=1.0.0&REQUEST=GetTile&" +
                            "LAYER=ZJIMGANNO&FORMAT=image/png&TILEMATRIXSET=TileMatrixSet0&" +
                            "TILEMATRIX="+this._level+"&STYLE=default&TILEROW="+this._row+"&TILECOL="+this._col);
                    break;
                default:
                    return null;
            }
        }else if (_level >= 0 && _level <= 14) {
            switch(this._tiandituMapServiceType){
                case VEC_C:
                    url.append("http://t0.tianditu.com/vec_c/wmts?SERVICE=WMTS&REQUEST=GetTile" +
                            "&VERSION=1.0.0&LAYER=vec&STYLE=default&TILEMATRIXSET=c&TILEMATRIX=" +
                            this._level + "&TILEROW=" + this._row + "&TILECOL=" + this._col + "&FORMAT=tiles");
                    break;
                case CVA_C:
                    url.append("http://t0.tianditu.com/cva_c/wmts?SERVICE=WMTS&REQUEST=GetTile" +
                            "&VERSION=1.0.0&LAYER=cva&STYLE=default&TILEMATRIXSET=c&TILEMATRIX="+
                            this._level+"&TILEROW="+this._row+"&TILECOL="+this._col+"&FORMAT=tiles");
                    break;
                case IMG_C:
                    url.append("http://t0.tianditu.com/img_c/wmts?SERVICE=WMTS&REQUEST=GetTile&VERSION=1.0.0&" +
                            "LAYER=img&STYLE=default&TILEMATRIXSET=c&TILEMATRIX="+this._level+"&TILEROW="+this._row+"&TILECOL="+this._col+"&FORMAT=tiles");
                    break;
                case CIA_C:
                    url.append("http://t0.tianditu.com/cia_c/wmts?SERVICE=WMTS&REQUEST=GetTile&VERSION=1.0.0&" +
                            "LAYER=cia&STYLE=default&TILEMATRIXSET=c&TILEMATRIX="+this._level+"&TILEROW="+this._row+"&TILECOL="+this._col+"&FORMAT=tiles");
                    break;
                default:
                    return null;
            }
        }
        return url.toString();
    }
}
