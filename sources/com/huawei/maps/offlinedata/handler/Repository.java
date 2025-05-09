package com.huawei.maps.offlinedata.handler;

import android.webkit.JavascriptInterface;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.reflect.TypeToken;
import com.huawei.health.h5pro.jsbridge.base.JsBaseModule;
import com.huawei.maps.offlinedata.handler.dto.persist.MapOfflineDataItemEntity;
import com.huawei.maps.offlinedata.handler.dto.persist.OfflineDataTaskEntity;
import com.huawei.maps.offlinedata.handler.dto.persist.WorldMapOfflineDataItemEntity;
import com.huawei.maps.offlinedata.jsbridge.a;
import com.huawei.maps.offlinedata.service.persist.b;
import com.huawei.maps.offlinedata.utils.d;
import java.util.List;

/* loaded from: classes5.dex */
public class Repository extends JsBaseModule {
    @JavascriptInterface
    public void addWorldMapDataItem(String str) {
        WorldMapOfflineDataItemEntity worldMapOfflineDataItemEntity = (WorldMapOfflineDataItemEntity) d.a(str, WorldMapOfflineDataItemEntity.class);
        if (worldMapOfflineDataItemEntity != null) {
            b.a().a(worldMapOfflineDataItemEntity);
        }
    }

    @JavascriptInterface
    public void deleteAllWorldMapDataItems() {
        b.a().c();
    }

    @JavascriptInterface
    public void getWorldMapDataItemList(final long j) {
        b.a().a(new com.huawei.maps.offlinedata.utils.b() { // from class: com.huawei.maps.offlinedata.handler.Repository$$ExternalSyntheticLambda1
            @Override // com.huawei.maps.offlinedata.utils.b
            public final void accept(Object obj) {
                Repository.c(j, (List) obj);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static /* synthetic */ void c(long j, List list) {
        a.a().a(list, j);
    }

    @JavascriptInterface
    public void addMapDataItems(String str) {
        b.a().a((List<MapOfflineDataItemEntity>) d.a(str, new TypeToken<List<MapOfflineDataItemEntity>>() { // from class: com.huawei.maps.offlinedata.handler.Repository.1
        }.getType()));
    }

    @JavascriptInterface
    public void deleteMapDataItems() {
        b.a().d();
    }

    @JavascriptInterface
    public void getMapDataItemList(final long j) {
        b.a().b(new com.huawei.maps.offlinedata.utils.b() { // from class: com.huawei.maps.offlinedata.handler.Repository$$ExternalSyntheticLambda0
            @Override // com.huawei.maps.offlinedata.utils.b
            public final void accept(Object obj) {
                Repository.b(j, (List) obj);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static /* synthetic */ void b(long j, List list) {
        a.a().a(list, j);
    }

    @JavascriptInterface
    public void addDataTask(String str) {
        b.a().a((OfflineDataTaskEntity) d.a(str, OfflineDataTaskEntity.class));
    }

    @JavascriptInterface
    public void deleteById(String str) {
        b.a().a(str);
    }

    @JavascriptInterface
    public void updateProgressById(String str) {
        JsonElement jsonElement = (JsonElement) d.a(str, JsonElement.class);
        if (jsonElement != null) {
            JsonObject asJsonObject = jsonElement.getAsJsonObject();
            b.a().a(asJsonObject.get("id").getAsString(), asJsonObject.get("downloadProgress").getAsInt(), asJsonObject.get("transmitProgress").getAsInt());
        }
    }

    @JavascriptInterface
    public void updateStatusById(String str) {
        JsonElement jsonElement = (JsonElement) d.a(str, JsonElement.class);
        if (jsonElement != null) {
            JsonObject asJsonObject = jsonElement.getAsJsonObject();
            b.a().b(asJsonObject.get("id").getAsString(), asJsonObject.get("itemState").getAsString(), asJsonObject.get("taskState").getAsString());
        }
    }

    @JavascriptInterface
    public void getDataTaskList(final long j) {
        b.a().c(new com.huawei.maps.offlinedata.utils.b() { // from class: com.huawei.maps.offlinedata.handler.Repository$$ExternalSyntheticLambda2
            @Override // com.huawei.maps.offlinedata.utils.b
            public final void accept(Object obj) {
                Repository.a(j, (List) obj);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static /* synthetic */ void a(long j, List list) {
        a.a().a(list, j);
    }
}
