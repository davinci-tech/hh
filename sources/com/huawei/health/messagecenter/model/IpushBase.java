package com.huawei.health.messagecenter.model;

import android.content.Context;
import java.util.List;

/* loaded from: classes.dex */
public interface IpushBase {
    List<String> getPushType();

    void processPushMsg(Context context, String str);
}
