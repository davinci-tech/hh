package defpackage;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;
import com.huawei.haf.bundle.AppBundle;
import com.huawei.health.R;
import com.huawei.openalliance.ad.constant.Constants;
import com.huawei.ui.commonui.dialog.CustomViewDialog;
import com.huawei.ui.commonui.healthtextview.HealthTextView;
import health.compact.a.LanguageUtil;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

/* loaded from: classes.dex */
public final class cco {
    private final List<String> b;
    private final Context c;
    private final long e;

    public cco(Context context, List<String> list, long j) {
        this.c = context;
        this.b = list;
        this.e = j;
    }

    public void Da_(View.OnClickListener onClickListener, View.OnClickListener onClickListener2) {
        boolean c = c();
        ccl cclVar = new ccl(onClickListener);
        ccl cclVar2 = new ccl(onClickListener2);
        CustomViewDialog.Builder builder = new CustomViewDialog.Builder(this.c);
        builder.a(this.c.getString(R.string.IDS_service_area_notice_title));
        builder.czg_(CZ_(c));
        builder.czc_(R.string._2130841130_res_0x7f020e2a, cclVar);
        builder.cze_(c ? R.string._2130844008_res_0x7f021968 : R.string.IDS_bundle_download_button, cclVar2);
        CustomViewDialog e2 = builder.e();
        e2.setCancelable(false);
        e2.setOnDismissListener(cclVar);
        cclVar2.c(cclVar);
        e2.show();
    }

    private View CZ_(boolean z) {
        View inflate = LayoutInflater.from(this.c).inflate(R.layout.activity_bundle_ask_dialog, (ViewGroup) null);
        CX_(inflate, z);
        CY_(inflate);
        return inflate;
    }

    private void CX_(View view, boolean z) {
        HealthTextView healthTextView = (HealthTextView) view.findViewById(R.id.bundle_ask_dialog_content);
        int i = z ? R.string._2130844002_res_0x7f021962 : R.string._2130844001_res_0x7f021961;
        String b = nsn.b(this.c, this.e);
        if (LanguageUtil.bc(this.c)) {
            b = Constants.LRM_STR + b;
        }
        healthTextView.setText(this.c.getString(i, b));
    }

    private void CY_(View view) {
        ((ListView) view.findViewById(R.id.bundle_ask_dialog_list)).setAdapter((ListAdapter) new e(this.c, R.layout.activity_bundle_ask_dialog_list_item, R.id.bundle_dialog_list_item, this.b));
    }

    private boolean c() {
        Set<String> updateModules = AppBundle.c().getUpdateModules(this.c, true);
        Iterator<String> it = this.b.iterator();
        while (it.hasNext()) {
            if (updateModules.contains(it.next())) {
                return true;
            }
        }
        return false;
    }

    /* loaded from: classes8.dex */
    static class e extends ArrayAdapter<String> {
        @Override // android.widget.BaseAdapter, android.widget.ListAdapter
        public boolean areAllItemsEnabled() {
            return false;
        }

        @Override // android.widget.BaseAdapter, android.widget.ListAdapter
        public boolean isEnabled(int i) {
            return false;
        }

        e(Context context, int i, int i2, List<String> list) {
            super(context, i, i2, list);
        }

        @Override // android.widget.ArrayAdapter, android.widget.Adapter
        /* renamed from: b, reason: merged with bridge method [inline-methods] */
        public String getItem(int i) {
            return AppBundle.c().getModuleTitle(getContext(), (String) super.getItem(i));
        }
    }
}
