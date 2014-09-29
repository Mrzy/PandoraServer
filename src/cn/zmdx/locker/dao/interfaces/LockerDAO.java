package cn.zmdx.locker.dao.interfaces;

import java.util.List;
import java.util.Map;

import cn.zmdx.locker.entity.Data_img_table;
import cn.zmdx.locker.entity.Data_table;


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
	
}
