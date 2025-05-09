package com.huawei.health.device.constants;

/* loaded from: classes3.dex */
public class WeightConstants {
    private static final double[] c = {50.0d, 100.0d};

    public enum FragmentType {
        DEVICE_BIND_WAITING,
        DEVICE_BIND_WAITING_UNIVERSAL,
        WIFI_PRODUCT_INTRODUCTION,
        HAGRID_DEVICE_MANAGER,
        HAGRID_DEVICE_BIND_GUID,
        HONOUR_DEVICE
    }

    public static double[] c() {
        return (double[]) c.clone();
    }
}
