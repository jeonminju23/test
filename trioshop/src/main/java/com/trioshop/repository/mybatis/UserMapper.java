package com.trioshop.repository.mybatis;

import com.trioshop.model.dto.user.*;
import org.apache.ibatis.annotations.Param;


public interface UserMapper {

    /* TRIO_USERS에서 user_id, user_passwd 정보 확인 /login */
    UserInfoBySession loginUser(UserIdPasswd userIdPasswd);

    // TRIO_USERS 테이블에 사용자 정보 저장 /join
    void saveUsers(UserJoin userJoin);
    void saveUserInfo(UserJoin userJoin);

    /* @Param를 사용해서 userName,userTel을 입력했을때 userId 반환하기 때문에 명시를 위해 사용했습니다. 그리고 void로 하면 null값이 반환이 되게때문에 객체명으로 사용했습니다.  /findId */
    UserFindId findId(@Param("userName") String userName,
                      @Param("userTel") String userTel);

    /* @Param를 사용해서 userName,userId 입력했을때 userPasswd 반환하기 때문에 명시를 위해 사용했습니다. 그리고 void로 하면 null값이 반환이 되게때문에 객체명으로 사용했습니다. /findPw*/
    UserFindPw findPw(@Param("userName") String userName,
                      @Param("userId") String userId);

    /* 반환됐을때 userPasswd를 update하기 위해 하나 더 만들었습니다.(쿼리문이 다르기떄문에!)/update */
    UserFindPw updatePw(@Param("userPasswd") String userPasswd);


}