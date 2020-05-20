package net.rainr.musiccast.domain;

import lombok.Value;

@Value
public class Song {

  Artist artist;
  Album album;
  String title;
}
