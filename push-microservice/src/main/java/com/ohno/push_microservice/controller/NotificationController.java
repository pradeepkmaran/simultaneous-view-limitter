package com.ohno.push_microservice.controller;

import com.ohno.push_microservice.dto.Exist.ExistDto;
import com.ohno.push_microservice.dto.WatchInfo.WatchInfoDto;
import com.ohno.push_microservice.service.ConnectService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
public class NotificationController {
    private final ConnectService connectService;

    public NotificationService(ConnectService connectService) {
        this.connectService = connectService;
    }

    @GetMapping("/")
    public String index () {
        return "index";
    }

    @GetMapping("/notifications")
    public String notification () {
        return "notification";
    }

    @PostMapping("/send")
    @ResponseBody
    public ResponseEntity<?> send(@RequestBody WatchInfoDto.Request request) {
        connectService.connectUser(request);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping("/force")
    public ResponseEntity<?> forceConnect(@RequestBody ExistDto.Request request) {
        connectService.forceConnect(request);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PatchMapping("/stop")
    public ResponseEntity<?> stopConnect(@RequestBody WatchInfoDto.Request request) {
        connectService.stopConnect(request);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}