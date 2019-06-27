## Security Web
### 使用Spring Security 5示例

总体思路如下：
   - 配置视图跳转，除了主页、登录页以及退出页，其余均需要认证访问
   - 继承WebSecurityConfigurerAdapter类
       - 重写protected void configure(HttpSecurity http)来定义网页访问权限
       - 重写protected UserDetailsService userDetailsService()方法来定义登录用户和密码