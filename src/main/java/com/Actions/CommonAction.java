package com.Actions;

import com.opensymphony.xwork2.ActionSupport;

import org.json.simple.JSONObject;


public class CommonAction extends ActionSupport {
    private JSONObject result;

    public JSONObject getResult() {
        return result;
    }

    public void setResult(JSONObject result) {
        this.result = result;
    }
    public void setResult(String key, Object result) {
        if (this.result == null) {
            this.result = new JSONObject();
        }
        this.result.put(key, result);
    }

}
