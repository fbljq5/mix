package cn.liangjq.mix.admin.service.impl;

import cn.liangjq.mix.admin.dao.RoleMapper;
import cn.liangjq.mix.admin.dao.UserMapper;
import cn.liangjq.mix.admin.dao.UserRoleMapper;
import cn.liangjq.mix.admin.exception.OperationException;
import cn.liangjq.mix.admin.service.IRoleService;
import cn.liangjq.mix.admin.service.IUserService;
import cn.liangjq.mix.admin.util.PageUtils;
import cn.liangjq.mix.common.config.JwtConfig;
import cn.liangjq.mix.common.dto.LoginDTO;
import cn.liangjq.mix.common.dto.LoginResultDTO;
import cn.liangjq.mix.common.dto.PageResponse;
import cn.liangjq.mix.common.dto.role.RoleInfoDTO;
import cn.liangjq.mix.common.dto.user.*;
import cn.liangjq.mix.common.entity.Role;
import cn.liangjq.mix.common.entity.User;
import cn.liangjq.mix.common.entity.UserRole;
import cn.liangjq.mix.common.result.R;
import cn.liangjq.mix.common.utils.JWTUtils;
import cn.liangjq.mix.common.utils.MD5Utils;
import cn.liangjq.mix.common.utils.RedisUtil;
import com.alibaba.nacos.common.utils.Objects;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.time.DateFormatUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

/**
 * @Author: liangjq
 * @Description: 用户接口实现类
 * @Date: 2021/4/14
 */
@Service
@RequiredArgsConstructor
@Slf4j
public class UserServiceImpl implements IUserService {

    private final UserMapper userMapper;
    private final RoleMapper roleMapper;
    private final UserRoleMapper userRoleMapper;
    private final RedisUtil redisUtil;
    private final JwtConfig jwtConfig;
    private final IRoleService roleService;

    @Override
    public R<String> checkLoginVO(LoginDTO loginDTO) {
        if (null == loginDTO || StringUtils.isBlank(loginDTO.getUsername())
                || StringUtils.isBlank(loginDTO.getPassword())) {
            return R.fail();
        }
        User user = userMapper.findUserByUsername(loginDTO.getUsername());
        if (null == user) {
            return R.fail("用户名或密码有误");
        }
        if (!Objects.equals(MD5Utils.getMd5(loginDTO.getPassword()), user.getPassword())) {
            return R.fail("用户名或密码有误");
        }
        // 获得角色列表
        List<Role> roleList = roleMapper.getRoleListByUserId(user.getId());
        List<String> roleNameList = roleList.stream().map(Role::getRoleName).collect(Collectors.toList());
        if (CollectionUtils.isEmpty(roleNameList)) {
            return R.fail("当前用户没有角色权限");
        }
        // 账号密码校验成功
        // 生成token
        String token = JWTUtils.createToken(user.getUsername(), StringUtils.join(roleNameList, ","),
                jwtConfig.getExpiresSecond(), jwtConfig.getSecret());

        if (StringUtils.isBlank(token)) {
            return R.fail("token不能为空");
        }
        redisUtil.setEx(token, token, jwtConfig.getExpiresSecond(), TimeUnit.SECONDS);
        //更新用户最近登录信息
        user.setLoginDate(new Date());
        user.login();
        userMapper.updateByPrimaryKeySelective(user);
        return R.ok(token);
    }

    @Override
    public UserInfoDTO getUserByName(String username) {
        User user = userMapper.findUserByUsername(username);
        if (null == user) {
            return null;
        }
        UserInfoDTO userInfoDTO = new UserInfoDTO();
        BeanUtils.copyProperties(user, userInfoDTO);
        // 获取角色列表并组装
        List<RoleInfoDTO> roleInfoDTOList = roleService.getRoleInfoListByUserId(user.getId());
        if (!CollectionUtils.isEmpty(roleInfoDTOList)) {
            RoleInfoDTO[] roleInfoArr = new RoleInfoDTO[roleInfoDTOList.size()];
            userInfoDTO.setRoles(roleInfoDTOList.toArray(roleInfoArr));
        }
        return userInfoDTO;
    }


    @Override
    public List<UserRole> listUserRoleByUserId(Long id) {
        return userRoleMapper.selectByUserId(id);
    }

