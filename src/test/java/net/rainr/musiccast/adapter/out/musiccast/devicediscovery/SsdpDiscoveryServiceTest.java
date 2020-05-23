package net.rainr.musiccast.adapter.out.musiccast.devicediscovery;

import io.resourcepool.ssdp.model.SsdpService;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;
import org.junit.jupiter.api.Test;

class SsdpDiscoveryServiceTest {


  @Test
  void name() throws InterruptedException, TimeoutException, ExecutionException {
    final var ssdpDiscoveryService = new SsdpDiscoveryService();

    final var ssdpServices = ssdpDiscoveryService.getServices().get(5, TimeUnit.SECONDS);

    for (String ssdpService : ssdpServices) {
      System.out.println(ssdpService);
    }

  }
}