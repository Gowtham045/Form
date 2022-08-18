package Commands;


import DataBase.ConnectSql;
import org.apache.commons.chain.Context;



public class GetUsersCommand extends CommonCommand{
    @Override
    public boolean excuteCommand(Context context) throws Exception {
        ConnectSql.getUsers(context);
        return false;
    }
}
