package defpackage;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.media.AudioAttributes;
import android.os.Message;
import android.os.VibrationEffect;
import android.os.Vibrator;
import android.view.View;
import android.widget.LinearLayout;
import com.huawei.haf.application.BaseApplication;
import com.huawei.haf.application.BroadcastManager;
import com.huawei.haf.handler.BaseHandlerCallback;
import com.huawei.haf.handler.ExtendHandler;
import com.huawei.haf.handler.HandlerCenter;
import com.huawei.health.R;
import com.huawei.health.algorithm.api.BreathTrainApi;
import com.huawei.health.algorithm.api.BreatheWearAppInterface;
import com.huawei.health.breathe.bean.BreatheBean;
import com.huawei.health.breathtrain.BreatheDataProvider;
import com.huawei.hianalytics.visual.autocollect.instrument.ViewClickInstrumentation;
import com.huawei.hihealth.HiDataInsertOption;
import com.huawei.hihealth.HiHealthData;
import com.huawei.hihealth.api.HiHealthNativeApi;
import com.huawei.hihealth.data.listener.HiDataOperateListener;
import com.huawei.hihealth.dictionary.utils.DicDataTypeUtil;
import com.huawei.hihealth.util.HiJsonUtil;
import com.huawei.hmf.annotation.ApiDefine;
import com.huawei.hmf.annotation.Singleton;
import com.huawei.hwbasemgr.IBaseResponseCallback;
import com.huawei.hwcommonmodel.constants.AnalyticsValue;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.ui.commonui.dialog.CustomViewDialog;
import com.huawei.ui.commonui.healthtextview.HealthTextView;
import com.huawei.utils.FoundationCommonUtil;
import health.compact.a.HiCommonUtil;
import health.compact.a.hwlogsmodel.ReleaseLogUtil;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

@ApiDefine(uri = BreathTrainApi.class)
@Singleton
/* loaded from: classes3.dex */
public class cbp implements BreathTrainApi {
    private static List<JSONObject> e = new ArrayList(16);
    private Vibrator ad;
    private BreatheWearAppInterface g;
    private CustomViewDialog j;
    private int[] z;
    private List<Integer> v = new ArrayList(16);
    private List<Integer> p = new ArrayList(16);
    private List<float[]> i = new ArrayList(16);
    private List<Integer> u = new ArrayList(16);
    private List<Integer> k = new ArrayList(16);
    private List<Float> d = new ArrayList(16);
    private int n = 0;
    private int r = -1;
    private float[] b = null;
    private boolean s = false;
    private int ab = -1;
    private int m = 3;
    private int q = 0;
    private AtomicBoolean l = new AtomicBoolean(false);
    private int aa = 100;
    private boolean t = false;
    private int y = 1;
    private int h = 0;

    /* renamed from: a, reason: collision with root package name */
    private boolean f598a = false;
    private boolean o = false;
    private long c = 0;
    private IBaseResponseCallback f = new IBaseResponseCallback() { // from class: cbp.4
        @Override // com.huawei.hwbasemgr.IBaseResponseCallback
        /* renamed from: onResponse */
        public void d(int i, Object obj) {
            if (obj != null) {
                cbp.this.d(i, obj);
            } else {
                LogUtil.a("BreathTrainImpl", "mCallBack onResponse objData is null.");
            }
        }
    };
    private final ExtendHandler w = HandlerCenter.yt_(new c(this), "BreathTrainImpl");
    private b x = new b();

    class b extends BroadcastReceiver {
        private b() {
        }

