package ko.co.ksyVer1.user.service;

import java.util.List;

import ko.co.ksyVer1.user.service.bean.UserInfo;
import ko.co.ksyVer1.user.service.bean.VUserInfo;

public interface UserService {
	//로그린
	UserInfo login(UserInfo userInfo);
	//이름으로 사용자 검색
	List<UserInfo> searchByName(String name);
	//사용자추가
	void addUser(VUserInfo userInfo);
	//아이지 중복 체크
	List<UserInfo> searchByIdAjax(String userid);
	
}
