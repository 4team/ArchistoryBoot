package kkamnyang.persistence;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import kkamnyang.domain.RouteVO;

public interface RouteMapper {

	@Insert("insert into tbl_route(adminno,routename,secret,step,password,lat,lng) values(1,#{routename},#{secret},#{step},#{password},#{lat},#{lng})")
	public int create(RouteVO vo) throws Exception;
	
	@Select("select * from tbl_route")
	public List<RouteVO> list() throws Exception;
	
	@Select("select * from tbl_route where adminno = #{adminno}")
	public List<RouteVO> list(Integer adminno) throws Exception;
	
	@Select("select * from tbl_route where routeno = #{routeno}")
	public RouteVO read(Integer adminno) throws Exception;

	@Update("update tbl_route set routename = #{routename}, lat = #{lat}, lng = #{lng} where routeno = #{routeno}")
	public void update(RouteVO vo)throws Exception;
	
	@Delete("delete from tbl_route where routeno = #{routeno}")
	public void delete(Integer routeno) throws Exception;
}
