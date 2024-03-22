package club.p6e.coat.console.domain.service;

import club.p6e.coat.common.pagination.PaginationContext;
import club.p6e.coat.common.search.SearchableContext;
import club.p6e.coat.common.sortable.SortableContext;
import club.p6e.coat.console.domain.aggregate.UserListAggregate;
import club.p6e.coat.console.domain.entity.PasswordEntity;
import club.p6e.coat.console.domain.entity.UserEntity;
import club.p6e.coat.console.domain.identifier.UserId;

/**
 * 用户领域服务
 *
 * @author lidashuang
 * @version 1.0
 */
public interface UserDomainService {

    /**
     * 读取
     *
     * @param id ID 对象
     * @return 实体对象
     */
    UserEntity get(UserId id);

    /**
     * 删除
     *
     * @param id ID 对象
     * @return 实体对象
     */
    UserEntity delete(UserId id);

    /**
     * 创建
     *
     * @param ue 实体对象
     * @param pe 密码实体对象
     * @return 实体对象
     */
    UserEntity create(UserEntity ue, PasswordEntity pe);

    /**
     * 重置密码
     *
     * @param id ID 对象
     * @return 实体对象
     */
    UserEntity resetPassword(UserId id);

    /**
     * 更新密码
     *
     * @param id     ID 对象
     * @param oldPwd 旧密码实体对象
     * @param newPwd 新密码实体对象
     * @return 实体对象
     */
    UserEntity updatePassword(UserId id, PasswordEntity oldPwd, PasswordEntity newPwd);

    /**
     * 更新状态
     *
     * @param id     ID 对象
     * @param status 状态
     * @return 实体对象
     */
    UserEntity updateStatus(UserId id, Integer status);

    /**
     * 更新启用标记
     *
     * @param id      ID 对象
     * @param enabled 启用标记
     * @return 实体对象
     */
    UserEntity updateEnabled(UserId id, Integer enabled);

    /**
     * 更新内置标记
     *
     * @param id       ID 对象
     * @param internal 内置标记
     * @return 实体对象
     */
    UserEntity updateInternal(UserId id, Integer internal);

    /**
     * 列表
     *
     * @param search     搜索上下文
     * @param sort       排序上下文
     * @param pagination 分页上下文
     * @return 列表聚合对象
     */
    UserListAggregate list(SearchableContext search, SortableContext sort, PaginationContext pagination);

}
