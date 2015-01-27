package ko.co.ksyVer1.aop;

import java.lang.reflect.Method;

import ko.co.ksyVer1.user.service.bean.UserInfo;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;

@Aspect
public class AuthorizeAspect {
	@Pointcut("execution(@org.springframework.web.bind.annotation.RequestMapping * *(..))")
//	@Pointcut("execution(@Authorize)")		//pointcut 적용범위
	private void handlerMethod() {}
	
	@Before("handlerMethod()")
	public void interceptMethod(JoinPoint jp) throws Exception {
		MethodSignature ms = (MethodSignature)jp.getSignature();
		//java.lang.reflect.Method;
		Method me = ms.getMethod();
		
		//메서드에서 해당 어노테이션 취득
		Authorize au = me.getAnnotation(Authorize.class);
		if(au!=null){ //어노테이션이 붙어있다는 뜻.
			//권한 체크가 필요
			RequestAttributes ra = RequestContextHolder.getRequestAttributes();
			UserInfo loginUser = (UserInfo)ra.getAttribute("userInfo", RequestAttributes.SCOPE_SESSION);
			
			if (loginUser == null) {
				//로그인 하지않고 접근한 경우
				System.out.println("로그인 하십시오");
				throw new InvalidLoginException();
			} else {

				RequestMapping rm = me.getAnnotation(RequestMapping.class);
				if (rm.value().length > 0) {
					System.out.println("-"+rm.value()[0]+"-");
					if (rm.value()[0].equals("/update.html") || rm.value()[0].equals("/delete.html")) {
						if (!loginUser.getRole().equals("admin")) {
							//관리자가 아닌경우 리턴
							throw new InvalidLoginException();
						}
					}
					else {}
				}
				else {
					//로그인 후 접근한 경우
					System.out.println("로그인 감사합니다.");
				}

			}
		}
	}
}
