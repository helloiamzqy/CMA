package controller;

import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pojo.User;
import service.UserService;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;


@RestController
@RequestMapping("user")
public class UserController {
    @Autowired
    private UserService userService;
    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
    @PostMapping
    public void addUser(@RequestParam("file")MultipartFile multiUploadFile, HttpServletRequest request){
        String birth = request.getParameter("birth");
        String username = request.getParameter("username");
        int sex = Integer.parseInt(request.getParameter("sex"));
        User user = new User();
        String filePath = "D:/uploading/"+multiUploadFile.getOriginalFilename();
        user.setSex(sex);
        user.setImg(filePath);
        user.setUsername(username);

        if(multiUploadFile != null&&multiUploadFile.getSize() > 0){
            try {
                user.setBirth(sdf.parse(birth));
                multiUploadFile.transferTo(new File(filePath));
            } catch (IllegalStateException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }catch (ParseException e){
                e.printStackTrace();
            }
        }
        userService.addUser(user);
    }
}
