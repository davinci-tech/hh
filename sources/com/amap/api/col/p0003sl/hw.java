package com.amap.api.col.p0003sl;

import android.content.Context;
import android.util.Log;
import com.huawei.hwsmartinteractmgr.data.SmartMsgConstant;
import com.huawei.openalliance.ad.constant.Constants;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.Iterator;
import org.json.JSONObject;

/* loaded from: classes2.dex */
public final class hw {

    /* renamed from: a, reason: collision with root package name */
    private static volatile b f1154a = b.Unknow;
    private static volatile d b = d.Unknow;
    private static volatile String c = "";
    private static volatile String d = "";
    private static volatile long e = -1;
    private static volatile a f = a.Unknow;
    private static volatile long g = -1;
    private static volatile String h = "";
    private static volatile String i = "";
    private static volatile long j = 0;
    private static volatile long k = 0;
    private static volatile boolean l = false;
    private static volatile boolean m = true;

    public enum d {
        Unknow(-1),
        NotShow(0),
        DidShow(1);

        private int d;

        d(int i) {
            this.d = i;
        }

        public final int a() {
            return this.d;
        }

        public static d a(int i) {
            d dVar = NotShow;
            if (i == dVar.a()) {
                return dVar;
            }
            d dVar2 = DidShow;
            return i == dVar2.a() ? dVar2 : Unknow;
        }
    }

    public enum b {
        Unknow(-1),
        NotContain(0),
        DidContain(1);

        private int d;

        b(int i) {
            this.d = i;
        }

        public final int a() {
            return this.d;
        }

        public static b a(int i) {
            b bVar = NotContain;
            if (i == bVar.a()) {
                return bVar;
            }
            b bVar2 = DidContain;
            return i == bVar2.a() ? bVar2 : Unknow;
        }
    }

    public enum a {
        Unknow(-1),
        NotAgree(0),
        DidAgree(1);

        private int d;

        a(int i) {
            this.d = i;
        }

        public final int a() {
            return this.d;
        }

        public static a a(int i) {
            a aVar = NotAgree;
            if (i == aVar.a()) {
                return aVar;
            }
            a aVar2 = DidAgree;
            return i == aVar2.a() ? aVar2 : Unknow;
        }
    }

    public enum c {
        SuccessCode(0),
        ShowUnknowCode(555570),
        ShowNoShowCode(555571),
        InfoUnknowCode(555572),
        InfoNotContainCode(555573),
        AgreeUnknowCode(555574),
        AgreeNotAgreeCode(555575),
        InvaildUserKeyCode(10001),
        IllegalArgument(20001);

        private final int j;

        c(int i) {
            this.j = i;
        }

        public final int a() {
            return this.j;
        }
    }

    public static void a(Context context, boolean z, boolean z2, hz hzVar) {
        d dVar;
        b bVar;
        if (z2) {
            dVar = d.DidShow;
        } else {
            dVar = d.NotShow;
        }
        if (z) {
            bVar = b.DidContain;
        } else {
            bVar = b.NotContain;
        }
        a(context, dVar, bVar, hzVar);
    }

    private static void a(Context context, d dVar, b bVar, hz hzVar) {
        synchronized (hw.class) {
            if (context == null || hzVar == null) {
                return;
            }
            if (!l) {
                e(context);
                l = true;
            }
            Boolean bool = Boolean.FALSE;
            if (dVar != b) {
                bool = Boolean.TRUE;
                b = dVar;
            }
            if (bVar != f1154a) {
                bool = Boolean.TRUE;
                f1154a = bVar;
            }
            if (bool.booleanValue()) {
                c = hzVar.a();
                d = hzVar.b();
                long currentTimeMillis = System.currentTimeMillis();
                e = currentTimeMillis;
                j = currentTimeMillis;
                d(context);
            }
        }
    }

    public static void a(Context context, boolean z, hz hzVar) {
        a aVar;
        if (z) {
            aVar = a.DidAgree;
        } else {
            aVar = a.NotAgree;
        }
        a(context, aVar, hzVar);
    }

    private static void a(Context context, a aVar, hz hzVar) {
        synchronized (hw.class) {
            if (context == null || hzVar == null) {
                return;
            }
            if (!l) {
                e(context);
                l = true;
            }
            Boolean bool = Boolean.FALSE;
            if (aVar != f) {
                Boolean bool2 = Boolean.TRUE;
                f = aVar;
                h = hzVar.a();
                i = hzVar.b();
                long currentTimeMillis = System.currentTimeMillis();
                g = currentTimeMillis;
                j = currentTimeMillis;
                d(context);
            }
        }
    }

