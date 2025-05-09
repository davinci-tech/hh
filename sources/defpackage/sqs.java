package defpackage;

import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import com.huawei.haf.handler.ExtendHandler;
import com.huawei.haf.handler.HandlerCenter;
import com.huawei.health.manager.util.HiHealthHelper;
import com.huawei.health.manager.util.TimeUtil;
import com.huawei.health.suggestion.model.FitRunPlayAudio;
import com.huawei.health.userprofilemgr.api.UserProfileMgrApi;
import com.huawei.hihealth.HiHealthData;
import com.huawei.hihealth.api.HiHealthManager;
import com.huawei.hihealth.data.listener.HiDataOperateListener;
import com.huawei.hihealth.motion.HealthOpenSDK;
import com.huawei.hihealth.motion.IExecuteResult;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.operation.utils.Constants;
import com.huawei.up.model.UserInfomation;
import com.huawei.utils.FoundationCommonUtil;
import health.compact.a.CalculateCaloriesUtils;
import health.compact.a.Services;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/* loaded from: classes7.dex */
public class sqs {
    private HealthOpenSDK b;
    private List<gyc> k;
    private int l = 0;
    private int d = 0;
    private int e = 0;
    private int j = -1;
    private int p = 0;
    private int f = -1;
    private int i = -1;
    private int o = 0;
    private int n = 0;
    private long r = 0;
    private boolean c = false;
    private int g = 0;
    private long m = 0;
    private int h = -1;
    private float t = 0.5f;
    private float q = 60.0f;
    private String s = "unKnown";

    /* renamed from: a, reason: collision with root package name */
    private final ExtendHandler f17221a = HandlerCenter.yt_(new d(), "Track_TreadmillStepPointData");

    class d implements Handler.Callback {
        private d() {
        }

        @Override // android.os.Handler.Callback
        public boolean handleMessage(Message message) {
            switch (message.what) {
                case 1001:
                    sqs.this.ekH_(message);
                    LogUtil.a("Track_TreadmillStepPointData", "MSG_START step is", Integer.valueOf(sqs.this.l), ", calories is ", Integer.valueOf(sqs.this.d), ", distance is ", Integer.valueOf(sqs.this.e));
                    sqs sqsVar = sqs.this;
                    sqsVar.j = sqsVar.l;
                    sqs sqsVar2 = sqs.this;
                    sqsVar2.f = sqsVar2.d;
                    sqs sqsVar3 = sqs.this;
                    sqsVar3.i = sqsVar3.e;
                    return true;
                case 1002:
                    LogUtil.a("Track_TreadmillStepPointData", "MSG_STOP ");
                    sqs.this.ekH_(message);
                    sqs.this.b();
                    return true;
                case 1003:
                    LogUtil.a("Track_TreadmillStepPointData", "MSG_CROSS_DAY ");
                    sqs.this.ekH_(message);
                    sqs.this.b();
                    sqs sqsVar4 = sqs.this;
                    sqsVar4.p = sqsVar4.o;
                    sqs.this.m = System.currentTimeMillis() + 60000;
                    try {
                        sqs.this.h = Integer.parseInt(new SimpleDateFormat(FitRunPlayAudio.PLAY_TYPE_D).format(Long.valueOf(sqs.this.m)));
                        return true;
                    } catch (NumberFormatException unused) {
                        sqs.this.h = -1;
                        LogUtil.h("Track_TreadmillStepPointData", "isNewDay exception");
                        return true;
                    }
                case 1004:
                    sqs.this.ekH_(message);
                    LogUtil.a("Track_TreadmillStepPointData", "MSG_REFRESH_DATA step is", Integer.valueOf(sqs.this.l), ",calories is ", Integer.valueOf(sqs.this.d), ", distance is ", Integer.valueOf(sqs.this.e));
                    sqs sqsVar5 = sqs.this;
                    sqsVar5.j = sqsVar5.l;
                    sqs sqsVar6 = sqs.this;
                    sqsVar6.f = sqsVar6.d;
                    sqs sqsVar7 = sqs.this;
                    sqsVar7.i = sqsVar7.e;
                    sqs.this.c = false;
                    return true;
                default:
                    return false;
            }
        }
    }

