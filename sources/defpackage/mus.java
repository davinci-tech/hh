package defpackage;

import com.hihonor.assistant.cardmgrsdk.CardMgrSdkConst;
import com.huawei.health.R;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.operation.beans.TitleBean;
import com.huawei.watchface.mvp.model.latona.provider.WatchFaceProvider;
import java.io.File;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/* loaded from: classes6.dex */
public class mus {

    /* renamed from: a, reason: collision with root package name */
    public static final String f15185a;
    public static final String b;
    public static final Map<Integer, Integer> c;
    public static final String d = jcu.f13746a;
    public static final String e;
    public static final String f;
    public static final String g;
    public static final String h;
    public static final String i;
    public static final String j;
    public static final String k;
    public static final String l;
    public static final String m;
    public static final String o;

    static {
        String absolutePath = BaseApplication.getContext().getFilesDir().getAbsolutePath();
        g = absolutePath;
        f15185a = absolutePath + File.separator + "plugins" + File.separator + TitleBean.RIGHT_BTN_TYPE_SHARE + File.separator + WatchFaceProvider.BACKGROUND_LABEL;
        b = absolutePath + File.separator + "plugins" + File.separator + TitleBean.RIGHT_BTN_TYPE_SHARE + File.separator + CardMgrSdkConst.KEY_RECOMMEND;
        l = absolutePath + File.separator + "plugins" + File.separator + TitleBean.RIGHT_BTN_TYPE_SHARE + File.separator + "watermark";
        o = absolutePath + File.separator + "plugins" + File.separator + TitleBean.RIGHT_BTN_TYPE_SHARE + File.separator + "sticker";
        String str = absolutePath + File.separator + "plugins" + File.separator + TitleBean.RIGHT_BTN_TYPE_SHARE + File.separator + "json";
        j = str;
        h = str + File.separator + "share_index_download.json";
        e = str + File.separator + "background_download.json";
        k = str + File.separator + "themebackground_download.json";
        i = str + File.separator + "recommend_download.json";
        m = str + File.separator + "watermark_download.json";
        f = str + File.separator + "sticker_download.json";
        c = Collections.unmodifiableMap(new HashMap<Integer, Integer>() { // from class: mus.4
            private static final long serialVersionUID = -7918315086085509155L;

            {
                put(Integer.valueOf(R.drawable.share_geometry_1), 8);
                put(Integer.valueOf(R.drawable.share_geometry_2), 9);
                put(Integer.valueOf(R.drawable.share_geometry_3), 10);
                put(Integer.valueOf(R.drawable.share_run_default_background), 2);
                put(Integer.valueOf(R.drawable.share_cycling_default_background), 11);
                put(Integer.valueOf(R.drawable.basketball_background_default), 20);
                put(Integer.valueOf(R.drawable.climb_hill_background_default), 13);
                put(Integer.valueOf(R.drawable.swim_background_default), 21);
                put(Integer.valueOf(R.drawable.fitness_background_1), 22);
            }
        });
    }
}
