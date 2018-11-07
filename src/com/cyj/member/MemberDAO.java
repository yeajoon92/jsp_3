package com.cyj.member;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.List;

import com.cyj.memo.MemoDTO;
import com.cyj.util.DBConnector;

public class MemberDAO {
	
	//checkId
	public boolean checkId(String id) throws Exception {
		boolean check = true;
		Connection con = DBConnector.getConnect();
		String sql = "select id from member where id=?";
		PreparedStatement st = con.prepareStatement(sql);
		
		st.setString(1, id);
		
		ResultSet rs = st.executeQuery();
		check = rs.next();
		
		DBConnector.disConnect(rs, st, con);
		return check;
	}
	
	//join
	public int join(MemberDTO mDTO) throws Exception {
		Connection con = DBConnector.getConnect();
		String sql = "insert into member values(?,?,?,?,?,?,?,?)";
		PreparedStatement st = con.prepareStatement(sql);
		
		st.setString(1, mDTO.getId());
		st.setString(2, mDTO.getPw());
		st.setString(3, mDTO.getName());
		st.setString(4, mDTO.getEmail());
		st.setString(5, mDTO.getKind());
		st.setString(6, mDTO.getClassMate());
		st.setString(7, mDTO.getfName());
		st.setString(8, mDTO.getoName());
		
		int result = st.executeUpdate();
		DBConnector.disConnect(st, con);
		return result;
	}
	
	//login
	public MemberDTO login(MemberDTO mDTO) throws Exception {
		System.out.println(mDTO.getId());
		System.out.println(mDTO.getPw());
		System.out.println(mDTO.getKind());
		Connection con = DBConnector.getConnect();
		String sql = "select * from member where id=? and pw=? and kind=?";
		PreparedStatement st = con.prepareStatement(sql);
		
		st.setString(1, mDTO.getId());
		st.setString(2, mDTO.getPw());
		st.setString(3, mDTO.getKind());
		
		ResultSet rs = st.executeQuery();
		if(rs.next()) {
			mDTO.setName(rs.getString("name"));
			mDTO.setEmail(rs.getString("email"));
			mDTO.setClassMate(rs.getString("classMate"));
			mDTO.setfName(rs.getString("fName"));
			mDTO.setoName(rs.getString("oName"));
		}else {
			mDTO = null;
		}
		
		DBConnector.disConnect(rs, st, con);
		return mDTO;
	}
	
	//selectOne
	public MemberDTO selectOne() throws Exception {
		Connection con = DBConnector.getConnect();
		String sql = "";
		PreparedStatement st = con.prepareStatement(sql);
		
		MemberDTO mDTO = null;
		
		ResultSet rs = st.executeQuery();
		DBConnector.disConnect(st, con);
		return mDTO;
	}
	
	//update
	public int update(MemberDTO mDTO) throws Exception {
		Connection con = DBConnector.getConnect();
		String sql = "update member set pw=?, name=?, email=?, fName=?, oName=? where id=?";
		PreparedStatement st = con.prepareStatement(sql);
		st.setString(1, mDTO.getPw());
		st.setString(2, mDTO.getName());
		st.setString(3, mDTO.getEmail());
		st.setString(4, mDTO.getfName());
		st.setString(5, mDTO.getoName());
		st.setString(6, mDTO.getId());
		
		int result = st.executeUpdate();
		DBConnector.disConnect(st, con);
		return result;
	}
	
	//delete
	public int delete(MemberDTO mDTO) throws Exception {
		Connection con = DBConnector.getConnect();
		String sql = "delete member where id=?";
		PreparedStatement st = con.prepareStatement(sql);
		
		st.setString(1, mDTO.getId());
		
		int result = st.executeUpdate();
		DBConnector.disConnect(st, con);
		return result;
	}
	
	//logout
	public int logout() throws Exception {
		Connection con = DBConnector.getConnect();
		String sql = "";
		PreparedStatement st = con.prepareStatement(sql);
		
		int result = st.executeUpdate();
		DBConnector.disConnect(st, con);
		return result;
	}
	
	
}
