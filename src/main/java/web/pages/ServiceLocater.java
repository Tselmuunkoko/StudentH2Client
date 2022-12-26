package web.pages;

import web.ejb.StudentStateless;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import java.util.Properties;

public class ServiceLocater {
    public static StudentStateless getStudentStatelessService() {
        Properties jndiProperties=new Properties();
        jndiProperties.put(Context.INITIAL_CONTEXT_FACTORY, "org.jboss.naming.remote.client.InitialContextFactory");
        jndiProperties.put(Context.PROVIDER_URL,"http-remoting://127.0.0.1:8080");

        Context ctx=null;
        StudentStateless bean=null;
        try {
            ctx = new InitialContext(jndiProperties);
            bean = (StudentStateless)ctx.lookup( "ejb:/ejb-remote-server/StudentStatelessBean!web.ejb.StudentStateless");
        } catch (NamingException ex) {
            ex.printStackTrace();
        }
        return bean;
    }
}
