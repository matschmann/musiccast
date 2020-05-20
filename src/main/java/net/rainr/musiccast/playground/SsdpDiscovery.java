package net.rainr.musiccast.playground;

import io.resourcepool.ssdp.client.SsdpClient;
import io.resourcepool.ssdp.model.DiscoveryListener;
import io.resourcepool.ssdp.model.DiscoveryRequest;
import io.resourcepool.ssdp.model.SsdpRequest;
import io.resourcepool.ssdp.model.SsdpService;
import io.resourcepool.ssdp.model.SsdpServiceAnnouncement;

class SsdpDiscovery {

  public static void main(String... args) {
    new SsdpDiscovery().discover();

  }

  public void discover() {
    SsdpClient client = SsdpClient.create();
    DiscoveryRequest all = SsdpRequest.discoverAll();
    client.discoverServices(all, new DiscoveryListener() {
      @Override
      public void onServiceDiscovered(SsdpService service) {
        System.out.println("Found service: " + service);
      }

      @Override
      public void onServiceAnnouncement(SsdpServiceAnnouncement announcement) {
        System.out.println("Service announced something: " + announcement);
      }

      @Override
      public void onFailed(Exception ex) {
        ex.printStackTrace();
      }
    });
  }
}
