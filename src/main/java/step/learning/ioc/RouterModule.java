package step.learning.ioc;

import com.google.inject.servlet.ServletModule;
import step.learning.filters.*;
import step.learning.servlets.*;

public class RouterModule extends ServletModule {
    @Override
    protected void configureServlets() {
        // Третій спосіб конфігурування фільтрів та сервлетів - IoC
        filter("/*").through(CharsetFilter.class);

        serve("/").with(HomeServlet.class);
        serve("/signup").with(SignupServlet.class);
        serve("/successful-register").with(RegisterServlet.class);
        serve("/successful-create").with(RegisterServlet.class);
        serve("/admin-panel").with(AdminServlet.class);

    }
}
