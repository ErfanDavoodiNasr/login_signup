package com.github.erfan_davoodi_nasr_hw10_maktab117.resources;


import com.github.erfan_davoodi_nasr_hw10_maktab117.model.User;
import com.github.erfan_davoodi_nasr_hw10_maktab117.model.dto.SaveUserRequest;
import com.github.erfan_davoodi_nasr_hw10_maktab117.util.ApplicationContext;
import lombok.SneakyThrows;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.validation.ConstraintViolation;
import javax.validation.Validator;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import static com.github.erfan_davoodi_nasr_hw10_maktab117.util.ValidatorProvider.getValidator;


@WebServlet("/signup")
public class SignUpServlet extends HttpServlet {

    private static List<String> validate(SaveUserRequest userRequest) {
        Validator validator = getValidator();
        Set<ConstraintViolation<SaveUserRequest>> validate = validator.validate(userRequest);
        List<String> problems = new ArrayList<>();
        for (ConstraintViolation<SaveUserRequest> saveUserRequestConstraintViolation : validate) {
            problems.add(saveUserRequestConstraintViolation.getMessage());
        }
        return problems;
    }

    @Override
    @SneakyThrows
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) {
        SaveUserRequest userRequest = SaveUserRequest.builder()
                .firstName(req.getParameter("firstName").trim())
                .lastName(req.getParameter("lastName").trim())
                .phoneNumber(req.getParameter("phoneNumber").trim())
                .build();

        List<String> problems = validate(userRequest);
        if (problems.isEmpty()) {
            try {
                User save = ApplicationContext.getUserService().save(userRequest);
                HttpSession session = req.getSession();
                session.setAttribute("id", save.getId());
                session.setAttribute("firstName", save.getFirstname());
                session.setAttribute("lastName", save.getLastName());
                session.setAttribute("phoneNumber", save.getPhoneNumber());
                req.setAttribute("message", "user successfully signed up");
                req.setAttribute("salam","salammmm");
                req.getRequestDispatcher("/index.jsp").forward(req, resp);
            } catch (Exception e) {
                req.setAttribute("message", "there is some problem to signing you up");
                req.getRequestDispatcher("/signup.jsp").forward(req, resp);
            }
        } else {
            req.setAttribute("message", problems);
            req.getRequestDispatcher("/signup.jsp").forward(req, resp);
        }
    }
}
