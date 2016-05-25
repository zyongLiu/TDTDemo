package com.liu.tdtdemo.tdt;

public class TileUtil {
	
	/**
	 * 英尺到米的换算关系
	 */
	public static  double Inch_to_Meters = 0.02540005080010160020;
	
	/**
	 * 英尺到度的换算关系
	 */
	public static  double Inch_to_Degree = 2.2842825229870454443102146673684E-7;//2.2858428153903053748850792524563E-7;
	
	/**
	 * DPI，Inch_to_Pixels
	 */
	public static  double DPI = 96;//72 is OK
	
	/**
	 * 
	 * resolution到scale换算
	 * @param resolution
	 * @param unit 可选，默认为degree
	 * @return scale
	 * 
	 */
	public static double resolution_to_scale(double resolution,String unit)
	{
		double inch_to_unit;
		if(unit==Unit_Meter)
		{
			inch_to_unit = Inch_to_Meters;
		}else if(unit==Unit_Degree)
		{
			inch_to_unit = Inch_to_Degree;
		}else
		{
			inch_to_unit = Inch_to_Degree;
		}		
		double scale = resolution * DPI / inch_to_unit;
		
		return scale;
	}
	
	/**
	 * 
	 * scale到resolution换算
	 * @param scale
	 * @param unit 可选，默认为degree
	 * @return resolution
	 * 
	 */
	public static  double scale_to_resolution(double scale,String unit)
	{
		double inch_to_unit;
		if(unit==Unit_Meter)
		{
			inch_to_unit = Inch_to_Meters;
		}else if(unit==Unit_Degree)
		{
			inch_to_unit = Inch_to_Degree;
		}else
		{
			inch_to_unit = Inch_to_Degree;
		}
		double resolution = scale * inch_to_unit / DPI;
		return resolution;
	}
	
	/**
	 *
	 * 米 
	 */
	public static  String Unit_Meter = "meter";
	/**
	 * 
	 * 度
	 */
	public static  String Unit_Degree = "degree";
	/**
	 *
	 * 未知 
	 */
	public static  String Unit_Anonymous = "anonymous";
}