    @Override
    public R<PageResponse> pageUser(UserSearchDTO request) {
        PageResponse pageResponse = PageUtils.setPageResult(request, () ->
                userMapper.selectByUserRequest(request));
        List<User> userList = (List<User>) pageResponse.getList();
        List<UserPageDTO> userPageDTOList = userList.stream().map(this::toUserVO).collect(Collectors.toList());
        pageResponse.setList(userPageDTOList);
        return R.ok(pageResponse);
    }

    @Override
    @Transactional
    public R<String> addUser(UserAddDTO userAddDTO) {
        // 校验数据合法性
        this.checkDataValid(userAddDTO);
        // 判断用户名是否存在
        this.checkUsernameExist(userAddDTO.getUsername());
        // 新增用户
        this.doAddUser(userAddDTO);
        // 新增用户角色关联
        this.updateUserRole(userAddDTO.getId(), userAddDTO.getRoleIds());
        return R.ok("新增成功");
    }

    /**
     * 校验数据合法性
     *
     * @param userAddDTO
     */
    private void checkDataValid(UserAddDTO userAddDTO) {
        if (userAddDTO == null || StringUtils.isBlank(userAddDTO.getUsername())) {
            throw new OperationException("数据有误");
        }
    }

    /**
     * 判断用户名是否存在
     *
     * @param username
     */
    private void checkUsernameExist(String username) {
        boolean checkResult = userMapper.checkUserExistByUsername(username);
        if (checkResult) {
            throw new OperationException("用户名已存在");
        }
    }

    private void doAddUser(UserAddDTO userAddDTO) {
        User user = this.toUser(userAddDTO);
        user.add();
        int insert = userMapper.insert(user);
        if (insert <= 0) {
            throw new OperationException("新增失败");
        }
        userAddDTO.setId(user.getId());
    }

    /**
     * 更新/新增用户角色关联
     *
     * @param userId
     * @param roleIds
     */
    private void updateUserRole(Long userId, Long[] roleIds) {
        this.removeUserRole(userId);
        this.addUserRole(userId, roleIds);
    }

    /**
     * 移除用户角色关联
     *
     * @param userId
     */
    private void removeUserRole(Long userId) {
        userRoleMapper.deleteUserRoleAssgin(userId);
    }

    /**
     * 新增用户角色关联
     *
     * @param userId
     * @param roleIds
     */
    private void addUserRole(Long userId, Long[] roleIds) {
        if (userId == null || null == roleIds) {
            throw new OperationException("数据有误");
        }
        for (Long roleId : roleIds) {
            this.doAddUserRole(userId, roleId);
        }
    }

    /**
     * 持久化用户角色关联
     *
     * @param userId
     * @param roleId
     */
    private void doAddUserRole(Long userId, Long roleId) {
        userRoleMapper.insert(UserRole.builder().roleId(Long.valueOf(roleId)).userId(userId).build());
    }

    @Override
    @Transactional
    public R<String> deleteUser(Long userId) {
        // 删除前校验用户信息
        this.checkBeforeDeleteUser(userId);
        // 删除用户信息
        this.doDeleteUser(userId);
        //删除用户角色关联信息
        this.removeUserRole(userId);
        return R.ok("删除成功");
    }

    /**
     * 删除前校验数据
     *
     * @param userId
     */
    private void checkBeforeDeleteUser(Long userId) {
        if (null == userId) {
            throw new OperationException("请提供ID");
        }
        // 判断用户是否存在
        this.checkUserExist(userId);
    }

    /**
     * 删除用户信息
     *
     * @param userId
     */
    private void doDeleteUser(Long userId) {
        int result = userMapper.deleteById(userId);
        if (result <= 0) {
            throw new OperationException("删除失败");
        }
    }

    @Override
    @Transactional
    public R<String> updateUser(UserUpdateDTO updateDTO) {
        // 校验更新数据
        this.checkDataValid(updateDTO);
        // 校验用户名是否重复
        this.checkUsernameExist(updateDTO.getId(), updateDTO.getUsername());
        // 更新用户
        this.doUpdateUser(updateDTO);
        // 更新用户角色关联
        this.updateUserRole(updateDTO.getId(), updateDTO.getRoleIds());
        return R.ok("更新成功");
    }

    /**
     * 校验更新数据是否合法
     *
     * @param updateDTO
     */
    private void checkDataValid(UserUpdateDTO updateDTO) {
        if (null == updateDTO || updateDTO.getId() == null) {
            throw new OperationException("更新数据不完整");
        }

        //先通过id查找并判断用户是否存在
        this.checkUserExist(updateDTO.getId());
    }

