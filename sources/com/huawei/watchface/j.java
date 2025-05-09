package com.huawei.watchface;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.Looper;
import android.os.Message;
import android.text.TextUtils;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;
import com.huawei.hihealth.listener.ResultCallback;
import com.huawei.secure.android.common.intent.SafeBroadcastReceiver;
import com.huawei.secure.android.common.util.SafeString;
import com.huawei.skinner.internal.OnRegisterSkinAttrsListener;
import com.huawei.watchface.mvp.model.datatype.CommandJsonInfo;
import com.huawei.watchface.mvp.model.datatype.CommonFileInfo;
import com.huawei.watchface.mvp.model.datatype.CommonFileRetryInfo;
import com.huawei.watchface.mvp.model.datatype.DeviceInfo;
import com.huawei.watchface.mvp.model.datatype.FileTransferParameter;
import com.huawei.watchface.mvp.model.datatype.Tlv;
import com.huawei.watchface.mvp.model.datatype.TlvException;
import com.huawei.watchface.mvp.model.datatype.TlvUtils;
import com.huawei.watchface.utils.CommonUtils;
import com.huawei.watchface.utils.FileHelper;
import com.huawei.watchface.utils.HwLog;
import com.huawei.watchface.utils.IntegerUtils;
import com.huawei.watchface.utils.callback.IBaseCommonCallback;
import com.huawei.watchface.utils.callback.IPhotoFileCallback;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.nio.ByteBuffer;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArrayList;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes7.dex */
public class j {

    /* renamed from: a, reason: collision with root package name */
    private static final String f11050a = "j";
    private static volatile j b;
    private HandlerThread c;
    private b d;
    private ResultCallback e;
    private Context j;
    private BroadcastReceiver l;
    private ConcurrentHashMap<Integer, CommonFileInfo> f = new ConcurrentHashMap<>(20);
    private CopyOnWriteArrayList<CommonFileInfo> g = new CopyOnWriteArrayList<>();
    private ConcurrentHashMap<Integer, CommonFileRetryInfo> h = new ConcurrentHashMap<>(20);
    private TlvUtils i = new TlvUtils();
    private ConcurrentHashMap<Integer, FileTransferParameter> k = new ConcurrentHashMap<>(20);

    private j(Context context) {
        this.j = context;
        HandlerThread handlerThread = new HandlerThread(f11050a);
        this.c = handlerThread;
        handlerThread.start();
        this.d = new b(this.c.getLooper());
        this.l = new a();
        LocalBroadcastManager.getInstance(this.j).registerReceiver(this.l, new IntentFilter("com.huawei.watchface.action.CONNECTION_STATE_CHANGED"));
    }

    private void c() {
        this.g.clear();
    }

    private void d() {
        String str = f11050a;
        HwLog.i(str, "removeKit() size:" + this.g.size());
        if (this.g.size() <= 0 || this.g.get(0).getKitCallback() == null) {
            return;
        }
        HwLog.i(str, "removeKit() kit callback");
        this.g.remove(0);
        g();
    }

    public void a(ResultCallback resultCallback) {
        this.e = resultCallback;
    }

    public void a() {
        if (this.l != null) {
            LocalBroadcastManager.getInstance(this.j).unregisterReceiver(this.l);
        }
        e();
    }

    private static void e() {
        synchronized (j.class) {
            HwLog.i(f11050a, "destroyInstance() enter.");
            b = null;
        }
    }

    public static j a(Context context) {
        if (b == null) {
            synchronized (j.class) {
                if (b == null) {
                    b = new j(context.getApplicationContext());
                }
            }
        }
        return b;
    }

    private void f() {
        for (Map.Entry<Integer, CommonFileInfo> entry : this.f.entrySet()) {
            HwLog.i(f11050a, "reportFailedForDisconnect() fileId: " + entry.getKey());
            a(entry);
            if (entry.getValue().getFileRequestCallBack() != null) {
                entry.getValue().getFileRequestCallBack().onFailure(30004, "");
            }
            c(entry.getValue());
        }
    }

    public void a(byte[] bArr) {
        if (bArr == null || bArr.length <= 1) {
            return;
        }
        String str = f11050a;
        HwLog.i(str, "handleGeneralFileCallbackResult() message: " + da.a(bArr));
        byte b2 = bArr[1];
        if (b2 == 1) {
            b(bArr);
            return;
        }
        if (b2 == 2) {
            c(bArr);
            return;
        }
        if (b2 == 3) {
            d(bArr);
            return;
        }
        if (b2 == 5) {
            e(bArr);
        } else if (b2 == 6) {
            f(bArr);
        } else {
            HwLog.i(str, "handleGeneralFileCallbackResult() default");
        }
    }

    public void a(String str, int i, boolean z, IPhotoFileCallback iPhotoFileCallback) {
        CommonFileInfo commonFileInfo = new CommonFileInfo();
        commonFileInfo.setFileName(str);
        commonFileInfo.setFileType(i);
        commonFileInfo.setNeedVerify(z);
        commonFileInfo.setFileRequestCallBack(iPhotoFileCallback);
        if (!a(commonFileInfo)) {
            this.g.add(commonFileInfo);
        }
        a(str, i);
    }

    private void g() {
        if (this.g.size() > 0) {
            HwLog.i(f11050a, "requestNextTask() request next task");
            CommonFileInfo commonFileInfo = this.g.get(0);
            a(commonFileInfo.getFileName(), commonFileInfo.getFileType(), commonFileInfo.getStartTime(), commonFileInfo.getEndTime());
            return;
        }
        HwLog.i(f11050a, "requestNextTask() task is empty.");
    }

    private boolean a(CommonFileInfo commonFileInfo) {
        Iterator<CommonFileInfo> it = this.g.iterator();
        boolean z = false;
        while (it.hasNext()) {
            CommonFileInfo next = it.next();
            if (TextUtils.equals(commonFileInfo.getFileName(), next.getFileName()) && commonFileInfo.getFileType() == next.getFileType()) {
                z = true;
            }
        }
        return z;
    }

