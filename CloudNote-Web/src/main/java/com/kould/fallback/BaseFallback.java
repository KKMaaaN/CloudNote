package com.kould.fallback;

import java.util.List;

public interface BaseFallback<T> {
    T fallbackOne() ;
    List<T> fallbackList() ;
}
