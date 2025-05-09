package defpackage;

import com.huawei.health.device.kit.wsp.task.BleTaskQueueUtil;
import com.huawei.operation.ble.BleConstants;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.UUID;

/* loaded from: classes3.dex */
public class chb {

    /* renamed from: a, reason: collision with root package name */
    public static final UUID f715a;
    public static final UUID aa;
    public static final UUID ab;
    public static final UUID ac;
    public static final UUID ad;
    public static final UUID ae;
    public static final Set<String> af;
    public static final UUID ag;
    public static final UUID ah;
    public static final UUID ai;
    public static final UUID aj;
    public static final UUID ak;
    public static final UUID al;
    public static final UUID am;
    public static final UUID an;
    public static final UUID ao;
    public static final UUID ap;
    public static final UUID aq;
    public static final Set<String> ar;
    public static final UUID as;
    public static final UUID at;
    private static Map<UUID, Integer> au;
    private static Map<BleTaskQueueUtil.TaskType, c> av;
    public static final UUID aw;
    public static final UUID ax;
    public static final UUID b;
    public static final UUID c;
    public static final UUID d;
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
    public static final UUID w;
    public static final UUID x;
    public static final UUID y;
    public static final UUID z;

    static {
        UUID fromString = UUID.fromString("0000181c-0000-1000-8000-00805f9b34fb");
        ao = fromString;
        UUID fromString2 = UUID.fromString("00002a80-0000-1000-8000-00805f9b34fb");
        f715a = fromString2;
        UUID fromString3 = UUID.fromString("00002a8c-0000-1000-8000-00805f9b34fb");
        n = fromString3;
        UUID fromString4 = UUID.fromString("00002a8e-0000-1000-8000-00805f9b34fb");
        q = fromString4;
        UUID fromString5 = UUID.fromString("02b2a08e-f8b0-4047-b1fd-f4e0efeee679");
        s = fromString5;
        UUID fromString6 = UUID.fromString("32330a04-15d9-421a-91c5-2a2d5c7525c9");
        d = fromString6;
        UUID fromString7 = UUID.fromString("a3d330f8-b84f-4f48-a78c-f8d1e33b597a");
        ac = fromString7;
        UUID fromString8 = UUID.fromString("42596cbe-d291-4da3-8ca6-d1ae5d1c9174");
        c = fromString8;
        UUID fromString9 = UUID.fromString("8cc61d7d-66c0-4802-89c3-38c5a163592e");
        z = fromString9;
        o = UUID.fromString("4338c65e-ed8e-4085-bbea-a25e33ca6b54");
        y = UUID.fromString("3a3a7d71-37f6-4b94-b44a-21ac1916ac4f");
        i = UUID.fromString("2acf269d-6dae-4a64-98a4-dbf98c2e66c4");
        UUID fromString10 = UUID.fromString("319d46ad-df02-4be5-b80f-6aa6f9c69329");
        g = fromString10;
        j = UUID.fromString("4599778a-ea8a-4ffe-9499-de1ba7a03f3a");
        h = UUID.fromString("7749db19-9db5-4430-b6e2-5fcfb3f5b988");
        t = UUID.fromString("78bdadc1-5cae-4ec4-8832-21a020b1080b");
        UUID fromString11 = UUID.fromString("00001805-0000-1000-8000-00805f9b34fb");
        an = fromString11;
        UUID fromString12 = UUID.fromString("00002a2b-0000-1000-8000-00805f9b34fb");
        f = fromString12;
        l = UUID.fromString("75143e79-f878-4a00-a628-edc40509de7e");
        e = UUID.fromString("426f058d-8211-413e-8320-397a890a08bf");
        k = UUID.fromString("1f5d3d5c-496d-4290-af03-c7a8d5419741");
        aa = UUID.fromString("11872f15-a91d-49da-ac89-5107284f3425");
        m = UUID.fromString("7e6dbc73-42e7-45b9-a6ec-6aa2d7834695");
        UUID fromString13 = UUID.fromString("0000181d-0000-1000-8000-00805f9b34fb");
        ap = fromString13;
        UUID fromString14 = UUID.fromString("00002a9d-0000-1000-8000-00805f9b34fb");
        ad = fromString14;
        UUID fromString15 = UUID.fromString("0000181b-0000-1000-8000-00805f9b34fb");
        am = fromString15;
        UUID fromString16 = UUID.fromString("00002a9c-0000-1000-8000-00805f9b34fb");
        b = fromString16;
        ag = UUID.fromString("46797c17-d639-488d-9476-4789e8472878");
        ae = UUID.fromString("0212f42a-5f19-4bc1-ba52-d7ec7ccb71a4");
        ab = UUID.fromString(BleConstants.CLIENT_CHARACTERISTIC_CONFIG_DESCRIPTOR_UUID);
        r = UUID.fromString("bfc36f6e-4150-4a4b-9052-3d359e52962e");
        ak = UUID.fromString("0000fff0-0000-1000-8000-00805f9b34fb");
        v = UUID.fromString("0000fff1-0000-1000-8000-00805f9b34fb");
        w = UUID.fromString("0000fff2-0000-1000-8000-00805f9b34fb");
        x = UUID.fromString("0000fff3-0000-1000-8000-00805f9b34fb");
        u = UUID.fromString("0000fff4-0000-1000-8000-00805f9b34fb");
        aj = UUID.fromString("00001880-0000-1000-8000-00805f9b34fb");
        p = UUID.fromString("ba216311-1787-472b-bef6-3eb29e62293e");
        aw = UUID.fromString("e3c7a876-3307-414d-84f6-fbcd64710dc3");
        at = UUID.fromString("33ff0cad-834f-4e42-ab91-a0747856d574");
        ah = UUID.fromString("b4e384f2-d880-44e7-8c7e-5249239633b4");
        aq = UUID.fromString("0000fe86-0000-1000-8000-00805f9b34fb");
        ai = UUID.fromString("000018a1-0000-1000-8000-00805f9b34fb");
        al = UUID.fromString("000018a0-0000-1000-8000-00805f9b34fb");
        ax = UUID.fromString("0000fe01-0000-1000-8000-00805f9b34fb");
        as = UUID.fromString("0000fe02-0000-1000-8000-00805f9b34fb");
        af = Collections.unmodifiableSet(new HashSet<String>() { // from class: chb.5
            {
                add(chb.p.toString());
                add(chb.ak.toString());
                add(chb.v.toString());
                add(chb.x.toString());
                add(chb.ae.toString());
                add(chb.as.toString());
            }
        });
        ar = Collections.unmodifiableSet(new HashSet<String>() { // from class: chb.4
            {
                add(chb.p.toString());
                add(chb.at.toString());
                add(chb.ah.toString());
                add(chb.y.toString());
                add(chb.i.toString());
                add(chb.ac.toString());
                add(chb.v.toString());
                add(chb.w.toString());
                add(chb.x.toString());
                add(chb.u.toString());
                add(chb.j.toString());
                add(chb.h.toString());
                add(chb.aa.toString());
                add(chb.m.toString());
                add(chb.l.toString());
                add(chb.z.toString());
                add(chb.f.toString());
                add(chb.e.toString());
                add(chb.g.toString());
                add(chb.ai.toString());
            }
        });
        HashMap<BleTaskQueueUtil.TaskType, c> hashMap = new HashMap<BleTaskQueueUtil.TaskType, c>(16) { // from class: chb.3
            public static final long serialVersionUID = -5758607221325354320L;

            {
                put(BleTaskQueueUtil.TaskType.GET_MANAGER_INFO, new c(chb.ao, chb.o));
                put(BleTaskQueueUtil.TaskType.SET_MANAGER_INFO, new c(chb.ao, chb.y));
                put(BleTaskQueueUtil.TaskType.SEND_HILINK_CONFIG_INFO, new c(chb.ao, chb.i));
                put(BleTaskQueueUtil.TaskType.SEND_DEVICE_RESET, new c(chb.ao, chb.j));
                put(BleTaskQueueUtil.TaskType.SEND_DELETE_USER_DATA, new c(chb.ao, chb.h));
                put(BleTaskQueueUtil.TaskType.SEND_GET_USER_DATA, new c(chb.ao, chb.t));
                put(BleTaskQueueUtil.TaskType.GET_DEVICE_SSID, new c(chb.an, chb.l));
                put(BleTaskQueueUtil.TaskType.GET_ALLOW_RESET_WIFI, new c(chb.an, chb.e));
                put(BleTaskQueueUtil.TaskType.GET_WEIGHT_REAL_TIME_DATA, new c(chb.am, chb.ag));
                put(BleTaskQueueUtil.TaskType.REQUEST_OTA_UPGRADE, new c(chb.ak, chb.v));
                put(BleTaskQueueUtil.TaskType.OTA_UPGRADE_SHA_CHECK, new c(chb.ak, chb.w));
                put(BleTaskQueueUtil.TaskType.SEND_OTA_PACKAGE_DATA, new c(chb.ak, chb.x));
                put(BleTaskQueueUtil.TaskType.GET_OTA_UPGRADE_RESULT, new c(chb.ak, chb.u));
                put(BleTaskQueueUtil.TaskType.GET_SCALE_VERSION, new c(chb.an, chb.k));
                put(BleTaskQueueUtil.TaskType.GET_WEIGHT_HISTORY_DATA, new c(chb.am, chb.ae));
                put(BleTaskQueueUtil.TaskType.SET_WEIGHT_UNIT, new c(chb.an, chb.aa));
                put(BleTaskQueueUtil.TaskType.GET_WEIGHT_UNIT, new c(chb.an, chb.m));
                put(BleTaskQueueUtil.TaskType.SEND_WAKE_UP, new c(chb.an, chb.aw));
                put(BleTaskQueueUtil.TaskType.SEND_SSID, new c(chb.an, chb.at));
                put(BleTaskQueueUtil.TaskType.SEND_WIFI_PASSWORD, new c(chb.an, chb.ah));
                put(BleTaskQueueUtil.TaskType.OPEN_STATUS, new c(chb.an, chb.p));
                put(BleTaskQueueUtil.TaskType.WRITE_BLE_FILE, new c(chb.aq, chb.ax));
                put(BleTaskQueueUtil.TaskType.NOTIFY_BLE_FILE, new c(chb.aq, chb.as));
                put(BleTaskQueueUtil.TaskType.SEND_OTA_URL, new c(chb.al, chb.ai));
            }
        };
        av = hashMap;
        hashMap.put(BleTaskQueueUtil.TaskType.SET_AGE, new c(fromString, fromString2));
        av.put(BleTaskQueueUtil.TaskType.SET_GENDER, new c(fromString, fromString3));
        av.put(BleTaskQueueUtil.TaskType.SET_HEIGHT, new c(fromString, fromString4));
        av.put(BleTaskQueueUtil.TaskType.SET_SYNC_TIME, new c(fromString11, fromString12));
        av.put(BleTaskQueueUtil.TaskType.ENABLE_WEIGHT_SCALE, new c(fromString13, fromString14));
        av.put(BleTaskQueueUtil.TaskType.ENABLE_BODY_MEASUREMENT, new c(fromString15, fromString16));
        av.put(BleTaskQueueUtil.TaskType.REQUEST_AUTH, new c(fromString, fromString5));
        av.put(BleTaskQueueUtil.TaskType.AUTH_TOKEN, new c(fromString, fromString6));
        av.put(BleTaskQueueUtil.TaskType.SEND_WORK_KEY, new c(fromString, fromString7));
        av.put(BleTaskQueueUtil.TaskType.BIND_REQUEST, new c(fromString, fromString8));
        av.put(BleTaskQueueUtil.TaskType.SET_USER_INFO, new c(fromString, fromString9));
        av.put(BleTaskQueueUtil.TaskType.SEND_HILINK_CONFIG_INFO_ENCRYPTED, new c(fromString, fromString10));
        au = new HashMap<UUID, Integer>(16) { // from class: chb.2
            public static final long serialVersionUID = -5753607221225351370L;

            {
                put(chb.f715a, Integer.valueOf(BleTaskQueueUtil.TaskType.SET_AGE.ordinal()));
                put(chb.n, Integer.valueOf(BleTaskQueueUtil.TaskType.SET_GENDER.ordinal()));
                put(chb.q, Integer.valueOf(BleTaskQueueUtil.TaskType.SET_HEIGHT.ordinal()));
                put(chb.f, Integer.valueOf(BleTaskQueueUtil.TaskType.SET_SYNC_TIME.ordinal()));
                put(chb.ad, Integer.valueOf(BleTaskQueueUtil.TaskType.ENABLE_WEIGHT_SCALE.ordinal()));
                put(chb.b, Integer.valueOf(BleTaskQueueUtil.TaskType.ENABLE_BODY_MEASUREMENT.ordinal()));
                put(chb.s, Integer.valueOf(BleTaskQueueUtil.TaskType.REQUEST_AUTH.ordinal()));
                put(chb.d, Integer.valueOf(BleTaskQueueUtil.TaskType.AUTH_TOKEN.ordinal()));
                put(chb.ac, Integer.valueOf(BleTaskQueueUtil.TaskType.SEND_WORK_KEY.ordinal()));
                put(chb.c, Integer.valueOf(BleTaskQueueUtil.TaskType.BIND_REQUEST.ordinal()));
                put(chb.z, Integer.valueOf(BleTaskQueueUtil.TaskType.SET_USER_INFO.ordinal()));
                put(chb.o, Integer.valueOf(BleTaskQueueUtil.TaskType.GET_MANAGER_INFO.ordinal()));
                put(chb.y, Integer.valueOf(BleTaskQueueUtil.TaskType.SET_MANAGER_INFO.ordinal()));
                put(chb.i, Integer.valueOf(BleTaskQueueUtil.TaskType.SEND_HILINK_CONFIG_INFO.ordinal()));
                put(chb.j, Integer.valueOf(BleTaskQueueUtil.TaskType.SEND_DEVICE_RESET.ordinal()));
                put(chb.h, Integer.valueOf(BleTaskQueueUtil.TaskType.SEND_DELETE_USER_DATA.ordinal()));
                put(chb.t, Integer.valueOf(BleTaskQueueUtil.TaskType.SEND_GET_USER_DATA.ordinal()));
                put(chb.l, Integer.valueOf(BleTaskQueueUtil.TaskType.GET_DEVICE_SSID.ordinal()));
                put(chb.e, Integer.valueOf(BleTaskQueueUtil.TaskType.GET_ALLOW_RESET_WIFI.ordinal()));
                put(chb.ag, Integer.valueOf(BleTaskQueueUtil.TaskType.GET_WEIGHT_REAL_TIME_DATA.ordinal()));
                put(chb.v, Integer.valueOf(BleTaskQueueUtil.TaskType.REQUEST_OTA_UPGRADE.ordinal()));
                put(chb.w, Integer.valueOf(BleTaskQueueUtil.TaskType.OTA_UPGRADE_SHA_CHECK.ordinal()));
                put(chb.x, Integer.valueOf(BleTaskQueueUtil.TaskType.SEND_OTA_PACKAGE_DATA.ordinal()));
                put(chb.u, Integer.valueOf(BleTaskQueueUtil.TaskType.GET_OTA_UPGRADE_RESULT.ordinal()));
                put(chb.k, Integer.valueOf(BleTaskQueueUtil.TaskType.GET_SCALE_VERSION.ordinal()));
                put(chb.ae, Integer.valueOf(BleTaskQueueUtil.TaskType.GET_WEIGHT_HISTORY_DATA.ordinal()));
                put(chb.aa, Integer.valueOf(BleTaskQueueUtil.TaskType.SET_WEIGHT_UNIT.ordinal()));
                put(chb.m, Integer.valueOf(BleTaskQueueUtil.TaskType.GET_WEIGHT_UNIT.ordinal()));
                put(chb.ax, Integer.valueOf(BleTaskQueueUtil.TaskType.WRITE_BLE_FILE.ordinal()));
                put(chb.g, Integer.valueOf(BleTaskQueueUtil.TaskType.SEND_HILINK_CONFIG_INFO_ENCRYPTED.ordinal()));
                put(chb.ai, Integer.valueOf(BleTaskQueueUtil.TaskType.SEND_OTA_URL.ordinal()));
                put(chb.aw, Integer.valueOf(BleTaskQueueUtil.TaskType.SEND_WAKE_UP.ordinal()));
                put(chb.at, Integer.valueOf(BleTaskQueueUtil.TaskType.SEND_SSID.ordinal()));
                put(chb.ah, Integer.valueOf(BleTaskQueueUtil.TaskType.SEND_WIFI_PASSWORD.ordinal()));
                put(chb.p, Integer.valueOf(BleTaskQueueUtil.TaskType.OPEN_STATUS.ordinal()));
                put(chb.as, Integer.valueOf(BleTaskQueueUtil.TaskType.NOTIFY_BLE_FILE.ordinal()));
            }
        };
    }

    public static Map<UUID, Integer> a() {
        return au;
    }

    public static Map<BleTaskQueueUtil.TaskType, c> c() {
        return av;
    }

    public static UUID a(UUID uuid) {
        Integer num = au.get(uuid);
        BleTaskQueueUtil.TaskType[] values = BleTaskQueueUtil.TaskType.values();
        if (values == null || values.length <= num.intValue()) {
            return null;
        }
        return av.get(values[num.intValue()]).e();
    }

    public static class c {

        /* renamed from: a, reason: collision with root package name */
        private UUID f716a;
        private UUID b;

        c(UUID uuid, UUID uuid2) {
            this.b = uuid;
            this.f716a = uuid2;
        }

        public UUID e() {
            return this.b;
        }

        public UUID c() {
            return this.f716a;
        }
    }
}