    private void a(String str, int i, int i2, int i3) {
        StringBuilder sb = new StringBuilder(32);
        if (!TextUtils.isEmpty(str)) {
            String c = da.c(str);
            sb.append(da.a(1) + da.a(c.length() / 2) + c);
        } else {
            HwLog.i(f11050a, "sendFileInfo() file name is empty.");
        }
        sb.append(da.a(2) + da.a(1) + da.a(i));
        if (i2 != -1 && i3 != -1) {
            sb.append(da.a(5));
            sb.append(da.a(4));
            sb.append(da.b(i2));
            sb.append(da.a(6));
            sb.append(da.a(4));
            sb.append(da.b(i3));
        } else {
            HwLog.i(f11050a, "sendFileInfo() startTime or endTime is -1, startTime:" + i2 + ", end time:" + i3);
        }
        String str2 = CommonUtils.a() + sb.toString();
        CommandJsonInfo commandJsonInfo = new CommandJsonInfo();
        commandJsonInfo.setServiceId(44);
        commandJsonInfo.setCommandId(1);
        commandJsonInfo.setCommandType(800);
        commandJsonInfo.setDeviceCommand(str2);
        HwLog.i(f11050a, "sendFileInfo() commandJsonInfo: " + commandJsonInfo.toString());
        h.a(this.j, commandJsonInfo, this.e);
        a(i);
        b(i);
    }

    private void a(int i) {
        this.h.put(Integer.valueOf(i), new CommonFileRetryInfo());
    }

    private void a(String str, int i) {
        String c = da.c(str);
        String str2 = CommonUtils.a() + (da.a(1) + da.a(c.length() / 2) + c) + (da.a(2) + da.a(1) + da.a(i));
        CommandJsonInfo commandJsonInfo = new CommandJsonInfo();
        commandJsonInfo.setServiceId(44);
        commandJsonInfo.setCommandId(1);
        commandJsonInfo.setCommandType(800);
        commandJsonInfo.setDeviceCommand(str2);
        HwLog.i(f11050a, "sendFileInfo() commandJsonInfo: " + commandJsonInfo.toString());
        h.a(this.j, commandJsonInfo, this.e);
        a(i);
    }

    private void b(int i) {
        if (this.d == null) {
            HandlerThread handlerThread = new HandlerThread(f11050a);
            this.c = handlerThread;
            handlerThread.start();
            this.d = new b(this.c.getLooper());
        }
        Message obtain = Message.obtain();
        obtain.arg1 = 500;
        obtain.what = i;
        this.d.sendMessageDelayed(obtain, 35000L);
    }

    private void h() {
        HwLog.i(f11050a, "removeFileInfoTimeout() enter.");
        if (this.g.size() > 0) {
            f(this.g.get(0).getFileType());
        }
    }

    private void b(byte[] bArr) {
        h();
        String a2 = da.a(bArr);
        HwLog.i(f11050a, "handleRequestCommand() info: " + a2);
        a(SafeString.substring(a2, CommonUtils.w()));
    }

    private void a(String str) {
        int i;
        int i2;
        int i3;
        int i4;
        String str2;
        int i5;
        int i6;
        int i7;
        String str3 = null;
        int i8 = 0;
        try {
            List<Tlv> tlvList = this.i.builderTlvList(str).getTlvList();
            if (tlvList != null && tlvList.size() > 0) {
                i2 = 0;
                i3 = 0;
                int i9 = 0;
                for (Tlv tlv : tlvList) {
                    try {
                        int b2 = IntegerUtils.b(tlv.getTag(), 16);
                        String value = tlv.getValue();
                        if (b2 == 1) {
                            str3 = da.b(value);
                            HwLog.i(f11050a, "handleRequestTlv() file_name:" + str3);
                        } else if (b2 == 2) {
                            i8 = IntegerUtils.b(value, 16);
                            HwLog.i(f11050a, "handleRequestTlv() file_type:" + i8);
                        } else if (b2 == 3) {
                            i2 = IntegerUtils.b(value, 16);
                            HwLog.i(f11050a, "handleRequestTlv() file_id:" + i2);
                        } else if (b2 == 4) {
                            i3 = IntegerUtils.b(value, 16);
                            HwLog.i(f11050a, "handleRequestTlv() file_size:" + i3);
                        } else if (b2 == 127) {
                            i9 = IntegerUtils.b(value, 16);
                            HwLog.i(f11050a, "handleRequestTlv() error code:" + i9);
                        } else {
                            HwLog.i(f11050a, "handleRequestTlv() default");
                        }
                    } catch (TlvException unused) {
                        i = i8;
                        i8 = i9;
                        HwLog.e(f11050a, "handleRequestTlv() error");
                        i4 = i;
                        str2 = str3;
                        i5 = i8;
                        i6 = i2;
                        i7 = i3;
                        a(str2, i4, i6, i7, i5);
                    }
                }
                str2 = str3;
                i4 = i8;
                i6 = i2;
                i7 = i3;
                i5 = i9;
            } else {
                HwLog.w(f11050a, "handleRequestTlv() tlv list error");
                str2 = null;
                i4 = 0;
                i6 = 0;
                i7 = 0;
                i5 = 0;
            }
        } catch (TlvException unused2) {
            i = i8;
            i2 = i;
            i3 = i2;
        }
        a(str2, i4, i6, i7, i5);
    }

