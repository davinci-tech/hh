package com.huawei.ui.commonui.calendarview;

import android.app.Activity;
import android.content.Intent;
import android.content.res.Configuration;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import androidx.fragment.app.Fragment;
import com.huawei.haf.language.LanguageInstallHelper;
import com.huawei.health.R;
import com.huawei.hianalytics.visual.autocollect.instrument.ViewClickInstrumentation;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.ui.commonui.R$string;
import com.huawei.ui.commonui.base.BaseActivity;
import com.huawei.ui.commonui.base.CommonUiBaseResponse;
import com.huawei.ui.commonui.bottomsheet.HealthBottomSheet;
import com.huawei.ui.commonui.calendarview.HealthCalendarView;
import com.huawei.ui.commonui.healthtextview.HealthTextView;
import com.huawei.uikit.hwbottomsheet.widget.HwBottomSheet;
import defpackage.jcf;
import defpackage.nsf;
import defpackage.nsn;
import java.io.Serializable;

/* loaded from: classes6.dex */
public class HealthCalendarActivity extends Activity implements View.OnClickListener {

    /* renamed from: a, reason: collision with root package name */
    private static CommonUiBaseResponse f8770a;
    private HealthBottomSheet b;
    private HealthMultiCalendarView c;
    private HealthCalendar d;
    private Bundle e;
    private boolean f = false;
    private MarkDateTrigger g;
    private HealthTextView h;
    private Intent j;

