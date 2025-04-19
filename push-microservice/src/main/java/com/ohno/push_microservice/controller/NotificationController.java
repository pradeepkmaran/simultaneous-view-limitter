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

    public NotificationController(ConnectService connectService) {
        this.connectService = connectService;
    }

    @RequestMapping("/")
    public String index () {
        return "index";
    }

    @RequestMapping("/notifications")
    public String notification () {
        return "notifications";
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