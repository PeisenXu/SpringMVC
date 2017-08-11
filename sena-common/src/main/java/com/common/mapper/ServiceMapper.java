package com.common.mapper;

import com.common.entity.ServiceEntity;
import com.common.util.DBUtil;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by Sena on 2017/8/11.
 */
public class ServiceMapper {
    //region Column
    private static String ID = "Id";
    private static String UUID = "UId";
    private static String CREATEATUTC = "CreateAtUtc";
    private static String TYPE = "Type";
    private static String DATA = "Data";
    private static String FAILURE = "Failure";
    //endregion

    //region Sql
    public static final String SQL_SELECT_ACCOUNT_SERVICE = "SELECT * FROM account_service WHERE UId = ? AND IsDeleted = 0";
    public static final String SQL_INSERT_ACCOUNT_SERVICE = "INSERT INTO account_service (UId, Type, CreateAtUtc, Data, Failure) VALUE (?, ?, ?, ?, ?)";
    public static final String SQL_UPDATE_ACCOUNT_SERVICE_STATUS = "UPDATE account_service SET IsDeleted = 1 WHERE Id = ?";
    //endregion

    //region mapper
    public static final RowMapper<ServiceEntity> MAPPER_AGENCY = new RowMapper<ServiceEntity>() {
        @Override
        public ServiceEntity mapRow(ResultSet rs, int i) throws SQLException {
            ServiceEntity service = new ServiceEntity();
            service.setId(DBUtil.getInt(rs, ID));
            service.setUuid(DBUtil.getString(rs, UUID));
            service.setType(DBUtil.getString(rs, TYPE));
            service.setCreateAtUtc(DBUtil.getTimestamp(rs, CREATEATUTC));
            service.setData(DBUtil.getString(rs, DATA));
            service.setFailure(DBUtil.getInt(rs, FAILURE));
            return service;
        }
    };
    //endregion
}
