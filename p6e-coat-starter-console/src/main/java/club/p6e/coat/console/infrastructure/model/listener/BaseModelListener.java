package club.p6e.coat.console.infrastructure.model.listener;

import club.p6e.coat.common.global.GlobalUserInfo;
import club.p6e.coat.common.global.Globals;
import club.p6e.coat.console.infrastructure.model.*;
import jakarta.persistence.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.lang.reflect.Field;
import java.time.LocalDateTime;

/**
 * @author lidashuang
 * @version 1.0
 */
@Component
public class BaseModelListener {
    private static final String ID = "id";
    private static final String IS_DELETE = "isDelete";
    private static final String VERSION = "version";
    private static final String OPERATOR = "operator";
    private static final String CREATE_DATE = "createDate";
    private static final String UPDATE_DATE = "updateDate";

    /**
     * 注入日志对象
     */
    private static final Logger LOGGER = LoggerFactory.getLogger(BaseModelListener.class);

    /**
     * 注册使用改功能的模型
     */
    private static final Class<?>[] MS = new Class<?>[]{
            ConfigModel.class,
            DictionaryModel.class,
            FileUploadChunkModel.class,
            FileUploadModel.class,
            KeyValueModel.class,
            PermissionUrlGroupModel.class,
            PermissionUrlGroupRelationUrlModel.class,
            UserModel.class,
            PermissionUrlModel.class,
            UserAuthModel.class,
    };

    /**
     * 判断是否属于注册的模型
     *
     * @param target 模型对象
     * @return 判断是否属于注册的模型结果
     */
    private static boolean isModel(Object target) {
        if (target != null) {
            for (final Class<?> m : MS) {
                if (target.getClass() == m) {
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * 新增操作之前
     *
     * @param target 源对象
     */
    @PrePersist
    public void prePersist(Object target) {
        if (isModel(target)) {
            final Field[] fields = target.getClass().getDeclaredFields();
            for (final Field field : fields) {
                try {
                    final String name = field.getName();
                    switch (name) {
                        case ID -> {
                            field.setAccessible(true);
                            field.set(target, null);
                        }
                        case CREATE_DATE, UPDATE_DATE -> {
                            field.setAccessible(true);
                            field.set(target, LocalDateTime.now());
                        }
                        case OPERATOR -> {
                            field.setAccessible(true);
                            final GlobalUserInfo auth = Globals.getUserInfo();
                            field.set(target, String.valueOf(auth.getId()));
                        }
                        case VERSION, IS_DELETE -> {
                            field.setAccessible(true);
                            field.set(target, 0);
                        }
                        default -> {
                        }
                    }
                } catch (Exception e) {
                    LOGGER.error("[ BASE MODEL LISTENER UPDATE CREATE ]", e);
                }
            }
        }
    }

    /**
     * 新增操作之后
     *
     * @param target 源对象
     */
    @PostPersist
    public void postPersist(Object target) {
    }

    /**
     * 更新操作之前
     *
     * @param target 源对象
     */
    @PreUpdate
    public void preUpdate(Object target) {
        if (isModel(target)) {
            final Field[] fields = target.getClass().getDeclaredFields();
            for (final Field field : fields) {
                try {
                    final String name = field.getName();
                    switch (name) {
                        case UPDATE_DATE -> {
                            field.setAccessible(true);
                            field.set(target, LocalDateTime.now());
                        }
                        case OPERATOR -> {
                            field.setAccessible(true);
                            final GlobalUserInfo auth = Globals.getUserInfo();
                            field.set(target, String.valueOf(auth.getId()));
                        }
                        default -> {
                        }
                    }
                } catch (Exception e) {
                    LOGGER.error("[ BASE MODEL LISTENER UPDATE ERROR ]", e);
                }
            }
        }
    }

    /**
     * 更新操作之后
     *
     * @param target 源对象
     */
    @PostUpdate
    public void postUpdate(Object target) {
    }

    /**
     * 删除操作之前
     *
     * @param target 源对象
     */
    @PreRemove
    public void preRemove(Object target) {
    }

    /**
     * 删除操作之后
     *
     * @param target 源对象
     */
    @PostRemove
    public void postRemove(Object target) {
    }

}
