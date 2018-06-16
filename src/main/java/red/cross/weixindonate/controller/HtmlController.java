package red.cross.weixindonate.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Controller
public class HtmlController {
    @RequestMapping(value  = "/back-stage",method = RequestMethod.GET)
    public void backStage(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("endBuild/index.html").forward(request, response);
    }
    @RequestMapping(value  = "/backStage-fill",method = RequestMethod.GET)
    public void backStageFill(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("endBuild/index.html").forward(request, response);
    }

    @RequestMapping(value  = "/fillInfo",method = RequestMethod.GET)
    public void fillInfo(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("frontBuild/index.html").forward(request, response);
    }

    @RequestMapping(value  = "/donateResult",method = RequestMethod.GET)
    public void donateResult(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("frontBuild/index.html").forward(request, response);
    }

    @RequestMapping(value  = "/donateDetail",method = RequestMethod.GET)
    public void donateDetail(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("frontBuild/index.html").forward(request, response);
    }

    @RequestMapping(value  = "/",method = RequestMethod.GET)
    public void defaultHtml(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("frontBuild/index.html").forward(request, response);
    }
}
