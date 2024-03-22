package club.p6e.coat.console.application.listener;

import club.p6e.coat.console.event.UserCreateEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

/**
 * @author lidashuang
 * @version 1.0
 */
@Component
public class UserCreateEventListener implements ApplicationListener<UserCreateEvent> {

    @Override
    public void onApplicationEvent(UserCreateEvent event) {
        System.out.println(
                "onApplicationEvent [ UserCreateEvent ] >>> " + event.getModel()
        );
    }

}
