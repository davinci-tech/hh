package defpackage;

import android.os.Environment;
import android.text.TextUtils;
import com.huawei.datatype.DeviceCommand;
import com.huawei.hwbasemgr.IBaseResponseCallback;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.hwcommonmodel.datatypes.TransferFileInfo;
import com.huawei.hwdevice.outofprocess.util.hwdfx.MaintenaceInterface;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.hwlogsmodel.common.LogConfig;
import com.huawei.wisecloud.drmclient.license.HwDrmConstant;
import health.compact.a.CommonUtil;
import health.compact.a.CompileParameterUtil;
import health.compact.a.IoUtils;
import health.compact.a.KeyValDbManager;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.ConcurrentModificationException;
import java.util.Date;
import java.util.Iterator;
import org.apache.commons.io.FileUtils;

/* loaded from: classes5.dex */
public class jry implements MaintenaceInterface {
    private static jry c;
    private IBaseResponseCallback d;
    private ArrayList<byte[]> h;
    private boolean j;
    private String l;
    private String n;
    private ArrayList<byte[]> o;
    public static final String e = Environment.getExternalStorageDirectory() + "/huaweisystem/CoreSleepLog";
    private static final Object b = new Object();
    private String g = "";

    /* renamed from: a, reason: collision with root package name */
    private String f14042a = "";
    private String f = "";
    private Runnable i = new Runnable() { // from class: jry.3
        @Override // java.lang.Runnable
        public void run() {
            synchronized (jry.b) {
                LogUtil.a("DetailSleepUtil", "DetailSleep saveFile run mIsStateFileWrite = ", Boolean.valueOf(jry.this.j));
                if (!jry.this.j || jry.this.getmTransferDataContentPath() != null) {
                    jry.this.i();
                    jry.this.f();
                    jry.this.b();
                    if (jry.this.getmTransferStateContentPath() == null && jry.this.getmTransferDataContentPath() == null) {
                        LogUtil.h("DetailSleepUtil", "getmTransferStateContentPath() is null. getmTransferDataContentPath() is null. ");
                        jry.this.j = false;
                        if (jry.this.d != null) {
                            jry.this.d.d(10001, "path is all null.");
                        }
                    }
                    return;
                }
                LogUtil.b("DetailSleepUtil", "error: when mIsStateFileWriteis true,getmTransferDataContentPath() should not be null");
                if (jry.this.d != null) {
                    jry.this.d.d(10001, "error: when mIsStateFileWriteis true,getmTransferDataContentPath() should not be null");
                }
                jry.this.j = false;
            }
        }
    };

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

    private jry() {
        this.j = false;
        LogUtil.a("DetailSleepUtil", "DetailSleepUtil constructor()");
        this.l = null;
        this.n = null;
        synchronized (b) {
            this.j = false;
        }
        this.h = new ArrayList<>(20);
        this.o = new ArrayList<>(20);
    }

