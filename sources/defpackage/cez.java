package defpackage;

import com.huawei.haf.application.BaseApplication;
import com.huawei.operation.ble.BleConstants;
import health.compact.a.CommonUtil;
import java.io.File;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

/* loaded from: classes.dex */
public class cez {

    /* renamed from: a, reason: collision with root package name */
    public static final UUID f671a;
    public static final UUID aa;
    public static final UUID ab;
    public static final Set<String> ac;
    public static final UUID ad;
    public static final UUID ae;
    public static final UUID af;
    public static final Set<String> ag;
    public static final UUID ah;
    public static final UUID ai;
    public static final Set<String> ak;
    public static final Set<Integer> am;
    public static final UUID an;
    public static final String b;
    public static final UUID c;
    public static final String d;
    public static final UUID e;
    public static final UUID f;
    public static final UUID g;
    public static final UUID h;
    public static final UUID i;
    public static final UUID j;
    public static final UUID k;
    public static final UUID l;
    public static final UUID m;
    public static final UUID n;
    public static final UUID o;
    public static final UUID p;
    public static final UUID q;
    public static final UUID r;
    public static final UUID s;
    public static final UUID t;
    public static final UUID u;
    public static final UUID v;
    public static final String w;
    public static final UUID x;
    public static final UUID y;
    public static final UUID z;

    static {
        HashSet hashSet = new HashSet(3);
        ac = hashSet;
        HashSet hashSet2 = new HashSet(4);
        ak = hashSet2;
        HashSet hashSet3 = new HashSet(5);
        ag = hashSet3;
        HashSet hashSet4 = new HashSet();
        am = hashSet4;
        aa = UUID.fromString("00001810-0000-1000-8000-00805f9b34fb");
        e = UUID.fromString("00002A35-0000-1000-8000-00805f9b34fb");
        s = UUID.fromString("00002A08-0000-1000-8000-00805f9b34fb");
        r = UUID.fromString("00001805-0000-1000-8000-00805f9b34fb");
        p = UUID.fromString("00002A2B-0000-1000-8000-00805f9b34fb");
        ah = UUID.fromString("473ef714-9b8c-4a3b-bd79-c843137e803f");
        n = UUID.fromString(BleConstants.CLIENT_CHARACTERISTIC_CONFIG_DESCRIPTOR_UUID);
        u = UUID.fromString("00002a51-0000-1000-8000-00805f9b34fb");
        an = UUID.fromString("00002a50-0000-1000-8000-00805f9b34fb");
        y = UUID.fromString("0000fff0-0000-1000-8000-00805f9b34fb");
        x = UUID.fromString("0000fff1-0000-1000-8000-00805f9b34fb");
        v = UUID.fromString("0000fff2-0000-1000-8000-00805f9b34fb");
        z = UUID.fromString("00001808-0000-1000-8000-00805f9b34fb");
        g = UUID.fromString("00002A18-0000-1000-8000-00805f9b34fb");
        m = UUID.fromString("00002A52-0000-1000-8000-00805f9b34fb");
        q = UUID.fromString("0000180d-0000-1000-8000-00805f9b34fb");
        j = UUID.fromString("00002a37-0000-1000-8000-00805f9b34fb");
        t = UUID.fromString("00002a38-0000-1000-8000-00805f9b34fb");
        ai = UUID.fromString("0000181c-0000-1000-8000-00805f9b34fb");
        f671a = UUID.fromString("00002a80-0000-1000-8000-00805f9b34fb");
        f = UUID.fromString("00002a8c-0000-1000-8000-00805f9b34fb");
        h = UUID.fromString("00002a8e-0000-1000-8000-00805f9b34fb");
        ad = UUID.fromString("00001805-0000-1000-8000-00805f9b34fb");
        i = UUID.fromString("00002a2b-0000-1000-8000-00805f9b34fb");
        ae = UUID.fromString("0000181d-0000-1000-8000-00805f9b34fb");
        o = UUID.fromString("00002a9d-0000-1000-8000-00805f9b34fb");
        ab = UUID.fromString("0000181b-0000-1000-8000-00805f9b34fb");
        c = UUID.fromString("00002a9c-0000-1000-8000-00805f9b34fb");
        af = UUID.fromString("0000faa0-0000-1000-8000-00805f9b34fb");
        k = UUID.fromString("0000faa1-0000-1000-8000-00805f9b34fb");
        l = UUID.fromString("0000faa2-0000-1000-8000-00805f9b34fb");
        w = BaseApplication.d();
        String str = cos.f11394a + "am16Rs";
        b = str;
        d = str + File.separator + "6d5416d9-2167-41df-ab10-c492e152b44f.zip";
        hashSet.add("2G97");
        hashSet.add("2G98");
        hashSet.add("2IX6");
        hashSet3.add("2G97");
        hashSet3.add("2G98");
        hashSet3.add("O03P");
        hashSet3.add("2GYA");
        hashSet3.add("2IX6");
        hashSet4.add(6);
        hashSet4.add(7);
        hashSet4.add(11);
        hashSet2.add("2G97");
        hashSet2.add("2G98");
        hashSet2.add("2IX6");
        hashSet2.add("O03P");
    }

    public static boolean c() {
        return CommonUtil.as() || CommonUtil.cg() || CommonUtil.cc() || CommonUtil.aq();
    }
}
