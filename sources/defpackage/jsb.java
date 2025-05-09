package defpackage;

import com.huawei.datatype.DeviceCommand;
import com.huawei.hwbasemgr.IBaseResponseCallback;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.hwcommonmodel.datatypes.TransferFileInfo;
import com.huawei.hwdevice.outofprocess.util.hwdfx.MaintenaceInterface;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.wisecloud.drmclient.license.HwDrmConstant;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.Locale;
import org.apache.commons.io.FileUtils;

/* loaded from: classes5.dex */
public class jsb implements MaintenaceInterface {
    private static jsb b;
    private IBaseResponseCallback e;
    private ArrayList<byte[]> i;
    private final Object c = new Object();

    /* renamed from: a, reason: collision with root package name */
    private String f14044a = "";
    private Runnable d = new Runnable() { // from class: jsb.1
        @Override // java.lang.Runnable
        public void run() {
            synchronized (jsb.this.c) {
                if (jsb.this.getmTransferDataContentPath() != null) {
                    File filesDir = BaseApplication.getContext().getFilesDir();
                    if (filesDir.exists()) {
                        LogUtil.a("PeriodRRIFileUtil", "create is success ：", Boolean.valueOf(filesDir.mkdirs()));
                    }
                    File file = new File(filesDir, jsb.this.getmTransferDataContentPath());
                    FileOutputStream fileOutputStream = null;
                    try {
                        try {
                            if (!file.exists()) {
                                LogUtil.a("PeriodRRIFileUtil", "create success ", Boolean.valueOf(file.createNewFile()));
                            }
                            fileOutputStream = FileUtils.openOutputStream(file);
                            if (jsb.this.i != null) {
                                Iterator it = jsb.this.i.iterator();
                                while (it.hasNext()) {
                                    fileOutputStream.write((byte[]) it.next());
                                    fileOutputStream.flush();
                                }
                            }
                            jyu.e(file, 16);
                            if (fileOutputStream != null) {
                                try {
                                    try {
                                        fileOutputStream.close();
                                        LogUtil.b("PeriodRRIFileUtil", "finally write data file ok.");
                                    } catch (IOException e) {
                                        LogUtil.b("PeriodRRIFileUtil", "write data file Exception e is ", e.getMessage());
                                        LogUtil.b("PeriodRRIFileUtil", "finally write data file ok.");
                                    }
                                } catch (Throwable th) {
                                    LogUtil.b("PeriodRRIFileUtil", "finally write data file ok.");
                                    throw th;
                                }
                            }
                            jsb.this.e(100000, "success");
                        } catch (IOException unused) {
                            LogUtil.b("PeriodRRIFileUtil", "mLogRunnable stream write exception");
                            jsb.this.e(10001, "write data FileNotFoundException e.");
                            if (fileOutputStream != null) {
                                try {
                                    try {
                                        fileOutputStream.close();
                                        LogUtil.b("PeriodRRIFileUtil", "finally write data file ok.");
                                    } catch (IOException e2) {
                                        LogUtil.b("PeriodRRIFileUtil", "write data file Exception e is ", e2.getMessage());
                                        LogUtil.b("PeriodRRIFileUtil", "finally write data file ok.");
                                        return;
                                    }
                                } catch (Throwable th2) {
                                    LogUtil.b("PeriodRRIFileUtil", "finally write data file ok.");
                                    throw th2;
                                }
                            }
                            return;
                        }
                    } catch (Throwable th3) {
                        if (fileOutputStream != null) {
                            try {
                                try {
                                    fileOutputStream.close();
                                    LogUtil.b("PeriodRRIFileUtil", "finally write data file ok.");
                                } catch (IOException e3) {
                                    LogUtil.b("PeriodRRIFileUtil", "write data file Exception e is ", e3.getMessage());
                                    LogUtil.b("PeriodRRIFileUtil", "finally write data file ok.");
                                    throw th3;
                                }
                            } catch (Throwable th4) {
                                LogUtil.b("PeriodRRIFileUtil", "finally write data file ok.");
                                throw th4;
                            }
                        }
                        throw th3;
                    }
                }
                if (jsb.this.getmTransferDataContentPath() == null) {
                    jsb.this.e(10001, "path is all null.");
                }
            }
        }
    };
    private String f = null;
    private ArrayList<byte[]> j = new ArrayList<>(0);

    @Override // com.huawei.hwdevice.outofprocess.util.hwdfx.MaintenaceInterface
    public void cutFolder(String str, String str2) {
    }

    @Override // com.huawei.hwdevice.outofprocess.util.hwdfx.MaintenaceInterface
    public void deleteTenDayFile() {
    }

    @Override // com.huawei.hwdevice.outofprocess.util.hwdfx.MaintenaceInterface
    public ArrayList filtertFile(ArrayList arrayList, int i) {
        return arrayList;
    }

    @Override // com.huawei.hwdevice.outofprocess.util.hwdfx.MaintenaceInterface
    public int getMaintRetryNum() {
        return 0;
    }

    @Override // com.huawei.hwdevice.outofprocess.util.hwdfx.MaintenaceInterface
    public boolean getMaintRetryResult() {
        return false;
    }

    @Override // com.huawei.hwdevice.outofprocess.util.hwdfx.MaintenaceInterface
    public String getmTransferStateContentPath() {
        return null;
    }

