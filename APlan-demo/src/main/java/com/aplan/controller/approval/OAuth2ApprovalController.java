package com.aplan.controller.approval;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

@Api(tags = "授权接口组")
@Controller
@SessionAttributes("authorizationRequest")
public class OAuth2ApprovalController {


    @ApiOperation("自定义授权页")
    @RequestMapping("/oauth/confirm_access")
    public ModelAndView getAccessConfirmation() {
        ModelAndView view = new ModelAndView();
        view.setViewName("oauth_approval");
        return view;
    }


}
