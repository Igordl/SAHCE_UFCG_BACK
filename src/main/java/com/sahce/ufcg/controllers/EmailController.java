package com.sahce.ufcg.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sahce.ufcg.dtos.notification.NotificationDtoRequest;
import com.sahce.ufcg.dtos.restePassword.RequestResetPasswordDtoRequest;
import com.sahce.ufcg.dtos.schedule.ScheduleResponseDto;
import com.sahce.ufcg.services.EmailSenderService;
import com.sahce.ufcg.services.MyUserService;
import com.sahce.ufcg.services.ScheduleService;

@RestController
@RequestMapping("/v1")
@CrossOrigin(origins = "http://localhost:3000")
public class EmailController {

    @Autowired
    private EmailSenderService senderService;

    @Autowired
    private MyUserService myUserService;

    @Autowired
    private ScheduleService scheduleService;

    @PostMapping("/anonymous/requestResetPassword")
    public void resetPassword(@RequestBody RequestResetPasswordDtoRequest dto) {
        System.out.println("Resetando senha");
        System.out.println(dto);
        String password = myUserService.requestResetPassword(dto.getEmail());
        senderService.sendEmail(dto.getEmail(), "Redefinição de senha", "Senha para alteração: " + password);

    }

    @PostMapping("/admin/notification")
    public void sendEmaiNotificationl(@RequestBody NotificationDtoRequest dto) {

        List<ScheduleResponseDto> sDto = scheduleService.getAllByName(dto.getPlaceName());
        for (ScheduleResponseDto scheduleResponseDto : sDto) {
            senderService.sendEmail(scheduleResponseDto.getOwnerEmail(), dto.getSubject(), dto.getMessage());
        }

    }

}
