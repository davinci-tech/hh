package com.careforeyou.library;

import android.bluetooth.BluetoothGattCharacteristic;
import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import android.os.SystemClock;
import android.text.TextUtils;
import com.careforeyou.library.bean.CsFatScale;
import com.careforeyou.library.bean.RoleInfo;
import com.careforeyou.library.bean.WeightEntity;
import com.careforeyou.library.enums.Protocal_Type;
import com.careforeyou.library.intface.BIACallback;
import com.careforeyou.library.intface.OnBluetoothListener;
import com.careforeyou.library.intface.OnWeightScalesListener;
import com.careforeyou.library.utils.CsBtUtil_v11;
import com.huawei.operation.ble.BleConstants;
import com.huawei.operation.jsoperation.JsUtil;
import defpackage.ng;
import defpackage.nh;
import defpackage.nj;
import defpackage.nk;
import defpackage.nr;
import defpackage.nu;
import defpackage.nz;
import defpackage.oa;
import defpackage.ob;
import java.util.ArrayList;
import java.util.Date;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes2.dex */
public class BIAWorker implements OnBluetoothListener, OnWeightScalesListener {
    private static volatile BIAWorker b;

    /* renamed from: a, reason: collision with root package name */
    private boolean f1656a;
    private Runnable ac;
    private int ad;
    private BIACallback c;
    private boolean e;
    private String g;
    private int h;
    private Context j;
    private Handler l;
    private boolean n;
    private nk p;
    private ArrayList<nh> q;
    private RoleInfo u;
    private String w;
    private int x;
    private Protocal_Type y;
    private BluetoothGattCharacteristic z;
    private boolean i = false;
    private boolean v = false;
    private boolean o = true;
    private long t = 0;
    private Date s = new Date();
    private boolean m = true;
    private boolean f = true;
    private long ab = 0;
    private boolean r = false;
    private int d = 0;
    private CsBtUtil_v11 k = CsBtUtil_v11.e();

    private JSONObject c(WeightEntity weightEntity) {
        float weight = weightEntity.getWeight();
        String scaleweight = weightEntity.getScaleweight();
        int score = weightEntity.getScore();
        float bmi = weightEntity.getBmi();
        float axunge = weightEntity.getAxunge();
        float muscleWeight = weightEntity.getMuscleWeight();
        float viscera = weightEntity.getViscera();
        String bodyLeve = weightEntity.getBodyLeve();
        float metabolism = weightEntity.getMetabolism();
        float water = weightEntity.getWater();
        float bone = weightEntity.getBone();
        float protein = weightEntity.getProtein();
        float thinWeight = weightEntity.getThinWeight();
        float body_age = weightEntity.getBody_age();
        int heart = weightEntity.getHeart();
        String weight_time = weightEntity.getWeight_time();
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("weight", Float.valueOf(weight).toString());
            jSONObject.put("scaleWeight", scaleweight);
            jSONObject.put(JsUtil.SCORE, score);
            jSONObject.put(BleConstants.BMI, Float.valueOf(bmi).toString());
            jSONObject.put("axunge", Float.valueOf(axunge).toString());
            jSONObject.put("muscleWeight", Float.valueOf(muscleWeight).toString());
            jSONObject.put("viscera", Float.valueOf(viscera).toString());
            jSONObject.put("bodyLeve", bodyLeve);
            jSONObject.put("metabolism", Float.valueOf(metabolism).toString());
            jSONObject.put("water", Float.valueOf(water).toString());
            jSONObject.put("bone", Float.valueOf(bone).toString());
            jSONObject.put("protein", Float.valueOf(protein).toString());
            jSONObject.put("thinWeight", Float.valueOf(thinWeight).toString());
            jSONObject.put("body_age", Float.valueOf(body_age).toString());
            jSONObject.put("heart", heart);
            jSONObject.put("weight_time", weight_time);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return jSONObject;
    }

    public BIAWorker(Context context) {
        this.j = context;
        h();
    }

    public static BIAWorker e(Context context) {
        if (b == null) {
            synchronized (BIAWorker.class) {
                if (b == null) {
                    b = new BIAWorker(context);
                }
            }
        }
        return b;
    }

