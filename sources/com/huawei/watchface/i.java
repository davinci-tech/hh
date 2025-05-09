package com.huawei.watchface;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.Looper;
import android.os.Message;
import android.os.ParcelFileDescriptor;
import android.text.TextUtils;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;
import com.huawei.hihealth.listener.ResultCallback;
import com.huawei.hwdevice.mainprocess.mgr.hwmywatchfacemgr.constant.WatchFaceConstant;
import com.huawei.phoneservice.feedbackcommon.network.FeedbackWebConstants;
import com.huawei.secure.android.common.intent.SafeBroadcastReceiver;
import com.huawei.secure.android.common.util.SafeString;
import com.huawei.watchface.api.HwWatchFaceApi;
import com.huawei.watchface.environment.Environment;
import com.huawei.watchface.mvp.model.datatype.CommandJsonInfo;
import com.huawei.watchface.mvp.model.datatype.CommonFileInfo;
import com.huawei.watchface.mvp.model.datatype.DeviceInfo;
import com.huawei.watchface.mvp.model.datatype.FileTransferParameter;
import com.huawei.watchface.mvp.model.datatype.Tlv;
import com.huawei.watchface.mvp.model.datatype.TlvException;
import com.huawei.watchface.mvp.model.datatype.TlvUtils;
import com.huawei.watchface.mvp.model.latona.provider.WatchFaceProvider;
import com.huawei.watchface.utils.CommonUtils;
import com.huawei.watchface.utils.HwLog;
import com.huawei.watchface.utils.IntegerUtils;
import com.huawei.watchface.utils.callback.IAppTransferFileResultAIDLCallback;
import com.huawei.watchface.utils.callback.IBaseResponseCallback;
import com.huawei.watchface.utils.callback.IFileTransferStateCallback;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArrayList;

/* loaded from: classes7.dex */
public class i {

    /* renamed from: a, reason: collision with root package name */
    private static final String f11046a = "i";
    private static final String b = "watchfacePhoto" + File.separator + WatchFaceProvider.BACKGROUND_LABEL;
    private static final Object c = new Object();
    private static volatile i d;
    private HandlerThread g;
    private b h;
    private Context m;
    private ParcelFileDescriptor n;
    private ResultCallback q;
    private BroadcastReceiver r;
    private int e = -1;
    private FileInputStream f = null;
    private ConcurrentHashMap<Integer, CommonFileInfo> i = new ConcurrentHashMap<>(20);
    private CopyOnWriteArrayList<CommonFileInfo> j = new CopyOnWriteArrayList<>();
    private Queue<CommandJsonInfo> k = new LinkedList();
    private TlvUtils l = new TlvUtils();
    private ConcurrentHashMap<Integer, FileTransferParameter> o = new ConcurrentHashMap<>(20);
    private IAppTransferFileResultAIDLCallback p = null;

    private i(Context context) {
        this.m = context;
        HandlerThread handlerThread = new HandlerThread(f11046a);
        this.g = handlerThread;
        handlerThread.start();
        this.h = new b(this.g.getLooper());
        this.r = new a();
        LocalBroadcastManager.getInstance(this.m).registerReceiver(this.r, new IntentFilter("com.huawei.watchface.action.CONNECTION_STATE_CHANGED"));
    }

    public void a(ResultCallback resultCallback) {
        this.q = resultCallback;
    }

    public void a() {
        if (this.r != null) {
            LocalBroadcastManager.getInstance(this.m).unregisterReceiver(this.r);
        }
        c();
    }

    private static void c() {
        synchronized (i.class) {
            HwLog.i(f11046a, "destroyInstance() enter.");
            d = null;
        }
    }

    public static i a(Context context) {
        if (d == null) {
            synchronized (i.class) {
                if (d == null) {
                    d = new i(context.getApplicationContext());
                }
            }
        }
        return d;
    }

    private void d() {
        for (Map.Entry<Integer, CommonFileInfo> entry : this.i.entrySet()) {
            HwLog.i(f11046a, "reportFailedForDisconnect() fileId:" + entry.getKey());
            if (entry.getValue().getFileCallBack() != null) {
                entry.getValue().getFileCallBack().onUpgradeFailed(141001, "");
            }
            a(entry.getValue());
        }
    }

    public void a(byte[] bArr) {
        if (bArr == null || bArr.length <= 1) {
            return;
        }
        String str = f11046a;
        HwLog.i(str, "handleGeneralFileCallbackResult() message = " + da.a(bArr));
        switch (bArr[1]) {
            case 1:
                b(bArr);
                break;
            case 2:
                c(bArr);
                break;
            case 3:
                d(bArr);
                break;
            case 4:
                e(bArr);
                break;
            case 5:
                f(bArr);
                break;
            case 6:
            default:
                HwLog.i(str, "handleGeneralFileCallbackResult()  default switch " + ((int) bArr[1]));
                break;
            case 7:
                g(bArr);
                break;
            case 8:
                h(bArr);
                break;
            case 9:
                i(bArr);
                break;
        }
    }

    public void a(String str, String str2, int i, IFileTransferStateCallback iFileTransferStateCallback) {
        String str3;
        if (TextUtils.isEmpty(str)) {
            return;
        }
        try {
            str3 = new File(str).getCanonicalPath();
        } catch (IOException e) {
            HwLog.e(f11046a, "startTransfer() IOException :" + HwLog.printException((Exception) e));
            str3 = null;
        }
        if (TextUtils.isEmpty(str3)) {
            return;
        }
        File file = new File(str3);
        if (!file.exists()) {
            HwLog.e(f11046a, "startTransfer() file is not exist");
            iFileTransferStateCallback.onUpgradeFailed(20000, "");
            return;
        }
        int a2 = a(file, -1L);
        if (a2 == 0) {
            HwLog.e(f11046a, "startTransfer() file size is 0");
            iFileTransferStateCallback.onUpgradeFailed(20000, "");
            return;
        }
        CommonFileInfo commonFileInfo = new CommonFileInfo();
        commonFileInfo.setFileName(str2);
        commonFileInfo.setFilePath(str);
        commonFileInfo.setFileType(i);
        commonFileInfo.setFileSize(a2);
        commonFileInfo.setFileCallBack(iFileTransferStateCallback);
        if (!b(commonFileInfo)) {
            this.j.add(commonFileInfo);
        }
        e();
        a(str2, a2, i);
    }

