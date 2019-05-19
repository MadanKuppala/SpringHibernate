package com.madan.hp.authController;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.DatatypeConverter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.madan.hp.model.IntranetUser;
import com.madan.hp.model.User;
import com.madan.hp.service.UserService;
import com.madan.hp.util.EncryptPassword;

/*import com.apdrp.mis.model.IntranetUser;
import com.apdrp.mis.model.User;
import com.apdrp.mis.service.UserService;
import com.apdrp.mis.util.EncryptPassword;
import com.apdrp.mis.web.SignInController;*/

public class AuthenticationController implements AuthenticationProvider {

	   @Autowired 
	   private transient UserService userService;

	   Logger logger = LoggerFactory.getLogger(getClass());
	   
	   @Override
	    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
	        
	        User loggedInUser = new User(authentication.getName(), authentication.getCredentials().toString());
	        String userid=loggedInUser.getUserId();
	        IntranetUser intranetUser=new IntranetUser();
	        intranetUser.setUserid(userid);
	        IntranetUser user=userService.findById(intranetUser.getUserid());
	        ServletRequestAttributes attr = (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes();
	        
	        String accessToken = attr.getRequest().getParameter("accessToken");
	        logger.info("accessToken:"+ accessToken);
	        String captchaInput = attr.getRequest().getParameter("captchaInput");
	        logger.info("captchaInput:"+ captchaInput);
	        String tokenValue = SignInController.tokensMap.get(accessToken);  
	        
	        boolean isValid=false;
	        boolean captchaValid=false;        
	   //   byte[] decodedBytes = Base64.getDecoder().decode(loggedInUser.getPassword());
	        byte[] decodedBytes = DatatypeConverter.parseBase64Binary(loggedInUser.getPassword());
	   //   String res = DatatypeConverter.parseBase64Binary(str);
	        String passWord =new String(decodedBytes) ;
	        
	            try {
	                if(tokenValue.equals(captchaInput)) {
	                    captchaValid = true;
	                }
	                if(SignInController.tokensMap.containsKey(accessToken))
	                    SignInController.tokensMap.remove(accessToken);
	                if(user!=null && user.getPwdType().equals(new Integer(0)) && passWord.equals(user.getPasswd())){
	                	isValid=true;
	                }if(user!=null && user.getPwdType().equals(new Integer(1)) && EncryptPassword.encrypt(passWord).equals(user.getEncrypedPwd())){
	                    isValid=true;
	                }
	                    
	                if(isValid && captchaValid) {
	                    List<GrantedAuthority> grantedAuths = new ArrayList<GrantedAuthority>();
	                        String roleString = user.getRoleid(); 
	                        grantedAuths.add(new SimpleGrantedAuthority(roleString));
	                        Authentication auth = new UsernamePasswordAuthenticationToken(User.toUser(user),passWord, grantedAuths);  
	                        return auth;
	                
	                }
	                else {
	                    if(!captchaValid){
	                        throw new BadCredentialsException("Invalid Captcha.");
	                    }
	                    if(!isValid){
	                        throw new BadCredentialsException("Username/Password Invalid.");
	                    }
	                    return null;
	                 }
	                }
	             catch(Exception exception) {
	            	 logger.error("Error : "+exception);
	                 exception.printStackTrace();
	                 return null;
	             }
	            
	        }
	        


	   @Override
	    public boolean supports(Class<?> authentication) {
	        return authentication.equals(UsernamePasswordAuthenticationToken.class);
	   }

}
