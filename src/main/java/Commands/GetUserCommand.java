package Commands;

import DataBase.ConnectSql;
import org.apache.commons.chain.Context;

public class GetUserCommand  extends CommonCommand{
    @Override
    public boolean excuteCommand(Context context) throws Exception {
        ConnectSql.getUserById(context);
        return false;
    }
}