    public static hx a(final Context context, hz hzVar) {
        boolean z;
        hx hxVar;
        hx hxVar2;
        synchronized (hw.class) {
            if (context == null || hzVar == null) {
                return new hx(c.IllegalArgument, hzVar);
            }
            if (!l) {
                e(context);
                l = true;
            }
            hx hxVar3 = null;
            if (b != d.DidShow) {
                if (b == d.Unknow) {
                    hxVar2 = new hx(c.ShowUnknowCode, hzVar);
                } else {
                    if (b == d.NotShow) {
                        hxVar2 = new hx(c.ShowNoShowCode, hzVar);
                    }
                    z = false;
                }
                hxVar3 = hxVar2;
                z = false;
            } else {
                z = true;
            }
            if (z && f1154a != b.DidContain) {
                if (f1154a == b.Unknow) {
                    hxVar = new hx(c.InfoUnknowCode, hzVar);
                } else {
                    if (f1154a == b.NotContain) {
                        hxVar = new hx(c.InfoNotContainCode, hzVar);
                    }
                    z = false;
                }
                hxVar3 = hxVar;
                z = false;
            }
            if (z && f != a.DidAgree) {
                if (f == a.Unknow) {
                    hxVar3 = new hx(c.AgreeUnknowCode, hzVar);
                } else if (f == a.NotAgree) {
                    hxVar3 = new hx(c.AgreeNotAgreeCode, hzVar);
                }
                z = false;
            }
            if (k != j) {
                final long j2 = j;
                k = j;
                try {
                    final JSONObject jSONObject = new JSONObject();
                    jSONObject.put("privacyInfo", f1154a.a());
                    jSONObject.put("privacyShow", b.a());
                    jSONObject.put(SmartMsgConstant.MSG_SHOW_TIME, e);
                    jSONObject.put("show2SDK", c);
                    jSONObject.put("show2SDKVer", d);
                    jSONObject.put("privacyAgree", f.a());
                    jSONObject.put("agreeTime", g);
                    jSONObject.put("agree2SDK", h);
                    jSONObject.put("agree2SDKVer", i);
                    final boolean z2 = m;
                    la.a().a(new lb() { // from class: com.amap.api.col.3sl.hw.2
                        @Override // com.amap.api.col.p0003sl.lb
                        public final void runTask() {
                            if (z2) {
                                Iterator it = hw.b(hw.f(context)).iterator();
                                while (it.hasNext()) {
                                    hw.a(context, ((File) it.next()).getName());
                                }
                            }
                            hw.d(context);
                            hw.a(context, jSONObject, j2);
                            boolean b2 = hw.b(context, jSONObject);
                            if (b2) {
                                hw.b(context, hw.b(j2));
                            }
                            if (z2) {
                                hw.b(context);
                            }
                            if (b2) {
                                return;
                            }
                            hw.a(context, hw.b(j2));
                        }
                    });
                } catch (Throwable unused) {
                }
            } else if (m) {
                la.a().a(new lb() { // from class: com.amap.api.col.3sl.hw.1
                    @Override // com.amap.api.col.p0003sl.lb
                    public final void runTask() {
                        Iterator it = hw.b(hw.f(context)).iterator();
                        while (it.hasNext()) {
                            hw.a(context, ((File) it.next()).getName());
                        }
                        hw.b(context);
                    }
                });
            }
            m = false;
            String f2 = hn.f(context);
            if (f2 == null || f2.length() <= 0) {
                hxVar3 = new hx(c.InvaildUserKeyCode, hzVar);
                Log.e(hzVar.a(), String.format("获取apikey失败：\nerrorCode : %d\n原因：%s", Integer.valueOf(hxVar3.f1161a.a()), hxVar3.b));
            }
            if (z) {
                hxVar3 = new hx(c.SuccessCode, hzVar);
            } else {
                Log.e(hzVar.a(), String.format("隐私合规校验失败：\nerrorCode : %d\n原因：%s", Integer.valueOf(hxVar3.f1161a.a()), hxVar3.b));
            }
            return hxVar3;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static void d(Context context) {
        synchronized (hw.class) {
            if (context == null) {
                return;
            }
            if (!l) {
                e(context);
                l = true;
            }
            try {
                ji.a(context, "AMap.privacy.data", "AMap.privacy.data", String.format("%d&%d&%d&%s&%s&%d&%d&%s&%s&%d&%d", Integer.valueOf(f1154a.a()), Integer.valueOf(b.a()), Long.valueOf(e), c, d, Integer.valueOf(f.a()), Long.valueOf(g), h, i, Long.valueOf(j), Long.valueOf(k)));
            } catch (Throwable th) {
                th.printStackTrace();
            }
        }
    }

    private static void e(Context context) {
        String str;
        if (context == null) {
            return;
        }
        try {
            str = ji.a(context, "AMap.privacy.data", "AMap.privacy.data");
        } catch (Throwable th) {
            th.printStackTrace();
            str = null;
        }
        if (str == null) {
            return;
        }
        String[] split = str.split("&");
        if (split.length != 11) {
            return;
        }
        try {
            f1154a = b.a(Integer.parseInt(split[0]));
            b = d.a(Integer.parseInt(split[1]));
            e = Long.parseLong(split[2]);
            d = split[3];
            d = split[4];
            f = a.a(Integer.parseInt(split[5]));
            g = Long.parseLong(split[6]);
            h = split[7];
            i = split[8];
            j = Long.parseLong(split[9]);
            k = Long.parseLong(split[10]);
        } catch (Throwable th2) {
            th2.printStackTrace();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static boolean b(Context context, JSONObject jSONObject) {
        try {
            ix ixVar = new ix();
            ixVar.b = context;
            ixVar.f1207a = jSONObject;
            new jt();
            kb a2 = jt.a(ixVar);
            if (a2 != null) {
                JSONObject jSONObject2 = new JSONObject(ia.a(a2.f1250a));
                if (jSONObject2.has("status")) {
                    if (jSONObject2.getInt("status") == 1) {
                        return true;
                    }
                }
            }
        } catch (Throwable th) {
            th.printStackTrace();
        }
        return false;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static String b(long j2) {
        return String.format("%d-%s", Long.valueOf(j2), "privacy.data");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static String f(Context context) {
        return context.getFilesDir().getAbsolutePath() + "/AMap/Privacy/Upload";
    }

    private static String g(Context context) {
        return context.getFilesDir().getAbsolutePath() + "/AMap/Privacy/Reload";
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static ArrayList<File> b(String str) {
        ArrayList<File> arrayList = new ArrayList<>();
        if (str != null && str.length() != 0) {
            File file = new File(str);
            if (!file.exists()) {
                return arrayList;
            }
            File[] listFiles = file.listFiles();
            for (File file2 : listFiles) {
                if (file2.isFile()) {
                    arrayList.add(file2);
                }
            }
        }
        return arrayList;
    }

    static /* synthetic */ void a(Context context, String str) {
        if (str == null || str.length() == 0) {
            return;
        }
        try {
            File file = new File(f(context) + "/" + str);
            if (file.exists()) {
                File file2 = new File(g(context) + "/" + str);
                if (!file2.getParentFile().exists()) {
                    file2.getParentFile().mkdirs();
                }
                file.renameTo(file2);
            }
        } catch (Throwable th) {
            th.printStackTrace();
        }
    }

    static /* synthetic */ void b(Context context) {
        try {
            Iterator<File> it = b(g(context)).iterator();
            while (it.hasNext()) {
                File next = it.next();
                try {
                    String name = next.getName();
                    if (!name.endsWith("-privacy.data")) {
                        next.delete();
                    } else {
                        String[] split = name.split(Constants.LINK);
                        if (split == null && split.length != 2) {
                            next.delete();
                        } else if (Long.parseLong(split[0]) <= 0) {
                            next.delete();
                        } else {
                            FileInputStream fileInputStream = new FileInputStream(next);
                            byte[] bArr = new byte[fileInputStream.available()];
                            fileInputStream.read(bArr);
                            if (b(context, new JSONObject(new String(ji.b(context, bArr))))) {
                                next.delete();
                            }
                        }
                    }
                } catch (Throwable th) {
                    th.printStackTrace();
                }
            }
        } catch (Throwable th2) {
            th2.printStackTrace();
        }
    }

    static /* synthetic */ void a(Context context, JSONObject jSONObject, long j2) {
        Throwable th;
        FileOutputStream fileOutputStream;
        byte[] a2;
        try {
            a2 = ji.a(context, jSONObject.toString().getBytes());
            String b2 = b(j2);
            File file = new File(f(context) + "/" + b2);
            if (!file.getParentFile().exists()) {
                file.getParentFile().mkdirs();
            }
            fileOutputStream = new FileOutputStream(file);
        } catch (Throwable th2) {
            th = th2;
            fileOutputStream = null;
        }
        try {
            fileOutputStream.write(a2);
            try {
                fileOutputStream.close();
            } catch (Throwable th3) {
                th3.printStackTrace();
            }
        } catch (Throwable th4) {
            th = th4;
            try {
                th.printStackTrace();
                if (fileOutputStream != null) {
                    try {
                        fileOutputStream.close();
                    } catch (Throwable th5) {
                        th5.printStackTrace();
                    }
                }
            } catch (Throwable th6) {
                if (fileOutputStream != null) {
                    try {
                        fileOutputStream.close();
                    } catch (Throwable th7) {
                        th7.printStackTrace();
                    }
                }
                throw th6;
            }
        }
    }

    static /* synthetic */ void b(Context context, String str) {
        if (str == null || str.length() == 0) {
            return;
        }
        try {
            File file = new File(f(context) + "/" + str);
            if (file.exists()) {
                file.delete();
            }
        } catch (Throwable th) {
            th.printStackTrace();
        }
    }
}
