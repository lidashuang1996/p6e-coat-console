package club.p6e.coat.console.domain.service.impl;

import club.p6e.coat.common.pagination.PaginationContext;
import club.p6e.coat.common.search.SearchableContext;
import club.p6e.coat.common.sortable.SortableContext;
import club.p6e.coat.console.PasswordEncryptor;
import club.p6e.coat.console.Properties;
import club.p6e.coat.console.domain.aggregate.UserListAggregate;
import club.p6e.coat.console.domain.entity.PasswordEntity;
import club.p6e.coat.console.domain.entity.UserEntity;
import club.p6e.coat.console.domain.identifier.UserId;
import club.p6e.coat.console.domain.repository.UserDomainRepository;
import club.p6e.coat.console.domain.service.UserDomainService;
import club.p6e.coat.console.event.UserCleanEvent;
import club.p6e.coat.console.event.UserCreateEvent;
import club.p6e.coat.console.infrastructure.error.GlobalExceptionContext;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Component;

/**
 * 用户领域服务实现
 *
 * @author lidashuang
 * @version 1.0
 */
@Component
public class UserDomainServiceImpl implements UserDomainService {

    /**
     * 配置文件
     */
    private final Properties properties;

    /**
     * 密码加密器
     */
    private final PasswordEncryptor encryptor;

    /**
     * 用户领域存储库
     */
    private final UserDomainRepository repository;

    /**
     * Spring Boot 事件发布者
     */
    private final ApplicationEventPublisher applicationEventPublisher;

    /**
     * 构造方法初始化
     *
     * @param repository 用户领域存储库
     */
    public UserDomainServiceImpl(
            Properties properties,
            PasswordEncryptor encryptor,
            UserDomainRepository repository,
            ApplicationEventPublisher applicationEventPublisher
    ) {
        this.properties = properties;
        this.encryptor = encryptor;
        this.repository = repository;
        this.applicationEventPublisher = applicationEventPublisher;
    }

    @Override
    public UserEntity get(UserId id) {
        final UserEntity entity = repository.findById(id);
        if (entity == null) {
            throw GlobalExceptionContext.executeUserDataNotExistException(
                    this.getClass(),
                    "fun get(UserId id).",
                    "Request get data does not exist."
            );
        } else {
            return entity;
        }
    }

    @Override
    public UserEntity delete(UserId id) {
        final UserEntity entity = repository.delete(id);
        if (entity == null) {
            throw GlobalExceptionContext.executeUserDataNotExistException(
                    this.getClass(),
                    "fun delete(UserId id).",
                    "Request delete data does not exist."
            );
        } else {
            return entity;
        }
    }

    @Override
    public UserEntity create(UserEntity ue, PasswordEntity pe) {
        if (ue.getId() == null) {
            final UserEntity entity = repository.save(ue, new PasswordEntity(encryptor.execute(pe.content())));
            applicationEventPublisher.publishEvent(new UserCreateEvent(this, entity.convertToModel()));
            return entity;
        } else {
            throw GlobalExceptionContext.executeUserDataExistException(
                    this.getClass(),
                    "fun create(UserEntity ue, PasswordEntity pe).",
                    "Request create data already exist."
            );
        }
    }

    @Override
    public UserEntity resetPassword(UserId id) {
        final UserEntity entity = repository.updatePassword(id,
                new PasswordEntity(encryptor.execute(properties.getDefaultPassword())));
        if (entity == null) {
            throw GlobalExceptionContext.executeUserDataNotExistException(
                    this.getClass(),
                    "fun reset password(UserId id).",
                    "Request reset password data does not exist."
            );
        } else {
            applicationEventPublisher.publishEvent(new UserCleanEvent(this, entity.convertToModel()));
            return entity;
        }
    }

    @Override
    public UserEntity updatePassword(UserId id, PasswordEntity oldPwd, PasswordEntity newPwd) {
        final PasswordEntity pe = repository.getPassword(id);
        if (pe == null) {
            throw GlobalExceptionContext.executeUserDataNotExistException(
                    this.getClass(),
                    "fun update password(UserId id, PasswordEntity oldPwd, PasswordEntity newPwd).",
                    "Request update password data does not exist."
            );
        } else {
            if (pe.isMatch(encryptor.execute(oldPwd.content()))) {
                final UserEntity entity = repository.updatePassword(id,
                        new PasswordEntity(encryptor.execute(newPwd.content())));
                applicationEventPublisher.publishEvent(new UserCleanEvent(this, entity.convertToModel()));
                return entity;
            } else {
                throw GlobalExceptionContext.exceptionPasswordException(
                        this.getClass(),
                        "fun updatePassword(UserId id, PasswordEntity oldPwd, PasswordEntity newPwd).",
                        "Verify user password error."
                );
            }
        }
    }

    @Override
    public UserEntity updateStatus(UserId id, Integer status) {
        final UserEntity ue = repository.findById(id);
        if (ue == null) {
            throw GlobalExceptionContext.executeUserDataNotExistException(
                    this.getClass(),
                    "fun update status(UserId id, Integer status).",
                    "Request update status data does not exist."
            );
        } else {
            return repository.save(ue.setStatus(status));
        }
    }

    @Override
    public UserEntity updateEnabled(UserId id, Integer enabled) {
        final UserEntity ue = repository.findById(id);
        if (ue == null) {
            throw GlobalExceptionContext.executeUserDataNotExistException(
                    this.getClass(),
                    "fun update enabled(UserId id, Integer enabled).",
                    "Request update enabled data does not exist."
            );
        } else {
            return repository.save(ue.setEnabled(enabled));
        }
    }

    @Override
    public UserEntity updateInternal(UserId id, Integer internal) {
        final UserEntity ue = repository.findById(id);
        if (ue == null) {
            throw GlobalExceptionContext.executeUserDataNotExistException(
                    this.getClass(),
                    "fun update internal(UserId id, Integer internal).",
                    "Request update internal data does not exist."
            );
        } else {
            return repository.save(ue.setInternal(internal));
        }
    }

    @Override
    public UserListAggregate list(SearchableContext search, SortableContext sort, PaginationContext pagination) {
        return repository.list(search, sort, pagination);
    }
}
