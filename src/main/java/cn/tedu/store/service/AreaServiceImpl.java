package cn.tedu.store.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.tedu.store.entity.Area;
import cn.tedu.store.mapper.AreaMapper;


@Service("areaService")
public class AreaServiceImpl implements IAreaService {
		
	@Autowired
	private AreaMapper areaMapper;
	
	public List<Area> getAreaListByCityCode(String cityCode) {
		return areaMapper.getAreaListByCityCode(cityCode);
	}

	public Area getAreaByCode(String areaCode) {
		System.out.println("根据区的code查询到的area"+areaMapper.getAreaByCode(areaCode)+"::"+areaCode);
		return areaMapper.getAreaByCode(areaCode);
	}

}