    public void c(Context context, long j) {
        this.g = 0;
        this.r = j / 60000;
        this.o = 0;
        this.k = new ArrayList();
        this.j = -1;
        this.f = -1;
        this.i = -1;
        this.s = FoundationCommonUtil.getAndroidId(context);
        this.m = System.currentTimeMillis();
        this.p = 0;
        try {
            this.h = Integer.parseInt(new SimpleDateFormat(FitRunPlayAudio.PLAY_TYPE_D).format(Long.valueOf(this.m)));
        } catch (NumberFormatException unused) {
            LogUtil.h("Track_TreadmillStepPointData", "isNewDay exception");
        }
        HealthOpenSDK healthOpenSDK = this.b;
        if (healthOpenSDK != null) {
            healthOpenSDK.b((IExecuteResult) new a());
        } else {
            LogUtil.a("Track_TreadmillStepPointData", "create mHealthOpenSdk.");
            HealthOpenSDK healthOpenSDK2 = new HealthOpenSDK();
            this.b = healthOpenSDK2;
            healthOpenSDK2.initSDK(context, new a(), "TreadmillStepPointData");
            this.b.b((IExecuteResult) new a());
        }
        a();
    }

    public void c(long j, int i) {
        long j2 = j / 60000;
        this.o = i;
        if (this.r < j2) {
            if (this.k == null) {
                this.k = new ArrayList();
            }
            this.k.clear();
            LogUtil.a("Track_TreadmillStepPointData", "Time Changed old ", Long.valueOf(this.r), " new ", Long.valueOf(j2));
            if (this.f17221a != null) {
                if (this.g == 2) {
                    this.g = 3;
                    this.b.b((IExecuteResult) new a());
                }
                if (this.g == 0 && !this.c && e()) {
                    this.g = 2;
                    this.c = true;
                    this.b.b((IExecuteResult) new a());
                }
            }
            int i2 = i - this.n;
            this.r = j2;
            this.n = i;
            if (i2 <= 0) {
                LogUtil.a("Track_TreadmillStepPointData", "diffStep is 0");
                return;
            }
            if (i2 > 300.0d) {
                LogUtil.a("Track_TreadmillStepPointData", "diffStep is too big ,", Integer.valueOf(i2));
                return;
            }
            long j3 = j2 * 60000;
            gyc gycVar = new gyc();
            gycVar.e(j3 - 60000);
            gycVar.d(j3);
            gycVar.d(i2);
            LogUtil.a("Track_TreadmillStepPointData", "diffStep is ", Integer.valueOf(i2));
            this.k.add(gycVar);
            e(this.k);
        }
    }

    public void c(long j) {
        LogUtil.a("Track_TreadmillStepPointData", "stopStepPointData ");
        int i = this.g;
        if (i == 0 || (i == 3 && !this.c)) {
            this.g = 1;
        }
        int i2 = this.o;
        if (i2 > this.n) {
            c(j + 60000, i2);
        }
        HealthOpenSDK healthOpenSDK = this.b;
        if (healthOpenSDK == null) {
            LogUtil.h("Track_TreadmillStepPointData", "mHealthOpenSdk is null ");
        } else if (this.g == 1) {
            healthOpenSDK.b((IExecuteResult) new a());
        }
    }

