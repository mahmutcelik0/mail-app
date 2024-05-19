package org.example.maildemo.populator;

import java.util.List;
import java.util.Set;

public abstract class GenericPopulator<Source, Target> {
    public Target populate(Source source) {
        return populate(source, getTarget());
    }

    public abstract Target populate(Source source, Target target);

    public abstract Target getTarget();

    public List<Target> populateAlltoList(Set<Source> sources) {
        return sources.stream().map(this::populate).toList();
    }
}
