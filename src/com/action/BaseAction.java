package com.action;

import java.io.InputStream;
import java.lang.reflect.ParameterizedType;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.struts2.interceptor.ApplicationAware;
import org.apache.struts2.interceptor.RequestAware;
import org.apache.struts2.interceptor.SessionAware;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.utils.EmailUtil;
import com.utils.MessageUtil;

import com.model.FileImage;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import com.service.IAccountService;
import com.service.ICategoryService;
import com.service.IForderService;
import com.service.IPayService;
import com.service.IProductService;
import com.service.ISorderService;
import com.service.IUserService;
import com.utils.FileUpload;

/**
 * @author Qiyao
 * 
 * @Date 2017.03.28
 * 
 * @Des base action class
 * 
 */
@Controller("baseAction")
@Scope("prototype")
public class BaseAction<T> extends ActionSupport implements RequestAware,
		SessionAware, ApplicationAware, ModelDriven<T> {

	private static final long serialVersionUID = 9205024623913632611L;

	// 用来装有将要被打包成json格式返回给前台的数据，下面要实现get方法
	protected List<T> jsonList = null;

	public List<T> getJsonList() {
		return jsonList;
	}

	@Resource
	protected IProductService productService;

	// 获取要删除的ids，要有get和set方法
	// 流是用来想前台返回数据的，这个数据是让struts获取的，然后通过流的形式传到前台，所以实现get方法即可
	protected String ids;
	protected InputStream inputStream;

	public String getIds() {
		return ids;
	}

	public void setIds(String ids) {
		this.ids = ids;
	}

	public InputStream getInputStream() {
		return inputStream;
	}

	public void setInputStream(InputStream inputStream) {
		this.inputStream = inputStream;
	}

	// page和rows和分页有关，pageMap存放查询的数据，然后打包成json格式用的
	// page和rows实现get和set方法，pageMap只需要实现get方法即可，因为pageMap不是接收前台参数的，是让struts获取的
	protected Integer page;
	protected Integer rows;
	protected Map<String, Object> pageMap = null;// 让不同的Action自己去实现

	public Integer getPage() {
		return page;
	}

	public void setPage(Integer page) {
		this.page = page;
	}

	public Integer getRows() {
		return rows;
	}

	public void setRows(Integer rows) {
		this.rows = rows;
	}

	public Map<String, Object> getPageMap() {
		return pageMap;
	}

	// service对象
	@Resource
	protected ICategoryService categoryService;

	@Resource
	protected IAccountService accountService;

	public void setCategoryService(ICategoryService categoryService) {
		this.categoryService = categoryService;
	}

	public void setAccountService(IAccountService accountService) {
		this.accountService = accountService;
	}

	protected Map<String, Object> request;
	protected Map<String, Object> session;
	protected Map<String, Object> application;

	@Override
	public void setApplication(Map<String, Object> application) {
		this.application = application;
	}

	@Override
	public void setSession(Map<String, Object> session) {
		this.session = session;
	}

	@Override
	public void setRequest(Map<String, Object> request) {
		this.request = request;
	}

	protected T model;

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public T getModel() { // 这里通过解析传进来的T来new一个对应的instance
		ParameterizedType type = (ParameterizedType) this.getClass()
				.getGenericSuperclass();
		Class clazz = (Class) type.getActualTypeArguments()[0];
		try {
			model = (T) clazz.newInstance();
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
		return model;
	}
	
	//封装了图片信息的类  
    protected FileImage fileImage;  
      
    //上传文件工具类  
    @Resource  
    protected FileUpload fileUpload;  
  
    public FileImage getFileImage() {  
        return fileImage;  
    }  
    public void setFileImage(FileImage fileImage) {  
        this.fileImage = fileImage;  
    }  
    
    public IForderService getForderService() {
		return forderService;
	}

	public void setForderService(IForderService forderService) {
		this.forderService = forderService;
	}

	public ISorderService getSorderService() {
		return sorderService;
	}

	public void setSorderService(ISorderService sorderService) {
		this.sorderService = sorderService;
	}

	public IUserService getUserService() {
		return userService;
	}

	public void setUserService(IUserService userService) {
		this.userService = userService;
	}

	@Resource  
    protected IForderService forderService;  
    
    @Resource  
    protected ISorderService sorderService; 
    
    @Resource  
    protected IUserService userService; 
    
	@Resource
	protected IPayService payService;
	
	@Resource 
	protected EmailUtil emailUtil;
	
	@Resource 
	protected MessageUtil messageUtil;
}
