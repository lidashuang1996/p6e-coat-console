package club.p6e.coat.console.application.service.impl;

import club.p6e.coat.common.utils.CopyUtil;
import club.p6e.coat.console.application.service.UserService;
import club.p6e.coat.console.domain.aggregate.UserListAggregate;
import club.p6e.coat.console.domain.entity.PasswordEntity;
import club.p6e.coat.console.domain.entity.UserEntity;
import club.p6e.coat.console.domain.identifier.UserId;
import club.p6e.coat.console.domain.service.UserDomainService;
import club.p6e.coat.console.infrastructure.context.UserContext;
import club.p6e.coat.console.infrastructure.model.UserModel;
import org.springframework.stereotype.Service;

/**
 * 用户服务实现
 *
 * @author lidashuang
 * @version 1.0
 */
@Service
public class UserServerImpl implements UserService {

    /**
     * 用户领域服务
     */
    private final UserDomainService service;

    /**
     * 构造方法初始化
     *
     * @param service 用户领域服务
     */
    public UserServerImpl(UserDomainService service) {
        this.service = service;
    }

    @Override
    public UserContext.Dto get(UserContext.Request request) {
        return CopyUtil.run(service.get(new UserId(request.getId())).convertToModel(), UserContext.Dto.class);
    }

    @Override
    public UserContext.Dto create(UserContext.Request request) {
        return CopyUtil.run(service.create(
                new UserEntity(null, CopyUtil.run(request, UserModel.class)),
                new PasswordEntity(request.getPassword())
        ).convertToModel(), UserContext.Dto.class);
    }

    @Override
    public UserContext.Dto resetPassword(UserContext.Request request) {
        return CopyUtil.run(service.resetPassword(new UserId(request.getId())).convertToModel(), UserContext.Dto.class);
    }

    @Override
    public UserContext.Dto updateStatus(UserContext.Request request) {
        return CopyUtil.run(service.updateStatus(
                new UserId(request.getId()), request.getStatus()
        ).convertToModel(), UserContext.Dto.class);
    }

    @Override
    public UserContext.Dto updateEnabled(UserContext.Request request) {
        return CopyUtil.run(service.updateEnabled(
                new UserId(request.getId()), request.getEnabled()
        ).convertToModel(), UserContext.Dto.class);
    }

    @Override
    public UserContext.Dto updateInternal(UserContext.Request request) {
        return CopyUtil.run(service.updateInternal(
                new UserId(request.getId()), request.getInternal()
        ).convertToModel(), UserContext.Dto.class);
    }

    @Override
    public UserContext.ListDto list(UserContext.Request request) {
        final UserListAggregate aggregate = service.list(
                request.getSearch(), request.getSort(), request.getPaginationContext());
        final UserContext.ListDto result = new UserContext.ListDto();
        result.setPage(aggregate.getPage());
        result.setSize(aggregate.getSize());
        result.setTotal(aggregate.getTotal());
        result.setList(aggregate.getList().stream().map(m ->
                CopyUtil.run(m, UserContext.Item.class).setId(m.id().getId())).toList());
        return result;
    }

}
