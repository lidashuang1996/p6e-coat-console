package club.p6e.coat.console.application.service.impl;

import club.p6e.coat.common.utils.CopyUtil;
import club.p6e.coat.console.application.service.PermissionService;
import club.p6e.coat.console.domain.aggregate.*;
import club.p6e.coat.console.domain.entity.PermissionEntity;
import club.p6e.coat.console.domain.entity.PermissionUrlEntity;
import club.p6e.coat.console.domain.entity.PermissionUrlGroupEntity;
import club.p6e.coat.console.domain.identifier.PermissionId;
import club.p6e.coat.console.domain.identifier.PermissionUrlGroupId;
import club.p6e.coat.console.domain.identifier.PermissionUrlId;
import club.p6e.coat.console.domain.service.PermissionDomainService;
import club.p6e.coat.console.domain.service.PermissionUrlDomainService;
import club.p6e.coat.console.domain.service.PermissionUrlGroupDomainService;
import club.p6e.coat.console.infrastructure.context.PermissionContext;
import club.p6e.coat.console.infrastructure.model.PermissionUrlGroupModel;
import club.p6e.coat.console.infrastructure.model.PermissionUrlGroupRelationUrlModel;
import club.p6e.coat.console.infrastructure.model.PermissionUrlModel;
import org.springframework.stereotype.Service;

import java.util.stream.Collectors;

/**
 * @author lidashuang
 * @version 1.0
 */
@Service
public class PermissionServerImpl implements PermissionService {

    /**
     * 权限领域服务
     */
    private final PermissionDomainService permissionDomainService;

    /**
     * 权限 URL 领域服务
     */
    private final PermissionUrlDomainService permissionUrlDomainService;

    /**
     * 权限 URL 分组领域服务
     */
    private final PermissionUrlGroupDomainService permissionUrlGroupDomainService;

    /**
     * 构造方法初始化
     *
     * @param permissionDomainService         权限领域服务
     * @param permissionUrlDomainService      权限 URL 领域服务
     * @param permissionUrlGroupDomainService 权限 URL 分组领域服务
     */
    public PermissionServerImpl(
            PermissionDomainService permissionDomainService,
            PermissionUrlDomainService permissionUrlDomainService,
            PermissionUrlGroupDomainService permissionUrlGroupDomainService
    ) {
        this.permissionDomainService = permissionDomainService;
        this.permissionUrlDomainService = permissionUrlDomainService;
        this.permissionUrlGroupDomainService = permissionUrlGroupDomainService;
    }

    @Override
    public PermissionContext.Url.Dto urlGet(PermissionContext.Url.Request request) {
        return CopyUtil.run(permissionUrlDomainService.get(
                new PermissionUrlId(request.getId())
        ).convertToModel(), PermissionContext.Url.Dto.class);
    }

    @Override
    public PermissionContext.Url.Dto urlDelete(PermissionContext.Url.Request request) {
        return CopyUtil.run(permissionUrlDomainService.delete(
                new PermissionUrlId(request.getId())
        ).convertToModel(), PermissionContext.Url.Dto.class);
    }

    @Override
    public PermissionContext.Url.Dto urlCreate(PermissionContext.Url.Request request) {
        return CopyUtil.run(permissionUrlDomainService.create(
                new PermissionUrlEntity(null, CopyUtil.run(request, PermissionUrlModel.class))
        ).convertToModel(), PermissionContext.Url.Dto.class);
    }

    @Override
    public PermissionContext.Url.Dto urlUpdate(PermissionContext.Url.Request request) {
        return CopyUtil.run(permissionUrlDomainService.update(
                new PermissionUrlEntity(new PermissionUrlId(
                        request.getId()), CopyUtil.run(request, PermissionUrlModel.class))
        ).convertToModel(), PermissionContext.Url.Dto.class);
    }

    @Override
    public PermissionContext.Url.ListDto urlList(PermissionContext.Url.Request request) {
        final PermissionUrlListAggregate aggregate = permissionUrlDomainService.list(
                request.getSearch(), request.getSort(), request.getPaginationContext());
        final PermissionContext.Url.ListDto result = new PermissionContext.Url.ListDto();
        result.setPage(aggregate.getPage());
        result.setSize(aggregate.getSize());
        result.setTotal(aggregate.getTotal());
        result.setList(aggregate.getList().stream().map(m ->
                CopyUtil.run(m, PermissionContext.Url.Item.class).setId(m.id().getId())).toList());
        return result;
    }

    @Override
    public PermissionContext.UrlGroup.Dto urlGroupGet(PermissionContext.UrlGroup.Request request) {
        return CopyUtil.run(permissionUrlGroupDomainService.get(
                new PermissionUrlGroupId(request.getId())
        ).convertToModel(), PermissionContext.UrlGroup.Dto.class);
    }

    @Override
    public PermissionContext.UrlGroup.Dto urlGroupDelete(PermissionContext.UrlGroup.Request request) {
        return CopyUtil.run(permissionUrlGroupDomainService.delete(
                new PermissionUrlGroupId(request.getId())
        ).convertToModel(), PermissionContext.UrlGroup.Dto.class);
    }


