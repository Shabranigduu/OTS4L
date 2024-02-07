package ru.OTS4L.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import ru.OTS4L.service.TelegramBotService;
import ru.OTS4L.SelfExceptions.TelegramException;
import ru.OTS4L.dto.RouteSheetDTO;
import ru.OTS4L.service.RouteSheetService;

import java.util.Date;
import java.util.List;

@Controller
@RequestMapping("/send")
public class PesokBotController {

    private final TelegramBotService telegramBotService;
    private final RouteSheetService routeSheetService;

    @Autowired
    public PesokBotController(TelegramBotService telegramBotService, RouteSheetService routeSheetService) {
        this.telegramBotService = telegramBotService;
        this.routeSheetService = routeSheetService;
    }

    @PostMapping("/message/{date}")
    public ResponseEntity<String> sendMassage(@PathVariable(name = "date") @DateTimeFormat(pattern = "dd.MM.yyyy") Date date) {
        List<RouteSheetDTO> routeSheetList = routeSheetService.getAllRouteSheetDTOByDate(date);
        try {
            telegramBotService.sendAllRouteSheets(routeSheetList);
        } catch (TelegramException e) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body("Что-то пошло не так");
        }

        return ResponseEntity.status(HttpStatus.OK).body("Листы успешно отправлены");
    }

    @PostMapping("/loner")
    public ResponseEntity<String> sendOneMessageLoner(@RequestParam("message") String routeSheetDTO){
        try {
            telegramBotService.sendOneRouteSheet(routeSheetDTO, 1);
        } catch (TelegramException e) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body("Что-то пошло не так");
        }
        return ResponseEntity.status(HttpStatus.OK).body("Лист успешно отправлен");
    }

    @PostMapping("/train")
    public ResponseEntity<String> sendOneMessageTrain(@RequestParam("message") String routeSheetDTO){
        try {
            telegramBotService.sendOneRouteSheet(routeSheetDTO, 2);
        } catch (TelegramException e) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body("Что-то пошло не так");
        }
        return ResponseEntity.status(HttpStatus.OK).body("Лист успешно отправлен");
    }
}
