package com.huawei.pluginachievement;

import android.content.Context;
import android.os.Handler;
import com.huawei.pluginbase.PluginBaseAdapter;
import defpackage.mcz;
import defpackage.mdd;
import java.util.Map;

/* loaded from: classes6.dex */
public interface AchieveDataApi {
    void dealKakaTask(Context context, Handler handler, int i);

    void dealWeChatTask();

    void generateReportHealthData(Context context);

    PluginBaseAdapter getAdapter(Context context);

    mcz getData(int i, Map<String, String> map);

    boolean getKakaTaskRedDot(Context context);

    mdd getSingleDayData();

    void initAdapter(Context context, PluginBaseAdapter pluginBaseAdapter);
}
