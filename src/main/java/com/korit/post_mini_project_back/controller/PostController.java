package com.korit.post_mini_project_back.controller;

import com.korit.post_mini_project_back.dto.request.CreatePostReqDto;
import com.korit.post_mini_project_back.dto.request.GetFeedListReqDto;
import com.korit.post_mini_project_back.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/posts")
@RequiredArgsConstructor
public class PostController {

    private final PostService postService;

    @PostMapping(value = "", consumes = MediaType.MULTIPART_FORM_DATA_VALUE) //formData로 가지고 오기 위함(기본: json)
    public ResponseEntity<?> createPost(@ModelAttribute CreatePostReqDto dto) { //formData 받을 때는 @ModelAttribute
        System.out.println(dto);
        postService.createPost(dto);
        return ResponseEntity.ok(null);
    }
    @GetMapping("/feeds")
    public ResponseEntity<?> getFeedList(GetFeedListReqDto dto) {
        return ResponseEntity.ok(postService.getFeeds(dto));
    }
}
