package com.sena.exception;

import org.apache.avalon.framework.parameters.ParameterException;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Sena on 2017/3/1.
 */
public class MyException{
    public ModelAndView resolveException(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) {
        Map<String, Object> model = new HashMap<String, Object>();
        model.put("ex", ex);
        ex.printStackTrace();//打印异常信息
        // 根据不同错误转向不同页面
        if (ex instanceof CSRFException) {//受到csrf攻击
            return new ModelAndView("/errorPage/error", model);
        }
        if (ex instanceof BusinessException) {//业务逻辑处理出错
            return new ModelAndView("errorPage/businessError", model);
        } else if (ex instanceof ParameterException) {//参数处理出错。
            return new ModelAndView("errorPage/parameterError", model);
        } else {  //其他数据类型错误
            return new ModelAndView("errorPage/error", model);
        }
    }
}
