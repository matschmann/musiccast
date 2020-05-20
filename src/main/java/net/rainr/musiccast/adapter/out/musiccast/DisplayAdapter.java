package net.rainr.musiccast.adapter.out.musiccast;

import java.net.URI;
import lombok.AllArgsConstructor;
import net.rainr.musiccast.application.port.out.musiccast.DisplayPort;
import net.rainr.musiccast.domain.Album;
import net.rainr.musiccast.domain.Artist;
import net.rainr.musiccast.domain.Display;
import net.rainr.musiccast.domain.Song;
import net.rainr.musiccast.domain.Source;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
@AllArgsConstructor
class DisplayAdapter implements DisplayPort {

  public static final String GET_PLAY_INFO = "/YamahaExtendedControl/v1/netusb/getPlayInfo";
  private final RestTemplate restTemplate;

  @Override
  public Display currentDisplay(URI serverUri) {
    var playInfo = restTemplate.getForEntity(serverUri + GET_PLAY_INFO, PlayInfo.class).getBody();
    if (playInfo.isSuccessful()) {
      return new Display(new Song(new Artist(playInfo.getArtist()),
          new Album(playInfo.getAlbum(),
              URI.create(playInfo.getAlbumart_url())),
          playInfo.getTrack()),
          Source.valueOf(playInfo.getInput().toUpperCase()));
    } else {
      return null;
    }
  }
}
