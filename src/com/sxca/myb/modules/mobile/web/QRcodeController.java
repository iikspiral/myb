package com.sxca.myb.modules.mobile.web;

import com.swetake.util.Qrcode;
import com.sxca.myb.common.config.Global;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;

@Controller
@RequestMapping("${frontPath}/qrcode")
public class QRcodeController {
	@RequestMapping(value = "/createQRcode", method = RequestMethod.GET)
	private String createQRcode(String type,HttpServletRequest request, Model model) throws UnsupportedEncodingException, IOException {
		Qrcode qrcode=new Qrcode();
		qrcode.setQrcodeErrorCorrect('M'); //纠错等级L M Q H
		qrcode.setQrcodeEncodeMode('B'); //N:数字，A:a-Z ,B:其他字符
		int version = 7;
		qrcode.setQrcodeVersion(version);
		String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+request.getContextPath();
		String qrData = null;
		String viewpath = null;
		if("app".equals(type)) {
			qrData = basePath + "/upload/apk/miyaobao.apk";
			viewpath = "modules/config/qrcode/appDown";
		}else {
			qrData = basePath + "/upload/apk/appDemo.apk";
			viewpath = "modules/config/qrcode/appDemoDown";
		}
		byte data[]=qrData.getBytes("gb2312");
		int width = 67+12*(version-1);
		int hight=67+12*(version-1);
		BufferedImage bufferedImage=new BufferedImage(width, hight, BufferedImage.TYPE_INT_RGB);
		Graphics2D graphics2d=bufferedImage.createGraphics();
		graphics2d.setBackground(Color.white);
		graphics2d.setColor(Color.black);
		graphics2d.clearRect(0, 0, width, hight);
		int pixoff=2; //偏移量
		if (data.length>0 && data.length<120) {
			boolean[][] s=qrcode.calQrcode(data);
			for (int i = 0; i < s.length; i++) {
				for (int j = 0; j < s.length; j++) {
					if (s[j][i]) {
						graphics2d.fillRect(j*3+pixoff, i*3+pixoff, 3, 3);
					}
				}
			}
		}
		graphics2d.dispose();
		bufferedImage.flush();
		String imgPath = "res/mobilefiles/imag.png";
		String path = request.getSession().getServletContext().getRealPath("/");
		ImageIO.write(bufferedImage, "png", new File(path+imgPath));
		model.addAttribute("qrcodePath",imgPath);
		return viewpath;
	}
}
