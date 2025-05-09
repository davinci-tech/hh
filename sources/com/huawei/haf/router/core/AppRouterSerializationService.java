package com.huawei.haf.router.core;

import android.content.Context;
import com.google.gson.Gson;
import com.huawei.haf.application.BaseApplication;
import com.huawei.haf.router.facade.service.SerializationService;
import java.lang.reflect.Type;

/* loaded from: classes.dex */
final class AppRouterSerializationService implements SerializationService {
    private SerializationService b;

    AppRouterSerializationService() {
        init(BaseApplication.e());
    }

    @Override // com.huawei.haf.router.facade.service.SerializationService
    public String object2Json(Object obj) {
        SerializationService serializationService = this.b;
        if (serializationService != null) {
            return serializationService.object2Json(obj);
        }
        return new Gson().toJson(obj);
    }

    @Override // com.huawei.haf.router.facade.service.SerializationService
    public <T> T parseObject(String str, Type type) {
        SerializationService serializationService = this.b;
        if (serializationService != null) {
            return (T) serializationService.parseObject(str, type);
        }
        return (T) new Gson().fromJson(str, type);
    }

    @Override // com.huawei.haf.router.facade.template.ServiceProvider
    public void init(Context context) {
        this.b = (SerializationService) AppRouterHelper.e(SerializationService.class);
    }
}
