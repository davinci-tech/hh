package defpackage;

import com.huawei.haf.application.BaseApplication;
import com.huawei.haf.store.SharedPreferencesAdapter;
import com.huawei.utils.FoundationCommonUtil;
import health.compact.a.CommonUtil;

/* loaded from: classes.dex */
final class byw extends SharedPreferencesAdapter {
    byw() {
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    @Override // com.huawei.haf.store.SharedPreferencesAdapter, android.content.SharedPreferences
    public String getString(String str, String str2) {
        char c;
        String androidId;
        str.hashCode();
        switch (str.hashCode()) {
            case -1159299126:
                if (str.equals("AndroidId")) {
                    c = 0;
                    break;
                }
                c = 65535;
                break;
            case 974986840:
                if (str.equals("PhoneUdid")) {
                    c = 1;
                    break;
                }
                c = 65535;
                break;
            case 1173835313:
                if (str.equals("DeviceId")) {
                    c = 2;
                    break;
                }
                c = 65535;
                break;
            case 1173835633:
                if (str.equals("DeviceSn")) {
                    c = 3;
                    break;
                }
                c = 65535;
                break;
            default:
                c = 65535;
                break;
        }
        if (c == 0) {
            androidId = FoundationCommonUtil.getAndroidId(BaseApplication.e());
        } else if (c == 1 || c == 2) {
            androidId = CommonUtil.a(BaseApplication.e(), false);
        } else if (c == 3) {
            androidId = CommonUtil.g();
        } else {
            androidId = super.getString(str, str2);
        }
        return androidId != null ? androidId : str2;
    }

    @Override // com.huawei.haf.store.SharedPreferencesAdapter, android.content.SharedPreferences
    public boolean getBoolean(String str, boolean z) {
        str.hashCode();
        if (str.equals("SystemInfoAuthorized")) {
            return FoundationCommonUtil.isSystemInfoAuthorized(BaseApplication.e());
        }
        return super.getBoolean(str, z);
    }
}
