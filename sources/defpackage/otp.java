package defpackage;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Looper;
import android.os.Message;
import android.os.SystemClock;
import android.text.TextUtils;
import android.util.ArrayMap;
import android.view.View;
import android.widget.ImageView;
import androidx.recyclerview.widget.RecyclerView;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;
import com.huawei.basefitnessadvice.callback.OnFitnessStatusChangeCallback;
import com.huawei.haf.handler.BaseHandler;
import com.huawei.haf.handler.HandlerExecutor;
import com.huawei.haf.threadpool.ThreadPoolManager;
import com.huawei.health.R;
import com.huawei.health.courseplanservice.api.RecordApi;
import com.huawei.health.health.utils.functionsetcard.FunctionSetBean;
import com.huawei.health.health.utils.functionsetcard.FunctionSetType;
import com.huawei.health.health.utils.functionsetcard.HandleCacheDataRunnable;
import com.huawei.health.health.utils.functionsetcard.manager.model.CardConfig;
import com.huawei.health.health.utils.functionsetcard.reader.FunctionSetBeanReader;
import com.huawei.health.health.utils.functionsetcard.reader.FunctionSetSubCardData;
import com.huawei.health.sport.model.WorkoutRecord;
import com.huawei.healthcloud.plugintrack.ui.sporttypeconfig.bean.HwSportTypeInfo;
import com.huawei.hihealth.HiAggregateOption;
import com.huawei.hihealth.HiHealthData;
import com.huawei.hihealth.api.HiHealthManager;
import com.huawei.hihealth.api.HiHealthNativeApi;
import com.huawei.hihealth.data.listener.HiAggregateListener;
import com.huawei.hihealth.data.model.RelativeSportData;
import com.huawei.hms.support.api.entity.common.CommonConstant;
import com.huawei.hwbasemgr.IBaseResponseCallback;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.hwcommonmodel.constants.AnalyticsValue;
import com.huawei.hwfoundationmodel.trackmodel.MotionPathSimplify;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.login.ui.login.LoginInit;
import com.huawei.operation.ble.BleConstants;
import com.huawei.ui.commonui.imageview.HealthImageView;
import com.huawei.ui.commonui.utils.ResInfo;
import com.huawei.ui.homehealth.sportsrecordingcard.SportsRecordingBean;
import com.huawei.ui.main.stories.history.SportHistoryActivity;
import defpackage.kwy;
import health.compact.a.CommonUtil;
import health.compact.a.CommonUtils;
import health.compact.a.DeviceUtil;
import health.compact.a.LanguageUtil;
import health.compact.a.LogAnonymous;
import health.compact.a.Services;
import health.compact.a.SharedPreferenceManager;
import health.compact.a.StorageParams;
import health.compact.a.UnitUtil;
import health.compact.a.hwlogsmodel.ReleaseLogUtil;
import health.compact.a.utils.StringUtils;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

/* loaded from: classes9.dex */
public class otp extends FunctionSetBeanReader {

    /* renamed from: a, reason: collision with root package name */
    private int f15947a;
    private int aa;
    private List<Integer> ab;
    private List<Integer> ac;
    private String ad;
    private ArrayMap<String, Integer> ae;
    private long ag;
    private CountDownLatch b;
    private CountDownLatch c;
    private String d;
    private Bitmap e;
    private List<String> f;
    private long g;
    private final b h;
    private j i;
    private String j;
    private boolean k;
    private WeakReference<ImageView> l;
    private boolean m;
    private volatile boolean n;
    private e o;
    private boolean p;
    private boolean q;
    private a r;
    private long s;
    private boolean t;
    private WorkoutRecord u;
    private f v;
    private int w;
    private boolean x;
    private OnFitnessStatusChangeCallback y;
    private SportsRecordingBean z;

    static /* synthetic */ void c(int i2, Object obj) {
    }

    @Override // com.huawei.health.health.utils.functionsetcard.reader.FunctionSetBeanReader
    public int getButtonTextId() {
        return R.string._2130839598_res_0x7f02082e;
    }

    @Override // com.huawei.health.health.utils.functionsetcard.reader.FunctionSetBeanReader
    public int getDefaultImage(boolean z) {
        return z ? R.drawable._2131431550_res_0x7f0b107e : R.drawable.sports_record_small_img_default;
    }

    @Override // com.huawei.health.health.utils.functionsetcard.reader.FunctionSetBeanReader
    public String getDefaultLargeCardImageRes() {
        return null;
    }

    @Override // com.huawei.health.health.utils.functionsetcard.reader.FunctionSetBeanReader
    public boolean isMessageDefaultLargeCard() {
        return true;
    }

    @Override // com.huawei.health.health.utils.functionsetcard.reader.FunctionSetBeanReader
    public boolean isNeedRefreshOnResume() {
        return false;
    }

    @Override // com.huawei.health.health.utils.functionsetcard.reader.FunctionSetBeanReader
    public boolean isOverseaDefaultLargeCard() {
        return true;
    }

    @Override // com.huawei.health.health.utils.functionsetcard.reader.FunctionSetSubCardData
    public boolean isSubscribeType(int i2) {
        return i2 == 4;
    }

    static class b extends HandleCacheDataRunnable {

        /* renamed from: a, reason: collision with root package name */
        private boolean f15950a;
        private final WeakReference<otp> b;
        private boolean c;
        private boolean d;
        private Bitmap e;
        private int h;

        b(otp otpVar) {
            super("SportsRecordingCardReader", null);
            this.b = new WeakReference<>(otpVar);
        }

        void dhs_(SportsRecordingBean sportsRecordingBean, boolean z, int i, Bitmap bitmap) {
            HiHealthData hiHealthData;
            this.d = true;
            if (sportsRecordingBean != null) {
                hiHealthData = new HiHealthData();
                hiHealthData.putString("_t", sportsRecordingBean.getSportTime());
                hiHealthData.putString("_d", sportsRecordingBean.getSportData());
                hiHealthData.putString("_u", sportsRecordingBean.getSportUnit());
                hiHealthData.putInt("_st", sportsRecordingBean.getShowType());
                hiHealthData.putBoolean("_", z);
                hiHealthData.putLong("_tl", sportsRecordingBean.getSportStartTime());
                if (z) {
                    hiHealthData.putString("_n", sportsRecordingBean.getSportTypeName());
                } else {
                    hiHealthData.putInt("_s", i);
                }
                if (i == 222) {
                    hiHealthData.putString("_n", sportsRecordingBean.getSportTypeName());
                    hiHealthData.putString("_k", sportsRecordingBean.getSportKeepTime());
                }
                dhq_(hiHealthData, bitmap);
            } else {
                hiHealthData = null;
            }
            otp.saveDataFromHealthApi("SportsRecordingCardReader", this.b, hiHealthData);
        }

