package defpackage;

import com.huawei.pluginfitnessadvice.ChoreographedSingleAction;
import com.huawei.pluginfitnessadvice.TargetConfig;
import com.huawei.pluginfitnessadvice.cloudmodelparse.CloudDataParseBase;
import org.json.JSONArray;
import org.json.JSONObject;

/* loaded from: classes6.dex */
public class mnb extends CloudDataParseBase<ChoreographedSingleAction> {
    public mnb(int i) {
        super(i);
    }

    @Override // com.huawei.pluginfitnessadvice.cloudmodelparse.CloudDataParse
    /* renamed from: d, reason: merged with bridge method [inline-methods] */
    public ChoreographedSingleAction parse(String str, JSONObject jSONObject) {
        ChoreographedSingleAction choreographedSingleAction = new ChoreographedSingleAction();
        choreographedSingleAction.setAction(mnc.d(this.mType).parse(str, jSONObject));
        choreographedSingleAction.setTargetConfig((TargetConfig) moj.e(jSONObject.optString("target"), TargetConfig.class));
        choreographedSingleAction.setIntensityConfig((TargetConfig) moj.e(jSONObject.optString("strength"), TargetConfig.class));
        JSONArray optJSONArray = jSONObject.optJSONArray("playAudios");
        if (optJSONArray != null) {
            choreographedSingleAction.setCommentaryGap(mnh.c(optJSONArray, 4));
        }
        return choreographedSingleAction;
    }
}
