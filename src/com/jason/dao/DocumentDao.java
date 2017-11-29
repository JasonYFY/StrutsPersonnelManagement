package com.jason.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;

import com.jason.domain.Document;
import com.jason.domain.PageModel;
import com.jason.domain.User;
import com.jason.service.domain.File;


public class DocumentDao extends BaseDao{
	
	public int insertDocument(Document doc) {
		// 获取数据库的连接对象
		Connection conn = null;
		PreparedStatement pstmt = null;
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement("insert into document_inf(TITLE,filename,REMARK,CREATE_DATE,file_data,USER_ID) values(?,?,?,?,?,?)");

			pstmt.setString(1, doc.getTitle());
			pstmt.setString(2, doc.getFileName());
			pstmt.setString(3, doc.getRemark());
			pstmt.setTimestamp(4, new Timestamp(doc.getCreateDate().getTime()));
			pstmt.setBlob(5, doc.getFileData());
			pstmt.setInt(6, doc.getUserId());
			
			return pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				closeConnection(null,pstmt,conn);
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

		return 0;
	}

	public int deleteDocument(Document doc) {
		// TODO Auto-generated method stub
		Connection conn = null;
		PreparedStatement pstmt = null;
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement("delete from document_inf where ID=?");
			
			pstmt.setInt(1, doc.getId());
			
			return pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				closeConnection(null,pstmt,conn);
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return 0;
	}
	
	public ArrayList<Document> queryDocument(Document doc){
		ArrayList<Document> list = new ArrayList<>();
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement("select * from document_inf where title like ? ");

			pstmt.setString(1, "%"+doc.getTitle()+"%");
			
			rs = pstmt.executeQuery();
			while(rs.next()) {
				Document nowDoc = new Document();
				nowDoc.setCreateDate(new Date(rs.getTimestamp("create_date").getTime()));
				nowDoc.setFileData(rs.getBlob("file_data").getBinaryStream());
				nowDoc.setFileName(rs.getString("filename"));
				nowDoc.setId(rs.getInt("ID"));
				nowDoc.setRemark(rs.getString("remark"));
				nowDoc.setTitle(rs.getString("TITLE"));
				nowDoc.setUserId(rs.getInt("USER_ID"));
				list.add(nowDoc);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				closeConnection(rs,pstmt,conn);
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return list;
	}
	
	//分页查询记录数
	public Integer queryDocumentCount(Document doc){
		Integer count = null;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement("select count(*) from document_inf where title like ?");
			
			if(doc.getTitle()==null)
				pstmt.setString(1, "%%");
			else
				pstmt.setString(1, "%"+doc.getTitle()+"%");
			
			rs = pstmt.executeQuery();
			while(rs.next()) {
				count = rs.getInt(1);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				closeConnection(rs,pstmt,conn);
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return count;
	}
	
	//分页查询
	public ArrayList<File> queryDocumentByPage(Document doc,PageModel page){
		ArrayList<File> list = new ArrayList<>();
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement("select a.ID,a.TITLE,a.filename,a.REMARK,a.CREATE_DATE,a.USER_ID,b.* from document_inf a,user_inf b where title like ? and a.USER_ID=b.ID limit "+page.startRowNum()+","+page.getPageSize());
			if(doc.getTitle()==null)
				pstmt.setString(1, "%%");
			else
				pstmt.setString(1, "%"+doc.getTitle()+"%");
			
			rs = pstmt.executeQuery();
			while(rs.next()) {
				Document nowDoc = new Document();
				nowDoc.setCreateDate(new Date(rs.getTimestamp("create_date").getTime()));
//				nowDoc.setFileData(rs.getBlob("file_data").getBinaryStream());
				nowDoc.setFileName(rs.getString("filename"));
				nowDoc.setId(rs.getInt("ID"));
				nowDoc.setRemark(rs.getString("remark"));
				nowDoc.setTitle(rs.getString("TITLE"));
				nowDoc.setUserId(rs.getInt("USER_ID"));
				User nowUser = new User();
				nowUser.setId(rs.getInt("ID"));
				nowUser.setPassword(rs.getString("PASSWORD"));
				nowUser.setLoginname(rs.getString("loginname"));
				nowUser.setStatus(rs.getInt("STATUS"));
				nowUser.setUsername(rs.getString("username"));
				nowUser.setCreatedate(new Date(rs.getTimestamp("createdate").getTime()));
				File file = new File();
				file.setDoc(nowDoc);
				file.setUser(nowUser);
				list.add(file);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				closeConnection(rs,pstmt,conn);
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return list;
	}
	
	//下载
	public Document downloadDocument(Document doc){
		Document nowDoc = new Document();
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement("select * from document_inf where ID=? ");

			pstmt.setInt(1, doc.getId());
			
			rs = pstmt.executeQuery();
			while(rs.next()) {
				nowDoc.setCreateDate(new Date(rs.getTimestamp("create_date").getTime()));
				nowDoc.setFileData(rs.getBlob("file_data").getBinaryStream());
				nowDoc.setFileName(rs.getString("filename"));
				nowDoc.setId(rs.getInt("ID"));
				nowDoc.setRemark(rs.getString("remark"));
				nowDoc.setTitle(rs.getString("TITLE"));
				nowDoc.setUserId(rs.getInt("USER_ID"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				closeConnection(rs,pstmt,conn);
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return nowDoc;
	}
	
	public ArrayList<Document> queryAllDocument(){
		ArrayList<Document> list = new ArrayList<>();
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement("select * from document_inf ");
			
			rs = pstmt.executeQuery();
			while(rs.next()) {
				Document nowDoc = new Document();
				nowDoc.setCreateDate(new Date(rs.getTimestamp("create_date").getTime()));
				nowDoc.setFileData(rs.getBlob("file_data").getBinaryStream());
				nowDoc.setFileName(rs.getString("filename"));
				nowDoc.setId(rs.getInt("ID"));
				nowDoc.setRemark(rs.getString("remark"));
				nowDoc.setTitle(rs.getString("TITLE"));
				nowDoc.setUserId(rs.getInt("USER_ID"));
				list.add(nowDoc);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				closeConnection(rs,pstmt,conn);
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return list;
	}
	
	
}
