package club.p6e.coat.console.domain.repository.impl;

import club.p6e.coat.common.pagination.JpaPaginationConverter;
import club.p6e.coat.common.pagination.PaginationContext;
import club.p6e.coat.common.search.JpaSearchableConverter;
import club.p6e.coat.common.search.SearchableContext;
import club.p6e.coat.common.sortable.JpaSortableConverter;
import club.p6e.coat.common.sortable.SortableContext;
import club.p6e.coat.common.utils.CopyUtil;
import club.p6e.coat.console.domain.aggregate.UserListAggregate;
import club.p6e.coat.console.domain.entity.PasswordEntity;
import club.p6e.coat.console.domain.entity.UserEntity;
import club.p6e.coat.console.domain.identifier.UserId;
import club.p6e.coat.console.domain.repository.UserDomainRepository;
import club.p6e.coat.console.infrastructure.model.UserAuthModel;
import club.p6e.coat.console.infrastructure.model.UserModel;
import club.p6e.coat.console.infrastructure.repository.UserAuthRepository;
import club.p6e.coat.console.infrastructure.repository.UserRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;

import java.util.Optional;
import java.util.stream.Collectors;

/**
 * 用户领域存储库实现
 *
 * @author lidashuang
 * @version 1.0
 */
@Component
public class UserDomainRepositoryImpl implements UserDomainRepository {

    /**
     * 实体管理器
     */
    @PersistenceContext
    private EntityManager entityManager;

    /**
     * 用户存储库
     */
    private final UserRepository userRepository;

    /**
     * 用户认证存储库
     */
    private final UserAuthRepository userAuthRepository;

    /**
     * 构造方法初始化
     *
     * @param userRepository     用户存储库
     * @param userAuthRepository 用户认证存储库
     */
    public UserDomainRepositoryImpl(UserRepository userRepository, UserAuthRepository userAuthRepository) {
        this.userRepository = userRepository;
        this.userAuthRepository = userAuthRepository;
    }

    @Override
    public UserEntity save(UserEntity ue) {
        if (ue.id() == null) {
            final UserModel um = userRepository.saveAndFlush(ue.convertToModel());
            return new UserEntity(new UserId(um.getId()), um);
        } else {
            final Optional<UserModel> optional = userRepository.findById(ue.id().getId());
            if (optional.isEmpty()) {
                return null;
            } else {
                final UserModel um = optional.get();
                userRepository.saveAndFlush(CopyUtil.run(ue.convertToModel(), um));
                return new UserEntity(new UserId(um.getId()), um);
            }
        }
    }

    @Override
    public UserEntity delete(UserId id) {
        final Optional<UserModel> optional = userRepository.findById(id.getId());
        if (optional.isEmpty()) {
            return null;
        } else {
            final UserModel um = optional.get();
            userRepository.delete(um);
            return new UserEntity(new UserId(um.getId()), um);
        }
    }

    @Override
    public UserEntity findById(UserId id) {
        final Optional<UserModel> optional = userRepository.findById(id.getId());
        if (optional.isEmpty()) {
            return null;
        } else {
            final UserModel um = optional.get();
            return new UserEntity(new UserId(um.getId()), um);
        }
    }

    @Override
    public UserListAggregate list(SearchableContext searchable, SortableContext sortable, PaginationContext pagination) {
        final PageRequest pr = JpaPaginationConverter.execute(pagination);
        final Page<UserModel> data = userRepository.findAll((Specification<UserModel>) (rt, qy, cb) -> {
            JpaSortableConverter.injectSort(rt, qy, cb, sortable, Sort.by(Sort.Order.asc(UserModel.ID)));
            JpaSearchableConverter.injectSearch(rt, qy, cb, searchable);
            return qy.getRestriction();
        }, pr);
        return new UserListAggregate(
                (pr.getPageNumber() + 1), pr.getPageSize(), data.getTotalElements(),
                data.getContent().stream().map(m -> new UserEntity(new UserId(m.getId()), m)).collect(Collectors.toList())
        );
    }

    @Override
    @Transactional
    public UserEntity save(UserEntity ue, PasswordEntity pe) {
        final UserModel um = userRepository.save(ue.convertToModel());
        final UserAuthModel uam = new UserAuthModel();
        uam.setId(um.getId());
        uam.setPhone(um.getPhone());
        uam.setMailbox(um.getMailbox());
        uam.setAccount(um.getAccount());
        uam.setPassword(pe.content());
        uam.setVersion(0);
        uam.setOperator(um.getOperator());
        uam.setCreateDate(um.getCreateDate());
        uam.setUpdateDate(um.getUpdateDate());
        entityManager.persist(uam);
        return new UserEntity(new UserId(um.getId()), um);
    }

    @Override
    public PasswordEntity getPassword(UserId id) {
        return userRepository
                .findById(id.getId())
                .flatMap(model -> userAuthRepository
                        .findById(model.getId())
                        .map(m -> new PasswordEntity(m.getPassword()))
                ).orElse(null);
    }

    @Override
    public UserEntity updatePassword(UserId id, PasswordEntity pe) {
        final Optional<UserModel> optional = userRepository.findById(id.getId());
        if (optional.isEmpty()) {
            return null;
        } else {
            final UserModel um = optional.get();
            userAuthRepository.findById(id.getId()).ifPresent(m ->
                    userAuthRepository.saveAndFlush(m.setPassword(pe.content())));
            return new UserEntity(new UserId(um.getId()), um);
        }
    }

}
