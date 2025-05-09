package defpackage;

import android.os.Bundle;
import com.careforeyou.library.BIAWorker;
import com.careforeyou.library.bean.WeightEntity;
import com.careforeyou.library.intface.BIACallback;
import com.huawei.health.device.callback.IHealthDeviceCallback;
import com.huawei.health.device.fatscale.multiusers.MultiUsersManager;
import com.huawei.health.device.model.HealthDevice;
import com.huawei.health.device.open.BleMeasureController;
import com.huawei.hihealth.util.HiJsonUtil;
import com.huawei.hwbasemgr.ResponseCallback;
import com.huawei.hwlogsmodel.LogUtil;
import health.compact.a.HiCommonUtil;
import health.compact.a.UnitUtil;
import health.compact.a.hwlogsmodel.ReleaseLogUtil;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.concurrent.CountDownLatch;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes3.dex */
public class cfj extends BleMeasureController {

    /* renamed from: a, reason: collision with root package name */
    private IHealthDeviceCallback f680a;
    private BIAWorker d;
    private HealthDevice e;
    private float h = 173.0f;
    private int c = 29;
    private int f = 1;
    private boolean i = false;
    private boolean g = false;
    BIACallback b = new BIACallback() { // from class: cfj.5
        @Override // com.careforeyou.library.intface.BIACallback
        public void onResult(int i, JSONObject jSONObject) {
            ReleaseLogUtil.e("R_PluginDevice_ChipSeaMeasureController", "onResult: ", Integer.valueOf(i));
            if (i != 1 || cfj.this.f680a == null) {
                return;
            }
            cfj.this.f680a.onDataChanged(cfj.this.e, cfj.this.e(jSONObject));
            if (cfj.this.d == null || cfj.this.g) {
                return;
            }
            cfj.this.d.b();
            cfj.this.g = true;
        }

        @Override // com.careforeyou.library.intface.BIACallback
        public void onCanSyncUnit() {
            LogUtil.a("PluginDevice_ChipSeaMeasureController", "onCanSyncUnit");
        }

        @Override // com.careforeyou.library.intface.BIACallback
        public void onSyncAllUserInfo() {
            LogUtil.a("PluginDevice_ChipSeaMeasureController", "onSyncAllUserInfo");
            cfj.this.d.d(cfj.this.e());
        }

        @Override // com.careforeyou.library.intface.BIACallback
        public void onState(int i, String str) {
            ReleaseLogUtil.e("R_PluginDevice_ChipSeaMeasureController", "BIAWorker State: ", Integer.valueOf(i), "msg: ", str);
        }

        @Override // com.careforeyou.library.intface.BIACallback
        public void onUserMatchResult(JSONObject jSONObject, List<Integer> list) {
            LogUtil.a("PluginDevice_ChipSeaMeasureController", "onUserMatchResult");
        }
    };

    private int a(int i) {
        return i == 0 ? 0 : 1;
    }

    public cfj() {
        LogUtil.a("PluginDevice_ChipSeaMeasureController", "ChipSeaMeasureController constructor");
        if (this.d == null) {
            this.d = nb.d(cpp.a());
        }
    }

    @Override // com.huawei.health.device.open.MeasureController
    public boolean prepare(HealthDevice healthDevice, IHealthDeviceCallback iHealthDeviceCallback, Bundle bundle) {
        LogUtil.a("PluginDevice_ChipSeaMeasureController", "ChipSeaMeasureController prepare");
        if (!(healthDevice instanceof cxh) || iHealthDeviceCallback == null) {
            return false;
        }
        final CountDownLatch countDownLatch = new CountDownLatch(1);
        MultiUsersManager.INSTANCE.getCurrentUserForUserInfo(new ResponseCallback() { // from class: cfp
            @Override // com.huawei.hwbasemgr.ResponseCallback
            public final void onResponse(int i, Object obj) {
                cfj.this.a(countDownLatch, i, (cfi) obj);
            }
        });
        try {
            countDownLatch.await();
        } catch (InterruptedException e) {
            LogUtil.b("PluginDevice_ChipSeaMeasureController", "ChipSeaMeasureController prepare:", e.getMessage());
        }
        this.e = healthDevice;
        this.f680a = iHealthDeviceCallback;
        this.h = dks.d((int) this.h);
        this.f = dks.b(this.f);
        this.c = dks.a(this.c);
        return true;
    }

