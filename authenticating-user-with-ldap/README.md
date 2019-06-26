## Authenticating a user with LDAP
### 使用LDAP来认证用户

大致思路如下：
   - 配置文件通过集成WebSecurityConfigurerAdapter来重写其中的
       - protected void configure(AuthenticationManagerBuilder auth)方法。
       该方法用来配置与LDAP相关的内容
       - protected void configure(HttpSecurity http) --配置网页访问权限
   - 在resources中添加LDAP的ldif文件，来自定义LDAP的配置

   - 同时配置如下property
   ```properties
   spring.ldap.embedded.ldif=classpath:test-server.ldif
   spring.ldap.embedded.base-dn=dc=springframework,dc=org
   spring.ldap.embedded.port=8389
   ```