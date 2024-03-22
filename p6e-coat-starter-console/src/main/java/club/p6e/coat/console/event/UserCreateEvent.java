package club.p6e.coat.console.event;

import club.p6e.coat.console.infrastructure.model.UserModel;
import lombok.Getter;
import org.springframework.context.ApplicationEvent;

/**
 * 用户创建事件
 *
 * @author lidashuang
 * @version 1.0
 */
@Getter
public class UserCreateEvent extends ApplicationEvent {

    private final UserModel model;

    public UserCreateEvent(Object source, UserModel model) {
        super(source);
        this.model = model;
    }

}
