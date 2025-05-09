package defpackage;

import android.content.Context;
import com.huawei.hms.mlsdk.asr.MLAsrConstants;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.operation.utils.Constants;
import com.huawei.pluginachievement.connectivity.https.HttpResCallBack;
import com.huawei.pluginachievement.impl.AchieveObserver;
import com.huawei.pluginachievement.manager.model.MultiLanguageRes;
import com.huawei.pluginachievement.manager.model.UserAchieveWrapper;
import java.io.File;
import java.util.HashMap;
import java.util.Locale;

/* loaded from: classes6.dex */
public class mff implements AchieveObserver, HttpResCallBack {
    private static volatile mff d;

    /* renamed from: a, reason: collision with root package name */
    private meh f14949a;
    private String b;
    private String c;
    private Context e;
    private String g;

    private mff(Context context) {
        this.e = context;
    }

    public static mff a(Context context) {
        if (d == null) {
            synchronized (mff.class) {
                if (d == null) {
                    d = new mff(context);
                }
            }
        }
        return d;
    }

    public boolean c() {
        return this.f14949a != null;
    }

    public void b(meh mehVar) {
        if (mehVar != null) {
            this.f14949a = mehVar;
            this.g = String.format(Locale.ENGLISH, "/data/data/%s/language_res/", this.e.getPackageName());
            this.f14949a.b(this);
        }
    }

    public void a() {
        String language = this.e.getResources().getConfiguration().locale.getLanguage();
        String country = this.e.getResources().getConfiguration().locale.getCountry();
        StringBuffer stringBuffer = new StringBuffer(8);
        stringBuffer.append(language).append("_").append(country);
        this.b = stringBuffer.toString();
        String b = mct.b(this.e, "last_language");
        this.c = b;
        LogUtil.a("PLGACHIEVE_LanguageRes", "downLoadRes->mCurrentLanguage:", this.b, " mLastLanguage:", b);
        HashMap hashMap = new HashMap(1);
        String str = "0";
        if (MLAsrConstants.LAN_ZH.equals(language)) {
            if (!this.c.startsWith(MLAsrConstants.LAN_ZH)) {
                hashMap.put("last_language", this.b);
                mct.b(this.e, hashMap);
                if (!this.c.startsWith("en")) {
                    e("languagesRes_extra.xml");
                }
            }
            str = mct.e(this.e, "zh_version", "0");
        } else if ("en".equals(language)) {
            if (!this.c.startsWith("en")) {
                hashMap.put("last_language", this.b);
                mct.b(this.e, hashMap);
                if (!this.c.startsWith(MLAsrConstants.LAN_ZH)) {
                    e("languagesRes_extra.xml");
                }
            }
            str = mct.e(this.e, "en_version", "0");
        } else if (!this.c.startsWith(language)) {
            hashMap.put("last_language", this.b);
            mct.b(this.e, hashMap);
        } else {
            str = mct.e(this.e, "extra_version", "0");
        }
        LogUtil.a("PLGACHIEVE_LanguageRes", "downLoadRes->version:", str);
    }

    private void e(String str) {
        String str2 = this.g + str;
        LogUtil.a("PLGACHIEVE_LanguageRes", "deleteFile->filePath:", str2);
        File file = new File(str2);
        if (file.exists()) {
            LogUtil.a("PLGACHIEVE_LanguageRes", "deleteFile->isDelete:", Boolean.valueOf(file.delete()));
        }
    }

    @Override // com.huawei.pluginachievement.impl.AchieveObserver
    public void onDataChanged(int i, UserAchieveWrapper userAchieveWrapper) {
        String str;
        Object[] objArr = new Object[4];
        objArr[0] = "onDataChanged error:";
        objArr[1] = Integer.valueOf(i);
        objArr[2] = "  userAchieveWrapper:";
        if (userAchieveWrapper == null) {
            str = Constants.NULL;
        } else {
            str = " ContentType:" + userAchieveWrapper.getContentType();
        }
        objArr[3] = str;
        LogUtil.a("PLGACHIEVE_LanguageRes", objArr);
        if (i == 200 && userAchieveWrapper != null && userAchieveWrapper.getContentType() == 6) {
            String resultCode = userAchieveWrapper.getResultCode();
            LogUtil.a("PLGACHIEVE_LanguageRes", "onDataChanged resultCode:", resultCode);
            if ("0".equals(resultCode)) {
                e(userAchieveWrapper.getLanguageRes());
            }
        }
    }

    private void e(MultiLanguageRes multiLanguageRes) {
        if (multiLanguageRes != null) {
            HashMap hashMap = new HashMap(2);
            String str = this.b;
            if (str != null && str.startsWith(MLAsrConstants.LAN_ZH)) {
                hashMap.put("zh_version", multiLanguageRes.getVersion());
            } else {
                String str2 = this.b;
                if (str2 != null && str2.startsWith("en")) {
                    hashMap.put("en_version", multiLanguageRes.getVersion());
                } else {
                    hashMap.put("extra_version", multiLanguageRes.getVersion());
                }
            }
            hashMap.put("last_language", multiLanguageRes.getVersion());
            mct.b(this.e, hashMap);
            c(multiLanguageRes.getUrl());
        }
    }

    private void c(String str) {
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append(this.g);
        String str2 = this.b;
        if (str2 != null && str2.startsWith(MLAsrConstants.LAN_ZH)) {
            stringBuffer.append("languagesRes_zh.xml");
        } else {
            String str3 = this.b;
            if (str3 != null && str3.startsWith("en")) {
                stringBuffer.append("languagesRes_en.xml");
            } else {
                stringBuffer.append("languagesRes_extra.xml");
            }
        }
        LogUtil.c("PLGACHIEVE_LanguageRes", "download->sbPath:", stringBuffer.toString());
        mdk.d(str, stringBuffer.toString(), this);
    }

    @Override // com.huawei.pluginachievement.connectivity.https.HttpResCallBack
    public void onFinished(int i, String str) {
        LogUtil.a("PLGACHIEVE_LanguageRes", "onFinished->resCode:", Integer.valueOf(i));
    }
}
