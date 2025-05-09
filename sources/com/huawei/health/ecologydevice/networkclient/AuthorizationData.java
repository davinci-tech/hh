package com.huawei.health.ecologydevice.networkclient;

import java.util.List;

/* loaded from: classes8.dex */
public class AuthorizationData {
    private List<Scopes> scopes;

    public static class Scopes {
        private int id;
        private String name;
        private List<String> permissions;
        private String uri;

        public int getId() {
            return this.id;
        }

        public void setId(int i) {
            this.id = i;
        }

        public String getName() {
            return this.name;
        }

        public void setName(String str) {
            this.name = str;
        }

        public List<String> getPermissions() {
            return this.permissions;
        }

        public void setPermissions(List<String> list) {
            this.permissions = list;
        }

        public String getUri() {
            return this.uri;
        }

        public void setUri(String str) {
            this.uri = str;
        }
    }

    public List<Scopes> getScopes() {
        return this.scopes;
    }

    public void setScopes(List<Scopes> list) {
        this.scopes = list;
    }
}
