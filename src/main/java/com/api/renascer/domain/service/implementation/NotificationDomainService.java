package com.api.renascer.domain.service.implementation;

import com.api.renascer.domain.enums.NotificationTypes;
import com.api.renascer.domain.exception.HttpResquestException;
import com.api.renascer.domain.model.Notification;
import com.api.renascer.domain.model.User;
import com.api.renascer.domain.model.Video;
import com.api.renascer.domain.repository.NotificationRepository;
import com.api.renascer.domain.service.NotificationService;
import com.api.renascer.domain.service.VideosService;
import com.api.renascer.domain.dto.ExpoNotificationDTO;
import com.api.renascer.domain.model.Schedule;
import com.api.renascer.application.web.ScheduleService;
import com.api.renascer.application.web.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.*;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.*;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class NotificationDomainService implements NotificationService {

    private final NotificationRepository notificationRepository;
    private final VideosService videosService;
    private final ScheduleService scheduleService;
    private final UserService userService;

    @Override
    public List<Notification> getAllNotifications(Long userId) {
        try {
            return notificationRepository.getAllNotifications(userId);
        } catch (HttpResquestException e) {
            throw new HttpResquestException(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @Override
    public Boolean checkIfThereAreNotifications(Long userId) {
        try {
            return notificationRepository.checkIfThereAreNotifications(userId);
        } catch (HttpResquestException e) {
            throw new HttpResquestException(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @Override
    public Notification readNotification(Long id) {
        try {
            Notification notification = notificationRepository.findById(id).get();
            notification.setRead(Boolean.TRUE);
            return notificationRepository.save(notification);
        } catch (HttpResquestException e) {
            throw new HttpResquestException(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @Override
    public List<Notification> readAllNotifications(Long userId) {
        try {
            notificationRepository.readAll(userId);
            return getAllNotifications(userId);
        } catch (HttpResquestException e) {
            throw new HttpResquestException(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @Override
    public void deleteAllNotifications(Long userId) {
        try {
            notificationRepository.deleteAllByUser(userId);
        } catch (HttpResquestException e) {
            throw new HttpResquestException(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @Override
    public String sendExpoNotification(ExpoNotificationDTO dto) {
        try {
            HttpHeaders headers = new HttpHeaders();
            headers.set("Accept", "application/json");
            headers.set("Content-Type", "application/json");

            Map<String, String> body = new HashMap<>();
            body.put("to", dto.getExpoToken());
            body.put("title", dto.getTitle());
            body.put("body", dto.getBody());
            body.put("sound", "default");
            body.put("vibrate", "true");

            HttpEntity httpEntity = new HttpEntity<>(body, headers);

            RestTemplate restTemplate = new RestTemplate();
            ResponseEntity<Map> response = restTemplate.exchange("https://exp.host/--/api/v2/push/send", HttpMethod.POST, httpEntity, Map.class);
            if (response.getStatusCode().is2xxSuccessful()) {
                return "Deu certo!";
            } else {
                return "Deu errado!";
            }
        } catch (HttpResquestException e) {
            throw new HttpResquestException(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @Override
    public String expoNotification(String expoToken) {
        try {
            ExpoNotificationDTO dto = new ExpoNotificationDTO(
                    "ExponentPushToken[" + expoToken + "]",
                    "Notificação via API JAVA",
                    "Teste notificação via API JAVA."
            );

            return sendExpoNotification(dto);
        } catch (HttpResquestException e) {
            throw new HttpResquestException(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @Override
    public String generate() {
        try {
            notifyNewVideos();
            notifyNewEvents();
            return "Gerados com sucesso!";
        } catch (HttpResquestException e) {
            throw new HttpResquestException(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @Scheduled(cron = "0 0 8 * * *")
    private void notifyNewVideos() {
        List<Video> videosToNotify = videosService.getVideosToNotify();
        List<String> expoTokenUsers = userService.getAllExpoToken();

        if (Objects.nonNull(videosToNotify) && !videosToNotify.isEmpty()) {
            videosToNotify.forEach(video -> {
                notificationRepository.insertNotification(
                        video.getTitle(),
                        "Tem vídeo novo! Confira agora mesmo.",
                        new Date(),
                        "VIDEOS",
                        video.getId()
                );

                expoTokenUsers.forEach(expoToken -> {
                    ExpoNotificationDTO dto = new ExpoNotificationDTO(
                            expoToken,
                            "Tem vídeo novo!",
                            video.getTitle()
                    );
                    sendExpoNotification(dto);
                });
            });

            List<Long> ids = videosToNotify.stream().map(Video::getId).collect(Collectors.toList());
            videosService.readVideosByIds(ids);
        }
    }

    @Scheduled(cron = "0 0 9 * * *")
    private void notifyNewEvents() {
        List<Schedule> scheduleToNotify = scheduleService.getScheduleToNotify();
        List<String> expoTokenUsers = userService.getAllExpoToken();

        if (Objects.nonNull(scheduleToNotify) && !scheduleToNotify.isEmpty()) {
            scheduleToNotify.forEach(schedule -> {
                notificationRepository.insertNotification(
                        schedule.getTitle(),
                        "Temos um novo evento! Não perca confira agora mesmo",
                        new Date(),
                        "EVENTOS",
                        schedule.getId()
                );

                expoTokenUsers.forEach(expoToken -> {
                    ExpoNotificationDTO dto = new ExpoNotificationDTO(
                            expoToken,
                            "Temos um novo evento!",
                            schedule.getTitle() + ". Saiba mais!"
                    );
                    sendExpoNotification(dto);
                });
            });

            List<Long> ids = scheduleToNotify.stream().map(Schedule::getId).collect(Collectors.toList());
            scheduleService.readScheduleByIds(ids);
        }
    }

    //Todos os dias as 09hs ver se tem video novo
    //Todos os dias as 13hs ver se tem evento novo
    //    A: Segundos (0 - 59).
    //    B: Minutos (0 - 59).
    //    C: Horas (0 - 23).
    //    D: Dia (1 - 31).
    //    E: Mês (1 - 12).
    //    F: Dia da semana (0 - 6).
    @Scheduled(cron = "0 0 7 * * *")
    private void notifyBirthdays() {
        List<User> birthdays = userService.getAllBirthdays();
        if (Objects.nonNull(birthdays) && !birthdays.isEmpty()) {
            List<Notification> notificationList = new ArrayList<>();
            birthdays.forEach(user -> {
                Notification notification = Notification.builder()
                        .title("Feliz Aniversário!!!")
                        .description("O Senhor te abençoe e te guarde; o Senhor faça resplandecer " +
                                "o seu rosto sobre ti " +
                                "e te conceda graça; o Senhor volte para ti o seu rosto " +
                                "e te dê paz. (Números 6:24-26)"
                        )
                        .date(new Date())
                        .types(NotificationTypes.BIRTHDAY)
                        .entityId(user.getId())
                        .build();
                notificationList.add(notification);
            });
            notificationRepository.saveAll(notificationList);
        }
    }
}
