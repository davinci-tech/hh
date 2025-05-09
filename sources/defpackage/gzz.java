package defpackage;

import android.util.SparseArray;
import com.huawei.health.userprofilemgr.api.UserProfileMgrApi;
import com.huawei.health.userprofilemgr.model.BaseResponseCallback;
import com.huawei.hihealth.HiDataReadOption;
import com.huawei.hihealth.HiHealthData;
import com.huawei.hihealth.api.HiHealthManager;
import com.huawei.hihealth.data.listener.HiDataReadResultListener;
import com.huawei.hwbasemgr.IBaseResponseCallback;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.hwcommonmodel.utils.DateFormatUtil;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.up.model.UserInfomation;
import health.compact.a.Services;
import java.util.List;

/* loaded from: classes4.dex */
public class gzz {

    /* renamed from: a, reason: collision with root package name */
    private int f13033a;
    private char b;
    private int c;
    private int d;
    private char e;
    private int f;
    private boolean g;
    private int h;
    private int i = -1;
    private boolean j;
    private int k;
    private float l;
    private char m;
    private int n;
    private char o;
    private UserInfomation p;

    public gzz(final boolean z) {
        ((UserProfileMgrApi) Services.c("HWUserProfileMgr", UserProfileMgrApi.class)).getUserInfo(new BaseResponseCallback() { // from class: gzx
            @Override // com.huawei.health.userprofilemgr.model.BaseResponseCallback
            public final void onResponse(int i, Object obj) {
                gzz.this.e(z, i, (UserInfomation) obj);
            }
        });
    }

    /* synthetic */ void e(boolean z, int i, UserInfomation userInfomation) {
        this.p = userInfomation;
        e(z);
    }

    private void e(boolean z) {
        this.f13033a = o();
        UserInfomation userInfomation = this.p;
        if (userInfomation != null) {
            this.f = userInfomation.getGenderOrDefaultValue();
            this.c = this.p.getAgeOrDefaultValue();
        }
        this.j = false;
        if (z) {
            this.d = 0;
            this.n = 0;
            this.h = 0;
            this.g = false;
            this.b = (char) 0;
            this.k = 0;
        } else {
            this.o = (char) 0;
            this.m = (char) 0;
        }
        this.e = 'A';
    }

    public int b(int i) {
        if (i == 0) {
            return ((Integer) jdy.d(Integer.valueOf(this.h))).intValue();
        }
        if (i == 5) {
            return ((Integer) jdy.d(Integer.valueOf(this.n))).intValue();
        }
        LogUtil.h("Track_SmartCoachBeforeSportConfig", "getLast5DaysAverageRestHeartRate no match dayCount.");
        return this.i;
    }

    public void c(int i, int i2) {
        if (i == 0) {
            this.h = ((Integer) jdy.d(Integer.valueOf(i2))).intValue();
        } else if (i == 5) {
            this.n = ((Integer) jdy.d(Integer.valueOf(i2))).intValue();
        } else {
            LogUtil.h("Track_SmartCoachBeforeSportConfig", "setLast5DaysAverageRestHeartRate no match dayCount.");
        }
    }

    public boolean c(int i) {
        if (i == 7) {
            return ((Boolean) jdy.d(Boolean.valueOf(this.j))).booleanValue();
        }
        return false;
    }

    public void d(int i, boolean z) {
        if (i == 7) {
            this.j = ((Boolean) jdy.d(Boolean.valueOf(z))).booleanValue();
        }
    }

    public char e() {
        return ((Character) jdy.d(Character.valueOf(this.b))).charValue();
    }

    public void c(char c) {
        this.b = ((Character) jdy.d(Character.valueOf(c))).charValue();
    }

    public char d() {
        return ((Character) jdy.d(Character.valueOf(this.e))).charValue();
    }

    public void e(char c) {
        this.e = ((Character) jdy.d(Character.valueOf(c))).charValue();
    }

    public char h() {
        return ((Character) jdy.d(Character.valueOf(this.m))).charValue();
    }

    public void b(char c) {
        this.m = ((Character) jdy.d(Character.valueOf(c))).charValue();
    }

    public char j() {
        return ((Character) jdy.d(Character.valueOf(this.o))).charValue();
    }

    public void a(char c) {
        this.o = ((Character) jdy.d(Character.valueOf(c))).charValue();
    }

    public int b() {
        return ((Integer) jdy.d(Integer.valueOf(this.f13033a))).intValue();
    }

    public String m() {
        return this.f == 0 ? "male" : "female";
    }

    public int c() {
        return ((Integer) jdy.d(Integer.valueOf(this.f))).intValue();
    }

    public int f() {
        return ((Integer) jdy.d(Integer.valueOf(this.c))).intValue();
    }

    public boolean l() {
        return ((Boolean) jdy.d(Boolean.valueOf(this.g))).booleanValue();
    }

    public void c(boolean z) {
        this.g = ((Boolean) jdy.d(Boolean.valueOf(z))).booleanValue();
    }

    public int i() {
        return ((Integer) jdy.d(Integer.valueOf(this.k))).intValue();
    }

    public void d(int i) {
        this.k = ((Integer) jdy.d(Integer.valueOf(i))).intValue();
    }

