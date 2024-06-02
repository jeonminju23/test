// UserInfoService.java
package com.trioshop.service.user;

import com.trioshop.model.dto.user.*;
import com.trioshop.repository.dao.user.UserInfoDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UserInfoService {

    @Autowired
    private UserInfoDao userInfoDao;

    // 사용자의 유효성을 확인하고 로그인하는 메소드입니다.
    public UserInfoBySession isValidUser(UserIdPasswd userIdPasswd) {
        return userInfoDao.loginUser(userIdPasswd);
    }


    // saveUserInfo Dao에서 saveUsers,saveUserInfo 를 불러오고 boolean을 사용해서 판단합니다.
    // @Transactional를 사용해서 2개의 sql을 동시에 판단할 수 있게 한다.
    @Transactional
    public boolean saveUserInfo(UserJoin userJoin) {
        try {

            userInfoDao.saveUsers(userJoin);
            userInfoDao.saveUserInfo(userJoin);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public UserFindId isfindId(String userName, String userTel){return userInfoDao.findId(userName, userTel);}

    //@Transactional을 사용해서 userName,userId 을 select 하고 바로 userPasswd 나오게도 할수있었지만 이렇게 하니까 나중에 jsp에서 폼을 2개로 만들때 힘들었습니다 그래서 그냥 두개로 나누었습니다..
    public UserFindPw isfindPw(String userName, String userId){return userInfoDao.findPw(userName, userId);}
    public UserFindPw isupdate(String userPasswd){return userInfoDao.updatePw(userPasswd);}


}
