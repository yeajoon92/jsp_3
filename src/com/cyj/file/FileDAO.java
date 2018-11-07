package com.cyj.file;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.cyj.util.DBConnector;

public class FileDAO {
	
	//selectList
	public List<FileDTO> selectList(FileDTO fDTO) throws Exception {
		List<FileDTO> ar = new ArrayList<>();
		Connection con = DBConnector.getConnect();
		String sql = "select * from upload where num=? and kind=?";
		PreparedStatement st = con.prepareStatement(sql);
		
		st.setInt(1, fDTO.getNum());
		st.setString(2, fDTO.getKind());
		
		ResultSet rs = st.executeQuery();
		while(rs.next()) {
			fDTO = new FileDTO(); //fDTO 재활용^^
			fDTO.setfNum(rs.getInt("fNum"));
			fDTO.setfName(rs.getString("fName"));
			fDTO.setoName(rs.getString("oName"));
			fDTO.setNum(rs.getInt("num"));
			fDTO.setKind(rs.getString("kind"));
			ar.add(fDTO);
		}
		
		DBConnector.disConnect(rs, st, con);
		return ar;
	}
	
	//insert
	public int insert(FileDTO fDTO) throws Exception {
		Connection con = DBConnector.getConnect();
		String sql = "insert into upload values (file_seq.nextval,?,?,?,?)";
		PreparedStatement st = con.prepareStatement(sql);
		
		st.setString(1, fDTO.getfName());
		st.setString(2, fDTO.getoName());
		st.setInt(3, fDTO.getNum());
		st.setString(4, fDTO.getKind());
		
		int result = st.executeUpdate();
		
		DBConnector.disConnect(st, con);
		return result;
	}
	
	//delete
	public int delete(int fNum) throws Exception {
		Connection con = DBConnector.getConnect();
		String sql = "delete upload where fNum=?";
		PreparedStatement st = con.prepareStatement(sql);
		
		st.setInt(1, fNum);
		
		int result = st.executeUpdate();
		
		DBConnector.disConnect(st, con);
		return result;
	}
	
	//deleteAll
	public int deleteAll(int num) throws Exception {
		Connection con = DBConnector.getConnect();
		String sql = "delete upload where num=?";
		PreparedStatement st = con.prepareStatement(sql);
		
		st.setInt(1, num);
		
		int result = st.executeUpdate();
		
		DBConnector.disConnect(st, con);
		return result;
	}
	
	
	

}