    private void a(String str, int i, int i2, int i3, int i4) {
        CommonFileInfo commonFileInfo = new CommonFileInfo();
        Iterator<CommonFileInfo> it = this.g.iterator();
        while (it.hasNext()) {
            CommonFileInfo next = it.next();
            String str2 = f11050a;
            HwLog.i(str2, "handleRequestFunc() has cache file name:" + next.getFileName() + ", cache type:" + next.getFileType() + ", file id:" + i2 + ", file type:" + i + ", file name:" + str + ", need verify:" + next.isNeedVerify());
            if (a(next.getFileName(), str) && (next.getFileType() == i || i == 0)) {
                commonFileInfo.setFileSize(i3);
                commonFileInfo.setFileRequestCallBack(next.getFileRequestCallBack());
                commonFileInfo.setKitCallback(next.getKitCallback());
                commonFileInfo.setFileName(str);
                commonFileInfo.setFileType(i);
                commonFileInfo.setNeedVerify(next.isNeedVerify());
                HwLog.i(str2, "handleRequestFunc() commonFileInfo.name:" + commonFileInfo.getFileName());
                break;
            }
            if (str == null && next.getFileName() == null && i4 != 100000) {
                commonFileInfo.setKitCallback(next.getKitCallback());
            }
        }
        if (i4 != 100000) {
            a(commonFileInfo, i4);
            if (commonFileInfo.getFileCallBack() != null) {
                commonFileInfo.getFileRequestCallBack().onFailure(i4, "");
                HwLog.i(f11050a, "handleRequestFunc() errorCode:" + i4);
                c(commonFileInfo);
                return;
            }
            HwLog.i(f11050a, "handleRequestFunc() file callback is null");
            return;
        }
        a(i2, commonFileInfo);
    }

    private void a(int i, CommonFileInfo commonFileInfo) {
        String str = f11050a;
        HwLog.i(str, "beginTransferFile() device support transfer file");
        if (commonFileInfo.getFileType() == 0) {
            HwLog.w(str, "beginTransferFile() no task deal with this tlv.");
            return;
        }
        commonFileInfo.setFileId(i);
        this.f.put(Integer.valueOf(commonFileInfo.getFileId()), commonFileInfo);
        HwLog.i(str, "beginTransferFile() reset info:file id:" + commonFileInfo.getFileId() + ", fileType:" + commonFileInfo.getFileType() + ", filename:" + commonFileInfo.getFileName() + ", NeedVerify:" + commonFileInfo.isNeedVerify());
        if (commonFileInfo.isNeedVerify()) {
            a(commonFileInfo.getFileId(), 1);
        } else {
            c(commonFileInfo.getFileId());
        }
    }

    private boolean a(String str, String str2) {
        return TextUtils.equals(str, str2) || TextUtils.isEmpty(str2) || TextUtils.isEmpty(str);
    }

    private void a(int i, int i2) {
        String str = CommonUtils.a() + (da.a(1) + da.a(1) + da.a(i)) + (da.a(2) + da.a(1) + da.a(i2));
        CommandJsonInfo commandJsonInfo = new CommandJsonInfo();
        commandJsonInfo.setServiceId(44);
        commandJsonInfo.setCommandId(2);
        commandJsonInfo.setCommandType(800);
        commandJsonInfo.setDeviceCommand(str);
        HwLog.i(f11050a, "sendFileCheck() commandJsonInfo: " + commandJsonInfo.toString());
        h.a(this.j, commandJsonInfo, this.e);
    }

    private void c(byte[] bArr) {
        String a2 = da.a(bArr);
        String str = f11050a;
        HwLog.i(str, "handleRequestHash() info: " + a2);
        try {
            List<Tlv> tlvList = this.i.builderTlvList(SafeString.substring(a2, CommonUtils.w())).getTlvList();
            if (tlvList != null && tlvList.size() > 0) {
                int i = 0;
                String str2 = "";
                for (Tlv tlv : tlvList) {
                    int b2 = IntegerUtils.b(tlv.getTag(), 16);
                    String value = tlv.getValue();
                    if (b2 == 1) {
                        i = IntegerUtils.b(value, 16);
                        HwLog.i(f11050a, "handleRequestHash() file id:" + i);
                    } else if (b2 != 3) {
                        HwLog.i(f11050a, "handleRequestHash() default");
                    } else {
                        str2 = value;
                    }
                }
                a(i, str2);
                return;
            }
            HwLog.w(str, "handleRequestHash() tlv list error");
        } catch (TlvException unused) {
            HwLog.e(f11050a, "handleRequestHash() error");
        }
    }

    private void a(int i, String str) {
        if (this.f.get(Integer.valueOf(i)) != null) {
            this.f.get(Integer.valueOf(i)).setHashValue(str);
            c(i);
        } else {
            HwLog.i(f11050a, "saveFileHash() error");
        }
    }

    private void c(int i) {
        String str = CommonUtils.a() + (da.a(1) + da.a(1) + da.a(i)) + (da.a(2) + da.a(0)) + (da.a(3) + da.a(0)) + (da.a(4) + da.a(0)) + (da.a(5) + da.a(0));
        CommandJsonInfo commandJsonInfo = new CommandJsonInfo();
        commandJsonInfo.setServiceId(44);
        commandJsonInfo.setCommandId(3);
        commandJsonInfo.setCommandType(800);
        commandJsonInfo.setDeviceCommand(str);
        HwLog.i(f11050a, "sendFileHashResult() commandJsonInfo: " + commandJsonInfo.toString());
        h.a(this.j, commandJsonInfo, this.e);
    }