    public void a(String str, int i, IBaseResponseCallback iBaseResponseCallback) {
        for (Map.Entry<Integer, CommonFileInfo> entry : this.i.entrySet()) {
            if (TextUtils.equals(entry.getValue().getFileName(), str) && entry.getValue().getFileType() == i) {
                HwLog.i(f11046a, "stopTransferFile() fileId :" + entry.getKey());
                entry.getValue().setCallback(iBaseResponseCallback);
                c(entry.getKey().intValue());
            }
        }
    }

    public void a(IAppTransferFileResultAIDLCallback iAppTransferFileResultAIDLCallback) {
        this.p = iAppTransferFileResultAIDLCallback;
    }

    private void e() {
        this.e = -1;
        df.a(this.f);
        df.a(this.n);
        this.f = null;
    }

    private void b(byte[] bArr) {
        try {
            List<Tlv> tlvList = this.l.builderTlvList(SafeString.substring(da.a(bArr), CommonUtils.w())).getTlvList();
            if (tlvList != null && tlvList.size() > 0) {
                String str = "";
                int i = 3;
                for (Tlv tlv : tlvList) {
                    int b2 = IntegerUtils.b(tlv.getTag());
                    String value = tlv.getValue();
                    if (b2 == 1) {
                        str = da.b(value);
                        HwLog.i(f11046a, "handleDeviceRequest() file_name :" + str);
                    } else if (b2 == 2) {
                        i = IntegerUtils.b(value);
                        HwLog.i(f11046a, "handleDeviceRequest() file_type:" + i);
                    } else {
                        HwLog.i(f11046a, "handleDeviceRequest() default type:" + b2);
                    }
                }
                if (i != 3 && i != 1) {
                    HwLog.e(f11046a, "handleDeviceRequest() fileType invalid");
                    return;
                } else if (a(i, str)) {
                    a(str, i, 100000);
                    a(str, i);
                    return;
                } else {
                    a(str, i, 140006);
                    return;
                }
            }
            HwLog.e(f11046a, "handleDeviceRequest() tlvs error");
        } catch (TlvException unused) {
            HwLog.e(f11046a, "handleDeviceRequest() error");
        }
    }

    private void a(String str, int i) {
        try {
            String str2 = this.m.getFilesDir().getCanonicalPath() + File.separator + b + File.separator + str;
            String str3 = SafeString.substring(str2, 0, str2.lastIndexOf(".")) + WatchFaceConstant.BIN_SUFFIX;
            if (Environment.sIsAarInTheme) {
                b(str, i, str3);
            } else {
                a(str, i, str3);
            }
        } catch (IOException unused) {
            HwLog.e(f11046a, "deviceStartTransfer() IOException");
        }
    }

    private void a(String str, int i, String str2) {
        HwWatchFaceApi.getInstance(this.m).commonTransferFile(str2, str, i, new IAppTransferFileResultAIDLCallback() { // from class: com.huawei.watchface.i.1
            @Override // com.huawei.watchface.utils.callback.IAppTransferFileResultAIDLCallback
            public void onFileTransferState(int i2, String str3) {
                if (i.this.p != null) {
                    i.this.p.onFileTransferState(i2, "");
                }
            }

            @Override // com.huawei.watchface.utils.callback.IAppTransferFileResultAIDLCallback
            public void onUpgradeFailed(int i2, String str3) {
                HwLog.i(i.f11046a, "deviceStartTransfer() onUpgradeFailed");
                if (i.this.p != null) {
                    i.this.p.onUpgradeFailed(i2, str3);
                }
            }

            @Override // com.huawei.watchface.utils.callback.IAppTransferFileResultAIDLCallback
            public void onFileRespond(int i2, String str3) {
                HwLog.i(i.f11046a, "deviceStartTransfer() onFileRespond");
                if (i.this.p != null) {
                    i.this.p.onFileRespond(i2, "");
                }
            }
        });
    }

    private void b(String str, int i, String str2) {
        a(str2, str, i, new IFileTransferStateCallback() { // from class: com.huawei.watchface.i.2
            @Override // com.huawei.watchface.utils.callback.IFileTransferStateCallback
            public void onFileTransferState(int i2) {
                if (i.this.p != null) {
                    i.this.p.onFileTransferState(i2, "");
                }
            }

            @Override // com.huawei.watchface.utils.callback.IFileTransferStateCallback
            public void onUpgradeFailed(int i2, String str3) {
                HwLog.i(i.f11046a, "deviceStartTransfer() onUpgradeFailed");
                if (i.this.p != null) {
                    i.this.p.onUpgradeFailed(i2, str3);
                }
            }

            @Override // com.huawei.watchface.utils.callback.IFileTransferStateCallback
            public void onFileRespond(int i2) {
                HwLog.i(i.f11046a, "deviceStartTransfer() onFileRespond");
                if (i.this.p != null) {
                    i.this.p.onFileRespond(i2, "");
                }
            }
        });
    }

    private boolean a(int i, String str) {
        if (str == null) {
            HwLog.e(f11046a, "deviceStartTransfer() fileType: " + i + "fileName = null");
            return false;
        }
        return a(str);
    }

    private boolean a(String str) {
        try {
            File file = new File(this.m.getFilesDir().getCanonicalPath() + File.separator + b);
            String str2 = f11046a;
            StringBuilder sb = new StringBuilder("checkFileExistForWatchFace() file exist: ");
            sb.append(file.exists());
            HwLog.i(str2, sb.toString());
            if (!file.isDirectory()) {
                HwLog.w(str2, "checkFileExistForWatchFace() not directory");
                return false;
            }
            File[] listFiles = file.listFiles();
            if (listFiles != null && listFiles.length != 0) {
                for (File file2 : listFiles) {
                    String name = file2.getName();
                    if (name.contains(str) && name.endsWith(".png")) {
                        HwLog.i(f11046a, "checkFileExistForWatchFace() exists");
                        return true;
                    }
                }
                HwLog.i(f11046a, "checkFileExistForWatchFace() not exists");
            }
            return false;
        } catch (IOException unused) {
            HwLog.e(f11046a, "checkFileExistForWatchFace() IOException");
            return false;
        }
    }

    private void a(String str, int i, int i2) {
        String c2 = da.c(str);
        String str2 = CommonUtils.a() + (da.a(1) + da.a(c2.length() / 2) + c2) + (da.a(2) + da.a(1) + da.a(i)) + (da.a(127) + da.a(4) + da.b(i2));
        CommandJsonInfo commandJsonInfo = new CommandJsonInfo();
        commandJsonInfo.setServiceId(40);
        commandJsonInfo.setCommandId(1);
        commandJsonInfo.setCommandType(800);
        commandJsonInfo.setDeviceCommand(str2);
        HwLog.i(f11046a, "reportDeviceRequest() commandJsonInfo: " + commandJsonInfo.toString());
        h.a(this.m, commandJsonInfo, this.q);
    }

