package com.sxca.myb.modules.sms.web;


import com.sxca.myb.common.persistence.Page;
import com.sxca.myb.common.utils.DownLoadUtil;
import com.sxca.myb.common.utils.SMSUtil;
import com.sxca.myb.common.utils.StringUtils;
import com.sxca.myb.common.web.BaseController;
import com.sxca.myb.modules.sms.entity.SMSLogs;
import com.sxca.myb.modules.sms.entity.SMSPlatform;
import com.sxca.myb.modules.sms.entity.SMSTemplate;
import com.sxca.myb.modules.sms.service.SMSLogsService;
import com.sxca.myb.modules.sms.service.SMSPlatformService;
import com.sxca.myb.modules.sms.service.SMSTemplateService;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;


/**
 * 通知管理短信平台控制层
 * @author glq
 * @time   20170421
 */

@Controller
@RequestMapping("${adminPath}/smsPlatform")
public class SMSPlatformController extends BaseController {
	@Autowired
	private SMSPlatformService smsPlatformService;
	@Autowired
	private SMSTemplateService smsTemplateService;
	@Autowired
	private SMSLogsService smsLogsService;

	@ModelAttribute("smsPlatform")
	public SMSPlatform get(@RequestParam(required=false) String id) {
		if (StringUtils.isNotBlank(id)){
			return smsPlatformService.get(id);
		}else{
			return new SMSPlatform();
		}
	}

