package net.rainr.musiccast.adapter.out.musiccast.devicediscovery;

import java.util.Collections;
import java.util.List;
import lombok.AllArgsConstructor;
import net.rainr.musiccast.application.port.out.musiccast.MusicCastDeviceDiscoveryPort;
import net.rainr.musiccast.domain.MusicCastDevice;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
class MusicCastMusicCastDeviceDiscoveryAdapter implements MusicCastDeviceDiscoveryPort {

  private final SsdpDiscoveryService ssdpDiscoveryService;

  @Override
  public List<MusicCastDevice> discoverDevices() {
    return Collections.emptyList();
  }

}
