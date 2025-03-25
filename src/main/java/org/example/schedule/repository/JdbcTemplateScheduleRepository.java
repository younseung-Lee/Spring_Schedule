package org.example.schedule.repository;

import org.example.schedule.dto.ScheduleResponseDto;
import org.example.schedule.entity.Schedule;
import org.springframework.http.HttpStatus;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;
import org.springframework.web.server.ResponseStatusException;

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Repository
public class JdbcTemplateScheduleRepository implements ScheduleRepository{
    private final JdbcTemplate jdbcTemplate;

    public JdbcTemplateScheduleRepository(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    @Override
    public ScheduleResponseDto saveSchedule(Schedule schedule){
        SimpleJdbcInsert jdbcInsert = new SimpleJdbcInsert(jdbcTemplate);
        jdbcInsert.withTableName("schedule").usingGeneratedKeyColumns("id");

        Map<String, Object> parameters = new HashMap<>();
        parameters.put("todo", schedule.getTodo());
        parameters.put("username", schedule.getUsername());
        parameters.put("password", schedule.getPassword());
        parameters.put("createtime", Timestamp.valueOf(schedule.getCreatetime()));  // LocalDateTime -> Timestamp 변환
        parameters.put("updatetime", Timestamp.valueOf(schedule.getUpdatetime()));

        Number key = jdbcInsert.executeAndReturnKey(new MapSqlParameterSource(parameters));

        return new ScheduleResponseDto(
                key.longValue(),
                schedule.getTodo(),
                schedule.getUsername(),
                schedule.getCreatetime(),
                schedule.getUpdatetime()
        );
    }

    @Override
    public List<ScheduleResponseDto> findAll() {
        return jdbcTemplate.query("select * from schedule order by updatetime desc ", scheduleRowMapper());
    }

    @Override
    public Optional<Schedule> findById(Long id){
        List<Schedule> result = jdbcTemplate.query("select * from schedule where id = ?", scheduleRowMapperV2(), id);
        return result.stream().findAny();
    }

    @Override
    public Schedule findByIdOrElseThrows(Long id) {
        List<Schedule> result = jdbcTemplate.query("select * from schedule where id = ?", scheduleRowMapperV2(), id);
        return result.stream().findAny().orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,"id값이 없습니다"+id));
    }

    @Override
    public int updateSchedule(Long id, String todo, String username, String password) {
        return jdbcTemplate.update(
                "update schedule set todo=?, username=?, password=?, updatetime=? where id=?",
                todo, username, password, Timestamp.valueOf(LocalDateTime.now()), id
        );
    }

    @Override
    public int delete(Long id) {
        return jdbcTemplate.update("delete  from schedule where id = ?", id);
    }

    private RowMapper<Schedule> scheduleRowMapperV2() {
        return new RowMapper<Schedule>() {
            @Override
            public Schedule mapRow(ResultSet rs, int rowNum) throws SQLException {
                return new Schedule(
                        rs.getLong("id"),
                        rs.getString("todo"),
                        rs.getString("username"),
//                        rs.getString("createtime"),
//                        rs.getString("updatetime")
                        rs.getTimestamp("createtime").toLocalDateTime(),
                        rs.getTimestamp("updatetime").toLocalDateTime()
                );
            }
        };
    }
    /**
     * rs.getString("createtime"),
     * rs.getString("updatetime")
     * 문제: createtime, updatetime은 TIMESTAMP 타입이므로, getTimestamp("createtime")을 사용하고 LocalDateTime으로 변환해야 함.
     * **/

    private RowMapper<ScheduleResponseDto> scheduleRowMapper() {
        return new RowMapper<ScheduleResponseDto>() {
            @Override
            public ScheduleResponseDto mapRow(ResultSet rs, int rowNum) throws SQLException {
                return new ScheduleResponseDto(
                        rs.getLong("id"),
                        rs.getString("todo"),
                        rs.getString("username"),
                        rs.getTimestamp("createtime").toLocalDateTime(),
                        rs.getTimestamp("updatetime").toLocalDateTime()
                );
            }
        };
    }

}
