package Commands;

import DataBase.ConnectSql;
import org.apache.commons.chain.Context;

public class RegisterationCommand extends CommonCommand{
    @Override
    public boolean excuteCommand(Context contex) throws Exception {
        boolean re=ConnectSql.addUser(contex);
        contex.put("DP_Result",!re);
        return false;
    }
}
