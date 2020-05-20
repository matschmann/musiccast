package net.rainr.musiccast.application.port.out.musiccast;

import java.net.URI;
import net.rainr.musiccast.domain.Display;

public interface DisplayPort {

  Display currentDisplay(URI serverUri);

}
