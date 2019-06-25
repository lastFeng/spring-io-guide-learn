## Restful Web Service Example
### Restful网页服务实例
SpringBoot对**Restful Api**进行了很好的封装

如果不想以Json对外输出，则使用注解@Controller进行（需要自定义页面，
或直接返回字符串[@ResponseBody注解]）

如果想以Json格式对外输出，则使用注解@RestController进行。

而浏览器有GET、PUT、POST、DELETE等方法，**Restful**提供了相应的注解进行使用：
   - @GetMapping   -- @RequestMapping(method=GET)
   - @PutMapping   -- @RequestMapping(method=PUT)
   - @PostMapping  -- @RequestMapping(method=POST)
   - @DeleteMapping-- @RequestMapping(method=Delete)