/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**Controller for @link User 's pages
 *
 * @author Алена
 */
@Controller
public class UserController {
    
    @RequestMapping(value = "/registration", method = RequestMethod.GET)
    public String registration(Model model){
        model.addAttribute("message","This is registration page" );
        return "registration";
    }
    
//    @RequestMapping(value = "/registration", method = RequestMethod.POST) //заполняем форму регистрации
//    public String registration(@ModelAttribute("userForm") Users userForm,BindingResult bindingResult, Model model){
//        userValidator.validate(userForm, bindingResult); //проверяем валидацию
//        
//        //проверка на ошибки
//        if (bindingResult.hasErrors()){
//            return "registration";
//        }
//        //сохранение нового пользователя
//        userService.save(userForm);
//        
//        securityService.autoLogin(userForm.getLogin(), userForm.getPassword());
//        return "redirect:/main";
//    } 
    
    @RequestMapping(value="/login",method=RequestMethod.GET)
    public String login(Model model, String error, String logout){
        if (error!=null){
            model.addAttribute("error","Login or Password is incorrect.");
        }
        if(logout !=null){
            model.addAttribute("mesage","logger out successfuly."); 
        }
        return "login";
    }
    
    @RequestMapping(value={"/","/main"},method=RequestMethod.GET)
    public String welcome(Model model){
        return "main";
    }
    
}
