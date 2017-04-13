package com.sena.mapper;

import com.sena.entity.AgentEntity;
import com.sena.entity.UserEntity;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by Sena on 2017/4/13.
 */
public class AgentMapper {

    //region Column
    private static String ID = "Id";
    private static String IP = "Ip";
    private static String PORT = "Port";
    private static String TYPE = "Type";
    private static String SURVIVALTIME = "SurvivalTime";
    private static String CREATEATUTC = "CreateAtUtc";
    //endregion

    //region Sql
    public static final String SQL_CRRATE_AGENT = "INSERT INTO agent_ip (Ip, Port, Type, SurvivalTime, CreateAtUtc) VALUES (?,?,?,?,?)";
    //endregion

    //region mapper
    public static final RowMapper<AgentEntity> AGENT_MAPPER = new RowMapper<AgentEntity>() {
        @Override
        public AgentEntity mapRow(ResultSet rs, int i) throws SQLException {
            AgentEntity agent = new AgentEntity();
            agent.setId(rs.getInt(ID));
            agent.setPort(rs.getString(PORT));
            agent.setType(rs.getString(TYPE));
            agent.setSurvivalTime(rs.getString(SURVIVALTIME));
            agent.setCreateAtUtc(rs.getDate(CREATEATUTC));
            return agent;
        }
    };
    //endregion
}
