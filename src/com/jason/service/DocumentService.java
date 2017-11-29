package com.jason.service;

import java.util.ArrayList;

import com.jason.dao.DocumentDao;
import com.jason.domain.Document;
import com.jason.domain.PageModel;
import com.jason.domain.User;
import com.jason.service.domain.File;

public class DocumentService {
	private DocumentDao docDao;
	public DocumentService() {
		docDao = new DocumentDao();
	}
	/*public ArrayList<File> getAllDocument() {
		ArrayList<Document> list = docDao.queryAllDocument();
		ArrayList<File> fileList = new ArrayList<>();
		if(list.size()>0) {
			for(Document d: list) {
				User user = new User();
				user.setId(d.getUserId());
				ArrayList<User> userList = new UserService().QueryUser(user);
				File f = new File();
				f.setCreateDate(d.getCreateDate());
				f.setFileData(d.getFileData());
				f.setFileName(d.getFileName());
				f.setId(d.getId());
				f.setRemark(d.getRemark());
				f.setTitle(d.getTitle());
				f.setUser(userList.get(0));
				fileList.add(f);
			}
		}
		return fileList;
	}*/
	
	//查询页数
	public Integer queryDocumentCount(Document doc) {
		return docDao.queryDocumentCount(doc);
	}
	//分页查询
	public ArrayList<File> queryDocumentByPage(Document doc,PageModel page){
		page.setTotalRecordSum(queryDocumentCount(doc));
		ArrayList<File> list = docDao.queryDocumentByPage(doc, page);
		if(list!=null&&list.size()>0) {
			return list;
		}
		return null;
	}
	
	/*public ArrayList<File> queryDocument(Document doc){
		ArrayList<Document> list = docDao.queryDocument(doc);
		if(list!=null&&list.size()>0) {
			ArrayList<File> fileList = new ArrayList<>();
			for(Document d: list) {
				User user = new User();
				user.setId(d.getUserId());
				ArrayList<User> userList = new UserService().QueryUser(user);
				File f = new File();
				f.setCreateDate(d.getCreateDate());
				f.setFileData(d.getFileData());
				f.setFileName(d.getFileName());
				f.setId(d.getId());
				f.setRemark(d.getRemark());
				f.setTitle(d.getTitle());
				f.setUser(userList.get(0));
				fileList.add(f);
			}
			return fileList;
		}
		return null;
	}*/
	
	public Document downloadDocument(Document doc){
		return docDao.downloadDocument(doc);
	}
	
	public boolean deleteDocument(Document doc) {
		int pos = docDao.deleteDocument(doc);
		if(pos>0) {
			return true;
		}
		return false;
	}
	public boolean uploadDocument(Document doc) {
		int pos = docDao.insertDocument(doc);
		if(pos>0) {
			return true;
		}
		return false;
	}
}
