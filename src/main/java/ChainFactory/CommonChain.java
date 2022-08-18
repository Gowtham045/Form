package ChainFactory;

import Contexts.CommonContext;
import org.apache.commons.chain.Chain;
import org.apache.commons.chain.Command;
import org.apache.commons.chain.Context;
import org.apache.commons.chain.impl.ChainBase;

public class CommonChain extends ChainBase {

    private Context context;

    public Context getContext() {
        if(context==null)
            context=new CommonContext();
        return context;
    }

    public void setContext(Context context) {
        this.context = context;
    }
    @Override
    public boolean execute(Context context) throws Exception {
        return  super.execute(context);
    }
    public boolean execute() throws Exception {
        return  this.execute(this.getContext());
    }

}
