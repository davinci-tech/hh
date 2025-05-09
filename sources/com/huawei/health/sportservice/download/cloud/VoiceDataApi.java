package com.huawei.health.sportservice.download.cloud;

import android.content.Context;
import com.huawei.basefitnessadvice.callback.UiCallback;
import defpackage.fgy;
import defpackage.fha;
import java.util.List;

/* loaded from: classes7.dex */
public interface VoiceDataApi {
    void getVoiceBroadcastList(Context context, List<fha> list, UiCallback<List<fgy>> uiCallback);
}