    /**
     * 更新用户前检查用户名是否重复
     *
     * @param userId
     * @param username
     */
    private void checkUsernameExist(Long userId, String username) {
        //判断修改后的用户名是否重复（除了本身）
        boolean checkResult = userMapper.checkUserExistByIdAndName(userId, username);
        if (checkResult) {
            throw new OperationException("该用户名已被占用");
        }
    }

    /**
     * 更新用户信息
     *
     * @param updateDTO
     */
    private void doUpdateUser(UserUpdateDTO updateDTO) {
        User user = userMapper.selectByPrimaryKey(updateDTO.getId());
        BeanUtils.copyProperties(updateDTO, user);
        user.update();
        int i = userMapper.updateByPrimaryKeySelective(user);
        if (i <= 0) {
            throw new OperationException("更新失败");
        }
    }

    @Override
    @Transactional
    public R<String> modifyPassword(UserModifyPwdDTO modifyPwdDTO) {
        // 修改密码前检查数据合法性
        this.checkDataValid(modifyPwdDTO);
        // 修改密码
        this.doModifyPassword(modifyPwdDTO);
        return R.ok("修改密码成功");
    }

    /**
     * 修改密码前检查数据合法性
     *
     * @param modifyPwdDTO
     */
    private void checkDataValid(UserModifyPwdDTO modifyPwdDTO) {
        if (null == modifyPwdDTO || modifyPwdDTO.getId() == null) {
            throw new OperationException("数据不完整");
        }
        //先通过id查找并判断用户是否存在
        User user = userMapper.selectByPrimaryKey(modifyPwdDTO.getId());
        if (null == user) {
            throw new OperationException("用户不存在");
        }
    }

    /**
     * 修改密码
     *
     * @param modifyPwdDTO
     */
    private void doModifyPassword(UserModifyPwdDTO modifyPwdDTO) {
        User user = userMapper.selectByPrimaryKey(modifyPwdDTO.getId());
        user.setPassword(MD5Utils.getMd5(modifyPwdDTO.getPassword()));
        user.setGmtModified(new Date());
        int res = userMapper.updateByPrimaryKeySelective(user);
        if (res <= 0) {
            throw new OperationException("修改密码失败");
        }
    }

    /**
     * 检查用户是否存在
     *
     * @param userId
     */
    private void checkUserExist(Long userId) {
        if (null == userId) {
            throw new OperationException("请提供ID");
        }
        User user = userMapper.selectByPrimaryKey(userId);
        if (null == user) {
            throw new OperationException("用户不存在");
        }
    }

    @Override
    public R<UserUpdateDTO> findUser(Long userId) {
        // 检查用户信息
        this.checkUserExist(userId);
        User user = userMapper.selectByPrimaryKey(userId);
        UserUpdateDTO userUpdateDTO = new UserUpdateDTO();
        BeanUtils.copyProperties(user, userUpdateDTO);
        return R.ok(userUpdateDTO);
    }

    @Override
    public R logout(String token) {
        // 移除redis中的token
        redisUtil.delete(token);
        return R.ok();
    }

    @Override
    public R checkToken(String tokenStr) {
        String tokenOfRedis = redisUtil.get(tokenStr);
        if (null == tokenOfRedis) {
            return R.fail("token不存在或已过期");
        }
        return R.ok();
    }

    /**
     * 用户信息转换成userVO信息
     *
     * @param user
     * @return
     */
    private UserPageDTO toUserVO(User user) {
        UserPageDTO userPageDTO = new UserPageDTO();
        BeanUtils.copyProperties(user, userPageDTO);
        if (user.getLoginDate() != null) {
            userPageDTO.setLoginDate(DateFormatUtils.format(user.getLoginDate(), "yyyy-MM-dd HH:mm"));
        }
        if (user.getGmtCreate() != null) {
            userPageDTO.setGmtCreate(DateFormatUtils.format(user.getGmtCreate(), "yyyy-MM-dd HH:mm"));
        }
        if (user.getGmtModified() != null) {
            userPageDTO.setGmtModified(DateFormatUtils.format(user.getGmtModified(), "yyyy-MM-dd HH:mm"));
        }

        return userPageDTO;
    }

    /**
     * 数据转换，DTO 转成user
     *
     * @param userAddDTO
     * @return
     */
    private User toUser(UserAddDTO userAddDTO) {
        if (null == userAddDTO) {
            return null;
        }
        User user = new User();
        BeanUtils.copyProperties(userAddDTO, user);
        return user;
    }
}
