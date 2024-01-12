package com.github.osmundf.mongo.board.model.data;

import java.util.List;
import org.bson.conversions.Bson;
import org.springframework.lang.NonNull;

public record UpdateOrPipeline(
    @NonNull UpdateProcessType process,
    @NonNull Bson document,
    @NonNull List<? extends Bson> pipeline) {}
