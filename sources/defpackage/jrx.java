package defpackage;

import android.content.Context;
import android.text.TextUtils;
import android.util.Log;
import com.huawei.datatype.DeviceCommand;
import com.huawei.haf.common.exception.ExceptionUtils;
import com.huawei.haf.threadpool.ThreadPoolManager;
import com.huawei.health.devicemgr.business.entity.DeviceInfo;
import com.huawei.hwbasemgr.IBaseResponseCallback;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.hwcommonmodel.datatypes.TransferFileInfo;
import com.huawei.hwdevice.outofprocess.util.hwdfx.MaintenaceInterface;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.hwlogsmodel.common.LogConfig;
import com.huawei.openalliance.ad.constant.VastAttribute;
import com.huawei.utils.FoundationCommonUtil;
import com.huawei.wisecloud.drmclient.license.HwDrmConstant;
import health.compact.a.CommonUtil;
import health.compact.a.CompileParameterUtil;
import health.compact.a.ProcessUtil;
import health.compact.a.SharedPreferenceManager;
import health.compact.a.StorageParams;
import health.compact.a.UnitUtil;
import health.compact.a.Utils;
import health.compact.a.hwlogsmodel.ReleaseLogUtil;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.ConcurrentModificationException;
import java.util.Date;
import java.util.Iterator;
import java.util.Locale;
import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes5.dex */
public class jrx implements MaintenaceInterface {
    private String ac;
    private IBaseResponseCallback j;
    private ByteBuffer m;
    private FileOutputStream v;
    private FileChannel w;
    public static final String b = LogConfig.o() + "MaintenanceLogTemp";
    public static final String c = LogConfig.o() + "MaintenanceLog";

    /* renamed from: a, reason: collision with root package name */
    public static final String f14040a = LogConfig.o() + "tempdumplog";
    private static final String d = LogConfig.j();
    private static final byte[] h = new byte[0];
    private static jvi g = null;
    private static jrx f = null;
    private static String i = "";
    final ArrayList<byte[]> e = new ArrayList<>(0);
    private Queue<byte[]> y = new ConcurrentLinkedQueue();
    private Queue<byte[]> k = new ConcurrentLinkedQueue();
    private d u = null;
    private Date n = null;
    private int r = 7;
    private String q = "";
    private String p = "";
    private boolean x = false;
    private String z = "";
    private Date aa = null;
    private long l = 0;
    private String t = "";
    private String s = "";
    private int o = 0;

    @Override // com.huawei.hwdevice.outofprocess.util.hwdfx.MaintenaceInterface
    public String getmTransferDataContentPath() {
        return null;
    }

    @Override // com.huawei.hwdevice.outofprocess.util.hwdfx.MaintenaceInterface
    public String getmTransferStateContentPath() {
        return null;
    }

    @Override // com.huawei.hwdevice.outofprocess.util.hwdfx.MaintenaceInterface
    public DeviceCommand transferFileEndProcess() {
        return null;
    }

    static /* synthetic */ int i(jrx jrxVar) {
        int i2 = jrxVar.o;
        jrxVar.o = i2 + 1;
        return i2;
    }

    public static jrx d() {
        jrx jrxVar;
        g = jvi.a();
        synchronized (h) {
            if (f == null) {
                f = new jrx();
            }
            jrxVar = f;
        }
        return jrxVar;
    }

    public static void c() {
        i = "";
    }

    public static void d(String str) {
        i = str;
    }

    @Override // com.huawei.hwdevice.outofprocess.util.hwdfx.MaintenaceInterface
    public void initName() {
        d("");
        this.z = "";
        this.aa = null;
    }

    @Override // com.huawei.hwdevice.outofprocess.util.hwdfx.MaintenaceInterface
    public ArrayList filtertFile(ArrayList arrayList, int i2) {
        ArrayList arrayList2 = new ArrayList(0);
        if (Utils.o()) {
            ArrayList e = e(arrayList);
            ReleaseLogUtil.e("MaintenanceUtil", "oversea filtertFileList:", e.toString());
            return e;
        }
        for (int i3 = 0; i3 < arrayList.size(); i3++) {
            Object obj = arrayList.get(i3);
            if (obj instanceof String) {
                String lowerCase = ((String) obj).toLowerCase(Locale.ENGLISH);
                boolean z = true;
                boolean z2 = lowerCase.contains("event") || lowerCase.contains(VastAttribute.ERROR) || lowerCase.contains("dump.log");
                if (!lowerCase.contains("power.log") && !lowerCase.contains("fs.log")) {
                    z = false;
                }
                boolean contains = lowerCase.contains("debug");
                if (z2 || z) {
                    arrayList2.add(arrayList.get(i3));
                }
                if (i2 == 2 && contains) {
                    arrayList2.add(arrayList.get(i3));
                }
            }
        }
        return arrayList2;
    }

