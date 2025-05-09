package defpackage;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.ui.main.R$string;
import java.util.HashMap;
import java.util.Map;

/* loaded from: classes9.dex */
public final class rhg {
    private static final Map<Integer, Integer> b;

    /* renamed from: a, reason: collision with root package name */
    private Drawable f16760a;
    private String c;
    private String d;
    private int e;
    private int f;
    private boolean g;
    private int h;
    private boolean i;
    private boolean j;

    static {
        HashMap hashMap = new HashMap();
        b = hashMap;
        hashMap.put(10001, Integer.valueOf(R$string.IDS_hw_show_main_home_page_bloodsugar));
        hashMap.put(10002, Integer.valueOf(R$string.IDS_hw_show_main_home_page_bloodpressure));
        hashMap.put(101001, Integer.valueOf(R$string.IDS_hw_show_main_permission_user_profile_basic_info));
        hashMap.put(101002, Integer.valueOf(R$string.IDS_hw_show_main_permission_user_profile_basic_feature));
        hashMap.put(47201, Integer.valueOf(R$string.IDS_hw_show_main_permission_blood_oxygen));
        hashMap.put(47202, Integer.valueOf(R$string.IDS_hw_show_main_permission_blood_oxygen));
        hashMap.put(47203, Integer.valueOf(R$string.IDS_hw_show_main_permission_blood_oxygen));
        hashMap.put(47204, Integer.valueOf(R$string.IDS_hw_show_main_permission_blood_oxygen));
        hashMap.put(101201, Integer.valueOf(R$string.IDS_hw_show_main_permission_device_info));
        hashMap.put(101003, Integer.valueOf(R$string.IDS_hw_show_main_permission_realtime_sport_data));
        hashMap.put(101202, Integer.valueOf(R$string.IDS_hw_show_main_permission_device_data));
        hashMap.put(101203, Integer.valueOf(R$string.IDS_hw_show_main_permission_device_basic_control));
        hashMap.put(101204, Integer.valueOf(R$string.IDS_hw_show_main_permission_device_advanced_control));
        hashMap.put(40002, Integer.valueOf(R$string.IDS_hw_show_main_permission_steps));
        hashMap.put(40004, Integer.valueOf(R$string.IDS_hw_show_main_permission_distance));
        hashMap.put(40003, Integer.valueOf(R$string.IDS_hw_show_main_permission_calorie));
        hashMap.put(30005, Integer.valueOf(R$string.IDS_hw_show_main_permission_track_record));
        hashMap.put(30006, Integer.valueOf(R$string.IDS_hw_show_main_permission_track_record));
        hashMap.put(30007, Integer.valueOf(R$string.IDS_hw_show_main_permission_track_record));
        hashMap.put(10008, Integer.valueOf(R$string.IDS_hw_show_main_permission_heart_statistic));
        hashMap.put(50001, Integer.valueOf(R$string.IDS_hw_show_main_permission_heart_realtime_measure));
        hashMap.put(10006, Integer.valueOf(R$string.IDS_hw_show_main_permission_weight));
        hashMap.put(10007, Integer.valueOf(R$string.IDS_hw_show_main_permission_sleep));
        hashMap.put(44000, Integer.valueOf(R$string.IDS_hw_show_main_permission_sleep));
        hashMap.put(47101, Integer.valueOf(R$string.IDS_hw_show_main_permission_intensity));
        hashMap.put(31001, Integer.valueOf(R$string.IDS_permission_heart_health));
        hashMap.put(101000, Integer.valueOf(R$string.IDS_hw_show_main_permission_app_sync));
        hashMap.put(10010, Integer.valueOf(R$string.IDS_hw_show_main_permission_menstruation));
        hashMap.put(2104, Integer.valueOf(R$string.IDS_settings_health_temperature));
        hashMap.put(2034, Integer.valueOf(R$string.IDS_settings_one_level_menu_settings_item_text_id14));
    }

    public rhg(int i, Drawable drawable, String str, boolean z, boolean z2) {
        this.f = i;
        this.f16760a = drawable;
        this.d = str;
        this.i = z;
        this.j = z2;
    }

    public rhg(int i, boolean z, boolean z2, boolean z3) {
        this.f = i;
        this.g = z;
        this.j = z3;
    }

    public rhg(int i, boolean z, boolean z2) {
        this.f = i;
        this.g = z;
        this.j = z2;
        c(BaseApplication.getContext().getString(R$string.IDS_hw_show_main_health_page_healthdata_bloodpresure_select));
    }

    public rhg(int i, boolean z, int i2, int i3, boolean z2) {
        this.f = i;
        this.g = z;
        this.h = i2;
        this.j = z2;
        e(i3);
    }

    public int d() {
        return this.f;
    }

    public boolean g() {
        return this.g;
    }

    public int f() {
        return this.h;
    }

    public int a() {
        return this.e;
    }

    public void e(int i) {
        String string;
        this.e = i;
        Context context = BaseApplication.getContext();
        Integer num = b.get(Integer.valueOf(i));
        if (num != null) {
            try {
                string = context.getString(num.intValue());
            } catch (Resources.NotFoundException unused) {
                LogUtil.b("ThirdPartAuthBean", "setHealthTypeId default");
            }
            c(string);
        }
        string = "";
        c(string);
    }

    public String c() {
        return this.c;
    }

    public void c(String str) {
        this.c = str;
    }

    public boolean j() {
        return this.j;
    }

    public void d(boolean z) {
        this.j = z;
    }

    public String e() {
        return this.d;
    }

    public boolean i() {
        return this.i;
    }

    public Drawable dOn_() {
        return this.f16760a;
    }

    public void dOo_(Drawable drawable) {
        this.f16760a = drawable;
    }

    public String toString() {
        return "ThirdPartAuthBean{itemViewType=" + this.f + ", itemTitleShow=" + this.g + ", operationType=" + this.h + ", healthTypeId=" + this.e + ", healthTypeName='" + this.c + "', isOpen=" + this.j + '}';
    }
}
