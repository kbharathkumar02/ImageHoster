package ImageHoster.controller;

import ImageHoster.model.Comment;
import ImageHoster.model.Image;
import ImageHoster.model.User;
import ImageHoster.service.CommentService;
import ImageHoster.service.ImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.time.LocalDate;

@Controller
public class CommentController {

    @Autowired
    private CommentService commentService;

    @Autowired
    private ImageService imageService;

    @RequestMapping(value = "/image/{id}/{title}/comments", method = RequestMethod.POST)
    public String createComment(@RequestParam("comment") String text, @PathVariable("id") Integer id, @PathVariable("title") String title, Comment comment, HttpSession session) throws IOException {
        comment = new Comment();
        User user = (User) session.getAttribute("loggeduser");
        Image image = imageService.getImage(id);
        comment.setText(text);
        comment.setUser(user);
        comment.setImage(image);
        comment.setCreatedDate(LocalDate.now());
        commentService.postComment(comment);
        return "redirect:/images/"+ id + "/" + title;
    }

}
