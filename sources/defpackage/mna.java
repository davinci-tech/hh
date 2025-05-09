package defpackage;

import com.huawei.pluginfitnessadvice.ChoreographedMultiActions;
import com.huawei.pluginfitnessadvice.ChoreographedSingleAction;
import com.huawei.pluginfitnessadvice.cloudmodelparse.CloudDataParseBase;
import java.util.List;
import org.json.JSONObject;

/* loaded from: classes6.dex */
public class mna extends CloudDataParseBase<ChoreographedMultiActions> {
    public mna(int i) {
        super(i);
    }

    @Override // com.huawei.pluginfitnessadvice.cloudmodelparse.CloudDataParse
    /* renamed from: a, reason: merged with bridge method [inline-methods] */
    public ChoreographedMultiActions parse(String str, JSONObject jSONObject) {
        List<ChoreographedSingleAction> parse = mnc.e(this.mType).parse(str, jSONObject.optJSONArray("actionList"));
        ChoreographedMultiActions choreographedMultiActions = new ChoreographedMultiActions();
        choreographedMultiActions.setActionList(parse);
        choreographedMultiActions.setRepeatTimes(jSONObject.optInt("repeatTimes", 1));
        return choreographedMultiActions;
    }
}
