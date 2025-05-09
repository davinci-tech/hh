package com.huawei.watchface.manager;

import android.content.Context;
import com.huawei.watchface.api.HwWatchFaceApi;
import com.huawei.watchface.environment.Environment;
import com.huawei.watchface.i;
import com.huawei.watchface.j;
import com.huawei.watchface.mvp.model.datatype.TransferFileReqType;
import com.huawei.watchface.utils.HwLog;
import com.huawei.watchface.utils.callback.IAppTransferFileResultAIDLCallback;
import com.huawei.watchface.utils.callback.IBaseResponseCallback;
import com.huawei.watchface.utils.callback.IFileTransferStateCallback;
import com.huawei.watchface.utils.callback.IPhotoFileCallback;
import java.util.List;

/* loaded from: classes7.dex */
public class HwDeviceConfigManager {

    /* renamed from: a, reason: collision with root package name */
    private static final String f11070a = "HwDeviceConfigManager";
    private static volatile HwDeviceConfigManager b;
    private Context c;

    public static HwDeviceConfigManager getInstance(Context context) {
        if (b == null) {
            synchronized (HwDeviceConfigManager.class) {
                if (b == null) {
                    b = new HwDeviceConfigManager(context.getApplicationContext());
                }
            }
        }
        return b;
    }

    private HwDeviceConfigManager(Context context) {
        this.c = context;
    }

    public void a(String str, int i, boolean z, IPhotoFileCallback iPhotoFileCallback) {
        HwLog.i(f11070a, "startRequestFile() enter.");
        if (Environment.sIsAarInTheme) {
            j.a(this.c).a(str, i, z, iPhotoFileCallback);
        } else {
            HwWatchFaceApi.getInstance(this.c).startRequestFile(str, i, z, iPhotoFileCallback);
        }
    }

    public void a(String str, int i, IBaseResponseCallback iBaseResponseCallback) {
        HwLog.i(f11070a, "commonStopTransfer() enter.");
        if (Environment.sIsAarInTheme) {
            i.a(this.c).a(str, i, iBaseResponseCallback);
        } else {
            HwWatchFaceApi.getInstance(this.c).commonStopTransfer(str, i, iBaseResponseCallback);
        }
    }

    public void a(String str, String str2, int i, IFileTransferStateCallback iFileTransferStateCallback, IAppTransferFileResultAIDLCallback iAppTransferFileResultAIDLCallback) {
        HwLog.i(f11070a, "commonTransferFile() enter.");
        if (Environment.sIsAarInTheme) {
            i.a(this.c).a(str, str2, i, iFileTransferStateCallback);
        } else {
            HwWatchFaceApi.getInstance(this.c).commonTransferFile(str, str2, i, iAppTransferFileResultAIDLCallback);
        }
    }

    public void registerPhotoAndVideoCallback(IAppTransferFileResultAIDLCallback iAppTransferFileResultAIDLCallback, List<Integer> list, TransferFileReqType transferFileReqType) {
        HwLog.i(f11070a, "registerPhotoAndVideoCallback() enter.");
        if (Environment.sIsAarInTheme) {
            i.a(this.c).a(iAppTransferFileResultAIDLCallback);
        } else {
            HwWatchFaceApi.getInstance(this.c).registerPhotoAndVideoCallback(iAppTransferFileResultAIDLCallback, list, transferFileReqType);
        }
    }
}