    private void c(byte[] bArr) {
        int i;
        int i2;
        int i3;
        int i4;
        String str;
        int i5;
        String a2 = da.a(bArr);
        String str2 = f11046a;
        HwLog.i(str2, "handleAppSend() info: " + a2);
        String substring = SafeString.substring(a2, CommonUtils.w());
        String str3 = "";
        CommonFileInfo commonFileInfo = new CommonFileInfo();
        int i6 = 0;
        try {
            List<Tlv> tlvList = this.l.builderTlvList(substring).getTlvList();
            if (tlvList != null && tlvList.size() > 0) {
                i2 = 0;
                int i7 = 0;
                for (Tlv tlv : tlvList) {
                    try {
                        int b2 = IntegerUtils.b(tlv.getTag());
                        String value = tlv.getValue();
                        if (b2 == 1) {
                            str3 = da.b(value);
                            HwLog.i(f11046a, "handleAppSend() file_name :" + str3);
                        } else if (b2 == 127) {
                            i7 = IntegerUtils.b(value);
                            HwLog.i(f11046a, "handleAppSend() error code :" + i7);
                        } else if (b2 == 3) {
                            i2 = IntegerUtils.b(value);
                            HwLog.i(f11046a, "handleAppSend() file_type :" + i2);
                        } else if (b2 == 4) {
                            i6 = IntegerUtils.b(value);
                            HwLog.i(f11046a, "handleAppSend() file_id :" + i6);
                        } else {
                            HwLog.i(f11046a, "handleAppSend() default type :" + b2);
                        }
                    } catch (TlvException unused) {
                        i = i6;
                        i6 = i7;
                        HwLog.e(f11046a, "handleAppSend() error");
                        i3 = i;
                        i4 = i2;
                        str = str3;
                        i5 = i6;
                        a(str, i4, i3, i5, commonFileInfo);
                    }
                }
                i4 = i2;
                i3 = i6;
                i5 = i7;
                str = str3;
            } else {
                HwLog.e(str2, "handleAppSend() tlvs error");
                str = "";
                i4 = 0;
                i3 = 0;
                i5 = 0;
            }
        } catch (TlvException unused2) {
            i = i6;
            i2 = i;
        }
        a(str, i4, i3, i5, commonFileInfo);
    }

    private void a(String str, int i, int i2, int i3, CommonFileInfo commonFileInfo) {
        Iterator<CommonFileInfo> it = this.j.iterator();
        while (true) {
            if (!it.hasNext()) {
                break;
            }
            CommonFileInfo next = it.next();
            String str2 = f11046a;
            HwLog.i(str2, "handleRequest() has cache file name:" + next.getFileName());
            HwLog.i(str2, "handleRequest() has cache file type:" + next.getFileType());
            if (TextUtils.equals(next.getFileName(), str) && next.getFileType() == i) {
                commonFileInfo.setFilePath(next.getFilePath());
                commonFileInfo.setFileSize(next.getFileSize());
                commonFileInfo.setFileCallBack(next.getFileCallBack());
                commonFileInfo.setFileName(str);
                commonFileInfo.setFileType(i);
                commonFileInfo.setUriId(next.getUriId());
                HwLog.i(str2, "handleRequest() name:" + commonFileInfo.getFileName());
                break;
            }
        }
        if (i3 != 100000) {
            if (commonFileInfo.getFileCallBack() != null) {
                commonFileInfo.getFileCallBack().onUpgradeFailed(i3, "");
                HwLog.i(f11046a, "handleRequest() errorCode :" + i3);
                a(commonFileInfo);
                return;
            }
            HwLog.e(f11046a, "handleRequest() file callBack is null");
            return;
        }
        HwLog.i(f11046a, "handleRequest() device support transfer file");
        commonFileInfo.setFileId(i2);
        this.i.put(Integer.valueOf(commonFileInfo.getFileId()), commonFileInfo);
        f();
    }

    private void a(String str, long j, int i) {
        String str2;
        String c2 = da.c(str);
        String str3 = da.a(1) + da.b(c2.length() / 2) + c2;
        String str4 = da.a(2) + da.a(4) + da.b(j);
        String str5 = da.a(3) + da.a(1) + da.a(i);
        if (i == 1) {
            String[] split = str.split("_");
            if (split.length != 2) {
                HwLog.e(f11046a, "sendFileInfo() deviceCommand error");
                return;
            }
            String c3 = da.c(split[0]);
            String str6 = da.a(5) + da.a(c3.length() / 2) + c3;
            String c4 = da.c(split[1]);
            str2 = str3 + str4 + str5 + str6 + (da.a(6) + da.a(c4.length() / 2) + c4);
            HwLog.i(f11046a, "sendFileInfo() get WatchInfo success");
        } else {
            str2 = str3 + str4 + str5;
        }
        String str7 = CommonUtils.a() + str2;
        CommandJsonInfo commandJsonInfo = new CommandJsonInfo();
        commandJsonInfo.setServiceId(40);
        commandJsonInfo.setCommandId(2);
        commandJsonInfo.setCommandType(800);
        commandJsonInfo.setDeviceCommand(str7);
        HwLog.i(f11046a, "sendFileInfo() commandJsonInfo: " + commandJsonInfo.toString());
        h.a(this.m, commandJsonInfo, this.q);
    }

    private void d(byte[] bArr) {
        String a2 = da.a(bArr);
        String str = f11046a;
        HwLog.i(str, "handleRequestHash() info: " + a2);
        try {
            List<Tlv> tlvList = this.l.builderTlvList(SafeString.substring(a2, CommonUtils.w())).getTlvList();
            if (tlvList != null && tlvList.size() > 0) {
                a(tlvList);
            } else {
                HwLog.e(str, "handleRequestHash() tlvs error");
            }
        } catch (TlvException unused) {
            HwLog.e(f11046a, "handleRequestHash() error");
        }
    }

    private void a(List<Tlv> list) {
        int i = 0;
        int i2 = 0;
        for (Tlv tlv : list) {
            int b2 = IntegerUtils.b(tlv.getTag());
            String value = tlv.getValue();
            if (b2 == 1) {
                i = IntegerUtils.b(value);
                HwLog.i(f11046a, "handleRequestHash() file_id :" + IntegerUtils.b(value));
            } else if (b2 == 2) {
                i2 = IntegerUtils.b(value);
                HwLog.i(f11046a, "handleRequestHash() check_mode:" + IntegerUtils.b(value));
            } else {
                HwLog.i(f11046a, "handleRequestHash() default type:" + b2);
            }
        }
        d(i, i2);
    }