    private ArrayList e(ArrayList arrayList) {
        ArrayList arrayList2 = new ArrayList(0);
        for (int i2 = 0; i2 < arrayList.size(); i2++) {
            Object obj = arrayList.get(i2);
            if ((obj instanceof String) && ((String) obj).toLowerCase(Locale.ENGLISH).contains("event")) {
                arrayList2.add(arrayList.get(i2));
            }
        }
        return arrayList2;
    }

    public String e(int i2, String str) {
        return (i2 < 19 || i2 == 20 || TextUtils.isEmpty(this.t)) ? str : this.t;
    }

    public String d(int i2, String str, String str2) {
        this.t = str2;
        return e(i2, str);
    }

    public String b(int i2, String str) {
        this.t = str;
        return getDeviceName(i2);
    }

    public String e() {
        String str;
        String str2;
        String str3;
        String e = e(this.r, getDeviceName(this.r));
        if (TextUtils.isEmpty(this.ac)) {
            LogUtil.a("MaintenanceUtil", "getMacAddress() size:", iyl.d().e(this.q), " identify size: ", iyl.d().e(this.s), " isOversea is ", Boolean.valueOf(Utils.o()));
            str = FoundationCommonUtil.getEncryptionLogMark(this.q, this.s, this.r);
        } else {
            str = this.ac;
        }
        String str4 = this.p;
        String str5 = "";
        if (str4 == null || "".equals(str4)) {
            this.p = "00.00.00";
        }
        try {
            str5 = new SimpleDateFormat(HwDrmConstant.TIME_FORMAT, Locale.ENGLISH).format(this.n);
        } catch (Exception e2) {
            LogUtil.b("MaintenanceUtil", "Exception: ", ExceptionUtils.d(e2));
        }
        if (!TextUtils.isEmpty(this.t) && cvz.e(this.t)) {
            str2 = "/" + e + "-HONOR_" + this.p + "_" + str + "_";
        } else {
            str2 = "/" + e + "_" + this.p + "_" + str + "_";
        }
        if (!cvt.c(this.r) && g.u()) {
            str3 = f14040a + str2;
        } else {
            str3 = b + str2;
        }
        if (CompileParameterUtil.a("RELEASE_EVENT_LOG_UPLOAD")) {
            str3 = str3 + str5 + "_WearableBeta_" + i;
        } else if (this.r == 10 && !TextUtils.isEmpty(i)) {
            String[] split = i.split("_");
            if (split != null && split.length > 0) {
                str3 = str3 + split[0] + "_WearableBeta_" + i.substring(split[0].length() + 1, i.length());
            }
        } else {
            str3 = str3 + str5 + "_WearableBeta_" + i;
        }
        LogUtil.a("MaintenanceUtil", "getFileName() deviceVersion targetPath: ", str3);
        return str3;
    }

    @Override // com.huawei.hwdevice.outofprocess.util.hwdfx.MaintenaceInterface
    public String getDeviceName(int i2) {
        if (c(i2)) {
            return d(i2);
        }
        if (i2 == 10) {
            return "Leo";
        }
        if (i2 == 63) {
            return "AW70-B49HN";
        }
        if (i2 == 23 || i2 == 24) {
            return "AW70";
        }
        if (i2 == 36 || i2 == 37) {
            return "AW70-B39";
        }
        if (i2 == 44) {
            return "Andes_B19";
        }
        if (i2 == 45) {
            return "Andes_B29";
        }
        switch (i2) {
            case 18:
                return "Crius";
            case 19:
                return "Terra";
            case 20:
                return "Talos";
            case 21:
                return "Fortuna";
            default:
                return e(i2);
        }
    }

    private boolean c(int i2) {
        if (i2 == 1 || i2 == 2 || i2 == 3 || i2 == 5 || i2 == 7 || i2 == 8) {
            return true;
        }
        switch (i2) {
            case 13:
            case 14:
            case 15:
            case 16:
                return true;
            default:
                LogUtil.h("MaintenanceUtil", "new device.");
                return false;
        }
    }

