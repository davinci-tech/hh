package defpackage;

import com.huawei.pluginfitnessadvice.FitWorkout;
import org.json.JSONObject;

/* loaded from: classes6.dex */
public class mnl extends mng {
    @Override // defpackage.mng, com.huawei.pluginfitnessadvice.cloudmodelparse.CloudDataParse
    /* renamed from: d */
    public FitWorkout parse(String str, JSONObject jSONObject) {
        FitWorkout parse = super.parse(str, jSONObject);
        parse.setMusicRunFlag(jSONObject.optInt("musicRunFlag", -1));
        return parse;
    }
}
