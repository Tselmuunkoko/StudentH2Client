package web.studentclient;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import java.util.Properties;

public class EJBClient {
    EJBClient() {
        Properties jndiProperties=new Properties();
        jndiProperties.put(Context.INITIAL_CONTEXT_FACTORY, "org.wildfly.naming.client.WildFlyInitialContextFactory");
        jndiProperties.put(Context.PROVIDER_URL,"http-remoting://localhost:8080");
        Context ctx=null;
        SimpleBeanIF bean=null;
        try {
            ctx = new InitialContext(jndiProperties);
            System.out.println("before");
            bean = (SimpleBeanIF) ctx.lookup( "ejb:/StudentH2-1.0-SNAPSHOT/SimpleBeanImpl!web.ejb.SimpleBeanIF");
            System.out.println(bean.incrementNumber(5));
        } catch (NamingException ex) {
            ex.printStackTrace();
        }
    }
    public static void main(String a[]){
        EJBClient client=new EJBClient();
    }

    public static interface SimpleBeanIF {
        public int incrementNumber(int n);
    }
}