        @Override // android.content.BroadcastReceiver
        public void onReceive(Context context, Intent intent) {
            if (intent == null || context == null) {
                return;
            }
            if ("android.intent.action.SCREEN_OFF".equals(intent.getAction())) {
                LogUtil.a("BreathTrainImpl", "ACTION_SCREEN_OFF");
                cbp.this.f598a = true;
            }
            LogUtil.a("BreathTrainImpl", "ScreenBroadcastReceiver intent:", intent.getAction());
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void d(int i, Object obj) {
        if (i != 0) {
            LogUtil.c("BreathTrainImpl", "checkResponseCode() errCode= ", Integer.valueOf(i));
            this.h = i;
            return;
        }
        try {
            if (e.size() >= this.n / 4) {
                LogUtil.a("BreathTrainImpl", "sListBack.size() = ", Integer.valueOf(e.size()));
                return;
            }
            if (obj instanceof String) {
                JSONObject jSONObject = new JSONObject((String) obj);
                LogUtil.c("BreathTrainImpl", "mCallBack Message is ", jSONObject);
                this.s = true;
                e.add(jSONObject);
                this.v.clear();
                this.p.clear();
                c(jSONObject);
                b();
                b(4);
                e(6);
                c(5);
            }
        } catch (JSONException e2) {
            LogUtil.b("BreathTrainImpl", "mCallBack ", e2.getMessage());
        }
    }

    @Override // com.huawei.health.algorithm.api.BreathTrainApi
    public int canFinish(Activity activity) {
        int i = this.ab;
        if (i == 1) {
            return 1;
        }
        if (i != 2) {
            return 3;
        }
        LogUtil.a("BreathTrainImpl", "show stop breathe_dialog");
        if (activity != null) {
            CB_(activity);
        }
        return 2;
    }

    private void CB_(Activity activity) {
        if (this.j == null) {
            CA_(activity);
        }
        if (activity == null || activity.isFinishing() || this.j.isShowing()) {
            return;
        }
        this.j.show();
    }

    private void CA_(final Activity activity) {
        View inflate = View.inflate(activity, R.layout.pressure_device_connect, null);
        ((HealthTextView) inflate.findViewById(R.id.pressure_device_no_connect)).setText(activity.getString(R.string._2130842621_res_0x7f0213fd));
        ((LinearLayout) inflate.findViewById(R.id.hw_health_agree_dialog_pressure)).setVisibility(8);
        this.j = new CustomViewDialog.Builder(activity).czg_(inflate).czf_(activity.getString(R.string._2130841131_res_0x7f020e2b), new View.OnClickListener() { // from class: cbp.3
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                cbp.this.j = null;
                bzb bzbVar = new bzb();
                bzbVar.d(4);
                cbp.this.breathControl(bzbVar, 0, null);
                activity.finish();
                ViewClickInstrumentation.clickOnView(view);
            }
        }).czd_(activity.getString(R.string._2130841130_res_0x7f020e2a), new View.OnClickListener() { // from class: cbp.1
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                ViewClickInstrumentation.clickOnView(view);
            }
        }).e();
    }

    @Override // com.huawei.health.algorithm.api.BreathTrainApi
    public void registerBreatheCallback(BreatheWearAppInterface breatheWearAppInterface) {
        LogUtil.a("BreathTrainImpl", "registerBreatheCallback() enter");
        this.g = breatheWearAppInterface;
    }

    private void c(int i) {
        float[] fArr = this.b;
        if (fArr == null || i >= fArr.length || i < 0) {
            return;
        }
        float f = fArr[i];
        if (f != 0.0f) {
            this.k.add(Integer.valueOf((int) f));
        }
    }

    private void b(int i) {
        float[] fArr = this.b;
        if (fArr == null || i >= fArr.length || i < 0) {
            return;
        }
        this.u.add(Integer.valueOf((int) fArr[i]));
    }

    private void c(JSONObject jSONObject) {
        JSONArray optJSONArray = jSONObject.optJSONArray("rri_list");
        for (int i = 0; i < optJSONArray.length(); i++) {
            JSONObject optJSONObject = optJSONArray.optJSONObject(i);
            a(optJSONObject.optInt("rri_value"), optJSONObject.optInt("rri_sqi"));
        }
    }

    private void b() {
        if (this.v.size() != 0) {
            int i = e.size() != 1 ? 2 : 1;
            LogUtil.a("BreathTrainImpl", " getDataResult mDuration ", Integer.valueOf(this.n), ", mRriValueList.size() ", Integer.valueOf(this.v.size()), ", firstData ", Integer.valueOf(i));
            float[] breatheResultFromAlgorithm = BreatheDataProvider.getBreatheResultFromAlgorithm(this.n, this.v.size(), e(this.v), e(this.p), i);
            this.b = breatheResultFromAlgorithm;
            if (breatheResultFromAlgorithm == null || breatheResultFromAlgorithm.length <= 9 || breatheResultFromAlgorithm[9] == 0.0f) {
                return;
            }
            this.i.add(breatheResultFromAlgorithm);
        }
    }