	/**
	 * 初始化列表信息
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/list")
	public String skip(SMSPlatform smsPlatform, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<SMSPlatform> page = smsPlatformService.findPage(new Page<SMSPlatform>(request, response), smsPlatform);
		model.addAttribute("page", page);// 页面数据，包括每页显示多少条和总条数
		return "modules/sms/list";
	}
	/**
	 * 跳转到添加或修改页面
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "form")
	public String form(SMSPlatform smsPlatform, Model model) {
		if (smsPlatform.getId() != null && !"".equals(smsPlatform.getId())){
			model.addAttribute("smsPlatform", smsPlatformService.get(smsPlatform.getId()));
		}
		return "modules/sms/form";
	}
	/**
	 * 保存或者修改
	 * @param smsPlatform
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "save")
	public String save(SMSPlatform smsPlatform, HttpServletRequest request, RedirectAttributes redirectAttributes) {
		smsPlatformService.save(smsPlatform);
		addMessage(redirectAttributes, "保存成功");
		return "redirect:" + adminPath + "/smsPlatform/list";
	}
	/**
	 * 删除
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/del", method = RequestMethod.GET)
	public String del(SMSPlatform smsPlatform, HttpServletRequest request) throws Exception{
		smsPlatformService.delete(smsPlatform);
		return "redirect:" + adminPath + "/smsPlatform/list";
	}

	/**
	 * 初始化测试短信信息
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/sendTest", method = RequestMethod.GET)
	public String sendTest(Model model) throws Exception{
		List<SMSPlatform> listSMS= smsPlatformService.findAll();
		model.addAttribute("listSMS", listSMS);
		return "modules/sms/sendTest";
	}
	/**
	 * 获取验证码
	 * @param phone
	 * @param code
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	@RequestMapping(value = "/getYzm", method = RequestMethod.POST)
	public void getYzm(String phone,String code,HttpServletRequest request,HttpServletResponse response) throws IOException {
		response.setContentType("text/html;charset=utf-8");
		StringBuffer yzm = new StringBuffer();
		for (int i = 0; i < 6; i++) {
			yzm.append(new Random().nextInt(9) + 1);
		}

		request.getSession().removeAttribute("yzm");
		request.getSession().putValue("yzm", yzm.toString());
		request.getSession().setMaxInactiveInterval(300);

		String str=yzm.toString();

		boolean flag=false;
		System.out.println(str+"========="+phone+"========="+code);
		SMSPlatform smsPlatform = new SMSPlatform();
		smsPlatform.setSmsCode(code);
		List<SMSPlatform> list = smsPlatformService.findList(smsPlatform);
		if(code.equals("UCPaaS")) {
			flag=SMSUtil.UCPAASSMS(phone,str, list.get(0),"15336");
		}
		PrintWriter out=response.getWriter();
		out.write(String.valueOf(flag));
		out.flush();
		out.close();
	}
	/**
	 * 验证验证码
	 * @param mobileYz
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	@RequestMapping(value = "/regedit", method = RequestMethod.POST)
	public void regedit(String mobileYz,HttpServletRequest request,HttpServletResponse response)  throws Exception {
		response.setContentType("text/html;charset=utf-8");
		String yzm = (String) request.getSession().getAttribute("yzm");
		String str = "";
		if (yzm != null && yzm.equals(mobileYz)) {
			request.getSession().removeAttribute("yzm");
			str = "true";
		} else {
			str = "验证码输入错误！";
		}
		PrintWriter out = response.getWriter();
		out.write(str);
		out.flush();
		out.close();
	}

	/**
	 * 初始化发送短信信息
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/send", method = RequestMethod.GET)
	public String send(Model model,HttpServletRequest req) throws Exception{
		//获取短信模板
		List<SMSTemplate> temList= smsTemplateService.findAll();
		model.addAttribute("temList", temList);
		//短信平台
		List listSMS= smsPlatformService.findAll();
		model.addAttribute("listSMS", listSMS);
		return "modules/sms/send";
	}
	/**
	 * 下载模板文件
	 * @param request
	 * @param response
	 */
	@RequestMapping(value = "/downFile", method = RequestMethod.GET)
	public void downFile(HttpServletRequest request,HttpServletResponse response) {
		try {
			DateFormat dataFormat = (DateFormat) new SimpleDateFormat(
					"yyyy年MM月");
			String filePath = request.getRealPath("/") + "template"
					+ File.separator + "电话号码.xls";
			System.out.println(filePath);
			String fileName = dataFormat.format(new Date()).toString()
					+ "电话号码.xls";
			String fileType = "xls";
			DownLoadUtil.downLoadFile(filePath, response, fileName, fileType);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	/**
	 * 处理上传文件
	 * @param mobile
	 * @param request
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/importInfo", method = RequestMethod.POST)
	public String importInfo(@RequestParam(value = "file", required = false) MultipartFile file,String mobile,HttpServletRequest request,Model model) throws Exception {
		ArrayList<String> columnList = new ArrayList<String>();
		//上传文件
		String path = request.getSession().getServletContext().getRealPath("upload");
        String fileName = file.getOriginalFilename();
        File targetFile = new File(path, fileName);
        if(!targetFile.exists()){
            targetFile.mkdirs();
        }
        //保存
        try {
            file.transferTo(targetFile);
        } catch (Exception e) {
            e.printStackTrace();
        }
	    //处理文件
		try {
			FileInputStream in = new FileInputStream(targetFile);
			HSSFWorkbook wb = new HSSFWorkbook(in);
			Sheet sheet = wb.getSheetAt(0);
			int lastRowNum = sheet.getLastRowNum();
			Row row = null;
			Cell cell_a = null;
			for (int i = 1; i <= lastRowNum; i++) {
				row = sheet.getRow(i); // 取得第i行
				cell_a = row.getCell(1); // 取得i行的第二列
				if (cell_a != null) {
					cell_a.setCellType(Cell.CELL_TYPE_STRING);
					String cellValue = cell_a.getStringCellValue();
					columnList.add(cellValue);
				}
			}
			mobile = listToString(columnList);

		} catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println(mobile);
		//获取短信模板
		List<SMSTemplate> temList= smsTemplateService.findAll();
		model.addAttribute("temList", temList);
		//短信平台
		List listSMS= smsPlatformService.findAll();
		model.addAttribute("listSMS", listSMS);
		//传递手机号回去
		model.addAttribute("mobile", mobile);
		return "modules/sms/send";
    }
	/**
	 * 把一个List对象转换为String字符串
	 * @param stringList
	 * @return
	 */
	public static String listToString(List<String> stringList){
        if (stringList==null) {
            return null;
        }
        StringBuilder result=new StringBuilder();
        boolean flag=false;
        for (String string : stringList) {
            if (flag) {
                result.append(",");
            }else {
                flag=true;
            }
            result.append(string);
        }
        return result.toString();
    }
	/**
	 * 发送信息
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/sendAll", method = RequestMethod.POST)
	public String sendAll(SMSLogs log, String smsCode, HttpServletRequest req, HttpServletResponse response, RedirectAttributes redirectAttributes) throws Exception{
		if(smsCode!=null && smsCode!="") {
			boolean flag = false;
			String templateId = log.getTemplateName();//表单提交时,提交的值为短信模板ID
			//查询该templateId对应的模板信息
			SMSTemplate smsTemplate = new SMSTemplate();
			smsTemplate.setTemplateId(templateId);
			List<SMSTemplate> temlist = smsTemplateService.findList(smsTemplate);
			//查询smsCode对应的短信平台信息
			SMSPlatform smsPlatform = new SMSPlatform();
			smsPlatform.setSmsCode(smsCode);
			List<SMSPlatform> smslist = smsPlatformService.findList(smsPlatform);
			if ((temlist != null && temlist.size() > 0) && (smslist != null && smslist.size() > 0)) {
				String[] mobiles = log.getPhoneNum().split(",");
				for (int i = 0; i < mobiles.length; i++) {
					if (smsCode.equals("UCPaaS")) {
						flag = SMSUtil.UCPAASSMS(mobiles[i], log.getContent(), smslist.get(0), templateId);
					}
					//日志记录
					log.setPhoneNum(mobiles[i]);
					log.setTemplateName(temlist.get(0).getTemplateName());
					if (flag == true) {
						log.setSendState("已发送");
						log.setSendTime(new Date());
					}
					if (flag == false) {
						log.setSendState("未发送");
					}
					smsLogsService.save(log);
				}
			}
			addMessage(redirectAttributes,"发送完成!");
			return "redirect:" + adminPath + "/smsPlatform/send";
		}
		return null;
	}
}
