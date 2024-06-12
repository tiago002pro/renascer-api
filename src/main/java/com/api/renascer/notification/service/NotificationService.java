package com.api.renascer.notification.service;

import com.api.renascer.notification.helper.enums.NotificationTypes;
import com.api.renascer.notification.model.Notification;
import com.api.renascer.notification.repository.NotificationRepository;
import com.api.renascer.schedule.model.Schedule;
import com.api.renascer.schedule.service.ScheduleService;
import com.api.renascer.video.model.Video;
import com.api.renascer.video.service.VideoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Service
public class NotificationService {
    private final NotificationRepository repository;
    private final VideoService videoService;
    private final ScheduleService scheduleService;

    @Autowired
    public NotificationService(NotificationRepository repository,
                               VideoService videoService,
                               ScheduleService scheduleService) {
        this.repository = repository;
        this.videoService = videoService;
        this.scheduleService = scheduleService;
    }

    public List<Notification> getAllNotifications() {
        return repository.getAllNotifications();
    }

    //Todos os dias as 09hs ver se tem video novo
    //Todos os dias as 13hs ver se tem evento novo

    //    A: Segundos (0 - 59).
    //    B: Minutos (0 - 59).
    //    C: Horas (0 - 23).
    //    D: Dia (1 - 31).
    //    E: Mês (1 - 12).
    //    F: Dia da semana (0 - 6).

    @Scheduled(cron = "0 0 9 * * *")
    private void notifyNewVideos() {
        List<Video> videosToNotify = this.videoService.getVideosToNotify();

        if (Objects.nonNull(videosToNotify) && !videosToNotify.isEmpty()) {
            List<Notification> notificationList = new ArrayList<>();

            videosToNotify.forEach(video -> {
                Notification notification = new Notification(
                        video.getTitle(),
                        "Tem vídeo novo! Confira agora mesmo.",
                        video.getDate(),
                        Boolean.FALSE,
                        NotificationTypes.VIDEOS,
                        video.getId()
                );
                notificationList.add(notification);
            });

            this.repository.saveAll(notificationList);
        }
    }

    @Scheduled(cron = "0 0 13 * * *")
    private void notifyNewEvents() {
        List<Schedule> scheduleToNotify = this.scheduleService.getScheduleToNotify();

        if (Objects.nonNull(scheduleToNotify) && !scheduleToNotify.isEmpty()) {
            List<Notification> notificationList = new ArrayList<>();

            scheduleToNotify.forEach(schedule -> {
                Notification notification = new Notification(
                        schedule.getTitle(),
                        "Temos um novo evento! Não perca confira agora mesmo.",
                        schedule.getStartDate(),
                        Boolean.FALSE,
                        NotificationTypes.EVENTOS,
                        schedule.getId()
                );
                notificationList.add(notification);
            });

            this.repository.saveAll(notificationList);
        }
    }

    public Notification readNotification(Long id) {
        Notification notification = this.repository.findById(id).get();
        notification.setRead(Boolean.TRUE);
        return this.repository.save(notification);
    }
}