    private void a() {
        BIACallback bIACallback;
        CsBtUtil_v11 csBtUtil_v11 = this.k;
        if (csBtUtil_v11 == null) {
            return;
        }
        this.e = csBtUtil_v11.a();
        boolean e = nu.e(this.j);
        this.f1656a = e;
        if (this.e) {
            if (e || (bIACallback = this.c) == null) {
                return;
            }
            bIACallback.onState(1, "定位权限未打开");
            return;
        }
        BIACallback bIACallback2 = this.c;
        if (bIACallback2 != null) {
            bIACallback2.onState(0, "蓝牙未打开");
        }
    }

    private void h() {
        CsBtUtil_v11 csBtUtil_v11 = this.k;
        if (csBtUtil_v11 == null) {
            return;
        }
        csBtUtil_v11.a(this);
        this.k.d(this);
        this.k.c(this.j);
    }

    private RoleInfo e(JSONObject jSONObject) {
        this.u = new RoleInfo();
        if (jSONObject != null) {
            try {
                int i = jSONObject.getInt("id");
                int i2 = jSONObject.getInt("height");
                int i3 = jSONObject.getInt("sex");
                int i4 = jSONObject.getInt("age");
                double d = jSONObject.getDouble("weight");
                this.u.setId(i);
                this.u.setHeight(i2);
                this.u.setSex(i3);
                this.u.setAge(i4);
                this.u.setWeight((float) d);
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        return this.u;
    }

    public void b(String str, JSONObject jSONObject, byte[] bArr, BIACallback bIACallback) {
        this.g = str;
        this.u = e(jSONObject);
        this.c = bIACallback;
        a();
        this.d = 1;
        this.k.j();
        this.k.b();
        e(true);
        i();
    }

    public void b() {
        this.d = 0;
        CsBtUtil_v11 csBtUtil_v11 = this.k;
        if (csBtUtil_v11 != null) {
            csBtUtil_v11.d(this.j);
            e(false);
            c(false);
            this.k.j();
            oa.e("CsBtUtil_v11", "stop");
            this.k.h();
            this.k.c();
            d();
        }
        g();
    }

    private void i() {
        this.n = false;
        this.l = new Handler(Looper.getMainLooper());
        this.ac = new Runnable() { // from class: com.careforeyou.library.BIAWorker.4
            @Override // java.lang.Runnable
            public void run() {
                if (BIAWorker.this.n) {
                    BIAWorker.this.g();
                    BIAWorker.this.n = false;
                } else if (BIAWorker.this.ab > 120000) {
                    BIAWorker.this.c.onState(19, "设备搜索超时");
                    if (BIAWorker.this.k != null) {
                        BIAWorker.this.k.j();
                    }
                    BIAWorker.this.g();
                }
                BIAWorker.this.ab += 1000;
                BIAWorker.this.c();
            }
        };
        c();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void g() {
        Handler handler = this.l;
        if (handler != null) {
            handler.removeCallbacks(this.ac);
            this.l = null;
            this.ab = 0L;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void c() {
        Handler handler = this.l;
        if (handler != null) {
            handler.postDelayed(this.ac, 1000L);
        }
    }

    public void d(JSONArray jSONArray) {
        e(jSONArray);
        c(2);
    }

    private void e(boolean z) {
        this.i = z;
    }

    private void c(boolean z) {
        this.o = z;
    }

    private boolean e() {
        return this.o;
    }

    private void d() {
        this.p = null;
    }

    @Override // com.careforeyou.library.intface.OnBluetoothListener
    public void bluetoothStateChange(int i) {
        String str;
        switch (i) {
            case 2:
                str = "上报数据";
                break;
            case 3:
                str = "下发数据";
                break;
            case 4:
                str = "正在测体重";
                break;
            case 5:
                str = "正在测脂";
                break;
            case 6:
                str = "正在测心率";
                break;
            case 7:
                str = "蓝牙已关闭";
                break;
            case 8:
                str = "蓝牙已打开";
                break;
            case 9:
                str = "蓝牙扫描中";
                break;
            case 10:
                str = "发现服务";
                break;
            case 11:
                this.m = true;
                if (this.k.a() && e()) {
                    f();
                }
                str = "连接已关闭";
                break;
            case 12:
                str = "连接中";
                break;
            case 13:
                str = "已连接";
                break;
            case 14:
                str = "等待链接";
                break;
            case 15:
                str = "计算中";
                break;
            case 16:
                str = "等待测量";
                break;
            case 17:
                str = "位置权限有变化";
                break;
            default:
                str = null;
                break;
        }
        BIACallback bIACallback = this.c;
        if (bIACallback != null) {
            bIACallback.onState(i, str);
        }
    }

    private void f() {
        SystemClock.sleep(3000L);
        oa.e("CsBtUtil_v11", "mac:" + this.w + "+protocalType:+" + this.y);
        this.k.c(this.w, this.y, false);
    }

    @Override // com.careforeyou.library.intface.OnBluetoothListener
    public void bluetoothTurnOff() {
        oa.e("CsBtUtil_v11", "bluetoothTurnOff");
        this.k.j();
        if (this.h == 1) {
            oa.e("CsBtUtil_v11", "deviceType == 1");
            this.k.h();
            this.k.c();
        }
    }

    @Override // com.careforeyou.library.intface.OnBluetoothListener
    public void bluetoothTurnOn() {
        if (this.h == 1) {
            a(this.w, this.y, true);
        } else {
            this.k.j();
            this.k.b();
        }
    }

    @Override // com.careforeyou.library.intface.OnWeightScalesListener
    public void broadcastChipseaData(nr nrVar) {
        oa.e("CsBtUtil_v11", "++broadcastChipseaData+" + nrVar.toString());
        if (TextUtils.isEmpty(this.g)) {
            b(nrVar);
        } else if (this.g.equalsIgnoreCase(nrVar.c)) {
            b(nrVar);
        }
    }

    private void b(nr nrVar) {
        this.n = true;
        this.x = nrVar.f;
        this.w = nrVar.c;
        if (this.i) {
            a(nrVar);
        } else {
            e(nrVar);
        }
    }

    private void e(nr nrVar) {
        oa.e("CsBtUtil_v11", "onBLEData");
        if (this.w == null) {
            return;
        }
        WeightEntity d = nj.a(this.j).d((float) nrVar.n, nrVar.k, nrVar.i, nrVar.l, nrVar.j);
        if (this.w.equalsIgnoreCase(nrVar.c)) {
            if (nrVar.e == 1) {
                if (nrVar.d >= 2) {
                    oa.e("CsBtUtil_v11", "isWeightStartLock = 0;");
                    e(true, d);
                    return;
                } else {
                    oa.e("CsBtUtil_v11", "isWeightStartLock = 1;");
                    e(true, d);
                    return;
                }
            }
            oa.e("CsBtUtil_v11", "isWeightStartLock = 2;");
            e(false, d);
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:10:0x005c  */
    /* JADX WARN: Removed duplicated region for block: B:20:? A[RETURN, SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private void a(defpackage.nr r5) {
        /*
            r4 = this;
            java.lang.String r0 = "CsBtUtil_v11"
            java.lang.String r1 = "onBLEbound"
            defpackage.oa.e(r0, r1)
            nk r0 = r4.p
            r1 = 0
            r2 = 1
            if (r0 == 0) goto L24
            java.lang.String r0 = r0.a()
            if (r0 == 0) goto L22
            nk r0 = r4.p
            java.lang.String r0 = r0.a()
            java.lang.String r5 = r5.c
            boolean r5 = r0.equals(r5)
            if (r5 == 0) goto L22
            goto L53
        L22:
            r5 = r1
            goto L54
        L24:
            nk r0 = new nk
            r0.<init>()
            r4.p = r0
            java.lang.String r3 = r5.c
            r0.d(r3)
            nk r0 = r4.p
            byte r3 = r5.m
            r0.d(r3)
            nk r0 = r4.p
            byte r3 = r5.d
            r0.e(r3)
            nk r0 = r4.p
            int r3 = r5.j
            r0.b(r3)
            nk r0 = r4.p
            com.careforeyou.library.enums.Protocal_Type r3 = r5.g
            r0.d(r3)
            nk r0 = r4.p
            java.lang.String r5 = r5.f15451a
            r0.a(r5)
        L53:
            r5 = r2
        L54:
            r4.e(r1)
            r4.c(r2)
            if (r5 == 0) goto L86
            nk r5 = r4.p
            int r5 = r5.e()
            if (r5 != r2) goto L81
            com.careforeyou.library.utils.CsBtUtil_v11 r5 = r4.k
            r5.j()
            com.careforeyou.library.utils.CsBtUtil_v11 r5 = r4.k
            boolean r5 = r5.d()
            if (r5 != 0) goto L86
            nk r5 = r4.p
            java.lang.String r5 = r5.a()
            nk r0 = r4.p
            com.careforeyou.library.enums.Protocal_Type r0 = r0.b()
            r4.a(r5, r0, r1)
            goto L86
        L81:
            com.careforeyou.library.utils.CsBtUtil_v11 r5 = r4.k
            r5.b()
        L86:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.careforeyou.library.BIAWorker.a(nr):void");
    }

    private void a(String str, Protocal_Type protocal_Type, boolean z) {
        CsBtUtil_v11 csBtUtil_v11 = this.k;
        if (csBtUtil_v11 == null || !csBtUtil_v11.a() || str == null) {
            return;
        }
        this.w = str;
        this.y = protocal_Type;
        this.v = false;
        this.k.c(str, protocal_Type, z);
    }

    @Override // com.careforeyou.library.intface.OnWeightScalesListener
    public void specialFatScaleInfo(CsFatScale csFatScale) {
        if (csFatScale != null) {
            WeightEntity c = nj.a(this.j).c(csFatScale);
            oa.e("CsBtUtil_v11", "BIAWorker WeightEntity：" + c.toString());
            if (csFatScale.getLockFlag() == 1) {
                oa.e("CsBtUtil_v11", "BIAWorker fatScale.getLockFlag() == 1：");
                if (this.h == 2) {
                    csFatScale.cleanFatInfo();
                    csFatScale.setLockFlag((byte) 0);
                    c(this.u);
                }
                if (this.y == Protocal_Type.OKOKCloud && csFatScale.weighingDate != null && !csFatScale.weighingDate.equals(this.s)) {
                    this.m = true;
                    this.s = csFatScale.weighingDate;
                }
                if (this.m) {
                    oa.e("CsBtUtil_v11", "BIAWorker isFatFirst");
                    this.t = csFatScale.getRoleId();
                    e(true, c);
                } else {
                    oa.e("CsBtUtil_v11", "BIAWorker !isFatFirst");
                    if (this.t != csFatScale.getRoleId()) {
                        oa.e("CsBtUtil_v11", "BIAWorker !isFatFirst mPrevMatchUserId != fatScale.getRoleId()");
                        this.t = csFatScale.getRoleId();
                        e(true, c);
                    }
                }
            } else {
                oa.e("CsBtUtil_v11", "BIAWorker fatScale.getLockFlag() == 0");
                this.m = true;
                e(false, c);
            }
            if (csFatScale.isHistoryData()) {
                return;
            }
            this.r = true;
        }
    }

    private void e(boolean z, WeightEntity weightEntity) {
        this.d = 2;
        BIACallback bIACallback = this.c;
        if (bIACallback != null) {
            bIACallback.onState(15, "计算中");
        }
        nj.a(this.j).e(weightEntity, this.u, this.p);
        JSONObject c = c(weightEntity);
        if (this.c != null) {
            if (weightEntity.getInsRateMeasuringType() == -1 && weightEntity.getHeartRateMeasuringType() == -1 && z) {
                this.c.onState(18, "完成");
            } else if (weightEntity.getHeartRateMeasuringType() == 1 && weightEntity.getInsRateMeasuringType() == 0) {
                this.c.onState(6, "正在测心率");
            } else if (weightEntity.getHeartRateMeasuringType() == 0 && weightEntity.getInsRateMeasuringType() == 0 && z) {
                this.c.onState(18, "完成");
            }
        }
        if (this.c != null) {
            oa.e("CsBtUtil_v11", "BIAWorker onResult");
            this.c.onResult(z ? 1 : 0, c);
        }
    }

    @Override // com.careforeyou.library.intface.OnWeightScalesListener
    public void matchUserMsg(ng ngVar) {
        WeightEntity d = nj.a(this.j).d((float) ngVar.a(), ngVar.e(), 0.0f, new Date(), 0);
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("weight", Float.valueOf(d.getWeight()).toString());
            jSONObject.put("scaleWeight", d.getScaleweight());
            jSONObject.put("weight_time", d.getWeight_time());
        } catch (JSONException e) {
            e.printStackTrace();
        }
        BIACallback bIACallback = this.c;
        if (bIACallback != null) {
            bIACallback.onUserMatchResult(jSONObject, ngVar.d());
        }
    }

    @Override // com.careforeyou.library.intface.OnWeightScalesListener
    public void bluetoothWriteChannelDone(BluetoothGattCharacteristic bluetoothGattCharacteristic) {
        RoleInfo roleInfo;
        CsBtUtil_v11 csBtUtil_v11 = this.k;
        if (csBtUtil_v11 == null) {
            return;
        }
        this.z = bluetoothGattCharacteristic;
        nk nkVar = this.p;
        if (nkVar == null) {
            return;
        }
        this.h = csBtUtil_v11.c(nkVar.a());
        this.ad = this.p.i();
        if (this.y == Protocal_Type.OKOKCloud) {
            this.k.i();
            j();
        }
        if (this.y == Protocal_Type.OKOKCloudV3 || this.y == Protocal_Type.OKOKCloudV4) {
            this.k.f();
            if (this.y != Protocal_Type.OKOKCloudV4 || (roleInfo = this.u) == null) {
                return;
            }
            byte[] a2 = nz.a(roleInfo);
            try {
                Thread.sleep(150L);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            this.k.e(a2);
            return;
        }
        if (this.ad == 17) {
            BIACallback bIACallback = this.c;
            if (bIACallback != null) {
                bIACallback.onSyncAllUserInfo();
                return;
            }
            return;
        }
        c(this.u);
    }

    private void j() {
        BIACallback bIACallback;
        if (this.y == Protocal_Type.OKOKCloud && this.ad == 2 && (bIACallback = this.c) != null) {
            bIACallback.onCanSyncUnit();
        }
    }

    private void c(final RoleInfo roleInfo) {
        if (roleInfo == null) {
            return;
        }
        final byte b2 = 1;
        if (this.h != 2 ? roleInfo.getSex() == 0 : roleInfo.getSex() == 1) {
            b2 = 0;
        }
        final byte height = (byte) (roleInfo.getHeight() & 255);
        ob.e(new Runnable() { // from class: com.careforeyou.library.BIAWorker.2
            @Override // java.lang.Runnable
            public void run() {
                int i = 0;
                while (i < 4) {
                    try {
                        Thread.sleep(150L);
                        if (BIAWorker.this.z != null && BIAWorker.this.k != null) {
                            BIAWorker.this.k.bB_(BIAWorker.this.z, nz.e(roleInfo.getId(), b2, height, roleInfo.getAge()), CsBtUtil_v11.Down_Instruction_Type.Sync_UserInfo);
                        }
                        i++;
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        });
    }

    private void c(final int i) {
        ArrayList<nh> arrayList = this.q;
        if (arrayList == null) {
            return;
        }
        final ArrayList<byte[]> c = nz.c(arrayList);
        ob.e(new Runnable() { // from class: com.careforeyou.library.BIAWorker.1
            @Override // java.lang.Runnable
            public void run() {
                BIAWorker.this.v = true;
                for (int i2 = 0; i2 < i; i2++) {
                    for (int i3 = 0; i3 < c.size(); i3++) {
                        try {
                            Thread.sleep(200L);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        byte[] bArr = (byte[]) c.get(i3);
                        if (BIAWorker.this.z != null && BIAWorker.this.k != null) {
                            BIAWorker.this.k.bB_(BIAWorker.this.z, bArr, CsBtUtil_v11.Down_Instruction_Type.Sync_UserInfo);
                        }
                    }
                }
                BIAWorker.this.v = false;
            }
        });
    }

    private void e(JSONArray jSONArray) {
        this.q = new ArrayList<>();
        for (int i = 0; i < jSONArray.length(); i++) {
            try {
                RoleInfo e = e((JSONObject) jSONArray.get(i));
                nh nhVar = new nh();
                nhVar.b = (byte) (e.getAge() & 255);
                nhVar.d = (byte) (e.getHeight() & 255);
                nhVar.c = e.getId();
                nhVar.e = (byte) e.getSex();
                nhVar.f15282a = (short) (e.getWeight() * 10.0f);
                this.q.add(nhVar);
            } catch (JSONException e2) {
                e2.printStackTrace();
            }
        }
    }
}
