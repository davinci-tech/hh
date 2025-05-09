package defpackage;

import android.content.Context;
import android.text.TextUtils;
import com.huawei.haf.bundle.AppBundlePluginProxy;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.pluginsport.multilingualaudio.MultilingualAudioApi;
import health.compact.a.SharedPreferenceManager;
import java.io.File;
import java.util.Arrays;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;

/* loaded from: classes6.dex */
public class mxb extends AppBundlePluginProxy<MultilingualAudioApi> implements MultilingualAudioApi {
    private static final List<Locale> d = Arrays.asList(Locale.GERMAN, Locale.ITALIAN, Locale.FRENCH, Locale.JAPAN);
    private static final List<String> e = Arrays.asList("pl", "es", "es-US", "ar", "tr", "ru", "ms", "th");

    /* renamed from: a, reason: collision with root package name */
    private MultilingualAudioApi f15228a;
    private String b;
    private mxh c;

    static class a {
        private static final mxb c = new mxb();
    }

    private mxb() {
        super("MultilingualAudioProxy", "MultilingualAudio", "com.huawei.health.multilingualaudio.urlgenerator.MultilingualAudioUrlGenerator");
        this.b = "default";
        LogUtil.a("MultilingualAudioProxy", "MultilingualAudioProxy()");
        this.f15228a = createPluginApi();
    }

    public static mxb a() {
        return a.c;
    }

    public boolean c(Context context) {
        if (context == null) {
            return false;
        }
        return d(c());
    }

    public boolean b(Context context, String str) {
        String c = c();
        LogUtil.a("MultilingualAudioProxy", "languageName= ", c);
        if (d(c) && a(c, str)) {
            return mxc.d(c, str);
        }
        return false;
    }

    public static String c() {
        String e2 = mtj.e(null);
        if (!TextUtils.isEmpty(e2)) {
            return e2;
        }
        LogUtil.a("MultilingualAudioProxy", "getSupportLanguageName is null");
        return BaseApplication.getContext().getResources().getConfiguration().locale.getLanguage();
    }

    private boolean a(String str, String str2) {
        return new File(mxc.e(str, str2)).exists();
    }

    public boolean d(String str) {
        Iterator<Locale> it = d.iterator();
        while (it.hasNext()) {
            if (it.next().getLanguage().equals(str)) {
                return true;
            }
        }
        Iterator<String> it2 = e.iterator();
        while (it2.hasNext()) {
            if (it2.next().equals(str)) {
                return true;
            }
        }
        return false;
    }

    @Override // com.huawei.pluginsport.multilingualaudio.MultilingualAudioApi
    public void init(mxh mxhVar) {
        this.c = mxhVar;
        if (this.f15228a == null) {
            LogUtil.h("MultilingualAudioProxy", "api is null");
            this.f15228a = createPluginApi();
        } else {
            LogUtil.a("MultilingualAudioProxy", "init api");
            this.f15228a.init(mxhVar);
        }
    }

    @Override // com.huawei.pluginsport.multilingualaudio.MultilingualAudioApi
    public List<String> getScenarioAudioPaths(String str, mwz mwzVar) {
        mxh mxhVar;
        Object[] objArr = new Object[4];
        objArr[0] = "audioId ";
        objArr[1] = str;
        objArr[2] = " Api = null is ";
        objArr[3] = Boolean.valueOf(this.f15228a == null);
        LogUtil.a("MultilingualAudioProxy", objArr);
        MultilingualAudioApi multilingualAudioApi = this.f15228a;
        if (multilingualAudioApi == null) {
            this.f15228a = createPluginApi();
            return Collections.emptyList();
        }
        if (!multilingualAudioApi.isInitSuccess() && (mxhVar = this.c) != null) {
            this.f15228a.init(mxhVar);
        }
        return this.f15228a.getScenarioAudioPaths(str, mwzVar);
    }

    @Override // com.huawei.pluginsport.multilingualaudio.MultilingualAudioApi
    public void release() {
        MultilingualAudioApi multilingualAudioApi = this.f15228a;
        if (multilingualAudioApi == null) {
            return;
        }
        multilingualAudioApi.release();
    }

    @Override // com.huawei.haf.bundle.AppBundlePluginProxy
    public boolean isPluginAvaiable() {
        return this.f15228a != null;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.huawei.haf.bundle.AppBundlePluginProxy
    /* renamed from: c, reason: merged with bridge method [inline-methods] */
    public void initialize(MultilingualAudioApi multilingualAudioApi) {
        LogUtil.a("MultilingualAudioProxy", "initialize() pluginApi: ", multilingualAudioApi);
        this.f15228a = multilingualAudioApi;
    }

    public void d(String str, String str2) {
        if (TextUtils.isEmpty(str) || TextUtils.isEmpty(str2)) {
            LogUtil.h("MultilingualAudioProxy", "language or speaker is null ");
            return;
        }
        this.b = str2;
        SharedPreferenceManager.c(String.valueOf(101010), "multilingual_key_speaker" + str, str2);
    }

    public String b(String str) {
        if ("default".equals(this.b)) {
            this.b = SharedPreferenceManager.e(String.valueOf(101010), "multilingual_key_speaker" + str, "");
        }
        LogUtil.a("MultilingualAudioProxy", "mSpeaker = ", this.b);
        return this.b;
    }

    public boolean e() {
        return ("ar".equals(c()) || Locale.JAPAN.getLanguage().equals(c())) ? false : true;
    }
}
