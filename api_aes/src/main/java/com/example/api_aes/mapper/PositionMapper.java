package com.example.api_aes.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;

import com.example.api_aes.model.Position;

public interface PositionMapper {

	@Insert("insert into positions (position_id,position_name,description) values (#{positionId},#{positionName},#{description})")
	//@Options(useGeneratedKeys=true, keyProperty="userId")
	public void createPosition(Position pos);
	
	@Select("select * from positions")
	@Results({
		@Result(property = "Id", column = "id"),
		@Result(property = "positionId", column = "position_id"),
	    @Result(property = "positionName", column = "position_name"),
	    @Result(property = "description", column = "description")})
	public List<Position> getAllPosition();
}
