package cn.liangjq.mix.admin.service.impl;

import cn.liangjq.mix.admin.dao.RoleMapper;
import cn.liangjq.mix.admin.dao.UserMapper;
import cn.liangjq.mix.admin.dao.UserRoleMapper;
import cn.liangjq.mix.admin.exception.OperationException;
import cn.liangjq.mix.admin.service.IUserService;
import cn.liangjq.mix.admin.util.PageUtils;
import cn.liangjq.mix.common.config.JwtConfig;
import cn.liangjq.mix.common.dto.LoginVO;
import cn.liangjq.mix.common.dto.PageResponse;
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
import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.time.DateFormatUtils;
import org.apache.http.client.utils.DateUtils;
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

    @Override
    public R<String> checkLoginVO(LoginVO loginVO) {
        if (null == loginVO || StringUtils.isBlank(loginVO.getUsername())
                || StringUtils.isBlank(loginVO.getPassword())) {
            return R.fail();
        }
        User user = userMapper.findUserByUsername(loginVO.getUsername());
        if (null == user) {
            return R.fail("用户名或密码有误");
        }
        if (!Objects.equals(MD5Utils.getMd5(loginVO.getPassword()), user.getPassword())) {
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
        String token = JWTUtils.createToken(user.getUserName(), StringUtils.join(roleNameList, ","),
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
    public UserVO getUserByName(String username) {
        User user = userMapper.findUserByUsername(username);
        if (null == user) {
            return null;
        }
        UserVO userVO = new UserVO();
        BeanUtils.copyProperties(user, userVO);
        return userVO;
    }

    @Override
    public List<UserRole> listUserRoleByUserId(Long id) {
        return userRoleMapper.selectByUserId(id);
    }

    @Override
    public R<PageResponse> pageUser(UserListRequest request) {
        PageResponse pageResponse = PageUtils.setPageResult(request, () ->
                userMapper.selectByUserRequest(request));
        List<User> userList = (List<User>) pageResponse.getList();
        List<UserVO> userVOList = userList.stream().map(this::toUserVO).collect(Collectors.toList());
        pageResponse.setList(userVOList);
        return R.ok(pageResponse);
    }

    @Override
    @Transactional
    public R<String> addUser(UserAddDTO userAddDTO) {
        if (userAddDTO == null || StringUtils.isBlank(userAddDTO.getUserName())) {
            return R.fail("数据有误");
        }
        // 判断用户名是否存在
        boolean checkResult = userMapper.checkUserExistByUsername(userAddDTO.getUserName());
        if (checkResult) {
            return R.fail("用户名已存在");
        }
        User user = this.toUser(userAddDTO);
        user.add();
        int insert = userMapper.insert(user);
        this.updateUserRole(user.getId(), userAddDTO.getRoleIds());
        // 新增角色
        if (insert > 0) {
            return R.ok("新增成功");
        }
        return R.fail("新增失败");
    }

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
     * @param userId
     * @param roleIds
     */
    private void addUserRole(Long userId, Long[] roleIds) {
        if (userId == null || null==roleIds) {
            throw new OperationException("数据有误");
        }
        for(Long roleId:roleIds){
            this.doAddUserRole(userId, roleId);
        }
    }

    private void doAddUserRole(Long userId, Long roleId) {
        userRoleMapper.insert(UserRole.builder().roleId(Long.valueOf(roleId)).userId(userId).build());
    }

    @Override
    @Transactional
    public R<String> deleteUser(Long userId) {
        if (null == userId) {
            return R.fail("id无效");
        }
        // 判断用户是否存在
        User user = userMapper.selectByPrimaryKey(userId);
        if (user == null) {
            return R.fail("用户不存在");
        }
        //删除用户角色关联记录
        // TODO 暂时不删除角色关联
        //userRoleMapper.deleteUserRoleAssgin(userId);
        user.delete();
        int result = userMapper.updateByPrimaryKeySelective(user);
        this.removeUserRole(userId);
        if (result > 0) {
            return R.ok("删除成功");
        }
        return R.fail("删除失败");
    }

    @Override
    @Transactional
    public R<String> updateUser(UserUpdateDTO updateDTO) {
        if (null == updateDTO || updateDTO.getId() == null) {
            return R.fail("数据不完整");
        }
        //判断修改后的用户名是否重复（除了本身）
        boolean checkResult = userMapper.checkUserExistByIdAndName(updateDTO.getId(), updateDTO.getUserName());
        if (checkResult) {
            return R.fail("该用户名已被占用");
        }

        //先通过id查找并判断用户是否存在
        User user = userMapper.selectByPrimaryKey(updateDTO.getId());
        if (null == user) {
            return R.fail("用户不存在");
        }
        // 更新用户
        BeanUtils.copyProperties(updateDTO, user);
        if (StringUtils.isNotBlank(updateDTO.getPassword())) {
            user.modifyPwd();
        }
        user.update();
        int i = userMapper.updateByPrimaryKeySelective(user);
        this.updateUserRole(updateDTO.getId(), updateDTO.getRoleIds());
        if (i > 0) {
            return R.ok("更新成功");
        }
        return R.fail("更新失败");
    }

    @Override
    @Transactional
    public R<String> modifyPassword(UserModifyPwdDTO modifyPwdDTO) {
        if (null == modifyPwdDTO || modifyPwdDTO.getId() == null) {
            return R.fail("数据不完整");
        }
        //先通过id查找并判断用户是否存在
        User user = userMapper.selectByPrimaryKey(modifyPwdDTO.getId());
        if (null == user) {
            return R.fail("用户不存在");
        }
        user.setPassword(MD5Utils.getMd5(modifyPwdDTO.getPassword()));
        user.setGmtModified(new Date());
        userMapper.updateByPrimaryKeySelective(user);
        return R.ok("修改密码成功");
    }

    @Override
    @Transactional
    public R<String> assignRoles(UserAssignRolesDTO userAssignRolesDTO) {
        if (null == userAssignRolesDTO || userAssignRolesDTO.getId() == null || ArrayUtils.isEmpty(userAssignRolesDTO.getRoleIds())) {
            return R.fail("数据不完整");
        }
        // 判断用户是否存在
        User user = userMapper.selectByPrimaryKey(userAssignRolesDTO.getId());
        if (null == user) {
            return R.fail("用户不存在");
        }
        //判断角色是否都存在
        int countIdFromDB = roleMapper.countId(userAssignRolesDTO.getRoleIds());
        if (countIdFromDB != userAssignRolesDTO.getRoleIds().length) {
            return R.fail("部分角色不存在,分配失败");
        }
        // 先删除已有的关联
        userRoleMapper.deleteUserRoleAssgin(userAssignRolesDTO.getId());
        // 插入新增的关联
        userRoleMapper.assginUserRole(userAssignRolesDTO.getId(), userAssignRolesDTO.getRoleIds());
        return R.ok("分配成功");
    }

    @Override
    public R<UserUpdateDTO> findUser(Long userId) {
        User user = userMapper.selectByPrimaryKey(userId);
        if (null == user) {
            return R.fail("用户不存在");
        }
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
    private UserVO toUserVO(User user) {
        UserVO userVO = new UserVO();
        BeanUtils.copyProperties(user, userVO);
        if (user.getLoginDate() != null) {
            userVO.setLoginDate(DateFormatUtils.format(user.getLoginDate(), "yyyy-MM-dd HH:mm"));
        }
        if (user.getGmtCreate() != null) {
            userVO.setGmtCreate(DateFormatUtils.format(user.getGmtCreate(), "yyyy-MM-dd HH:mm"));
        }
        if (user.getGmtModified() != null) {
            userVO.setGmtModified(DateFormatUtils.format(user.getGmtModified(), "yyyy-MM-dd HH:mm"));
        }

        return userVO;
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
