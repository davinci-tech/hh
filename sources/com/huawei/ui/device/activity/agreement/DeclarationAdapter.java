package com.huawei.ui.device.activity.agreement;

import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.text.SpannableStringBuilder;
import android.text.TextPaint;
import android.text.TextUtils;
import android.text.method.LinkMovementMethod;
import android.text.style.ClickableSpan;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewStub;
import android.widget.BaseAdapter;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListAdapter;
import android.widget.ListView;
import com.huawei.haf.threadpool.ThreadPoolManager;
import com.huawei.health.R;
import com.huawei.health.device.util.EventBus;
import com.huawei.hianalytics.visual.autocollect.instrument.ViewClickInstrumentation;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.nfc.PluginPayAdapter;
import com.huawei.openalliance.ad.constant.Constants;
import com.huawei.openalliance.ad.constant.OsType;
import com.huawei.ui.commonui.checkbox.HealthCheckBox;
import com.huawei.ui.commonui.dialog.CustomAlertDialog;
import com.huawei.ui.commonui.healthtextview.HealthTextView;
import com.huawei.ui.commonui.switchbutton.HealthSwitchButton;
import com.huawei.ui.device.declaration.xmlparser.DeclarationConstants;
import defpackage.jdx;
import defpackage.nsn;
import defpackage.nuj;
import defpackage.nyu;
import defpackage.nzb;
import defpackage.nzg;
import defpackage.nzk;
import defpackage.nzl;
import defpackage.nzm;
import defpackage.nzn;
import defpackage.nzo;
import defpackage.nzp;
import defpackage.nzr;
import defpackage.nzt;
import health.compact.a.CommonUtil;
import health.compact.a.GRSManager;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;

/* loaded from: classes6.dex */
public class DeclarationAdapter extends BaseAdapter {

    /* renamed from: a, reason: collision with root package name */
    private boolean f9042a;
    private List<nyu> b;
    private Context c;
    private String d;
    private nyu e;
    private CustomAlertDialog f;
    private int g;
    private String h;
    private CompoundButton.OnCheckedChangeListener i = new CompoundButton.OnCheckedChangeListener() { // from class: com.huawei.ui.device.activity.agreement.DeclarationAdapter.5
        @Override // android.widget.CompoundButton.OnCheckedChangeListener
        public void onCheckedChanged(CompoundButton compoundButton, boolean z) {
            if (DeclarationAdapter.this.e != null) {
                String d = DeclarationAdapter.this.e.d();
                if (TextUtils.isEmpty(d)) {
                    LogUtil.h("DeclarationAdapter", " featureId is empty");
                    ViewClickInstrumentation.clickOnView(compoundButton);
                    return;
                }
                Bundle bundle = new Bundle();
                LogUtil.a("DeclarationAdapter", " featureId is :", d);
                if ("hms_auto_update".equals(d)) {
                    LogUtil.a("DeclarationAdapter", " featureId is HMS_AUTO_UPDATE :", Boolean.valueOf(z));
                    bundle.putInt("hms_auto_update", z ? 1 : 0);
                    EventBus.d(new EventBus.b("device_pair_aboard_switch_status", bundle));
                }
                ViewClickInstrumentation.clickOnView(compoundButton);
                return;
            }
            LogUtil.h("DeclarationAdapter", " mDeclarationBean is null");
            ViewClickInstrumentation.clickOnView(compoundButton);
        }
    };

    @Override // android.widget.Adapter
    public long getItemId(int i) {
        return i;
    }

    @Override // android.widget.BaseAdapter, android.widget.Adapter
    public int getViewTypeCount() {
        return 2;
    }

    public DeclarationAdapter(Context context, List<nyu> list, int i) {
        this.f9042a = false;
        this.g = -1;
        this.c = context;
        this.b = list;
        this.f9042a = a(list);
        this.d = GRSManager.a(context).getCommonCountryCode();
        this.g = i;
    }

    @Override // android.widget.Adapter
    public int getCount() {
        List<nyu> list = this.b;
        if (list == null) {
            return 0;
        }
        return list.size();
    }