    private String d(int i2) {
        if (i2 == 1) {
            return "B2";
        }
        if (i2 == 2) {
            return "K1";
        }
        if (i2 == 3) {
            return "W1";
        }
        if (i2 == 5) {
            return "B0";
        }
        if (i2 == 7) {
            return "Gemini";
        }
        if (i2 == 8) {
            return "Metis";
        }
        switch (i2) {
            case 13:
                return "NYX";
            case 14:
                return "GRUS";
            case 15:
                return "Eris";
            case 16:
                return "Janus";
            default:
                LogUtil.h("MaintenanceUtil", "go to default branch.");
                return "HUAWEI WEAR";
        }
    }

    private String e(int i2) {
        String f2;
        if (ProcessUtil.b().endsWith(":PhoneService")) {
            f2 = juu.a(i2).c();
        } else {
            f2 = jfu.c(i2).f();
        }
        return c(f2);
    }

    private String c(String str) {
        if (!TextUtils.isEmpty(this.t)) {
            return this.t;
        }
        if (TextUtils.isEmpty(str) || !TextUtils.isEmpty(this.t)) {
            return "HUAWEI WEAR";
        }
        for (int i2 = 0; i2 < str.length(); i2++) {
            if (!"ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz1234567890-=[];\\',./ ~!@#$%^&*()_+\"{}|:<>?".contains(str.charAt(i2) + "") || str.contains("_")) {
                return "HUAWEI WEAR";
            }
        }
        return str;
    }

    @Override // com.huawei.hwdevice.outofprocess.util.hwdfx.MaintenaceInterface
    public void initMaintenanceParame(TransferFileInfo transferFileInfo) {
        this.s = transferFileInfo.getDeviceSn();
        this.q = transferFileInfo.getDeviceMac();
        this.p = transferFileInfo.getDeviceVersion();
        this.r = transferFileInfo.getDeviceType();
        this.t = transferFileInfo.getDeviceModel();
        this.ac = transferFileInfo.getUdidFromDevice();
    }

    @Override // com.huawei.hwdevice.outofprocess.util.hwdfx.MaintenaceInterface
    public String getDayDateTime() {
        Log.e("MaintenanceUtil", "getCurrentTime: strCurTime");
        long time = Calendar.getInstance().getTime().getTime();
        Log.e("MaintenanceUtil", "getCurrentTime: strCurTime = " + String.valueOf(time));
        return String.valueOf(time);
    }

    @Override // com.huawei.hwdevice.outofprocess.util.hwdfx.MaintenaceInterface
    public void deleteTenDayFile() {
        ArrayList<File> f2 = f();
        if (f2 == null) {
            LogUtil.h("MaintenanceUtil", "deleteTenDayFile, not have ten days log");
            return;
        }
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(HwDrmConstant.TIME_FORMAT, Locale.ENGLISH);
        String format = simpleDateFormat.format(new Date());
        Iterator<File> it = f2.iterator();
        String str = "";
        while (it.hasNext()) {
            File next = it.next();
            String[] split = next.getName().split("_");
            if (split.length > 4) {
                str = split[4];
            }
            try {
                if ((simpleDateFormat.parse(format).getTime() - simpleDateFormat.parse(str).getTime()) / 86400000 > 10) {
                    LogUtil.a("MaintenanceUtil", "deleteTenDayFile isDelete: ", Boolean.valueOf(next.delete()));
                }
            } catch (ParseException unused) {
                LogUtil.b("MaintenanceUtil", "deleteTenDayFile, Exception ParseException");
            }
        }
    }

    private ArrayList<File> f() {
        ArrayList<File> arrayList = new ArrayList<>(0);
        File[] listFiles = new File(b).listFiles();
        if (listFiles == null || listFiles.length == 0) {
            return null;
        }
        for (int i2 = 0; i2 < listFiles.length; i2++) {
            if (!listFiles[i2].isDirectory()) {
                arrayList.add(listFiles[i2]);
            }
        }
        return arrayList;
    }

