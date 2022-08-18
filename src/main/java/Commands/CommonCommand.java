package Commands;

import org.apache.commons.chain.Command;
import org.apache.commons.chain.Context;

public abstract class CommonCommand implements Command {
    @Override
    public boolean execute(Context context) throws Exception {
        boolean result=this.excuteCommand(context);
        return result;
    }
    public abstract boolean excuteCommand(Context contex) throws Exception;
}
