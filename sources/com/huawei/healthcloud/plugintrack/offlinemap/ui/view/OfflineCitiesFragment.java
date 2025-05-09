package com.huawei.healthcloud.plugintrack.offlinemap.ui.view;

import android.os.Bundle;
import android.os.Message;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.ExpandableListView;
import android.widget.ListAdapter;
import android.widget.ListView;
import androidx.appcompat.widget.SearchView;
import com.amap.api.maps.offlinemap.OfflineMapCity;
import com.amap.api.maps.offlinemap.OfflineMapProvince;
import com.huawei.haf.handler.BaseHandler;
import com.huawei.health.R;
import com.huawei.healthcloud.plugintrack.offlinemap.adapter.OfflineMapAllCitiesAdapter;
import com.huawei.healthcloud.plugintrack.offlinemap.adapter.OfflineMapSomeCitiesAdapter;
import com.huawei.healthcloud.plugintrack.offlinemap.manager.OfflineMapSearchThreadManager;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.ui.commonui.base.BaseFragment;
import com.huawei.ui.commonui.healthtextview.HealthTextView;
import com.huawei.ui.commonui.searchview.HealthSearchView;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/* loaded from: classes4.dex */
public class OfflineCitiesFragment extends BaseFragment implements OfflineMapSearchThreadManager.OfflineMapSearchCallback {
    private ExpandableListView b;
    private OfflineMapAllCitiesAdapter c;
    private OfflineMapSomeCitiesAdapter g;
    private OfflineMapSearchThreadManager h;
    private ListView i;
    private HealthSearchView j;
    private HealthTextView l;
    private HashMap<Integer, CityListBean> d = new HashMap<>(16);
    private List<OfflineMapCity> f = new ArrayList(10);

    /* renamed from: a, reason: collision with root package name */
    private String f3544a = null;
    private d e = new d(this);

    enum EnableShowType {
        ALL_CITY_LIST,
        SEARCH_CITY_LIST,
        NULL_DATA
    }

    static class d extends BaseHandler<OfflineCitiesFragment> {
        d(OfflineCitiesFragment offlineCitiesFragment) {
            super(offlineCitiesFragment);
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // com.huawei.haf.handler.BaseHandler
        /* renamed from: aWD_, reason: merged with bridge method [inline-methods] */
        public void handleMessageWhenReferenceNotNull(OfflineCitiesFragment offlineCitiesFragment, Message message) {
            if (offlineCitiesFragment == null || message == null) {
                return;
            }
            LogUtil.a("OfflineCitiesFragment", "handleMessageWhenReferenceNotNull msg.what:", Integer.valueOf(message.what));
            if (message.what == 100) {
                if (offlineCitiesFragment.f3544a == null) {
                    offlineCitiesFragment.e(EnableShowType.ALL_CITY_LIST);
                    return;
                }
                Object obj = message.obj;
                if (message.obj == null) {
                    return;
                }
                if (!(obj instanceof List)) {
                    LogUtil.b("OfflineCitiesFragment", "MyHandler msg.obj is not List or is null");
                    return;
                }
                List list = (List) obj;
                if (list.size() > 0) {
                    offlineCitiesFragment.e(EnableShowType.SEARCH_CITY_LIST);
                    offlineCitiesFragment.a((List<OfflineMapCity>) list);
                } else {
                    offlineCitiesFragment.e(EnableShowType.NULL_DATA);
                }
            }
        }
    }

    @Override // androidx.fragment.app.Fragment
    public void onDetach() {
        super.onDetach();
    }

    @Override // androidx.fragment.app.Fragment
    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        LogUtil.a("OfflineCitiesFragment", "onCreateView()");
        View inflate = layoutInflater.inflate(R.layout.track_offlinemap_citylist_fragment, viewGroup, false);
        this.b = (ExpandableListView) inflate.findViewById(R.id.list);
        this.i = (ListView) inflate.findViewById(R.id.list_search_city);
        this.l = (HealthTextView) inflate.findViewById(R.id.txt_no_city);
        this.j = (HealthSearchView) inflate.findViewById(R.id.search_view);
        return inflate;
    }

    @Override // com.huawei.ui.commonui.base.BaseFragment, androidx.fragment.app.Fragment
    public void onViewCreated(View view, Bundle bundle) {
        LogUtil.a("OfflineCitiesFragment", "onViewCreated()");
        super.onViewCreated(view, bundle);
        this.b.setGroupIndicator(null);
        OfflineMapAllCitiesAdapter offlineMapAllCitiesAdapter = new OfflineMapAllCitiesAdapter(getActivity(), new ArrayList(10), this.d);
        this.c = offlineMapAllCitiesAdapter;
        this.b.setAdapter(offlineMapAllCitiesAdapter);
        OfflineMapSomeCitiesAdapter offlineMapSomeCitiesAdapter = new OfflineMapSomeCitiesAdapter(getActivity(), this.f);
        this.g = offlineMapSomeCitiesAdapter;
        this.i.setAdapter((ListAdapter) offlineMapSomeCitiesAdapter);
        OfflineMapSearchThreadManager offlineMapSearchThreadManager = new OfflineMapSearchThreadManager();
        this.h = offlineMapSearchThreadManager;
        offlineMapSearchThreadManager.c(this);
        this.h.e();
        this.j.setOnQueryTextListener(new SearchView.OnQueryTextListener() { // from class: com.huawei.healthcloud.plugintrack.offlinemap.ui.view.OfflineCitiesFragment.5
            @Override // androidx.appcompat.widget.SearchView.OnQueryTextListener
            public boolean onQueryTextSubmit(String str) {
                return false;
            }

            @Override // androidx.appcompat.widget.SearchView.OnQueryTextListener
            public boolean onQueryTextChange(String str) {
                if (!TextUtils.isEmpty(str)) {
                    OfflineCitiesFragment.this.f3544a = str;
                    OfflineCitiesFragment.this.h.d(OfflineCitiesFragment.this.f3544a, OfflineCitiesFragment.this.d);
                    return false;
                }
                OfflineCitiesFragment.this.f3544a = null;
                OfflineCitiesFragment.this.e(EnableShowType.ALL_CITY_LIST);
                return false;
            }
        });
    }

