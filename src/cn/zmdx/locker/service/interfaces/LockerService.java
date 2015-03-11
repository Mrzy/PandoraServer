package cn.zmdx.locker.service.interfaces;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import cn.zmdx.locker.entity.Data_img_table;
import cn.zmdx.locker.entity.Data_table;
import cn.zmdx.locker.entity.Img;
import cn.zmdx.locker.entity.WallPaper;

public interface LockerService {
	
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

   /**
    * 根据数据id获取相应标签
    * @param filterMap
    * @return
    */
   public List queryDataTagById(Map<String, String> filterMap);
   
   /**
    * 增加浏览量
    * @param id
    * @return
    */
   public void addViews(String id);

   /**
    * 新版本的图片数据查询（1.5.2版后）
    * @author louxiaojian
    * @date： 日期：2015-3-9 时间：上午11:59:08
    * @param filterMap
    * @return
    */
   public List<Data_img_table> queryDataImgTableNew(Map<String, String> filterMap);

   /**
	 * 新版本的壁纸数据查询（1.5.2版后）
	 * @author louxiaojian
	 * @date： 日期：2015-3-9 时间：下午4:36:09
	 * @throws IOException
	 */
   public List<WallPaper> queryWallPaperNew(Map<String, String> filterMap);
}