    @Override
    public PermissionContext.UrlGroup.Dto urlGroupCreate(PermissionContext.UrlGroup.Request request) {
        return CopyUtil.run(permissionUrlGroupDomainService.create(
                new PermissionUrlGroupEntity(null, CopyUtil.run(request, PermissionUrlGroupModel.class))
        ).convertToModel(), PermissionContext.UrlGroup.Dto.class);
    }

    @Override
    public PermissionContext.UrlGroup.Dto urlGroupUpdate(PermissionContext.UrlGroup.Request request) {
        return CopyUtil.run(permissionUrlGroupDomainService.update(
                new PermissionUrlGroupEntity(new PermissionUrlGroupId(
                        request.getId()), CopyUtil.run(request, PermissionUrlGroupModel.class))
        ).convertToModel(), PermissionContext.UrlGroup.Dto.class);
    }

    @Override
    public PermissionContext.UrlGroup.ListDto urlGroupList(PermissionContext.UrlGroup.Request request) {
        final PermissionUrlGroupListAggregate aggregate = permissionUrlGroupDomainService.list(
                request.getSearch(), request.getSort(), request.getPaginationContext());
        final PermissionContext.UrlGroup.ListDto result = new PermissionContext.UrlGroup.ListDto();
        result.setPage(aggregate.getPage());
        result.setSize(aggregate.getSize());
        result.setTotal(aggregate.getTotal());
        result.setList(aggregate.getList().stream().map(m ->
                CopyUtil.run(m, PermissionContext.UrlGroup.Item.class).setId(m.id().getId())).toList());
        return result;
    }

    @Override
    public PermissionContext.Url.Details.Dto urlDetails(PermissionContext.Url.Details.Request request) {
        final PermissionUrlDetailsAggregate aggregate = permissionDomainService.urlDetails(
                new PermissionUrlId(request.getId()),
                request.getPaginationContext()
        );
        final PermissionContext.Url.Details.Dto result = CopyUtil.run(
                aggregate.getData(), PermissionContext.Url.Details.Dto.class
        ).setId(aggregate.getData().id().getId());
        result.setPage(aggregate.getPage());
        result.setSize(aggregate.getSize());
        result.setTotal(aggregate.getTotal());
        result.setGroups(aggregate.getList().stream().map(m -> {
            final PermissionContext.Url.Details.GroupItem group = new PermissionContext.Url.Details.GroupItem();
            group.setRelationConfig(m.getConfig()).setRelationAttribute(m.getAttribute());
            if (m.getPermissionUrlGroupEntity() == null) {
                return group;
            } else {
                return CopyUtil.run(m.getPermissionUrlGroupEntity(), group).setId(m.id().getGid());
            }
        }).collect(Collectors.toList()));
        return result;
    }

    @Override
    public PermissionContext.UrlGroup.Details.Dto urlGroupDetails(PermissionContext.UrlGroup.Details.Request request) {
        final PermissionUrlGroupDetailsAggregate aggregate = permissionDomainService.urlGroupDetails(
                new PermissionUrlGroupId(request.getId()),
                request.getPaginationContext()
        );
        final PermissionContext.UrlGroup.Details.Dto result = CopyUtil.run(
                aggregate.getData(), PermissionContext.UrlGroup.Details.Dto.class
        ).setId(aggregate.getData().id().getId());
        result.setPage(aggregate.getPage());
        result.setSize(aggregate.getSize());
        result.setTotal(aggregate.getTotal());
        result.setUrls(aggregate.getList().stream().map(m -> {
            final PermissionContext.UrlGroup.Details.UrlItem url = new PermissionContext.UrlGroup.Details.UrlItem();
            url.setRelationConfig(m.getConfig()).setRelationAttribute(m.getAttribute());
            if (m.getPermissionUrlGroupEntity() == null) {
                return url;
            } else {
                return CopyUtil.run(m.getPermissionUrlEntity(), url).setId(m.id().getUid());
            }
        }).toList());
        return result;
    }

    @Override
    public PermissionContext.Tree.Dto tree(PermissionContext.Tree.Request request) {
        final PermissionUrlGroupTreeAggregate aggregate =
                permissionUrlGroupDomainService.tree(new PermissionUrlGroupId(request.getId()));
        final PermissionContext.Tree.Dto result = new PermissionContext.Tree.Dto();
        result.setList(aggregate.getList().stream().map(m ->
                CopyUtil.run(m, PermissionContext.Tree.Item.class).setId(m.id().getId())).toList());
        return result;
    }

    @Override
    public PermissionContext.Association.Dto association(PermissionContext.Association.Request request) {
        final PermissionEntity entity = permissionDomainService.association(new PermissionEntity(
                new PermissionId(request.getUid(), request.getGid()),
                CopyUtil.run(request, PermissionUrlGroupRelationUrlModel.class)
        ));
        return CopyUtil.run(entity.convertToModel(), PermissionContext.Association.Dto.class);
    }

    @Override
    public PermissionContext.Disassociate.Dto disassociate(PermissionContext.Disassociate.Request request) {
        final PermissionEntity entity = permissionDomainService.disassociate(
                new PermissionEntity(new PermissionId(request.getUid(), request.getGid())));
        return CopyUtil.run(entity.convertToModel(), PermissionContext.Disassociate.Dto.class);
    }

}
