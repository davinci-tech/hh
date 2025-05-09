package com.huawei.ui.commonui.popupview;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListAdapter;
import android.widget.ListView;
import com.huawei.health.R;
import com.huawei.hianalytics.visual.autocollect.instrument.ViewClickInstrumentation;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.ui.commonui.R$color;
import com.huawei.ui.commonui.R$string;
import com.huawei.ui.commonui.healthtextview.HealthTextView;
import com.huawei.uikit.hwcolumnsystem.widget.HwColumnSystem;
import defpackage.koq;
import defpackage.nqc;
import defpackage.nsy;
import java.io.File;
import java.util.ArrayList;

/* loaded from: classes6.dex */
public class PopViewList {
    private static boolean d;
    private static boolean e;

    /* renamed from: a, reason: collision with root package name */
    private Context f8917a;
    private ArrayList<String> b;
    private ListView c;
    private e f;
    private PopViewClickListener i;
    private nqc j;

    public interface PopViewClickListener {
        void setOnClick(int i);
    }

    public PopViewList(Context context, ArrayList<String> arrayList) {
        ArrayList<String> arrayList2 = new ArrayList<>();
        this.b = arrayList2;
        this.f8917a = context;
        arrayList2.addAll(arrayList);
        c();
        d();
    }

    public PopViewList(Context context, View view, ArrayList<String> arrayList) {
        this(context, view, arrayList, 17);
    }

    public PopViewList(Context context, View view, ArrayList<String> arrayList, int i) {
        this(context, arrayList);
        this.j.cEh_(view, i);
    }

    public PopViewList(Context context, View view, ArrayList<String> arrayList, boolean z, boolean z2) {
        this(context, view, arrayList, 17);
        e(z);
        a(z2);
    }

    private static void e(boolean z) {
        d = z;
    }

    private static void a(boolean z) {
        e = z;
    }

    private void c() {
        LogUtil.a("SPORT_ENTRANCE_POP_VIEW", "initSportEntrancePopView");
        View inflate = LayoutInflater.from(this.f8917a).inflate(R.layout.list_pop_window, (ViewGroup) null);
        ListView listView = (ListView) nsy.cMd_(inflate, R.id.list_pop_window_tab);
        this.c = listView;
        listView.setOverScrollMode(2);
        this.j = new nqc(this.f8917a, inflate);
    }

    private int cEi_(ListView listView) {
        if (listView == null || listView.getAdapter() == null) {
            return 0;
        }
        int count = listView.getAdapter().getCount();
        int i = 0;
        for (int i2 = 0; i2 < count; i2++) {
            View view = listView.getAdapter().getView(i2, null, listView);
            if (view != null) {
                view.measure(View.MeasureSpec.makeMeasureSpec(0, 0), View.MeasureSpec.makeMeasureSpec(0, 0));
                if (view.getMeasuredWidth() > i) {
                    i = view.getMeasuredWidth();
                }
            }
        }
        return e(i);
    }

