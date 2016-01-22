package kkamnyang.persistence;

import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;

import kkamnyang.domain.RouteVO;

public interface RouteMapper {

	@Insert("insert into tbl_route(adminno,routename,secret,step,password,lat,lng) values(1,#{routename},#{secret},#{step},#{password},#{lat},#{lng})")
	public void insert(RouteVO vo) throws Exception;
	
	@Select("select * from tbl_route")
	public List<RouteVO> list() throws Exception;
}
