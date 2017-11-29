package com.jason.action;

import java.io.File;

import java.io.FileInputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.Map;

import com.jason.domain.Document;
import com.jason.domain.PageModel;
import com.jason.domain.User;
import com.jason.service.DocumentService;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import com.opensymphony.xwork2.util.ValueStack;

public class DocumentAction extends ActionSupport implements ModelDriven<Document>{
	private Document doc = new Document();
	private PageModel pageModel = new PageModel();
	private DocumentService docService = new DocumentService();
	private File uploadData;
	private String uploadDataContentType; //获取上传文件的类型（自动注入）
	private String uploadDataFileName;
	
	public Document getDoc() {
		return doc;
	}

	public void setDoc(Document doc) {
		this.doc = doc;
	}

	public PageModel getPageModel() {
		return pageModel;
	}

	public void setPageModel(PageModel pageModel) {
		this.pageModel = pageModel;
	}
	
	public File getUploadData() {
		return uploadData;
	}

	public void setUploadData(File uploadData) {
		this.uploadData = uploadData;
	}

	public String getUploadDataContentType() {
		return uploadDataContentType;
	}

	public void setUploadDataContentType(String uploadDataContentType) {
		this.uploadDataContentType = uploadDataContentType;
	}

	public String getUploadDataFileName() {
		return uploadDataFileName;
	}

	public void setUploadDataFileName(String uploadDataFileName) {
		this.uploadDataFileName = uploadDataFileName;
	}

	@Override
	public Document getModel() {
		// TODO Auto-generated method stub
		return this.doc;
	}
	
	//文档查询页面
	public String docQueryForm() throws Exception {
		ActionContext context = ActionContext.getContext();
		ValueStack vs = context.getValueStack();
		ArrayList<com.jason.service.domain.File> list = docService.queryDocumentByPage(getModel(), getPageModel());
		vs.set("fileList", list);
		return SUCCESS;
	}
	//上传
	public String uploadFile() throws Exception {
		ValueStack vs = ActionContext.getContext().getValueStack();
		Map<String, Object> session = ActionContext.getContext().getSession();
		System.out.println("上传文件类型="+getUploadDataContentType());
		System.out.println("上传文件名="+getUploadDataFileName());
		FileInputStream in = new FileInputStream(getUploadData());
		System.out.println("文件大小="+in.available());
		
		getModel().setFileData(in);
		getModel().setFileName(getUploadDataFileName());
		getModel().setCreateDate(new Date());
		User user = (User) session.get("activeUser");
		boolean pos = false;
		if(user!=null) {
			getModel().setUserId(user.getId());
			pos = docService.uploadDocument(getModel());
			System.out.println("user:"+user);
		}
		if(pos) {
			vs.set("errorF", "no");
		}else {
			vs.set("errorF", "yes");
			return INPUT;
		}
		
		return SUCCESS;
	}
	public String docQuery() throws Exception {
		ActionContext context = ActionContext.getContext();
		ValueStack vs = context.getValueStack();
		ArrayList<com.jason.service.domain.File> list = docService.queryDocumentByPage(getModel(), getPageModel());
		vs.set("fileList", list);
		return SUCCESS;
	}
	public InputStream getDocDownload() throws Exception {
		Document document = docService.downloadDocument(getModel());
		InputStream fileData = document.getFileData();
		String fileName = new String(document.getFileName().getBytes(), "ISO8859-1");
		setUploadDataFileName(fileName);
		return fileData;
	}
	public String docDelete() throws Exception {
		ValueStack vs = ActionContext.getContext().getValueStack();
		boolean pos = docService.deleteDocument(getModel());
		if(pos) {
			vs.set("errorD", "no");
		}else {
			vs.set("errorD", "yes");
		}
		return SUCCESS;
	}
	
	

}
