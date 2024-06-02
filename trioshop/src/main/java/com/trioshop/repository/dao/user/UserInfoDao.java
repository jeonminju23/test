// UserInfoDao.java
package com.trioshop.repository.dao.user;

import com.trioshop.model.dto.user.*;
import com.trioshop.repository.mybatis.UserMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@RequiredArgsConstructor //이 어노테이션은 클래스에 포함된 모든 final 필드에 대한 생성자를 자동으로 생성해 줍니다.
public class UserInfoDao {

    private final UserMapper userMapper;

    // 로그인 기능을 제공하는 메소드입니다. 사용자 아이디와 비밀번호를 받아와서 해당 정보로 로그인을 시도하고, 로그인에 성공하면 세션에 사용자 정보를 저장하여 반환합니다.
    public UserInfoBySession loginUser(UserIdPasswd userIdPasswd) {
        return userMapper.loginUser(userIdPasswd);
    }

    public void saveUsers(UserJoin userJoin){userMapper.saveUsers(userJoin);}
    public void saveUserInfo(UserJoin userJoin){userMapper.saveUserInfo(userJoin);}


    public UserFindId findId(String userName, String userTel) {
        return userMapper.findId(userName, userTel);
    }

    public UserFindPw findPw(String userName, String userId) {return userMapper.findPw(userName, userId);}
    public UserFindPw updatePw(String userPasswd) {return userMapper.updatePw(userPasswd);}


}
