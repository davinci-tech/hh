package com.huawei.health.sportservice;

/* loaded from: classes.dex */
public interface SportLifecycle {
    default void onCountDown() {
    }

    default void onPauseSport() {
    }

    default void onPreSport() {
    }

    default void onResumeSport() {
    }

    default void onStartSport() {
    }

    default void onStopSport() {
    }

    default void onPreSport(String str) {
        onPreSport();
    }

    default void onCountDown(String str) {
        onCountDown();
    }

    default void onStartSport(String str) {
        onStartSport();
    }

    default void onResumeSport(String str) {
        onResumeSport();
    }

    default void onPauseSport(String str) {
        onPauseSport();
    }

    default void onStopSport(String str) {
        onStopSport();
    }
}
