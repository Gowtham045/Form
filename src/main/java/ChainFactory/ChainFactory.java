package ChainFactory;

import Commands.GetUserCommand;
import Commands.GetUsersCommand;
import Commands.MD5Command;
import Commands.RegisterationCommand;


public class ChainFactory {
    private static CommonChain getDefaultChain(){
       return new CommonChain();
    }
    public static CommonChain getRegistersationChain(){
          CommonChain chain=getDefaultChain();
          chain.addCommand(new MD5Command());
          chain.addCommand(new RegisterationCommand());
          return chain;
    }
    public static CommonChain getUsersInfoChain(){
        CommonChain chain=getDefaultChain();
        chain.addCommand(new GetUsersCommand());
        return chain;
    }
    public static CommonChain getUserInfoChain(){
        CommonChain chain=getDefaultChain();
        chain.addCommand(new GetUserCommand());
        return chain;
    }
}
