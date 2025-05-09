package com.huawei.hwdevice.phoneprocess.mgr.exercise.utils;

import android.text.TextUtils;
import com.huawei.hwlogsmodel.LogUtil;
import defpackage.kbt;
import defpackage.kbu;
import defpackage.koa;
import defpackage.koq;
import java.util.List;

/* loaded from: classes5.dex */
public class SequenceFileDataUtils {
    private static final int EVENT_LENGTH = 32;
    private static final String TAG = "SequenceFileDataUtils";

    private SequenceFileDataUtils() {
    }

    public static String getSequenceFileDataString(kbu kbuVar) {
        StringBuilder sb = new StringBuilder();
        if (kbuVar == null) {
            LogUtil.h(TAG, "sampleSequenceFileData is null");
            return sb.toString();
        }
        List<kbt> e = kbuVar.e();
        if (koq.b(e)) {
            LogUtil.h(TAG, "sampleSequenceDataList is null or empty");
            return sb.toString();
        }
        kbt kbtVar = e.get(0);
        if (kbtVar == null) {
            LogUtil.h(TAG, "sampleSequenceData is null");
            return sb.toString();
        }
        String d = kbtVar.d();
        if (TextUtils.isEmpty(d)) {
            LogUtil.h(TAG, "detailTotalData is null");
            return sb.toString();
        }
        while (d.length() >= 32) {
            sb.append(new koa(d.substring(0, 32)).toString());
            d = d.substring(32);
        }
        return sb.toString();
    }
}
