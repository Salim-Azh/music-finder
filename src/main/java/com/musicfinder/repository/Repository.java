package com.musicfinder.repository;

import java.util.Optional;

public interface Repository<T> {
    Optional<T> save(T any);
}