    private void d(byte[] bArr) {
        String a2 = da.a(bArr);
        String str = f11050a;
        HwLog.i(str, "handleConsult() info: " + a2);
        String substring = SafeString.substring(a2, CommonUtils.w());
        FileTransferParameter fileTransferParameter = new FileTransferParameter();
        try {
            List<Tlv> tlvList = this.i.builderTlvList(substring).getTlvList();
            if (tlvList != null && tlvList.size() > 0) {
                for (Tlv tlv : tlvList) {
                    a(fileTransferParameter, IntegerUtils.b(tlv.getTag(), 16), tlv.getValue());
                }
                String str2 = f11050a;
                HwLog.i(str2, "handleConsult() fileId:" + fileTransferParameter.getFileId());
                int maxApplyDataSize = fileTransferParameter.getMaxApplyDataSize() % fileTransferParameter.getTransferUnitSize() == 0 ? (fileTransferParameter.getMaxApplyDataSize() / fileTransferParameter.getTransferUnitSize()) - 1 : fileTransferParameter.getMaxApplyDataSize() / fileTransferParameter.getTransferUnitSize();
                fileTransferParameter.setPackageNumberMax(maxApplyDataSize);
                HwLog.i(str2, "handleConsult() psnMax:" + maxApplyDataSize);
                this.k.put(Integer.valueOf(fileTransferParameter.getFileId()), fileTransferParameter);
                if (this.f.get(Integer.valueOf(fileTransferParameter.getFileId())) != null) {
                    d(fileTransferParameter.getFileId());
                    a(fileTransferParameter, this.f.get(Integer.valueOf(fileTransferParameter.getFileId())));
                    return;
                }
                return;
            }
            HwLog.w(str, "handleConsult() tlv list error");
        } catch (TlvException unused) {
            HwLog.e(f11050a, "handleConsult() error");
        }
    }

    private void a(FileTransferParameter fileTransferParameter, int i, String str) {
        if (i == 1) {
            fileTransferParameter.setFileId(IntegerUtils.b(str, 16));
            HwLog.i(f11050a, "handleParamTlv() file id:" + fileTransferParameter.getFileId());
            return;
        }
        if (i == 2) {
            fileTransferParameter.setAppWaitTime(IntegerUtils.b(str, 16));
            HwLog.i(f11050a, "handleParamTlv() device wait timeout:" + fileTransferParameter.getAppWaitTime());
            return;
        }
        if (i == 3) {
            fileTransferParameter.setTransferUnitSize(IntegerUtils.b(str, 16));
            HwLog.i(f11050a, "handleParamTlv() unit size:" + fileTransferParameter.getTransferUnitSize());
            return;
        }
        if (i == 4) {
            fileTransferParameter.setMaxApplyDataSize(IntegerUtils.b(str, 16));
            HwLog.i(f11050a, "handleParamTlv() max apply:" + fileTransferParameter.getMaxApplyDataSize());
            return;
        }
        if (i == 5) {
            fileTransferParameter.setTransferNotEncrypt(IntegerUtils.b(str) == 1);
            HwLog.i(f11050a, "handleParamTlv() not need encrypt:" + fileTransferParameter.isTransferNotEncrypt());
            return;
        }
        HwLog.i(f11050a, "handleParamTlv() default");
    }

    private void d(int i) {
        HwLog.i(f11050a, "startRequest() fileId:" + i);
        CommonFileInfo commonFileInfo = this.f.get(Integer.valueOf(i));
        commonFileInfo.setByteAll(ByteBuffer.allocate(commonFileInfo.getFileSize()));
        b(i, 0, false);
    }

