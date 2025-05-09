package defpackage;

import android.content.Context;
import android.content.res.TypedArray;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.util.Log;
import com.huawei.haf.application.BaseApplication;
import com.huawei.health.R;
import com.huawei.operation.share.ShareConfig;
import com.huawei.ui.commonui.R$styleable;
import com.huawei.ui.commonui.calendarview.HealthCalendar;
import com.huawei.ui.commonui.calendarview.HealthCalendarView;
import com.huawei.ui.commonui.calendarview.HealthDefaultMonthView;
import com.huawei.ui.commonui.calendarview.HealthDefaultWeekView;
import com.huawei.ui.commonui.calendarview.HealthMark;
import com.huawei.ui.commonui.calendarview.HealthWeekBar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/* loaded from: classes6.dex */
public final class nky {

    /* renamed from: a, reason: collision with root package name */
    public HealthCalendar f15354a;
    private boolean ab;
    private boolean ae;
    private boolean af;
    private boolean ag;
    private boolean ah;
    private int aj;
    private int ak;
    private boolean al;
    private int am;
    private int an;
    private boolean ao;
    private HealthCalendar ap;
    private int ar;
    private HealthCalendar as;
    private int at;
    private Class<?> au;
    private HealthCalendar av;
    private String aw;
    private int ax;
    private int ay;
    private int az;
    public Map<String, HealthCalendar> b;
    private int ba;
    private int bb;
    private int bc;
    private int bd;
    private String be;
    private Class<?> bf;
    private int bg;
    private int bh;
    private String bi;
    private Class<?> bj;
    private int bl;
    public HealthCalendarView.OnInnerDateSelectedListener c;
    public HealthCalendarView.OnCalendarSelectListener d;
    HealthMark e;
    public int f;
    public HealthCalendar g;
    public HealthCalendar h;
    public int i;
    public int j;
    public int k;
    public HealthCalendar l;
    public int m;
    public HealthCalendarView.OnViewChangeListener n;
    public HealthCalendarView.OnWeekChangeListener o;
    private int p;
    private int q;
    private int r;
    private int s;
    public int t;
    private int u;
    private int w;
    private int x;
    private int y;
    private boolean z;
    private boolean aa = true;
    private boolean ac = true;
    private boolean ai = true;
    private boolean v = true;
    private boolean ad = false;
    private HashMap<String, int[]> aq = new HashMap<>();

    public void c(int i) {
    }

