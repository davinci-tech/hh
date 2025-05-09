package defpackage;

import android.os.Bundle;
import com.huawei.health.device.api.IndoorEquipManagerApi;
import com.huawei.health.sport.model.WorkoutRecord;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.operation.ble.BleConstants;
import java.io.Serializable;

/* loaded from: classes4.dex */
public class gxi implements Serializable {
    private static final long serialVersionUID = 8138097492060215279L;

    /* renamed from: a, reason: collision with root package name */
    private String[] f12990a;
    private int aa;
    private float ab;
    private int ac;
    private int ad;
    private float ae;
    private int af;
    private int ag;
    private int ah;
    private int ai;
    private int ak;
    private int al;
    private int am;
    private long an;
    private int b;
    private int c;
    private int d;
    private float e;
    private int f;
    private int g;
    private boolean h;
    private boolean i;
    private boolean j;
    private boolean k;
    private boolean l;
    private boolean m;
    private boolean n;
    private boolean o;
    private boolean p;
    private boolean q;
    private boolean r;
    private boolean s;
    private boolean t;
    private long u;
    private float v;
    private boolean w;
    private boolean x;
    private boolean y;
    private float z;

    public gxi(int i, Bundle bundle) {
        this.ah = i;
        if (bundle == null) {
            LogUtil.h("Track_SportStateVoiceParameter", "playContent is null");
            return;
        }
        String string = bundle.getString("voiceType", "invalid");
        if ("invalid".equals(string)) {
            LogUtil.a("Track_SportStateVoiceParameter", "there is no bundle param");
        } else {
            aVA_(bundle, string);
        }
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    private void aVA_(Bundle bundle, String str) {
        char c;
        str.hashCode();
        switch (str.hashCode()) {
            case -1992012396:
                if (str.equals("duration")) {
                    c = 0;
                    break;
                }
                c = 65535;
                break;
            case 3432979:
                if (str.equals("pace")) {
                    c = 1;
                    break;
                }
                c = 65535;
                break;
            case 109641799:
                if (str.equals("speed")) {
                    c = 2;
                    break;
                }
                c = 65535;
                break;
            case 200416838:
                if (str.equals(IndoorEquipManagerApi.KEY_HEART_RATE)) {
                    c = 3;
                    break;
                }
                c = 65535;
                break;
            case 288459765:
                if (str.equals("distance")) {
                    c = 4;
                    break;
                }
                c = 65535;
                break;
            default:
                c = 65535;
                break;
        }
        if (c == 0) {
            this.ag = bundle.getInt(BleConstants.SPORT_TYPE);
            this.an = bundle.getLong(str);
            if (bundle.getInt(WorkoutRecord.Extend.COURSE_TARGET_TYPE, -1) == 0) {
                aVD_(bundle);
            }
            this.s = true;
            return;
        }
        if (c == 1) {
            this.ag = bundle.getInt(BleConstants.SPORT_TYPE);
            this.u = bundle.getLong(str);
            this.k = bundle.getBoolean("paceTip");
            this.x = true;
            return;
        }
        if (c == 2) {
            this.ag = bundle.getInt(BleConstants.SPORT_TYPE);
            this.ae = bundle.getFloat(str);
            this.k = bundle.getBoolean("paceTip");
            this.y = true;
            return;
        }
        if (c == 3) {
            this.ag = bundle.getInt(BleConstants.SPORT_TYPE);
            this.d = bundle.getInt(str);
            this.r = true;
        } else {
            if (c == 4) {
                this.ag = bundle.getInt(BleConstants.SPORT_TYPE);
                this.e = bundle.getFloat(str);
                if (bundle.getInt(WorkoutRecord.Extend.COURSE_TARGET_TYPE, -1) == 1) {
                    aVD_(bundle);
                }
                this.q = true;
                return;
            }
            aVB_(bundle, str);
        }
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    private void aVB_(Bundle bundle, String str) {
        char c;
        str.hashCode();
        switch (str.hashCode()) {
            case -2139295330:
                if (str.equals("impactTips")) {
                    c = 0;
                    break;
                }
                c = 65535;
                break;
            case -912956487:
                if (str.equals("connectSuccess")) {
                    c = 1;
                    break;
                }
                c = 65535;
                break;
            case -19843715:
                if (str.equals("sportState")) {
                    c = 2;
                    break;
                }
                c = 65535;
                break;
            case 91083161:
                if (str.equals("startRun_others")) {
                    c = 3;
                    break;
                }
                c = 65535;
                break;
            case 465360728:
                if (str.equals("warmUpTips")) {
                    c = 4;
                    break;
                }
                c = 65535;
                break;
            case 1214153508:
                if (str.equals("stepRateTips")) {
                    c = 5;
                    break;
                }
                c = 65535;
                break;
            case 1316784841:
                if (str.equals("startRun")) {
                    c = 6;
                    break;
                }
                c = 65535;
                break;
            case 1590484994:
                if (str.equals("sportStateWithoutCalorie")) {
                    c = 7;
                    break;
                }
                c = 65535;
                break;
            case 1932386131:
                if (str.equals("commonAbnormal")) {
                    c = '\b';
                    break;
                }
                c = 65535;
                break;
            default:
                c = 65535;
                break;
        }
        switch (c) {
            case 0:
                this.j = true;
                this.f = bundle.getInt("treadmillEvent");
                break;
            case 1:
                this.h = true;
                break;
            case 2:
                this.ag = bundle.getInt(BleConstants.SPORT_TYPE);
                aVF_(bundle);
                this.c = bundle.getInt("calorie");
                this.t = true;
                if (hab.g()) {
                    try {
                        this.f12990a = bundle.getStringArray("beforeSportFirstVoicePath");
                        break;
                    } catch (ArrayIndexOutOfBoundsException e) {
                        LogUtil.b("Track_SportStateVoiceParameter", "SportStateVoiceParameter ", e.getMessage());
                        return;
                    }
                }
                break;
            case 3:
                this.l = true;
                break;
            case 4:
                this.w = true;
                this.am = bundle.getInt("treadmillEvent");
                this.al = bundle.getInt("warmUpHeartRateLow");
                this.ak = bundle.getInt("warmUpHeartRateHeight");
                break;
            case 5:
                this.o = true;
                this.ai = bundle.getInt("treadmillEvent");
                break;
            case 6:
                this.m = true;
                break;
            case 7:
                aVF_(bundle);
                break;
            case '\b':
                this.n = true;
                break;
            default:
                aVC_(bundle, str);
                break;
        }
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    private void aVC_(Bundle bundle, String str) {
        char c;
        str.hashCode();
        switch (str.hashCode()) {
            case -2017003803:
                if (str.equals("sportSmartCoach")) {
                    c = 0;
                    break;
                }
                c = 65535;
                break;
            case -100892522:
                if (str.equals("sportStateWithPace")) {
                    c = 1;
                    break;
                }
                c = 65535;
                break;
            case -33024773:
                if (str.equals("intelligentVoice")) {
                    c = 2;
                    break;
                }
                c = 65535;
                break;
            case -29329742:
                if (str.equals("commonNormal")) {
                    c = 3;
                    break;
                }
                c = 65535;
                break;
            case 238173334:
                if (str.equals("marathon")) {
                    c = 4;
                    break;
                }
                c = 65535;
                break;
            case 505979477:
                if (str.equals("skippingNum")) {
                    c = 5;
                    break;
                }
                c = 65535;
                break;
            case 1352226353:
                if (str.equals("countdown")) {
                    c = 6;
                    break;
                }
                c = 65535;
                break;
            default:
                c = 65535;
                break;
        }
        switch (c) {
            case 0:
                this.z = bundle.getFloat("sectionLower");
                this.ab = bundle.getFloat("sectionUpper");
                this.ad = bundle.getInt("sportDuration");
                try {
                    this.f12990a = bundle.getStringArray("beforeSportFirstVoicePath");
                    break;
                } catch (ArrayIndexOutOfBoundsException e) {
                    LogUtil.b("Track_SportStateVoiceParameter", "SportStateVoiceParameter ", e.getMessage());
                }
            case 1:
                aVE_(bundle);
                break;
            case 2:
                this.g = bundle.getInt("intelligentVoice");
                break;
            case 3:
                this.i = true;
                this.b = bundle.getInt("treadmillEvent");
                break;
            case 4:
                this.an = bundle.getLong("duration");
                break;
            case 5:
                this.aa = bundle.getInt("skippingNum");
                break;
            case 6:
                this.v = bundle.getFloat("lastValue");
                this.e = bundle.getFloat("distance");
                this.g = bundle.getInt("intelligentVoice");
                break;
        }
    }

    private void aVF_(Bundle bundle) {
        if (bundle != null) {
            this.e = bundle.getFloat("distance");
            this.an = bundle.getLong("duration");
            this.ae = bundle.getFloat("speed");
            this.d = bundle.getInt(IndoorEquipManagerApi.KEY_HEART_RATE);
            this.q = true;
            this.s = true;
            this.y = true;
            this.r = true;
        }
    }

    public int ac() {
        return this.ah;
    }

    public int ah() {
        return this.al;
    }

    public int aa() {
        return this.ak;
    }

    public int u() {
        return this.f;
    }

    public int y() {
        return this.b;
    }

    public int ad() {
        return this.ai;
    }

    public int ab() {
        return this.am;
    }

    public boolean af() {
        return this.h;
    }

    public boolean aj() {
        return this.m;
    }

    public boolean am() {
        return this.l;
    }

    public boolean ai() {
        return this.n;
    }

    public boolean ae() {
        return this.i;
    }

    public boolean ag() {
        return this.j;
    }

    public boolean al() {
        return this.o;
    }

    public boolean ak() {
        return this.w;
    }

    public boolean p() {
        return this.q;
    }

    public boolean s() {
        return this.s;
    }

    public boolean v() {
        return this.x;
    }

    public boolean q() {
        return this.r;
    }

    public boolean w() {
        return this.y;
    }

    public int n() {
        return this.ag;
    }

    public float a() {
        return this.e;
    }

    public long r() {
        return this.an;
    }

    public int c() {
        return this.d;
    }

    public long g() {
        return this.u;
    }

    public float e() {
        return this.ae;
    }

    public boolean h() {
        return this.k;
    }

    public int x() {
        return this.c;
    }

    public int f() {
        return this.aa;
    }

    public boolean t() {
        return this.t;
    }

    public float j() {
        return this.v;
    }

    public boolean o() {
        return this.p;
    }

    public int m() {
        return this.af;
    }

    public int b() {
        return this.g;
    }

    public String[] d() {
        String[] strArr = this.f12990a;
        if (strArr != null) {
            return (String[]) strArr.clone();
        }
        return null;
    }

    public float i() {
        return this.z;
    }

    public float l() {
        return this.ab;
    }

    public int k() {
        return this.ad;
    }

    public int z() {
        return this.ac;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder(16);
        sb.append("SportStateVoiceParameter [mSportType=");
        sb.append(this.ag);
        sb.append(", mCurrentDistance=");
        sb.append(this.e);
        sb.append(", mTotalDuration=");
        sb.append(this.an);
        sb.append(", mLastPace=");
        sb.append(this.u);
        sb.append(", mSpeed=");
        sb.append(this.ae);
        sb.append(", mIsPaceTip=");
        sb.append(this.k);
        sb.append(", mIsVoiceDistance=");
        sb.append(this.q);
        sb.append(", mIsVoiceDuration=");
        sb.append(this.s);
        sb.append(", mIsVoicePace=");
        sb.append(this.x);
        sb.append(", mVoiceHR=");
        sb.append(this.r);
        sb.append(", mIsVoiceSpeed=");
        sb.append(this.y);
        sb.append("]");
        return sb.toString();
    }

    private void aVD_(Bundle bundle) {
        if (bundle != null) {
            this.v = bundle.getFloat("lastValue");
            this.p = bundle.getBoolean("targetTip");
            this.af = bundle.getInt("targetProgress");
        }
    }

    private void aVE_(Bundle bundle) {
        if (bundle != null) {
            this.ag = bundle.getInt(BleConstants.SPORT_TYPE, 0);
            this.e = bundle.getFloat("distance", 0.0f);
            this.an = bundle.getLong("duration", 0L);
            this.d = bundle.getInt(IndoorEquipManagerApi.KEY_HEART_RATE, -1);
            this.c = bundle.getInt("calorie", 0);
            this.u = bundle.getLong("pace", 0L);
            this.k = bundle.getBoolean("paceTip", false);
            this.q = bundle.getBoolean("voice_distance", true);
            this.s = bundle.getBoolean("voice_speed_time", true);
            this.x = bundle.getBoolean("voice_pace", true);
            this.r = bundle.getBoolean("voice_heart_rate", true);
            this.ac = bundle.getInt("voice_play_count");
        }
    }
}