        boolean dhr_(ImageView imageView) {
            Bitmap bitmap = this.e;
            if (!this.d) {
                otp otpVar = this.b.get();
                if (otpVar == null) {
                    LogUtil.b("SportsRecordingCardReader", "initCardView reader is null!");
                    return false;
                }
                if (bitmap == null) {
                    otpVar.dhm_(imageView, this.f15950a, this.h);
                    LogUtil.a("SportsRecordingCardReader", "initCardView bitmap is null, init with default pic");
                    return true;
                }
                imageView.setImageBitmap(bitmap);
                return true;
            }
            if (bitmap == null) {
                LogUtil.b("SportsRecordingCardReader", "initCardView bitmap is null!");
                return false;
            }
            Drawable drawable = imageView.getDrawable();
            if ((drawable instanceof BitmapDrawable) && bitmap == ((BitmapDrawable) drawable).getBitmap()) {
                imageView.setImageBitmap(null);
            }
            bitmap.recycle();
            this.e = null;
            LogUtil.a("SportsRecordingCardReader", "initCardView to restore bitmap is invalid!");
            return false;
        }

        @Override // com.huawei.health.health.utils.functionsetcard.HandleCacheDataRunnable
        public void handleCacheData(HiHealthData hiHealthData, boolean z) {
            String str;
            CharSequence c;
            otp otpVar = this.b.get();
            if (otpVar == null || otpVar.i == null) {
                return;
            }
            if (hiHealthData == null) {
                otp.setHasCardData(this.b, false);
                if (this.c && z) {
                    otpVar.o();
                    return;
                }
                return;
            }
            long j = hiHealthData.getLong("_tl");
            if (j > 0 && enk.c() > j) {
                ReleaseLogUtil.e("TimeEat_SportsRecordingCardReader", "refreshFromSpCache");
                MotionPathSimplify e = enk.e();
                if (e != null) {
                    d(otpVar, e);
                    enk.d();
                    return;
                }
            }
            otp.setHasCardData(this.b, true);
            this.c = true;
            boolean z2 = hiHealthData.getBoolean("_");
            int i = z2 ? 10001 : hiHealthData.getInt("_s");
            if (!z && !this.d) {
                this.f15950a = z2;
                this.h = i;
                this.e = dhp_(hiHealthData);
            }
            String string = (z2 || i == 222) ? hiHealthData.getString("_n") : otpVar.e(i);
            String string2 = hiHealthData.getString("_u");
            if (hiHealthData.getInt("_st") == 6) {
                c = a(hiHealthData.getString("_d"));
                str = "";
            } else {
                str = string2;
                c = c(i, hiHealthData.getString("_d"), hiHealthData.getString("_k"));
            }
            otpVar.a(d(i, hiHealthData.getString("_t")), c, str, d(i, string), z);
        }

        private void d(otp otpVar, MotionPathSimplify motionPathSimplify) {
            CharSequence c;
            int requestSportType = motionPathSimplify.requestSportType();
            if (motionPathSimplify.hasTrackPoint()) {
                this.e = enk.arr_();
            } else {
                this.e = null;
            }
            SportsRecordingBean b = new otw().b(motionPathSimplify.requestChiefSportDataType(), requestSportType).b(motionPathSimplify);
            otp.setHasCardData(this.b, true);
            boolean z = enk.a() > cat.d();
            LogUtil.a("SportsRecordingCardReader", "track cache is read: ", Boolean.valueOf(z));
            otpVar.q = z;
            this.c = true;
            this.f15950a = false;
            this.h = requestSportType;
            int showType = b.getShowType();
            String sportTypeName = b.getSportTypeName();
            String sportUnit = b.getSportUnit();
            if (showType == 6) {
                c = a(b.getSportData());
                sportUnit = "";
            } else {
                c = c(requestSportType, b.getSportData(), b.getSportKeepTime());
            }
            otpVar.a(d(requestSportType, b.getSportTime()), c, sportUnit, d(requestSportType, sportTypeName), true);
        }

        private CharSequence d(int i, String str) {
            return i != 222 ? str : mlh.e(str, str, BaseApplication.getContext().getResources().getDimensionPixelSize(R.dimen._2131362711_res_0x7f0a0397));
        }

        private CharSequence c(int i, String str, String str2) {
            return i != 222 ? str : gvv.c(CommonUtils.g(str2), R.style.sport_day_hour_min_num, R.style.sport_day_hour_min_unit);
        }

        private CharSequence a(String str) {
            Context context = BaseApplication.getContext();
            int e = nsn.e(str);
            String e2 = UnitUtil.e(e, 1, 0);
            return owm.b(context.getResources().getQuantityString(R.plurals._2130903269_res_0x7f0300e5, e, e2), e2);
        }

        private Bitmap dhp_(HiHealthData hiHealthData) {
            Bitmap cJp_ = hiHealthData.getBoolean("_b") ? nrf.cJp_("SportsRecordingCardReader", true) : null;
            Object[] objArr = new Object[2];
            objArr[0] = "restoreBitmap, read ret=";
            objArr[1] = Boolean.valueOf(cJp_ != null);
            LogUtil.h("SportsRecordingCardReader", objArr);
            return cJp_;
        }

        private void dhq_(HiHealthData hiHealthData, Bitmap bitmap) {
            String str;
            if (bitmap != null) {
                str = nrf.cJr_("SportsRecordingCardReader", bitmap, Bitmap.CompressFormat.PNG, true);
                if (str != null) {
                    hiHealthData.putBoolean("_b", true);
                }
            } else {
                str = null;
            }
            Object[] objArr = new Object[2];
            objArr[0] = "saveBitmap, save ret=";
            objArr[1] = Boolean.valueOf(str != null);
            LogUtil.a("SportsRecordingCardReader", objArr);
        }
    }

    public otp(Context context, CardConfig cardConfig) {
        super(context, "SportsRecordingCardReader", cardConfig);
        this.t = false;
        this.i = null;
        this.x = false;
        this.s = 0L;
        this.k = false;
        this.o = new e(this);
        this.ag = 0L;
        this.g = 0L;
        this.aa = 0;
        this.ad = "";
        this.w = 0;
        this.u = null;
        this.v = new f(this);
        this.r = new a();
        this.ac = new ArrayList(20);
        this.ab = null;
        this.p = false;
        this.q = false;
        this.c = null;
        this.b = null;
        this.n = true;
        this.m = true;
        this.h = new b(this);
        this.ae = new ArrayMap<>();
        this.l = new WeakReference<>(null);
        this.i = new j(this);
        p();
    }

    public class j extends BaseHandler<otp> {
        private j(otp otpVar) {
            super(Looper.getMainLooper(), otpVar);
        }

