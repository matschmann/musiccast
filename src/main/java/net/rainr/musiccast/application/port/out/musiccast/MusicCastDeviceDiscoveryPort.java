package net.rainr.musiccast.application.port.out.musiccast;

import java.util.List;
import net.rainr.musiccast.domain.MusicCastDevice;

public interface MusicCastDeviceDiscoveryPort {

  List<MusicCastDevice> discoverDevices();


}
