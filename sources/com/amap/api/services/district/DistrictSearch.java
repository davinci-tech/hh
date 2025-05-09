package com.amap.api.services.district;

import android.content.Context;
import com.amap.api.col.p0003sl.gu;
import com.amap.api.services.core.AMapException;
import com.amap.api.services.interfaces.IDistrictSearch;

/* loaded from: classes2.dex */
public class DistrictSearch {

    /* renamed from: a, reason: collision with root package name */
    private IDistrictSearch f1490a;

    public interface OnDistrictSearchListener {
        void onDistrictSearched(DistrictResult districtResult);
    }

    public DistrictSearch(Context context) throws AMapException {
        if (this.f1490a == null) {
            try {
                this.f1490a = new gu(context);
            } catch (Exception e) {
                e.printStackTrace();
                if (e instanceof AMapException) {
                    throw ((AMapException) e);
                }
            }
        }
    }

    public DistrictSearchQuery getQuery() {
        IDistrictSearch iDistrictSearch = this.f1490a;
        if (iDistrictSearch != null) {
            return iDistrictSearch.getQuery();
        }
        return null;
    }

    public void setQuery(DistrictSearchQuery districtSearchQuery) {
        IDistrictSearch iDistrictSearch = this.f1490a;
        if (iDistrictSearch != null) {
            iDistrictSearch.setQuery(districtSearchQuery);
        }
    }

    public DistrictResult searchDistrict() throws AMapException {
        IDistrictSearch iDistrictSearch = this.f1490a;
        if (iDistrictSearch != null) {
            return iDistrictSearch.searchDistrict();
        }
        return null;
    }

    public void searchDistrictAsyn() {
        IDistrictSearch iDistrictSearch = this.f1490a;
        if (iDistrictSearch != null) {
            iDistrictSearch.searchDistrictAsyn();
        }
    }

    public void searchDistrictAnsy() {
        IDistrictSearch iDistrictSearch = this.f1490a;
        if (iDistrictSearch != null) {
            iDistrictSearch.searchDistrictAnsy();
        }
    }

    public void setOnDistrictSearchListener(OnDistrictSearchListener onDistrictSearchListener) {
        IDistrictSearch iDistrictSearch = this.f1490a;
        if (iDistrictSearch != null) {
            iDistrictSearch.setOnDistrictSearchListener(onDistrictSearchListener);
        }
    }
}