        @Override // com.huawei.haf.handler.BaseHandler
        /* renamed from: dht_, reason: merged with bridge method [inline-methods] */
        public void handleMessageWhenReferenceNotNull(otp otpVar, Message message) {
            if (otpVar == null || message == null) {
                LogUtil.b("SportsRecordingCardReader", "recordingCardReader or msg is null !");
                return;
            }
            LogUtil.a("SportsRecordingCardReader", "handleMessageWhenReferenceNotNull()", Integer.valueOf(message.what));
            int i = message.what;
            if (i != 1) {
                if (i == 2) {
                    otp otpVar2 = otp.this;
                    otpVar2.notifyItemChanged(otpVar2.buildEmptyCardBean());
                    return;
                } else if (i == 3) {
                    otp.this.n = true;
                    return;
                } else {
                    if (i == 6) {
                        if (message.obj instanceof FunctionSetBean) {
                            otp.this.c(otpVar, (FunctionSetBean) message.obj);
                            return;
                        }
                        return;
                    }
                    LogUtil.h("SportsRecordingCardReader", "handleMessageWhenReferenceNotNull switch err");
                    return;
                }
            }
            if (!(message.obj instanceof SportsRecordingBean)) {
                LogUtil.b("SportsRecordingCardReader", "data is null");
                return;
            }
            SportsRecordingBean sportsRecordingBean = (SportsRecordingBean) message.obj;
            otpVar.i = otp.this.i;
            LogUtil.a("SportsRecordingCardReader", "data.getSportType()", Integer.valueOf(sportsRecordingBean.getShowType()));
            int showType = sportsRecordingBean.getShowType();
            if (showType != 0) {
                if (showType != 2 && showType != 3) {
                    if (showType != 4) {
                        if (showType != 5) {
                            otp.this.b(sportsRecordingBean);
                            return;
                        }
                    }
                }
                otp.this.e = null;
                otp.this.s = sportsRecordingBean.getSportStartTime();
                otp.this.b(sportsRecordingBean);
                return;
            }
            otp.this.c(sportsRecordingBean);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void e(Object obj) {
        MotionPathSimplify motionPathSimplify = obj instanceof MotionPathSimplify ? (MotionPathSimplify) obj : null;
        if (motionPathSimplify == null) {
            LogUtil.b("SportsRecordingCardReader", "LastTrackCallBack trackData is null");
            b();
            return;
        }
        RelativeSportData requestFatherSportItem = motionPathSimplify.requestFatherSportItem();
        if (requestFatherSportItem != null) {
            this.k = motionPathSimplify.hasTrackPoint();
            i iVar = new i(this);
            LogUtil.a("SportsRecordingCardReader", "StartTime = ", Long.valueOf(requestFatherSportItem.getStartTime()), " EndTime = ", Long.valueOf(requestFatherSportItem.getEndTime()));
            kpm.c().d(requestFatherSportItem.getStartTime(), requestFatherSportItem.getEndTime(), iVar);
            return;
        }
        LogUtil.b("SportsRecordingCardReader", "Father data is null ");
        a(motionPathSimplify);
        this.p = true;
        b();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void d(Object obj) {
        MotionPathSimplify motionPathSimplify = obj instanceof MotionPathSimplify ? (MotionPathSimplify) obj : null;
        if (motionPathSimplify == null) {
            LogUtil.b("SportsRecordingCardReader", "TrackForTriathlon trackData is null");
            b();
        } else {
            a(motionPathSimplify);
            this.p = true;
            b();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void b(Object obj) {
        List list = koq.e(obj, HiHealthData.class) ? (List) obj : null;
        if (k()) {
            CountDownLatch countDownLatch = this.b;
            if (countDownLatch != null) {
                countDownLatch.countDown();
                return;
            }
            return;
        }
        if (n()) {
            CountDownLatch countDownLatch2 = this.b;
            if (countDownLatch2 != null) {
                countDownLatch2.countDown();
                return;
            }
            return;
        }
        if (koq.b(list)) {
            LogUtil.b("SportsRecordingCardReader", "all run data null or empty");
            this.q = false;
            CountDownLatch countDownLatch3 = this.b;
            if (countDownLatch3 != null) {
                countDownLatch3.countDown();
                return;
            }
            return;
        }
        c((HiHealthData) list.get(0));
    }

    private boolean k() {
        String b2 = SharedPreferenceManager.b(BaseApplication.getContext(), String.valueOf(10000), "SPORT_CARD_RED_DOT_FOR_AUTO_TRACK");
        LogUtil.a("SportsRecordingCardReader", "isAutoTrackRedDot ", b2);
        if (!"true".equals(b2)) {
            return false;
        }
        this.q = true;
        return true;
    }

    private boolean n() {
        String b2 = SharedPreferenceManager.b(BaseApplication.getContext(), String.valueOf(10000), "HOME_HEALTH_SHOW_FITNESS_DOT");
        LogUtil.a("SportsRecordingCardReader", "isShowFitnessRedDot ", b2);
        if (!"true".equals(b2)) {
            return false;
        }
        this.q = true;
        return true;
    }

    private void c(HiHealthData hiHealthData) {
        long j2;
        if (hiHealthData == null) {
            LogUtil.b("SportsRecordingCardReader", "data is null");
            return;
        }
        int i2 = hiHealthData.getInt("wear_device_type_datas");
        String b2 = SharedPreferenceManager.b(BaseApplication.getContext(), String.valueOf(10000), "HOME_HEALTH_SHOW_DOT");
        LogUtil.a("SportsRecordingCardReader", "showRedDotStr is ", b2);
        long j3 = i2;
        String b3 = SharedPreferenceManager.b(BaseApplication.getContext(), String.valueOf(10000), "HOME_HEALTH_TRACK_NUM");
        LogUtil.a("SportsRecordingCardReader", "trackNumString is ", b3);
        if (b3 == null || "".equals(b3) || !cat.c(b3)) {
            j2 = 0;
        } else {
            j2 = CommonUtil.g(b3);
            LogUtil.a("SportsRecordingCardReader", "trackNumLocal is ", Long.valueOf(j2));
        }
        LogUtil.a("SportsRecordingCardReader", "trackNumLocal is ", Long.valueOf(j2), "   trackNumData is ", Long.valueOf(j3));
        if (j2 != j3) {
            b2 = a(BaseApplication.getContext(), b2, j2, j3);
        }
        String str = b2;
        long f2 = f();
        long d2 = cat.d();
        if (d2 > 0 && f2 > 0 && d(f2, str, d2)) {
            CountDownLatch countDownLatch = this.b;
            if (countDownLatch != null) {
                countDownLatch.countDown();
                return;
            }
            return;
        }
        if (a(f2, str)) {
            CountDownLatch countDownLatch2 = this.b;
            if (countDownLatch2 != null) {
                countDownLatch2.countDown();
                return;
            }
            return;
        }
        this.q = false;
        CountDownLatch countDownLatch3 = this.b;
        if (countDownLatch3 != null) {
            countDownLatch3.countDown();
        }
        SharedPreferenceManager.e(BaseApplication.getContext(), String.valueOf(10000), "HOME_HEALTH_SHOW_DOT", String.valueOf(false), new StorageParams());
        LogUtil.a("SportsRecordingCardReader", "getSportsCardRedDot: not show red Dot fourth");
    }

    private long f() {
        String c2 = DeviceUtil.c();
        if (StringUtils.i(c2) && cat.c(c2)) {
            return CommonUtil.g(c2);
        }
        return 0L;
    }

    private boolean a(long j2, String str) {
        if (j2 == 0) {
            this.q = false;
            SharedPreferenceManager.e(BaseApplication.getContext(), String.valueOf(10000), "HOME_HEALTH_SHOW_DOT", "false", new StorageParams());
            LogUtil.a("SportsRecordingCardReader", "getSportsCardRedDot: not showRedDot third");
            return true;
        }
        if (!"true".equals(str)) {
            return false;
        }
        this.q = true;
        SharedPreferenceManager.e(BaseApplication.getContext(), String.valueOf(10000), "HOME_HEALTH_SHOW_DOT", "true", new StorageParams());
        return true;
    }

    private boolean d(long j2, String str, long j3) {
        LogUtil.a("SportsRecordingCardReader", "getSportsCardRedDot: intoActivityTime = ", Long.valueOf(j3));
        if (j2 > j3 && str != null && "true".equals(str)) {
            this.q = true;
            LogUtil.a("SportsRecordingCardReader", "getSportsCardRedDot: showRedDot first");
            return true;
        }
        if (j2 > j3 || str == null) {
            return false;
        }
        this.q = false;
        SharedPreferenceManager.e(BaseApplication.getContext(), String.valueOf(10000), "HOME_HEALTH_SHOW_DOT", "false", new StorageParams());
        LogUtil.a("SportsRecordingCardReader", "getSportsCardRedDot: not showRedDot second");
        return true;
    }

    private String a(Context context, String str, long j2, long j3) {
        SharedPreferenceManager.e(context, String.valueOf(10000), "HOME_HEALTH_TRACK_NUM", String.valueOf(j3), new StorageParams());
        if (j3 <= j2) {
            return str;
        }
        SharedPreferenceManager.e(context, String.valueOf(10000), "HOME_HEALTH_SHOW_DOT", "true", new StorageParams());
        String b2 = SharedPreferenceManager.b(context, String.valueOf(10000), "HOME_HEALTH_SHOW_DOT");
        LogUtil.a("SportsRecordingCardReader", "showRedDot is true");
        return b2;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void c(SportsRecordingBean sportsRecordingBean) {
        if (sportsRecordingBean.getSportStartTime() == 0 || sportsRecordingBean.getSportEndTime() == 0) {
            return;
        }
        if (this.w == 4 || this.x) {
            LogUtil.a("SportsRecordingCardReader", "mNeedTryAgain ", Boolean.valueOf(this.x));
            d(sportsRecordingBean);
        } else if ((this.s != sportsRecordingBean.getSportStartTime() && this.k) || this.x) {
            this.s = sportsRecordingBean.getSportStartTime();
            LogUtil.a("SportsRecordingCardReader", "mNeedTryAgain ", Boolean.valueOf(this.x));
            d(sportsRecordingBean);
        } else {
            LogUtil.a("SportsRecordingCardReader", "mLastStartTime", Long.valueOf(this.s), "data.getSportStartTime() ", Long.valueOf(sportsRecordingBean.getSportStartTime()), "mHasPoints ", Boolean.valueOf(this.k), "mNeedTryAgain", Boolean.valueOf(this.x), ", mShowType = ", Integer.valueOf(this.w), ", sportType = ", Integer.valueOf(sportsRecordingBean.getShowType()));
            b(sportsRecordingBean);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void c(otp otpVar, FunctionSetBean functionSetBean) {
        if (functionSetBean == null) {
            LogUtil.b("SportsRecordingCardReader", "putRedDotAndBitmapToBean, bean == null");
            return;
        }
        functionSetBean.e(this.q);
        if (otpVar.e != null) {
            LogUtil.a("SportsRecordingCardReader", "bitmap not null has track is true");
        }
        notifyItemChanged(functionSetBean);
    }

    static class c implements IBaseResponseCallback {
        private SportsRecordingBean b;
        private WeakReference<otp> e;

        c(otp otpVar, SportsRecordingBean sportsRecordingBean) {
            this.e = new WeakReference<>(otpVar);
            this.b = sportsRecordingBean;
        }

        @Override // com.huawei.hwbasemgr.IBaseResponseCallback
        /* renamed from: onResponse */
        public void d(int i, Object obj) {
            LogUtil.a("SportsRecordingCardReader", "InnerBaseResponseCallback errCode = ", Integer.valueOf(i));
            otp otpVar = this.e.get();
            if (otpVar == null || otpVar.i == null) {
                return;
            }
            ArrayMap arrayMap = otpVar.ae;
            String str = this.b.getSportStartTime() + "_" + this.b.getSportEndTime();
            Integer num = (Integer) arrayMap.get(str);
            int intValue = num == null ? 0 : num.intValue();
            if (i != 303 || (arrayMap.containsKey(str) && (!arrayMap.containsKey(str) || intValue >= 3))) {
                if (i == 0) {
                    if (obj instanceof Bitmap) {
                        otpVar.e = (Bitmap) obj;
                        otpVar.dho_(otpVar.e);
                    }
                    LogUtil.a("SportsRecordingCardReader", "track pic is back hasTrack is true");
                    otpVar.x = false;
                    if (otpVar.ae != null) {
                        otpVar.ae.remove(str);
                    }
                } else {
                    LogUtil.a("SportsRecordingCardReader", "track pic is not back hasTrack is true");
                    otpVar.e = null;
                    otpVar.x = true;
                }
                otpVar.b(this.b);
                return;
            }
            int i2 = intValue + 1;
            otpVar.ae.put(str, Integer.valueOf(i2));
            LogUtil.h("SportsRecordingCardReader", "onResponse: list size error retry get data ", str, " ,times :", Integer.valueOf(i2));
            otpVar.d(this.b);
        }
    }

    private void s() {
        HiAggregateOption hiAggregateOption = new HiAggregateOption();
        hiAggregateOption.setStartTime(0L);
        hiAggregateOption.setEndTime(System.currentTimeMillis());
        hiAggregateOption.setAggregateType(1);
        hiAggregateOption.setConstantsKey(new String[]{"wear_device_type_datas"});
        hiAggregateOption.setType(new int[]{43500});
        hiAggregateOption.setGroupUnitType(7);
        hiAggregateOption.setReadType(0);
        HiHealthManager.d(this.mContext).aggregateHiHealthData(hiAggregateOption, this.o);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void d(SportsRecordingBean sportsRecordingBean) {
        this.i = new j(this);
        ArrayList arrayList = new ArrayList(20);
        ArrayList arrayList2 = new ArrayList(20);
        if (sportsRecordingBean.getShowType() == 4 && koq.c(sportsRecordingBean.getChildSportItems())) {
            for (RelativeSportData relativeSportData : sportsRecordingBean.getChildSportItems()) {
                if (relativeSportData.isHasDetailInfo()) {
                    arrayList.add(Long.valueOf(relativeSportData.getStartTime()));
                    arrayList2.add(Long.valueOf(relativeSportData.getEndTime()));
                }
            }
        } else {
            arrayList.add(Long.valueOf(sportsRecordingBean.getSportStartTime()));
            arrayList2.add(Long.valueOf(sportsRecordingBean.getSportEndTime()));
        }
        LogUtil.a("SportsRecordingCardReader", "enter getTrackDraw");
        hpu.a(arrayList, arrayList2, this.mContext.getApplicationContext(), new c(this, sportsRecordingBean));
    }

    private void l() {
        LogUtil.a("SportsRecordingCardReader", "refreshSportDataAndTime called");
        this.t = false;
        Message obtainMessage = this.i.obtainMessage();
        obtainMessage.what = 1;
        obtainMessage.obj = this.z;
        this.i.sendMessage(obtainMessage);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a(CharSequence charSequence, CharSequence charSequence2, String str, CharSequence charSequence3, boolean z) {
        setBiHasData(this.s);
        setBiHasDataType("" + this.aa);
        FunctionSetBean c2 = new FunctionSetBean.a(BaseApplication.getContext().getResources().getString(R.string.IDS_motiontrack_my_health_data)).c(charSequence).a(charSequence2).b(str).a(FunctionSetType.SPORTS_CARD).b(FunctionSetBean.ViewType.DATA_VIEW).e(charSequence3).d(BaseApplication.getContext()).c();
        LogUtil.a("SportsRecordingCardReader", "refreshCardView: ", Boolean.valueOf(z));
        if (z) {
            Message obtainMessage = this.i.obtainMessage(6);
            obtainMessage.obj = c2;
            this.i.sendMessage(obtainMessage);
            return;
        }
        refreshCardBySp(c2);
    }

    private void m() {
        LogUtil.a("SportsRecordingCardReader", "refreshFitnessDataAndTime called");
        WorkoutRecord workoutRecord = this.u;
        if (workoutRecord == null) {
            LogUtil.b("SportsRecordingCardReader", "refreshFitnessDataAndTime mRecord is null.");
            return;
        }
        if (workoutRecord.acquireExerciseTime() == 0) {
            return;
        }
        SportsRecordingBean d2 = new otr().d(this.u);
        this.aa = 10001;
        this.t = true;
        this.k = false;
        Message obtainMessage = this.i.obtainMessage();
        obtainMessage.what = 1;
        obtainMessage.obj = d2;
        this.i.sendMessage(obtainMessage);
    }

    static class i implements IBaseResponseCallback {
        private WeakReference<otp> c;

        i(otp otpVar) {
            this.c = new WeakReference<>(otpVar);
        }

        @Override // com.huawei.hwbasemgr.IBaseResponseCallback
        /* renamed from: onResponse */
        public void d(int i, final Object obj) {
            final otp otpVar = this.c.get();
            if (otpVar == null) {
                LogUtil.h("SportsRecordingCardReader", "TrackForTriathlon reader is null");
            } else if (i != 0) {
                LogUtil.h("SportsRecordingCardReader", "TrackForTriathlon callback fail and errCode is ", Integer.valueOf(i));
                otpVar.b();
            } else {
                ThreadPoolManager.d().execute(new Runnable() { // from class: otp.i.4
                    @Override // java.lang.Runnable
                    public void run() {
                        otpVar.d(obj);
                    }
                });
            }
        }
    }

    static class f implements IBaseResponseCallback {
        private final WeakReference<otp> b;

        f(otp otpVar) {
            this.b = new WeakReference<>(otpVar);
        }

        @Override // com.huawei.hwbasemgr.IBaseResponseCallback
        /* renamed from: onResponse */
        public void d(int i, final Object obj) {
            LogUtil.a("SportsRecordingCardReader", "LastTrackCallBack onResponse()");
            final otp otpVar = this.b.get();
            if (otpVar != null) {
                otpVar.b();
                if (i != 0) {
                    otpVar.b();
                    return;
                } else {
                    ThreadPoolManager.d().execute(new Runnable() { // from class: otp.f.3
                        @Override // java.lang.Runnable
                        public void run() {
                            otpVar.e(obj);
                        }
                    });
                    return;
                }
            }
            LogUtil.h("SportsRecordingCardReader", "LastTrackCallBack reader is null");
        }
    }

    private void a(MotionPathSimplify motionPathSimplify) {
        c(motionPathSimplify);
        LogUtil.a("SportsRecordingCardReader", "initCallbackData sports result = ", motionPathSimplify);
        ReleaseLogUtil.e("R_SportsRecordingCardReader", "initCallbackData sports result");
        oth b2 = new otw().b(motionPathSimplify.requestChiefSportDataType(), motionPathSimplify.requestSportType());
        this.z = b2.b(motionPathSimplify);
        this.w = b2.c();
    }

    private void c(MotionPathSimplify motionPathSimplify) {
        this.aa = motionPathSimplify.requestSportType();
        boolean hasTrackPoint = motionPathSimplify.hasTrackPoint();
        this.k = hasTrackPoint;
        LogUtil.a("SportsRecordingCardReader", "has track from simplify", Boolean.valueOf(hasTrackPoint));
        this.ag = motionPathSimplify.requestStartTime();
        this.g = motionPathSimplify.requestEndTime();
        this.f15947a = motionPathSimplify.getExtendDataInt("divingMode");
        this.ad = gwg.a(motionPathSimplify) ? this.mContext.getString(R.string._2130845268_res_0x7f021e54) : "";
        setBiDataSource(e(motionPathSimplify));
    }

    private String e(MotionPathSimplify motionPathSimplify) {
        return motionPathSimplify == null ? "" : motionPathSimplify.requestSportDataSource() == 2 ? "manual" : (motionPathSimplify.requestSportDataSource() == 3 || motionPathSimplify.requestSportDataSource() == 5) ? "thirdDevice" : (motionPathSimplify.requestTrackType() < 2 || motionPathSimplify.requestTrackType() > 7) ? "telephone" : "wearDevice";
    }

    static class a implements IBaseResponseCallback {
        private WeakReference<otp> c;

        private a(otp otpVar) {
            this.c = new WeakReference<>(otpVar);
        }

        @Override // com.huawei.hwbasemgr.IBaseResponseCallback
        /* renamed from: onResponse */
        public void d(int i, Object obj) {
            LogUtil.a("SportsRecordingCardReader", "LastFitnessCallback onResponse");
            otp otpVar = this.c.get();
            if (otpVar == null) {
                LogUtil.h("SportsRecordingCardReader", "LastFitnessCallback reader is null");
                return;
            }
            if (i != 0 || obj == null) {
                LogUtil.h("SportsRecordingCardReader", "LastFitnessCallback failed objData may be none.");
                otpVar.b();
                return;
            }
            if (koq.e(obj, WorkoutRecord.class)) {
                List list = (List) obj;
                if (koq.c(list)) {
                    otpVar.u = (WorkoutRecord) list.get(0);
                } else {
                    otpVar.u = null;
                }
            }
            if (obj instanceof WorkoutRecord) {
                otpVar.u = (WorkoutRecord) obj;
            }
            otpVar.b();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void b() {
        CountDownLatch countDownLatch = this.c;
        if (countDownLatch != null) {
            countDownLatch.countDown();
            LogUtil.c("SportsRecordingCardReader", "mCountDownLatch count = ", Long.valueOf(this.c.getCount()));
            if (this.c.getCount() <= 0) {
                e();
            }
        }
    }

    private void e() {
        WorkoutRecord workoutRecord = this.u;
        boolean z = false;
        boolean z2 = (workoutRecord == null || workoutRecord.acquireExerciseTime() == 0) ? false : true;
        boolean z3 = this.p;
        if (!z3 && !z2) {
            this.h.dhs_(null, false, 0, null);
            this.n = true;
            return;
        }
        if (!z2) {
            l();
            return;
        }
        if (!z3) {
            m();
            return;
        }
        if (b(this.aa)) {
            if (b(this.u.acquireWorkoutName(), this.mContext)) {
                LogUtil.a("SportsRecordingCardReader", "Stretching training");
                this.g += 3600000;
                z = true;
            } else {
                LogUtil.a("SportsRecordingCardReader", "not is Stretching training");
            }
        }
        if (this.ag > this.u.startTime()) {
            if (z) {
                this.g -= 3600000;
            }
            l();
        } else {
            this.w = 3;
            if (z) {
                this.g -= 3600000;
            }
            m();
        }
    }

    private boolean b(String str, Context context) {
        if (context != null && str != null) {
            return str.equals(context.getString(R.string._2130842372_res_0x7f021304));
        }
        LogUtil.b("SportsRecordingCardReader", "str is null or content is null");
        return false;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void o() {
        Message obtainMessage = this.i.obtainMessage();
        obtainMessage.what = 2;
        this.i.sendMessage(obtainMessage);
    }

    @Override // com.huawei.health.health.utils.functionsetcard.reader.FunctionSetBeanReader, com.huawei.health.health.utils.functionsetcard.reader.FunctionSetSubCardData
    public final void readCardData() {
        if (this.n) {
            this.n = false;
            super.readCardData();
            i();
            ThreadPoolManager.d().execute(new Runnable() { // from class: otp.2
                @Override // java.lang.Runnable
                public void run() {
                    otp.this.g();
                }
            });
            return;
        }
        LogUtil.a("SportsRecordingCardReader", "readCardData is not finish");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void g() {
        LogUtil.a("SportsRecordingCardReader", "executeReadCardData");
        this.p = false;
        this.c = new CountDownLatch(3);
        this.b = new CountDownLatch(1);
        kor.a().b(this.v);
        RecordApi recordApi = (RecordApi) Services.a("CoursePlanService", RecordApi.class);
        if (recordApi == null) {
            LogUtil.h("SportsRecordingCardReader", "executeReadCardData recordApi is null.");
        } else {
            recordApi.acquireDetailFitnessRecords(new kwy.a().a(0L).e(System.currentTimeMillis()).b(1).d(), this.r);
            s();
        }
    }

    private void i() {
        j jVar = this.i;
        if (jVar == null) {
            return;
        }
        jVar.removeMessages(3);
        this.i.sendMessageDelayed(this.i.obtainMessage(3), 5000L);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void b(final SportsRecordingBean sportsRecordingBean) {
        ThreadPoolManager.d().execute(new Runnable() { // from class: otp.3
            @Override // java.lang.Runnable
            public void run() {
                LogUtil.a("SportsRecordingCardReader", "dealShowSportView()");
                try {
                    if (otp.this.b != null) {
                        otp.this.b.await(5000L, TimeUnit.MILLISECONDS);
                    }
                } catch (InterruptedException e2) {
                    LogUtil.b("SportsRecordingCardReader", "dealShowSportView exception = ", LogAnonymous.b((Throwable) e2));
                }
                Bitmap bitmap = otp.this.h() ? otp.this.e : null;
                Object[] objArr = new Object[6];
                objArr[0] = "dealShowSportView() has bitmap=";
                objArr[1] = Boolean.valueOf(bitmap != null);
                objArr[2] = ", isFitness=";
                objArr[3] = Boolean.valueOf(otp.this.t);
                objArr[4] = ", sportType=";
                objArr[5] = Integer.valueOf(otp.this.aa);
                LogUtil.a("SportsRecordingCardReader", objArr);
                otp.this.h.dhs_(sportsRecordingBean, otp.this.t, otp.this.aa, bitmap);
                otp.this.n = true;
            }
        });
    }

    @Override // com.huawei.health.health.utils.functionsetcard.reader.FunctionSetSubCardData
    public void updateSuccessList(List<Integer> list) {
        LogUtil.a("SportsRecordingCardReader", "subscribeSportData, onResult");
        if (list == null || list.isEmpty()) {
            return;
        }
        LogUtil.a("SportsRecordingCardReader", "registerSportListener success");
        this.ab = list;
    }

    private void p() {
        LogUtil.a("SportsRecordingCardReader", "subscribeSportData");
        this.ac.add(4);
        HiHealthNativeApi.a(this.mContext).subscribeHiHealthData(this.ac, new FunctionSetBeanReader.c("SportsRecordingCardReader", this));
        this.y = new d(this);
        ary.a().e(this.y, "WORKOUT_FINISHED");
    }

    static class d implements OnFitnessStatusChangeCallback {
        private WeakReference<otp> c;

        d(otp otpVar) {
            this.c = new WeakReference<>(otpVar);
        }

        @Override // com.huawei.basefitnessadvice.callback.OnFitnessStatusChangeCallback
        public void onUpdate() {
            LogUtil.a("SportsRecordingCardReader", "update by fitness");
            otp otpVar = this.c.get();
            if (otpVar == null) {
                LogUtil.h("SportsRecordingCardReader", "FitnessStatusChangeCallback reader is null");
            } else {
                otpVar.readCardData();
            }
        }
    }

    public void a() {
        LogUtil.a("SportsRecordingCardReader", "unSubscribeSportRecordingData");
        List<Integer> list = this.ab;
        if (list != null && !list.isEmpty()) {
            HiHealthNativeApi.a(this.mContext).unSubscribeHiHealthData(this.ab, new FunctionSetBeanReader.b("SportsRecordingCardReader", "unSubscribeSportRecordingData, isSuccess:"));
        }
        ary.a().c(this.y, "WORKOUT_FINISHED");
    }

    static class e implements HiAggregateListener {
        private WeakReference<otp> e;

        e(otp otpVar) {
            this.e = new WeakReference<>(otpVar);
        }

        @Override // com.huawei.hihealth.data.listener.HiAggregateListener
        public void onResult(final List<HiHealthData> list, int i, int i2) {
            final otp otpVar = this.e.get();
            if (otpVar == null) {
                LogUtil.h("SportsRecordingCardReader", "sportsRecordingCard is null.");
            } else {
                ThreadPoolManager.d().execute(new Runnable() { // from class: otp.e.4
                    @Override // java.lang.Runnable
                    public void run() {
                        otpVar.b(list);
                    }
                });
            }
        }

        @Override // com.huawei.hihealth.data.listener.HiAggregateListener
        public void onResultIntent(int i, List<HiHealthData> list, int i2, int i3) {
            LogUtil.a("SportsRecordingCardReader", "onResultIntent: called");
        }
    }

    private boolean b(int i2) {
        String e2 = gwg.e(this.mContext, i2);
        if (e2 != null && (e2.equals(this.mContext.getString(R.string.IDS_start_track_sport_type_outdoor_run)) || e2.equals(this.mContext.getString(R.string.IDS_start_track_sport_type_indoor_run)))) {
            LogUtil.a("SportsRecordingCardReader", "is TypeRun");
            return true;
        }
        LogUtil.a("SportsRecordingCardReader", "not is TypeRun");
        return false;
    }

    @Override // com.huawei.health.health.utils.functionsetcard.reader.FunctionSetSubCardData
    public View createCardView() {
        HealthImageView healthImageView = new HealthImageView(this.mContext);
        LogUtil.a("SportsRecordingCardReader", "createCardView");
        this.l = new WeakReference<>(healthImageView);
        if (this.h.dhr_(healthImageView)) {
            LogUtil.a("SportsRecordingCardReader", "createCardView initCardView done");
            return healthImageView;
        }
        if (h()) {
            LogUtil.a("SportsRecordingCardReader", "hasTrack is true");
            healthImageView.setImageBitmap(this.e);
        } else {
            LogUtil.a("SportsRecordingCardReader", "hasTrack is false");
            dhm_(healthImageView, this.t, this.aa);
        }
        return healthImageView;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public boolean h() {
        return this.e != null && (!(!this.k || this.t || this.w == 6) || this.w == 4);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void dhm_(ImageView imageView, boolean z, int i2) {
        LogUtil.a("SportsRecordingCardReader", "initCardView default pic with isFitness = ", Boolean.valueOf(z), ", sportType = ", Integer.valueOf(i2));
        if (z) {
            i2 = 10001;
        }
        Drawable dhl_ = dhl_(i2);
        if (LanguageUtil.bc(this.mContext)) {
            dhl_ = nrz.cKm_(this.mContext, dhl_);
        }
        imageView.setImageDrawable(dhl_);
    }

    private Drawable dhl_(int i2) {
        HwSportTypeInfo d2 = hln.c(this.mContext).d(i2);
        Drawable drawable = this.mContext.getDrawable(R.drawable.home_page_cross_country_race);
        return (d2 == null || d2.getHomePage() == null) ? drawable : d2.getHomePage().getShowCardDrawable(Integer.toString(this.f15947a));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public String e(int i2) {
        if (i2 == 291) {
            return cat.a(this.f15947a, this.mContext);
        }
        return TextUtils.isEmpty(this.ad) ? gwg.e(this.mContext, i2) : this.ad;
    }

    @Override // com.huawei.health.health.utils.functionsetcard.reader.FunctionSetBeanReader, com.huawei.health.health.utils.functionsetcard.reader.FunctionSetSubCardData
    public void onResume() {
        long elapsedRealtime = SystemClock.elapsedRealtime();
        super.onResume();
        if (this.m) {
            ReleaseLogUtil.e("TimeEat_SportsRecordingCardReader", "first onResume finished, time cost: " + (SystemClock.elapsedRealtime() - elapsedRealtime));
            this.m = false;
            return;
        }
        ThreadPoolManager.d().execute(new Runnable() { // from class: otm
            @Override // java.lang.Runnable
            public final void run() {
                otp.this.c();
            }
        });
        readCardData();
        ReleaseLogUtil.e("TimeEat_SportsRecordingCardReader", "onResume finished, time cost: " + (SystemClock.elapsedRealtime() - elapsedRealtime));
    }

    /* synthetic */ void c() {
        if (!"true".equals(SharedPreferenceManager.b(BaseApplication.getContext(), String.valueOf(10000), "HOME_HEALTH_SHOW_DOT")) || cat.d() <= f()) {
            return;
        }
        HandlerExecutor.a(new Runnable() { // from class: oto
            @Override // java.lang.Runnable
            public final void run() {
                otp.this.d();
            }
        });
        SharedPreferenceManager.e(BaseApplication.getContext(), String.valueOf(10000), "HOME_HEALTH_SHOW_DOT", "false", new StorageParams());
    }

    /* synthetic */ void d() {
        FunctionSetBean functionSetBean = getFunctionSetBean();
        if (functionSetBean != null) {
            functionSetBean.e(false);
            notifyItemChanged(functionSetBean);
        }
    }

    @Override // com.huawei.health.health.utils.functionsetcard.reader.FunctionSetBeanReader, com.huawei.health.health.utils.functionsetcard.reader.FunctionSetSubCardData
    public void onBindViewHolder(RecyclerView.ViewHolder viewHolder, FunctionSetSubCardData functionSetSubCardData) {
        long elapsedRealtime = SystemClock.elapsedRealtime();
        super.onBindViewHolder(viewHolder, functionSetSubCardData);
        FunctionSetBean functionSetBean = functionSetSubCardData.getFunctionSetBean();
        if (!(viewHolder instanceof FunctionSetBeanReader.MyHolder) || functionSetBean == null) {
            LogUtil.b("SportsRecordingCardReader", "holder or functionSetBean is invalid");
            return;
        }
        View view = ((FunctionSetBeanReader.MyHolder) viewHolder).itemView;
        LogUtil.a("SportsRecordingCardReader", "itemView: " + view);
        if (functionSetBean.q() == FunctionSetBean.ViewType.EMPTY_VIEW) {
            ImageView imageView = (ImageView) view.findViewById(R.id.function_set_empty_red_dot);
            if (imageView == null) {
                LogUtil.b("SportsRecordingCardReader", "functionSetEmptyRedDot is null");
                return;
            } else if (functionSetBean.h()) {
                imageView.setVisibility(0);
            } else {
                imageView.setVisibility(8);
            }
        }
        ReleaseLogUtil.e("TimeEat_SportsRecordingCardReader", "onBindViewHolder finished, time cost: " + (SystemClock.elapsedRealtime() - elapsedRealtime));
    }

    @Override // com.huawei.health.health.utils.functionsetcard.reader.FunctionSetBeanReader
    public void onCardViewClickListener(FunctionSetBean.ViewType viewType) {
        if (nsn.a(500)) {
            return;
        }
        if (LoginInit.getInstance(BaseApplication.getContext()).isBrowseMode()) {
            LoginInit.getInstance(BaseApplication.getContext()).browsingToLogin(new IBaseResponseCallback() { // from class: otn
                @Override // com.huawei.hwbasemgr.IBaseResponseCallback
                /* renamed from: onResponse */
                public final void d(int i2, Object obj) {
                    otp.c(i2, obj);
                }
            }, AnalyticsValue.HEALTH_HOME_GPS_HISTORY_2010015.value());
        } else {
            j();
        }
    }

    private void j() {
        HashMap hashMap = new HashMap(1);
        hashMap.put("click", 1);
        ixx.d().d(this.mContext, AnalyticsValue.HEALTH_HOME_GPS_HISTORY_2010015.value(), hashMap, 0);
        if (this.hasCardData || efb.e(this.mContext)) {
            Intent intent = new Intent(this.mContext, (Class<?>) SportHistoryActivity.class);
            intent.setFlags(268435456);
            intent.putExtra(BleConstants.SPORT_TYPE, 0);
            gnm.aPB_(this.mContext, intent);
            if (BaseApplication.getActivity() != null) {
                BaseApplication.getActivity().overridePendingTransition(0, R.anim._2130771981_res_0x7f01000d);
                return;
            }
            return;
        }
        LogUtil.a("SportsRecordingCardReader", "jump to sport-run page");
        Intent intent2 = new Intent();
        intent2.setAction(CommonConstant.ACTION.HWID_SCHEME_URL);
        intent2.setFlags(268435456);
        intent2.setData(Uri.parse("huaweischeme://healthapp/track?sportType=2&targetType=m&targetValue=1000"));
        gnm.aPB_(this.mContext, intent2);
    }

    @Override // com.huawei.health.health.utils.functionsetcard.reader.FunctionSetBeanReader, com.huawei.health.health.utils.functionsetcard.reader.FunctionSetSubCardData
    public void onDestroy() {
        super.onDestroy();
        a();
        j jVar = this.i;
        if (jVar != null) {
            jVar.removeCallbacksAndMessages(null);
        }
    }

    @Override // com.huawei.health.health.utils.functionsetcard.reader.FunctionSetBeanReader
    public String getDefaultMarketingDes(int i2) {
        return this.mContext.getResources().getString(i2, 1);
    }

    @Override // com.huawei.health.health.utils.functionsetcard.reader.FunctionSetBeanReader
    public void setGifImage(final ImageView imageView, final boolean z) {
        if (imageView == null) {
            LogUtil.h("SportsRecordingCardReader", "setGifImage gifView is null");
            return;
        }
        boolean a2 = nrt.a(this.mContext);
        LogUtil.a("SportsRecordingCardReader", "setGifImage, isDark: ", Boolean.valueOf(a2));
        if (a2) {
            if (!TextUtils.isEmpty(this.d)) {
                dhk_(imageView, z);
                return;
            }
        } else if (!TextUtils.isEmpty(this.j)) {
            dhk_(imageView, z);
            return;
        }
        ThreadPoolManager.d().execute(new Runnable() { // from class: otp.1
            @Override // java.lang.Runnable
            public void run() {
                otp otpVar = otp.this;
                otpVar.f = nsb.b(otpVar.mContext, "functionset_sport_record_gif_background", ResInfo.Location.HOME);
                if (!koq.b(otp.this.f) && otp.this.f.size() > 1) {
                    LogUtil.a("SportsRecordingCardReader", "mGifList: ", otp.this.f);
                    for (String str : otp.this.f) {
                        if (!TextUtils.isEmpty(str)) {
                            if (str.endsWith("z_sport_record_gif.gif")) {
                                otp.this.j = str;
                            }
                            if (str.endsWith("z_sport_record_gif_dark.gif")) {
                                otp.this.d = str;
                            }
                        }
                    }
                    otp.this.dhk_(imageView, z);
                    return;
                }
                LogUtil.b("SportsRecordingCardReader", "mGifList is error");
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void dhk_(final ImageView imageView, final boolean z) {
        if (!HandlerExecutor.b()) {
            HandlerExecutor.a(new Runnable() { // from class: otp.4
                @Override // java.lang.Runnable
                public void run() {
                    otp.this.dhk_(imageView, z);
                }
            });
            return;
        }
        RequestOptions diskCacheStrategy = new RequestOptions().diskCacheStrategy(DiskCacheStrategy.RESOURCE);
        boolean a2 = nrt.a(this.mContext);
        LogUtil.a("SportsRecordingCardReader", "bindGifImage, isDark: ", Boolean.valueOf(a2));
        if (a2) {
            if (TextUtils.isEmpty(this.d)) {
                LogUtil.b("SportsRecordingCardReader", "mDarkGif is null");
                return;
            } else {
                nrf.cIw_(this.d, diskCacheStrategy, imageView);
                return;
            }
        }
        if (TextUtils.isEmpty(this.j)) {
            LogUtil.b("SportsRecordingCardReader", "mGif is null");
        } else {
            nrf.cIw_(this.j, diskCacheStrategy, imageView);
        }
    }

    @Override // com.huawei.health.health.utils.functionsetcard.reader.FunctionSetBeanReader
    public HandleCacheDataRunnable getRunnable() {
        return this.h;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: dhn_, reason: merged with bridge method [inline-methods] */
    public void dho_(final Bitmap bitmap) {
        if (!Looper.getMainLooper().isCurrentThread()) {
            HandlerExecutor.a(new Runnable() { // from class: otl
                @Override // java.lang.Runnable
                public final void run() {
                    otp.this.dho_(bitmap);
                }
            });
            return;
        }
        ImageView imageView = this.l.get();
        if (imageView != null) {
            imageView.setImageBitmap(bitmap);
        }
    }

    @Override // com.huawei.health.health.utils.functionsetcard.reader.FunctionSetBeanReader
    public String getChildTag() {
        return "SportsRecordingCardReader";
    }
}
