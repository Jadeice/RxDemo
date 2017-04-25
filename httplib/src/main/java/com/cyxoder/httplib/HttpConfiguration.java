package com.cyxoder.httplib;

import android.content.Context;
import android.os.Environment;
import android.text.TextUtils;

import com.innfotech.httplib.enums.CacheType;

import java.util.List;

import okhttp3.Interceptor;

/**
 * Created by Jade on 2017/4/24.
 */

public class HttpConfiguration {
    private final Context context;
    private final String baseUrl;
    private final String mapping;
    private final boolean useCache;
    private final CacheType cacheType;
    private final String cachePath;
    private final String downloadPath;
    private final long readTimeOut;
    private final long writeTimeOut;
    private final long connectTimeOut;
    private final long forceTimeOut;
    private final List<String> baseUrls;
    private final boolean useMultiUrl;
    private final Interceptor interceptor;

    public HttpConfiguration(Builder builder) {
        this.context = builder.ctx;
        this.baseUrl = builder.baseUrl;
        this.baseUrls = builder.baseUrls;
        this.mapping = builder.mapping;
        this.useCache = builder.useCache;
        this.cacheType = builder.cacheType;
        this.cachePath = builder.cachePath;
        this.downloadPath = builder.downloadPath;
        this.readTimeOut = builder.readTimeOut;
        this.writeTimeOut = builder.writeTimeOut;
        this.connectTimeOut = builder.connectTimeOut;
        this.forceTimeOut = builder.forceTimeOut;
        this.useMultiUrl = builder.useMultiUrl;
        this.interceptor = builder.interceptor;
    }


    public Context getContext() {
        return context;
    }

    public String getBaseUrl() {
        return baseUrl;
    }

    public String getMapping() {
        return mapping;
    }

    public boolean isUseCache() {
        return useCache;
    }

    public CacheType getCacheType() {
        return cacheType;
    }

    public String getCachePath() {
        return cachePath;
    }

    public String getDownloadPath() {
        return downloadPath;
    }

    public long getReadTimeOut() {
        return readTimeOut;
    }

    public long getWriteTimeOut() {
        return writeTimeOut;
    }

    public long getConnectTimeOut() {
        return connectTimeOut;
    }

    public long getForceTimeOut() {
        return forceTimeOut;
    }

    public List<String> getBaseUrls() {
        return baseUrls;
    }

    public boolean isUseMultiUrl() {
        return useMultiUrl;
    }

    public Interceptor getInterceptor() {
        return interceptor;
    }

    public static class Builder {
        private static final long DEFAULT_READ_TIMEOUT = 10 * 1000L;
        private static final long DEFAULT_WRITE_TIMEOUT = 10 * 1000L;
        private static final long DEFAULT_CONNECT_TIMEOUT = 10 * 1000L;
        private static final long DEFAULT_FORCE_TIMEOUT = 10 * 1000L;
        private final Context ctx;
        private String baseUrl;
        private String mapping;
        private boolean useCache;
        private CacheType cacheType = CacheType.TYPE_DISK;
        private String cachePath;
        private String downloadPath;
        private long readTimeOut = DEFAULT_READ_TIMEOUT;
        private long writeTimeOut = DEFAULT_WRITE_TIMEOUT;
        private long connectTimeOut = DEFAULT_CONNECT_TIMEOUT;
        private long forceTimeOut = DEFAULT_FORCE_TIMEOUT;
        private List<String> baseUrls;
        private boolean useMultiUrl;
        private boolean useForceTimeout = false;
        private Interceptor interceptor;

        public Builder(Context context) {
            this.ctx = context;
        }

        public Builder bindBaseUrl(String baseUrl) {
            this.baseUrl = baseUrl;
            return this;
        }

        public Builder bindBaseUrls(List<String> baseUrls) {
            this.baseUrls = baseUrls;
            return this;
        }

        public Builder bindMapping(String mapping) {
            this.mapping = mapping;
            return this;
        }

        public Builder useMultiUrl(boolean multi) {
            this.useMultiUrl = multi;
            return this;
        }

        public Builder cacheType(CacheType type) {
            this.cacheType = type;
            return this;
        }

        public Builder useCache(boolean useCache) {
            this.useCache = useCache;
            return this;
        }

        public Builder cachePath(String path) {
            this.cachePath = path;
            if (TextUtils.isEmpty(path)) {
                cachePath = Environment.getExternalStorageState() + "/httplib/cache/";
            }
            return this;
        }

        public Builder downloadPath(String path) {
            this.downloadPath = path;
            if (TextUtils.isEmpty(path)) {
                downloadPath = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS).getPath();
            }
            return this;
        }

        public Builder readTimeOut(long millis) {
            this.readTimeOut = millis;
            return this;
        }

        public Builder writeTimeOut(long millis) {
            this.writeTimeOut = millis;
            return this;
        }

        public Builder connectTimeOut(long millis) {
            this.connectTimeOut = millis;
            return this;
        }

        /**
         * 准确的强制超时
         *
         * @param millis
         * @return
         */
        public Builder forceTimeOut(long millis) {
            this.forceTimeOut = millis;
            return this;
        }

        public Builder useForceTimeOut(boolean useForceTimeout) {
            this.useForceTimeout = useForceTimeout;
            return this;
        }

        public Builder addInterceptor(Interceptor interceptor) {
            this.interceptor = interceptor;
            return this;
        }

        public HttpConfiguration build() {
            return new HttpConfiguration(this);
        }
    }
}
