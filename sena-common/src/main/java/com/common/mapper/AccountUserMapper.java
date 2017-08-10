package com.common.mapper;

import com.common.entity.UserEntity;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by Sena on 2017/2/28.
 */
public class AccountUserMapper {
    //region Column
    private static String ID = "Id";
    private static String USERNAME = "UserName";
    private static String HASHPASSWORD = "HashPassword";
    private static String EMAIL = "Email";
    //endregion

    //region Sql
    public static final String SQL_SELECT_GETUSER = "SELECT * FROM account_user";
    public static final String SQL_SELECT_USER_BY_INFO = "SELECT * FROM account_user WHERE (UserName = ? OR Email = ?) AND HashPassword = ?";
    public static final String SQL_INSERT_USER = "INSERT INTO account_user (UserName, HashPassword, Email, CreateDate, UpdateDate) VALUE (?,?,?,?,?)";
    //endregion

    //region mapper
    public static final RowMapper<UserEntity> MAPPER_AGENCY = new RowMapper<UserEntity>() {
        @Override
        public UserEntity mapRow(ResultSet rs, int i) throws SQLException {
            UserEntity user = new UserEntity();
            user.setId(rs.getInt(ID));
            user.setUserName(rs.getString(USERNAME));
            user.setHashPassword(rs.getString(HASHPASSWORD));
            user.setEmail(rs.getString(EMAIL));
            return user;
        }
    };
    //endregion
}
