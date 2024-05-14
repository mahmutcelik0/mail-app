package org.example.maildemo.populator;

import org.example.maildemo.dto.TagDto;
import org.example.maildemo.entity.Tag;
import org.springframework.stereotype.Component;

@Component
public class TagDtoPopulator extends GenericPopulator <Tag, TagDto> {
    @Override
    public TagDto populate(Tag tag, TagDto tagDto) {
        tagDto.setName(tag.getName());
        return tagDto;
    }

    @Override
    public TagDto getTarget() {
        return new TagDto();
    }
}
