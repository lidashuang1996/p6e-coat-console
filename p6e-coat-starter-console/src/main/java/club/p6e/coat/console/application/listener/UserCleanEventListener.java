package club.p6e.coat.console.application.listener;

import club.p6e.coat.console.event.UserCleanEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

/**
 * @author lidashuang
 * @version 1.0
 */
@Component
public class UserCleanEventListener implements ApplicationListener<UserCleanEvent> {

    @Override
    public void onApplicationEvent(UserCleanEvent event) {
        System.out.println(
                "onApplicationEvent [ UserCleanEvent ] >>> " + event.getModel()
        );
    }

}
