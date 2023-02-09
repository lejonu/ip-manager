package ipmanager.service;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import ipmanager.model.Ip;

@FeignClient(name = "ip-api", url = "http://ip-api.com/json")
public interface IpApiService {
	@GetMapping("/{query}")
	Ip consultIP(@PathVariable("query") String query);
}
