package com.huawei.openalliance.ad.utils;

import com.huawei.openalliance.ad.inter.data.FeedbackInfo;
import com.huawei.profile.profile.ProfileExtendConstants;
import java.util.List;

/* loaded from: classes5.dex */
public class ad {

    /* renamed from: a, reason: collision with root package name */
    private static long f7572a;
    private static long b;

    public static void b() {
        b = System.currentTimeMillis();
    }

    public static boolean a(List<FeedbackInfo> list) {
        if (bg.a(list)) {
            return false;
        }
        for (FeedbackInfo feedbackInfo : list) {
            if (feedbackInfo != null && feedbackInfo.b()) {
                return true;
            }
        }
        return false;
    }

    public static boolean a() {
        if (Math.abs(System.currentTimeMillis() - f7572a) < 500 || Math.abs(System.currentTimeMillis() - b) < ProfileExtendConstants.TIME_OUT) {
            return true;
        }
        f7572a = System.currentTimeMillis();
        return false;
    }
}
