package com.yang.freight.test.interfaces;

import com.alibaba.fastjson.JSONObject;
import com.alipay.api.AlipayApiException;
import com.alipay.api.AlipayClient;
import com.alipay.api.DefaultAlipayClient;
import com.alipay.api.request.AlipayTradePagePayRequest;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

/**
 * @description:
 * @author：杨超
 * @date: 2023/12/19
 * @Copyright：
 */
@Slf4j
public class ApiTest {

    // 「沙箱环境」应用ID - 您的APPID，收款账号既是你的APPID对应支付宝账号。获取地址；https://open.alipay.com/develop/sandbox/app
    public static String app_id = "9021000133604965";
    // 「沙箱环境」商户私钥，你的PKCS8格式RSA2私钥
    public static String merchant_private_key = "MIIEvgIBADANBgkqhkiG9w0BAQEFAASCBKgwggSkAgEAAoIBAQDWJgj7qpOj+HMk2FR4FDZYrkIEVY6UokTcbwKUxb0jqLn5JoGCwivu71xWhWne9423IqFEf5GA8Oylkl3SnFXAVmXlO5209cKhUAOu5Em0bYzDWEuAS/qpLXRQbJ8dLFSl1oeJc4qJ65geMJQ1r92q+yzGe9oNwy/rul4TMu7PXMr9Pio96GX+JhXD0sfJhN7nta0FiDU2VPUa61hj9R6nCYSqn0+nZgxE3g+AdVmn4syZT+bG6oA2cCmZtOhOn0N34mUlprJycmW9og8Pjx42FiqvRcLg4gMDmVcgcQoVnC6If7MXz2BFFzG4VcGqxhxSfCYsfIe9mCXhIWt8FeE5AgMBAAECggEAWH8RWUctiiZjFwfbKGBZ8R3aJEZjDKNeR6YMcASjW/gfGopCHRI265+5DfxGOyEcTGEgOdlp/NoTQX0SDrYSbdCGfUu8qYqiKt2RGBY5KznWYOe9E8DSTlV9arroqjqmpH3aNwArcUGN9HPo26LYvQ7dewy1mNhflk/HDrIOpFwXZclAS8qL1l+EUayb94qSMCPpJCx+aJaiheii1GWdmiaCw8Bsolvm0HvvDpuwNyFwjLARcIwgdFqLgsjZzyTMMBWqQzuxJsdwv4tYnSTp3ZkV+sFyfTe8SPFVeTu+toZFJCNkbxr7QPFjVXAuXgZc7KDtRAa/EfsFtlSlj5yl/QKBgQD8r9O6tfsGyq4bqVqTL3Q0hQj1P+7AWOCvKtjW215JhkIzbSwAZmYgYhmYayaNdYMSlZ5qjAoa0etL76G6z/3jcC/qUUZvFtmdd3fMqIGAW3+II6gGm3s7+6QnFwOzxGvN6Fm+sVxHMKZvbmMOGHsCdddERWOe6+s1LHET0wWL/wKBgQDY9NmRKYvYZC7f9MiN8dg8tjIDZmEROe1FynWnqhDCZUKk2CDLtOGf8P7nhi59rVzYvji6ttIh4P97535L4l3acxP/RyXVhCRhU++1pr0XYPvCztji6nBM/m7uUXZCwe2ePfhoUeyphea7ZlHBwfIRsfB+TY+8JkOwv1QxF5HyxwKBgGbSdWnIm3lMCEUhSMYTzUmByP9jIJgMkoE1Aj+LzHZGt7GNfxY6lNwgjyHCmFF/u/JYB7p7ebEoMoMX48vMzxc5k4XZxLt3pN26u3kAKJI+qUqQl9L4edESuN1fNBZ2mxeSsNI0AcTGOzOky5H0zDlYElypj7wfa/qbNvhGKj+pAoGBAK4nBYonvnDqIlJ1zJNcgcZiPg1KnjpUtGjkVsNKmX9iBzgJoXHDdaWMryEsLZ+GjNQTBbijTlQRpyNPtKq3P709yNyOwQj+tLFHzlCTUpA0soMyxRiJNbIoPuWgkHyP7OysM/2RXZbKvGbLgG82/thDbJgGLI3MbWOwgaWSlP47AoGBAJeQn0U7KNlVnP1PHleqnudsyCtD1Ic3B6tTh3RZCDvZ4XQKUXipWgFZtTIbEr8igzf1eOCmxYMdTo530OL2uavHkjy0XS67VulXJhIDqX99vw+BN134MXNmcgcc0ukDqTo+VQYrCPByVesriPDGhrIhygSv65w1yZN2kvc1qX1O";
    // 「沙箱环境」支付宝公钥
    public static String alipay_public_key = "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEA1iYI+6qTo/hzJNhUeBQ2WK5CBFWOlKJE3G8ClMW9I6i5+SaBgsIr7u9cVoVp3veNtyKhRH+RgPDspZJd0pxVwFZl5TudtPXCoVADruRJtG2Mw1hLgEv6qS10UGyfHSxUpdaHiXOKieuYHjCUNa/dqvssxnvaDcMv67peEzLuz1zK/T4qPehl/iYVw9LHyYTe57WtBYg1NlT1GutYY/UepwmEqp9Pp2YMRN4PgHVZp+LMmU/mxuqANnApmbToTp9Dd+JlJaaycnJlvaIPD48eNhYqr0XC4OIDA5lXIHEKFZwuiH+zF89gRRcxuFXBqsYcUnwmLHyHvZgl4SFrfBXhOQIDAQAB";
    // 「沙箱环境」服务器异步通知页面路径。这里小傅哥用了 natapp.cn 内网穿透工具
    public static String notify_url = "http://69w6hj.natappfree.cc/api/v1/alipay/alipay_notify_url";
    // 「沙箱环境」页面跳转同步通知页面路径 需http://格式的完整路径，必须外网可以正常访问，才会同步跳转
    public static String return_url = "https://gaga.plus";
    // 「沙箱环境」
    public static String gatewayUrl = "https://openapi-sandbox.dl.alipaydev.com/gateway.do";
    // 签名方式
    public static String sign_type = "RSA2";
    // 字符编码格式
    public static String charset = "utf-8";

    @Test
    public void test_AliPay() throws AlipayApiException {
        AlipayClient alipayClient = new DefaultAlipayClient(gatewayUrl,
                app_id,
                merchant_private_key,
                "json",
                charset,
                alipay_public_key,
                sign_type);

        AlipayTradePagePayRequest request = new AlipayTradePagePayRequest();  // 发送请求的 Request类
        request.setNotifyUrl(notify_url);
        request.setReturnUrl(return_url);

        JSONObject bizContent = new JSONObject();
        bizContent.put("out_trade_no", "daniel82AAAA000032333361X02");  // 我们自己生成的订单编号
        bizContent.put("total_amount", "0.01"); // 订单的总金额
        bizContent.put("subject", "测试商品");   // 支付的名称
        bizContent.put("product_code", "FAST_INSTANT_TRADE_PAY");  // 固定配置
        request.setBizContent(bizContent.toString());

        String form = alipayClient.pageExecute(request).getBody();
        log.info("测试结果：{}", form);
    }
}
