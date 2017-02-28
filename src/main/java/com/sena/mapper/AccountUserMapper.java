package com.sena.mapper;

import com.sena.entity.UserEntity;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by Sena on 2017/2/28.
 */
public class AccountUserMapper implements RowMapper<UserEntity> {
    private String ID = "Id";
    private String USERNAME = "UserName";
    private String HASHPASSWORD = "HashPassword";
    private String EMAIL = "Email";



    public UserEntity mapRow(ResultSet rs, int i) throws SQLException {
        UserEntity user = new UserEntity();
        user.setId(rs.getInt(ID));
        user.setUserName(rs.getString(USERNAME));
        user.setHashPassword(rs.getString(HASHPASSWORD));
        user.setEmail(rs.getString(EMAIL));
        return user;
    }
}
