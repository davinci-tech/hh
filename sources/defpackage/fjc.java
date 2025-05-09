package defpackage;

import android.content.Context;
import com.huawei.health.suggestion.customizecourse.manager.model.CourseConfig;
import com.huawei.health.suggestion.customizecourse.manager.model.CourseSetConfig;
import com.huawei.hwlogsmodel.LogUtil;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/* loaded from: classes4.dex */
public class fjc {
    private static final Object c = new Object();
    private static volatile fjc e;

    /* renamed from: a, reason: collision with root package name */
    private CourseSetConfig f12538a;
    private Map<Integer, CourseConfig> b = new HashMap();
    private Context d;

    private fjc(Context context) {
        long currentTimeMillis = System.currentTimeMillis();
        this.d = context;
        a();
        LogUtil.a("Suggestion_CoursesConfigManager_TIME", "init cost:", Long.valueOf(System.currentTimeMillis() - currentTimeMillis));
    }

    public static final fjc c(Context context) {
        fjc fjcVar;
        LogUtil.a("Suggestion_CoursesConfigManager", "getInstance()");
        synchronized (c) {
            if (e == null) {
                e = new fjc(context.getApplicationContext());
            }
            fjcVar = e;
        }
        return fjcVar;
    }

    public CourseConfig e(int i) {
        return this.b.get(Integer.valueOf(i));
    }

    private void a() {
        e();
        d();
    }

    private void d() {
        CourseSetConfig courseSetConfig = this.f12538a;
        if (courseSetConfig == null || koq.b(courseSetConfig.getCourseConfigs())) {
            LogUtil.h("Suggestion_CoursesConfigManager", "loadConfigToTypeMap () course config size error");
            return;
        }
        this.b.clear();
        for (CourseConfig courseConfig : this.f12538a.getCourseConfigs()) {
            if (courseConfig != null) {
                this.b.put(Integer.valueOf(courseConfig.getCourseType()), courseConfig);
            }
        }
        LogUtil.a("Suggestion_CoursesConfigManager", "loadConfigToTypeMap() mCourseConfigTypeMap size：", Integer.valueOf(this.b.size()));
    }

    private void e() {
        Context context = this.d;
        if (context == null) {
            LogUtil.h("Suggestion_CoursesConfigManager", "loadDefaultCourseSetConfig failed with null context");
            return;
        }
        if (this.f12538a == null) {
            try {
                this.f12538a = (CourseSetConfig) ixu.d(context.getAssets().open("CustomCourseSetConfig.json"), CourseSetConfig.class);
            } catch (IOException unused) {
                LogUtil.b("Suggestion_CoursesConfigManager", "loadDefaultCourseSetConfig failed, ");
            }
            Object[] objArr = new Object[2];
            objArr[0] = "mDefaultConfig : ";
            CourseSetConfig courseSetConfig = this.f12538a;
            objArr[1] = courseSetConfig == null ? "" : courseSetConfig.getVersion();
            LogUtil.a("Suggestion_CoursesConfigManager", objArr);
        }
    }
}