    private void a(int i, int i2, boolean z) {
        CommonFileRetryInfo commonFileRetryInfo = this.h.get(Integer.valueOf(i));
        if (commonFileRetryInfo == null) {
            HwLog.w(f11050a, "setRetryInfo() info is null.");
            return;
        }
        commonFileRetryInfo.setLastOffset(i2);
        if (z) {
            commonFileRetryInfo.setToKitNumber(-1);
        }
        this.h.put(Integer.valueOf(i), commonFileRetryInfo);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void b(int i, int i2, boolean z) {
        String str = f11050a;
        HwLog.i(str, "doRequest() fileId:" + i + "offset:" + i2);
        CommonFileInfo commonFileInfo = this.f.get(Integer.valueOf(i));
        if (commonFileInfo == null) {
            HwLog.w(str, "doRequest() fileId not in list!");
            return;
        }
        FileTransferParameter fileTransferParameter = this.k.get(Integer.valueOf(i));
        if (fileTransferParameter == null) {
            HwLog.w(str, "doRequest() fileTransferParameter is null");
            return;
        }
        commonFileInfo.setFileOffset(i2);
        commonFileInfo.setFilePsn(0);
        a(i, i2, !z);
        if (fileTransferParameter.getMaxApplyDataSize() <= commonFileInfo.getFileSize() - commonFileInfo.getFileOffset()) {
            HwLog.i(str, "doRequest() request max:" + fileTransferParameter.getMaxApplyDataSize());
            commonFileInfo.setFileLength(fileTransferParameter.getMaxApplyDataSize());
            commonFileInfo.setFilePsnMax(fileTransferParameter.getPackageNumberMax());
            commonFileInfo.setByteUnit(ByteBuffer.allocate(fileTransferParameter.getMaxApplyDataSize()));
            a(i, commonFileInfo.getFileOffset(), fileTransferParameter.getMaxApplyDataSize());
        } else {
            int fileSize = commonFileInfo.getFileSize() - commonFileInfo.getFileOffset();
            HwLog.i(str, "doRequest() request not max:" + fileSize);
            if (fileSize % fileTransferParameter.getTransferUnitSize() == 0) {
                commonFileInfo.setFilePsnMax((fileSize / fileTransferParameter.getTransferUnitSize()) - 1);
            } else {
                commonFileInfo.setFilePsnMax(fileSize / fileTransferParameter.getTransferUnitSize());
            }
            commonFileInfo.setFileLength(fileSize);
            commonFileInfo.setByteUnit(ByteBuffer.allocate(fileSize));
            a(i, commonFileInfo.getFileOffset(), commonFileInfo.getFileLength());
        }
        if (!z) {
            HwLog.i(str, "doRequest() first!");
            e(i, fileTransferParameter.getAppWaitTime());
        }
        b(i, i2, fileTransferParameter.getAppWaitTime() / 3);
    }

    private void a(int i, int i2, int i3) {
        String str = CommonUtils.a() + (da.a(1) + da.a(1) + da.a(i)) + (da.a(2) + da.a(4) + da.b(i2)) + (da.a(3) + da.a(4) + da.b(i3));
        CommandJsonInfo commandJsonInfo = new CommandJsonInfo();
        commandJsonInfo.setServiceId(44);
        commandJsonInfo.setCommandId(4);
        commandJsonInfo.setCommandType(800);
        commandJsonInfo.setDeviceCommand(str);
        HwLog.i(f11050a, "sendRequestCommand() commandJsonInfo: " + commandJsonInfo.toString());
        h.a(this.j, commandJsonInfo, this.e);
    }

    private void e(byte[] bArr) {
        String a2 = da.a(bArr);
        String str = f11050a;
        HwLog.i(str, "handleDeviceDataReceived() info: " + a2);
        String substring = SafeString.substring(a2, CommonUtils.w());
        int b2 = IntegerUtils.b(SafeString.substring(substring, 0, 2), 16);
        int b3 = IntegerUtils.b(SafeString.substring(substring, 2, 10), 16);
        int b4 = IntegerUtils.b(SafeString.substring(substring, 10, 12), 16);
        HwLog.i(str, "handleDeviceDataReceived() fileID:" + b2 + ", offset:" + b3 + ", psn:" + b4);
        CommonFileInfo commonFileInfo = this.f.get(Integer.valueOf(b2));
        if (commonFileInfo != null) {
            if (commonFileInfo.getFileOffset() != b3 || commonFileInfo.getFilePsn() != b4) {
                HwLog.i(str, "handleDeviceDataReceived() fileOffset or psn error");
                return;
            }
            a(substring, b2, b4, commonFileInfo);
            int fileSize = (b3 * 100) / commonFileInfo.getFileSize();
            HwLog.i(str, "handleDeviceDataReceived() progress:" + fileSize);
            b(b2, fileSize);
            return;
        }
        HwLog.w(str, "handleDeviceDataReceived() fileID error");
    }

    private void a(String str, int i, int i2, CommonFileInfo commonFileInfo) {
        f(i);
        byte[] a2 = da.a(SafeString.substring(str, 12));
        if (commonFileInfo.getByteUnit() != null) {
            commonFileInfo.getByteUnit().put(a2);
        }
        a(commonFileInfo, a2, i, i2);
        if (i2 < commonFileInfo.getFilePsnMax()) {
            HwLog.i(f11050a, "handleDeviceDataReceived keep wait");
            if (this.k.get(Integer.valueOf(i)) != null) {
                e(i, this.k.get(Integer.valueOf(i)).getAppWaitTime());
                CommonFileRetryInfo commonFileRetryInfo = this.h.get(Integer.valueOf(i));
                if (commonFileRetryInfo != null) {
                    b(i, commonFileRetryInfo.getLastOffset(), this.k.get(Integer.valueOf(i)).getAppWaitTime() / 3);
                }
            }
            commonFileInfo.setFilePsn(i2 + 1);
            commonFileInfo.setFileOffset(commonFileInfo.getFileOffset() + a2.length);
            return;
        }
        String str2 = f11050a;
        HwLog.i(str2, "handleDeviceDataReceived one unit all received");
        commonFileInfo.getByteUnit().clear();
        commonFileInfo.getByteAll().put(commonFileInfo.getByteUnit());
        if (commonFileInfo.getByteAll().hasRemaining()) {
            HwLog.i(str2, "start request next");
            b(i, commonFileInfo.getFileOffset() + a2.length, false);
        } else {
            e(i);
        }
    }

    private void e(int i) {
        CommonFileInfo commonFileInfo = this.f.get(Integer.valueOf(i));
        if (commonFileInfo == null) {
            HwLog.w(f11050a, "handleRequestOver commonFileInfo is null");
            return;
        }
        try {
            if (commonFileInfo.isNeedVerify()) {
                String a2 = da.a(CommonUtils.a(commonFileInfo.getByteAll().array()));
                HwLog.i(f11050a, "app hashValue:" + a2 + ", device hashValue:" + commonFileInfo.getHashValue());
                if (TextUtils.equals(a2.toUpperCase(Locale.ENGLISH), commonFileInfo.getHashValue().toUpperCase(Locale.ENGLISH))) {
                    c(i, 1);
                    b(commonFileInfo);
                } else {
                    d(commonFileInfo);
                    c(i, 2);
                    commonFileInfo.getFileRequestCallBack().onFailure(30001, "");
                }
            } else {
                c(i, 1);
                b(commonFileInfo);
            }
            b(commonFileInfo, 30005);
            d();
        } catch (IOException unused) {
            HwLog.e(f11050a, "handleRequestOver IOException");
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r1v10 */
    /* JADX WARN: Type inference failed for: r1v11 */
    /* JADX WARN: Type inference failed for: r1v12 */
    /* JADX WARN: Type inference failed for: r1v13, types: [java.io.FileOutputStream, java.io.OutputStream] */
    /* JADX WARN: Type inference failed for: r1v14 */
    /* JADX WARN: Type inference failed for: r1v15 */
    /* JADX WARN: Type inference failed for: r1v16 */
    /* JADX WARN: Type inference failed for: r1v7, types: [java.io.File] */
    /* JADX WARN: Type inference failed for: r1v8 */
    /* JADX WARN: Type inference failed for: r1v9 */
    private void b(CommonFileInfo commonFileInfo) throws IOException {
        BufferedOutputStream bufferedOutputStream;
        BufferedOutputStream bufferedOutputStream2;
        File parentFile;
        String filterFilePath = CommonUtils.filterFilePath(this.j.getFilesDir().getCanonicalPath() + File.separator + "commonFileRequest" + File.separator + commonFileInfo.getFileName());
        if (filterFilePath == null) {
            HwLog.w(f11050a, "createFileWithByte path is null");
            return;
        }
        ?? file = new File(filterFilePath);
        BufferedOutputStream bufferedOutputStream3 = null;
        try {
            try {
                parentFile = file.getParentFile();
            } catch (Throwable th) {
                th = th;
            }
        } catch (IOException e) {
            e = e;
            file = 0;
        } catch (Throwable th2) {
            th = th2;
            bufferedOutputStream = null;
            cq.a(bufferedOutputStream3);
            cq.a(bufferedOutputStream);
            throw th;
        }
        if (parentFile != null) {
            if (!parentFile.exists()) {
                boolean mkdirs = parentFile.mkdirs();
                HwLog.i(f11050a, "createFileWithByte file mkdir:" + mkdirs);
            }
            if (!file.exists()) {
                boolean createNewFile = file.createNewFile();
                HwLog.i(f11050a, "createFileWithByte file create is:" + createNewFile);
            }
            file = FileHelper.openOutputStream(file, false);
            try {
                bufferedOutputStream2 = new BufferedOutputStream(file);
            } catch (IOException e2) {
                e = e2;
            }
            try {
                bufferedOutputStream2.write(commonFileInfo.getByteAll().array());
                bufferedOutputStream2.flush();
                HwLog.i(f11050a, "createFileWithByte end");
                file = file;
                if (commonFileInfo.getFileRequestCallBack() != null) {
                    commonFileInfo.getFileRequestCallBack().onSuccess(30000, filterFilePath, "");
                    file = file;
                }
            } catch (IOException e3) {
                e = e3;
                bufferedOutputStream3 = bufferedOutputStream2;
                HwLog.e(f11050a, "createFileWithByte exception:" + HwLog.printException((Exception) e));
                bufferedOutputStream2 = bufferedOutputStream3;
                file = file;
                bufferedOutputStream3 = file;
                cq.a(bufferedOutputStream3);
                cq.a(bufferedOutputStream2);
            } catch (Throwable th3) {
                th = th3;
                bufferedOutputStream3 = bufferedOutputStream2;
                bufferedOutputStream = bufferedOutputStream3;
                bufferedOutputStream3 = file;
                cq.a(bufferedOutputStream3);
                cq.a(bufferedOutputStream);
                throw th;
            }
            bufferedOutputStream3 = file;
            cq.a(bufferedOutputStream3);
            cq.a(bufferedOutputStream2);
        }
        bufferedOutputStream2 = null;
        cq.a(bufferedOutputStream3);
        cq.a(bufferedOutputStream2);
    }

    private void b(int i, int i2) {
        CommonFileInfo commonFileInfo = this.f.get(Integer.valueOf(i));
        if (commonFileInfo == null) {
            HwLog.w(f11050a, "reportProgressForUi commonFileInfo is null");
        } else if (commonFileInfo.getFileRequestCallBack() != null) {
            commonFileInfo.getFileRequestCallBack().onProgress(i2, "");
        }
    }

    private void c(int i, int i2) {
        String str = CommonUtils.a() + (da.a(1) + da.a(1) + da.a(i)) + (da.a(2) + da.a(1) + da.a(i2));
        CommandJsonInfo commandJsonInfo = new CommandJsonInfo();
        commandJsonInfo.setServiceId(44);
        commandJsonInfo.setCommandId(6);
        commandJsonInfo.setCommandType(800);
        commandJsonInfo.setDeviceCommand(str);
        HwLog.i(f11050a, "sendRequestCommand() commandJsonInfo: " + commandJsonInfo.toString());
        h.a(this.j, commandJsonInfo, this.e);
    }

    private void f(byte[] bArr) {
        String a2 = da.a(bArr);
        String str = f11050a;
        HwLog.i(str, "handleDeviceStatusReport() info: " + da.a(bArr));
        try {
            List<Tlv> tlvList = this.i.builderTlvList(SafeString.substring(a2, CommonUtils.w())).getTlvList();
            if (tlvList != null && tlvList.size() > 0) {
                int i = 0;
                int i2 = 0;
                for (Tlv tlv : tlvList) {
                    int b2 = IntegerUtils.b(tlv.getTag(), 16);
                    String value = tlv.getValue();
                    if (b2 == 1) {
                        i = IntegerUtils.b(value, 16);
                        HwLog.i(f11050a, "handleDeviceStatusReport() file_id:" + i);
                    } else if (b2 == 127) {
                        i2 = IntegerUtils.b(value, 16);
                        HwLog.i(f11050a, "handleDeviceStatusReport() status:" + i2);
                    } else {
                        HwLog.i(f11050a, "handleDeviceStatusReport() default");
                    }
                }
                d(i, i2);
                return;
            }
            HwLog.w(str, "handleDeviceStatusReport() tlv list error");
        } catch (TlvException unused) {
            HwLog.e(f11050a, "handleDeviceStatusReport() error");
        }
    }

    private void d(int i, int i2) {
        CommonFileInfo commonFileInfo = this.f.get(Integer.valueOf(i));
        if (commonFileInfo == null) {
            HwLog.w(f11050a, "reportCancelResult() commonFileInfo is null");
            return;
        }
        if (commonFileInfo.getCallback() != null) {
            if (i2 == 100000) {
                commonFileInfo.getCallback().onResponse(30005, "");
                HwLog.i(f11050a, "reportCancelResult() success");
                f(i);
                c(commonFileInfo);
                b(commonFileInfo, 30005);
                return;
            }
            commonFileInfo.getCallback().onResponse(30006, "");
            b(commonFileInfo, 30006);
            HwLog.i(f11050a, "reportCancelResult() failed");
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void f(int i) {
        b bVar = this.d;
        if (bVar == null || !bVar.hasMessages(i)) {
            return;
        }
        this.d.removeMessages(i);
    }

    private void e(int i, int i2) {
        if (this.d != null) {
            if (i2 != 0) {
                Message obtain = Message.obtain();
                obtain.what = i;
                obtain.arg1 = 100;
                this.d.sendMessageDelayed(obtain, i2 * 1000);
                return;
            }
            return;
        }
        HandlerThread handlerThread = new HandlerThread(f11050a);
        this.c = handlerThread;
        handlerThread.start();
        this.d = new b(this.c.getLooper());
        if (i2 != 0) {
            Message obtain2 = Message.obtain();
            obtain2.what = i;
            obtain2.arg1 = 100;
            this.d.sendMessageDelayed(obtain2, i2 * 1000);
        }
    }

    private void b(int i, int i2, int i3) {
        if (this.d != null) {
            if (i3 != 0) {
                Message obtain = Message.obtain();
                obtain.what = i;
                obtain.arg1 = 200;
                obtain.arg2 = i2;
                this.d.sendMessageDelayed(obtain, i3 * 1000);
                return;
            }
            return;
        }
        HandlerThread handlerThread = new HandlerThread(f11050a);
        this.c = handlerThread;
        handlerThread.start();
        this.d = new b(this.c.getLooper());
        if (i3 != 0) {
            Message obtain2 = Message.obtain();
            obtain2.what = i;
            obtain2.arg1 = 200;
            obtain2.arg2 = i2;
            this.d.sendMessageDelayed(obtain2, i3 * 1000);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void c(CommonFileInfo commonFileInfo) {
        if (commonFileInfo == null) {
            HwLog.w(f11050a, "handleRequestEnd() error, file info is null");
            return;
        }
        int i = 0;
        while (true) {
            if (i >= this.g.size()) {
                break;
            }
            CommonFileInfo commonFileInfo2 = this.g.get(i);
            String str = f11050a;
            HwLog.i(str, "handleRequestEnd() has cache file name:" + commonFileInfo2.getFileName() + ", type:" + commonFileInfo2.getFileType());
            if ((TextUtils.equals(commonFileInfo2.getFileName(), commonFileInfo.getFileName()) || TextUtils.isEmpty(commonFileInfo2.getFileName())) && commonFileInfo2.getFileType() == commonFileInfo.getFileType()) {
                HwLog.i(str, "handleRequestEnd() delete commonFileInfo.name:" + commonFileInfo2.getFileName());
                this.g.remove(commonFileInfo2);
                g();
                break;
            }
            i++;
        }
        if (this.f.get(Integer.valueOf(commonFileInfo.getFileId())) != null) {
            HwLog.i(f11050a, "handleRequestEnd() has mTransferringFileList file id:" + commonFileInfo.getFileId());
            this.f.remove(Integer.valueOf(commonFileInfo.getFileId()));
        }
        if (this.k.get(Integer.valueOf(commonFileInfo.getFileId())) != null) {
            HwLog.i(f11050a, "handleRequestEnd() has mFileTypeTransferInfos file id:" + commonFileInfo.getFileId());
            this.k.remove(Integer.valueOf(commonFileInfo.getFileId()));
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void f(int i, int i2) {
        CommonFileInfo commonFileInfo = this.f.get(Integer.valueOf(i));
        if (commonFileInfo == null) {
            HwLog.w(f11050a, "reportFailedForUi() commonFileInfo is null");
            return;
        }
        if (commonFileInfo.getFileRequestCallBack() != null) {
            commonFileInfo.getFileRequestCallBack().onFailure(i2, "");
            HwLog.i(f11050a, "reportFailedForUi() fileId:" + i + "errorCode:" + i2);
        }
    }

    class b extends Handler {
        b(Looper looper) {
            super(looper);
        }

        @Override // android.os.Handler
        public void handleMessage(Message message) {
            super.handleMessage(message);
            int i = message.arg1;
            if (i == 100) {
                HwLog.i(j.f11050a, "handleMessage() wait timeout!");
                int i2 = message.what;
                HwLog.i(j.f11050a, "handleMessage() wait timeout! file id:" + i2);
                j jVar = j.this;
                jVar.e((CommonFileInfo) jVar.f.get(Integer.valueOf(i2)));
                j.this.f(i2, OnRegisterSkinAttrsListener.REGISTER_BY_SCAN);
                j jVar2 = j.this;
                jVar2.c((CommonFileInfo) jVar2.f.get(Integer.valueOf(i2)));
                j.this.f(i2);
                return;
            }
            if (i != 200) {
                if (i != 500) {
                    HwLog.i(j.f11050a, "handleMessage() default");
                    return;
                } else {
                    HwLog.i(j.f11050a, "handleMessage() kit NO_MANAGER_CALLBACK");
                    j.this.g(message.what);
                    return;
                }
            }
            HwLog.i(j.f11050a, "handleMessage() retry");
            int i3 = message.what;
            int i4 = message.arg2;
            HwLog.i(j.f11050a, "handleMessage() retry! file id:" + i3 + ", offset:" + i4);
            j.this.b(i3, i4, true);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void g(int i) {
        CommonFileInfo commonFileInfo;
        Iterator<CommonFileInfo> it = this.g.iterator();
        while (true) {
            if (!it.hasNext()) {
                commonFileInfo = null;
                break;
            } else {
                commonFileInfo = it.next();
                if (commonFileInfo.getFileType() == i) {
                    break;
                }
            }
        }
        if (commonFileInfo != null) {
            b(commonFileInfo, 100009);
            if (this.g.size() > 0) {
                this.g.remove(0);
                g();
                return;
            }
            return;
        }
        HwLog.w(f11050a, "reportFileInfoTimeout() is null");
    }

    private void a(Map.Entry<Integer, CommonFileInfo> entry) {
        String str = f11050a;
        HwLog.i(str, "toKitReportNoConnectDevice() enter.");
        if (entry.getValue().getKitCallback() == null || !a(entry.getValue().getKitCallback())) {
            return;
        }
        HwLog.i(str, "toKitReportNoConnectDevice() to kit error code.");
        entry.getValue().getKitCallback().onResponse(300004, "");
    }

    private boolean a(IBaseCommonCallback iBaseCommonCallback) {
        if (this.g.size() == 0) {
            return false;
        }
        Iterator<CommonFileInfo> it = this.g.iterator();
        while (it.hasNext()) {
            if (it.next().getKitCallback() == iBaseCommonCallback) {
                return true;
            }
        }
        return false;
    }

    private void a(CommonFileInfo commonFileInfo, int i) {
        HwLog.i(f11050a, "toKitFileMessageErrorCode() enter.");
        b(commonFileInfo, i);
        if (this.g.size() <= 0 || i == 30003) {
            return;
        }
        this.g.remove(0);
        g();
    }

    private void d(CommonFileInfo commonFileInfo) {
        HwLog.i(f11050a, "toKitCheckFailure() enter.");
        b(commonFileInfo, 30001);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void e(CommonFileInfo commonFileInfo) {
        HwLog.i(f11050a, "toKitFileTimeout() enter");
        a(commonFileInfo, OnRegisterSkinAttrsListener.REGISTER_BY_SCAN);
    }

    private void b(CommonFileInfo commonFileInfo, int i) {
        if (commonFileInfo != null && commonFileInfo.getKitCallback() != null) {
            HwLog.i(f11050a, "toKitFailureCode() enter. errorCode:" + i);
            try {
                commonFileInfo.getKitCallback().onResponse(i, "");
                return;
            } catch (Exception e) {
                HwLog.e(f11050a, "toKitFailureCode() third part has exception, catch this" + HwLog.printException(e));
                return;
            }
        }
        HwLog.w(f11050a, "toKitFailureCode() getKitCallback() is null. code:" + i);
    }

    private void a(FileTransferParameter fileTransferParameter, CommonFileInfo commonFileInfo) {
        if (commonFileInfo.getKitCallback() == null) {
            HwLog.w(f11050a, "toKitFileConsultInfo() callback is null");
            return;
        }
        HwLog.i(f11050a, "toKitFileConsultInfo() enter.");
        String str = "";
        try {
            str = a(0, fileTransferParameter.getMaxApplyDataSize(), commonFileInfo.getFileSize(), commonFileInfo.getHashValue().getBytes("UTF-8"));
            commonFileInfo.getKitCallback().onResponse(10001, str);
        } catch (UnsupportedEncodingException unused) {
            HwLog.e(f11050a, "toKitFileConsultInfo() UnsupportedEncodingException : no support utf-8");
            commonFileInfo.getKitCallback().onResponse(10001, str);
        }
    }

    private void a(CommonFileInfo commonFileInfo, byte[] bArr, int i, int i2) {
        if (commonFileInfo.getKitCallback() == null) {
            HwLog.w(f11050a, "toKitFrameData() callback is null.");
            return;
        }
        CommonFileRetryInfo commonFileRetryInfo = this.h.get(Integer.valueOf(i));
        if (commonFileRetryInfo == null) {
            HwLog.w(f11050a, "toKitFrameData() retryInfo is null.");
            return;
        }
        if (i2 != commonFileRetryInfo.getToKitNumber() + 1) {
            HwLog.w(f11050a, "toKitFrameData() this frame has return to kit, return.");
            return;
        }
        commonFileRetryInfo.setToKitNumber(i2);
        this.h.put(Integer.valueOf(i), commonFileRetryInfo);
        String str = f11050a;
        HwLog.i(str, "toKitFrameData() enter");
        commonFileInfo.setTotalIndex(commonFileInfo.getTotalIndex() + 1);
        String a2 = a(0, commonFileInfo.getTotalIndex(), bArr);
        HwLog.i(str, "toKitFrameData():" + a2);
        commonFileInfo.getKitCallback().onResponse(10002, a2);
    }

    private String a(int i, int i2, byte[] bArr) {
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("errorCode", i);
            jSONObject.put("index", i2);
            jSONObject.put("value", da.a(bArr));
        } catch (JSONException unused) {
            HwLog.e(f11050a, "toFileTransferInfoJson() exception");
        }
        HwLog.i(f11050a, "toFileTransferInfoJson() json:" + jSONObject.toString());
        return jSONObject.toString();
    }

    private String a(int i, int i2, int i3, byte[] bArr) {
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("errorCode", i);
            jSONObject.put("maxTransferUnit", i2);
            jSONObject.put("fileSize", i3);
            jSONObject.put("crc", da.a(bArr));
        } catch (JSONException unused) {
            HwLog.e(f11050a, "toFileConsultInfoJson() exception");
        }
        HwLog.i(f11050a, "toFileConsultInfoJson() json is:" + jSONObject.toString());
        return jSONObject.toString();
    }

    static class a extends SafeBroadcastReceiver {
        private a() {
        }

        @Override // com.huawei.secure.android.common.intent.SafeBroadcastReceiver
        public void onReceiveMsg(Context context, Intent intent) {
            if (context == null) {
                return;
            }
            if (intent == null || intent.getExtras() == null || TextUtils.isEmpty(intent.getAction())) {
                HwLog.w(j.f11050a, "CommonFileRequestBroadCastReceiver() intent is null.");
                return;
            }
            if ("com.huawei.watchface.action.CONNECTION_STATE_CHANGED".equals(intent.getAction())) {
                DeviceInfo deviceInfo = (DeviceInfo) intent.getParcelableExtra("deviceinfo");
                if (deviceInfo == null) {
                    HwLog.w(j.f11050a, "CommonFileRequestBroadCastReceiver() deviceInfo null");
                    return;
                }
                HwLog.i(j.f11050a, "CommonFileRequestBroadCastReceiver() status:" + deviceInfo.getDeviceConnectState());
                j.b(deviceInfo);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static void b(DeviceInfo deviceInfo) {
        int deviceConnectState = deviceInfo.getDeviceConnectState();
        if (deviceConnectState == 2) {
            HwLog.i(f11050a, "CommonFileRequestBroadCastReceiver() DEVICE_CONNECTED");
            return;
        }
        if (deviceConnectState == 3) {
            HwLog.i(f11050a, "CommonFileRequestBroadCastReceiver() DEVICE_DISCONNECTED");
            if (b == null) {
                return;
            }
            b.f();
            b.c();
            if (b.d == null) {
                return;
            }
            b.d.removeMessages(200);
            return;
        }
        HwLog.i(f11050a, "CommonFileRequestBroadCastReceiver() default");
    }
}
