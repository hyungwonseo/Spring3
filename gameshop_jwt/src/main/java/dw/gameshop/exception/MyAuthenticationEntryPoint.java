package dw.gameshop.exception;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import dw.gameshop.dto.BaseResponse;
import dw.gameshop.enums.ResultCode;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.io.PrintWriter;

// 스프링 시큐리티 필터 체인 단계에서 발생하는 예외상황을 처리하도록 설계된 것이므로,
// 애플리케이션의 실제 실행 로직에 진입하기 전에 발생하므로 별도로 아래와 같이 정의함
@Component
public class MyAuthenticationEntryPoint implements AuthenticationEntryPoint {

    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response,
                         AuthenticationException authException)
            throws IOException, ServletException, JsonProcessingException {
        // 인증되지 않은 사용자가 보호된 리소스에 접근했을 때 호출됨

        // 응답으로 전송할 JSON 데이터 생성
        BaseResponse<String> responseData = new BaseResponse<>();
        responseData.setResultCode(ResultCode.ERROR.name());
        responseData.setData("Authentication required");
        responseData.setMessage("인증이 실패했습니다.");

        // JSON 데이터를 문자열로 변환
        ObjectMapper mapper = new ObjectMapper();
        String jsonResponse = mapper.writeValueAsString(responseData);

        // 응답 헤더 설정
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);

        // 응답 내용 출력
        PrintWriter out = response.getWriter();
        out.print(jsonResponse);
        out.flush();
    }
}
