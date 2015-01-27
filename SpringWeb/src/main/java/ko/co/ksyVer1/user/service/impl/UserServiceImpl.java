package ko.co.ksyVer1.user.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ko.co.ksyVer1.user.service.UserService;
import ko.co.ksyVer1.user.service.bean.UserInfo;
import ko.co.ksyVer1.user.service.bean.VUserInfo;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserMapper userMapper;
	//로그린
	@Override
	public UserInfo login(UserInfo userInfo) {
		return userMapper.login(userInfo);
	}
	//이름으로 사용자 검색
	@Override
	public List<UserInfo> searchByName(String name) {
		return userMapper.searchByName(name);
	}
	//사용자추가
	@Override
	public void addUser(VUserInfo userInfo) {
		userMapper.addUser(userInfo);
	}
	//아이지 중복 체크
	@Override
	public List<UserInfo> searchByIdAjax(String userid) {
		return userMapper.searchByIdAjax(userid);
	}

}