    public nky(Context context, AttributeSet attributeSet, int i, int i2) {
        TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, R$styleable.HealthCalendarView, i, i2);
        this.p = obtainStyledAttributes.getDimensionPixelSize(R$styleable.HealthCalendarView_calendar_padding_start, 0);
        this.s = obtainStyledAttributes.getDimensionPixelSize(R$styleable.HealthCalendarView_calendar_padding_end, 0);
        if (obtainStyledAttributes.peekValue(R$styleable.HealthCalendarView_calendar_padding) != null) {
            int dimensionPixelSize = obtainStyledAttributes.getDimensionPixelSize(R$styleable.HealthCalendarView_calendar_padding, 0);
            this.r = dimensionPixelSize;
            this.p = dimensionPixelSize;
            this.s = dimensionPixelSize;
        }
        this.aw = obtainStyledAttributes.getString(R$styleable.HealthCalendarView_month_view);
        this.bi = obtainStyledAttributes.getString(R$styleable.HealthCalendarView_week_view);
        this.be = obtainStyledAttributes.getString(R$styleable.HealthCalendarView_week_bar_view);
        this.bl = obtainStyledAttributes.getDimensionPixelSize(R$styleable.HealthCalendarView_week_text_size, 0);
        this.bh = obtainStyledAttributes.getDimensionPixelSize(R$styleable.HealthCalendarView_week_bar_height, 0);
        this.t = obtainStyledAttributes.getDimensionPixelSize(R$styleable.HealthCalendarView_week_date_text_margin_top, 0);
        this.k = obtainStyledAttributes.getDimensionPixelSize(R$styleable.HealthCalendarView_week_date_maker_margin_top, 0);
        this.m = obtainStyledAttributes.getDimensionPixelSize(R$styleable.HealthCalendarView_week_date_circle_size, context.getResources().getDimensionPixelSize(R.dimen._2131362850_res_0x7f0a0422));
        this.ag = obtainStyledAttributes.getBoolean(R$styleable.HealthCalendarView_subHeader_show_date, false);
        this.af = obtainStyledAttributes.getBoolean(R$styleable.HealthCalendarView_week_day_replace_date, false);
        this.z = obtainStyledAttributes.getBoolean(R$styleable.HealthCalendarView_month_view_scrollable, true);
        this.al = obtainStyledAttributes.getBoolean(R$styleable.HealthCalendarView_week_view_scrollable, true);
        this.u = obtainStyledAttributes.getInt(R$styleable.HealthCalendarView_month_view_auto_select_day, 0);
        this.ax = obtainStyledAttributes.getInt(R$styleable.HealthCalendarView_month_view_show_mode, 2);
        this.bg = obtainStyledAttributes.getInt(R$styleable.HealthCalendarView_week_start_with, 1);
        this.ay = obtainStyledAttributes.getColor(R$styleable.HealthCalendarView_week_background, 0);
        this.bd = obtainStyledAttributes.getColor(R$styleable.HealthCalendarView_week_text_color, 0);
        this.x = obtainStyledAttributes.getColor(R$styleable.HealthCalendarView_current_day_text_color, 0);
        this.y = obtainStyledAttributes.getColor(R$styleable.HealthCalendarView_current_day_selected_text_color, 0);
        this.bc = obtainStyledAttributes.getColor(R$styleable.HealthCalendarView_selected_theme_color, 0);
        this.ba = obtainStyledAttributes.getColor(R$styleable.HealthCalendarView_selected_text_color, 0);
        this.az = obtainStyledAttributes.getColor(R$styleable.HealthCalendarView_current_month_text_color, 0);
        this.at = obtainStyledAttributes.getColor(R$styleable.HealthCalendarView_other_month_text_color, 0);
        this.w = obtainStyledAttributes.getDimensionPixelSize(R$styleable.HealthCalendarView_day_text_size, 0);
        this.q = obtainStyledAttributes.getDimensionPixelSize(R$styleable.HealthCalendarView_calendar_height, 0);
        this.ak = obtainStyledAttributes.getDimensionPixelSize(R$styleable.HealthCalendarView_item_vertical_space, 0);
        this.an = obtainStyledAttributes.getDimensionPixelSize(R$styleable.HealthCalendarView_item_extra_space, 0);
        this.aj = obtainStyledAttributes.getDimensionPixelSize(R$styleable.HealthCalendarView_last_item_extra_space, 0);
        this.ah = obtainStyledAttributes.getBoolean(R$styleable.HealthCalendarView_show_future_date, true);
        this.ae = obtainStyledAttributes.getBoolean(R$styleable.HealthCalendarView_set_gray_unmarked_date, false);
        cxu_(obtainStyledAttributes);
        cxv_(obtainStyledAttributes);
        obtainStyledAttributes.recycle();
        ax();
    }

    private void cxu_(TypedArray typedArray) {
        this.f = typedArray.getDimensionPixelSize(R$styleable.HealthCalendarView_mark_margin_top, 0);
        this.j = typedArray.getDimensionPixelSize(R$styleable.HealthCalendarView_mark_view_size, 0);
        this.am = typedArray.getColor(R$styleable.HealthCalendarView_mark_text_color, 0);
        this.ar = typedArray.getDimensionPixelSize(R$styleable.HealthCalendarView_mark_text_size, 0);
        int color = typedArray.getColor(R$styleable.HealthCalendarView_mark_theme_color, 0);
        HealthMark healthMark = new HealthMark(typedArray.getDrawable(R$styleable.HealthCalendarView_mark_drawale), typedArray.getString(R$styleable.HealthCalendarView_mark_text), color);
        this.e = healthMark;
        healthMark.d(this.f);
    }

    private void cxv_(TypedArray typedArray) {
        int i = typedArray.getInt(R$styleable.HealthCalendarView_min_year, 1900);
        this.as = new HealthCalendar(i > 1900 ? i : 1900, typedArray.getInt(R$styleable.HealthCalendarView_min_year_month, 1), typedArray.getInt(R$styleable.HealthCalendarView_min_year_day, 1));
        int i2 = R$styleable.HealthCalendarView_max_year;
        int i3 = ShareConfig.MSG_SHARE_FAIL_TOAST;
        int i4 = typedArray.getInt(i2, ShareConfig.MSG_SHARE_FAIL_TOAST);
        int i5 = typedArray.getInt(R$styleable.HealthCalendarView_max_year_month, 12);
        int i6 = typedArray.getInt(R$styleable.HealthCalendarView_max_year_day, -1);
        if (i4 < 2099) {
            i3 = i4;
        }
        this.ap = new HealthCalendar(i3, i5, i6);
    }

    private void ax() {
        this.av = new HealthCalendar();
        Date date = new Date();
        this.av.setYear(nkw.c("yyyy", date));
        this.av.setMonth(nkw.c("MM", date));
        this.av.setDay(nkw.c("dd", date));
        this.av.setPresentDay(true);
        at();
        try {
            this.bf = TextUtils.isEmpty(this.be) ? HealthWeekBar.class : Class.forName(this.be);
        } catch (ClassNotFoundException unused) {
            Log.e("HealthCalendarViewManager", "WeekBar class not found");
        }
        try {
            this.au = TextUtils.isEmpty(this.aw) ? HealthDefaultMonthView.class : Class.forName(this.aw);
        } catch (ClassNotFoundException unused2) {
            Log.e("HealthCalendarViewManager", "MonthView class not found");
        }
        try {
            this.bj = TextUtils.isEmpty(this.bi) ? HealthDefaultWeekView.class : Class.forName(this.bi);
        } catch (ClassNotFoundException unused3) {
            Log.e("HealthCalendarViewManager", "WeekView class not found");
        }
    }

    public int g() {
        return this.x;
    }

    public int j() {
        return this.y;
    }

    public int ab() {
        return this.bd;
    }

    public int k() {
        return this.am;
    }

    public int o() {
        return this.ar;
    }

    public int s() {
        return this.at;
    }

    public int x() {
        return this.az;
    }

    public int w() {
        return this.ba;
    }

    public HealthMark i() {
        return this.e;
    }

    public void f(int i) {
        this.bc = i;
        this.x = i;
    }

    public void l(int i) {
        this.m = i;
    }

    public int ac() {
        return this.m;
    }

    public void e(int i) {
        this.ak = i;
    }

    public int u() {
        return this.bc;
    }

    public int v() {
        return this.ay;
    }

    public Class<?> t() {
        return this.au;
    }

    public Class<?> ag() {
        return this.bj;
    }

    public Class<?> ad() {
        return this.bf;
    }

    public int aa() {
        return this.bh;
    }

    public void h(int i) {
        this.bh = i;
    }

    public int y() {
        return this.bb;
    }

    public void i(int i) {
        this.bb = i;
    }

    public int h() {
        if (nsn.r()) {
            return BaseApplication.e().getResources().getDimensionPixelSize(R.dimen._2131362973_res_0x7f0a049d);
        }
        return this.w;
    }

    public void a(int i) {
        this.w = i;
    }

    public int c() {
        return this.q + this.ak;
    }

    public int l() {
        return this.an;
    }

    int m() {
        return this.aj;
    }

    public void d(int i) {
        this.aj = i;
    }

    public void b(int i) {
        this.an = i;
    }

    public int p() {
        return this.ax;
    }

    public void j(int i) {
        this.ax = i;
    }

    public boolean aq() {
        return this.ag;
    }

    public boolean ar() {
        return this.af;
    }

    public void g(boolean z) {
        this.af = z;
    }

    public void g(int i) {
        this.j = i;
    }

    public boolean ai() {
        return this.z;
    }

    public boolean ap() {
        return this.al;
    }

    public void b(boolean z) {
        this.ad = z;
    }

    public void j(boolean z) {
        this.z = z;
    }

    public void k(boolean z) {
        this.al = z;
    }

    public int z() {
        return this.bg;
    }

    public void m(int i) {
        this.bg = i;
    }

    int f() {
        return this.u;
    }

    public boolean ae() {
        return this.v;
    }

    public void c(boolean z) {
        this.v = z;
    }

    public int ah() {
        if (nsn.r()) {
            return BaseApplication.e().getResources().getDimensionPixelSize(R.dimen._2131362922_res_0x7f0a046a);
        }
        return this.bl;
    }

    public HealthCalendar q() {
        return this.av;
    }

    public void as() {
        Date date = new Date();
        this.av.setYear(nkw.c("yyyy", date));
        this.av.setMonth(nkw.c("MM", date));
        this.av.setDay(nkw.c("dd", date));
    }

    public int a() {
        return this.p;
    }

    public int b() {
        return this.s;
    }

    public boolean ao() {
        return this.ah;
    }

    public void i(boolean z) {
        this.ah = z;
    }

    public boolean am() {
        return this.ae;
    }

    public void a(boolean z) {
        this.ae = z;
    }

    public boolean an() {
        return this.ab;
    }

    public void e(boolean z) {
        this.ab = z;
    }

    public boolean al() {
        return this.aa;
    }

    public void d(boolean z) {
        this.aa = z;
    }

    public boolean ak() {
        return this.ac;
    }

    public void f(boolean z) {
        this.ac = z;
    }

    public HealthCalendar d() {
        HealthCalendar healthCalendar = new HealthCalendar();
        healthCalendar.setYear(this.av.getYear());
        healthCalendar.setMonth(this.av.getMonth());
        healthCalendar.setDay(this.av.getDay());
        healthCalendar.setPresentDay(true);
        return healthCalendar;
    }

    public HealthCalendar r() {
        return this.as;
    }

    public HealthCalendar n() {
        return this.ap;
    }

    public void e(List<HealthCalendar> list) {
        Map<String, HealthCalendar> map = this.b;
        if (map == null || map.size() == 0) {
            return;
        }
        for (HealthCalendar healthCalendar : list) {
            if (this.b.containsKey(healthCalendar.toString())) {
                HealthCalendar healthCalendar2 = this.b.get(healthCalendar.toString());
                if (healthCalendar2 != null) {
                    if (healthCalendar2.getMarks() == null) {
                        healthCalendar2.addMark(this.e);
                    }
                    healthCalendar.setMarks(healthCalendar2.getMarks());
                }
            } else {
                healthCalendar.clearMark();
            }
        }
    }

    public void e(Map<String, HealthCalendar> map) {
        if (this.b == null) {
            this.b = new HashMap();
        }
        for (Map.Entry<String, HealthCalendar> entry : map.entrySet()) {
            String key = entry.getKey();
            this.b.remove(key);
            HealthCalendar value = entry.getValue();
            if (value != null) {
                if (value.getMarks() == null) {
                    value.addMark(this.e);
                }
                this.b.put(key, value);
            }
        }
    }

    public void e() {
        this.b = null;
    }

    public void c(HealthCalendar healthCalendar, HealthCalendar healthCalendar2) {
        this.as = healthCalendar;
        this.ap = healthCalendar2;
        at();
    }

    public void d(HealthCalendar healthCalendar, HealthCalendar healthCalendar2) {
        this.h = healthCalendar;
        this.g = healthCalendar2;
    }

    private void at() {
        if (!this.ad && this.ap.getYear() < this.av.getYear()) {
            this.ap.setYear(this.av.getYear());
        }
        if (this.ap.getDay() == -1) {
            HealthCalendar healthCalendar = this.ap;
            healthCalendar.setDay(nkw.d(healthCalendar.getYear(), this.ap.getMonth()));
        }
        this.i = nkw.e(this.av, this);
    }

    public boolean af() {
        return this.ao;
    }

    public void h(boolean z) {
        this.ao = z;
    }

    public void o(boolean z) {
        this.ai = z;
    }

    public boolean aj() {
        return this.ai;
    }

    int[] c(String str) {
        return this.aq.get(str);
    }

    public void c(String str, int[] iArr) {
        this.aq.put(str, iArr);
    }
}
