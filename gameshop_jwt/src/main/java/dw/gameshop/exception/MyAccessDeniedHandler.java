package dw.gameshop.exception;

import com.fasterxml.jackson.databind.ObjectMapper;
import dw.gameshop.dto.BaseResponse;
import dw.gameshop.enums.ResultCode;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.io.PrintWriter;

// 스프링 시큐리티 필터 체인 단계에서 발생하는 예외상황을 처리하도록 설계된 것이므로,
// 애플리케이션의 실제 실행 로직에 진입하기 전에 발생하므로 별도로 아래와 같이 정의함
@Component
public class MyAccessDeniedHandler implements AccessDeniedHandler {

    @Override
    public void handle(HttpServletRequest request, HttpServletResponse response,
                       AccessDeniedException accessDeniedException)
            throws IOException, ServletException {
        // 권한이 없는 사용자가 보호된 리소스에 접근했을 때 호출됨

        // 응답으로 전송할 JSON 데이터 생성
        BaseResponse<String> responseData = new BaseResponse<>();
        responseData.setResultCode(ResultCode.ERROR.name());
        responseData.setData("Forbidden : Access denied.");
        responseData.setMessage("권한이 없습니다.");

        // JSON 데이터를 문자열로 변환
        ObjectMapper mapper = new ObjectMapper();
        String jsonResponse = mapper.writeValueAsString(responseData);

        // 응답 헤더 설정
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        response.setStatus(HttpServletResponse.SC_FORBIDDEN);

        // 응답 내용 출력
        PrintWriter out = response.getWriter();
        out.print(jsonResponse);
        out.flush();
    }
}