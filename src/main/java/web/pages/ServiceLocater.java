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
        jndiProperties.put(Context.URL_PKG_PREFIXES, "org.jboss.ejb.client.naming");
        jndiProperties.put("jboss.naming.client.ejb.context", true);
        jndiProperties.put("endpoint.name", "client-endpoint");
        jndiProperties.put("remote.connectionprovider.create.options.org.xnio.Options.SSL_ENABLED", false);
        jndiProperties.put("remote.connections", "default");
        jndiProperties.put("remote.connection.default.connect.options.org.xnio.Options.SASL_POLICY_NOANONYMOUS", false);

        jndiProperties.put(Context.SECURITY_PRINCIPAL, "user");
        jndiProperties.put(Context.SECURITY_CREDENTIALS, "user");
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
