package kr.mjc.kys.web2023.song;

import jakarta.servlet.http.HttpServletRequest;
import kr.mjc.kys.web2023.HttpUtils;
import kr.mjc.kys.web2023.dao.Limit;
import lombok.AllArgsConstructor;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.SessionAttribute;
import org.springframework.web.server.ResponseStatusException;

@Controller
@AllArgsConstructor
public class SongController {

    private static final String CURRENT_SONG_LIST = "CURRENT_SONG_LIST";
    private final SongDao songDao;

    @GetMapping("/song/songList")
    public void songList(HttpServletRequest req, Limit limit, Model model) {
        req.getSession().setAttribute(CURRENT_SONG_LIST,
                HttpUtils.getRequestURLWithQueryString(req));

        req.setAttribute("songList", songDao.listSong(limit));
    }

    @GetMapping("/song/songForm")
    public void mapDefault() {}

    @GetMapping("/song/song")
    public void song(int songId, Model model) {
        model.addAttribute("song", songDao.getSong(songId));
    }

    @PostMapping("/song/addSong")
    public String addSong(Song song,
                          @SessionAttribute("me_userId") int userId) {
        song.setUserId(userId);
        songDao.addSong(song);
        return "redirect:/app/song/songList";
    }

    @GetMapping("/song/songEdit")
    public void songEdit(int songId,
                            @SessionAttribute("me_userId") int userId, Model model) {
        Song song = getUserSong(songId, userId);
        model.addAttribute("song", song);
    }

    @PostMapping("/song/updateSong")
    public String updateSong(Song song,
                             @SessionAttribute("me_userId") int userId) {
        getUserSong(song.getSongId(), userId);
        song.setUserId(userId);
        songDao.updateSong(song);
        return "redirect:/app/song/song?songId=" + song.getSongId();
    }

    @GetMapping("/song/deleteSong")
    public String deleteSong(int songId,
                             @SessionAttribute("me_userId") int userId,
                             @SessionAttribute(CURRENT_SONG_LIST) String currentSongList) {
        getUserSong(songId, userId);
        songDao.deleteSong(songId, userId);
        return "redirect:" + currentSongList;
    }

    private Song getUserSong(int songId, int userId) {
        try {
            return songDao.getUserSong(songId, userId);
        } catch (DataAccessException e) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED);
        }
    }
}
