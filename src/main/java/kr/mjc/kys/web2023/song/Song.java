package kr.mjc.kys.web2023.song;

import lombok.Data;
import org.owasp.encoder.Encode;

@Data
public class Song {
    int songId;
    String songTitle;
    String singer;
    int userId;

    public String getSongTitleEncoded() {
        return Encode.forHtml(songTitle);
    }

    public String getSingerEncoded() {
        return Encode.forHtml(singer);
    }
}
