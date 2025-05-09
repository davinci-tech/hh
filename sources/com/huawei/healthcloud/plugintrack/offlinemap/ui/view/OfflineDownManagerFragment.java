package com.huawei.healthcloud.plugintrack.offlinemap.ui.view;

import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.ListAdapter;
import android.widget.ListView;
import androidx.fragment.app.FragmentActivity;
import com.amap.api.maps.offlinemap.OfflineMapCity;
import com.huawei.health.R;
import com.huawei.healthcloud.plugintrack.offlinemap.adapter.OfflineMapSomeCitiesAdapter;
import com.huawei.healthcloud.plugintrack.offlinemap.ui.activity.OfflineMapTabActivity;
import com.huawei.hianalytics.visual.autocollect.instrument.ViewClickInstrumentation;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.ui.commonui.base.BaseActivity;
import com.huawei.ui.commonui.base.BaseFragment;
import com.huawei.ui.commonui.dialog.CustomAlertDialog;
import com.huawei.ui.commonui.dialog.CustomViewDialog;
import com.huawei.ui.commonui.divider.HealthDivider;
import com.huawei.ui.commonui.healthtextview.HealthTextView;
import defpackage.gyf;
import defpackage.koq;
import java.util.ArrayList;

/* loaded from: classes4.dex */
public class OfflineDownManagerFragment extends BaseFragment {

    /* renamed from: a, reason: collision with root package name */
    private LinearLayout f3545a;
    private OfflineMapSomeCitiesAdapter c;
    private OfflineListView e;
    private LinearLayout f;
    private LinearLayout g;
    private OfflineMapSomeCitiesAdapter h;
    private OfflineListView i;
    private ArrayList<OfflineMapCity> b = new ArrayList<>(10);
    private ArrayList<OfflineMapCity> d = new ArrayList<>(10);

    enum EnableShowType {
        DOWNLOADING_CITY_LIST,
        COMPLETE_CITY_LIST,
        ALL_CITY_LIST,
        NO_LIST,
        NULL_DATA
    }

