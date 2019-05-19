package com.madan.hp.authController;

import java.awt.Color;
import java.awt.Font;
import java.awt.GradientPaint;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Base64;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

/*import com.apdrp.mis.bean.DashboardBean;
import com.apdrp.mis.bean.PackagesBean;
import com.apdrp.mis.service.DashBoardService;
import com.apdrp.mis.service.PackageService;
import com.apdrp.mis.util.ContextUtil;
import com.apdrp.mis.util.RequestContext;
import com.google.gson.Gson;*/

public class SignInController {
	
//public static final String URL = "/login";
	
	@Autowired
	HttpServletRequest request;
	
	/*@Autowired
	DashBoardService dashBoardService;
	
	@Autowired
	PackageService packageService;*/
	
	public static Map<String, String> tokensMap = new HashMap<String, String>();

	Logger logger = LoggerFactory.getLogger(getClass());
	
	@RequestMapping(value = {"/", "login"}, method = RequestMethod.GET)
	public ModelAndView login(@RequestParam(value = "error", required = false) String error,
			@RequestParam(value = "logout", required = false) String logout) {

		ModelAndView model = new ModelAndView();
		if (error != null) {
			model.addObject("error", "Invalid username and password!");
		}

		if (logout != null) {
			model.addObject("msg", "You've been logged out successfully.");
		}
		model.setViewName("login");

		return model;

	}
	
	@RequestMapping(value = {"/welcome**" }, method = RequestMethod.GET)
	public ModelAndView homePage() {

		ModelAndView model = new ModelAndView();
		model.addObject("title", "Spring Security Login Form - Database Authentication");
		model.addObject("message", "This is default page!");
		model.setViewName("hello");
		return model;
	}

	@RequestMapping(value = "/admin", method = RequestMethod.GET)
	public ModelAndView adminPage() {

		ModelAndView model = new ModelAndView();
		model.addObject("title", "Spring Security Login Form - Database Authentication");
		model.addObject("message", "This page is for ROLE_ADMIN only!");
		model.setViewName("admin");
		return model;

	}
	@RequestMapping(value = "/user", method = RequestMethod.GET)
	public ModelAndView userPage() {

		ModelAndView model = new ModelAndView();
		model.addObject("title", "Spring Security Login Form - Database Authentication");
		model.addObject("message", "This page is for ROLE_ADMIN and ROLE_USER!");
		model.setViewName("user");
		return model;

	}

	// for 403 access denied page
	@RequestMapping(value = "/403", method = RequestMethod.GET)
	public ModelAndView accesssDenied() {

		ModelAndView model = new ModelAndView();

		// check if user is login
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		if (!(auth instanceof AnonymousAuthenticationToken)) {
			UserDetails userDetail = (UserDetails) auth.getPrincipal();
			model.addObject("username", userDetail.getUsername());
		}

		model.setViewName("403");
		return model;

	}

	@RequestMapping(value = "/getCaptcha", method = RequestMethod.GET)
	@ResponseBody
	public String getCaptcha(HttpServletRequest request,HttpServletResponse response) throws IOException {
		String captchaGen = this.generateCaptcha();
		String token = this.generateToken();
		tokensMap.put(token, captchaGen);

        logger.info("accessToken:"+ token);
        logger.info("captchaInput:"+ captchaGen);
        
        System.out.println("accessToken:"+ token);
        System.out.println("captchaInput:"+ captchaGen);
		
		response.setHeader("accessToken", token);
		response.setStatus(200);
		
		byte[] arr = generateImageByteArray(request, response, captchaGen);
		return Base64.getEncoder().encodeToString(arr); //java 8
	//	return DatatypeConverter.printBase64Binary(arr); //java 7
	}

