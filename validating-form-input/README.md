## Validating form input
### 验证表格输入的有效性示例

   - 利用javax.validation包下的验证内容，在实体类构建时使用相应的依赖注解
   - 在接收前端请求时使用注解@Valid对需要验证的内容进行注解，
   同时利用BindingResult的hasError()方法，来判断是否正确匹配验证