    @Override // android.widget.Adapter
    /* renamed from: e, reason: merged with bridge method [inline-methods] */
    public nyu getItem(int i) {
        List<nyu> list = this.b;
        if (list == null || list.isEmpty()) {
            LogUtil.h("DeclarationAdapter", "getItem: mData is null or empty.");
            return new nyu();
        }
        if (i < 0 || i > this.b.size() - 1) {
            LogUtil.h("DeclarationAdapter", "getItem: illegal position.");
            return new nyu();
        }
        return this.b.get(i);
    }

    @Override // android.widget.BaseAdapter, android.widget.Adapter
    public int getItemViewType(int i) {
        if (i < 0 || i > this.b.size() - 1) {
            LogUtil.h("DeclarationAdapter", "getItemViewType: illegal position.");
            return 0;
        }
        return this.b.get(i).i();
    }

    @Override // android.widget.Adapter
    public View getView(int i, View view, ViewGroup viewGroup) {
        List<nyu> list = this.b;
        if (list == null || list.isEmpty()) {
            LogUtil.h("DeclarationAdapter", "getView: mData is null or empty.");
            return new View(this.c);
        }
        if (i < 0 || i > this.b.size() - 1) {
            LogUtil.h("DeclarationAdapter", "getView: illegal position.");
            return new View(this.c);
        }
        if (this.b.get(i) == null) {
            LogUtil.h("DeclarationAdapter", "getView: the data of this position is null");
            return new View(this.c);
        }
        nyu nyuVar = this.b.get(i);
        int itemViewType = getItemViewType(i);
        if (itemViewType == 0) {
            return cOT_(view, viewGroup, nyuVar);
        }
        if (itemViewType == 1) {
            return cOR_(view, viewGroup, nyuVar);
        }
        View view2 = new View(this.c);
        LogUtil.h("DeclarationAdapter", "get view in default case");
        return view2;
    }

    private View cOT_(View view, ViewGroup viewGroup, nyu nyuVar) {
        if (view == null) {
            view = LayoutInflater.from(this.c).inflate(R.layout.item_list_view_declaration_head, viewGroup, false);
            HealthTextView healthTextView = (HealthTextView) view.findViewById(R.id.tv_title);
            if (nyuVar != null && healthTextView != null) {
                String h = nyuVar.h();
                if (!TextUtils.isEmpty(h)) {
                    healthTextView.setText(nuj.a(h));
                }
            }
        }
        return view;
    }

    private View cOR_(View view, ViewGroup viewGroup, nyu nyuVar) {
        c cVar;
        if (TextUtils.isEmpty(nyuVar.d())) {
            LogUtil.h("DeclarationAdapter", "getBodyItemView: featureId is null or emtpy.");
            return new View(this.c);
        }
        if (view == null) {
            view = LayoutInflater.from(this.c).inflate(R.layout.item_list_view_declaration_body, viewGroup, false);
            cVar = new c(view);
            view.setTag(cVar);
        } else {
            Object tag = view.getTag();
            cVar = tag instanceof c ? (c) tag : null;
        }
        if (cVar == null) {
            LogUtil.h("DeclarationAdapter", "getBodyItemView: bodyViewHolder is null.");
            return new View(this.c);
        }
        a(cVar, nyuVar);
        return view;
    }

    private void a(c cVar, nyu nyuVar) {
        if (cVar == null || nyuVar == null) {
            LogUtil.h("DeclarationAdapter", "setValue some thing is null");
            return;
        }
        b(cVar, nyuVar);
        cVar.f9045a.removeAllViews();
        List<nzn> e = nyuVar.e();
        if (e == null || e.isEmpty()) {
            return;
        }
        a(cVar, e, nyuVar);
    }

    private void b(c cVar, nyu nyuVar) {
        if (cVar.g.getParent() != null && nyuVar.n()) {
            cOX_(cVar, cVar.g);
        }
        if (cVar.i.getParent() != null && nyuVar.j()) {
            cOX_(cVar, cVar.i);
        }
        String h = nyuVar.h();
        if (TextUtils.isEmpty(h)) {
            cVar.e.setVisibility(8);
        } else {
            cVar.e.setVisibility(0);
            cVar.e.setText(nuj.a(h));
        }
        if (cVar.d) {
            cVar.b.setOnCheckedChangeListener(null);
            if ("select_all_enhanced_services".equals(nyuVar.d())) {
                cVar.b.setChecked(a(this.b));
            } else {
                cVar.b.setChecked(nyuVar.g());
            }
        }
        int i = (nyuVar.j() || nyuVar.n()) ? 0 : 8;
        if (cVar.d) {
            cVar.b.setVisibility(i);
        }
        d(cVar, nyuVar);
    }

