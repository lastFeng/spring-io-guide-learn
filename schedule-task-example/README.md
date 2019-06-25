## Schedule task example
### 定时任务的简单实例

在SpringBoot中的定时任务，实现非常简单，在方法中使用@Scheduled注解即可将方法
定义成定时任务，而要真正执行定时任务，在还需要在主运行类中添加@EnableScheduling
注解，在运行时即可执行定义的定时任务。