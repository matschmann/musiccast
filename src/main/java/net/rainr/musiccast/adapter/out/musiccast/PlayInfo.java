package net.rainr.musiccast.adapter.out.musiccast;

import java.util.List;
import lombok.Data;

@Data
class PlayInfo {

  private String response_code;
  private String input;
  private String play_queue_type;
  private String playback;
  private String repeat;
  private String shuffle;
  private String play_time;
  private String total_time;
  private String artist;
  private String album;
  private String track;
  private String albumart_url;
  private String albumart_id;
  private String usb_devicetype;
  private String auto_stopped;
  private String attribute;
  private List<String> repeat_available;
  private List<String> shuffle_available;

  public boolean isSuccessful() {
    return "0".equals(response_code);
  }
}
