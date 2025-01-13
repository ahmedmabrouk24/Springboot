package com.global.security;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Optional;
import java.util.UUID;

import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.global.entity.AppUser;
import com.global.entity.TokenInfo;
import com.global.service.TokenInfoService;
import com.global.service.UserService;

import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

@Service
@RequiredArgsConstructor
@Log4j2
public class AuthService {

	private final AuthenticationManager authenticationManager;
	private final HttpServletRequest httpRequest;
	private final TokenInfoService tokenInfoService;
	private final UserService userService;
	private final JwtTokenUtils jwtTokenUtils;
	
	public JWTResponseDto login(String login, String password) {

		Authentication authentication = authenticationManager
				.authenticate(new UsernamePasswordAuthenticationToken(login, password));
		log.info("Valid userDetails Credentials");

		AppUserDetail userDetails = (AppUserDetail) authentication.getPrincipal();

		SecurityContextHolder.getContext().setAuthentication(authentication);

		TokenInfo tokenInfo = null;
		try {
			tokenInfo = createLoginToken(login, userDetails.getId());
		} catch (UnknownHostException e) {
			e.printStackTrace();
		}

		return JWTResponseDto.builder().accessToken(tokenInfo.getAccessToken())
				.refreshToken(tokenInfo.getRefreshToken()).build();
	}

	public TokenInfo createLoginToken(String username, long userId) throws UnknownHostException {

		log.info("->>>>>>>>>>>>>>>> in createLoginToken");

		String userAgent = httpRequest.getHeader(HttpHeaders.USER_AGENT);
		InetAddress ip = null;

		ip = InetAddress.getLocalHost();

		String accessTokenId = UUID.randomUUID().toString();
		String accessToken = JwtTokenUtils.generateToken(username, accessTokenId, false);
		log.info("Access Token with id: " + accessTokenId + " has been created!");

		String refreshTokenId = UUID.randomUUID().toString();
		String refreshToken = JwtTokenUtils.generateToken(username, refreshTokenId, true);
		log.info("Refresh Token with id: " + refreshTokenId + " has been created!");

		AppUser appUser = userService.findById(userId);

		TokenInfo tokenInfo = new TokenInfo(accessToken, refreshToken, userAgent, ip.getHostAddress(),
				httpRequest.getRemoteAddr(), appUser);

		return tokenInfoService.save(tokenInfo);
	}

	public AccessTokenDto refreshAccessToken(String refreshToken) {
		if (jwtTokenUtils.isTokenExpired(refreshToken)) {
			return null;
		}
		String userName = jwtTokenUtils.getUserNameFromToken(refreshToken);
		Optional<TokenInfo> refresh = tokenInfoService.findByRefreshToken(refreshToken);
		if (!refresh.isPresent()) {
			return null;
		}
		return new AccessTokenDto(JwtTokenUtils.generateToken(userName, UUID.randomUUID().toString(), false));

	}

	public void logoutUser(String refreshToken) {
		Optional<TokenInfo> tokenInfo = tokenInfoService.findByRefreshToken(refreshToken);
		if (tokenInfo.isPresent()) {
			tokenInfoService.deleteById(tokenInfo.get().getId());
		}
	}
	
}
