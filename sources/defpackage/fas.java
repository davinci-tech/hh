package defpackage;

import android.webkit.JavascriptInterface;
import com.huawei.haf.common.exception.ExceptionUtils;
import com.huawei.health.h5pro.jsbridge.base.JsBaseModule;
import com.huawei.hihealth.HiHealthData;
import com.huawei.hihealth.HiSportStatDataAggregateOption;
import com.huawei.hihealth.api.HiHealthManager;
import com.huawei.hihealth.data.listener.HiAggregateListener;
import com.huawei.operation.utils.Constants;
import health.compact.a.LogUtil;
import health.compact.a.SharedPreferenceManager;
import java.math.BigDecimal;
import java.util.List;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes7.dex */
public class fas extends JsBaseModule {
    private double c = 0.0d;
    private double e = 0.0d;

    /* renamed from: a, reason: collision with root package name */
    private double f12409a = 0.0d;
    private int[] d = {42053, 42054, 42103, 42104, 42153, 42154};
    private String[] b = {"WALK_CALORIES_SUM", "WALK_DURATION_SUM", "RUN_CALORIES_SUM", "RUN_DURATION_SUM", "RIDE_CALORIES_SUM", "RIDE_DURATION_SUM"};

    @JavascriptInterface
    public void getSportsEnergy(final long j) {
        LogUtil.c(this.TAG, "==getSportsEnergy==");
        HiSportStatDataAggregateOption hiSportStatDataAggregateOption = new HiSportStatDataAggregateOption();
        hiSportStatDataAggregateOption.setGroupUnitType(6);
        hiSportStatDataAggregateOption.setReadType(0);
        hiSportStatDataAggregateOption.setConstantsKey(this.b);
        hiSportStatDataAggregateOption.setType(this.d);
        hiSportStatDataAggregateOption.setAggregateType(1);
        hiSportStatDataAggregateOption.setTimeRange(0L, System.currentTimeMillis());
        hiSportStatDataAggregateOption.setCount(1);
        HiHealthManager.d(this.mContext).aggregateSportStatData(hiSportStatDataAggregateOption, new HiAggregateListener() { // from class: fas.4
            @Override // com.huawei.hihealth.data.listener.HiAggregateListener
            public void onResultIntent(int i, List<HiHealthData> list, int i2, int i3) {
            }

            @Override // com.huawei.hihealth.data.listener.HiAggregateListener
            public void onResult(List<HiHealthData> list, int i, int i2) {
                if (list != null && list.size() > 0) {
                    HiHealthData hiHealthData = list.get(0);
                    double d = hiHealthData.getDouble("RUN_DURATION_SUM");
                    double d2 = hiHealthData.getDouble("RUN_CALORIES_SUM");
                    if (d != 0.0d && d2 != 0.0d) {
                        fas.this.c = ((d2 / 1000.0d) * 10.0d) / (d / 60000.0d);
                    }
                    double d3 = hiHealthData.getDouble("WALK_DURATION_SUM");
                    double d4 = hiHealthData.getDouble("WALK_CALORIES_SUM");
                    if (d3 != 0.0d && d4 != 0.0d) {
                        fas.this.e = ((d4 / 1000.0d) * 10.0d) / (d3 / 60000.0d);
                    }
                    double d5 = hiHealthData.getDouble("RIDE_DURATION_SUM");
                    double d6 = hiHealthData.getDouble("RIDE_CALORIES_SUM");
                    if (d5 != 0.0d && d6 != 0.0d) {
                        fas.this.f12409a = ((d6 / 1000.0d) * 10.0d) / (d5 / 60000.0d);
                    }
                }
                JSONObject jSONObject = new JSONObject();
                try {
                    fas fasVar = fas.this;
                    jSONObject.put(Constants.RIDE, fasVar.d(fasVar.f12409a));
                    fas fasVar2 = fas.this;
                    jSONObject.put("run", fasVar2.d(fasVar2.c));
                    fas fasVar3 = fas.this;
                    jSONObject.put("walk", fasVar3.d(fasVar3.e));
                } catch (JSONException e) {
                    LogUtil.e(fas.this.TAG, "getSportsEnergy JSONException: ", ExceptionUtils.d(e));
                }
                LogUtil.c(fas.this.TAG, "getSportsEnergy jsonResult:", jSONObject.toString());
                fas.this.onSuccessCallback(j, jSONObject.toString());
            }
        });
    }

    @JavascriptInterface
    public void getImgBase64Encode(long j) {
        String str;
        LogUtil.c(this.TAG, "==getImgBase64Encode==");
        String auR_ = fba.auR_(nrf.cHQ_(fba.d));
        if (auR_ == null) {
            LogUtil.e(this.TAG, "getImgBase64Encode encodeStr is null");
            str = "";
        } else {
            LogUtil.c(this.TAG, "getImgBase64Encode success");
            str = "data:image/jpeg;base64," + auR_;
        }
        onSuccessCallback(j, str);
    }

    @JavascriptInterface
    public void isTrialUseFinish(long j) {
        LogUtil.c(this.TAG, "==isTrialUseFinish==");
        onSuccessCallback(j, Boolean.valueOf(SharedPreferenceManager.a("nutritionTableOcr", "isTrialUseFinish", false)));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public double d(double d) {
        return new BigDecimal(d).setScale(2, 4).doubleValue();
    }
}
