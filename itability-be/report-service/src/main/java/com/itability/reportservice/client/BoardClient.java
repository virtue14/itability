package com.itability.reportservice.client;

import org.springframework.cloud.openfeign.FeignClient;

@FeignClient(name="itability-board-service", url="${gateway.url}")
public interface BoardClient {

}
