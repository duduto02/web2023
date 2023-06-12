package kr.mjc.kys.web2023.song;

import kr.mjc.kys.web2023.dao.Limit;
import lombok.AllArgsConstructor;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@AllArgsConstructor
public class SongDao {
    private static final String LIST_SONGS = """
            select songId, songTitle, singer from song 
            order by songId desc limit ?, ?""";

    private static final String GET_SONG = """
            select songId, songTitle, singer from song 
            where songId=?""";

    private static final String GET_USER_SONG = """
            select songId, songTitle, userId, singer from song
            where songId=? and userId=?""";

    private static final String ADD_SONG = """
            insert song(songTitle, userId, singer) values(:songTitle, :userId, :singer)""";

    private static final String UPDATE_SONG = """
            update song set songTitle=:songTitle, singer=:singer 
            where songId=:songId and userId=:userId
            """;

    private static final String DELETE_SONG =
            "delete from song where songId=? and userId=?";

    private final JdbcTemplate jdbcTemplate;
    private final NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    private final RowMapper<Song> songRowMapper =
            new BeanPropertyRowMapper<>(Song.class);

    // 음악 목록보기
    public List<Song> listSong(Limit limit) {
        return jdbcTemplate.query(LIST_SONGS, songRowMapper,
                limit.getOffset(), limit.getCount());
    }

    // 음악 한 건 보기
    public Song getSong(int songId) {
        return jdbcTemplate.queryForObject(GET_SONG, songRowMapper,
                songId);
    }

    public Song getUserSong(int songId, int userId) {
        return jdbcTemplate.queryForObject(GET_USER_SONG, songRowMapper,
                songId, userId);
    }

    public void addSong(Song song) {
        SqlParameterSource params = new BeanPropertySqlParameterSource(song);
        namedParameterJdbcTemplate.update(ADD_SONG, params);
    }

    public int updateSong(Song song) {
        SqlParameterSource params = new BeanPropertySqlParameterSource(song);
        return namedParameterJdbcTemplate.update(UPDATE_SONG, params);
    }

    public int deleteSong(int songId, int userId) {
        return jdbcTemplate.update(DELETE_SONG, songId, userId);
    }
}