    public void a(int i) {
        this.d = ((Integer) jdy.d(Integer.valueOf(i))).intValue();
    }

    public float g() {
        return ((Float) jdy.d(Float.valueOf(this.l))).floatValue();
    }

    public void e(float f) {
        this.l = ((Float) jdy.d(Float.valueOf(f))).floatValue();
    }

    private int o() {
        UserInfomation userInfomation = this.p;
        if (userInfomation != null) {
            int height = userInfomation.getHeight();
            double weight = this.p.getWeight();
            if (height != 0) {
                return (int) ((weight * 10000.0d) / (height * height));
            }
        } else {
            LogUtil.h("Track_SmartCoachBeforeSportConfig", "getUserBmiValue userInfo is null.");
        }
        return 0;
    }

    public void c(int i, int i2, IBaseResponseCallback iBaseResponseCallback) {
        long currentTimeMillis = System.currentTimeMillis();
        b(jdl.d(currentTimeMillis, 1 - i), currentTimeMillis, i2, iBaseResponseCallback);
    }

    public static void b(long j, long j2, int i, final IBaseResponseCallback iBaseResponseCallback) {
        int i2;
        HiDataReadOption hiDataReadOption = new HiDataReadOption();
        int b = DateFormatUtil.b(j);
        int b2 = DateFormatUtil.b(j2);
        hiDataReadOption.setTimeInterval(String.valueOf(b), String.valueOf(b2), HiDataReadOption.TimeFormatType.DATE_FORMAT_YYYY_MM_DD);
        if (i != 262) {
            if (i != 264) {
                switch (i) {
                    case 257:
                        i2 = 30005;
                        break;
                    case 258:
                        break;
                    case 259:
                        i2 = 30007;
                        break;
                    default:
                        i2 = 30002;
                        break;
                }
            }
            i2 = 30006;
        } else {
            i2 = 30021;
        }
        final int[] iArr = {i2};
        hiDataReadOption.setType(iArr);
        hiDataReadOption.setSortOrder(1);
        HiHealthManager.d(BaseApplication.getContext()).readHiHealthData(hiDataReadOption, new HiDataReadResultListener() { // from class: gzz.2
            @Override // com.huawei.hihealth.data.listener.HiDataReadResultListener
            public void onResultIntent(int i3, Object obj, int i4, int i5) {
            }

            @Override // com.huawei.hihealth.data.listener.HiDataReadResultListener
            public void onResult(Object obj, int i3, int i4) {
                if (obj == null) {
                    IBaseResponseCallback.this.d(i3, null);
                    LogUtil.h("Track_SmartCoachBeforeSportConfig", "requestTrackSimplifyData The return run data is null");
                    return;
                }
                SparseArray sparseArray = (SparseArray) obj;
                if (sparseArray.size() > 0) {
                    gzz.c(sparseArray.get(iArr[0]), i3, IBaseResponseCallback.this);
                } else {
                    IBaseResponseCallback.this.d(i3, null);
                    LogUtil.h("Track_SmartCoachBeforeSportConfig", "requestTrackSimplifyData The return run data is empty");
                }
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static void c(Object obj, int i, IBaseResponseCallback iBaseResponseCallback) {
        List list = koq.e(obj, HiHealthData.class) ? (List) obj : null;
        if (koq.b(list)) {
            LogUtil.h("Track_SmartCoachBeforeSportConfig", "requestTrackSimplifyData list is null or empty");
            iBaseResponseCallback.d(i, null);
        } else {
            iBaseResponseCallback.d(i, list);
            LogUtil.h("Track_SmartCoachBeforeSportConfig", "requestTrackSimplifyData list.size = ", Integer.valueOf(list.size()));
        }
    }

    public char d(int i, long j, int i2) {
        int i3 = i / 60;
        if (i3 <= 0) {
            LogUtil.h("Track_SmartCoachBeforeSportConfig", "getRecoverGearByTime recoverTime <= 0");
            return (char) 0;
        }
        int currentTimeMillis = i3 - ((int) ((System.currentTimeMillis() - j) / 3600000));
        if (i2 == 1) {
            if (currentTimeMillis <= 13) {
                return 'A';
            }
            if (currentTimeMillis <= 30) {
                return 'B';
            }
            if (currentTimeMillis <= 54) {
                return 'C';
            }
            if (currentTimeMillis <= 96) {
                return 'D';
            }
            LogUtil.h("Track_SmartCoachBeforeSportConfig", "getRecoverGearByTime no match recoverTime");
            return (char) 0;
        }
        if (currentTimeMillis <= 18) {
            return 'A';
        }
        if (currentTimeMillis <= 35) {
            return 'B';
        }
        if (currentTimeMillis <= 53) {
            return 'C';
        }
        if (currentTimeMillis <= 96) {
            return 'D';
        }
        LogUtil.h("Track_SmartCoachBeforeSportConfig", "getRecoverGearByTime no match recoverTime");
        return (char) 0;
    }

    public static boolean a() {
        double currentTimeMillis = System.currentTimeMillis() - nom.b(System.currentTimeMillis());
        return currentTimeMillis >= 1.8E7d && currentTimeMillis <= 3.06E7d;
    }
}
