package com.api.renascer.notification.service;

import com.api.renascer.notification.helper.dto.ExpoNotificationDTO;
import com.api.renascer.notification.helper.enums.NotificationTypes;
import com.api.renascer.notification.model.Notification;
import com.api.renascer.notification.repository.NotificationRepository;
import com.api.renascer.schedule.model.Schedule;
import com.api.renascer.schedule.service.ScheduleService;
import com.api.renascer.user.domain.User;
import com.api.renascer.user.service.UserService;
import com.api.renascer.video.model.Video;
import com.api.renascer.video.service.VideoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class NotificationService {
    private final NotificationRepository repository;
    private final UserService userService;
    private final VideoService videoService;
    private final ScheduleService scheduleService;

    @Autowired
    public NotificationService(NotificationRepository repository,
                               UserService userService,
                               VideoService videoService,
                               ScheduleService scheduleService) {
        this.repository = repository;
        this.userService = userService;
        this.videoService = videoService;
        this.scheduleService = scheduleService;
    }

    public List<Notification> getAllNotifications(Long userId) {
        return repository.getAllNotifications(userId);
    }

    public Boolean checkIfThereAreNotifications(Long userId) {
        return repository.checkIfThereAreNotifications(userId);
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
        List<User> birthdays = this.userService.getAllBirthdays();
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
            this.repository.saveAll(notificationList);
        }
    }

    @Scheduled(cron = "0 0 8 * * *")
    private void notifyNewVideos() {
        List<Video> videosToNotify = this.videoService.getVideosToNotify();
        List<String> expoTokenUsers = this.userService.getAllExpoToken();

        if (Objects.nonNull(videosToNotify) && !videosToNotify.isEmpty()) {
            videosToNotify.forEach(video -> {
                this.repository.insertNotification(
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
                    this.sendExpoNotification(dto);
                });
            });

            List<Long> ids = videosToNotify.stream().map(Video::getId).collect(Collectors.toList());
            this.videoService.readVideosByIds(ids);
        }
    }

    @Scheduled(cron = "0 0 9 * * *")
    private void notifyNewEvents() {
        List<Schedule> scheduleToNotify = this.scheduleService.getScheduleToNotify();
        List<String> expoTokenUsers = this.userService.getAllExpoToken();

        if (Objects.nonNull(scheduleToNotify) && !scheduleToNotify.isEmpty()) {
            scheduleToNotify.forEach(schedule -> {
                this.repository.insertNotification(
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
                    this.sendExpoNotification(dto);
                });
            });

            List<Long> ids = scheduleToNotify.stream().map(Schedule::getId).collect(Collectors.toList());
            this.scheduleService.readScheduleByIds(ids);
        }
    }

    public Notification readNotification(Long id) {
        Notification notification = this.repository.findById(id).get();
        notification.setRead(Boolean.TRUE);
        return this.repository.save(notification);
    }

    public List<Notification> readAllNotifications(Long userId) {
        this.repository.readAll(userId);
        return this.getAllNotifications(userId);
    }

    public void deleteAllNotifications(Long userId) {
        this.repository.deleteAllByUser(userId);
    }

    public String sendExpoNotification(ExpoNotificationDTO dto) {
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
    }

    public String expoNotification(String expoToken) {
        ExpoNotificationDTO dto = new ExpoNotificationDTO(
            "ExponentPushToken[" + expoToken + "]",
            "Notificação via API JAVA",
            "Teste notificação via API JAVA."
        );

        return this.sendExpoNotification(dto);
    }

    public String generate() {
        this.notifyNewVideos();
        this.notifyNewEvents();
        return "Gerados com sucesso!";
    }
}
