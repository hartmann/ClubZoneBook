package com.bekk.wa.webframework;

import com.bekk.wa.webframework.test.RepopulateDatabase;
import org.apache.wicket.Page;
import org.apache.wicket.injection.web.InjectorHolder;
import org.apache.wicket.protocol.http.WebApplication;
import org.apache.wicket.settings.IMarkupSettings;
import org.apache.wicket.spring.injection.annot.SpringComponentInjector;

/**
 * Application object for your web application. If you want to run this application without deploying, run the Start class.
 */
public class WicketApplication extends WebApplication {

    public Class<? extends Page> getHomePage() {
        return ListEvents.class;
    }

    @Override
    protected void init() {
        super.init();
        addComponentInstantiationListener(new SpringComponentInjector(this));
        InjectorHolder.getInjector().inject(this);

        IMarkupSettings settings = getMarkupSettings();
        settings.setDefaultMarkupEncoding("UTF-8");
        settings.setStripWicketTags(true);
        settings.setAutomaticLinking(true);
        getApplicationSettings().setPageExpiredErrorPage(ListEvents.class);
        getApplicationSettings().setAccessDeniedPage(ListEvents.class);
        getApplicationSettings().setInternalErrorPage(ListEvents.class);

        mountBookmarkablePage("/events", ListEvents.class);
        
        if (DEVELOPMENT.equals(getConfigurationType())) {
            mountBookmarkablePage("/reset", RepopulateDatabase.class);
        }
    }

}
