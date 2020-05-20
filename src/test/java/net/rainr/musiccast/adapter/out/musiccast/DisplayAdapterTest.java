package net.rainr.musiccast.adapter.out.musiccast;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.requestTo;
import static org.springframework.test.web.client.response.MockRestResponseCreators.withSuccess;

import java.net.URI;
import net.rainr.musiccast.domain.Display;
import net.rainr.musiccast.domain.Source;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.client.RestClientTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.client.MockRestServiceServer;
import org.springframework.web.client.RestTemplate;

@RestClientTest({DisplayAdapter.class, MusicCastApiConfiguration.class})
class DisplayAdapterTest {


  @Autowired
  private DisplayAdapter sut;

  @Autowired
  private MockRestServiceServer server;

  @Autowired
  private RestTemplate restTemplate;

  @Test
  void playingSpotify() {

    this.server.expect(requestTo("http://192.168.178.43/YamahaExtendedControl/v1/netusb/getPlayInfo"))
        .andRespond(withSuccess("{"
                + "  \"response_code\": 0,"
                + "  \"input\": \"spotify\","
                + "  \"play_queue_type\": \"system\","
                + "  \"playback\": \"play\","
                + "  \"repeat\": \"off\","
                + "  \"shuffle\": \"on\","
                + "  \"play_time\": -60000,"
                + "  \"total_time\": 0,"
                + "  \"artist\": \"Project Pablo\","
                + "  \"album\": \"The Solution\","
                + "  \"track\": \"Slow Sweet\","
                + "  \"albumart_url\": \"/YamahaRemoteControl/AlbumART/AlbumART0991.jpg\","
                + "  \"albumart_id\": 991,"
                + "  \"usb_devicetype\": \"unknown\","
                + "  \"auto_stopped\": false,"
                + "  \"attribute\": 16777631,"
                + "  \"repeat_available\": [],"
                + "  \"shuffle_available\": []"
                + "}",
            MediaType.APPLICATION_JSON));

    Display currentDisplay = sut.currentDisplay(URI.create("http://192.168.178.43/"));

    assertThat(currentDisplay.getSong().getArtist().getName()).isEqualTo("Project Pablo");
    assertThat(currentDisplay.getSong().getAlbum().getTitle()).isEqualTo("The Solution");
    assertThat(currentDisplay.getSong().getAlbum().getAlbumArt())
        .hasPath("/YamahaRemoteControl/AlbumART/AlbumART0991.jpg");
    assertThat(currentDisplay.getSong().getTitle()).isEqualTo("Slow Sweet");
    assertThat(currentDisplay.getSource()).isEqualTo(Source.SPOTIFY);

  }
}