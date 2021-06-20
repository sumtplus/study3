package xyz.sumtplus.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import lombok.Data;
import lombok.extern.log4j.Log4j;
import xyz.sumtplus.domain.MemberVO;
import xyz.sumtplus.mapper.MemberMapper;
import xyz.sumtplus.security.domain.CustomUser;


/**
 * 유저디테일서비스
 *
 * @author 류슬희
 * @date   2021. 5. 19.
 */
@Log4j
@Data
public class CustomUserDetailsService implements UserDetailsService{
	@Autowired
	private MemberMapper memberMapper;

	// 로그인시에 CustomUser를 반환
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		log.warn(username);
		Long userNo = memberMapper.findUserNoBy(username);
		MemberVO vo =  memberMapper.read(userNo);
		log.warn("member mapper's vo :: "+ vo);
		// 비활성화된 계정이면 반환하지 않음
		return vo == null || !vo.isEnabled() ? null : new CustomUser(vo);
	}
}