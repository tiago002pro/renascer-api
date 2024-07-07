package com.api.renascer.notification.service;

import com.api.renascer.notification.model.Notification;
import com.api.renascer.notification.repository.NotificationRepository;
import com.api.renascer.schedule.model.Schedule;
import com.api.renascer.schedule.service.ScheduleService;
import com.api.renascer.video.model.Video;
import com.api.renascer.video.service.VideoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.Date;
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
    @Scheduled(cron = "0 0 9 * * *")
    private void notifyNewVideos() {
        List<Video> videosToNotify = this.videoService.getVideosToNotify();

        if (Objects.nonNull(videosToNotify) && !videosToNotify.isEmpty()) {
            videosToNotify.forEach(video -> {
                this.repository.insertNotification(
                    video.getTitle(),
                    "Tem vídeo novo! Confira agora mesmo.",
                    new Date(),
                    "VIDEOS",
                    video.getId()
                );
            });
        }
    }

    @Scheduled(cron = "0 0 13 * * *")
    private void notifyNewEvents() {
        List<Schedule> scheduleToNotify = this.scheduleService.getScheduleToNotify();

        if (Objects.nonNull(scheduleToNotify) && !scheduleToNotify.isEmpty()) {
            scheduleToNotify.forEach(schedule -> {
                this.repository.insertNotification(
                        schedule.getTitle(),
                        "Temos um novo evento! Não perca confira agora mesmo",
                        new Date(),
                        "EVENTOS",
                        schedule.getId()
                );
            });
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
}
