package red.cross.weixindonate.service;

import red.cross.weixindonate.entity.JsApiConfig;
import red.cross.weixindonate.entity.UnifiedOrderParam;

import java.io.IOException;
import java.net.MalformedURLException;

public interface UnifiedOrderService {
    JsApiConfig unifiedOrder(UnifiedOrderParam param) throws Exception;
}
