package org.example.maildemo.service;

import lombok.RequiredArgsConstructor;
import org.example.maildemo.constants.CustomResponseMessage;
import org.example.maildemo.entity.Tag;
import org.example.maildemo.exception.NotFoundException;
import org.example.maildemo.repository.TagRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class TagService {
    private final TagRepository tagRepository;

    public List<Tag> findAll() {
        return tagRepository.findAll();
    }

    public void createTag(Tag tag) {
        tagRepository.save(tag);
    }

    public Set<Tag> findTags(List<String> tags) {
        return tags.stream().map(e -> {
            try {
                return tagRepository.findTagByName(e).orElseThrow(() -> new NotFoundException(CustomResponseMessage.TAG_NOT_FOUND));
            } catch (NotFoundException ex) {
                throw new RuntimeException(ex);
            }
        }).collect(Collectors.toSet());
    }
}
