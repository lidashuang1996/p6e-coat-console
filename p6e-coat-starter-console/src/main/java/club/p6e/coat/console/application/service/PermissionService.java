package club.p6e.coat.console.application.service;

import club.p6e.coat.console.infrastructure.context.PermissionContext;

/**
 * 权限服务
 *
 * @author lidashuang
 * @version 1.0
 */
public interface PermissionService {

    /**
     * 读取
     *
     * @param request 参数对象
     * @return 结果对象
     */
    PermissionContext.Url.Dto urlGet(PermissionContext.Url.Request request);

    /**
     * 删除
     *
     * @param request 参数对象
     * @return 结果对象
     */
    PermissionContext.Url.Dto urlDelete(PermissionContext.Url.Request request);

    /**
     * 创建
     *
     * @param request 参数对象
     * @return 结果对象
     */
    PermissionContext.Url.Dto urlCreate(PermissionContext.Url.Request request);

    /**
     * 修改
     *
     * @param request 参数对象
     * @return 结果对象
     */
    PermissionContext.Url.Dto urlUpdate(PermissionContext.Url.Request request);

    /**
     * 列表
     *
     * @param request 参数对象
     * @return 结果对象
     */
    PermissionContext.Url.ListDto urlList(PermissionContext.Url.Request request);

    /**
     * 读取
     *
     * @param request 参数对象
     * @return 结果对象
     */
    PermissionContext.UrlGroup.Dto urlGroupGet(PermissionContext.UrlGroup.Request request);

    /**
     * 删除
     *
     * @param request 参数对象
     * @return 结果对象
     */
    PermissionContext.UrlGroup.Dto urlGroupDelete(PermissionContext.UrlGroup.Request request);

    /**
     * 创建
     *
     * @param request 参数对象
     * @return 结果对象
     */
    PermissionContext.UrlGroup.Dto urlGroupCreate(PermissionContext.UrlGroup.Request request);

    /**
     * 修改
     *
     * @param request 参数对象
     * @return 结果对象
     */
    PermissionContext.UrlGroup.Dto urlGroupUpdate(PermissionContext.UrlGroup.Request request);

    /**
     * 列表
     *
     * @param request 参数对象
     * @return 结果对象
     */
    PermissionContext.UrlGroup.ListDto urlGroupList(PermissionContext.UrlGroup.Request request);

    /**
     * 详情
     *
     * @param request 参数对象
     * @return 结果对象
     */
    PermissionContext.Url.Details.Dto urlDetails(PermissionContext.Url.Details.Request request);

    /**
     * 详情
     *
     * @param request 参数对象
     * @return 结果对象
     */
    PermissionContext.UrlGroup.Details.Dto urlGroupDetails(PermissionContext.UrlGroup.Details.Request request);


    /**
     * 树节点
     *
     * @param request 参数对象
     * @return 结果对象
     */
    PermissionContext.Tree.Dto tree(PermissionContext.Tree.Request request);

    /**
     * 关联
     *
     * @param request 参数对象
     * @return 结果对象
     */
    PermissionContext.Association.Dto association(PermissionContext.Association.Request request);

    /**
     * 取消关联
     *
     * @param request 参数对象
     * @return 结果对象
     */
    PermissionContext.Disassociate.Dto disassociate(PermissionContext.Disassociate.Request request);

}