    private void a(int i) {
        String str = CommonUtils.a() + (da.a(1) + da.a(1) + da.a(i)) + (da.a(127) + da.a(4) + da.b(20001L));
        CommandJsonInfo commandJsonInfo = new CommandJsonInfo();
        commandJsonInfo.setServiceId(40);
        commandJsonInfo.setCommandId(3);
        commandJsonInfo.setCommandType(800);
        commandJsonInfo.setDeviceCommand(str);
        HwLog.i(f11046a, "sendFileHashResult() commandJsonInfo: " + commandJsonInfo.toString());
        h.a(this.m, commandJsonInfo, this.q);
    }

    private void e(byte[] bArr) {
        String a2 = da.a(bArr);
        String str = f11046a;
        HwLog.i(str, "handleConsult() info: " + a2);
        String substring = SafeString.substring(a2, CommonUtils.w());
        FileTransferParameter fileTransferParameter = new FileTransferParameter();
        try {
            List<Tlv> tlvList = this.l.builderTlvList(substring).getTlvList();
            if (tlvList != null && tlvList.size() > 0) {
                for (Tlv tlv : tlvList) {
                    a(fileTransferParameter, IntegerUtils.b(tlv.getTag()), tlv.getValue());
                }
                HwLog.i(f11046a, "handleConsult() fileId :" + fileTransferParameter.getFileId());
                this.o.put(Integer.valueOf(fileTransferParameter.getFileId()), fileTransferParameter);
                a(fileTransferParameter.getFileId(), fileTransferParameter.isTransferNotEncrypt());
                return;
            }
            HwLog.e(str, "handleConsult() tlvs error");
        } catch (TlvException unused) {
            HwLog.e(f11046a, "handleConsult() error");
        }
    }

    private void a(FileTransferParameter fileTransferParameter, int i, String str) {
        switch (i) {
            case 1:
                fileTransferParameter.setFileId(IntegerUtils.b(str));
                HwLog.i(f11046a, "handleParamTlv() file_id :" + IntegerUtils.b(str));
                break;
            case 2:
                fileTransferParameter.setFileProtocolVersion(da.b(str));
                HwLog.i(f11046a, "handleParamTlv() protocol version:" + da.b(str));
                break;
            case 3:
                fileTransferParameter.setAppWaitTime(IntegerUtils.b(str));
                HwLog.i(f11046a, "handleParamTlv() app_wait_time:" + IntegerUtils.b(str));
                break;
            case 4:
                fileTransferParameter.setBitmapEnable(IntegerUtils.b(str) == 1);
                HwLog.i(f11046a, "handleParamTlv() bitmap_enable:" + IntegerUtils.b(str));
                break;
            case 5:
                fileTransferParameter.setTransferUnitSize(IntegerUtils.b(str));
                HwLog.i(f11046a, "handleParamTlv() unit_size:" + IntegerUtils.b(str));
                break;
            case 6:
                fileTransferParameter.setMaxApplyDataSize(IntegerUtils.b(str));
                HwLog.i(f11046a, "handleParamTlv() max_apply_data_size:" + IntegerUtils.b(str));
                break;
            case 7:
                fileTransferParameter.setInterval(IntegerUtils.b(str));
                HwLog.i(f11046a, "handleParamTlv() interval:" + IntegerUtils.b(str));
                break;
            case 8:
                fileTransferParameter.setReceivedFileSize(IntegerUtils.b(str));
                HwLog.i(f11046a, "handleParamTlv() received_file_size :" + IntegerUtils.b(str));
                break;
            case 9:
                fileTransferParameter.setTransferNotEncrypt(IntegerUtils.b(str) == 1);
                HwLog.i(f11046a, "handleParamTlv() not need encrypt:" + fileTransferParameter.isTransferNotEncrypt());
                break;
            default:
                HwLog.i(f11046a, "handleParamTlv() default type: " + i + ", value: " + IntegerUtils.b(str));
                break;
        }
    }

    private void a(int i, boolean z) {
        String str;
        String str2 = f11046a;
        HwLog.i(str2, "sendConsultAck() fileId :" + i + ", isEncrypt:" + z);
        String str3 = da.a(1) + da.a(1) + da.a(i);
        String str4 = da.a(127) + da.a(4) + da.b(100000L);
        if (z) {
            str = str4 + str3 + (da.a(9) + da.a(1) + da.a(1));
        } else {
            str = str4 + str3;
        }
        String str5 = CommonUtils.a() + str;
        CommandJsonInfo commandJsonInfo = new CommandJsonInfo();
        commandJsonInfo.setServiceId(40);
        commandJsonInfo.setCommandId(4);
        commandJsonInfo.setCommandType(800);
        commandJsonInfo.setDeviceCommand(str5);
        HwLog.i(str2, "sendConsultAck() commandJsonInfo: " + commandJsonInfo.toString());
        h.a(this.m, commandJsonInfo, this.q);
    }

    private void f(byte[] bArr) {
        String a2 = da.a(bArr);
        HwLog.i(f11046a, "handleDeviceRequestData() info: " + a2);
        String substring = SafeString.substring(a2, CommonUtils.w());
        List<Integer> arrayList = new ArrayList<>(20);
        try {
            List<Tlv> tlvList = this.l.builderTlvList(substring).getTlvList();
            if (tlvList == null || tlvList.size() <= 0) {
                return;
            }
            int i = 0;
            int i2 = 0;
            int i3 = 0;
            for (Tlv tlv : tlvList) {
                int b2 = IntegerUtils.b(tlv.getTag());
                String value = tlv.getValue();
                if (b2 == 1) {
                    i = IntegerUtils.b(value);
                    HwLog.i(f11046a, "handleDeviceRequestData() file_id :" + IntegerUtils.b(value));
                } else if (b2 == 2) {
                    i2 = IntegerUtils.b(value);
                    HwLog.i(f11046a, "handleDeviceRequestData() offset :" + IntegerUtils.b(value));
                } else if (b2 == 3) {
                    i3 = IntegerUtils.b(value);
                    HwLog.i(f11046a, "handleDeviceRequestData() length :" + IntegerUtils.b(value));
                } else if (b2 == 4) {
                    arrayList = cr.a(value);
                    HwLog.i(f11046a, "handleDeviceRequestData() bitmap :" + arrayList.size());
                } else {
                    HwLog.i(f11046a, "handleDeviceRequestData() default :" + b2);
                }
            }
            a(i, i2, i3, arrayList);
        } catch (TlvException unused) {
            HwLog.e(f11046a, "handleDeviceRequestData() error");
        }
    }