    public static jry d() {
        jry jryVar;
        synchronized (b) {
            if (c == null) {
                c = new jry();
            }
            jryVar = c;
        }
        return jryVar;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void f() {
        if (getmTransferDataContentPath() != null) {
            try {
                try {
                    try {
                        ObjectOutputStream j = j();
                        try {
                        } catch (IOException e2) {
                            LogUtil.b("DetailSleepUtil", "write data file Exception e = ", e2.getMessage());
                        } finally {
                            LogUtil.b("DetailSleepUtil", "finally write data file ok");
                        }
                        if (j != null) {
                            j.close();
                        }
                    } catch (IOException e3) {
                        LogUtil.b("DetailSleepUtil", "write data file Exception e = ", e3.getMessage());
                        if (this.d != null) {
                            this.d.d(10001, "write data file Exception");
                        }
                    }
                } catch (FileNotFoundException e4) {
                    LogUtil.b("DetailSleepUtil", "write data File FileNotFoundException e = ", e4.getMessage());
                    if (this.d != null) {
                        this.d.d(10001, "write data FileNotFoundException");
                    }
                } catch (ConcurrentModificationException unused) {
                    LogUtil.b("DetailSleepUtil", "saveFile exception is ConcurrentModificationException");
                    if (this.d != null) {
                        this.d.d(10001, "write data file ConcurrentModificationException");
                    }
                }
            } finally {
                this.j = false;
            }
        }
    }

    public void b(String str) {
        FileInputStream fileInputStream;
        FileInputStream fileInputStream2;
        LogUtil.a("DetailSleepUtil", "DetailSleepUtil saveFile, is_debug_version:", Boolean.valueOf(CompileParameterUtil.a("IS_DEBUG_VERSION")), "saveFile path is empty:", Boolean.valueOf(TextUtils.isEmpty(str)));
        if ((CommonUtil.aq() || CommonUtil.as() || g()) && !TextUtils.isEmpty(str)) {
            String a2 = a();
            int lastIndexOf = str.lastIndexOf("/");
            if (lastIndexOf == -1) {
                LogUtil.h("DetailSleepUtil", "path is wrong.");
                return;
            }
            String substring = str.substring(lastIndexOf + 1);
            if (TextUtils.isEmpty(substring)) {
                LogUtil.h("DetailSleepUtil", "path is wrong. no file name.");
                return;
            }
            FileOutputStream fileOutputStream = null;
            try {
                File file = FileUtils.getFile(a2 + new SimpleDateFormat("YYYYMMddHHmmss").format(new Date()) + "_" + substring);
                File file2 = FileUtils.getFile(a2);
                if (!file.exists()) {
                    LogUtil.c("DetailSleepUtil", "isSuccess mkdir : ", Boolean.valueOf(file2.mkdirs()));
                }
                fileInputStream2 = FileUtils.openInputStream(FileUtils.getFile(str));
                try {
                    try {
                        fileOutputStream = FileUtils.openOutputStream(file);
                        byte[] bArr = new byte[1024];
                        while (true) {
                            int read = fileInputStream2.read(bArr);
                            if (read == -1) {
                                break;
                            } else {
                                fileOutputStream.write(bArr, 0, read);
                            }
                        }
                        fileOutputStream.flush();
                        LogUtil.c("DetailSleepUtil", "copy file success.");
                    } catch (IOException unused) {
                        LogUtil.b("DetailSleepUtil", "save File IOException");
                        IoUtils.e(fileOutputStream);
                        IoUtils.e(fileInputStream2);
                    }
                } catch (Throwable th) {
                    fileInputStream = fileInputStream2;
                    th = th;
                    IoUtils.e(fileOutputStream);
                    IoUtils.e(fileInputStream);
                    throw th;
                }
            } catch (IOException unused2) {
                fileInputStream2 = null;
            } catch (Throwable th2) {
                th = th2;
                fileInputStream = null;
                IoUtils.e(fileOutputStream);
                IoUtils.e(fileInputStream);
                throw th;
            }
            IoUtils.e(fileOutputStream);
            IoUtils.e(fileInputStream2);
        }
    }

    private ObjectOutputStream j() throws FileNotFoundException, IOException, ConcurrentModificationException {
        ArrayList<byte[]> arrayList = this.h;
        ObjectOutputStream objectOutputStream = new ObjectOutputStream(BaseApplication.getContext().openFileOutput(getmTransferDataContentPath(), 0));
        if (arrayList == null) {
            LogUtil.h("DetailSleepUtil", "saveFile saveDataBytes is null");
            return objectOutputStream;
        }
        LogUtil.a("DetailSleepUtil", " mTransferDataContent size : ", Integer.valueOf(arrayList.size()));
        objectOutputStream.writeObject(arrayList);
        objectOutputStream.close();
        if (!TextUtils.isEmpty(getmTransferDataContentPath())) {
            jyu.e(BaseApplication.getContext().getFileStreamPath(getmTransferDataContentPath()), 15);
        }
        if (g() && (CommonUtil.aq() || CommonUtil.as())) {
            b(this.f14042a, arrayList);
        }
        IBaseResponseCallback iBaseResponseCallback = this.d;
        if (iBaseResponseCallback != null) {
            iBaseResponseCallback.d(100000, "success");
        }
        return objectOutputStream;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void i() {
        ObjectOutputStream objectOutputStream;
        ArrayList<byte[]> arrayList;
        String str = getmTransferStateContentPath();
        if (str == null || this.j) {
            return;
        }
        ObjectOutputStream objectOutputStream2 = null;
        try {
            try {
                objectOutputStream = new ObjectOutputStream(BaseApplication.getContext().openFileOutput(str, 0));
            } catch (Throwable th) {
                th = th;
                objectOutputStream = null;
            }
        } catch (FileNotFoundException e2) {
            e = e2;
        } catch (IOException e3) {
            e = e3;
        }
        try {
            arrayList = this.o;
        } catch (FileNotFoundException e4) {
            e = e4;
            objectOutputStream2 = objectOutputStream;
            LogUtil.b("DetailSleepUtil", "write stateFile FileNotFoundException e = ", e.getMessage());
            IBaseResponseCallback iBaseResponseCallback = this.d;
            if (iBaseResponseCallback != null) {
                iBaseResponseCallback.d(10001, "write state FileNotFoundException e.");
            }
            if (objectOutputStream2 != null) {
                try {
                    objectOutputStream2.close();
                    LogUtil.b("DetailSleepUtil", "finally write stateFile ok");
                } catch (IOException e5) {
                    LogUtil.b("DetailSleepUtil", "write stateFile Exception e = ", e5.getMessage());
                } finally {
                }
            }
            this.j = true;
        } catch (IOException e6) {
            e = e6;
            objectOutputStream2 = objectOutputStream;
            LogUtil.b("DetailSleepUtil", "write stateFile Exception e = ", e.getMessage());
            IBaseResponseCallback iBaseResponseCallback2 = this.d;
            if (iBaseResponseCallback2 != null) {
                iBaseResponseCallback2.d(10001, "write state file Exception");
            }
            try {
            } catch (IOException e7) {
                LogUtil.b("DetailSleepUtil", "write stateFile Exception e = ", e7.getMessage());
            } finally {
            }
            if (objectOutputStream2 != null) {
                objectOutputStream2.close();
                LogUtil.b("DetailSleepUtil", "finally write stateFile ok");
            }
            this.j = true;
        } catch (Throwable th2) {
            th = th2;
            try {
            } catch (IOException e8) {
                LogUtil.b("DetailSleepUtil", "write stateFile Exception e = ", e8.getMessage());
            } finally {
            }
            if (objectOutputStream != null) {
                objectOutputStream.close();
            }
            this.j = true;
            throw th;
        }
        if (arrayList == null) {
            LogUtil.h("DetailSleepUtil", "handleStateFalse mTransferStateBytes is null");
            try {
                objectOutputStream.close();
            } catch (IOException e9) {
                LogUtil.b("DetailSleepUtil", "write stateFile Exception e = ", e9.getMessage());
            } finally {
            }
            this.j = true;
            return;
        }
        LogUtil.a("DetailSleepUtil", " mTransferStateBytes size : ", Integer.valueOf(arrayList.size()));
        objectOutputStream.writeObject(this.o);
        objectOutputStream.close();
        jyu.e(BaseApplication.getContext().getFileStreamPath(str), 14);
        if (g() && (CommonUtil.aq() || CommonUtil.as())) {
            b(this.f, this.o);
        }
        IBaseResponseCallback iBaseResponseCallback3 = this.d;
        if (iBaseResponseCallback3 != null) {
            iBaseResponseCallback3.d(100000, "success");
        }
        try {
            objectOutputStream.close();
        } catch (IOException e10) {
            LogUtil.b("DetailSleepUtil", "write stateFile Exception e = ", e10.getMessage());
        } finally {
        }
        this.j = true;
    }

    @Override // com.huawei.hwdevice.outofprocess.util.hwdfx.MaintenaceInterface
    public String getmTransferDataContentPath() {
        return this.l;
    }

    @Override // com.huawei.hwdevice.outofprocess.util.hwdfx.MaintenaceInterface
    public String getmTransferStateContentPath() {
        return this.n;
    }

    @Override // com.huawei.hwdevice.outofprocess.util.hwdfx.MaintenaceInterface
    public void setMaintRetryResult(boolean z) {
        LogUtil.a("DetailSleepUtil", " mTransferStateBytes = ", this.o);
        if (z) {
            if (this.l != null) {
                LogUtil.a("DetailSleepUtil", "isDeleteDataSuccess :", Boolean.valueOf(BaseApplication.getContext().deleteFile(this.l)));
                this.l = null;
            }
            if (this.n != null) {
                LogUtil.a("DetailSleepUtil", "isDeleteStateSuccess :", Boolean.valueOf(BaseApplication.getContext().deleteFile(this.n)));
                this.n = null;
            }
            ArrayList<byte[]> arrayList = this.h;
            if (arrayList != null) {
                arrayList.clear();
            }
            ArrayList<byte[]> arrayList2 = this.o;
            if (arrayList2 != null) {
                arrayList2.clear();
            }
        }
        synchronized (b) {
            this.j = false;
            LogUtil.a("DetailSleepUtil", "mIsStateFileWrite= ", false);
        }
        this.g = "";
        LogUtil.a("DetailSleepUtil", "Leave setMaintRetryResult fileName = ", "");
    }

    @Override // com.huawei.hwdevice.outofprocess.util.hwdfx.MaintenaceInterface
    public void writeLogToFile(ArrayList<byte[]> arrayList, String str, Date date) {
        LogUtil.a("DetailSleepUtil", "writeLogToFile file_name = ", str);
        if (arrayList != null && arrayList.size() > 0) {
            LogUtil.a("DetailSleepUtil", "writeLogToFile file_name = ", str, " file size = ", Integer.valueOf(arrayList.size()));
            Iterator<byte[]> it = arrayList.iterator();
            while (it.hasNext()) {
                byte[] next = it.next();
                if ("sleep_data.bin".equals(str)) {
                    this.h.add(next);
                } else if ("sleep_state.bin".equals(str)) {
                    this.o.add(next);
                } else {
                    LogUtil.h("DetailSleepUtil", "writeLogToFile else fileNameValue:", str);
                }
            }
        }
        if ("".equals(this.g) || !this.g.equals(str)) {
            LogUtil.a("DetailSleepUtil", "file_name = ", str);
            this.g = str;
            initMaintenanceFile();
        }
    }

    @Override // com.huawei.hwdevice.outofprocess.util.hwdfx.MaintenaceInterface
    public void save2File(IBaseResponseCallback iBaseResponseCallback, boolean z) {
        LogUtil.a("DetailSleepUtil", "save2File enter");
        synchronized (b) {
            this.d = iBaseResponseCallback;
            LogUtil.a("DetailSleepUtil", "mCallback = ", iBaseResponseCallback);
        }
        jrq.b("DetailSleepUtil", this.i);
    }

    private void b(String str, ArrayList<byte[]> arrayList) {
        LogUtil.a("DetailSleepUtil", "writeFileToSd() enter, fileName = ", str, "content = ", arrayList, "is_debug_version = ", Boolean.valueOf(CompileParameterUtil.a("IS_DEBUG_VERSION")), "writeLogToFile don't write sleepstate and sleepdate bin file to sdCard :", Boolean.valueOf(CompileParameterUtil.a("RELEASE_EVENT_LOG_UPLOAD")));
        if ((CommonUtil.aq() || CommonUtil.as() || g()) && !CompileParameterUtil.a("RELEASE_EVENT_LOG_UPLOAD")) {
            if (!Environment.getExternalStorageState().equals("mounted")) {
                LogUtil.a("DetailSleepUtil", "SD card is not avaiable/writeable right now.");
            } else {
                d(str, arrayList);
            }
        }
    }

    private void d(String str, ArrayList<byte[]> arrayList) {
        String str2;
        if (!CommonUtil.ar()) {
            str2 = e;
        } else {
            str2 = LogConfig.m() + "/CoreSleepLog";
        }
        File file = new File(str2);
        File file2 = new File(str2, str);
        LogUtil.a("DetailSleepUtil", "Path exists:", Boolean.valueOf(file.exists()), "File exists:", Boolean.valueOf(file2.exists()));
        if (!file.exists() && !file.mkdir()) {
            LogUtil.b("DetailSleepUtil", "path.mkdir() fail!");
            return;
        }
        if (!file2.exists()) {
            try {
                if (!file2.createNewFile()) {
                    LogUtil.b("DetailSleepUtil", "getLogFile() createNewFile is failed.");
                }
            } catch (IOException e2) {
                LogUtil.b("DetailSleepUtil", "createNewFile Exception e = ", e2.getMessage());
                return;
            }
        }
        FileOutputStream fileOutputStream = null;
        try {
            try {
                fileOutputStream = FileUtils.openOutputStream(file2);
                Iterator<byte[]> it = arrayList.iterator();
                while (it.hasNext()) {
                    fileOutputStream.write(it.next());
                    fileOutputStream.flush();
                }
                if (fileOutputStream != null) {
                    try {
                        fileOutputStream.close();
                    } catch (IOException e3) {
                        LogUtil.b("DetailSleepUtil", "stream.close() Exception e = ", e3.getMessage());
                    }
                }
            } catch (IOException e4) {
                LogUtil.b("DetailSleepUtil", "stream write Exception e = ", e4.getMessage());
                if (fileOutputStream != null) {
                    try {
                        fileOutputStream.close();
                    } catch (IOException e5) {
                        LogUtil.b("DetailSleepUtil", "stream.close() Exception e = ", e5.getMessage());
                    }
                }
            }
        } catch (Throwable th) {
            if (fileOutputStream != null) {
                try {
                    fileOutputStream.close();
                } catch (IOException e6) {
                    LogUtil.b("DetailSleepUtil", "stream.close() Exception e = ", e6.getMessage());
                }
            }
            throw th;
        }
    }

    public void b() {
        LogUtil.a("DetailSleepUtil", "enter updateCoreSleepDir.");
        String str = !CommonUtil.ar() ? e : LogConfig.m() + "/CoreSleepLog";
        File file = new File(str);
        if (!file.exists()) {
            LogUtil.h("DetailSleepUtil", "coreSleepFile is not exists.");
            return;
        }
        LogUtil.a("DetailSleepUtil", "coreSleepFile path: ", str);
        File[] listFiles = file.listFiles();
        if (listFiles == null || listFiles.length == 0) {
            LogUtil.h("DetailSleepUtil", "CoreSleepFile is empty.");
            return;
        }
        try {
            long parseLong = Long.parseLong(new SimpleDateFormat(HwDrmConstant.TIME_FORMAT).format(new Date(System.currentTimeMillis() - 604800000)));
            LogUtil.a("DetailSleepUtil", "oneWeekDateLong: ", Long.valueOf(parseLong));
            for (File file2 : listFiles) {
                if (!file2.isDirectory()) {
                    String name = file2.getName();
                    if (name.contains("_")) {
                        long parseLong2 = Long.parseLong(name.substring(0, name.indexOf("_")));
                        LogUtil.c("DetailSleepUtil", "oneWeekDateLong: ", Long.valueOf(parseLong), ",fileDateLong: ", Long.valueOf(parseLong2));
                        if (parseLong2 < parseLong) {
                            LogUtil.c("DetailSleepUtil", "deleteDirWithFile result=", Boolean.valueOf(file2.delete()));
                        }
                    }
                }
            }
        } catch (NumberFormatException unused) {
            LogUtil.b("DetailSleepUtil", "NumberFormatException error occurs.");
        }
    }

    private String e() {
        String str = "CoreSleep_" + this.g;
        LogUtil.a("DetailSleepUtil", " getFileName()  deviceVersion targetPath ", str);
        return str;
    }

    @Override // com.huawei.hwdevice.outofprocess.util.hwdfx.MaintenaceInterface
    public void initMaintenanceFile() {
        String e2 = e();
        LogUtil.a("DetailSleepUtil", "enter initMaintenanceFile() filePath = ", e2);
        if (e2.contains("sleep_data.bin")) {
            this.l = e2;
        } else if (e2.contains("sleep_state.bin")) {
            this.n = e2;
        } else {
            LogUtil.h("DetailSleepUtil", "initMaintenanceFile else filePath");
        }
        if (g()) {
            if (CommonUtil.aq() || CommonUtil.as()) {
                a(e2);
            }
        }
    }

    private void a(String str) {
        String format = new SimpleDateFormat(HwDrmConstant.TIME_FORMAT).format(new Date());
        if (str.contains("sleep_data.bin")) {
            this.f14042a = format + "_" + str;
        } else if (str.contains("sleep_state.bin")) {
            this.f = format + "_" + str;
        } else {
            LogUtil.h("DetailSleepUtil", "initCoreSleepDataSaveDir else filePath");
        }
        LogUtil.a("DetailSleepUtil", "initCoreSleepDataSaveDir filePath = ", str, "  mDataFilePath = ", this.f14042a, "  mStateFilePath = ", this.f);
    }

    @Override // com.huawei.hwdevice.outofprocess.util.hwdfx.MaintenaceInterface
    public void onDestroyMaintenance() {
        LogUtil.a("DetailSleepUtil", "enter onDestroyMaintenance()");
    }

    @Override // com.huawei.hwdevice.outofprocess.util.hwdfx.MaintenaceInterface
    public DeviceCommand maintParametersCommand() {
        DeviceCommand deviceCommand = new DeviceCommand();
        deviceCommand.setServiceID(10);
        deviceCommand.setCommandID(2);
        String str = cvx.e(6) + cvx.e(1) + cvx.e(1);
        deviceCommand.setDataLen(cvx.a(str).length);
        deviceCommand.setDataContent(cvx.a(str));
        LogUtil.a("DetailSleepUtil", "getMaintenanceParameters deviceCommand = ", deviceCommand.toString());
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
        LogUtil.a("DetailSleepUtil", "transferFileEndProcess deviceCommand");
        return deviceCommand;
    }

    private String a() {
        if (!CommonUtil.ar()) {
            return Environment.getExternalStorageDirectory() + "/huaweisystem/CoreSleepLog/";
        }
        return LogConfig.m() + "/CoreSleepLog/";
    }

    private boolean g() {
        String e2 = KeyValDbManager.b(BaseApplication.getContext()).e("save_sleep_key");
        if (TextUtils.isEmpty(e2)) {
            return false;
        }
        return Boolean.parseBoolean(e2);
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