    public void d(ArrayList<OfflineMapProvince> arrayList, HashMap<Integer, CityListBean> hashMap) {
        if (arrayList == null || hashMap == null) {
            return;
        }
        LogUtil.a("OfflineCitiesFragment", "updateMap() provinceList:", Integer.valueOf(arrayList.size()), ",cityMap:" + hashMap.size());
        this.d = hashMap;
        c(arrayList, hashMap);
        String str = this.f3544a;
        if (str != null) {
            this.h.d(str, this.d);
        } else {
            e(EnableShowType.ALL_CITY_LIST);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a(List<OfflineMapCity> list) {
        this.g.e(list);
        this.g.notifyDataSetChanged();
    }

    private void c(ArrayList<OfflineMapProvince> arrayList, HashMap<Integer, CityListBean> hashMap) {
        OfflineMapAllCitiesAdapter offlineMapAllCitiesAdapter = this.c;
        if (offlineMapAllCitiesAdapter == null) {
            LogUtil.h("OfflineCitiesFragment", "refreshAllCitiesList mAllCitiesAdapterListAdapter == null");
        } else {
            offlineMapAllCitiesAdapter.b(hashMap, arrayList);
        }
    }

    /* renamed from: com.huawei.healthcloud.plugintrack.offlinemap.ui.view.OfflineCitiesFragment$4, reason: invalid class name */
    static /* synthetic */ class AnonymousClass4 {
        static final /* synthetic */ int[] e;

        static {
            int[] iArr = new int[EnableShowType.values().length];
            e = iArr;
            try {
                iArr[EnableShowType.ALL_CITY_LIST.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                e[EnableShowType.SEARCH_CITY_LIST.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                e[EnableShowType.NULL_DATA.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void e(EnableShowType enableShowType) {
        LogUtil.a("OfflineCitiesFragment", "enableShowList type : ", enableShowType);
        int i = AnonymousClass4.e[enableShowType.ordinal()];
        if (i == 1) {
            HealthTextView healthTextView = this.l;
            if (healthTextView != null) {
                healthTextView.setVisibility(8);
            }
            aWC_(this.i, 8);
            aWC_(this.b, 0);
            return;
        }
        if (i == 2) {
            HealthTextView healthTextView2 = this.l;
            if (healthTextView2 != null) {
                healthTextView2.setVisibility(8);
            }
            aWC_(this.i, 0);
            aWC_(this.b, 8);
            return;
        }
        if (i != 3) {
            return;
        }
        HealthTextView healthTextView3 = this.l;
        if (healthTextView3 != null) {
            healthTextView3.setVisibility(0);
        }
        aWC_(this.i, 8);
        aWC_(this.b, 8);
    }

    private void aWC_(ListView listView, int i) {
        if (listView != null) {
            listView.setVisibility(i);
        }
    }

    @Override // androidx.fragment.app.Fragment
    public void onStart() {
        LogUtil.a("OfflineCitiesFragment", "onStart()");
        super.onStart();
    }

    @Override // com.huawei.ui.commonui.base.BaseFragment, androidx.fragment.app.Fragment
    public void onResume() {
        LogUtil.a("OfflineCitiesFragment", "onResume()");
        super.onResume();
    }

    @Override // com.huawei.ui.commonui.base.BaseFragment, androidx.fragment.app.Fragment
    public void onPause() {
        LogUtil.a("OfflineCitiesFragment", "onPause()");
        super.onPause();
    }

    @Override // androidx.fragment.app.Fragment
    public void onDestroy() {
        LogUtil.a("OfflineCitiesFragment", "onDestroy()");
        super.onDestroy();
        OfflineMapSearchThreadManager offlineMapSearchThreadManager = this.h;
        if (offlineMapSearchThreadManager != null) {
            offlineMapSearchThreadManager.a();
        }
        HealthSearchView healthSearchView = this.j;
        if (healthSearchView != null) {
            healthSearchView.setOnQueryTextListener(null);
        }
        c();
    }

    @Override // com.huawei.healthcloud.plugintrack.offlinemap.manager.OfflineMapSearchThreadManager.OfflineMapSearchCallback
    public void onSearchResult(List<OfflineMapCity> list) {
        if (list == null) {
            LogUtil.b("OfflineCitiesFragment", "onSearchResult cities is null");
            return;
        }
        LogUtil.a("OfflineCitiesFragment", "onSearchResult() cities size:", Integer.valueOf(list.size()));
        d dVar = this.e;
        if (dVar == null) {
            LogUtil.b("OfflineCitiesFragment", "onSearchResult() mHandler null");
            return;
        }
        Message obtainMessage = dVar.obtainMessage();
        obtainMessage.what = 100;
        obtainMessage.obj = list;
        this.e.sendMessage(obtainMessage);
    }

    public void c() {
        this.j.clearFocus();
        Object systemService = getActivity().getApplicationContext().getSystemService("input_method");
        if (systemService instanceof InputMethodManager) {
            ((InputMethodManager) systemService).hideSoftInputFromWindow(this.j.getWindowToken(), 0);
        }
    }
}
