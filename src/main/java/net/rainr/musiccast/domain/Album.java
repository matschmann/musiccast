package net.rainr.musiccast.domain;

import java.net.URI;
import lombok.Value;

@Value
public class Album {

  String title;
  URI albumArt;
}
