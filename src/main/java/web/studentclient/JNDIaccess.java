package web.studentclient;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import java.util.Properties;

public class JNDIaccess {
    public static void main(String a[]) throws NamingException {
        new JNDIaccess();
    }
    public JNDIaccess() throws NamingException {
        Properties jndiProps = new Properties();
        EJBClient.SimpleBeanIF result = null;
        jndiProps.put(Context.INITIAL_CONTEXT_FACTORY,
                "org.wildfly.naming.client.WildFlyInitialContextFactory");
        jndiProps.put(Context.PROVIDER_URL, "remote+http://127.0.0.1:8080");
        jndiProps.put(Context.SECURITY_PRINCIPAL, "admin123");
        jndiProps.put(Context.SECURITY_CREDENTIALS, "admin123");
        InitialContext initialContext = new InitialContext(jndiProps);
        result = (EJBClient.SimpleBeanIF) initialContext.lookup("ejb:/StudentH2-1.0-SNAPSHOT/SimpleBeanImpl!web.ejb.SimpleBeanIF");
        System.out.println(result);
        System.out.println(result.incrementNumber(5));
    }

    public Object get() throws NamingException {
        Properties jndiProps = new Properties();
        jndiProps.put(Context.INITIAL_CONTEXT_FACTORY,
                "org.wildfly.naming.client.WildFlyInitialContextFactory");
        jndiProps.put(Context.PROVIDER_URL, "remote+http://127.0.0.1:8080");
        jndiProps.put(Context.SECURITY_PRINCIPAL, "admin123");
        jndiProps.put(Context.SECURITY_CREDENTIALS, "admin123");
        InitialContext initialContext = new InitialContext(jndiProps);
        Object result = initialContext.lookup("jndi/mykey");
        return result;
    }
}
