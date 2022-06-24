package com.alibaba.qlexpress4.cache;

import com.alibaba.qlexpress4.member.MethodHandler;

/**
 * @Author TaoKan
 * @Date 2022/4/7 下午7:58
 */
public class FunctionCacheElement implements ICacheElement<Object> {
    private ICache<Object, Boolean> FUNCTION_CACHE = null;

    @Override
    public void initCache(int size, boolean enableUseCacheClear) {
        FUNCTION_CACHE = CacheFactory.cacheBuilder(size, enableUseCacheClear);
    }

    @Override
    public Object getCacheElement(String key) {
        return null;
    }

    @Override
    public void setCacheElement(String key, Object value) {
    }


    public boolean cacheFunctionInterface(Class<?> clazz) {
        Boolean cacheRes = FUNCTION_CACHE.get(clazz);
        if (cacheRes != null) {
            return cacheRes;
        }
        boolean res = clazz.isInterface() && MethodHandler.hasOnlyOneAbstractMethod(clazz.getMethods());
        FUNCTION_CACHE.put(clazz, res);
        return res;

    }
}