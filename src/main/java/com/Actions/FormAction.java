package com.Actions;


import ChainFactory.ChainFactory;
import ChainFactory.CommonChain;
import CommonConstants.CommonConstants;
import Contexts.UserContext;
import lombok.Getter;
import lombok.Setter;
import org.apache.commons.chain.Context;
import org.apache.struts2.ServletActionContext;

import javax.servlet.http.HttpServletRequest;

@Getter@Setter
public class FormAction extends CommonAction{


    private UserContext user;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    private int id=-1;



    public String addUser() throws Exception {
        CommonChain chain= ChainFactory.getRegistersationChain();
        Context context=chain.getContext();
        context.put(CommonConstants.USER,user);
        chain.execute();
        boolean DPresult=(boolean)context.get("DP_Result") ;
        setResult("status",DPresult?"User added succesfully in DP":"Fail to Add");
        return SUCCESS;
    }
    public String getInfo() throws Exception {
        HttpServletRequest request = ServletActionContext.getRequest();
        String id=request.getParameter("id");
        this.id=Integer.valueOf(id);
        CommonChain chain = ChainFactory.getUserInfoChain();
        Context context = chain.getContext();
        context.put(CommonConstants.ID,getId());
        chain.execute();
        setResult(CommonConstants.USER,context.get(CommonConstants.USER));
        return SUCCESS;
    }
    public String getUsersInfo() throws Exception {
        CommonChain chain = ChainFactory.getUsersInfoChain();
        Context context = chain.getContext();
        chain.execute();
        setResult(CommonConstants.USERS_LIST,context.get(CommonConstants.USERS_LIST));
        return SUCCESS;
    }

}
