package com.huawei.featureuserprofile.sos.fragment;

import android.app.Dialog;
import android.os.Bundle;
import android.preference.Preference;
import android.preference.PreferenceCategory;
import android.preference.PreferenceFragment;
import android.preference.PreferenceScreen;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import com.huawei.featureuserprofile.sos.interf.OnPreferenceChangedListener;
import com.huawei.featureuserprofile.sos.interf.ReloadablePreferenceInterface;
import com.huawei.featureuserprofile.sos.manager.EmergencyInfoManager;
import com.huawei.featureuserprofile.sos.preference.EmergencyEditTextPreference;
import com.huawei.featureuserprofile.sos.preference.EmergencyEditTextTipPreference;
import com.huawei.featureuserprofile.sos.preference.EmergencyListPreference;
import com.huawei.health.R;
import com.huawei.ui.commonui.listviewforscroll.ListViewForScroll;
import defpackage.btz;
import defpackage.gmw;
import health.compact.a.util.LogUtil;

/* loaded from: classes7.dex */
public class EditEmergencyInfoFragment extends PreferenceFragment implements OnPreferenceChangedListener {
    private Window b = null;
    private Preference e = null;

    /* renamed from: a, reason: collision with root package name */
    private PreferenceCategory f2026a = null;

    @Override // android.preference.PreferenceFragment, android.app.Fragment
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
    }

    @Override // android.preference.PreferenceFragment, android.app.Fragment
    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        View inflate = layoutInflater.inflate(R.layout.fragment_editemergencyinfo, (ViewGroup) null);
        ListViewForScroll listViewForScroll = (ListViewForScroll) inflate.findViewById(android.R.id.list);
        if (listViewForScroll != null) {
            listViewForScroll.setDivider(getResources().getDrawable(R.drawable._2131430789_res_0x7f0b0d85));
            listViewForScroll.setDividerHeight(1);
            listViewForScroll.setFooterDividersEnabled(false);
            listViewForScroll.setOverscrollFooter(getResources().getDrawable(R.color._2131299296_res_0x7f090be0));
            listViewForScroll.setHeaderDividersEnabled(false);
            listViewForScroll.setOverscrollHeader(getResources().getDrawable(R.color._2131299296_res_0x7f090be0));
        }
        addPreferencesFromResource(R.xml._2132082692_res_0x7f150004);
        PreferenceCategory preferenceCategory = findPreference("title_personal") instanceof PreferenceCategory ? (PreferenceCategory) findPreference("title_personal") : null;
        if (preferenceCategory != null) {
            preferenceCategory.setLayoutResource(R.layout.custom_preference_category_no_divider);
        }
        if (findPreference("title_emergency_contact") instanceof PreferenceCategory) {
            this.f2026a = (PreferenceCategory) findPreference("title_emergency_contact");
        }
        this.e = findPreference("entry_contact_edit");
        return inflate;
    }

    @Override // android.preference.PreferenceFragment, android.app.Fragment
    public void onViewCreated(View view, Bundle bundle) {
        super.onViewCreated(view, bundle);
        e();
        for (String str : gmw.d()) {
            a(str);
        }
        this.f2026a.removePreference(this.e);
        this.f2026a.addPreference(this.e);
    }

    @Override // android.preference.PreferenceFragment
    public boolean onPreferenceTreeClick(PreferenceScreen preferenceScreen, Preference preference) {
        if (btz.c(getActivity())) {
            uW_(preference);
        }
        return super.onPreferenceTreeClick(preferenceScreen, preference);
    }

    public void uW_(Preference preference) {
        Dialog dialog;
        if (preference instanceof EmergencyEditTextPreference) {
            dialog = ((EmergencyEditTextPreference) preference).getDialog();
        } else if (preference instanceof EmergencyEditTextTipPreference) {
            dialog = ((EmergencyEditTextTipPreference) preference).getDialog();
        } else if (preference instanceof EmergencyListPreference) {
            dialog = ((EmergencyListPreference) preference).getDialog();
        } else {
            LogUtil.c("EditEmergencyInfoFragment", "setDialogHideNavigation preference is not definition");
            dialog = null;
        }
        if (dialog == null) {
            LogUtil.c("EditEmergencyInfoFragment", "getDialog before dialog init done");
            return;
        }
        Window window = dialog.getWindow();
        this.b = window;
        if (window != null) {
            this.b.getDecorView().setSystemUiVisibility(window.getDecorView().getSystemUiVisibility() | 5890);
            this.b.getDecorView().setOnSystemUiVisibilityChangeListener(new View.OnSystemUiVisibilityChangeListener() { // from class: com.huawei.featureuserprofile.sos.fragment.EditEmergencyInfoFragment.5
                @Override // android.view.View.OnSystemUiVisibilityChangeListener
                public void onSystemUiVisibilityChange(int i) {
                    if (i != 5890) {
                        EditEmergencyInfoFragment.this.b.getDecorView().setSystemUiVisibility(i | 5890);
                    }
                }
            });
        }
    }

    public void e() {
        for (String str : gmw.d()) {
            ReloadablePreferenceInterface reloadablePreferenceInterface = findPreference(str) instanceof ReloadablePreferenceInterface ? (ReloadablePreferenceInterface) findPreference(str) : null;
            if (reloadablePreferenceInterface != null) {
                reloadablePreferenceInterface.reloadFromPreference();
            }
        }
    }

    @Override // android.preference.PreferenceFragment, android.app.Fragment
    public void onActivityCreated(Bundle bundle) {
        super.onActivityCreated(bundle);
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    private void a(String str) {
        char c;
        Preference findPreference = findPreference(str);
        if (findPreference == null) {
            LogUtil.c("EditEmergencyInfoFragment", "addPreferenceChangedListener preference is null. key is ", str);
        }
        str.hashCode();
        switch (str.hashCode()) {
            case -1148703137:
                if (str.equals("blood_type")) {
                    c = 0;
                    break;
                }
                c = 65535;
                break;
            case -1147692044:
                if (str.equals("address")) {
                    c = 1;
                    break;
                }
                c = 65535;
                break;
            case -1099451720:
                if (str.equals("organ_donor")) {
                    c = 2;
                    break;
                }
                c = 65535;
                break;
            case 3373707:
                if (str.equals("name")) {
                    c = 3;
                    break;
                }
                c = 65535;
                break;
            case 81679390:
                if (str.equals("allergies")) {
                    c = 4;
                    break;
                }
                c = 65535;
                break;
            case 1838387076:
                if (str.equals("medications")) {
                    c = 5;
                    break;
                }
                c = 65535;
                break;
            case 2111429926:
                if (str.equals("medical_conditions")) {
                    c = 6;
                    break;
                }
                c = 65535;
                break;
            default:
                c = 65535;
                break;
        }
        switch (c) {
            case 0:
            case 2:
                if (findPreference instanceof EmergencyListPreference) {
                    ((EmergencyListPreference) findPreference).d(this);
                    break;
                }
                break;
            case 1:
            case 4:
            case 5:
            case 6:
                if (findPreference instanceof EmergencyEditTextTipPreference) {
                    ((EmergencyEditTextTipPreference) findPreference).a(this);
                    break;
                }
                break;
            case 3:
                if (findPreference instanceof EmergencyEditTextPreference) {
                    ((EmergencyEditTextPreference) findPreference).a(this);
                    break;
                }
                break;
            default:
                LogUtil.c("EditEmergencyInfoFragment", "There is no this preference.");
                break;
        }
    }

    @Override // com.huawei.featureuserprofile.sos.interf.OnPreferenceChangedListener
    public void onPreferenceChanged(String str, String str2) {
        LogUtil.d("EditEmergencyInfoFragment", "onPreferenceChanged preferenceKey ", str);
        EmergencyInfoManager.c().a(str, str2);
    }
}