    @Override // android.app.Activity
    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        BaseActivity.setDisplaySideMode(this);
        setContentView(R.layout.activity_health_calendar);
        e();
        b();
    }

    private void b() {
        Intent intent = getIntent();
        if (intent == null) {
            return;
        }
        Serializable serializableExtra = intent.getSerializableExtra("calendar");
        if (serializableExtra instanceof HealthCalendar) {
            this.d = (HealthCalendar) serializableExtra;
        }
        boolean booleanExtra = intent.getBooleanExtra("isLandscape", false);
        Bundle extras = intent.getExtras();
        this.e = extras;
        if (extras != null) {
            String stringExtra = intent.getStringExtra("markDescText");
            if (!TextUtils.isEmpty(stringExtra)) {
                this.h.setText(stringExtra);
                this.h.setVisibility(0);
            }
            int intExtra = intent.getIntExtra("markDrawableId", -1);
            if (intExtra != -1) {
                this.h.setCompoundDrawablesWithIntrinsicBounds(getDrawable(intExtra), (Drawable) null, (Drawable) null, (Drawable) null);
            }
            Serializable serializable = this.e.getSerializable("calendar");
            if (serializable instanceof HealthCalendar) {
                this.d = (HealthCalendar) serializable;
            }
            booleanExtra = this.e.getBoolean("isLandscape", false);
            cxh_(intent);
        }
        HealthCalendar healthCalendar = this.d;
        if (healthCalendar != null) {
            this.f = true;
            this.c.setSelectedCalendar(healthCalendar);
            this.c.c();
        }
        if (booleanExtra) {
            setRequestedOrientation(Build.VERSION.SDK_INT == 26 ? -1 : 0);
        }
        int c = c(booleanExtra);
        this.b.setHeightGap(c);
        ViewGroup viewGroup = (ViewGroup) findViewById(R.id.drag_content);
        viewGroup.setPadding(viewGroup.getPaddingLeft(), viewGroup.getPaddingTop(), viewGroup.getPaddingRight(), c);
    }

    private void cxh_(Intent intent) {
        this.c.setIsSetGrayUnmarkedDate(intent.getBooleanExtra("isSetGrayUnmarkedDate", false));
        int intExtra = intent.getIntExtra("selectedThemeColor", -1);
        if (intExtra != -1) {
            this.c.setSelectedThemeColor(intExtra);
        }
        int intExtra2 = intent.getIntExtra("selectedMarkerSize", getResources().getDimensionPixelSize(R.dimen._2131362850_res_0x7f0a0422));
        if (intExtra2 >= 0) {
            this.c.setSelectedMarkerSize(intExtra2);
        }
        int intExtra3 = intent.getIntExtra("itemVerticalSpace", getResources().getDimensionPixelSize(R.dimen._2131362565_res_0x7f0a0305));
        if (intExtra3 >= 0) {
            this.c.setItemVerticalSpace(intExtra3);
        }
        this.c.setExtraSpace(intent.getIntExtra("extraSpace", 0));
        this.c.setLastExtraSpace(intent.getIntExtra("lastExtraSpace", 0));
        MarkDateTrigger markDateTrigger = (MarkDateTrigger) this.e.getParcelable("markDateTrigger");
        this.g = markDateTrigger;
        if (markDateTrigger != null) {
            this.c.setMarkDateTrigger(markDateTrigger);
        }
        this.c.setMarkerViewClickable(this.e.getBoolean("isMarkerClickable"));
    }

    private void e() {
        this.b = (HealthBottomSheet) findViewById(R.id.sliding_layout);
        this.h = (HealthTextView) findViewById(R.id.markDesc);
        this.b.setIndicateSafeInsetsEnabled(true);
        this.b.setForceShowIndicateEnabled(false);
        this.b.setSheetHeight(0);
        this.c = (HealthMultiCalendarView) findViewById(R.id.calendarview);
        findViewById(R.id.content).setOnClickListener(this);
        this.c.setOnCalendarSelectListener(new HealthCalendarView.OnCalendarSelectListener() { // from class: com.huawei.ui.commonui.calendarview.HealthCalendarActivity.5
            @Override // com.huawei.ui.commonui.calendarview.HealthCalendarView.OnCalendarSelectListener
            public void onCalendarOutOfRange(HealthCalendar healthCalendar) {
            }

            @Override // com.huawei.ui.commonui.calendarview.HealthCalendarView.OnCalendarSelectListener
            public void onCalendarSelect(HealthCalendar healthCalendar, boolean z) {
                if (HealthCalendarActivity.this.f) {
                    HealthCalendarActivity.this.f = false;
                    return;
                }
                if (!HealthCalendarActivity.this.c.b() || healthCalendar.hasMark()) {
                    HealthCalendarActivity.this.j = new Intent();
                    HealthCalendarActivity.this.j.putExtra("selectedDate", (Serializable) healthCalendar);
                    if (HealthCalendarActivity.f8770a != null) {
                        HealthCalendarActivity.f8770a.onResponse(1, healthCalendar);
                    }
                    HealthCalendarActivity.this.a();
                }
            }
        });
        this.b.d(new HwBottomSheet.SheetSlideListener() { // from class: com.huawei.ui.commonui.calendarview.HealthCalendarActivity.3
            @Override // com.huawei.uikit.hwbottomsheet.widget.HwBottomSheet.SheetSlideListener
            public void onSheetSlide(View view, float f) {
            }

            @Override // com.huawei.uikit.hwbottomsheet.widget.HwBottomSheet.SheetSlideListener
            public void onSheetStateChanged(View view, HwBottomSheet.SheetState sheetState, HwBottomSheet.SheetState sheetState2) {
                if (sheetState2 == HwBottomSheet.SheetState.COLLAPSED || sheetState2 == HwBottomSheet.SheetState.HIDDEN) {
                    HealthCalendarActivity healthCalendarActivity = HealthCalendarActivity.this;
                    healthCalendarActivity.setResult(-1, healthCalendarActivity.j);
                    HealthCalendarActivity.this.finish();
                }
            }
        });
        jcf.bEE_(this.b.findViewById(R.id.sheet_indicate), 2);
        View findViewById = this.b.findViewById(R.id.sheet_indicate_container);
        jcf.bEE_(findViewById, 1);
        jcf.bEA_(findViewById, nsf.j(R$string.accessibility_close), Button.class);
    }

    private int c(boolean z) {
        int j = nsn.j();
        return z ? nsn.c(this, 8.0f) : (int) ((j - ((j - r1) * 0.8f)) - nsn.r(this));
    }

    @Override // android.app.Activity
    public void onBackPressed() {
        if (this.b.getSheetState() == HwBottomSheet.SheetState.EXPANDED) {
            a();
        } else {
            super.onBackPressed();
        }
    }

    public static void cxj_(Activity activity, Bundle bundle, CommonUiBaseResponse commonUiBaseResponse) {
        if (nsn.a(500)) {
            return;
        }
        if (activity == null) {
            LogUtil.b("HealthCalendarActivity", "startSelf activity is null");
            return;
        }
        f8770a = commonUiBaseResponse;
        Intent intent = new Intent(activity, (Class<?>) HealthCalendarActivity.class);
        intent.putExtras(bundle);
        activity.startActivityForResult(intent, 0, null);
    }

    public static void cxk_(Activity activity, HealthCalendar healthCalendar) {
        if (nsn.a(500)) {
            return;
        }
        Intent intent = new Intent(activity, (Class<?>) HealthCalendarActivity.class);
        intent.putExtra("calendar", (Serializable) healthCalendar);
        activity.startActivityForResult(intent, 0, null);
    }

    public static void e(Fragment fragment, HealthCalendar healthCalendar) {
        if (nsn.a(500)) {
            return;
        }
        Intent intent = new Intent(fragment.getActivity(), (Class<?>) HealthCalendarActivity.class);
        intent.putExtra("calendar", (Serializable) healthCalendar);
        fragment.startActivityForResult(intent, 0, null);
    }

    public static void cxi_(Activity activity, Bundle bundle) {
        if (nsn.a(500)) {
            return;
        }
        Intent intent = new Intent(activity, (Class<?>) HealthCalendarActivity.class);
        intent.putExtras(bundle);
        activity.startActivityForResult(intent, 0, null);
    }

    public static void cxl_(Fragment fragment, Bundle bundle) {
        if (nsn.a(500)) {
            return;
        }
        Intent intent = new Intent(fragment.getActivity(), (Class<?>) HealthCalendarActivity.class);
        intent.putExtras(bundle);
        fragment.startActivityForResult(intent, 0, null);
    }

    public static void cxn_(Activity activity, HealthCalendar healthCalendar) {
        if (nsn.a(500)) {
            return;
        }
        Intent intent = new Intent(activity, (Class<?>) HealthCalendarActivity.class);
        intent.putExtra("calendar", (Serializable) healthCalendar);
        intent.putExtra("isLandscape", true);
        activity.startActivityForResult(intent, 0, null);
    }

    public static void cxm_(Activity activity, Bundle bundle) {
        if (nsn.a(500)) {
            return;
        }
        Intent intent = new Intent(activity, (Class<?>) HealthCalendarActivity.class);
        intent.putExtra("isLandscape", true);
        intent.putExtras(bundle);
        activity.startActivityForResult(intent, 0, null);
    }

    public static void cxo_(Fragment fragment, Bundle bundle) {
        if (nsn.a(500)) {
            return;
        }
        Intent intent = new Intent(fragment.getActivity(), (Class<?>) HealthCalendarActivity.class);
        intent.putExtra("isLandscape", true);
        intent.putExtras(bundle);
        fragment.startActivityForResult(intent, 0, null);
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View view) {
        a();
        ViewClickInstrumentation.clickOnView(view);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a() {
        setResult(-1, this.j);
        finish();
    }

    @Override // android.app.Activity, android.content.ComponentCallbacks
    public void onConfigurationChanged(Configuration configuration) {
        LanguageInstallHelper.updateResources(this);
        super.onConfigurationChanged(configuration);
    }
}