    @Override // androidx.fragment.app.Fragment
    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        LogUtil.a("OfflineDownManagerFragment", "onCreateView()");
        if (layoutInflater == null) {
            return null;
        }
        View inflate = layoutInflater.inflate(R.layout.track_offlinemap_down_fragment, viewGroup, false);
        this.e = (OfflineListView) inflate.findViewById(R.id.list_load_ok);
        this.i = (OfflineListView) inflate.findViewById(R.id.list_loading);
        this.f3545a = (LinearLayout) inflate.findViewById(R.id.ll_load_ok);
        this.g = (LinearLayout) inflate.findViewById(R.id.ll_loading);
        this.f = (LinearLayout) inflate.findViewById(R.id.ll_warn);
        return inflate;
    }

    @Override // com.huawei.ui.commonui.base.BaseFragment, androidx.fragment.app.Fragment
    public void onViewCreated(View view, Bundle bundle) {
        LogUtil.a("OfflineDownManagerFragment", "onViewCreated()");
        super.onViewCreated(view, bundle);
        a();
    }

    private void a() {
        LogUtil.a("OfflineDownManagerFragment", "initView()");
        OfflineMapSomeCitiesAdapter offlineMapSomeCitiesAdapter = new OfflineMapSomeCitiesAdapter(getActivity(), this.b);
        this.h = offlineMapSomeCitiesAdapter;
        this.i.setAdapter((ListAdapter) offlineMapSomeCitiesAdapter);
        OfflineMapSomeCitiesAdapter offlineMapSomeCitiesAdapter2 = new OfflineMapSomeCitiesAdapter(getActivity(), this.d);
        this.c = offlineMapSomeCitiesAdapter2;
        this.e.setAdapter((ListAdapter) offlineMapSomeCitiesAdapter2);
        BaseActivity.cancelLayoutById(this.i, this.e);
        BaseActivity.setViewSafeRegion(false, this.i, this.e);
        this.e.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() { // from class: com.huawei.healthcloud.plugintrack.offlinemap.ui.view.OfflineDownManagerFragment.3
            @Override // android.widget.AdapterView.OnItemLongClickListener
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long j) {
                OfflineDownManagerFragment.this.e(i);
                return true;
            }
        });
        this.i.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() { // from class: com.huawei.healthcloud.plugintrack.offlinemap.ui.view.OfflineDownManagerFragment.2
            @Override // android.widget.AdapterView.OnItemLongClickListener
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long j) {
                OfflineDownManagerFragment.this.d(i);
                return true;
            }
        });
        c(EnableShowType.NULL_DATA);
    }

    private void a(ArrayList<OfflineMapCity> arrayList, ArrayList<OfflineMapCity> arrayList2) {
        LogUtil.a("OfflineDownManagerFragment", "processShowListEnable");
        if (arrayList == null || arrayList2 == null) {
            LogUtil.a("OfflineDownManagerFragment", "processShowListEnable loadingCityList or downCityList is null");
            return;
        }
        if (arrayList.size() <= 0) {
            if (arrayList2.size() <= 0) {
                c(EnableShowType.NO_LIST);
                return;
            } else {
                c(EnableShowType.COMPLETE_CITY_LIST);
                return;
            }
        }
        if (arrayList2.size() <= 0) {
            c(EnableShowType.DOWNLOADING_CITY_LIST);
        } else {
            c(EnableShowType.ALL_CITY_LIST);
        }
    }

    private void c(EnableShowType enableShowType) {
        LogUtil.a("OfflineDownManagerFragment", "enableShowList type : ", enableShowType);
        if (this.g == null || this.f3545a == null || this.f == null) {
            LogUtil.b("OfflineDownManagerFragment", "mLoadingLayout == null || mDownLayout == null || mWarnTextView == null");
            return;
        }
        int i = AnonymousClass6.f3547a[enableShowType.ordinal()];
        if (i == 1) {
            this.g.setVisibility(0);
            this.f3545a.setVisibility(8);
            this.f.setVisibility(8);
            return;
        }
        if (i == 2) {
            this.g.setVisibility(8);
            this.f3545a.setVisibility(0);
            this.f.setVisibility(8);
            return;
        }
        if (i == 3) {
            this.g.setVisibility(0);
            this.f3545a.setVisibility(0);
            this.f.setVisibility(8);
        } else if (i == 4) {
            this.g.setVisibility(8);
            this.f3545a.setVisibility(8);
            this.f.setVisibility(0);
        } else {
            if (i != 5) {
                return;
            }
            this.f.setVisibility(8);
            this.f3545a.setVisibility(8);
            this.g.setVisibility(8);
        }
    }

    /* renamed from: com.huawei.healthcloud.plugintrack.offlinemap.ui.view.OfflineDownManagerFragment$6, reason: invalid class name */
    static /* synthetic */ class AnonymousClass6 {

        /* renamed from: a, reason: collision with root package name */
        static final /* synthetic */ int[] f3547a;

        static {
            int[] iArr = new int[EnableShowType.values().length];
            f3547a = iArr;
            try {
                iArr[EnableShowType.DOWNLOADING_CITY_LIST.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                f3547a[EnableShowType.COMPLETE_CITY_LIST.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                f3547a[EnableShowType.ALL_CITY_LIST.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                f3547a[EnableShowType.NO_LIST.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
            try {
                f3547a[EnableShowType.NULL_DATA.ordinal()] = 5;
            } catch (NoSuchFieldError unused5) {
            }
        }
    }

    public void b(ArrayList<OfflineMapCity> arrayList, ArrayList<OfflineMapCity> arrayList2) {
        LogUtil.a("OfflineDownManagerFragment", "mDownCityList size", Integer.valueOf(this.d.size()), "mLoadingCityList size", Integer.valueOf(this.b.size()));
        this.b = arrayList;
        this.d = arrayList2;
        c(arrayList);
        e(arrayList2);
        a(arrayList, arrayList2);
    }

    private void c(ArrayList<OfflineMapCity> arrayList) {
        OfflineMapSomeCitiesAdapter offlineMapSomeCitiesAdapter = this.h;
        if (offlineMapSomeCitiesAdapter == null) {
            LogUtil.b("OfflineDownManagerFragment", "mLoadingListAdapter  == null");
        } else {
            offlineMapSomeCitiesAdapter.e(arrayList);
            this.h.notifyDataSetChanged();
        }
    }

    private void e(ArrayList<OfflineMapCity> arrayList) {
        OfflineMapSomeCitiesAdapter offlineMapSomeCitiesAdapter = this.c;
        if (offlineMapSomeCitiesAdapter == null) {
            LogUtil.b("OfflineDownManagerFragment", "mDownListAdapter == null");
        } else {
            offlineMapSomeCitiesAdapter.e(arrayList);
            this.c.notifyDataSetChanged();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void e(int i) {
        LogUtil.a("OfflineDownManagerFragment", "showDeleteConfirmDialog() position:", Integer.valueOf(i));
        if (koq.b(this.d, i)) {
            return;
        }
        e(this.d.get(i).getCity());
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void d(int i) {
        LogUtil.a("OfflineDownManagerFragment", "showDeleteLoadingConfirmDialog() position:", Integer.valueOf(i));
        if (koq.b(this.b, i)) {
            return;
        }
        OfflineMapCity offlineMapCity = this.b.get(i);
        String city = offlineMapCity.getCity();
        int state = offlineMapCity.getState();
        LogUtil.a("OfflineDownManagerFragment", "showDeleteLoadingConfirmDialog state:", Integer.valueOf(state));
        if (state == 0) {
            b(city, "", 100);
            return;
        }
        if (state != 1) {
            if (state == 2) {
                b(city, getString(R.string._2130839735_res_0x7f0208b7), 102);
                return;
            }
            if (state == 3 || state == 5 || state == 6 || state == 101) {
                b(city, getString(R.string._2130839735_res_0x7f0208b7), 101);
            } else {
                e(city);
            }
        }
    }

    private void e(final String str) {
        LogUtil.a("OfflineDownManagerFragment", "showDeleteCityDialog()");
        String format = String.format(getString(R.string._2130839747_res_0x7f0208c3), str);
        final FragmentActivity activity = getActivity();
        if (!(activity instanceof OfflineMapTabActivity)) {
            LogUtil.a("OfflineDownManagerFragment", "showDeleteCityDialog factivity != OfflineMapTabActivity");
        } else {
            new CustomAlertDialog.Builder(activity).e(R.string._2130839727_res_0x7f0208af).c(format).cyn_(R.string._2130839728_res_0x7f0208b0, new DialogInterface.OnClickListener() { // from class: com.huawei.healthcloud.plugintrack.offlinemap.ui.view.OfflineDownManagerFragment.4
                @Override // android.content.DialogInterface.OnClickListener
                public void onClick(DialogInterface dialogInterface, int i) {
                    if (dialogInterface != null) {
                        dialogInterface.dismiss();
                    }
                    ViewClickInstrumentation.clickOnDialog(dialogInterface, i);
                }
            }).cyo_(R.string._2130841397_res_0x7f020f35, new DialogInterface.OnClickListener() { // from class: com.huawei.healthcloud.plugintrack.offlinemap.ui.view.OfflineDownManagerFragment.1
                @Override // android.content.DialogInterface.OnClickListener
                public void onClick(DialogInterface dialogInterface, int i) {
                    gyf.b(activity).d(str, false);
                    ViewClickInstrumentation.clickOnDialog(dialogInterface, i);
                }
            }).c().show();
        }
    }

    private void b(String str, String str2, int i) {
        String[] strArr;
        CustomViewDialog e2 = new CustomViewDialog.Builder(getActivity()).e();
        if (TextUtils.isEmpty(str2)) {
            strArr = new String[]{getString(R.string._2130841397_res_0x7f020f35)};
        } else {
            strArr = new String[]{str2, getString(R.string._2130841397_res_0x7f020f35)};
        }
        e eVar = new e(strArr, getActivity());
        View inflate = LayoutInflater.from(getActivity()).inflate(R.layout.track_offlinemap_common_ui_dialog_listview, (ViewGroup) null, false);
        ListView listView = (ListView) inflate.findViewById(R.id.listView);
        listView.setAdapter((ListAdapter) eVar);
        aWF_(listView);
        aWE_(str, i, e2, listView);
        e2.setContentView(inflate);
        e2.show();
    }

    private void aWE_(final String str, final int i, final CustomViewDialog customViewDialog, ListView listView) {
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() { // from class: com.huawei.healthcloud.plugintrack.offlinemap.ui.view.OfflineDownManagerFragment.5
            @Override // android.widget.AdapterView.OnItemClickListener
            public void onItemClick(AdapterView<?> adapterView, View view, int i2, long j) {
                FragmentActivity activity = OfflineDownManagerFragment.this.getActivity();
                if (!(activity instanceof OfflineMapTabActivity)) {
                    ViewClickInstrumentation.clickOnListView(adapterView, view, i2);
                    return;
                }
                LogUtil.a("OfflineDownManagerFragment", "showDoubleDeleteDialog onItemClick() position:", Integer.valueOf(i2));
                gyf b = gyf.b(activity);
                if (i2 == 0) {
                    int i3 = i;
                    if (i3 == 100) {
                        b.d(str, false);
                    } else if (i3 == 101) {
                        b.c(str, false);
                    } else if (i3 == 102) {
                        b.d(str);
                    }
                    OfflineDownManagerFragment.this.c(customViewDialog);
                } else if (i2 == 1) {
                    if (i == 100) {
                        b.d(str, true);
                    } else {
                        b.d(str, false);
                    }
                    OfflineDownManagerFragment.this.c(customViewDialog);
                }
                ViewClickInstrumentation.clickOnListView(adapterView, view, i2);
            }
        });
    }

    private void aWF_(ListView listView) {
        listView.setOnTouchListener(new View.OnTouchListener() { // from class: com.huawei.healthcloud.plugintrack.offlinemap.ui.view.OfflineDownManagerFragment.8
            @Override // android.view.View.OnTouchListener
            public boolean onTouch(View view, MotionEvent motionEvent) {
                if (motionEvent != null) {
                    return motionEvent.getAction() == 2;
                }
                LogUtil.h("OfflineDownManagerFragment", "setListViewOnTouchListener onTouch event is equals null");
                return false;
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void c(CustomViewDialog customViewDialog) {
        if (customViewDialog != null) {
            customViewDialog.dismiss();
        }
    }

    static class e extends BaseAdapter {

        /* renamed from: a, reason: collision with root package name */
        private String[] f3549a;
        private Context b;

        @Override // android.widget.Adapter
        public long getItemId(int i) {
            return i;
        }

        public e(String[] strArr, Context context) {
            if (strArr != null) {
                this.f3549a = (String[]) strArr.clone();
            }
            this.b = context;
        }

        @Override // android.widget.Adapter
        public int getCount() {
            String[] strArr = this.f3549a;
            if (strArr == null) {
                LogUtil.h("OfflineDownManagerFragment", "mType is null");
                return 0;
            }
            return strArr.length;
        }

        @Override // android.widget.Adapter
        public Object getItem(int i) {
            String[] strArr = this.f3549a;
            if (strArr == null) {
                LogUtil.h("OfflineDownManagerFragment", "mItemList is null");
                return null;
            }
            if (i < 0 || i >= strArr.length) {
                return null;
            }
            return strArr[i];
        }

        @Override // android.widget.Adapter
        public View getView(int i, View view, ViewGroup viewGroup) {
            a aVar;
            if (view == null) {
                view = View.inflate(this.b, R.layout.offline_map_list_item_single_choice, null);
                aVar = new a();
                aVar.e = (HealthTextView) view.findViewById(R.id.text1);
                aVar.b = (HealthDivider) view.findViewById(R.id.divide_line);
                view.setTag(aVar);
            } else if (view.getTag() instanceof a) {
                aVar = (a) view.getTag();
            } else {
                LogUtil.a("OfflineDownManagerFragment", "!view.getTag() instanceof ViewHolder");
                return view;
            }
            String[] strArr = this.f3549a;
            if (strArr == null) {
                LogUtil.h("OfflineDownManagerFragment", "mType is null");
                return view;
            }
            if (i >= 0 && i < strArr.length) {
                if (i == strArr.length - 1 && aVar.b != null) {
                    aVar.b.setVisibility(8);
                }
                if (aVar.e != null) {
                    aVar.e.setText(this.f3549a[i]);
                }
            }
            return view;
        }
    }

    static class a {
        HealthDivider b;
        HealthTextView e;

        a() {
        }
    }
}