    @Override // com.huawei.hwdevice.outofprocess.util.hwdfx.MaintenaceInterface
    public void initMaintenanceParame(TransferFileInfo transferFileInfo) {
    }

    @Override // com.huawei.hwdevice.outofprocess.util.hwdfx.MaintenaceInterface
    public void initName() {
    }

    @Override // com.huawei.hwdevice.outofprocess.util.hwdfx.MaintenaceInterface
    public void setMaintCheckTime(String str) {
    }

    @Override // com.huawei.hwdevice.outofprocess.util.hwdfx.MaintenaceInterface
    public void setMaintRetryNum(int i) {
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void e(int i, String str) {
        IBaseResponseCallback iBaseResponseCallback = this.e;
        if (iBaseResponseCallback != null) {
            iBaseResponseCallback.d(i, str);
        } else {
            LogUtil.b("PeriodRRIFileUtil", "mCallback is null");
        }
    }

    private jsb() {
    }

    public static jsb e() {
        jsb jsbVar;
        synchronized (jsb.class) {
            if (b == null) {
                b = new jsb();
            }
            jsbVar = b;
        }
        return jsbVar;
    }

    @Override // com.huawei.hwdevice.outofprocess.util.hwdfx.MaintenaceInterface
    public String getmTransferDataContentPath() {
        return this.f;
    }

    @Override // com.huawei.hwdevice.outofprocess.util.hwdfx.MaintenaceInterface
    public void setMaintRetryResult(boolean z) {
        if (z) {
            if (this.f != null) {
                this.f = null;
            }
            ArrayList<byte[]> arrayList = this.j;
            if (arrayList != null) {
                arrayList.clear();
            }
        }
        this.f14044a = "";
    }

    @Override // com.huawei.hwdevice.outofprocess.util.hwdfx.MaintenaceInterface
    public void writeLogToFile(ArrayList<byte[]> arrayList, String str, Date date) {
        if (this.j == null) {
            return;
        }
        if (arrayList != null && arrayList.size() > 0) {
            Iterator<byte[]> it = arrayList.iterator();
            while (it.hasNext()) {
                byte[] next = it.next();
                if ("rrisqi_data.bin".equals(str)) {
                    this.j.add(next);
                }
            }
        }
        if ("".equals(this.f14044a) || !this.f14044a.equals(str)) {
            this.f14044a = str;
            initMaintenanceFile();
        }
    }

    @Override // com.huawei.hwdevice.outofprocess.util.hwdfx.MaintenaceInterface
    public void save2File(IBaseResponseCallback iBaseResponseCallback, boolean z) {
        synchronized (this.c) {
            this.e = iBaseResponseCallback;
            ArrayList<byte[]> arrayList = new ArrayList<>(16);
            this.i = arrayList;
            arrayList.addAll(this.j);
        }
        jrq.b("PeriodRRIFileUtil", this.d);
    }

    private String a() {
        String str = ("RRI_" + new SimpleDateFormat(HwDrmConstant.TIME_FORMAT, Locale.ENGLISH).format(new Date())) + "_" + this.f14044a;
        LogUtil.a("PeriodRRIFileUtil", " getFileName()  deviceVersion targetPath ", str);
        return str;
    }

    @Override // com.huawei.hwdevice.outofprocess.util.hwdfx.MaintenaceInterface
    public void initMaintenanceFile() {
        LogUtil.a("PeriodRRIFileUtil", "enter initMaintenanceFile():");
        String a2 = a();
        if (a2.contains("rrisqi_data.bin")) {
            this.f = a2;
        }
    }

    @Override // com.huawei.hwdevice.outofprocess.util.hwdfx.MaintenaceInterface
    public void onDestroyMaintenance() {
        LogUtil.a("PeriodRRIFileUtil", "onDestroyMaintenance");
    }

    @Override // com.huawei.hwdevice.outofprocess.util.hwdfx.MaintenaceInterface
    public DeviceCommand maintParametersCommand() {
        DeviceCommand deviceCommand = new DeviceCommand();
        deviceCommand.setServiceID(10);
        deviceCommand.setCommandID(2);
        String str = cvx.e(6) + cvx.e(1) + cvx.e(4);
        deviceCommand.setDataLen(cvx.a(str).length);
        deviceCommand.setDataContent(cvx.a(str));
        return deviceCommand;
    }

    @Override // com.huawei.hwdevice.outofprocess.util.hwdfx.MaintenaceInterface
    public DeviceCommand transferFileEndProcess() {
        DeviceCommand deviceCommand = new DeviceCommand();
        deviceCommand.setServiceID(10);
        deviceCommand.setCommandID(6);
        String str = cvx.e(1) + cvx.e(1) + cvx.e(1);
        deviceCommand.setDataLen(cvx.a(str).length);
        deviceCommand.setDataContent(cvx.a(str));
        return deviceCommand;
    }

    @Override // com.huawei.hwdevice.outofprocess.util.hwdfx.MaintenaceInterface
    public String getMaintCheckTime() {
        return "0";
    }

    @Override // com.huawei.hwdevice.outofprocess.util.hwdfx.MaintenaceInterface
    public String getDeviceName(int i) {
        return "Huawei";
    }

    @Override // com.huawei.hwdevice.outofprocess.util.hwdfx.MaintenaceInterface
    public String getDayDateTime() {
        return "";
    }
}
