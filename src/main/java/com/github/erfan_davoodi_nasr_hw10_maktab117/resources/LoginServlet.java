package com.github.erfan_davoodi_nasr_hw10_maktab117.resources;

import com.github.erfan_davoodi_nasr_hw10_maktab117.api.SMS;
import com.github.erfan_davoodi_nasr_hw10_maktab117.model.User;
import com.github.erfan_davoodi_nasr_hw10_maktab117.model.dto.LoginUserRequest;
import com.github.erfan_davoodi_nasr_hw10_maktab117.util.ApplicationContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.validation.ConstraintViolation;
import javax.validation.Validator;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import static com.github.erfan_davoodi_nasr_hw10_maktab117.util.ValidatorProvider.getValidator;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {
    private static User user = null;

    private static void verifyCodeAction(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        Object authCode = session.getAttribute("authCode");
        String enteredCode = req.getParameter("authCode");
        if (authCode.equals(enteredCode)) {
            session.setAttribute("id", user.getId());
            session.setAttribute("firstName", user.getFirstname());
            session.setAttribute("lastName", user.getLastName());
            session.setAttribute("phoneNumber", user.getPhoneNumber());
            req.setAttribute("message", "user successfully logged in");
            req.getRequestDispatcher("/index.jsp").forward(req, resp);
        } else {
            req.setAttribute("message", "you entered wrong code");
            req.getRequestDispatcher("/verify.jsp").forward(req,resp);
        }
    }

    private static void sendCodeAction(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String[] results;
        LoginUserRequest userRequest = LoginUserRequest.builder()
                .phoneNumber(req.getParameter("phoneNumber").trim())
                .build();
        List<String> problems = validate(userRequest);
        if (problems.isEmpty()) {
            user = ApplicationContext.getUserService().findByPhoneNumber(userRequest);
            if (user == null) {
                req.setAttribute("message", "user not found");
                req.getRequestDispatcher("/signup.jsp").forward(req, resp);
            } else {
                results = SMS.sendSms(user.getPhoneNumber());
//                results = new String[]{"1234","200"};
                HttpSession session = req.getSession();
                session.setAttribute("authCode", results[0]);
                req.setAttribute("message", "sms successfully received: " + results[0]);
                req.getRequestDispatcher("/verify.jsp").forward(req, resp);
            }
        } else {
            req.setAttribute("message", problems);
            req.getRequestDispatcher("/login.jsp").forward(req, resp);
        }
    }

    private static List<String> validate(LoginUserRequest userRequest) {
        Validator validator = getValidator();
        Set<ConstraintViolation<LoginUserRequest>> validate = validator.validate(userRequest);
        List<String> problems = new ArrayList<>();
        for (ConstraintViolation<LoginUserRequest> loginUserRequestConstraintViolation : validate) {
            problems.add(loginUserRequestConstraintViolation.getMessage());
        }
        return problems;
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String[] results = null;
        String action = req.getParameter("action");
        if ("sendCode".equals(action)) {
            sendCodeAction(req, resp);
        } else if ("verifyCode".equals(action)) {
            verifyCodeAction(req, resp);
        }else {
            req.setAttribute("message", "bad request");
        }
    }

}