    private void e(List<gyc> list) {
        HiHealthManager.d(BaseApplication.getContext()).insertHiHealthData(sqi.a(BaseApplication.getContext(), list), new HiDataOperateListener() { // from class: sqs.4
            @Override // com.huawei.hihealth.data.listener.HiDataOperateListener
            public void onResult(int i, Object obj) {
                if (i == 0) {
                    LogUtil.a("Track_TreadmillStepPointData", "saveTreadmillStepPointData success obj = ", obj, ",type = ", Integer.valueOf(i));
                } else {
                    LogUtil.b("Track_TreadmillStepPointData", "saveTreadmillStepPointData fail obj = ", obj, ",type = ", Integer.valueOf(i));
                }
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void b() {
        LogUtil.a("Track_TreadmillStepPointData", "insertData step is", Integer.valueOf(this.l), ", calories is ", Integer.valueOf(this.d), ", distance is", Integer.valueOf(this.e), " ,mStartStep is ", Integer.valueOf(this.j), ", mStartTime is ", Long.valueOf(this.m), " ,mTreadMillStopStep is ", Integer.valueOf(this.p), " , mTreadMillStep is ", Integer.valueOf(this.o));
        int i = this.o - this.p;
        if (i <= 0) {
            LogUtil.a("Track_TreadmillStepPointData", "MSG_START treadmillStep is <= 0");
            return;
        }
        int i2 = this.l;
        int i3 = this.j;
        int round = Math.round(i * this.t);
        int b = (int) (CalculateCaloriesUtils.b(round / 1000.0d, this.q) * 1000.0d);
        LogUtil.a("Track_TreadmillStepPointData", "addData step is", Integer.valueOf(i), " ,calories is ", Integer.valueOf(b), " , distance is", Integer.valueOf(round));
        if (c(i2 - i3, i)) {
            ArrayList arrayList = new ArrayList();
            arrayList.add(a(this.j + i, 40002, this.m));
            arrayList.add(a(this.f + b, 40003, this.m));
            arrayList.add(a(this.i + round, 40004, this.m));
            LogUtil.a("Track_TreadmillStepPointData", "isInsertData step is", Integer.valueOf(this.j + i), " ,calories is ", Integer.valueOf(this.f + b), " , distance is", Integer.valueOf(this.i + round));
            HiHealthManager.d(BaseApplication.getContext()).insertHiHealthData(HiHealthHelper.b(arrayList), new c());
        }
        this.j = 0;
        this.f = 0;
        this.i = 0;
    }

    private boolean c(int i, int i2) {
        int i3;
        if (i > i2) {
            LogUtil.a("Track_TreadmillStepPointData", "totalDiffStep(", Integer.valueOf(i), ") > treadmillDiffStep(", Integer.valueOf(i2), Constants.RIGHT_BRACKET_ONLY);
            return false;
        }
        if (i < 0) {
            LogUtil.a("Track_TreadmillStepPointData", "mStep is less than mStartStep(totalDiffStep < 0) , mStep is ", Integer.valueOf(this.l), " , mStartStep is ", Integer.valueOf(this.j));
            return false;
        }
        try {
            i3 = Integer.parseInt(new SimpleDateFormat(FitRunPlayAudio.PLAY_TYPE_D).format(Long.valueOf(System.currentTimeMillis())));
        } catch (NumberFormatException unused) {
            LogUtil.h("Track_TreadmillStepPointData", "isInsertData exception");
            i3 = 0;
        }
        if (i3 != this.h) {
            LogUtil.a("Track_TreadmillStepPointData", "not same day");
            return false;
        }
        if (!d(i2) || this.j < 0 || this.d < 0 || this.e < 0 || i < 0) {
            return false;
        }
        LogUtil.a("Track_TreadmillStepPointData", "isInsertData is true");
        return true;
    }

    private boolean d(int i) {
        long j = this.m;
        if (j <= 0) {
            LogUtil.a("Track_TreadmillStepPointData", "mStartTime is less 0 ,mStartTime is ", Long.valueOf(j));
            return false;
        }
        if (i <= 0) {
            LogUtil.a("Track_TreadmillStepPointData", "totalDiffStep is less 0 , totalDiffStep is ", Integer.valueOf(i));
            return false;
        }
        long currentTimeMillis = (System.currentTimeMillis() - this.m) / 60000;
        if (currentTimeMillis <= 0) {
            LogUtil.a("Track_TreadmillStepPointData", "time is less than 0");
            return false;
        }
        long j2 = i / currentTimeMillis;
        if (j2 <= 300.0d) {
            return true;
        }
        LogUtil.a("Track_TreadmillStepPointData", "stepRate is bigger than 300 , stepRate is ", Long.valueOf(j2));
        return false;
    }

    private void a() {
        UserInfomation userInfo = ((UserProfileMgrApi) Services.c("HWUserProfileMgr", UserProfileMgrApi.class)).getUserInfo();
        if (userInfo != null) {
            int heightOrDefaultValue = userInfo.getHeightOrDefaultValue();
            if (heightOrDefaultValue == 0) {
                this.t = 0.714f;
            } else {
                this.t = (heightOrDefaultValue * 0.42f) / 100.0f;
            }
            LogUtil.c("Track_TreadmillStepPointData", "The Stride is ", Float.valueOf(this.t));
            float weightOrDefaultValue = userInfo.getWeightOrDefaultValue();
            if (weightOrDefaultValue - 0.0f < 1.0E-6d) {
                this.q = 60.0f;
            } else {
                this.q = weightOrDefaultValue;
            }
            LogUtil.c("Track_TreadmillStepPointData", "User weight is ", Float.valueOf(this.q));
            return;
        }
        LogUtil.h("Track_TreadmillStepPointData", "accountInfo is null");
    }

    private HiHealthData d(int i, int i2, String str) {
        HiHealthData hiHealthData = new HiHealthData();
        hiHealthData.setType(i2);
        hiHealthData.setDeviceUuid(str);
        hiHealthData.setTimeInterval(TimeUtil.d(System.currentTimeMillis()), System.currentTimeMillis());
        hiHealthData.setValue(i);
        return hiHealthData;
    }

    private HiHealthData a(int i, int i2, long j) {
        HiHealthData d2 = d(i, i2, this.s);
        d2.setStartTime(j);
        return d2;
    }

    private boolean e() {
        int i;
        try {
            i = Integer.parseInt(new SimpleDateFormat(FitRunPlayAudio.PLAY_TYPE_D).format(new Date(System.currentTimeMillis() + 60000)));
        } catch (NumberFormatException unused) {
            LogUtil.h("Track_TreadmillStepPointData", "isNewDay exception");
            i = -1;
        }
        int i2 = this.h;
        return (i2 == -1 || i == -1 || i2 == i) ? false : true;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void ekH_(Message message) {
        if (message == null || !(message.obj instanceof Bundle)) {
            LogUtil.h("Track_TreadmillStepPointData", "getDetail, (msg == null) || !(msg.obj instanceof Bundle)");
            return;
        }
        Bundle bundle = (Bundle) message.obj;
        this.l = bundle.getInt("step");
        this.d = bundle.getInt("carior");
        this.e = bundle.getInt("distance");
    }

    static class c implements HiDataOperateListener {
        private c() {
        }

        @Override // com.huawei.hihealth.data.listener.HiDataOperateListener
        public void onResult(int i, Object obj) {
            LogUtil.a("Track_TreadmillStepPointData", "uploadStaticsToDB() onResult type = ", Integer.valueOf(i), " obj=", obj);
            if (i == 0) {
                LogUtil.a("Track_TreadmillStepPointData", "uploadStaticsToDB success ");
            } else {
                LogUtil.h("Track_TreadmillStepPointData", "uploadStaticsToDB failed message=", obj);
            }
        }
    }

    class a implements IExecuteResult {
        private a() {
        }

        @Override // com.huawei.hihealth.motion.IExecuteResult
        public void onSuccess(Object obj) {
            LogUtil.a("Track_TreadmillStepPointData", "get today steps success");
            if (obj == null || sqs.this.f17221a == null) {
                LogUtil.h("Track_TreadmillStepPointData", "MyExecuteResult object or handler is null");
                return;
            }
            if (obj instanceof Bundle) {
                Bundle bundle = (Bundle) obj;
                int i = bundle.getInt("step", 0);
                int i2 = bundle.getInt("carior", 0);
                int i3 = bundle.getInt("distance", 0);
                Bundle bundle2 = new Bundle();
                bundle2.putInt("step", i);
                bundle2.putInt("carior", i2);
                bundle2.putInt("distance", i3);
                Message message = new Message();
                if (sqs.this.g != 0) {
                    if (sqs.this.g != 2) {
                        if (sqs.this.g == 3) {
                            message.what = 1004;
                            message.obj = bundle2;
                            sqs.this.f17221a.sendMessage(message);
                            return;
                        } else {
                            message.what = 1002;
                            message.obj = bundle2;
                            sqs.this.f17221a.sendMessage(message);
                            return;
                        }
                    }
                    message.what = 1003;
                    message.obj = bundle2;
                    sqs.this.f17221a.sendMessage(message);
                    return;
                }
                message.what = 1001;
                message.obj = bundle2;
                sqs.this.f17221a.sendMessage(message);
            }
        }

        @Override // com.huawei.hihealth.motion.IExecuteResult
        public void onFailed(Object obj) {
            LogUtil.h("Track_TreadmillStepPointData", "get today steps onFailed");
        }

        @Override // com.huawei.hihealth.motion.IExecuteResult
        public void onServiceException(Object obj) {
            LogUtil.h("Track_TreadmillStepPointData", "get today steps onServiceException");
        }
    }
}
