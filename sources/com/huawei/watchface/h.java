package com.huawei.watchface;

import android.content.Context;
import com.huawei.hihealth.listener.ResultCallback;
import com.huawei.hihealthkit.auth.HiHealthAuth;
import com.huawei.hihealthkit.auth.IAuthorizationListener;
import com.huawei.watchface.api.HwWatchFaceApi;
import com.huawei.watchface.environment.Environment;
import com.huawei.watchface.mvp.model.datatype.CommandJsonInfo;
import defpackage.ieb;
import java.nio.ByteBuffer;

/* loaded from: classes7.dex */
public class h {

    /* renamed from: a, reason: collision with root package name */
    public static final String f11045a = "h";

    public static void a(Context context, CommandJsonInfo commandJsonInfo, ResultCallback resultCallback) {
        if (Environment.sIsAarInTheme) {
            ieb.e(context.getApplicationContext(), commandJsonInfo.toString(), resultCallback);
        } else {
            HwWatchFaceApi.getInstance(context).sendBluetoothCommand(commandJsonInfo.getServiceId(), commandJsonInfo.getCommandId(), ByteBuffer.wrap(da.a(commandJsonInfo.getDeviceCommand())));
        }
    }

    public static void a(Context context, ResultCallback resultCallback) {
        ieb.c(context.getApplicationContext(), resultCallback);
    }

    public static void a(Context context, IAuthorizationListener iAuthorizationListener) {
        HiHealthAuth.requestAuthorization(context.getApplicationContext(), new int[]{101204}, new int[]{101201}, iAuthorizationListener);
    }
}