    private int[] e(List list) {
        int[] iArr = new int[list.size()];
        for (int i = 0; i < list.size(); i++) {
            iArr[i] = ((Integer) list.get(i)).intValue();
        }
        return iArr;
    }

    private void a(int i, int i2) {
        if (i != 0) {
            this.v.add(Integer.valueOf(i));
            this.p.add(Integer.valueOf(i2));
        }
    }

    /* JADX WARN: Type inference failed for: r0v10 */
    /* JADX WARN: Type inference failed for: r0v7 */
    /* JADX WARN: Type inference failed for: r0v8, types: [boolean, int] */
    @Override // com.huawei.health.algorithm.api.BreathTrainApi
    public int getJanusDeviceConnect() {
        List<Integer> list = this.v;
        if (list != null) {
            list.clear();
        } else {
            LogUtil.a("BreathTrainImpl", "getJanusDeviceConnect mRriValueList is null.");
        }
        List<Integer> list2 = this.p;
        if (list2 != null) {
            list2.clear();
        } else {
            LogUtil.a("BreathTrainImpl", "getJanusDeviceConnect mRriSqiList is null.");
        }
        BreatheWearAppInterface breatheWearAppInterface = this.g;
        ?? deviceSupportBreatheReport = breatheWearAppInterface != null ? breatheWearAppInterface.getDeviceSupportBreatheReport() : 0;
        LogUtil.a("BreathTrainImpl", "janus device connected ", Boolean.valueOf((boolean) deviceSupportBreatheReport));
        return deviceSupportBreatheReport;
    }

    @Override // com.huawei.health.algorithm.api.BreathTrainApi
    public void breathControl(bzb bzbVar, int i, IBaseResponseCallback iBaseResponseCallback) {
        this.l.set(false);
        int c2 = bzbVar.c();
        this.ab = c2;
        this.j = null;
        if (c2 == 1) {
            d((IBaseResponseCallback) null);
            this.s = false;
            this.b = null;
            return;
        }
        if (c2 == 2) {
            this.z = bzbVar.b();
            this.t = bzbVar.g();
            this.y = bzbVar.e();
            c(bzbVar.a(), bzbVar.d(), i);
            return;
        }
        if (c2 == 3) {
            this.l.set(true);
            c(iBaseResponseCallback);
        } else if (c2 == 4) {
            d(iBaseResponseCallback);
        } else {
            d(iBaseResponseCallback);
            LogUtil.a("BreathTrainImpl", "breathControl default");
        }
    }

    private void j() {
        if (HiCommonUtil.e(this.z)) {
            return;
        }
        if (this.ad == null) {
            Object systemService = BaseApplication.e().getSystemService("vibrator");
            if (systemService instanceof Vibrator) {
                this.ad = (Vibrator) systemService;
            }
        }
        Vibrator vibrator = this.ad;
        if (vibrator == null || !vibrator.hasVibrator()) {
            LogUtil.h("BreathTrainImpl", "this phone doesn't have vibrator");
            return;
        }
        LogUtil.a("BreathTrainImpl", "start vibrate");
        int[] iArr = this.z;
        int length = (iArr.length * 2) + 1;
        long[] jArr = new long[length];
        int[] iArr2 = new int[(iArr.length * 2) + 1];
        jArr[0] = 0;
        iArr2[0] = 0;
        int i = 0;
        while (true) {
            if (i >= this.z.length) {
                break;
            }
            int i2 = i * 2;
            int i3 = i2 + 1;
            jArr[i3] = this.aa;
            iArr2[i3] = 10;
            int i4 = i2 + 2;
            jArr[i4] = r7[i] - r10;
            iArr2[i4] = 0;
            i++;
        }
        this.ad.vibrate(VibrationEffect.createWaveform(jArr, iArr2, 0), new AudioAttributes.Builder().setContentType(4).setUsage(4).build());
        if (this.w != null) {
            this.c = 0L;
            for (int i5 = 0; i5 < length; i5++) {
                this.c += jArr[i5];
            }
            this.w.sendEmptyMessage(10002, this.c);
        }
        BroadcastManager.wn_(this.x);
    }

