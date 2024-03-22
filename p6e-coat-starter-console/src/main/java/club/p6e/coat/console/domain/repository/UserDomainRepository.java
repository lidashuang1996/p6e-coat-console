package club.p6e.coat.console.domain.repository;

import club.p6e.coat.common.pagination.PaginationContext;
import club.p6e.coat.common.search.SearchableContext;
import club.p6e.coat.common.sortable.SortableContext;
import club.p6e.coat.console.domain.Repository;
import club.p6e.coat.console.domain.aggregate.UserListAggregate;
import club.p6e.coat.console.domain.entity.PasswordEntity;
import club.p6e.coat.console.domain.entity.UserEntity;
import club.p6e.coat.console.domain.identifier.UserId;

/**
 * 用户领域存储库
 *
 * @author lidashuang
 * @version 1.0
 */
public interface UserDomainRepository extends Repository<UserEntity, UserId> {

    /**
     * 创建账号
     *
     * @param ue 用户对象
     * @param pe 密码对象
     * @return 实体对象
     */
    UserEntity save(UserEntity ue, PasswordEntity pe);

    /**
     * 获取密码
     *
     * @param id ID 对象
     * @return 实体对象
     */
    PasswordEntity getPassword(UserId id);

    /**
     * 修改密码
     *
     * @param id ID 对象
     * @param pe 密码对象
     * @return 实体对象
     */
    UserEntity updatePassword(UserId id, PasswordEntity pe);

    /**
     * 列表
     *
     * @param searchable 搜索对象
     * @param sortable   排序对象
     * @param pagination 分页对象
     * @return 列表聚合根
     */
    UserListAggregate list(SearchableContext searchable, SortableContext sortable, PaginationContext pagination);

}
