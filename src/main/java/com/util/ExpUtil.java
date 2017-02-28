package com.util;

import com.learninggenie.common.exception.LearningGenieRuntimeException;
import com.udojava.evalex.Expression;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by zjj on 2016/9/27.
 */
public class ExpUtil {

    public static float evaluate(String formula, Map<String, Object> vars) {
        Expression expression = new Expression(formula);
        for (String var : vars.keySet()) {
            expression.and(var, vars.get(var).toString());
        }
        try {
            return expression.eval().floatValue();
        } catch (NumberFormatException e) {
            throw new LearningGenieRuntimeException("Unrecognized formula: " + formula);
        }
    }

    public static void main(String[] args) {
//        String f = "(1.3276899999999999E-2)*(IF( (34 / 50)  < 0.3 ,1,0))";
        String f = "(19.31631000)*((3/38))+(-15.33509000)*((3/38)^2)+(8.88054800)*((3/38)^3)+(3.09810100)*((0/25))+(-0.71770290)*((0/25)^2)+(0.64863830)*((0/25)^3)+(8.97373600)*((4/38))+(-10.41305000)*((4/38)^2)+(7.15664600)*((4/38)^3)+(10.19637000)*(3/26)+(-11.57674000)*((3/26)^2)+(6.29845100)*((3/26)^3)+(-1.37315400)*((4/38)*(0/25))+(-3.17367900)*((4/38)*(3/38))+(-2.90881900)*((4/38)*(3/26))+(-0.20348160)*((0/25)*(3/38))+(0.67455380)*((0/25)*(3/26))+(0.58588900)*((3/38)*(3/26))+(0.07029410)*(IF((4/38)<0.3,1,0))+(0.03677320)*(IF((0/25)<0.3,1,0))+(0.27369510)*(IF((3/38)<0.3,1,0))+(0.25896130)*(IF((3/26)<0.3,1,0))+(-0.16877790)*(IF(((3/38)-(4/38))>0.2,1,0))+(-0.04236320)*(IF(((3/38)-(4/38))<-0.2,1,0))+(-0.00877940)*(IF(((3/38)-(0/25))>0.2,1,0))+(0.06837460)*(IF(((3/38)-(0/25))<-0.2,1,0))+(-0.06337950)*(IF(((3/38)-(3/26))>0.2,1,0))+(0.02540980)*(IF(((3/38)-(3/26))<-0.2,1,0))";
        Map<String, Object> vars = new HashMap<>();
        float r = ExpUtil.evaluate(f, vars);
        System.out.println(r);
    }
}
