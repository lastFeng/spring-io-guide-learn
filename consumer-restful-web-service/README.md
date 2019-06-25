## Consumer a Restful web service
### 消费Restful示例

[Spring.io提供的随机Restful接口生成](https://gturnquist-quoters.cfapps.io/api/random)

格式如下：
```json
{
   type: "success",
   value: {
      id: 10,
      quote: "Really loving Spring Boot, makes stand alone Spring apps easy."
   }
}
```

通过RestTemplate来消费Restful