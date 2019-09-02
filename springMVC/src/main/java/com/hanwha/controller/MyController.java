package com.hanwha.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.hanwha.model.DeptDAO;
import com.hanwha.model.DeptDAO_Mybatis;
import com.hanwha.model.DeptDTO;
import com.hanwha.model.EmpDAO_Mybatis;
import com.hanwha.model.EmpService;
import com.hanwha.model.EmpVO;

//<bean id = "mycontroller" class = ""/>
@Controller //요청 받아서 처리하는 애./ repository는 DB에 가서 작업하는 DAO / DB가는건아닌데 업무로직 수행하는건 Service. 이 모든애들은 Component상속받아서 만들지만 목적에 맞게 이름 설정
public class MyController {
	
	@Autowired
	//DeptDAO dao;
	DeptDAO_Mybatis dao;
	
	@Autowired
	EmpService service; 
	
	@RequestMapping("/404")
	public String error404(Model model) {
		model.addAttribute("company", "한화시스템/ICT");
		model.addAttribute("manager", "이아영");
		return "error404";
	}
	
	@ExceptionHandler(Exception.class)
	public String error500(Exception ex, Model model) {
		model.addAttribute("company", "한화시스템/ICT");
		model.addAttribute("manager", "이아영");
		model.addAttribute("number", "010-3411-5653");
		model.addAttribute("errormessage", ex.getMessage());
		return "error500";
	}
	
	@RequestMapping("/emp/emplist")
	public ModelAndView selectAll() {
		ModelAndView mv = new ModelAndView();
		mv.addObject("emplist", service.selectAll());
		mv.setViewName("emp/emplist");
		return mv; 
	}
	
	@RequestMapping(value = "/emp/empdetail", method = RequestMethod.GET)
	public ModelAndView empGetDetail(int empid) {
		ModelAndView mv = new ModelAndView(); 
		mv.addObject("emp", service.selectById(empid));
		mv.addObject("deptlist", dao.selectAll());
		mv.addObject("joblist", service.selectAllJob());
		mv.addObject("managerlist", service.selectAllManager());
		mv.setViewName("emp/empdetail");
		return mv; 
	}
	
	@RequestMapping(value = "/emp/empdetail", method = RequestMethod.POST)
	public String empPostDetail(EmpVO emp) {
		ModelAndView mv = new ModelAndView(); 
		mv.addObject("emp", service.updateEmp(emp));
		service.updateEmp(emp);
		return "redirect:emplist";
	}
	
	@RequestMapping(value = "/emp/empinsert", method = RequestMethod.GET)
	public ModelAndView empGetInsert() {
		ModelAndView mv = new ModelAndView(); 
		mv.addObject("deptlist", dao.selectAll());
		mv.addObject("joblist", service.selectAllJob());
		mv.addObject("managerlist", service.selectAllManager()); 
		mv.setViewName("emp/empinsert");
		return mv;
	}
	
	@RequestMapping(value = "/emp/empinsert", method = RequestMethod.POST)
	public String empPostInsert(EmpVO emp) {
		service.insertEmp(emp);
		return "redirect:emplist";
	}
	
	@RequestMapping("/emp/empdelete")
	public String empDelete(int empid) {
		service.deleteEmp(empid);
		return "redirect:emplist"; 
	}
	
	@RequestMapping("/dept/deptlist2") //요청이름
	public String deptlist3(Model model) {
		model.addAttribute("deptlist", dao.selectAll());
		return "dept/deptlist"; //페이지이름 
	}
	
	@RequestMapping(value = "/dept/deptdetail", method = RequestMethod.GET)
	public String deptGetdetail(int deptid, Model model) {
		model.addAttribute("dept", dao.selectByID(deptid));
		return "dept/deptdetail";
	}
	
	@RequestMapping(value = "/dept/deptdetail", method = RequestMethod.POST)
	public String deptDetailPost(DeptDTO dept) {
		dao.updateDept(dept);
		return "redirect:deptlist2";
	}
	
	@RequestMapping(value = "/dept/deptinsert", method = RequestMethod.GET)
	public String deptGetInsert() {
		return "dept/deptinsert";
	}
	
	@RequestMapping(value = "/dept/deptinsert", method = RequestMethod.POST)
	public String deptInsertPost(DeptDTO dept, HttpServletRequest request) {
		MultipartFile uploadfile = dept.getUploadfile();         
		if (uploadfile == null) {
			return "redirect:deptinsert";
		}
		
		String path = request.getSession().getServletContext().getRealPath("/resources/upload");         
		String fileName = uploadfile.getOriginalFilename();         
		String fpath = path +"\\" + fileName;                
		dept.setFileName(fileName); //파일경로저장 
		System.out.println(fpath);
		try {                               
			File file = new File(fpath);            
			uploadfile.transferTo(file);          
		} catch (IOException e) {     
				e.printStackTrace();     
		}
		
		dao.insertDept(dept);
		return "redirect:deptlist2"; 
	}
	
	@RequestMapping("/dept/deptdownload")
	public void download(String folder, String file, HttpServletRequest request, HttpServletResponse response) throws IOException {
	//	deptdownload?folder=resources&file=${dept.fileName}
		  response.setHeader("Content-Disposition", "attachment;filename="+file);
		  String fullPath = request.getSession().getServletContext().getRealPath(folder + "/" + file);    
		  FileInputStream fi = new FileInputStream(fullPath);    
		  ServletOutputStream sout = response.getOutputStream();    
		  byte[] buf = new byte[1024];    
		  int size = 0;    
		  while((size = fi.read(buf, 0, 1024))!=-1){      
			  sout.write(buf, 0, size);    
		  }    
		  fi.close();    
		  sout.close();
	}
	
	@RequestMapping("/dept/deptdelete")
	public String deptDelete(int deptid) {
		dao.deleteDept(deptid);
		return "redirect:deptlist2";
	}
}
