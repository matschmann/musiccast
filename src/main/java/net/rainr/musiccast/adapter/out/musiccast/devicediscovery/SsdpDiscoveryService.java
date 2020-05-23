package net.rainr.musiccast.adapter.out.musiccast.devicediscovery;


import io.resourcepool.ssdp.client.SsdpClient;
import io.resourcepool.ssdp.model.DiscoveryListener;
import io.resourcepool.ssdp.model.DiscoveryRequest;
import io.resourcepool.ssdp.model.SsdpRequest;
import io.resourcepool.ssdp.model.SsdpService;
import io.resourcepool.ssdp.model.SsdpServiceAnnouncement;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import lombok.Getter;
import org.springframework.stereotype.Component;

@Component
class SsdpDiscoveryService {

  private final ExecutorService executor = Executors.newSingleThreadExecutor();

  private final List<String> services = new ArrayList<>();

  public SsdpDiscoveryService() {
    SsdpClient client = SsdpClient.create();
    DiscoveryRequest all = SsdpRequest.discoverAll();
    client.discoverServices(all, new DiscoveryListener() {
      @Override
      public void onServiceDiscovered(SsdpService service) {
        services.add(service.getLocation());
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

  Future<Collection<String>> getServices() {
    return executor.submit(() -> {

      var previousSize = services.size();

      while (services.isEmpty() || previousSize < services.size()) {
        previousSize = services.size();
        Thread.sleep(200);
        System.out.println("x");
      }

      return new HashSet<>(services);
    });
  }
}
