package cn.liangjq.mix.api.fallback;

import cn.liangjq.mix.api.service.RemoteAdminService;
import cn.liangjq.mix.common.dto.LoginVO;
import cn.liangjq.mix.common.entity.Role;
import cn.liangjq.mix.common.entity.User;
import cn.liangjq.mix.common.entity.UserRole;
import cn.liangjq.mix.common.result.R;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @Description: 远程校验接口实现
 * @Author: liangjq
 * @Date: 2021/4/1
 */
@Component
@Slf4j
public class RemoteAdminServiceFallbackImpl implements RemoteAdminService {

    @Override
    public R checkLoginVO(LoginVO loginVO) {
        return R.fail("失败了");
    }

    @Override
    public User findUserByUsername(String username) {
        return null;
    }

    @Override
    public List<UserRole> listUserRoleByUserId(Long id) {
        return null;
    }

    @Override
    public Role findRoleByRoleId(Long roleId) {
        return null;
    }
}
