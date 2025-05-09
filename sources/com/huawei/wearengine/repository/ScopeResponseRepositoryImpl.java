package com.huawei.wearengine.repository;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.text.TextUtils;
import com.huawei.wearengine.repository.api.ScopeInfoRepository;
import com.huawei.wearengine.repository.api.ScopeResponseRepository;
import com.huawei.wearengine.scope.ScopeInfo;
import com.huawei.wearengine.scope.ScopeInfoResponse;
import defpackage.tqf;
import defpackage.tqh;
import java.util.Iterator;

/* loaded from: classes8.dex */
public class ScopeResponseRepositoryImpl implements ScopeResponseRepository {
    private ScopeInfoRepository b;
    private tqh e;

    public ScopeResponseRepositoryImpl(Context context) {
        this.e = new tqh(context, "tb_wear_engine_scope_response", tqf.a());
        this.b = new ScopeInfoRepositoryImpl(context);
    }

    @Override // com.huawei.wearengine.repository.api.ScopeResponseRepository
    public boolean insertScopeResponse(ScopeInfoResponse scopeInfoResponse, String str) {
        if (scopeInfoResponse != null && !TextUtils.isEmpty(str)) {
            if (getScopeResponse(str) != null) {
                deleteScopeResponse(str);
            }
            Iterator<ScopeInfo> it = scopeInfoResponse.getScopes().iterator();
            while (it.hasNext()) {
                this.b.insertScope(it.next(), str);
            }
            if (this.e.fex_(feu_(scopeInfoResponse, str)) > 0) {
                return true;
            }
        }
        return false;
    }

    @Override // com.huawei.wearengine.repository.api.ScopeResponseRepository
    public boolean deleteScopeResponse(String str) {
        if (TextUtils.isEmpty(str)) {
            return false;
        }
        return this.e.a("app_Id =? ", new String[]{str}) > 0 && this.b.deleteScope(str);
    }

    @Override // com.huawei.wearengine.repository.api.ScopeResponseRepository
    public ScopeInfoResponse getScopeResponse(String str) {
        if (TextUtils.isEmpty(str)) {
            return null;
        }
        ScopeInfoResponse few_ = few_(this.e.fey_("app_Id =? ", new String[]{str}, null, null, null));
        if (few_ == null) {
            return null;
        }
        few_.setScopes(this.b.getScopes(str));
        return few_;
    }

    @Override // com.huawei.wearengine.repository.api.ScopeResponseRepository
    public boolean updateScopeResponse(ScopeInfoResponse scopeInfoResponse, String str) {
        return this.e.fez_(feu_(scopeInfoResponse, str), "app_Id =? ", new String[]{str}) > 0;
    }

    private ContentValues feu_(ScopeInfoResponse scopeInfoResponse, String str) {
        ContentValues contentValues = new ContentValues();
        contentValues.put("app_Id", str);
        contentValues.put("cert_finger_print", scopeInfoResponse.getCertFingerprint());
        contentValues.put("cert_finger_print_extra", scopeInfoResponse.getCertFingerprintExtra());
        contentValues.put("expire_time", Long.valueOf(scopeInfoResponse.getExpireTime()));
        contentValues.put("failure_expire_time", Long.valueOf(scopeInfoResponse.getFailureExpireTime()));
        contentValues.put("vender_code", scopeInfoResponse.getVenderCode());
        contentValues.put("time_stamp", Long.valueOf(scopeInfoResponse.getTimeStamp()));
        return contentValues;
    }

    private ScopeInfoResponse few_(Cursor cursor) {
        if (cursor == null) {
            return null;
        }
        try {
            return cursor.moveToNext() ? fev_(cursor) : null;
        } finally {
            cursor.close();
        }
    }

    private ScopeInfoResponse fev_(Cursor cursor) {
        ScopeInfoResponse scopeInfoResponse = new ScopeInfoResponse();
        scopeInfoResponse.setCertFingerprint(cursor.getString(cursor.getColumnIndex("cert_finger_print")));
        scopeInfoResponse.setCertFingerprintExtra(cursor.getString(cursor.getColumnIndex("cert_finger_print_extra")));
        scopeInfoResponse.setExpireTime(cursor.getInt(cursor.getColumnIndex("expire_time")));
        scopeInfoResponse.setVenderCode(cursor.getString(cursor.getColumnIndex("vender_code")));
        scopeInfoResponse.setFailureExpireTime(cursor.getInt(cursor.getColumnIndex("failure_expire_time")));
        scopeInfoResponse.setTimeStamp(cursor.getLong(cursor.getColumnIndex("time_stamp")));
        return scopeInfoResponse;
    }
}
