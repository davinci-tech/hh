package defpackage;

import a.a.a.a.a.a;
import android.os.Bundle;
import android.text.TextUtils;
import com.hihonor.assistant.cardmgrsdk.CardMgrSdkConst;
import com.hihonor.assistant.cardmgrsdk.model.YOYOWidgetMsg;
import com.huawei.openalliance.ad.constant.JsbMapKeyNames;
import java.util.Optional;

/* loaded from: classes2.dex */
public class un {
    public Optional<YOYOWidgetMsg> cS_(Bundle bundle) {
        a.b("YOYOWidgetMsgParser", "parse in");
        if (bundle == null) {
            a.c("YOYOWidgetMsgParser", "bundle is empty");
            return Optional.empty();
        }
        String string = bundle.getString("host");
        if (TextUtils.isEmpty(string)) {
            a.c("YOYOWidgetMsgParser", "host is empty");
            return Optional.empty();
        }
        if (!"HonorYOYO".equals(string)) {
            a.c("YOYOWidgetMsgParser", "msg not from YOYO");
            return Optional.empty();
        }
        String string2 = bundle.getString(CardMgrSdkConst.CardInfoDesc.PARAMS_BUSINESS, "");
        String string3 = bundle.getString("scene", "");
        String string4 = bundle.getString("type", "");
        String string5 = bundle.getString(JsbMapKeyNames.H5_EXTRAS, "");
        YOYOWidgetMsg yOYOWidgetMsg = new YOYOWidgetMsg();
        yOYOWidgetMsg.setBusiness(string2);
        yOYOWidgetMsg.setScene(string3);
        yOYOWidgetMsg.setType(string4);
        yOYOWidgetMsg.setExtras(string5);
        return Optional.of(yOYOWidgetMsg);
    }
}
