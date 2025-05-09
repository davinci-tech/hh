package com.huawei.indoorequip;

import android.content.Context;
import com.huawei.hwfoundationmodel.trackmodel.HeartRateData;
import defpackage.ffs;
import java.util.ArrayList;

/* loaded from: classes9.dex */
public interface IVoiceResponseCallback {
    void onCreateVoice(int i, boolean z, int i2, int i3, int i4);

    void onResponse(int i, long j, long j2, int i2, int i3);

    void onResponse(int i, Object obj);

    void onScienceResponse(float f, int i, ffs ffsVar, ArrayList<HeartRateData> arrayList, Context context);
}