    private void d() {
        e eVar = new e(this.f8917a, this.b);
        this.f = eVar;
        this.c.setAdapter((ListAdapter) eVar);
        this.c.setLayoutParams(new LinearLayout.LayoutParams(cEi_(this.c), -2));
        this.c.setOnItemClickListener(new AdapterView.OnItemClickListener() { // from class: com.huawei.ui.commonui.popupview.PopViewList.2
            @Override // android.widget.AdapterView.OnItemClickListener
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long j) {
                if (i >= 0) {
                    if (!PopViewList.e(i, PopViewList.this.b, PopViewList.this.f8917a)) {
                        if (PopViewList.this.i != null) {
                            PopViewList.this.i.setOnClick(i);
                        }
                        PopViewList.this.j.b();
                        ViewClickInstrumentation.clickOnListView(adapterView, view, i);
                        return;
                    }
                    LogUtil.a("SPORT_ENTRANCE_POP_VIEW", "isIgnoreByLegal: legal ");
                    ViewClickInstrumentation.clickOnListView(adapterView, view, i);
                    return;
                }
                LogUtil.h("SPORT_ENTRANCE_POP_VIEW", "position is error");
                ViewClickInstrumentation.clickOnListView(adapterView, view, i);
            }
        });
    }

    int e(int i) {
        HwColumnSystem hwColumnSystem = new HwColumnSystem(this.f8917a);
        hwColumnSystem.e(10);
        hwColumnSystem.e(this.f8917a);
        int b = hwColumnSystem.b();
        int j = hwColumnSystem.j();
        return i > b ? b : i < j ? j : i;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static boolean e(int i, ArrayList<String> arrayList, Context context) {
        if (i < arrayList.size() && arrayList.get(i).equals(context.getString(R$string.IDS_setting_wearhome_legal_information))) {
            return !e();
        }
        return false;
    }

    public void e(String str, int i) {
        e eVar = this.f;
        if (eVar != null) {
            eVar.a(str, i);
        }
    }

    public void b() {
        this.j.b();
    }

    public boolean a() {
        return this.j.c();
    }

    private static boolean e() {
        return e && d;
    }

    static class e extends BaseAdapter {
        private Context c;
        private ArrayList<String> d;
        private ArrayList<String> e;

        @Override // android.widget.Adapter
        public long getItemId(int i) {
            return i;
        }

        e(Context context, ArrayList<String> arrayList) {
            this.c = context;
            int indexOf = arrayList != null ? arrayList.indexOf(File.separator) : -1;
            if (indexOf == -1) {
                this.d = arrayList == null ? new ArrayList<>(10) : arrayList;
                return;
            }
            this.d = new ArrayList<>(10);
            this.e = new ArrayList<>(10);
            for (int i = 0; i < arrayList.size(); i++) {
                if (i < indexOf) {
                    this.d.add(arrayList.get(i));
                } else if (i > indexOf) {
                    this.e.add(arrayList.get(i));
                } else {
                    LogUtil.c("SPORT_ENTRANCE_POP_VIEW", "PopWindowItemAdapter no deal branch.");
                }
            }
        }

        void a(String str, int i) {
            ArrayList<String> arrayList;
            if (i == 1) {
                ArrayList<String> arrayList2 = this.e;
                if (arrayList2 != null && !arrayList2.contains(str)) {
                    this.e.add(str);
                }
            } else if (i == 2 && (arrayList = this.e) != null && arrayList.contains(str)) {
                this.e.remove(str);
            }
            notifyDataSetChanged();
        }

        @Override // android.widget.Adapter
        public int getCount() {
            return this.d.size();
        }

        @Override // android.widget.Adapter
        public Object getItem(int i) {
            if (koq.d(this.d, i)) {
                return null;
            }
            return this.d.get(i);
        }

        @Override // android.widget.Adapter
        public View getView(int i, View view, ViewGroup viewGroup) {
            View inflate = LayoutInflater.from(this.c).inflate(R.layout.list_pop_window_item, (ViewGroup) null);
            HealthTextView healthTextView = (HealthTextView) nsy.cMd_(inflate, R.id.list_popup_window_item_text);
            ImageView imageView = (ImageView) nsy.cMd_(inflate, R.id.img_sport_setting_red_point);
            if (koq.d(this.d, i)) {
                String str = this.d.get(i);
                healthTextView.setText(str);
                d(i, healthTextView);
                ArrayList<String> arrayList = this.e;
                if (arrayList != null && arrayList.contains(str)) {
                    imageView.setVisibility(0);
                }
            }
            return inflate;
        }

        private void d(int i, HealthTextView healthTextView) {
            if (PopViewList.e(i, this.d, this.c)) {
                healthTextView.setTextColor(this.c.getResources().getColor(R$color.legal_un_clickable_color));
            } else {
                healthTextView.setTextColor(this.c.getResources().getColor(R$color.textColorPrimary));
            }
        }
    }

    public void e(PopViewClickListener popViewClickListener) {
        this.i = popViewClickListener;
    }
}
