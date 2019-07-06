package com.miaosha.response;

/**
 * @author luther
 */
public class CommonReturnType {
    /**
     * 表明对应请求的返回处理结果  "success"  或"fail"
     */
    private String status;
    /**
     * 若status==success则返回data数据
     * 若status==success则返回data数据
     */
    private Object data;

    /**
     * 若status==success则返回data数据
     */
    public static CommonReturnType create(Object result){
        return CommonReturnType.create(result,"success");
    }
    public static CommonReturnType create(Object result,String status){
        CommonReturnType type=new CommonReturnType();
        type.setStatus(status);
        type.setData(result);
        return type;
    }
    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }
}