	public String generateCaptcha() {
		Random random = new Random();
		int length = 5;
		StringBuffer captchaStringBuffer = new StringBuffer();
		for (int i = 0; i < length; i++) {
			int captchaNumber = Math.abs(random.nextInt()) % 60;
			int charNumber = 0;
			if (captchaNumber < 26) {
				charNumber = 65 + captchaNumber;
			} else if (captchaNumber < 52) {
				charNumber = 97 + (captchaNumber - 26);
			} else {
				charNumber = 48 + (captchaNumber - 52);
			}
			captchaStringBuffer.append((char) charNumber);
		}

		return captchaStringBuffer.toString();
	}

	public String generateToken() {
		Random random = new Random();
		int length = 30;
		StringBuffer captchaTokenBuffer = new StringBuffer();
		for (int i = 0; i < length; i++) {
			int captchaToken = Math.abs(random.nextInt()) % 67;
			int charNumber = 0;
			if (captchaToken < 26) {
				charNumber = 63 + captchaToken;
			} else if (captchaToken < 52) {
				charNumber = 79 + (captchaToken - 26);
			} else {
				charNumber = 48 + (captchaToken - 52);
			}
			captchaTokenBuffer.append((char) charNumber);
		}

		return captchaTokenBuffer.toString();
	}

	
	public void generateImage(HttpServletRequest request,HttpServletResponse response,String captchaStr) throws IOException {
		
		response.setHeader("Cache-Control", "no-cache");
        response.setDateHeader("Expires", 0);
        response.setHeader("Pragma", "no-cache");
        response.setDateHeader("Max-Age", 0);
        response.setContentType("image/jpeg");
		
		int width = 100;
		int height = 40;

		Color bg = new Color(22, 134, 182);
//		Color fg = new Color(225, 225, 225);

		RenderingHints rh = new RenderingHints(
		           RenderingHints.KEY_ANTIALIASING,
		           RenderingHints.VALUE_ANTIALIAS_ON);

	    rh.put(RenderingHints.KEY_RENDERING, 
	           RenderingHints.VALUE_RENDER_QUALITY);
		
	    GradientPaint gp = new GradientPaint(0, 0, 
	    	    Color.red, 0, height/2, Color.black, true);

		Font font = new Font("Times New Roman", Font.PLAIN, 20);
		BufferedImage cpimg = new BufferedImage(width, height, BufferedImage.OPAQUE);
		Graphics2D g = cpimg.createGraphics();

		g.setRenderingHints(rh);
		g.setPaint(gp);
		g.setFont(font);
		g.setColor(bg);
		g.fillRect(0, 0, width, height);
//		g.setColor(fg);
		g.drawString(captchaStr, 10, 25);

		HttpSession session = request.getSession(true);
		session.setAttribute("CAPTCHA", captchaStr);
		OutputStream outputStream = response.getOutputStream();
		ImageIO.write(cpimg, "image/jpeg", outputStream);
		outputStream.close();
	}

	public byte[] generateImageByteArray(HttpServletRequest request,HttpServletResponse response,String captchaStr) throws IOException {
		
		response.setHeader("Cache-Control", "no-cache");
        response.setDateHeader("Expires", 0);
        response.setHeader("Pragma", "no-cache");
        response.setDateHeader("Max-Age", 0);
        response.setContentType("image/jpeg");
		
		int width = 85;
		int height = 40;

		Color bg = new Color(22, 134, 182);
		Color fg = new Color(225, 225, 225);

		Font font = new Font("Arial", Font.BOLD, 16);
		BufferedImage cpimg = new BufferedImage(width, height, BufferedImage.OPAQUE);
		Graphics2D g = cpimg.createGraphics();

		g.setFont(font);
		g.setColor(bg);
		g.fillRect(0, 0, width, height);
		g.setColor(fg);
		g.drawString(captchaStr, 17, 25);

		HttpSession session = request.getSession(true);
		ByteArrayOutputStream bos=new ByteArrayOutputStream();
		session.setAttribute("CAPTCHA", captchaStr);
		ImageIO.write(cpimg, "jpeg", bos);
		bos.flush();
		
		return bos.toByteArray();
	}

}
