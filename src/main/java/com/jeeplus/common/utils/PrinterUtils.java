package com.jeeplus.common.utils;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

public class PrinterUtils {

    public static void print(HttpServletResponse response, String str){
        response.setContentType("application/json;charset=utf-8");
        PrintWriter out = null;
        try {
            out = response.getWriter();
            out.println(str);
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            out.flush();
            out.close();
        }
    }
}
