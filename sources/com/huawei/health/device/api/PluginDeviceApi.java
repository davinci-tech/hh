package com.huawei.health.device.api;

import android.content.ContentValues;
import android.content.Context;
import com.huawei.pluginbase.PluginBaseAdapter;
import defpackage.cjv;
import java.util.ArrayList;
import java.util.List;

/* loaded from: classes.dex */
public interface PluginDeviceApi {
    void checkAutoTrackStatus();

    PluginBaseAdapter getAdapter();

    ArrayList<ContentValues> getBondedDevice();

    List<cjv> getThirdDeviceList();

    void init(Context context);

    void setAdapter(PluginBaseAdapter pluginBaseAdapter);
}
