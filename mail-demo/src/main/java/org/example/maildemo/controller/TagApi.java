package org.example.maildemo.controller;

import lombok.RequiredArgsConstructor;
import org.example.maildemo.entity.Tag;
import org.example.maildemo.service.TagService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/tags")
@RequiredArgsConstructor
public class TagApi {
    private final TagService tagService;

    @GetMapping
    public List<Tag> getAllTags() {
        return tagService.findAll();
    }

    @PostMapping
    public void createTag(@RequestBody Tag tag){
        tagService.createTag(tag);
    }
}