    private void a() {
        Vibrator vibrator = this.ad;
        if (vibrator != null) {
            vibrator.cancel();
        }
    }

    private void d() {
        if (this.t) {
            LogUtil.c("BreathTrainImpl", "set screen on");
            final Activity wa_ = BaseApplication.wa_();
            if (wa_ == null) {
                LogUtil.h("BreathTrainImpl", "set screen: activity is null");
            } else {
                wa_.runOnUiThread(new Runnable() { // from class: cbp.2
                    @Override // java.lang.Runnable
                    public void run() {
                        wa_.getWindow().addFlags(128);
                    }
                });
            }
        }
    }

    private void c() {
        if (this.t) {
            LogUtil.c("BreathTrainImpl", "unset screen on");
            final Activity wa_ = BaseApplication.wa_();
            if (wa_ == null) {
                LogUtil.h("BreathTrainImpl", "unset screen: activity is null");
            } else {
                wa_.runOnUiThread(new Runnable() { // from class: cbp.5
                    @Override // java.lang.Runnable
                    public void run() {
                        wa_.getWindow().clearFlags(128);
                    }
                });
                this.t = false;
            }
        }
    }

    private void d(IBaseResponseCallback iBaseResponseCallback) {
        if (this.ab == 4 && this.n != 0 && iBaseResponseCallback == null) {
            HashMap hashMap = new HashMap(16);
            hashMap.put("keeptime", Integer.valueOf(this.n));
            if (this.q == 1) {
                hashMap.put("serviceSuccess", 1);
            } else {
                hashMap.put("serviceSuccess", 0);
            }
            ixx.d().d(BaseApplication.e(), AnalyticsValue.HEALTH_PRESSUER_NODATA_BREATHEPARENT_KEEPTIME_2160014.value(), hashMap, 0);
            this.n = 0;
            this.r = -1;
        }
        LogUtil.a("BreathTrainImpl", "ZXJ BI -- P3");
        this.v.clear();
        this.p.clear();
        this.u.clear();
        this.k.clear();
        this.d.clear();
        e.clear();
        this.b = null;
        c(iBaseResponseCallback);
        e();
        if (this.ab == 1 && this.n != 0) {
            this.q = 1;
        } else {
            this.q = 0;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void c(IBaseResponseCallback iBaseResponseCallback) {
        a();
        c();
        this.z = null;
        this.y = 1;
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("status", 4);
            LogUtil.a("BreathTrainImpl", "breatheClose jsonObject = ", jSONObject);
        } catch (JSONException e2) {
            LogUtil.b("BreathTrainImpl", "breatheClose ", e2.getMessage());
        }
        BreatheWearAppInterface breatheWearAppInterface = this.g;
        if (breatheWearAppInterface != null && this.s && this.y == 1) {
            breatheWearAppInterface.setHeartRateReportStatus(jSONObject, new IBaseResponseCallback() { // from class: cbp.6
                @Override // com.huawei.hwbasemgr.IBaseResponseCallback
                /* renamed from: onResponse */
                public void d(int i, Object obj) {
                    if (obj == null) {
                        LogUtil.a("BreathTrainImpl", "Message is null!!!");
                    } else if (i == 100000) {
                        cbp.this.s = false;
                        cbp.this.g.unRegisterNotificationPress(cbp.this.f);
                    } else {
                        LogUtil.a("BreathTrainImpl", "Message is not right!!!");
                    }
                }
            });
        }
        this.m = 3;
        if (getJanusDeviceConnect() == 1) {
            if (this.b != null && this.i.size() > 0 && this.h == 0) {
                this.m = 1;
            } else {
                this.m = 2;
            }
        } else if (this.b != null && this.i.size() > 0) {
            this.m = 1;
        }
        a(iBaseResponseCallback);
    }

    /* JADX WARN: Code restructure failed: missing block: B:15:0x00b9, code lost:
    
        if (r4[1] != 0.0f) goto L20;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private void a(com.huawei.hwbasemgr.IBaseResponseCallback r11) {
        /*
            Method dump skipped, instructions count: 225
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: defpackage.cbp.a(com.huawei.hwbasemgr.IBaseResponseCallback):void");
    }

    private void b(IBaseResponseCallback iBaseResponseCallback, BreatheBean breatheBean, JSONObject jSONObject) {
        if (this.l.get()) {
            a(breatheBean, jSONObject.toString(), iBaseResponseCallback);
        } else if (iBaseResponseCallback != null) {
            iBaseResponseCallback.d(this.m, jSONObject.toString());
        }
        e();
    }

    private void a(BreatheBean breatheBean, final String str, final IBaseResponseCallback iBaseResponseCallback) {
        if (breatheBean == null) {
            LogUtil.h("BreathTrainImpl", "insert breatheBean is null");
            if (iBaseResponseCallback != null) {
                iBaseResponseCallback.d(this.m, str);
                return;
            }
            return;
        }
        HiHealthData hiHealthData = new HiHealthData();
        hiHealthData.setType(DicDataTypeUtil.DataType.BREATH_TRAIN_SET.value());
        hiHealthData.setDeviceUuid(FoundationCommonUtil.getAndroidId(BaseApplication.e()));
        long currentTimeMillis = System.currentTimeMillis();
        hiHealthData.setStartTime(currentTimeMillis);
        hiHealthData.setEndTime(currentTimeMillis);
        HashMap hashMap = new HashMap();
        hashMap.put(Integer.valueOf(DicDataTypeUtil.DataType.BREATH_TRAIN.value()), HiJsonUtil.e(breatheBean));
        hiHealthData.setFieldsMetaData(HiJsonUtil.e(hashMap));
        HiDataInsertOption hiDataInsertOption = new HiDataInsertOption();
        hiDataInsertOption.addData(hiHealthData);
        final int i = this.m;
        HiHealthNativeApi.a(BaseApplication.e()).insertHiHealthData(hiDataInsertOption, new HiDataOperateListener() { // from class: cbp.10
            @Override // com.huawei.hihealth.data.listener.HiDataOperateListener
            public void onResult(int i2, Object obj) {
                LogUtil.a("BreathTrainImpl", "insert onResult errorCode ", Integer.valueOf(i2), " object ", obj, "movement:", Boolean.valueOf(cbp.this.o));
                if (i2 == 0) {
                    dsl.a(5);
                }
                if (iBaseResponseCallback == null) {
                    return;
                }
                if (cbp.this.o) {
                    cbp.this.o = false;
                    iBaseResponseCallback.d(4, new JSONObject().toString());
                } else {
                    iBaseResponseCallback.d(i, str);
                }
            }
        });
    }

    @Override // com.huawei.health.algorithm.api.BreathTrainApi
    public void insert(BreatheBean breatheBean) {
        a(breatheBean, "", null);
    }

    private void e(int i) {
        float[] fArr = this.b;
        if (fArr == null || i <= 0 || i >= fArr.length) {
            return;
        }
        float f = fArr[i];
        if (f != 0.0f) {
            this.d.add(Float.valueOf(f));
        }
    }

    private void c(final int i, int i2, int i3) {
        BreatheWearAppInterface breatheWearAppInterface;
        LogUtil.c("BreathTrainImpl", "breatheOpen = ", Integer.valueOf(i3));
        a(i);
        j();
        d();
        this.v.clear();
        this.p.clear();
        this.u.clear();
        this.k.clear();
        this.d.clear();
        e.clear();
        this.i.clear();
        this.b = null;
        this.n = i == 0 ? 60 : i;
        this.r = i2;
        this.h = 0;
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("status", 3);
        } catch (JSONException e2) {
            LogUtil.b("BreathTrainImpl", " breatheOpen ", e2.getMessage());
        }
        LogUtil.a("BreathTrainImpl", "breatheOpen jsonObject = ", jSONObject);
        if (this.y == 1 && (breatheWearAppInterface = this.g) != null && breatheWearAppInterface.getDeviceSupportBreatheReport()) {
            LogUtil.a("BreathTrainImpl", " breatheOpen send message");
            this.g.setHeartRateReportStatus(jSONObject, new IBaseResponseCallback() { // from class: cbp.8
                @Override // com.huawei.hwbasemgr.IBaseResponseCallback
                /* renamed from: onResponse */
                public void d(int i4, Object obj) {
                    if (obj == null) {
                        LogUtil.a("BreathTrainImpl", "Message is null!!!");
                        return;
                    }
                    ReleaseLogUtil.e("BreathTrainImpl", "errorCode: ", Integer.valueOf(i4));
                    if (i4 == 126007) {
                        cbp.this.o = true;
                    } else if (i4 == 100000) {
                        LogUtil.c("BreathTrainImpl", "breatheOpen()====", Integer.valueOf(i));
                        cbp.this.s = true;
                        cbp.this.g.registerNotificationPress(cbp.this.f);
                    }
                }
            });
        }
    }

    private void a(int i) {
        ExtendHandler extendHandler = this.w;
        if (extendHandler != null) {
            extendHandler.sendEmptyMessage(10001, (i + 8) * 1000);
        }
    }

    private void e() {
        ExtendHandler extendHandler = this.w;
        if (extendHandler != null) {
            extendHandler.removeMessages(10001);
            this.w.removeMessages(10002);
        } else {
            LogUtil.b("BreathTrainImpl", "cancelStressTimeoutWaiting(), mStressErrorHandler is null");
        }
        BroadcastManager.wB_(this.x);
    }

    static class c extends BaseHandlerCallback<cbp> {
        c(cbp cbpVar) {
            super(cbpVar);
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // com.huawei.haf.handler.BaseHandlerCallback
        /* renamed from: CC_, reason: merged with bridge method [inline-methods] */
        public boolean handleMessageWhenReferenceNotNull(cbp cbpVar, Message message) {
            int i = message.what;
            if (i == 10001) {
                cbpVar.c((IBaseResponseCallback) null);
                return true;
            }
            if (i == 10002) {
                c(cbpVar);
                return true;
            }
            LogUtil.b("BreathTrainImpl", "mStressErrorHandler, unknown error code");
            return false;
        }

        private void c(cbp cbpVar) {
            LogUtil.a("BreathTrainImpl", "breathTrain.isOpenVibrator==", Boolean.valueOf(cbpVar.f598a));
            if (cbpVar.w != null) {
                cbpVar.w.removeMessages(10002);
                cbpVar.w.sendEmptyMessage(10002, cbpVar.c);
            }
            if (cbpVar.ad != null && cbpVar.ad.hasVibrator()) {
                if (cbpVar.f598a) {
                    LogUtil.a("BreathTrainImpl", "openVibrator");
                    cbpVar.f598a = false;
                    if (cbpVar.z != null) {
                        long[] jArr = new long[(cbpVar.z.length * 2) + 1];
                        int[] iArr = new int[(cbpVar.z.length * 2) + 1];
                        jArr[0] = 0;
                        iArr[0] = 0;
                        for (int i = 0; i < cbpVar.z.length; i++) {
                            int i2 = i * 2;
                            int i3 = i2 + 1;
                            jArr[i3] = cbpVar.aa;
                            iArr[i3] = 10;
                            int i4 = i2 + 2;
                            jArr[i4] = cbpVar.z[i] - cbpVar.aa;
                            iArr[i4] = 0;
                        }
                        cbpVar.ad.vibrate(VibrationEffect.createWaveform(jArr, iArr, 0), new AudioAttributes.Builder().setContentType(4).setUsage(4).build());
                        return;
                    }
                    LogUtil.h("BreathTrainImpl", "mVibrateDuration == null");
                    return;
                }
                return;
            }
            LogUtil.h("BreathTrainImpl", "this phone doesn't have vibrator");
        }
    }
}
