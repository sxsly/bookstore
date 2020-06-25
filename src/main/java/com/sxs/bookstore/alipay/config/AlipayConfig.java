package com.sxs.bookstore.alipay.config;

import java.io.FileWriter;
import java.io.IOException;

/* *
 *类名：AlipayConfig
 *功能：基础配置类
 *详细：设置帐户有关信息及返回路径
 *修改日期：2017-04-05
 *说明：
 *以下代码只是为了方便商户测试而提供的样例代码，商户可以根据自己网站的需要，按照技术文档编写,并非一定要使用该代码。
 *该代码仅供学习和研究支付宝接口使用，只是提供一个参考。
 */

public class AlipayConfig {
	
//↓↓↓↓↓↓↓↓↓↓请在这里配置您的基本信息↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓

	// 应用ID,您的APPID，收款账号既是您的APPID对应支付宝账号
	public static String app_id = "2016100100638449";
	
	// 商户私钥，您的PKCS8格式RSA2私钥
    public static String merchant_private_key = "MIIEvgIBADANBgkqhkiG9w0BAQEFAASCBKgwggSkAgEAAoIBAQCxYwOVHQgUKoyF45UB1xawPF2zTRcjJxe2rTYkBg/KqFpHQyytDxSmqnbGOwU2I83RtYgdb9fWxRMLNawDccK1+9rQairpI3OlZe2RtLxwGFB9yvsDy0op7Qh6gA43PpYreCftDuQMtSLjmOcPPY5cxE6UAGpXvfEvC7NhKoElu0L3wmwx03kl2pX7NMd7tzZOLswOojSlshbcPOVCADkXceRw81awZXRs/MsqZmLpDI8Mv+FYcY7rbCB7weY6Og1eq57k6Lgw2Da0UsOlqnit/l5DgrGGvrDYDn99i+M+4q6uHvi1nCxJmkXtg6UxqtHDV/fXeZySSMbYAvaengeXAgMBAAECggEANRKCx+JfwGjaklR9CIfp6eClyDeQbJwNpEI+qJiHsQKdwCSBAWY5r4Pg9X5DGOetdMjzaJtF4KQmBnyQcpdewkQQO7sIC6jnuNy+L5H7ry4XJwvL/l6cWd7pYn1dw3meRHM2vt5trL+lKKNevliUd3zkZbOaHZZeysydZrzo1vioAAo5uA5APcwoaUY/xqnihU1ei71f7KdlAUHQF+ftJoTdbS7SvWOzZwxER+KNCGu53FosrjCQGb6ymS09eQLR1dguVB52Udyysvs9MtFp8eQO7WgwoCZhJXpzpapi58QhFYp8rDSXDDo8NNg0+yyH1x5fxUvT89VXtBEAzfM8gQKBgQDqbFR39XRWB4PjtxV7BKr4oVBkMOA+g1RLbvEuMC4fxK8+aGHE5QflEHCUyoIAqbjayFOXLf5qJUKR+YuDop726YoJ0D57NtZM+2G5KDubbayGIeMOdlL9ytjNHxU8YYwaSC78pNFvIZZXByuGa8sheZ6vkl2ZbuNdYxQO2yq3fwKBgQDBtr77abO8SiYm3Nds4ksaRz487Im4lp/+E0caECY3oh0nGnpiHIqQ/y0fPNApSO0bdcYZzVGjcDfPKb9v+/nIvXybFR/rFfW80HgWvFQQSGNhddHMG4FNlUHnAkMm44NgXJhTFk+oXFOe57+1PLcE8m3izcilqZgFy7g3Qu176QKBgDgqWFqdbpAmIM1wUi81QRls3OwEYdXYdVvVauecF5D4+NvE+pfIIAob1D1x/4/VGjshABYaqPgZkUeUOH8DeX7DGZWcVTwEOXgcSp+oKg/lSQ6iE9qlMuEntqX+qoHbBUxOuyNBAgTUUUbsqtr7/KE6mHCMauyWCteXI3kjvapVAoGBAIWa1jQu3kFiXtf0/zvhA+2W740zk3VVpfmmMCcAtmsui67Zq0kZ6FRDO0rNNcETxD1pSH2pR8JS9psSby/8eXYDgvqvYjlRBxD4M6tyrwQf+TNSAKYm3lK1PCPpQomC56zBkGnV/JDhROdXbbjBrHVkA3gneFkSFIEC1gZ/Sa5pAoGBAIClJe7pIVcoE3ieSnuRdo6rxrQ29i9qOXyXQGeCSvL5mOr9GML5T13qLamLgA3UwJ07SQ651b+rJJiw/F11TSuOa6fxfNrVSPmndSnXbH1cvVa4hGnQDuli9iPdN27UvtCnJwmlkbbLVE9LR4C1ScMNwtfJl+dBFXA7dlWWLgQk";
	
	// 支付宝公钥,查看地址：https://openhome.alipay.com/platform/keyManage.htm 对应APPID下的支付宝公钥。
    public static String alipay_public_key = "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAjtW5d4rjZ1OVXkEbVGwTKej/FEinyQH2jnNiB4hbsUbcpbwhRMtI26/jTSHzAl8t3urLi8XLpYO5XPxg2gqJ+8ossYa6fEnQpxQOVrZxY4r8fE8RwwuS35pPYvCkfeWMPpRvsvcnK+cICjf2zUwvyblxg97usCL/X1ATXecY/yEuWfn5EzVG+ZOjatAc3F4loAr0/1xF1zDNn08zqjqeLN7qQ6PhLCDA7+6eWNd7hQ5cnXqTfXSLItbyWRYPAFK0bbZ+NVWzlzXT+4qyVpj97/ukApkno4KATkDcRASfbJXG9CZ9JwJs4ru88sTqvUXL3BbEIwNaRKuP9uVntrShgwIDAQAB";

	// 服务器异步通知页面路径  需http://格式的完整路径，不能加?id=123这类自定义参数，必须外网可以正常访问
	public static String notify_url = "http://工程公网访问地址/alipay.trade.page.pay-JAVA-UTF-8/notify_url.jsp";

	// 页面跳转同步通知页面路径 需http://格式的完整路径，不能加?id=123这类自定义参数，必须外网可以正常访问
	public static String return_url = "http://localhost:8080/bookstore/order/paySuccess.do";

	// 签名方式
	public static String sign_type = "RSA2";
	
	// 字符编码格式
	public static String charset = "utf-8";
	
	// 支付宝网关
	public static String gatewayUrl = "https://openapi.alipaydev.com/gateway.do";
	
	// 支付宝网关
	public static String log_path = "C:\\";


//↑↑↑↑↑↑↑↑↑↑请在这里配置您的基本信息↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑

    /** 
     * 写日志，方便测试（看网站需求，也可以改成把记录存入数据库）
     * @param sWord 要写入日志里的文本内容
     */
    public static void logResult(String sWord) {
        FileWriter writer = null;
        try {
            writer = new FileWriter(log_path + "alipay_log_" + System.currentTimeMillis()+".txt");
            writer.write(sWord);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (writer != null) {
                try {
                    writer.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}

