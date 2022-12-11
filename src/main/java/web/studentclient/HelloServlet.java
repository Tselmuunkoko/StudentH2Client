package web.studentclient;

import web.studentclient.BeanInterface.SimpleStateless;

import java.io.*;
import java.util.Properties;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.http.*;
import javax.servlet.annotation.*;

@WebServlet(name = "helloServlet", value = "/hello-servlet")
public class HelloServlet extends HttpServlet {
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setContentType("text/html");
//        Context ctx = null;
        SimpleStateless hello=null;
//        try {
            Properties jndiProperties=new Properties();
            jndiProperties.put(Context.INITIAL_CONTEXT_FACTORY, "org.wildfly.naming.client.WildFlyInitialContextFactory");
            jndiProperties.put(Context.PROVIDER_URL,"http-remoting://localhost:8080");
            Context ctx=null;
            EJBClient.SimpleBeanIF bean=null;
            try {
                ctx = new InitialContext(jndiProperties);
                System.out.println("before");
                bean = (EJBClient.SimpleBeanIF) ctx.lookup( "ejb:/StudentH2-1.0-SNAPSHOT/SimpleBeanImpl!web.ejb.SimpleBeanIF");
                System.out.println(bean.incrementNumber(5));
            } catch (NamingException ex) {
                ex.printStackTrace();
            }
//            Properties jndiProps = new Properties();
//            jndiProps.put(Context.INITIAL_CONTEXT_FACTORY,
//                    "org.wildfly.naming.client.WildFlyInitialContextFactory");
//            jndiProps.put(Context.PROVIDER_URL, "remote+http://127.0.0.1:8080");
//            jndiProps.put(Context.SECURITY_PRINCIPAL, "admin123");
//            jndiProps.put(Context.SECURITY_CREDENTIALS, "admin123");
//            ctx = new InitialContext(jndiProps);
//            String name="ejb:/StudentH2-1.0-SNAPSHOT/SimpleStatelessBean!web.ejb.SimpleStateless";
//            hello= (SimpleStateless) ctx.lookup(name);
//        } catch (NamingException e) {
//            e.printStackTrace();
//        }
        PrintWriter out = response.getWriter();
        out.println("<html><body>");
        out.println("<h1>" + hello.getValue(1) + "</h1>");
        out.println("</body></html>");
    }
}