    private void a(int i, int i2, int i3, List<Integer> list) {
        if (this.i.containsKey(Integer.valueOf(i))) {
            f();
            b(i, i2, i3, list);
        } else {
            HwLog.i(f11046a, "checkFileId() fileId is not in mTransferingFileList");
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void f() {
        b bVar = this.h;
        if (bVar == null || !bVar.hasMessages(100)) {
            return;
        }
        this.h.removeMessages(100);
    }

    private void a(int i, int i2, int i3, byte[] bArr, boolean z) {
        String a2 = da.a(i);
        String a3 = da.a(i2);
        String b2 = da.b(i3);
        String a4 = da.a(bArr);
        StringBuilder sb = new StringBuilder(32);
        sb.append(a2);
        sb.append(a3);
        sb.append(b2);
        sb.append(a4);
        String str = CommonUtils.a() + sb.toString();
        CommandJsonInfo commandJsonInfo = new CommandJsonInfo();
        commandJsonInfo.setServiceId(40);
        commandJsonInfo.setCommandId(6);
        commandJsonInfo.setCommandType(800);
        commandJsonInfo.setDeviceCommand(str);
        HwLog.i(f11046a, "sendFileHashResult() commandJsonInfo: " + commandJsonInfo.toString());
        synchronized (c) {
            this.k.add(commandJsonInfo);
        }
        g();
    }

    private void g() {
        if (this.h == null) {
            HandlerThread handlerThread = new HandlerThread(f11046a);
            this.g = handlerThread;
            handlerThread.start();
            this.h = new b(this.g.getLooper());
            Message obtain = Message.obtain();
            obtain.what = 400;
            this.h.sendMessage(obtain);
            return;
        }
        Message obtain2 = Message.obtain();
        obtain2.what = 400;
        this.h.sendMessage(obtain2);
    }

    private void g(byte[] bArr) {
        e();
        String a2 = da.a(bArr);
        HwLog.i(f11046a, "handleDeviceResultReport() info: " + a2);
        try {
            List<Tlv> tlvList = this.l.builderTlvList(SafeString.substring(a2, CommonUtils.w())).getTlvList();
            if (tlvList == null || tlvList.size() <= 0) {
                return;
            }
            int i = 0;
            int i2 = -1;
            for (Tlv tlv : tlvList) {
                int b2 = IntegerUtils.b(tlv.getTag());
                String value = tlv.getValue();
                if (b2 == 1) {
                    i = IntegerUtils.b(value);
                    HwLog.i(f11046a, "handleDeviceResultReport() file_id :" + IntegerUtils.b(value));
                } else if (b2 == 2) {
                    i2 = IntegerUtils.b(value);
                    HwLog.i(f11046a, "handleDeviceResultReport() validity_result :" + IntegerUtils.b(value));
                } else {
                    HwLog.i(f11046a, "handleDeviceResultReport() default type:" + b2);
                }
            }
            a(i, i2);
        } catch (TlvException unused) {
            HwLog.e(f11046a, "handleDeviceResultReport() error");
        }
    }

    private void a(int i, int i2) {
        if (this.h == null) {
            HandlerThread handlerThread = new HandlerThread(f11046a);
            this.g = handlerThread;
            handlerThread.start();
            this.h = new b(this.g.getLooper());
            Message obtain = Message.obtain();
            obtain.what = 200;
            obtain.arg1 = i;
            obtain.arg2 = i2;
            this.h.sendMessage(obtain);
            return;
        }
        Message obtain2 = Message.obtain();
        obtain2.what = 200;
        obtain2.arg1 = i;
        obtain2.arg2 = i2;
        this.h.sendMessage(obtain2);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void b(int i) {
        String str = CommonUtils.a() + (da.a(127) + da.a(4) + da.b(100000L)) + (da.a(1) + da.a(1) + da.a(i));
        CommandJsonInfo commandJsonInfo = new CommandJsonInfo();
        commandJsonInfo.setServiceId(40);
        commandJsonInfo.setCommandId(7);
        commandJsonInfo.setCommandType(800);
        commandJsonInfo.setDeviceCommand(str);
        HwLog.i(f11046a, "sendFileHashResult() commandJsonInfo: " + commandJsonInfo.toString());
        h.a(this.m, commandJsonInfo, this.q);
    }

    private void h(byte[] bArr) {
        e();
        String a2 = da.a(bArr);
        String str = f11046a;
        HwLog.i(str, "handleDeviceStatusReport() info: " + a2);
        try {
            List<Tlv> tlvList = this.l.builderTlvList(SafeString.substring(a2, CommonUtils.w())).getTlvList();
            if (tlvList != null && tlvList.size() > 0) {
                int i = 0;
                int i2 = 0;
                for (Tlv tlv : tlvList) {
                    int b2 = IntegerUtils.b(tlv.getTag());
                    String value = tlv.getValue();
                    if (b2 == 1) {
                        i = IntegerUtils.b(value);
                        HwLog.i(f11046a, "handleDeviceStatusReport() file_id :" + IntegerUtils.b(value));
                    } else if (b2 == 127) {
                        i2 = IntegerUtils.b(value);
                        HwLog.i(f11046a, "handleDeviceStatusReport() status :" + IntegerUtils.b(value));
                    } else {
                        HwLog.i(f11046a, "handleDeviceStatusReport() default type" + b2);
                    }
                }
                b(i, i2);
                return;
            }
            HwLog.e(str, "handleDeviceStatusReport() tlvs error");
        } catch (TlvException unused) {
            HwLog.e(f11046a, "handleDeviceStatusReport() error");
        }
    }

    private void b(int i, int i2) {
        if (this.h == null) {
            HandlerThread handlerThread = new HandlerThread(f11046a);
            this.g = handlerThread;
            handlerThread.start();
            this.h = new b(this.g.getLooper());
            Message obtain = Message.obtain();
            obtain.what = 300;
            obtain.arg1 = i;
            obtain.arg2 = i2;
            this.h.sendMessage(obtain);
            return;
        }
        Message obtain2 = Message.obtain();
        obtain2.what = 300;
        obtain2.arg1 = i;
        obtain2.arg2 = i2;
        this.h.sendMessage(obtain2);
    }

    private void c(int i) {
        e();
        String str = CommonUtils.a() + (da.a(1) + da.a(1) + da.a(i));
        CommandJsonInfo commandJsonInfo = new CommandJsonInfo();
        commandJsonInfo.setServiceId(40);
        commandJsonInfo.setCommandId(9);
        commandJsonInfo.setCommandType(800);
        commandJsonInfo.setDeviceCommand(str);
        HwLog.i(f11046a, "sendFileHashResult() commandJsonInfo: " + commandJsonInfo.toString());
        h.a(this.m, commandJsonInfo, this.q);
    }

    private void i(byte[] bArr) {
        String a2 = da.a(bArr);
        String str = f11046a;
        HwLog.i(str, "handleCancelReply() info: " + a2);
        try {
            List<Tlv> tlvList = this.l.builderTlvList(SafeString.substring(a2, CommonUtils.w())).getTlvList();
            if (tlvList != null && tlvList.size() > 0) {
                int i = 0;
                int i2 = 0;
                for (Tlv tlv : tlvList) {
                    int b2 = IntegerUtils.b(tlv.getTag());
                    String value = tlv.getValue();
                    if (b2 == 1) {
                        i = IntegerUtils.b(value);
                        HwLog.i(f11046a, "handleCancelReply() CANCEL_FILE_ID :" + IntegerUtils.b(value));
                    } else if (b2 == 127) {
                        i2 = IntegerUtils.b(value);
                        HwLog.i(f11046a, "handleCancelReply() validity_result :" + IntegerUtils.b(value));
                    } else {
                        HwLog.i(f11046a, "handleCancelReply() default type:" + b2);
                    }
                }
                c(i, i2);
                return;
            }
            HwLog.e(str, "handleCancelReply() tlvs error");
        } catch (TlvException unused) {
            HwLog.e(f11046a, "handleCancelReply() error");
        }
    }

    private void c(int i, int i2) {
        CommonFileInfo commonFileInfo = this.i.get(Integer.valueOf(i));
        if (commonFileInfo == null) {
            HwLog.e(f11046a, "reportCancelResult() commonFileInfo is null.");
            return;
        }
        IBaseResponseCallback callback = commonFileInfo.getCallback();
        if (callback == null) {
            HwLog.e(f11046a, "handleCancelReply() callback is null.");
            return;
        }
        if (i2 == 100000) {
            callback.onResponse(20003, "");
            HwLog.i(f11046a, "handleCancelReply() success");
            f();
            a(this.i.get(Integer.valueOf(i)));
            return;
        }
        callback.onResponse(20004, "");
        HwLog.w(f11046a, "handleCancelReply() failed");
    }

    private void d(int i, int i2) {
        String str = f11046a;
        HwLog.i(str, "sendFileHashToDevice() fileId：" + i + "check_mode : " + i2);
        if (this.i.containsKey(Integer.valueOf(i)) && !TextUtils.isEmpty(this.i.get(Integer.valueOf(i)).getFilePath())) {
            if (new File(this.i.get(Integer.valueOf(i)).getFilePath()).exists()) {
                return;
            }
            HwLog.w(str, "sendFileHashToDevice() file is not exist");
            a(i);
            return;
        }
        HwLog.i(str, "sendFileHashToDevice() mFileInfoList have not or filePath isEmpty");
        a(i);
    }

    private void b(int i, int i2, int i3, List<Integer> list) {
        String str = f11046a;
        HwLog.i(str, "sendDataToDevice() fileId: " + i + ", offset: " + i2 + ", length: " + i3 + ", bitmap.size :" + list.size());
        if (!this.i.containsKey(Integer.valueOf(i))) {
            HwLog.w(str, "sendDataToDevice() found no fileId");
        } else {
            a(i, i2, i3, a(this.i.get(Integer.valueOf(i)), i, i2, i3));
        }
    }

    private void a(int i, int i2, int i3, byte[] bArr) {
        int i4 = i3;
        f(i, i2);
        FileTransferParameter fileTransferParameter = this.o.get(Integer.valueOf(i));
        int transferUnitSize = fileTransferParameter.getTransferUnitSize();
        String str = f11046a;
        HwLog.i(str, "groupDataToDevice() frameLength: " + transferUnitSize);
        boolean isTransferNotEncrypt = fileTransferParameter.isTransferNotEncrypt() ^ true;
        HwLog.i(str, "groupDataToDevice() isNeedEncrypt: " + isTransferNotEncrypt);
        int e = e(i4, transferUnitSize);
        int i5 = i2;
        int i6 = 0;
        int i7 = 0;
        int i8 = 0;
        int i9 = 0;
        while (i7 < e) {
            int a2 = a(i4, transferUnitSize, i8);
            byte[] a3 = a(i9, a2, bArr);
            int i10 = i6 > 255 ? 0 : i6;
            int i11 = i10;
            a(i, i10, i5, a3, isTransferNotEncrypt);
            StringBuilder sb = new StringBuilder(32);
            sb.append("transferData psnID ");
            sb.append(i11);
            sb.append("length = ");
            sb.append(a2);
            sb.append("ota_offset = ");
            sb.append(i5);
            sb.append("sended_length = ");
            sb.append(i8);
            HwLog.i(f11046a, sb.toString());
            i8 += a2;
            i5 += a2;
            i9 += a2;
            i6 = i11 + 1;
            i7++;
            i4 = i3;
        }
        int appWaitTime = this.o.get(Integer.valueOf(i)).getAppWaitTime();
        HwLog.i(f11046a, "groupDataToDevice() mWaitTimeout: " + appWaitTime);
        g(i, appWaitTime);
    }

    private int e(int i, int i2) {
        int i3;
        if (i % i2 == 0) {
            i3 = i / i2;
        } else {
            i3 = (i / i2) + 1;
        }
        HwLog.i(f11046a, "getFileNumber() file_array = " + i3);
        return i3;
    }

    private byte[] a(int i, int i2, byte[] bArr) {
        byte[] bArr2 = new byte[i2];
        try {
            System.arraycopy(bArr, i, bArr2, 0, i2);
        } catch (ArrayIndexOutOfBoundsException e) {
            HwLog.e(f11046a, "getSendBytes() ArrayIndexOutOfBoundsException = " + HwLog.printException((Exception) e));
        }
        return bArr2;
    }

    private int a(int i, int i2, int i3) {
        if ((i - i3) / i2 != 0) {
            HwLog.i(f11046a, "getSendLength() send max, size：" + i2);
            return i2;
        }
        int i4 = i % i2;
        HwLog.i(f11046a, "getSendLength() send not max, size = " + i4);
        return i4;
    }

    private void f(int i, int i2) {
        CommonFileInfo commonFileInfo = this.i.get(Integer.valueOf(i));
        if (commonFileInfo == null) {
            HwLog.e(f11046a, "reportProgressForUi() commonFileInfo is null.");
            return;
        }
        IFileTransferStateCallback fileCallBack = commonFileInfo.getFileCallBack();
        if (fileCallBack == null) {
            HwLog.e(f11046a, "reportProgressForUi() callback is null.");
            return;
        }
        int fileSize = commonFileInfo.getFileSize();
        if (fileSize != 0) {
            long j = (i2 * 100) / fileSize;
            if (j < -2147483648L || j > 2147483647L) {
                HwLog.i(f11046a, "reportProgressForUi() is not Integer.");
                return;
            }
            int i3 = (int) j;
            HwLog.i(f11046a, "reportProgressForUi() fileSize :" + fileSize + " ; offset :" + i2 + " ; progress :" + i3);
            fileCallBack.onFileTransferState(i3);
        }
    }

    private void g(int i, int i2) {
        if (this.h != null) {
            if (i2 != 0) {
                Message obtain = Message.obtain();
                obtain.what = 100;
                obtain.arg1 = i;
                this.h.sendMessageDelayed(obtain, i2 * 1000);
                return;
            }
            return;
        }
        HandlerThread handlerThread = new HandlerThread(f11046a);
        this.g = handlerThread;
        handlerThread.start();
        this.h = new b(this.g.getLooper());
        if (i2 != 0) {
            Message obtain2 = Message.obtain();
            obtain2.what = 100;
            obtain2.arg1 = i;
            this.h.sendMessageDelayed(obtain2, i2 * 1000);
        }
    }

    private byte[] a(CommonFileInfo commonFileInfo, int i, int i2, int i3) {
        boolean z;
        if (commonFileInfo == null) {
            HwLog.w(f11046a, "getFileByPath() info is null.");
            return new byte[0];
        }
        if (i3 > 10485760) {
            HwLog.w(f11046a, "getFileByPath() length is illegal.");
            return new byte[0];
        }
        String filePath = commonFileInfo.getFilePath();
        long uriId = commonFileInfo.getUriId();
        if (!b(filePath)) {
            return new byte[0];
        }
        if (this.e != i || this.f == null) {
            HwLog.w(f11046a, "getFileByPath() fileId is not same or stream is null.");
            e();
            this.e = i;
            this.f = a(filePath, uriId);
            z = true;
        } else {
            z = false;
        }
        if (this.f == null) {
            HwLog.w(f11046a, "getFileByPath() error.");
            df.a(this.n);
            return new byte[0];
        }
        byte[] bArr = new byte[i3];
        long j = 0;
        if (z) {
            try {
                HwLog.i(f11046a, "getFileByPath() set read position.");
                j = this.f.skip(i2);
            } catch (IOException unused) {
                HwLog.e(f11046a, "getFileByPath() IOException.");
            }
        }
        int read = this.f.read(bArr);
        HwLog.i(f11046a, "getFileByPath() readFileSize: " + read);
        if (j != -1) {
            return bArr;
        }
        HwLog.w(f11046a, "getFileByPath() set read position occur error.");
        return new byte[0];
    }

    private boolean b(String str) {
        return str == null || str.indexOf(FeedbackWebConstants.INVALID_FILE_NAME_PRE) < 0;
    }

    private int a(File file, long j) {
        int i = 0;
        if (file == null) {
            return 0;
        }
        if (file.exists()) {
            FileInputStream a2 = a(file.getPath(), j);
            if (a2 == null) {
                HwLog.e(f11046a, "getFileSize() error.");
                df.a(this.n);
                return 0;
            }
            try {
                try {
                    i = a2.available();
                } catch (IOException e) {
                    HwLog.e(f11046a, "getFileSize() IOException " + HwLog.printException((Exception) e));
                }
            } finally {
                df.a(a2);
                df.a(this.n);
            }
        }
        return i;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a(CommonFileInfo commonFileInfo) {
        if (commonFileInfo == null) {
            HwLog.w(f11046a, "handleFailed() error, fileInfo is null");
            return;
        }
        int i = 0;
        while (true) {
            if (i >= this.j.size()) {
                break;
            }
            CommonFileInfo commonFileInfo2 = this.j.get(i);
            String str = f11046a;
            HwLog.i(str, "handleFailed() has cache file name:" + commonFileInfo2.getFileName());
            HwLog.i(str, "handleFailed() has cache file type:" + commonFileInfo2.getFileType());
            if (TextUtils.equals(commonFileInfo2.getFileName(), commonFileInfo.getFileName()) && commonFileInfo2.getFileType() == commonFileInfo.getFileType()) {
                HwLog.i(str, "handleFailed() delete commonFileInfo.name:" + commonFileInfo2.getFileName());
                this.j.remove(commonFileInfo2);
                break;
            }
            i++;
        }
        if (this.i.get(Integer.valueOf(commonFileInfo.getFileId())) != null) {
            HwLog.i(f11046a, "handleFailed() has fileInfo file id:" + commonFileInfo.getFileId());
            this.i.remove(Integer.valueOf(commonFileInfo.getFileId()));
        }
        if (this.o.get(Integer.valueOf(commonFileInfo.getFileId())) != null) {
            HwLog.i(f11046a, "handleFailed() has fileInfo file id:" + commonFileInfo.getFileId());
            this.o.remove(Integer.valueOf(commonFileInfo.getFileId()));
        }
    }

    /* JADX WARN: Code restructure failed: missing block: B:12:0x004b, code lost:
    
        r4 = null;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private java.io.FileInputStream a(java.lang.String r3, long r4) {
        /*
            r2 = this;
            r0 = -1
            int r0 = (r4 > r0 ? 1 : (r4 == r0 ? 0 : -1))
            if (r0 != 0) goto L10
            java.io.FileInputStream r4 = new java.io.FileInputStream     // Catch: java.io.FileNotFoundException -> L32
            java.lang.String r3 = com.huawei.watchface.utils.CommonUtils.filterFilePath(r3)     // Catch: java.io.FileNotFoundException -> L32
            r4.<init>(r3)     // Catch: java.io.FileNotFoundException -> L32
            goto L4b
        L10:
            android.net.Uri r3 = android.provider.MediaStore.Audio.Media.EXTERNAL_CONTENT_URI     // Catch: java.io.FileNotFoundException -> L32
            android.net.Uri r3 = android.content.ContentUris.withAppendedId(r3, r4)     // Catch: java.io.FileNotFoundException -> L32
            android.content.Context r4 = r2.m     // Catch: java.io.FileNotFoundException -> L32
            android.content.ContentResolver r4 = r4.getContentResolver()     // Catch: java.io.FileNotFoundException -> L32
            java.lang.String r5 = "r"
            android.os.ParcelFileDescriptor r3 = r4.openFileDescriptor(r3, r5)     // Catch: java.io.FileNotFoundException -> L32
            r2.n = r3     // Catch: java.io.FileNotFoundException -> L32
            if (r3 == 0) goto L4a
            java.io.FileInputStream r4 = new java.io.FileInputStream     // Catch: java.io.FileNotFoundException -> L32
            android.os.ParcelFileDescriptor r3 = r2.n     // Catch: java.io.FileNotFoundException -> L32
            java.io.FileDescriptor r3 = r3.getFileDescriptor()     // Catch: java.io.FileNotFoundException -> L32
            r4.<init>(r3)     // Catch: java.io.FileNotFoundException -> L32
            goto L4b
        L32:
            r3 = move-exception
            java.lang.String r4 = com.huawei.watchface.i.f11046a
            java.lang.StringBuilder r5 = new java.lang.StringBuilder
            java.lang.String r0 = "getFileInputStreamByUriId() FileNotFoundException: "
            r5.<init>(r0)
            java.lang.String r3 = com.huawei.watchface.utils.HwLog.printException(r3)
            r5.append(r3)
            java.lang.String r3 = r5.toString()
            com.huawei.watchface.utils.HwLog.e(r4, r3)
        L4a:
            r4 = 0
        L4b:
            return r4
        */
        throw new UnsupportedOperationException("Method not decompiled: com.huawei.watchface.i.a(java.lang.String, long):java.io.FileInputStream");
    }

    private boolean b(CommonFileInfo commonFileInfo) {
        Iterator<CommonFileInfo> it = this.j.iterator();
        boolean z = false;
        while (it.hasNext()) {
            CommonFileInfo next = it.next();
            if (TextUtils.equals(commonFileInfo.getFileName(), next.getFileName()) && commonFileInfo.getFileType() == next.getFileType()) {
                z = true;
            }
        }
        return z;
    }

    class b extends Handler {
        b(Looper looper) {
            super(looper);
        }

        @Override // android.os.Handler
        public void handleMessage(Message message) {
            super.handleMessage(message);
            int i = message.what;
            if (i == 100) {
                HwLog.i(i.f11046a, "handleMessage() wait timeout!");
                int i2 = message.arg1;
                HwLog.i(i.f11046a, "handleMessage() fileId:" + i2);
                i.this.h(i2, 141000);
                i iVar = i.this;
                iVar.a((CommonFileInfo) iVar.i.get(Integer.valueOf(i2)));
                return;
            }
            if (i == 200) {
                HwLog.i(i.f11046a, "handleMessage() receive result!");
                int i3 = message.arg1;
                int i4 = message.arg2;
                HwLog.i(i.f11046a, "handleMessage() fileId :" + i3 + "; result :" + i4);
                i.this.b(i3);
                if (i.this.i.get(Integer.valueOf(i3)) == null || ((CommonFileInfo) i.this.i.get(Integer.valueOf(i3))).getFileCallBack() == null) {
                    return;
                }
                if (i4 == 1) {
                    ((CommonFileInfo) i.this.i.get(Integer.valueOf(i3))).getFileCallBack().onFileTransferState(100);
                }
                ((CommonFileInfo) i.this.i.get(Integer.valueOf(i3))).getFileCallBack().onFileRespond(i4);
                HwLog.i(i.f11046a, "handleMessage() receive result! onFileRespond");
                i.this.f();
                i iVar2 = i.this;
                iVar2.a((CommonFileInfo) iVar2.i.get(Integer.valueOf(i3)));
                return;
            }
            if (i != 300) {
                if (i != 400) {
                    HwLog.i(i.f11046a, "handleMessage() default msg.what:" + message.what);
                    return;
                }
                i.this.h();
                return;
            }
            HwLog.i(i.f11046a, "handleMessage() device report error!");
            int i5 = message.arg1;
            int i6 = message.arg2;
            HwLog.i(i.f11046a, "handleMessage() fileId :" + i5 + "; result :" + i6);
            i.this.h(i5, i6);
            i iVar3 = i.this;
            iVar3.a((CommonFileInfo) iVar3.i.get(Integer.valueOf(i5)));
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void h() {
        ArrayList arrayList = new ArrayList(32);
        synchronized (c) {
            CommandJsonInfo poll = this.k.poll();
            while (poll != null) {
                arrayList.add(poll);
                poll = this.k.poll();
            }
        }
        Iterator it = arrayList.iterator();
        while (it.hasNext()) {
            h.a(this.m, (CommandJsonInfo) it.next(), this.q);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void h(int i, int i2) {
        CommonFileInfo commonFileInfo = this.i.get(Integer.valueOf(i));
        if (commonFileInfo == null) {
            HwLog.e(f11046a, "reportFailedForUi() commonFileInfo is null.");
            return;
        }
        IFileTransferStateCallback fileCallBack = commonFileInfo.getFileCallBack();
        if (fileCallBack == null) {
            HwLog.e(f11046a, "reportFailedForUi() callback is null.");
            return;
        }
        fileCallBack.onUpgradeFailed(i2, "");
        HwLog.i(f11046a, "reportFailedForUi() fileId :" + i + "errorCode : " + i2);
    }

    static class a extends SafeBroadcastReceiver {
        private a() {
        }

        @Override // com.huawei.secure.android.common.intent.SafeBroadcastReceiver
        public void onReceiveMsg(Context context, Intent intent) {
            DeviceInfo deviceInfo;
            if (context == null) {
                return;
            }
            if (intent == null || intent.getExtras() == null || TextUtils.isEmpty(intent.getAction())) {
                HwLog.w(i.f11046a, "CommonFileBroadCastReceiver() intent is null.");
                return;
            }
            if (!"com.huawei.watchface.action.CONNECTION_STATE_CHANGED".equals(intent.getAction()) || (deviceInfo = (DeviceInfo) intent.getParcelableExtra("deviceinfo")) == null) {
                return;
            }
            HwLog.i(i.f11046a, "CommonFileBroadCastReceiver() status = " + deviceInfo.getDeviceConnectState());
            i.b(deviceInfo);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static void b(DeviceInfo deviceInfo) {
        int deviceConnectState = deviceInfo.getDeviceConnectState();
        if (deviceConnectState == 2) {
            HwLog.i(f11046a, "CommonFileBroadCastReceiver() DEVICE_CONNECTED");
            return;
        }
        if (deviceConnectState != 3) {
            return;
        }
        HwLog.i(f11046a, "CommonFileBroadCastReceiver() DEVICE_DISCONNECTED");
        if (d == null) {
            return;
        }
        d.d();
        d.f();
    }
}
