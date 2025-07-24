package com.onedimension.utils;

import com.onedimension.pojo.Result;

public class ResultUtil {

    private static final String SUCCESS = "success";
    private static final String FAIL = "fail";

    private static final Integer SUCCESS_CODE = 1;
    private static final Integer FAIL_CODE = 0;

    private static Result buildSuccess() {
        Result result = new Result();
        result.setCode(SUCCESS_CODE);
        result.setMsg(SUCCESS);
        return result;
    }

    private static Result buildFail() {
        Result result = new Result();
        result.setCode(FAIL_CODE);
        result.setMsg(FAIL);
        return result;
    }


    public static Result success() {
        return buildSuccess();
    }

    public static Result success(Object data) {
        Result result = buildSuccess();
        result.setData(data);
        return result;
    }

    public static Result success(String msg) {
        Result result = buildSuccess();
        result.setMsg(msg);
        return result;
    }

    public static <T> Result success(T data, String msg) {
        Result result = buildSuccess();
        result.setData(data);
        result.setMsg(msg);
        return result;
    }


    public static Result fail() {
        return buildFail();
    }

    public static Result fail(String msg) {
        Result result = buildFail();
        result.setMsg(msg);
        return result;
    }
}
