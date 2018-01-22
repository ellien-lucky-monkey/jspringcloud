#### hystrix的高级特性
  入坑点：hystrix的高级特性需要初始化HystrixRequestContext 
  
    测试方法可以使用 HystrixRequestContext.initializeContext()的方式
    
    但是正式的web应用中需要使用过滤器的方式实现请求上下文的初始化 
    
    本例中的 HystrixRequestContextFilter类（使用了@WebFilter注解来定义filter） 
    
        注意：不要忘记启动主类上需要加上@ServletComponentScan注解使得filter生效
