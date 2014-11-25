package cn.zmdx.locker.dao.interfaces;

import java.util.List;
import java.util.Map;
import cn.zmdx.locker.entity.Data_img_table;
import cn.zmdx.locker.entity.Data_table;
import cn.zmdx.locker.entity.Img;
import cn.zmdx.locker.entity.WallPaper;


public interface LockerDAO extends ParentDAO {
	
	/**
	 * 查询非图片数据数据
	 * 
	 */
	public List<Data_table> queryDataTable(Map<String, String> filterMap);
	
	/**
	 * 查询图片数据数据
	 * 
	 */
	   public List<Data_img_table> queryDataImgTable(Map<String, String> filterMap);
	/**
	 * 根据id查询相关的图文信息
	 * @param filterMap
	 * @return
	 */
	public List<Img> queryDataById(Map<String, String> filterMap);
	/**
    * 根据id获取Data_img_table
    * @param id
    * @return
    */
   public Data_img_table getDataImgTableById(String id);
	   
	   /**
	    * 查询壁纸数据
	    * 
	    */
	   public List<WallPaper> queryWallPaper(Map<String, String> filterMap);
	
}
