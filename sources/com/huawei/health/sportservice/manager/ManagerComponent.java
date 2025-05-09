package com.huawei.health.sportservice.manager;

import com.huawei.health.sportservice.SportLifecycle;
import com.huawei.health.sportservice.constants.SportObserverType;
import com.huawei.health.sportservice.constants.SportParamsType;

/* loaded from: classes4.dex */
public interface ManagerComponent extends SportLifecycle {
    default boolean addObserver(SportObserverType sportObserverType, String str, Object obj) {
        return false;
    }

    default void destroy() {
    }

    default <T> T getParas(SportParamsType sportParamsType) {
        return null;
    }

    default void init() {
    }

    default boolean removeObserver(SportObserverType sportObserverType, String str) {
        return false;
    }

    default void setParas(SportParamsType sportParamsType, Object obj) {
    }

    default boolean supportObserver(SportObserverType sportObserverType) {
        return false;
    }

    default boolean supportParas(SportParamsType sportParamsType) {
        return false;
    }
}