    @Override // com.huawei.hwdevice.outofprocess.util.hwdfx.MaintenaceInterface
    public void writeLogToFile(ArrayList<byte[]> arrayList, String str, Date date) {
        LogUtil.c("MaintenanceUtil", "Enter writeLogToFile");
        if (arrayList != null) {
            for (int i2 = 0; i2 < arrayList.size(); i2++) {
                if (arrayList.get(i2) != null) {
                    this.l += r2.length;
                }
            }
            this.e.addAll(arrayList);
            this.z = str;
            if (date != null && (date.clone() instanceof Date)) {
                this.aa = (Date) date.clone();
            }
            LogUtil.a("MaintenanceUtil", "writeLogToFile dataSize: ", Long.valueOf(this.l), ",tmpFileName: ", this.z);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void e(Date date, ArrayList<byte[]> arrayList, String str) {
        LogUtil.a("MaintenanceUtil", "enter writeLog", ";fileName: ", i, ";inputName: ", str);
        try {
            this.x = true;
            if (date != null) {
                this.n = new Date(date.getTime());
            }
            if (str == null || i == null) {
                StringBuilder sb = new StringBuilder("MaintenanceUtil, writeLog, null == inputName? ");
                sb.append(str == null);
                sb.append(", null == fileName? ");
                sb.append(i == null);
                sqo.p(sb.toString());
            }
            if (TextUtils.isEmpty(i) || !i.equals(str)) {
                LogUtil.a("MaintenanceUtil", "inputName: ", str);
                i = str;
                if (!cvt.c(this.r) && g.u()) {
                    i();
                } else {
                    initMaintenanceFile();
                }
            }
            this.y.clear();
            this.k.clear();
            Iterator<byte[]> it = arrayList.iterator();
            while (it.hasNext()) {
                byte[] next = it.next();
                if (next != null) {
                    this.k.add(next);
                } else {
                    LogUtil.b("MaintenanceUtil", "items: null");
                }
            }
            if (!arrayList.isEmpty()) {
                arrayList.clear();
            }
            this.y = this.k;
            d dVar = this.u;
            if (dVar == null) {
                d dVar2 = new d();
                this.u = dVar2;
                dVar2.start();
            } else {
                synchronized (dVar) {
                    this.u.notifyAll();
                }
            }
        } catch (IndexOutOfBoundsException unused) {
            LogUtil.b("MaintenanceUtil", "IndexOutOfBoundsException occur.");
        } catch (OutOfMemoryError e) {
            LogUtil.a("MaintenanceUtil", "Exception: ", e.getMessage());
        } catch (ConcurrentModificationException unused2) {
            LogUtil.b("MaintenanceUtil", "ConcurrentModificationException occur.");
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void i() {
        boolean createNewFile;
        boolean z = this.x;
        if (!z) {
            LogUtil.b("MaintenanceUtil", "initMaintenanceFile isWrite is ", Boolean.valueOf(z));
        }
        FileChannel fileChannel = this.w;
        if (fileChannel != null) {
            try {
                fileChannel.close();
                this.w = null;
            } catch (IOException unused) {
                LogUtil.b("MaintenanceUtil", "IOException occur.");
            }
        }
        try {
            String a2 = a();
            File file = new File(a2);
            if (!file.exists() && !(createNewFile = file.createNewFile())) {
                LogUtil.b("MaintenanceUtil", "initMaintenanceFile isCreated is ", Boolean.valueOf(createNewFile));
            }
            if (CommonUtil.c(a2) == null) {
                LogUtil.b("MaintenanceUtil", "filepath is illegale: ", a2);
                return;
            }
            FileOutputStream fileOutputStream = new FileOutputStream(a2, true);
            this.v = fileOutputStream;
            this.w = fileOutputStream.getChannel();
        } catch (IOException unused2) {
            LogUtil.b("MaintenanceUtil", "Exception IOException");
            try {
                FileChannel fileChannel2 = this.w;
                if (fileChannel2 != null) {
                    fileChannel2.close();
                }
                FileOutputStream fileOutputStream2 = this.v;
                if (fileOutputStream2 == null || !fileOutputStream2.getFD().valid()) {
                    return;
                }
                this.v.close();
            } catch (Exception unused3) {
                LogUtil.b("MaintenanceUtil", "Exception close");
            }
        }
    }

    private String a() {
        String str = f14040a;
        File file = new File(str);
        if (!file.exists()) {
            if (!file.mkdirs()) {
                LogUtil.b("MaintenanceUtil", "folder mkdirs failed.");
            }
            return e();
        }
        String e = e();
        File[] listFiles = file.listFiles();
        if (listFiles != null && listFiles.length > 0) {
            if (listFiles.length == 1) {
                return str + "/" + listFiles[0].getName();
            }
            a(listFiles);
        }
        return e;
    }

    private void a(File[] fileArr) {
        if (fileArr == null) {
            return;
        }
        for (File file : fileArr) {
            LogUtil.a("MaintenanceUtil", "isDeleted is ", Boolean.valueOf(file.delete()));
        }
    }

    @Override // com.huawei.hwdevice.outofprocess.util.hwdfx.MaintenaceInterface
    public void initMaintenanceFile() {
        boolean createNewFile;
        boolean mkdirs;
        boolean z = this.x;
        if (!z) {
            LogUtil.b("MaintenanceUtil", "initMaintenanceFile isWrite is ", Boolean.valueOf(z));
        }
        FileChannel fileChannel = this.w;
        if (fileChannel != null) {
            try {
                fileChannel.close();
                this.w = null;
            } catch (IOException unused) {
                LogUtil.b("MaintenanceUtil", "IOException IOException.");
            }
        }
        try {
            File file = new File(b);
            if (!file.exists() && !(mkdirs = file.mkdirs())) {
                LogUtil.b("MaintenanceUtil", "initMaintenanceFile isSuccess is ", Boolean.valueOf(mkdirs));
            }
            String e = e();
            File file2 = new File(e);
            if (!file2.exists() && !(createNewFile = file2.createNewFile())) {
                LogUtil.b("MaintenanceUtil", "initMaintenanceFile isCreated is ", Boolean.valueOf(createNewFile));
            }
            FileOutputStream fileOutputStream = new FileOutputStream(e, true);
            this.v = fileOutputStream;
            this.w = fileOutputStream.getChannel();
        } catch (IOException unused2) {
            LogUtil.b("MaintenanceUtil", "Exception IOException.");
            try {
                FileChannel fileChannel2 = this.w;
                if (fileChannel2 != null) {
                    fileChannel2.close();
                }
                FileOutputStream fileOutputStream2 = this.v;
                if (fileOutputStream2 == null || !fileOutputStream2.getFD().valid()) {
                    return;
                }
                this.v.close();
            } catch (Exception unused3) {
                LogUtil.b("MaintenanceUtil", "Exception close");
            }
        }
    }

    @Override // com.huawei.hwdevice.outofprocess.util.hwdfx.MaintenaceInterface
    public void onDestroyMaintenance() {
        try {
            try {
                FileChannel fileChannel = this.w;
                if (fileChannel != null) {
                    fileChannel.close();
                }
                FileOutputStream fileOutputStream = this.v;
                if (fileOutputStream != null) {
                    try {
                        if (fileOutputStream.getFD().valid()) {
                            this.v.close();
                        }
                    } catch (IOException unused) {
                        LogUtil.b("MaintenanceUtil", "fileOutputStream IOException");
                    }
                }
            } catch (IOException e) {
                LogUtil.b("MaintenanceUtil", e.getMessage());
                FileOutputStream fileOutputStream2 = this.v;
                if (fileOutputStream2 != null) {
                    try {
                        if (fileOutputStream2.getFD().valid()) {
                            this.v.close();
                        }
                    } catch (IOException unused2) {
                        LogUtil.b("MaintenanceUtil", "fileOutputStream IOException");
                    }
                }
            }
            this.w = null;
            this.e.clear();
            this.l = 0L;
        } catch (Throwable th) {
            FileOutputStream fileOutputStream3 = this.v;
            if (fileOutputStream3 != null) {
                try {
                    if (fileOutputStream3.getFD().valid()) {
                        this.v.close();
                    }
                } catch (IOException unused3) {
                    LogUtil.b("MaintenanceUtil", "fileOutputStream IOException");
                }
            }
            throw th;
        }
    }

    @Override // com.huawei.hwdevice.outofprocess.util.hwdfx.MaintenaceInterface
    public void cutFolder(String str, String str2) {
        File file;
        File file2 = new File(str);
        LogUtil.a("MaintenanceUtil", "cutFolder old: ", str, " newPath: ", str2, " oldFile.exists() is ", Boolean.valueOf(file2.exists()));
        if (file2.exists()) {
            File file3 = new File(str2);
            if (!file3.exists()) {
                LogUtil.a("MaintenanceUtil", "isSuccess = ", Boolean.valueOf(file3.mkdirs()));
            }
            String[] list = file2.list();
            if (list == null) {
                LogUtil.a("MaintenanceUtil", "cutFolder old file list = null");
                return;
            }
            for (int i2 = 0; i2 < list.length; i2++) {
                if (d.equals(str) && list[i2].endsWith("appdft_Beta.zip")) {
                    LogUtil.a("MaintenanceUtil", "appdft is not cut");
                } else {
                    if (str.endsWith(File.separator)) {
                        if (CommonUtil.c(str + list[i2]) == null) {
                            LogUtil.b("MaintenanceUtil", "file illegal: ", str + list[i2]);
                            return;
                        }
                        file = new File(CommonUtil.c(str + list[i2]));
                    } else {
                        if (CommonUtil.c(str + File.separator + list[i2]) == null) {
                            LogUtil.b("MaintenanceUtil", "file illegal: ", str + File.separator + list[i2]);
                            return;
                        }
                        file = new File(CommonUtil.c(str + File.separator + list[i2]));
                    }
                    if (file.isFile() && file.exists()) {
                        LogUtil.a("MaintenanceUtil", "temp.isFile() = " + file.isFile() + "exists() = " + file.exists());
                        e(file, str2);
                    } else if (file.isDirectory() && file.exists()) {
                        cutFolder(str + File.separator + list[i2], str2 + File.separator + list[i2]);
                    } else {
                        LogUtil.h("MaintenanceUtil", "enter else");
                    }
                    String str3 = CommonUtil.c(str2) + File.separator + file.getName();
                    LogUtil.a("MaintenanceUtil", "oldFile.delete() isDelete is ", file2.delete() + ", new file = " + str3 + ", is exist? " + new File(str3).exists());
                }
            }
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:60:0x013b A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:84:0x0215 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:93:0x01ef A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private void e(java.io.File r13, java.lang.String r14) {
        /*
            Method dump skipped, instructions count: 570
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: defpackage.jrx.e(java.io.File, java.lang.String):void");
    }

    class d extends Thread {

        /* renamed from: a, reason: collision with root package name */
        private boolean f14041a;

        private d() {
            this.f14041a = false;
        }

        @Override // java.lang.Thread
        public void start() {
            synchronized (this) {
                LogUtil.a("MaintenanceUtil", "LogThread start enter");
                if (jrx.this.v == null) {
                    if (!cvt.c(jrx.this.r) && jrx.g.u()) {
                        jrx.this.i();
                    } else {
                        jrx.this.initMaintenanceFile();
                    }
                }
                super.start();
            }
        }

        /* JADX WARN: Code restructure failed: missing block: B:49:0x007a, code lost:
        
            r5.e.j.d(10001, "Failed");
         */
        @Override // java.lang.Thread, java.lang.Runnable
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct add '--show-bad-code' argument
        */
        public void run() {
            /*
                Method dump skipped, instructions count: 270
                To view this dump add '--comments-level debug' option
            */
            throw new UnsupportedOperationException("Method not decompiled: jrx.d.run():void");
        }

        private void a() {
            jrx.this.l = 0L;
            if (jrx.this.j != null) {
                if (this.f14041a) {
                    jrx.this.j.d(10001, "Failed");
                } else {
                    jrx.this.j.d(100000, "success");
                }
            }
            try {
                jrx.this.o = 0;
                wait();
            } catch (InterruptedException unused) {
                LogUtil.b("MaintenanceUtil", "InterruptedException");
            }
        }

        @Override // java.lang.Thread
        @Deprecated
        public void destroy() {
            try {
                try {
                    jrx.this.x = false;
                    if (jrx.this.w != null) {
                        jrx.this.w.close();
                    }
                    try {
                        if (jrx.this.v.getFD().valid()) {
                            jrx.this.v.close();
                        }
                    } catch (IOException unused) {
                        LogUtil.b("MaintenanceUtil", "fileOutputStream IOException");
                    }
                } catch (IOException e) {
                    LogUtil.b("MaintenanceUtil", e.getMessage());
                }
                jrx.this.w = null;
            } finally {
                try {
                    if (jrx.this.v.getFD().valid()) {
                        jrx.this.v.close();
                    }
                } catch (IOException unused2) {
                    LogUtil.b("MaintenanceUtil", "fileOutputStream IOException");
                }
            }
        }
    }

    @Override // com.huawei.hwdevice.outofprocess.util.hwdfx.MaintenaceInterface
    public void setMaintCheckTime(String str) {
        LogUtil.a("MaintenanceUtil", "setMaintCheckTime, time: ", str);
        SharedPreferenceManager.e(BaseApplication.getContext(), String.valueOf(10), "MAINT_KEY_CHECK_TIME", str, new StorageParams());
    }

    @Override // com.huawei.hwdevice.outofprocess.util.hwdfx.MaintenaceInterface
    public String getMaintCheckTime() {
        String b2 = SharedPreferenceManager.b(BaseApplication.getContext(), String.valueOf(10), "MAINT_KEY_CHECK_TIME");
        LogUtil.a("MaintenanceUtil", "getMaintCheckTime,maintCheckTime: ", b2);
        return b2;
    }

    @Override // com.huawei.hwdevice.outofprocess.util.hwdfx.MaintenaceInterface
    public void setMaintRetryResult(boolean z) {
        LogUtil.a("MaintenanceUtil", "setMaintRetryResult, result is ", Boolean.valueOf(z));
        SharedPreferenceManager.e(BaseApplication.getContext(), String.valueOf(10), "MAINT_KEY_RESULT", String.valueOf(z), new StorageParams());
    }

    @Override // com.huawei.hwdevice.outofprocess.util.hwdfx.MaintenaceInterface
    public boolean getMaintRetryResult() {
        return "true".equals(SharedPreferenceManager.b(BaseApplication.getContext(), String.valueOf(10), "MAINT_KEY_RESULT"));
    }

    @Override // com.huawei.hwdevice.outofprocess.util.hwdfx.MaintenaceInterface
    public void setMaintRetryNum(int i2) {
        LogUtil.a("MaintenanceUtil", "setMaintRetryTime, retry: ", Integer.valueOf(i2));
        SharedPreferenceManager.e(BaseApplication.getContext(), String.valueOf(10), "MAINT_KEY_RETRY_TIME", String.valueOf(i2), new StorageParams());
    }

    @Override // com.huawei.hwdevice.outofprocess.util.hwdfx.MaintenaceInterface
    public int getMaintRetryNum() {
        String b2 = SharedPreferenceManager.b(BaseApplication.getContext(), String.valueOf(10), "MAINT_KEY_RETRY_TIME");
        if (nsn.a(b2)) {
            try {
                int parseInt = Integer.parseInt(b2);
                LogUtil.a("MaintenanceUtil", "getMaintRetryTime, retry: ", Integer.valueOf(parseInt));
                return parseInt;
            } catch (NumberFormatException e) {
                LogUtil.b("MaintenanceUtil", "getMaintRetryTime: ", e.getMessage());
            }
        }
        return 0;
    }

    @Override // com.huawei.hwdevice.outofprocess.util.hwdfx.MaintenaceInterface
    public DeviceCommand maintParametersCommand() {
        DeviceCommand deviceCommand = new DeviceCommand();
        deviceCommand.setServiceID(10);
        deviceCommand.setCommandID(2);
        deviceCommand.setmIdentify(this.s);
        return deviceCommand;
    }

    @Override // com.huawei.hwdevice.outofprocess.util.hwdfx.MaintenaceInterface
    public void save2File(IBaseResponseCallback iBaseResponseCallback, boolean z) {
        LogUtil.b("MaintenanceUtil", "enter save2File");
        this.j = iBaseResponseCallback;
        if (this.aa == null) {
            if (iBaseResponseCallback != null) {
                iBaseResponseCallback.d(10001, "Failed");
                return;
            }
            return;
        }
        LogUtil.a("MaintenanceUtil", "dataSize: ", Long.valueOf(this.l), ";isForce: ", Boolean.valueOf(z), ";callback: ", iBaseResponseCallback);
        if (this.l >= 200000 || z) {
            ThreadPoolManager.d().c("MaintenanceUtil", new Runnable() { // from class: jrx.4
                @Override // java.lang.Runnable
                public void run() {
                    jrx jrxVar = jrx.this;
                    jrxVar.e(jrxVar.aa, jrx.this.e, jrx.this.z);
                }
            });
        } else if (iBaseResponseCallback != null) {
            this.j.d(100000, "success");
        }
    }

    public static String b(JSONObject jSONObject, Context context, String str, String str2) {
        if (jSONObject == null) {
            return "";
        }
        try {
            jSONObject.put("datatime", jsd.d(SharedPreferenceManager.c(context, "ota_datatime")));
            jSONObject.put("EXCE_OTA_APP_UPG_TYPE", jsc.d(context));
            jSONObject.put("EXCE_OTA_APP_UPG_TLV", jsc.c(context));
            jSONObject.put("EXCE_OTA_APP_UPG_START_VERSION", jsc.e(context));
            jSONObject.put("EXCE_OTA_APP_UPG_GOAL_VERSION", jsc.a(context));
            long c2 = SharedPreferenceManager.c(context, "EXCE_OTA_APP_UPG_TRANS_START");
            long c3 = SharedPreferenceManager.c(context, "EXCE_OTA_APP_UPG_TRANS_STOP_TIME");
            jSONObject.put("EXCE_OTA_APP_UPG_TRANS_START", jsd.d(c2));
            jSONObject.put("EXCE_OTA_APP_UPG_TRANS_STOP", UnitUtil.e((c3 - c2) / 60000, 1, 1));
            jSONObject.put(str, str2);
        } catch (JSONException e) {
            LogUtil.a("MaintenanceUtil", "JSONException jsonException ", e.getMessage());
        }
        return jSONObject.toString();
    }

    public static String b(String str) {
        if (str == null) {
            return "";
        }
        String str2 = "Talos-";
        if (!str.contains("Talos-")) {
            str2 = "Fortuna-";
            if (!str.contains("Fortuna-")) {
                str2 = "Latona";
                if (!str.contains("Latona")) {
                    str2 = "ELA";
                    if (!str.contains("ELA")) {
                        str2 = "Crius";
                        if (!str.contains("Crius")) {
                            str2 = "TerraB09";
                            if (!str.contains("TerraB09")) {
                                str2 = "Terra";
                                if (!str.contains("Terra")) {
                                    str2 = "Diana";
                                    if (!str.contains("Diana")) {
                                        return "";
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
        return str2;
    }

    public static String e(String str) {
        if (System.currentTimeMillis() - SharedPreferenceManager.c(BaseApplication.getContext(), "BETA_CLUB_QUESTION_NUMBER_TIME") > 43200000) {
            SharedPreferenceManager.h(BaseApplication.getContext(), "");
        }
        String c2 = SharedPreferenceManager.c(BaseApplication.getContext());
        return (TextUtils.isEmpty(c2) || TextUtils.isEmpty(str)) ? str : str.replace("00000000", c2);
    }

    public static String a(String str) {
        if (cvz.e(str)) {
            String str2 = jsd.f14045a + str;
            ReleaseLogUtil.e("Dfx_MaintenanceUtil", "current log is honor");
            return str2;
        }
        String str3 = jsd.b + str;
        ReleaseLogUtil.e("Dfx_MaintenanceUtil", "current log isn't honor");
        return str3;
    }

    public String d(DeviceInfo deviceInfo) {
        String str;
        if (deviceInfo == null) {
            return "";
        }
        int productType = deviceInfo.getProductType();
        String securityDeviceId = deviceInfo.getSecurityDeviceId();
        String deviceIdentify = deviceInfo.getDeviceIdentify();
        String udidFromDevice = deviceInfo.getUdidFromDevice();
        String softVersion = deviceInfo.getSoftVersion();
        String deviceModel = deviceInfo.getDeviceModel();
        this.t = deviceModel;
        String e = e(productType, getDeviceName(productType));
        if (TextUtils.isEmpty(udidFromDevice)) {
            LogUtil.a("MaintenanceUtil", "getDetectFileName getMacAddress() size:", iyl.d().e(securityDeviceId), " identify size: ", iyl.d().e(deviceIdentify), " isOversea is ", Boolean.valueOf(Utils.o()));
            udidFromDevice = FoundationCommonUtil.getEncryptionLogMark(securityDeviceId, deviceIdentify, productType);
        }
        if (softVersion == null || "".equals(softVersion)) {
            softVersion = "00.00.00";
        }
        String format = new SimpleDateFormat(HwDrmConstant.TIME_FORMAT, Locale.ENGLISH).format(new Date());
        if (!TextUtils.isEmpty(deviceModel) && cvz.e(deviceModel)) {
            str = e + "-HONOR_" + softVersion + "_" + udidFromDevice + "_";
        } else {
            str = e + "_" + softVersion + "_" + udidFromDevice + "_";
        }
        String str2 = str + format + "_WearableBeta";
        if (CommonUtil.bv()) {
            str2 = str2.replace("_WearableBeta", "_WearableCommercial");
        }
        LogUtil.a("MaintenanceUtil", "getDetectFileName deviceVersion targetPath: ", str2);
        return str2;
    }
}
