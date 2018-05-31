package com.sxca.myb.modules.config.problem.web;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.sxca.myb.common.persistence.Page;
import com.sxca.myb.common.utils.StringUtils;
import com.sxca.myb.common.web.BaseController;
import com.sxca.myb.modules.config.problem.entity.Problem;
import com.sxca.myb.modules.config.problem.service.ProblemService;


/**  
* <p>Title: CertAppLogController</p>  
* <p>Description: </p>  
* <p>Company: 吉大正元</p>   
* @author Yophy  
* @date 上午8:31:03  
*/ 
@Controller
@RequestMapping(value = "${adminPath}/problem")
public class ProblemController extends BaseController {

	@Autowired
	private ProblemService problemService;

	@ModelAttribute
	public Problem get(@RequestParam(required = false) String id) {
		if (StringUtils.isNotBlank(id)) {
			return problemService.get(id);
		} else {
			return new Problem();
		}
	}


	@RequestMapping(value = "list")
	public String problemList(Problem entity,String clickType,
			HttpServletRequest request, HttpServletResponse response,
			Model model) {
		Page<Problem> page = new Page<Problem>(request,
				response);
		entity.setPage(page);
		page.setList(problemService.findList(entity));
		model.addAttribute("page", page);
		return "modules/config/problem/list";

	}

	@RequestMapping(value = "getProblem")
	public String getProblem(Problem entity,
			HttpServletRequest request, HttpServletResponse response,
			Model model){
		
		Problem problem = problemService.get(entity.getId());
		
		if(StringUtils.isNotBlank(problem.getPic())){
			if(problem.getPic().contains(",")){
				List strList = new ArrayList<>();
				String[] strSz = (problem.getPic()).split(",");
				for(String str : strSz){
					strList.add(str);
				}
				problem.setPicList(strList);
			}else{
				List strList = new ArrayList<>();
				strList.add(problem.getPic());
				problem.setPicList(strList);
			}
		}
		model.addAttribute("problem", problem);
		return "modules/config/problem/form";
	}


}
