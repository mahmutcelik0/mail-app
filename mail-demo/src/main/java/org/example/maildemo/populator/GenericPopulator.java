package org.example.maildemo.populator;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public abstract class GenericPopulator<Source, Target> {
    public Target populate(Source source) {
        return populate(source, getTarget());
    }

    public abstract Target populate(Source source, Target target);

    public abstract Target getTarget();

    public List<Target> populateAll(List<Source> sources) {
        return sources.stream().map(this::populate).toList();
    }

    public Set<Target> populateAll(Set<Source> sources) {
        return sources.stream().map(this::populate).collect(Collectors.toSet());
    }
}
