package com.host.videoserver.client.controller;

import com.host.videoserver.client.entity.User;
import com.host.videoserver.client.entity.Video;
import com.host.videoserver.client.service.UserService;
import com.host.videoserver.client.service.VideoService;
import com.host.videoserver.client.util.HttpHandler;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.core.io.UrlResource;
import org.springframework.core.io.support.ResourceRegion;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpRange;
import org.springframework.http.MediaTypeFactory;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

import static java.lang.Math.min;

@Controller
@ComponentScan("com.host.videoserver.client")
public class VideoController {

    @Value("${videoserver.url}")
    private String serverUrl;

    private final UserService userService;
    private final VideoService videoService;
    private final HttpHandler httpRequestHandler;

    public VideoController(UserService userService, VideoService videoService,
                           HttpHandler httpRequestHandler) {
        this.userService = userService;
        this.videoService = videoService;
        this.httpRequestHandler = httpRequestHandler;
    }

    @PostMapping(value = "/upload", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public String fileUpload(@RequestParam("file") MultipartFile file, @AuthenticationPrincipal User user,
                             Model model, @RequestParam(required = false) String open)  {
        if (file.getOriginalFilename().endsWith(".mp4")) {
            String originalFilename = file.getOriginalFilename();
            Video video = videoService.getByName(originalFilename);
            if (video == null) {
                video = new Video();
                if (open != null) {
                    video.setOpen(true);
                }
                video.setName(file.getOriginalFilename());
                String updateVideoUrl = "http://" + serverUrl + "/upload";
                MultiValueMap<String, Object> body = new LinkedMultiValueMap<>();
                body.add("file", file.getResource());
                body.add("userId", user.getId());
                String path = (String) httpRequestHandler
                        .executeRequest(updateVideoUrl, HttpMethod.POST, String.class, body,
                                MediaType.MULTIPART_FORM_DATA, user.getToken());
                video.setPath(path);
                User userFromDb = userService.findById(user.getId());
                Video save = videoService.save(video);
                List<Video> videos = userFromDb.getVideos();
                videos.add(save);
                userFromDb.setVideos(videos);
                User update = userService.update(userFromDb);
                model.addAttribute("videos", update.getVideos());
                return "user_profile";
            } else {
                throw new IllegalArgumentException("video with name" + originalFilename + "is already exists");
            }
        } else {
            throw new IllegalArgumentException("uncorrect video format please choose file with .mp4 ");
        }
    }

    @GetMapping("/videos")
    public ModelAndView streamVideoFile(@AuthenticationPrincipal User user) {
        ModelAndView modelAndView = new ModelAndView("videos");
        modelAndView.addObject("videos", videoService.getAllByUser(user));
        return modelAndView;
    }

    @GetMapping("/upload")
    public String uploadVideoPage() {
        return "upload";
    }

    @GetMapping("/video")
    public ResponseEntity<ResourceRegion> getVideo(@AuthenticationPrincipal User user,
                                                   @RequestParam String name, @RequestHeader HttpHeaders headers)
            throws Exception {
        Video video = videoService.getByName(name);
        String updateVideoUrl = "http://" + serverUrl + "videos?path=" + video.getPath();
        MultiValueMap<String, Object> body = new LinkedMultiValueMap<>();
        byte[] videoInBytes = (byte[]) httpRequestHandler.executeRequest(updateVideoUrl, HttpMethod.GET, byte[].class,
                body, MediaType.MULTIPART_FORM_DATA, user.getToken());
        File dir = new File("videos/" + user.getId() + "/");
        File file = new File(dir.getPath() + "/output.mp4");
        if (!dir.exists()) {
            boolean mkdirs = dir.mkdirs();
        }
        if (!file.exists()) {
            file.createNewFile();
        } else {
            file.delete();
            file.createNewFile();
        }
        Files.write(Paths.get(file.getPath()), videoInBytes);
        video.setWatchingCount(video.getWatchingCount() + 1);
        UrlResource videoPath = new UrlResource("file:" + file.getAbsolutePath());
        ResourceRegion region = resourceRegion(videoPath, headers);
        return ResponseEntity.status(HttpStatus.PARTIAL_CONTENT)
                .contentType(MediaTypeFactory
                        .getMediaType(videoPath)
                        .orElse(MediaType.APPLICATION_OCTET_STREAM))
                .body(region);
    }

    private ResourceRegion resourceRegion(UrlResource video, HttpHeaders headers) throws IOException {
        long contentLength = video.contentLength();
        List<HttpRange> range1 = headers.getRange();
        if (!range1.isEmpty()) {
            HttpRange range = headers.getRange().get(0);
            if (range != null) {
                long start = range.getRangeStart(contentLength);
                long end = range.getRangeEnd(contentLength);
                long rangeLength = min(1 * 1024 * 1024, end - start + 1);
                return new ResourceRegion(video, start, rangeLength);
            }
        }
        long rangeLength = min(1 * 1024 * 1024, contentLength);
        return new ResourceRegion(video, 0, rangeLength);
    }
}
