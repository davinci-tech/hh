package com.huawei.health.sportservice;

/* loaded from: classes.dex */
public interface SportBleStatus {
    default void onChecking() {
    }

    default void onConnected() {
    }

    default void onConnecting() {
    }

    default void onInterrupted() {
    }

    default void onReadSuccess() {
    }

    default void onRechecking() {
    }

    default void onReconnecting() {
    }

    default void onStateChanged(String str) {
    }
}