    /* synthetic */ void a(CountDownLatch countDownLatch, int i, cfi cfiVar) {
        ReleaseLogUtil.e("PluginDevice_ChipSeaMeasureController", "MultiUsersManager.INSTANCE.getCurrentUser code:", Integer.valueOf(i));
        if (i == 0 && cfiVar != null) {
            this.h = cfiVar.d();
            this.f = cfiVar.c();
            this.c = cfiVar.a();
        }
        countDownLatch.countDown();
    }

    @Override // com.huawei.health.device.open.MeasureController
    public boolean start() {
        LogUtil.a("PluginDevice_ChipSeaMeasureController", "ChipSeaMeasureController start");
        if (this.d != null && !this.i && this.e != null) {
            this.d.b(this.e.getAddress(), c(), null, this.b);
            this.i = true;
            dew.b(this.e.getAddress(), 1);
        }
        return true;
    }

    @Override // com.huawei.health.device.open.MeasureController
    public void ending() {
        LogUtil.a("PluginDevice_ChipSeaMeasureController", "ChipSeaMeasureController ending");
        BIAWorker bIAWorker = this.d;
        if (bIAWorker != null && !this.g && this.e != null) {
            bIAWorker.b();
            this.g = true;
            dew.b(this.e.getAddress(), 0);
        }
        cleanup();
    }

    @Override // com.huawei.health.device.open.MeasureController
    public void cleanup() {
        LogUtil.a("PluginDevice_ChipSeaMeasureController", "ChipSeaMeasureController cleanup");
        this.f680a = null;
        this.d = null;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public JSONArray e() {
        JSONArray jSONArray = new JSONArray();
        try {
            jSONArray.put(0, c());
        } catch (JSONException e) {
            LogUtil.b("PluginDevice_ChipSeaMeasureController", "getRoleInfos JSONException:", e.getMessage());
        }
        return jSONArray;
    }

    private JSONObject c() {
        JSONObject jSONObject = new JSONObject();
        try {
            boolean z = true;
            jSONObject.put("id", 1);
            jSONObject.put("height", this.h);
            jSONObject.put("sex", a(this.f));
            jSONObject.put("age", this.c);
            jSONObject.put("weight", 0);
            Object[] objArr = new Object[4];
            objArr[0] = "ChipSeaMeasureController info:";
            objArr[1] = Boolean.valueOf(this.h > 0.0f);
            objArr[2] = Boolean.valueOf(this.f == 1);
            if (this.c != 29) {
                z = false;
            }
            objArr[3] = Boolean.valueOf(z);
            LogUtil.a("PluginDevice_ChipSeaMeasureController", objArr);
        } catch (JSONException e) {
            LogUtil.b("PluginDevice_ChipSeaMeasureController", "getRoleInfo JSONException:", e.getMessage());
        }
        return jSONObject;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public ckm e(JSONObject jSONObject) {
        return d((WeightEntity) HiJsonUtil.e(jSONObject.toString(), WeightEntity.class));
    }

    private ckm d(WeightEntity weightEntity) {
        long time;
        ckm ckmVar = new ckm();
        try {
            time = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.ENGLISH).parse(weightEntity.getWeight_time()).getTime();
        } catch (ParseException unused) {
            LogUtil.b("PluginDevice_ChipSeaMeasureController", "Illegal time format:", weightEntity.getWeight_time());
            time = new Date().getTime();
        }
        ckmVar.setStartTime(time);
        ckmVar.setEndTime(time);
        ckmVar.setWeight(weightEntity.getWeight());
        ckmVar.setBodyFatRat((float) UnitUtil.a(weightEntity.getAxunge(), 1));
        ckmVar.b(weightEntity.getBmi());
        ckmVar.c(weightEntity.getViscera());
        ckmVar.a(weightEntity.getMetabolism());
        ckmVar.h(UnitUtil.a(weightEntity.getWater(), 1));
        ckmVar.d(UnitUtil.a(weightEntity.getBone(), 1));
        ckmVar.i(UnitUtil.a(HiCommonUtil.b(weightEntity.getWeight(), weightEntity.getAxunge(), (weightEntity.getWeight() * weightEntity.getWater()) / 100.0f, weightEntity.getBone()), 1));
        ckmVar.e(weightEntity.getBody_age());
        ckmVar.g(weightEntity.getHeart());
        ckmVar.e(false);
        ckmVar.d(true);
        return ckmVar;
    }
}