    private void cOX_(c cVar, ViewStub viewStub) {
        View inflate = viewStub.inflate();
        if (inflate != null) {
            cVar.d = true;
            cVar.b = (HealthCheckBox) inflate.findViewById(R.id.checkbox);
        }
    }

    private void d(c cVar, final nyu nyuVar) {
        if (!cVar.d) {
            LogUtil.h("DeclarationAdapter", "addCheckedListener: do not have checkbox.");
        } else {
            cVar.b.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() { // from class: com.huawei.ui.device.activity.agreement.DeclarationAdapter.4
                @Override // android.widget.CompoundButton.OnCheckedChangeListener
                public void onCheckedChanged(CompoundButton compoundButton, boolean z) {
                    LogUtil.a("DeclarationAdapter", "enter setOnCheckedChangeListener");
                    if (!"select_all_enhanced_services".equals(nyuVar.d())) {
                        for (nyu nyuVar2 : DeclarationAdapter.this.b) {
                            String d = nyuVar2.d();
                            if (!TextUtils.isEmpty(d) && d.equals(nyuVar.d())) {
                                nyuVar2.e(z);
                            }
                        }
                        for (nyu nyuVar3 : DeclarationAdapter.this.b) {
                            if ("select_all_enhanced_services".equals(nyuVar3.d())) {
                                DeclarationAdapter declarationAdapter = DeclarationAdapter.this;
                                nyuVar3.e(declarationAdapter.a((List<nyu>) declarationAdapter.b));
                            }
                        }
                        DeclarationAdapter.this.notifyDataSetChanged();
                    } else if (!"select_all_enhanced_services".equals(nyuVar.d())) {
                        LogUtil.a("DeclarationAdapter", "bean.getFeatureId()", nyuVar.d());
                    } else {
                        LogUtil.a("DeclarationAdapter", "enter equals All Enhanced services");
                        DeclarationAdapter declarationAdapter2 = DeclarationAdapter.this;
                        if (declarationAdapter2.c((List<nyu>) declarationAdapter2.b)) {
                            DeclarationAdapter.this.f9042a = false;
                        } else {
                            DeclarationAdapter declarationAdapter3 = DeclarationAdapter.this;
                            declarationAdapter3.f9042a = declarationAdapter3.a((List<nyu>) declarationAdapter3.b);
                        }
                        DeclarationAdapter.this.f9042a = !r7.f9042a;
                        Iterator it = DeclarationAdapter.this.b.iterator();
                        while (it.hasNext()) {
                            ((nyu) it.next()).e(DeclarationAdapter.this.f9042a);
                        }
                        LogUtil.a("DeclarationAdapter", "mIsSelectAll", Boolean.valueOf(DeclarationAdapter.this.f9042a));
                        DeclarationAdapter.this.notifyDataSetChanged();
                    }
                    ViewClickInstrumentation.clickOnView(compoundButton);
                }
            });
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public boolean a(List<nyu> list) {
        if (list == null || list.size() <= 0) {
            return false;
        }
        for (nyu nyuVar : list) {
            if (nyuVar != null && !TextUtils.isEmpty(nyuVar.d()) && !"select_all_enhanced_services".equals(nyuVar.d()) && !nyuVar.g()) {
                return false;
            }
        }
        return true;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public boolean c(List<nyu> list) {
        if (list == null || list.size() <= 0) {
            return false;
        }
        for (nyu nyuVar : list) {
            if (nyuVar != null && !TextUtils.isEmpty(nyuVar.d()) && !"select_all_enhanced_services".equals(nyuVar.d()) && nyuVar.g()) {
                return false;
            }
        }
        return true;
    }

    private void d(HealthTextView healthTextView) {
        if (healthTextView == null) {
            LogUtil.h("DeclarationAdapter", "addSwitchFaceListener textView is null");
        } else {
            healthTextView.setOnClickListener(new View.OnClickListener() { // from class: com.huawei.ui.device.activity.agreement.DeclarationAdapter.2
                @Override // android.view.View.OnClickListener
                public void onClick(View view) {
                    try {
                        Intent intent = new Intent(DeclarationAdapter.this.c, (Class<?>) HmsAutoUpdateActivity.class);
                        if (DeclarationAdapter.this.c instanceof AboardHmsDeclarationActivity) {
                            AboardHmsDeclarationActivity aboardHmsDeclarationActivity = (AboardHmsDeclarationActivity) DeclarationAdapter.this.c;
                            intent.putExtra("hms_auto_update", aboardHmsDeclarationActivity.d());
                            intent.putExtra("pairGuideSelectAddress", aboardHmsDeclarationActivity.b());
                            intent.putExtra("device_country_code", aboardHmsDeclarationActivity.a());
                            intent.putExtra("device_emui_version", aboardHmsDeclarationActivity.e());
                            intent.putExtra(PluginPayAdapter.KEY_DEVICE_INFO_NAME, aboardHmsDeclarationActivity.c());
                            intent.putExtra("device_version_type", DeclarationAdapter.this.g);
                        }
                        DeclarationAdapter.this.c.startActivity(intent);
                    } catch (ActivityNotFoundException e) {
                        LogUtil.b("DeclarationAdapter", "ActivityNotFoundException e:", e.getMessage());
                    }
                    ViewClickInstrumentation.clickOnView(view);
                }
            });
        }
    }

    private void c(c cVar, nyu nyuVar) {
        if (cVar.j == null) {
            LogUtil.h("DeclarationAdapter", " mToggleButton is null");
        } else {
            this.e = nyuVar;
            cVar.j.setOnCheckedChangeListener(this.i);
        }
    }

    private void a(c cVar, List<nzn> list, nyu nyuVar) {
        View cOS_;
        for (nzn nznVar : list) {
            if (!(nznVar instanceof nzl)) {
                if (nznVar instanceof nzk) {
                    if (nznVar instanceof nzm) {
                        cOS_ = cOW_((nzm) nznVar, cVar.f9045a, nyuVar);
                        HealthSwitchButton healthSwitchButton = (HealthSwitchButton) cOS_.findViewById(R.id.switch_button);
                        b(healthSwitchButton, nyuVar);
                        LogUtil.a("DeclarationAdapter", " enter addToggleButtonChangedListener");
                        cVar.j = healthSwitchButton;
                        c(cVar, nyuVar);
                    } else {
                        cOS_ = cOS_((nzk) nznVar, cVar.f9045a, nyuVar);
                    }
                    cVar.f9045a.addView(cOS_);
                } else if (!(nznVar instanceof nzo)) {
                    LogUtil.h("DeclarationAdapter", "setValue in for loop unknown part type");
                } else {
                    View cOV_ = cOV_((nzo) nznVar, cVar.f9045a);
                    cVar.f9045a.addView(cOV_);
                    cVar.c = (HealthTextView) cOV_.findViewById(R.id.text_view_switch);
                    b(cVar.c, nyuVar);
                    d(cVar.c);
                }
            } else {
                cVar.f9045a.addView(cOU_((nzl) nznVar, cVar.f9045a, nyuVar));
            }
        }
    }

    private void b(HealthSwitchButton healthSwitchButton, nyu nyuVar) {
        if (healthSwitchButton != null) {
            healthSwitchButton.setChecked(nyuVar.g());
        }
    }

    private void b(HealthTextView healthTextView, nyu nyuVar) {
        if (healthTextView != null) {
            healthTextView.setText(nuj.a(a(nyuVar.g() ? DeclarationConstants.SwitchFaceState.Enabled : DeclarationConstants.SwitchFaceState.Disabled)));
        }
    }

    private View cOV_(nzo nzoVar, ViewGroup viewGroup) {
        View inflate = LayoutInflater.from(this.c).inflate(R.layout.item_list_view_declaration_body_switch_face, viewGroup, false);
        HealthTextView healthTextView = (HealthTextView) inflate.findViewById(R.id.text_view_name);
        HealthTextView healthTextView2 = (HealthTextView) inflate.findViewById(R.id.text_view_description);
        ImageView imageView = (ImageView) inflate.findViewById(R.id.iv_next);
        Drawable drawable = this.c.getResources().getDrawable(R.drawable._2131427842_res_0x7f0b0202);
        if (drawable != null) {
            drawable.setAutoMirrored(true);
        }
        imageView.setBackgroundDrawable(drawable);
        healthTextView.setText(nzoVar.c());
        nzg d = nzoVar.d();
        ((HealthTextView) inflate.findViewById(R.id.text_view_switch)).setText(a(d.c()));
        if (TextUtils.isEmpty(d.b())) {
            healthTextView2.setVisibility(8);
        } else {
            healthTextView2.setVisibility(0);
            healthTextView2.setText(nuj.a(d.b()));
        }
        return inflate;
    }

    private String a(DeclarationConstants.SwitchFaceState switchFaceState) {
        int i;
        if (switchFaceState == DeclarationConstants.SwitchFaceState.Disabled) {
            i = R.string._2130844048_res_0x7f021990;
        } else if (switchFaceState == DeclarationConstants.SwitchFaceState.Enabled) {
            i = R.string._2130844049_res_0x7f021991;
        } else {
            LogUtil.h("DeclarationAdapter", "getSwitchText: unknown state");
            i = 0;
        }
        return i == 0 ? "" : this.c.getString(i);
    }

    private View cOS_(nzk nzkVar, ViewGroup viewGroup, nyu nyuVar) {
        LogUtil.a("DeclarationAdapter", "checkbox");
        View inflate = LayoutInflater.from(this.c).inflate(R.layout.item_list_view_declaration_body_check_box, viewGroup, false);
        ((HealthTextView) inflate.findViewById(R.id.text_view_name)).setText(nuj.a(nzkVar.b()));
        ((HealthTextView) inflate.findViewById(R.id.text_view_description)).setText(nuj.a(nzkVar.a()));
        HealthCheckBox healthCheckBox = (HealthCheckBox) inflate.findViewById(R.id.checkbox);
        if (nyuVar == null) {
            healthCheckBox.setChecked(nzkVar.d().c());
        } else {
            healthCheckBox.setChecked(nyuVar.g());
        }
        return inflate;
    }

    private View cOW_(nzm nzmVar, ViewGroup viewGroup, nyu nyuVar) {
        LogUtil.a("DeclarationAdapter", "ToggleButton");
        View inflate = LayoutInflater.from(this.c).inflate(R.layout.item_list_view_declaration_toggle, viewGroup, false);
        ((HealthTextView) inflate.findViewById(R.id.toggle_text_view_name)).setText(nzmVar.b());
        HealthTextView healthTextView = (HealthTextView) inflate.findViewById(R.id.toggle_text_view_description);
        List<nzr> e = nzmVar.e();
        String a2 = nzmVar.a();
        if (e != null && !e.isEmpty()) {
            healthTextView.setText(cOY_(e, a2, 0, nyuVar));
        } else {
            healthTextView.setText(a2);
        }
        return inflate;
    }

    private View cOU_(nzl nzlVar, ViewGroup viewGroup, nyu nyuVar) {
        View inflate = LayoutInflater.from(this.c).inflate(R.layout.item_list_view_declaration_body_paragraph, viewGroup, false);
        HealthTextView healthTextView = (HealthTextView) inflate.findViewById(R.id.text_view_content);
        if (nzlVar != null && nzlVar.b() != null) {
            String a2 = nuj.a(nzlVar.b().replace("\\n", System.lineSeparator()));
            List<nzr> e = nzlVar.e();
            if (e != null && !e.isEmpty()) {
                SpannableStringBuilder cOY_ = cOY_(e, a2, R.color.emui_accent, nyuVar);
                healthTextView.setMovementMethod(LinkMovementMethod.getInstance());
                healthTextView.setText(cOY_);
            } else {
                healthTextView.setText(a2);
            }
        }
        return inflate;
    }

    private SpannableStringBuilder cOY_(List<nzr> list, String str, final int i, final nyu nyuVar) {
        if (list == null || list.isEmpty()) {
            LogUtil.h("DeclarationAdapter", "parsePlaceHolder is null :", list);
            return new SpannableStringBuilder("");
        }
        int size = list.size();
        String[] strArr = new String[size];
        for (int i2 = 0; i2 < size; i2++) {
            strArr[i2] = list.get(i2).e();
        }
        String format = String.format(Locale.ENGLISH, str, strArr);
        LogUtil.a("DeclarationAdapter", "parsePlaceHolder value:", format);
        SpannableStringBuilder spannableStringBuilder = new SpannableStringBuilder(format);
        for (final nzr nzrVar : list) {
            String e = nzrVar.e();
            if (format.contains(e)) {
                spannableStringBuilder.setSpan(new ClickableSpan() { // from class: com.huawei.ui.device.activity.agreement.DeclarationAdapter.1
                    @Override // android.text.style.ClickableSpan, android.text.style.CharacterStyle
                    public void updateDrawState(TextPaint textPaint) {
                        super.updateDrawState(textPaint);
                        if (!c() || i == 0) {
                            textPaint.setColor(DeclarationAdapter.this.c.getResources().getColor(R.color._2131299236_res_0x7f090ba4));
                        } else {
                            textPaint.setColor(DeclarationAdapter.this.c.getResources().getColor(i));
                        }
                        textPaint.setUnderlineText(false);
                    }

                    @Override // android.text.style.ClickableSpan
                    public void onClick(View view) {
                        DeclarationAdapter.this.a(nzrVar, nyuVar);
                        ViewClickInstrumentation.clickOnView(view);
                    }

                    private boolean c() {
                        DeclarationConstants.PlaceholderType b = nzrVar.b();
                        return b == DeclarationConstants.PlaceholderType.URL || b == DeclarationConstants.PlaceholderType.JSON;
                    }
                }, format.indexOf(e), format.indexOf(e) + e.length(), 33);
            }
        }
        LogUtil.a("DeclarationAdapter", "getParagraphView stringBuilder.toString():", spannableStringBuilder.toString());
        return spannableStringBuilder;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a(nzr nzrVar, nyu nyuVar) {
        if (nzrVar instanceof nzt) {
            a((nzt) nzrVar, nyuVar);
        } else if (nzrVar instanceof nzp) {
            d((nzp) nzrVar);
        } else {
            LogUtil.h("DeclarationAdapter", "parsePlaceHolder#SpannableStringBuilder#onClick not supported placeholder.");
        }
    }

    private void a(final nzt nztVar, nyu nyuVar) {
        if (nztVar == null) {
            LogUtil.h("DeclarationAdapter", "handleUrl： urlPlaceholder is null.");
            return;
        }
        final String d = nztVar.d();
        if (TextUtils.isEmpty(d)) {
            LogUtil.h("DeclarationAdapter", "handleUrl：url is null or empty.");
            return;
        }
        int e = CommonUtil.e(nztVar.c(), -1);
        if (e == 1) {
            this.h = nyuVar.c();
        } else if (e == 2) {
            this.h = nyuVar.b();
        } else {
            this.h = "";
            LogUtil.h("DeclarationAdapter", "handleUrl：unknown flag: ", Integer.valueOf(e));
        }
        if (TextUtils.isEmpty(this.h)) {
            LogUtil.h("DeclarationAdapter", "handleUrl：version is null or empty.");
        } else {
            jdx.b(new Runnable() { // from class: com.huawei.ui.device.activity.agreement.DeclarationAdapter.3
                @Override // java.lang.Runnable
                public void run() {
                    String d2 = DeclarationAdapter.this.d();
                    String a2 = nztVar.a();
                    String format = String.format(Locale.ENGLISH, "country=%1$s&branchid=%2$s&version=%3$s&language=%4$s", DeclarationAdapter.this.d, a2, DeclarationAdapter.this.h, d2);
                    if (DeclarationAdapter.this.g == 99 && d.toLowerCase().contains("code")) {
                        format = String.format(Locale.ENGLISH, "branchid=%1$s&version=%2$s&language=%3$s", a2, DeclarationAdapter.this.h, d2);
                    }
                    nuj.a(DeclarationAdapter.this.c, d + format);
                }
            });
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public String d() {
        Locale locale = Locale.getDefault();
        String script = locale.getScript();
        String language = locale.getLanguage();
        if (!TextUtils.isEmpty(script)) {
            language = language + Constants.LINK + script;
        }
        return language + Constants.LINK + locale.getCountry();
    }

    private void d(nzp nzpVar) {
        if (nzpVar == null) {
            LogUtil.h("DeclarationAdapter", "handleJson： jsonPlaceholder is null.");
            return;
        }
        nzb d = nzpVar.d();
        LogUtil.a("DeclarationAdapter", "handleJson：", d);
        d(d);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public int a() {
        Resources resources = BaseApplication.getContext().getResources();
        if (resources != null) {
            return resources.getDimensionPixelSize(resources.getIdentifier("status_bar_height", "dimen", OsType.ANDROID));
        }
        return 0;
    }

    private void d(nzb nzbVar) {
        if (nzbVar == null || nzbVar.d() == null) {
            LogUtil.h("DeclarationAdapter", "showPermissionsDialog： permissionsNote note is null or permission list is null");
            return;
        }
        final CustomAlertDialog e = e();
        ((HealthTextView) e.findViewById(R.id.text_view_title)).setText(nuj.a(nzbVar.e()));
        ((HealthTextView) e.findViewById(R.id.text_view_description)).setText(nuj.a(nzbVar.a()));
        ((ListView) e.findViewById(R.id.list_view_content)).setAdapter((ListAdapter) new PermissionsDialogAdapter(this.c, nzbVar.d()));
        e.show();
        if (this.c instanceof EnhancementImprovementActivity) {
            ThreadPoolManager.d().execute(new Runnable() { // from class: com.huawei.ui.device.activity.agreement.DeclarationAdapter.8
                @Override // java.lang.Runnable
                public void run() {
                    try {
                        Thread.sleep(100L);
                        EnhancementImprovementActivity enhancementImprovementActivity = (EnhancementImprovementActivity) DeclarationAdapter.this.c;
                        final LinearLayout linearLayout = (LinearLayout) e.findViewById(R.id.permission_note_layout);
                        DisplayMetrics displayMetrics = new DisplayMetrics();
                        enhancementImprovementActivity.getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
                        final int a2 = (displayMetrics.heightPixels - DeclarationAdapter.this.a()) - nsn.c(BaseApplication.getContext(), 64.0f);
                        int measuredHeight = linearLayout.getMeasuredHeight();
                        LogUtil.a("DeclarationAdapter", "measureHeight：", Integer.valueOf(measuredHeight), "endHeight:", Integer.valueOf(a2));
                        if (measuredHeight > a2) {
                            enhancementImprovementActivity.runOnUiThread(new Runnable() { // from class: com.huawei.ui.device.activity.agreement.DeclarationAdapter.8.5
                                @Override // java.lang.Runnable
                                public void run() {
                                    ViewGroup.LayoutParams layoutParams = linearLayout.getLayoutParams();
                                    layoutParams.height = a2;
                                    linearLayout.setLayoutParams(layoutParams);
                                }
                            });
                        }
                    } catch (InterruptedException unused) {
                        LogUtil.b("DeclarationAdapter", "InterruptedException");
                    }
                }
            });
        }
    }

    private CustomAlertDialog e() {
        CustomAlertDialog customAlertDialog = this.f;
        if (customAlertDialog != null) {
            if (customAlertDialog.isShowing()) {
                this.f.dismiss();
            }
            return this.f;
        }
        CustomAlertDialog c2 = new CustomAlertDialog.Builder(this.c).cyp_(View.inflate(this.c, R.layout.dialog_layout_declaration_permissions_note, null)).cyn_(R.string._2130843954_res_0x7f021932, new DialogInterface.OnClickListener() { // from class: com.huawei.ui.device.activity.agreement.DeclarationAdapter.9
            @Override // android.content.DialogInterface.OnClickListener
            public void onClick(DialogInterface dialogInterface, int i) {
                DeclarationAdapter.this.c();
                ViewClickInstrumentation.clickOnDialog(dialogInterface, i);
            }
        }).c();
        this.f = c2;
        return c2;
    }

    public void c() {
        CustomAlertDialog customAlertDialog = this.f;
        if (customAlertDialog == null) {
            LogUtil.h("DeclarationAdapter", "dismissPermissionDialog: permission dialog is null.");
            return;
        }
        if (customAlertDialog.isShowing()) {
            this.f.dismiss();
        }
        this.f = null;
    }

    public static class PermissionsDialogAdapter extends BaseAdapter {
        private Context d;
        private List<nzb.b> e;

        @Override // android.widget.Adapter
        public long getItemId(int i) {
            return i;
        }

        public PermissionsDialogAdapter(Context context, List<nzb.b> list) {
            this.d = context;
            this.e = list;
        }

        @Override // android.widget.Adapter
        public int getCount() {
            List<nzb.b> list = this.e;
            if (list == null) {
                return 0;
            }
            return list.size();
        }

        @Override // android.widget.Adapter
        /* renamed from: e, reason: merged with bridge method [inline-methods] */
        public nzb.b getItem(int i) {
            List<nzb.b> list = this.e;
            if (list == null) {
                LogUtil.h("DeclarationAdapter", "mPermissions is null.");
                return new nzb.b();
            }
            if (i < 0 || i >= list.size()) {
                LogUtil.a("DeclarationAdapter", "invalid index of mPermissions.");
                return new nzb.b();
            }
            return this.e.get(i);
        }

        /* JADX WARN: Multi-variable type inference failed */
        @Override // android.widget.Adapter
        public View getView(int i, View view, ViewGroup viewGroup) {
            d dVar = null;
            Object[] objArr = 0;
            if (view == null) {
                view = LayoutInflater.from(this.d).inflate(R.layout.item_list_view_dialog_layout_declaration_permissions_note, viewGroup, false);
                d dVar2 = new d(view);
                view.setTag(dVar2);
                dVar = dVar2;
            } else {
                Object tag = view.getTag();
                if (tag instanceof d) {
                    dVar = (d) tag;
                }
            }
            if (dVar == null) {
                LogUtil.h("DeclarationAdapter", "PermissionsDialogAdapter#getView: viewHolder is null.");
                return new View(this.d);
            }
            List<nzb.b> list = this.e;
            if (list == null) {
                LogUtil.h("DeclarationAdapter", "PermissionsDialogAdapter#getView: mPermissions is null.");
                return new View(this.d);
            }
            if (i < 0 || i >= list.size()) {
                LogUtil.a("DeclarationAdapter", "PermissionsDialogAdapter#getView: invalid index of mPermissions.");
                return new View(this.d);
            }
            nzb.b bVar = this.e.get(i);
            if (bVar == null) {
                return new View(this.d);
            }
            dVar.b.setText(nuj.a(bVar.a()));
            dVar.c.setText(nuj.a(bVar.c()));
            return view;
        }

        static class d {
            private HealthTextView b;
            private HealthTextView c;

            private d(View view) {
                this.b = (HealthTextView) view.findViewById(R.id.text_view_sub_title);
                this.c = (HealthTextView) view.findViewById(R.id.text_view_sub_description);
            }
        }
    }

    static class c {

        /* renamed from: a, reason: collision with root package name */
        private LinearLayout f9045a;
        private HealthCheckBox b;
        private HealthTextView c;
        private boolean d;
        private HealthTextView e;
        private ViewStub g;
        private ViewStub i;
        private HealthSwitchButton j;

        c(View view) {
            if (view == null) {
                LogUtil.h("DeclarationAdapter", "BodyViewHolder view is null");
                return;
            }
            this.g = (ViewStub) view.findViewById(R.id.view_stub_start);
            this.e = (HealthTextView) view.findViewById(R.id.text_view_title);
            this.i = (ViewStub) view.findViewById(R.id.view_stub_end);
            this.f9045a = (LinearLayout) view.findViewById(R.id.ll_content);
            this.j = (HealthSwitchButton) view.findViewById(R.id.switch_button);
        }
    }
}
