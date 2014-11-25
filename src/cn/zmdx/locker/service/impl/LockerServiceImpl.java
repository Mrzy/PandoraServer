package cn.zmdx.locker.service.impl;

import java.util.List;
import java.util.Map;

import cn.zmdx.locker.dao.interfaces.LockerDAO;
import cn.zmdx.locker.entity.Data_img_table;
import cn.zmdx.locker.entity.Data_table;
import cn.zmdx.locker.entity.Img;
import cn.zmdx.locker.service.interfaces.LockerService;

public class LockerServiceImpl implements LockerService {
	private LockerDAO lockerDAO;

	public LockerServiceImpl(LockerDAO lockerDAO) {
		this.lockerDAO = lockerDAO;
	}

	@Override
	public List<Data_table> queryDataTable(Map<String, String> filterMap) {
		
		return lockerDAO.queryDataTable(filterMap);
	}

	@Override
	public List<Data_img_table> queryDataImgTable(Map<String, String> filterMap) {
		
		return lockerDAO.queryDataImgTable(filterMap);

	}

	@Override
	public List<Img> queryDataById(Map<String, String> filterMap) {
		return lockerDAO.queryDataById(filterMap);
	}
	@Override
	public Data_img_table getDataImgTableById(String id) {
		return lockerDAO.getDataImgTableById(id);
	}



}
