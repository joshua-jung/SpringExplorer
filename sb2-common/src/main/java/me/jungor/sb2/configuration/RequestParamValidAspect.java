package me.jungor.sb2.configuration;

import lombok.extern.slf4j.Slf4j;
import me.jungor.common.exception.ParameterException;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.hibernate.validator.internal.engine.path.PathImpl;
import org.springframework.core.LocalVariableTableParameterNameDiscoverer;
import org.springframework.core.ParameterNameDiscoverer;
import org.springframework.stereotype.Component;
import org.springframework.validation.BeanPropertyBindingResult;
import org.springframework.validation.ObjectError;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.ValidatorFactory;
import javax.validation.executable.ExecutableValidator;
import java.lang.reflect.Method;
import java.util.List;
import java.util.Set;

/**
 * 跑到了，不起作用，待探索
 * <p>
 * Reference：http://blog.u-call.net/wordpress/springmvc%E5%8F%82%E6%95%B0%E6%A0%A1%E9%AA%8C%EF%BC%8C%E5%8C%85%E6%8B%ACjavabean%E5%92%8C%E5%9F%BA%E6%9C%AC%E7%B1%BB%E5%9E%8B%E7%9A%84%E6%A0%A1%E9%AA%8C/spring/548/lak/
 * <p>
 * Created by joshua on 2018/6/6.
 */
@Component
@Slf4j
@Aspect
public class RequestParamValidAspect {

    @Pointcut("execution(public * me.jungor.sb2.controller..*.*(..))")
    public void valid() {
        log.info("Inner RequestParamValidAspect");
    }

    /**
     * 简单实现
//     * @param point
     */
//    @Before("valid()")
//    public void before(JoinPoint point) {
//        Object target = point.getThis();
//        // 获得切入方法参数
//        Object[] args = point.getArgs();
//        // 获得切入的方法
//        Method method = ((MethodSignature) point.getSignature()).getMethod();
//
//        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
//        ExecutableValidator executableValidator = factory.getValidator().forExecutables();
//
//        // 执行校验，获得校验结果
//        Set<ConstraintViolation<Object>> validResult = executableValidator.validateParameters(target, method, args);
//        //如果有校验不通过的
//        if (!validResult.isEmpty()) {
//            log.info("Validate Failed");
//            //todo
////            for(ConstraintViolation<Object> constraintViolation: validResult) {
////                log.info(constraintViolation.getMessage());
////            }
//            throw new ParameterException();
//        }
//        //返回第一条
//    }

    //环绕通知,环绕增强，相当于MethodInterceptor
    @Around("valid()")
    public Object arround(ProceedingJoinPoint pjp) throws Throwable {
        log.info("RequestParamValidAspect 校验方法环绕 start..... ");
        //取参数，如果没参数，那肯定不校验了
        Object[] objects = pjp.getArgs();
        if (objects.length == 0) {
            return pjp.proceed();
        }
        /************************** 校验 javabean **********************/
        //寻找带BindingResult参数的方法，然后判断是否有error，如果有则是校验不通过
        //如果配了 @Valid，应该走不到这里
        for (Object object : objects) {
            if (object instanceof BeanPropertyBindingResult) {
                //有校验
                BeanPropertyBindingResult result = (BeanPropertyBindingResult) object;
                if (result.hasErrors()) {
                    List<ObjectError> list = result.getAllErrors();
                    for (ObjectError error : list) {
                        log.info(error.getCode() + "---" + error.getArguments() + "--" + error.getDefaultMessage());
                        //返回第一条校验失败信息。也可以拼接起来返回所有的
                        return error.getDefaultMessage();
                    }
                }
            }
        }

        /************************** 校验普通参数 *************************/
        //  获得切入目标对象
        Object target = pjp.getThis();
        // 获得切入的方法
        Method method = ((MethodSignature) pjp.getSignature()).getMethod();
        // 执行校验，获得校验结果
        Set<ConstraintViolation<Object>> validResult = validMethodParams(target, method, objects);
        //如果有校验不通过的
        if (!validResult.isEmpty()) {
            String[] parameterNames = parameterNameDiscoverer.getParameterNames(method);  // 获得方法的参数名称

            for (ConstraintViolation<Object> constraintViolation : validResult) {
                PathImpl pathImpl = (PathImpl) constraintViolation.getPropertyPath();  // 获得校验的参数路径信息
                int paramIndex = pathImpl.getLeafNode().getParameterIndex();  // 获得校验的参数位置
                String paramName = parameterNames[paramIndex];  // 获得校验的参数名称

                //校验信息
                log.info("{} valid message:{}", paramName, constraintViolation.getMessage());
            }
            //返回第一条
//            return validResult.iterator().next().getMessage();
            throw new ParameterException();
        }

        return pjp.proceed();
    }

    private ParameterNameDiscoverer parameterNameDiscoverer = new LocalVariableTableParameterNameDiscoverer();
    private final ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
    private final ExecutableValidator validator = factory.getValidator().forExecutables();

    private <T> Set<ConstraintViolation<T>> validMethodParams(T obj, Method method, Object[] params) {
        return validator.validateParameters(obj, method, params);
    }
}